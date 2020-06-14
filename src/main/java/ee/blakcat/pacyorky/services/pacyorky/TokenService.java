package ee.blakcat.pacyorky.services.pacyorky;

import ee.blakcat.pacyorky.models.AccessToken;

public interface TokenService {
    AccessToken exchange(String token);

}
