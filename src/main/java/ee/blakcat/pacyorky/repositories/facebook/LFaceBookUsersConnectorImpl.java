package ee.blakcat.pacyorky.repositories.facebook;

import com.restfb.FacebookClient;
import com.restfb.types.Account;
import com.restfb.types.User;
import ee.blakcat.pacyorky.models.FaceBookUser;
import ee.blakcat.pacyorky.repositories.database.FaceBookUserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class LFaceBookUsersConnectorImpl implements LFaceBookUsersConnector {
    @Autowired
    private FaceBookUserRepositoryJPA faceBookUserRepositoryJPA;
    @Value("${appId}")
    private String appId;


    public Set<FaceBookUser> getFaceBookAppUsers(FacebookClient appClient) {
        saveUserToDTB(appClient);
        return new HashSet<>(faceBookUserRepositoryJPA.findByAccessTrue());
    }

    public FacebookClient getUserClient(Account account, FacebookClient facebookClient) {
        return facebookClient.createClientWithAccessToken(account.getAccessToken());
    }

    private void saveUserToDTB(FacebookClient appClient) {
        List<String> usersIdFromDB = faceBookUserRepositoryJPA.findAll().stream().map(FaceBookUser::getId).collect(Collectors.toList());
        List<User> users = appClient.fetchConnection(appId + "/accounts", User.class).getData();
        for (User user : users) {
           if (!usersIdFromDB.contains(user.getId()))faceBookUserRepositoryJPA.save(new FaceBookUser(appClient.fetchObject(user.getId(), User.class)));
        }
        System.out.println(faceBookUserRepositoryJPA.findAll().size() + " fbr size");
    }
}
