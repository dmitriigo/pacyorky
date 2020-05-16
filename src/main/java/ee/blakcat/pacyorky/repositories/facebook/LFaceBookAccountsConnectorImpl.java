package ee.blakcat.pacyorky.repositories.facebook;

import com.restfb.FacebookClient;
import com.restfb.types.Account;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class LFaceBookAccountsConnectorImpl implements LFaceBookAccountsConnector {
    @Value("${appId}")
    private String appId;

    public Set<Account> getFacebookAppAccounts(FacebookClient appClient) {
        return new HashSet<>(appClient.fetchConnection(appId + "/accounts", Account.class).getData());
    }
}
