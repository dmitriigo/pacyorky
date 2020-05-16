package ee.blakcat.pacyorky.repositories.facebook;

import com.restfb.FacebookClient;
import com.restfb.types.Account;
import com.restfb.types.User;
import ee.blakcat.pacyorky.models.FaceBookUser;

import java.util.Set;

public interface LFaceBookUsersConnector {
    Set<FaceBookUser> getFaceBookAppUsers(FacebookClient appClient);
    FacebookClient getUserClient(Account account, FacebookClient facebookClient);
}
