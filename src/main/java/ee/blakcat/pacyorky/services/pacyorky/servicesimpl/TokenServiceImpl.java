package ee.blakcat.pacyorky.services.pacyorky.servicesimpl;

import com.restfb.FacebookClient;
import com.restfb.Parameter;
import ee.blakcat.pacyorky.models.AccessToken;
import ee.blakcat.pacyorky.models.FacebookUser;
import ee.blakcat.pacyorky.repositories.database.FacebookUserRepositoryJPA;
import ee.blakcat.pacyorky.services.pacyorky.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

@Service
@EnableScheduling
public class TokenServiceImpl implements TokenService {
    private final FacebookClient facebookClient;
    private final FacebookUserRepositoryJPA facebookUserRepositoryJPA;
    private final Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);
    @Value("${appId}")
    private String appId;
    @Value("${appSecret}")
    private String appSecret;

    @Autowired
    public TokenServiceImpl(FacebookClient facebookClient, FacebookUserRepositoryJPA facebookUserRepositoryJPA) {
        this.facebookClient = facebookClient;
        this.facebookUserRepositoryJPA = facebookUserRepositoryJPA;
    }


    @Override
    public AccessToken exchange(String token) {
        FacebookClient.AccessToken accessToken = facebookClient.fetchObject("/oauth/access_token", FacebookClient.AccessToken.class,
                Parameter.with("grant_type", "fb_exchange_token"),
                Parameter.with("fb_exchange_token", token),
                Parameter.with("client_id", appId),
                Parameter.with("client_secret", appSecret));
        if (accessToken == null) {
            logger.error("Token not might exchanged! " + token);
            throw new RuntimeException();
        }
        AccessToken tokenForReturn = new AccessToken();
        tokenForReturn.setToken(accessToken.getAccessToken());
        tokenForReturn.setExpDate(accessToken.getExpires().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        return tokenForReturn;
    }

    @Override
    public void checkToken(FacebookUser facebookUser, String token) {
        if (facebookUser.getAccessToken().getExpDate().isBefore(LocalDate.now())) {
            AccessToken accessToken = exchange(token);
            logger.info("Token renewaled for user "+facebookUser.getId()+" "+ facebookUser.getName());
            facebookUser.setAccessToken(accessToken);
        }
    }

    @Scheduled (cron = "0 0 2 * * *")
    public void updateTokens() {
        Set<FacebookUser> facebookUsers = new HashSet<>(facebookUserRepositoryJPA.findAll());
        for (FacebookUser facebookUser : facebookUsers) {
            if (facebookUser.getAccessToken().getExpDate().minusWeeks(1L).isBefore(LocalDate.now())) {
                facebookUser.setAccessToken(exchange(facebookUser.getAccessToken().getToken()));
                facebookUserRepositoryJPA.save(facebookUser);
            }
        }
    }
}
