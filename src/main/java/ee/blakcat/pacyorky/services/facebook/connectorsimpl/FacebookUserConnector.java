package ee.blakcat.pacyorky.services.facebook.connectorsimpl;

import com.restfb.FacebookClient;
import com.restfb.types.Account;
import com.restfb.types.User;
import ee.blakcat.pacyorky.services.facebook.FacebookConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class FacebookUserConnector implements FacebookConnector<User> {

    private final FacebookClient facebookClient;
    private final FacebookConnector<Account> accountFacebookConnector;

    @Autowired
    public FacebookUserConnector(FacebookClient facebookClient, FacebookConnector<Account> accountFacebookConnector) {
        this.facebookClient = facebookClient;
        this.accountFacebookConnector = accountFacebookConnector;
    }

    @Override
    public User getOne() {
        return getData().stream().findAny().orElseThrow(RuntimeException::new);
    }

    public User getOne(String id) {
        return facebookClient.fetchObject(id, User.class);
    }

    @Override
    public Collection<User> getData() {
        List<Account> accounts = new ArrayList<>(accountFacebookConnector.getData());
        List<User> users = new ArrayList<>();
        for (Account account : accounts) {
            User user = getOne(account.getId());
            users.add(user);
        }
        return users;
    }
}
