package ee.blakcat.pacyorky.services.facebook.connectorsimpl;

import com.restfb.FacebookClient;
import com.restfb.types.Group;
import com.restfb.types.User;
import ee.blakcat.pacyorky.services.facebook.FacebookConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class FacebookGroupConnector implements FacebookConnector<Group> {
    private final FacebookClient facebookClient;
    @Value("${appId}")
    private String appId;

    @Autowired
    public FacebookGroupConnector(FacebookClient facebookClient) {
        this.facebookClient = facebookClient;
    }

    @Override
    public Group getOne(String id) {
        return facebookClient.fetchObject(id, Group.class);
    }

    @Override
    public Collection<Group> getData() {
        return facebookClient.fetchConnection(appId + "/app_installed_groups", Group.class).getData();
    }
}
