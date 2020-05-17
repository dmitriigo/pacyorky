package ee.blakcat.pacyorky.services.facebook.services;

import com.restfb.types.Account;
import com.restfb.types.Event;
import com.restfb.types.User;
import ee.blakcat.pacyorky.services.facebook.FacebookConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class FacebookService {

    private final FacebookConnector<User> userFacebookConnector;
    private final FacebookEventService facebookEventService;
    private final FacebookAccountService facebookAccountService;

    @Autowired
    public FacebookService(FacebookConnector<User> userFacebookConnector, FacebookEventService facebookEventService, FacebookAccountService facebookAccountService) {
        this.userFacebookConnector = userFacebookConnector;
        this.facebookEventService = facebookEventService;
        this.facebookAccountService = facebookAccountService;
    }

    public Set<Event> getAllowedEvents() {
        return facebookEventService.getAllAllowedEvents();
    }

    public Set<Account> getAllAllowedAccounts() {
        return facebookAccountService.allowedAccounts();
    }

    public Set<User> getAllUsers() {
        return new HashSet<>(userFacebookConnector.getData());
    }
}
