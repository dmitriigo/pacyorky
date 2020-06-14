package ee.blakcat.pacyorky.services.facebook.services;

import com.restfb.types.Event;
import com.restfb.types.Group;
import ee.blakcat.pacyorky.models.PacyorkyGroup;
import ee.blakcat.pacyorky.repositories.database.PacyorkyGroupRepositoryJPA;
import ee.blakcat.pacyorky.services.facebook.FacebookConnector;
import ee.blakcat.pacyorky.services.facebook.FacebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class FacebookGroupService implements FacebookService<Group> {
    private final FacebookConnector<Group> facebookConnector;
    private final PacyorkyGroupRepositoryJPA pacyorkyGroupRepositoryJPA;

    @Autowired
    public FacebookGroupService(FacebookConnector<Group> facebookConnector, PacyorkyGroupRepositoryJPA pacyorkyGroupRepositoryJPA) {
        this.facebookConnector = facebookConnector;
        this.pacyorkyGroupRepositoryJPA = pacyorkyGroupRepositoryJPA;
    }


    @Override
    public Set<Group> getAllowedData() {
        return new HashSet<>(facebookConnector.getData());
    }
}
