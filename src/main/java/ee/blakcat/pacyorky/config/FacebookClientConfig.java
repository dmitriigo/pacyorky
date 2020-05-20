package ee.blakcat.pacyorky.config;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FacebookClientConfig {
    @Value("${appId}")
    private String appId;
    @Value("${appSecret}")
    private String appSecret;

    @Bean
    public FacebookClient getFacebookClient() {
        FacebookClient facebookClient = new DefaultFacebookClient(Version.LATEST);
        return facebookClient.createClientWithAccessToken(
                facebookClient.obtainAppAccessToken(appId, appSecret)
                        .getAccessToken());
    }
}
