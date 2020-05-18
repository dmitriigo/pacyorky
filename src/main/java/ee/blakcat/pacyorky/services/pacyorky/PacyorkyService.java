package ee.blakcat.pacyorky.services.pacyorky;

import com.restfb.types.Event;
import ee.blakcat.pacyorky.adapters.FacebookToPacyorkyEventAdapter;
import ee.blakcat.pacyorky.adapters.FacebookUserToDatabaseUserAdapter;
import ee.blakcat.pacyorky.models.FaceBookUser;
import ee.blakcat.pacyorky.models.PacyorkyEvent;
import ee.blakcat.pacyorky.repositories.database.FaceBookUserRepositoryJPA;
import ee.blakcat.pacyorky.services.facebook.services.FacebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PacyorkyService {
    private final FacebookUserToDatabaseUserAdapter facebookUserToDatabaseUserAdapter;
    private final FacebookToPacyorkyEventAdapter facebookToPacyorkyEventAdapter;
    private final FacebookService facebookService;
    private final FaceBookUserRepositoryJPA faceBookUserRepositoryJPA;

    @Autowired
    public PacyorkyService(FacebookUserToDatabaseUserAdapter facebookUserToDatabaseUserAdapter, FacebookToPacyorkyEventAdapter facebookToPacyorkyEventAdapter, FacebookService facebookService, FaceBookUserRepositoryJPA faceBookUserRepositoryJPA) {
        this.facebookUserToDatabaseUserAdapter = facebookUserToDatabaseUserAdapter;
        this.facebookToPacyorkyEventAdapter = facebookToPacyorkyEventAdapter;
        this.facebookService = facebookService;
        this.faceBookUserRepositoryJPA = faceBookUserRepositoryJPA;
    }

    public Set<FaceBookUser> getUsers() {
        return new HashSet<>(facebookUserToDatabaseUserAdapter.convertAll(facebookService.getAllUsers()));
    }

    public Set<PacyorkyEvent> getEvents() {
        return facebookService.getAllowedEvents().stream().map(this::convertEvent).collect(Collectors.toSet());
    }

    private PacyorkyEvent convertEvent(Event event) {
        event = setNameToEvent(event);
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
