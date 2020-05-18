package ee.blakcat.pacyorky.services.facebook.services;

import com.restfb.types.Account;
import ee.blakcat.pacyorky.models.FaceBookUser;
import ee.blakcat.pacyorky.repositories.database.FaceBookUserRepositoryJPA;
import ee.blakcat.pacyorky.services.facebook.FacebookConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FacebookAccountService {
    private final FacebookConnector<Account> accountFacebookConnector;
    private final FaceBookUserRepositoryJPA faceBookUserRepositoryJPA;

    @Autowired
    public FacebookAccountService(FacebookConnector<Account> accountFacebookConnector, FaceBookUserRepositoryJPA faceBookUserRepositoryJPA) {
        this.accountFacebookConnector = accountFacebookConnector;
        this.faceBookUserRepositoryJPA = faceBookUserRepositoryJPA;
    }

    public Set<Account> allowedAccounts() {
        return accountFacebookConnector.getData().stream().filter(account ->
                faceBookUserRepositoryJPA.findByAccessTrue().stream()
                        .map(FaceBookUser::getId).collect(Collectors.toList())
                        .contains(account.getId())).collect(Collectors.toSet());
    }
}
