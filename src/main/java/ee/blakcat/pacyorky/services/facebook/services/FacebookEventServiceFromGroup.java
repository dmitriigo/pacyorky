package ee.blakcat.pacyorky.services.facebook.services;

import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Event;
import com.restfb.types.Group;
import ee.blakcat.pacyorky.services.facebook.FacebookService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class FacebookEventServiceFromGroup implements FacebookService<Event> {
    private final FacebookClient facebookClient;
    private final FacebookService<Group> facebookService;

    @Autowired
    public FacebookEventServiceFromGroup(FacebookClient facebookClient, FacebookService<Group> facebookService) {
        this.facebookClient = facebookClient;
        this.facebookService = facebookService;
    }

    @Override
    public Set<Event> getAllowedData() {
        Set <Group> groups = facebookService.getAllowedData();
        Set <Event> events = new HashSet<>();
        for (Group group : groups) {
            events.addAll(facebookClient.fetchConnection(group.getId() + "/events", Event.class, Parameter.with("fields",
                    "cover,description,end_time,name,owner,place,start_time"
            )).getData());
        }
        return events;
    }
}
