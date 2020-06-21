package ee.blakcat.pacyorky.services.facebook.services;

import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Event;
import ee.blakcat.pacyorky.models.FacebookUser;
import ee.blakcat.pacyorky.models.PacyorkyGroup;
import ee.blakcat.pacyorky.services.facebook.FacebookService;
import ee.blakcat.pacyorky.services.pacyorky.PacyorkyGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FacebookEventServiceFromGroup implements FacebookService<Event> {
    private final FacebookClient facebookClient;
    private final PacyorkyGroupService pacyorkyGroupService;
    private final Logger logger = LoggerFactory.getLogger(FacebookEventServiceFromGroup.class);
    @Autowired
    public FacebookEventServiceFromGroup(FacebookClient facebookClient, PacyorkyGroupService pacyorkyGroupService) {
        this.facebookClient = facebookClient;
        this.pacyorkyGroupService = pacyorkyGroupService;
    }

    @Override
    @Transactional
    public Set<Event> getAllowedData() {
        Set <PacyorkyGroup> groups = pacyorkyGroupService.findAllAllowed();
        Set <Event> events = new HashSet<>();
        for (PacyorkyGroup group : groups) {
            Set<FacebookUser> facebookUsers = group.getFacebookUsers();
            for (FacebookUser facebookUser : facebookUsers) {
                try {
                   FacebookClient client = facebookClient.createClientWithAccessToken(facebookUser.getAccessToken().getToken());
                  List<Event> fromFB = client.fetchConnection(group.getId() + "/events", Event.class, Parameter.with("fields",
                           "cover,description,end_time,name,owner,place,start_time"
                   )).getData();
                  fromFB.forEach(event -> {
                      Event.Owner owner = new Event.Owner();
                      owner.setId(group.getId());
                      owner.setName(group.getName());
                      event.setOwner(owner);
                  });
                  events.addAll(fromFB);
                   break;
                } catch (Exception e) {
                    logger.error("wrong token, exception: " + e.toString());
                }
            }
        }
        return events;
    }
}
