package ee.blakcat.pacyorky.services.pacyorky;

import ee.blakcat.pacyorky.models.AccessToken;
import ee.blakcat.pacyorky.models.FacebookUser;

public interface TokenService {
    AccessToken exchange(String token);

    void checkToken(FacebookUser facebookUser, String token);
}
