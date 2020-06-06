package ee.blakcat.pacyorky.services.facebook.connectorsimpl;

import com.restfb.FacebookClient;
import com.restfb.types.Group;
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
    public Collection<Group> getData() {
        return facebookClient.fetchConnection(appId + "/groups", Group.class).getData();
    }
}
