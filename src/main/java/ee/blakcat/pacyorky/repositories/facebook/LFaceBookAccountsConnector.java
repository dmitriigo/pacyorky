package ee.blakcat.pacyorky.repositories.facebook;

import com.restfb.FacebookClient;
import com.restfb.types.Account;

import java.util.Set;

public interface LFaceBookAccountsConnector {
    Set<Account> getFacebookAppAccounts(FacebookClient appClient);
}
