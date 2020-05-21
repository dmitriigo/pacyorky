package ee.blakcat.pacyorky.services.facebook.services;

import com.restfb.types.Account;
import com.restfb.types.Event;
import com.restfb.types.User;
import ee.blakcat.pacyorky.services.facebook.FacebookConnector;
import ee.blakcat.pacyorky.services.facebook.FacebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class FacebookMainService {

    private final FacebookConnector<User> userFacebookConnector;
    private final FacebookService <Event> facebookEventService;
    private final FacebookService <Account> facebookAccountService;

    @Autowired
    public FacebookMainService(FacebookConnector<User> userFacebookConnector, FacebookService <Event> facebookEventService, FacebookService <Account> facebookAccountService) {
        this.userFacebookConnector = userFacebookConnector;
        this.facebookEventService = facebookEventService;
        this.facebookAccountService = facebookAccountService;
    }

    public Set<Event> getAllowedEvents() {
        return facebookEventService.getAllowedData();
    }

    public Set<Account> getAllAllowedAccounts() {
        return facebookAccountService.getAllowedData();
    }

    public Set<User> getAllUsers() {
        return new HashSet<>(userFacebookConnector.getData());
    }
}
