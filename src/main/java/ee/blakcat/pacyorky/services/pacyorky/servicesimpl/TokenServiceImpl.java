package ee.blakcat.pacyorky.services.pacyorky.servicesimpl;

import com.restfb.FacebookClient;
import com.restfb.Parameter;
import ee.blakcat.pacyorky.models.AccessToken;
import ee.blakcat.pacyorky.models.FacebookUser;
import ee.blakcat.pacyorky.repositories.database.FacebookUserRepositoryJPA;
import ee.blakcat.pacyorky.services.notifications.TelegramNotificator;
import ee.blakcat.pacyorky.services.pacyorky.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

@Service
@EnableScheduling
public class TokenServiceImpl implements TokenService {
    private final FacebookClient facebookClient;
    private final FacebookUserRepositoryJPA facebookUserRepositoryJPA;
    private final Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);
    private final TelegramNotificator telegramNotificator;
    @Value("${appId}")
    private String appId;
    @Value("${appSecret}")
    private String appSecret;

    @Autowired
    public TokenServiceImpl(FacebookClient facebookClient, FacebookUserRepositoryJPA facebookUserRepositoryJPA, TelegramNotificator telegramNotificator) {
        this.facebookClient = facebookClient;
        this.facebookUserRepositoryJPA = facebookUserRepositoryJPA;
        this.telegramNotificator = telegramNotificator;
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
        LocalDate expire;
        if (accessToken.getExpires() == null) {
            expire = LocalDate.now().plusMonths(3L);
        } else {
            expire = accessToken.getExpires().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }
        tokenForReturn.setExpDate(expire);
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

    @Scheduled (cron = "0 0 15 * * *")
    public void updateTokens() {
        Set<FacebookUser> facebookUsers = new HashSet<>(facebookUserRepositoryJPA.findAll());
        for (FacebookUser facebookUser : facebookUsers) {
            if (facebookUser.getAccessToken().getExpDate().minusWeeks(1L).isBefore(LocalDate.now())) {
                telegramNotificator.sendNotification("try update user "+facebookUser.toString());
                facebookUser.setAccessToken(exchange(facebookUser.getAccessToken().getToken()));
                facebookUserRepositoryJPA.save(facebookUser);
            }
        }
    }

    @Scheduled (cron = "0 0 14 * * *")
    public void checkTokens() {
        if (facebookUserRepositoryJPA.getWrongTokenUsersCount(LocalDateTime.now()) > 0) {
            telegramNotificator.sendNotification("Found users before now, please check");
        }
        if (facebookUserRepositoryJPA.getWrongTokenUsersCount(LocalDateTime.now().plusWeeks(1L)) > 0) {
            telegramNotificator.sendNotification("Found users before week, please check");
        }

    }
}
