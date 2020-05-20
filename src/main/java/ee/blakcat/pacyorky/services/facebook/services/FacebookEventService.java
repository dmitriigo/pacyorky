package ee.blakcat.pacyorky.services.facebook.services;

import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class FacebookEventService {

    private final FacebookAccountService facebookAccountService;
    private final FacebookClient facebookClient;

    @Autowired
    public FacebookEventService(FacebookAccountService facebookAccountService, FacebookClient facebookClient) {
        this.facebookAccountService = facebookAccountService;
        this.facebookClient = facebookClient;
    }

    public Set<Event> getAllAllowedEvents() {
        Set<Event> events = new HashSet<>();
        facebookAccountService.allowedAccounts().forEach(account -> {
            Set<Event> oneUserEvents = getOneUserEvents(facebookClient.createClientWithAccessToken(account.getAccessToken()));
            oneUserEvents.forEach(event -> {
                Event.Owner owner = new Event.Owner();
                owner.setId(account.getId());
                event.setOwner(owner);
                events.add(event);
            });
        });
        return events;
    }

    private Set<Event> getOneUserEvents(FacebookClient userClient) {
        return new HashSet<>(userClient.fetchConnection("me/events", Event.class,
                Parameter.with("fields",
                        "cover,description,end_time,name,owner,place,start_time"
                )).getData());
    }
}
