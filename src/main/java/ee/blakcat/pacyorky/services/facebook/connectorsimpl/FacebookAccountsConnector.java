package ee.blakcat.pacyorky.services.facebook.connectorsimpl;

import com.restfb.FacebookClient;
import com.restfb.types.Account;
import ee.blakcat.pacyorky.services.facebook.FacebookConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class FacebookAccountsConnector implements FacebookConnector<Account> {

    private final FacebookClient facebookClient;
    @Value("${appId}")
    private String appId;

    @Autowired
    public FacebookAccountsConnector(FacebookClient facebookClient) {
        this.facebookClient = facebookClient;
    }

    @Override
    public Account getOne() {
        return getData().stream().findAny().orElseThrow(RuntimeException::new);

    }

    @Override
    public Collection<Account> getData() {
        return facebookClient.fetchConnection(appId + "/accounts", Account.class).getData();
    }
}
