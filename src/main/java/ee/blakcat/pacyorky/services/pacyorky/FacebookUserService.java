package ee.blakcat.pacyorky.services.pacyorky;

import ee.blakcat.pacyorky.models.FacebookUser;

import java.util.Set;

public interface FacebookUserService {
  void updateUsers();

    Set<FacebookUser> findAll();

    FacebookUser addUser(String id, String token);

    FacebookUser saveUser(FacebookUser facebookUser);

    FacebookUser getUser(String id);
}
