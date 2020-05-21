package ee.blakcat.pacyorky.services.pacyorky;

import com.restfb.types.Event;
import com.restfb.types.User;
import ee.blakcat.pacyorky.adapters.Adapter;
import ee.blakcat.pacyorky.models.FacebookUser;
import ee.blakcat.pacyorky.models.PacyorkyEvent;
import ee.blakcat.pacyorky.repositories.database.FacebookUserRepositoryJPA;
import ee.blakcat.pacyorky.services.facebook.services.FacebookMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PacyorkyServiceImpl implements PacyorkyService {
    private final Adapter<User, FacebookUser> facebookUserToDatabaseUserAdapter;
    private final Adapter<Event, PacyorkyEvent> facebookToPacyorkyEventAdapter;
    private final FacebookMainService facebookMainService;
    private final FacebookUserRepositoryJPA faceBookUserRepositoryJPA;

    @Autowired
    public PacyorkyServiceImpl(Adapter<User, FacebookUser> facebookUserToDatabaseUserAdapter, Adapter<Event, PacyorkyEvent> facebookToPacyorkyEventAdapter, FacebookMainService facebookMainService, FacebookUserRepositoryJPA faceBookUserRepositoryJPA) {
        this.facebookUserToDatabaseUserAdapter = facebookUserToDatabaseUserAdapter;
        this.facebookToPacyorkyEventAdapter = facebookToPacyorkyEventAdapter;
        this.facebookMainService = facebookMainService;
        this.faceBookUserRepositoryJPA = faceBookUserRepositoryJPA;
    }

    public Set<FacebookUser> getUsers() {
        return new HashSet<>(facebookUserToDatabaseUserAdapter.convertAll(facebookMainService.getAllUsers()));
    }

    public Set<PacyorkyEvent> getEvents() {
        return facebookMainService.getAllowedEvents().stream().map(this::convertEvent).collect(Collectors.toSet());
    }

    private PacyorkyEvent convertEvent(Event event) {
        setNameToEvent(event);
        return facebookToPacyorkyEventAdapter.convert(event);

    }

    private Event setNameToEvent(Event event) {
        Map<String, String> idAndNamesUsers = new HashMap<>();
        faceBookUserRepositoryJPA.findAll().forEach(faceBookUser -> idAndNamesUsers.put(faceBookUser.getId(), faceBookUser.getName()));
        Event.Owner owner = event.getOwner();
        owner.setName(idAndNamesUsers.get(owner.getId()));
        event.setOwner(owner);
        return event;
    }
}
