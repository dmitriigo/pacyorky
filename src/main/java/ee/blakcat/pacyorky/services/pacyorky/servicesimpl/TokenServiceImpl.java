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

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

@Service
@EnableScheduling
public class TokenServiceImpl implements TokenService {
    private final FacebookClient facebookClient;
    private final FacebookUserRepositoryJPA facebookUserRepositoryJPA;
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
        if (accessToken == null) throw new RuntimeException("Token not might exchanged! " + token);
        AccessToken tokenForReturn = new AccessToken();
        tokenForReturn.setToken(accessToken.getAccessToken());
        tokenForReturn.setExpDate(accessToken.getExpires().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        return tokenForReturn;
    }

    @Scheduled (fixedDelay = 100000)
    public void updateTokens() {
        Set<FacebookUser> facebookUsers = new HashSet<>(facebookUserRepositoryJPA.findAll());
        for (FacebookUser facebookUser : facebookUsers) {
            if (facebookUser.getAccessToken().getExpDate().minusMonths(1).compareTo(LocalDate.now()) < 0) {
                facebookUser.setAccessToken(exchange(facebookUser.getAccessToken().getToken()));
                facebookUserRepositoryJPA.save(facebookUser);
            }
        }
    }
}
