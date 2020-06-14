package ee.blakcat.pacyorky.services.facebook.connectorsimpl;

import com.restfb.FacebookClient;
import com.restfb.types.User;
import ee.blakcat.pacyorky.models.FacebookUser;
import ee.blakcat.pacyorky.repositories.database.FacebookUserRepositoryJPA;
import ee.blakcat.pacyorky.services.facebook.FacebookConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class FacebookUserConnector implements FacebookConnector<User> {

    private final FacebookClient facebookClient;
    private final FacebookUserRepositoryJPA facebookUserRepositoryJPA;

    @Autowired
    public FacebookUserConnector(FacebookClient facebookClient, FacebookUserRepositoryJPA facebookUserRepositoryJPA) {
        this.facebookClient = facebookClient;
        this.facebookUserRepositoryJPA = facebookUserRepositoryJPA;
    }


    @Override
    public User getOne(String id) {
        return facebookClient.fetchObject(id, User.class);
    }

    @Override
    public Collection<User> getData() {
        List<User> users = new ArrayList<>();
        Set<FacebookUser> facebookUserSet = new HashSet<>(facebookUserRepositoryJPA.findAll());
        for (FacebookUser facebookUser : facebookUserSet) {
            User user = getOne(facebookUser.getId());
            users.add(user);
        }
        return users;
    }
}
