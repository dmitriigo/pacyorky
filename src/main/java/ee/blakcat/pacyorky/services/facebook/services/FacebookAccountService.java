package ee.blakcat.pacyorky.services.facebook.services;

import com.restfb.types.Account;
import ee.blakcat.pacyorky.models.FacebookUser;
import ee.blakcat.pacyorky.repositories.database.FacebookUserRepositoryJPA;
import ee.blakcat.pacyorky.services.facebook.FacebookConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FacebookAccountService {
    private final FacebookConnector<Account> accountFacebookConnector;
    private final FacebookUserRepositoryJPA faceBookUserRepositoryJPA;

    @Autowired
    public FacebookAccountService(FacebookConnector<Account> accountFacebookConnector, FacebookUserRepositoryJPA faceBookUserRepositoryJPA) {
        this.accountFacebookConnector = accountFacebookConnector;
        this.faceBookUserRepositoryJPA = faceBookUserRepositoryJPA;
    }

    public Set<Account> allowedAccounts() {
        return accountFacebookConnector.getData().stream().filter(account ->
                faceBookUserRepositoryJPA.findByAccessTrue().stream()
                        .map(FacebookUser::getId).collect(Collectors.toList())
                        .contains(account.getId())).collect(Collectors.toSet());
    }
}
