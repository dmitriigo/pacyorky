package ee.blakcat.pacyorky.services.pacyorky.servicesimpl;

import com.restfb.types.User;
import ee.blakcat.pacyorky.models.FacebookUser;
import ee.blakcat.pacyorky.repositories.database.FacebookUserRepositoryJPA;
import ee.blakcat.pacyorky.services.facebook.FacebookConnector;
import ee.blakcat.pacyorky.services.pacyorky.FacebookUserService;
import ee.blakcat.pacyorky.services.pacyorky.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class FaceBookUserServiceImpl implements FacebookUserService {

    private final FacebookUserRepositoryJPA facebookUserRepositoryJPA;
    private final FacebookConnector<User> facebookConnector;
    private final TokenService tokenService;
    private final Logger logger = LoggerFactory.getLogger(FaceBookUserServiceImpl.class);

    @Autowired
    public FaceBookUserServiceImpl(FacebookUserRepositoryJPA facebookUserRepositoryJPA, FacebookConnector<User> facebookConnector, TokenService tokenService) {
        this.facebookUserRepositoryJPA = facebookUserRepositoryJPA;
        this.facebookConnector = facebookConnector;
        this.tokenService = tokenService;
    }

    @Override
    public void updateUsers() {
        Set<FacebookUser> facebookUserSet = new HashSet<>(facebookUserRepositoryJPA.findAll());
        for (FacebookUser facebookUser : facebookUserSet) {
            User remoteUser = facebookConnector.getOne(facebookUser.getId());
            if (remoteUser != null) {
                facebookUser.setName(remoteUser.getName());
            } else {
                logger.error("Wrong user id=" + facebookUser.getId());
                throw new RuntimeException();
            }
            facebookUserRepositoryJPA.save(facebookUser);
        }
        logger.info("Users update: " + facebookUserSet.size());
    }

    @Override
    public Set<FacebookUser> findAll() {
        return new HashSet<>(facebookUserRepositoryJPA.findAll());
    }

    @Override
    public FacebookUser addUser(String id, String token) {
        return facebookUserRepositoryJPA.findById(id).orElse(createNewUser(id, token));
    }

    @Override
    public FacebookUser saveUser(FacebookUser facebookUser) {
        return facebookUserRepositoryJPA.save(facebookUser);
    }

    @Override
    public FacebookUser getUser(String id) {
        return facebookUserRepositoryJPA.findById(id).orElse(null);
    }

    private FacebookUser createNewUser(String id, String token) {
        FacebookUser facebookUser = new FacebookUser();
        facebookUser.setAccessToken(tokenService.exchange(token));
        facebookUser.setId(id);
        facebookUserRepositoryJPA.save(facebookUser);
        updateUsers();
        return facebookUserRepositoryJPA.findById(id).orElseThrow(RuntimeException::new);
    }
}
