package ee.blakcat.pacyorky.services.updateData;

import ee.blakcat.pacyorky.models.FacebookUser;
import ee.blakcat.pacyorky.models.PacyorkyEvent;
import ee.blakcat.pacyorky.repositories.database.EventRepositoryJPA;
import ee.blakcat.pacyorky.repositories.database.FacebookUserRepositoryJPA;
import ee.blakcat.pacyorky.services.pacyorky.LocationPointService;
import ee.blakcat.pacyorky.services.pacyorky.PacyorkyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UpdateServiceImpl implements UpdateService {
    private final EventRepositoryJPA eventRepositoryJPA;
    private final FacebookUserRepositoryJPA faceBookUserRepositoryJPA;
    private final PacyorkyService pacyorkyService;
    private final LocationPointService locationPointService;

    @Autowired
    public UpdateServiceImpl(EventRepositoryJPA eventRepositoryJPA, FacebookUserRepositoryJPA faceBookUserRepositoryJPA, PacyorkyService pacyorkyService, LocationPointService locationPointService) {
        this.eventRepositoryJPA = eventRepositoryJPA;
        this.faceBookUserRepositoryJPA = faceBookUserRepositoryJPA;
        this.pacyorkyService = pacyorkyService;
        this.locationPointService = locationPointService;
    }

    @Override
    public void updateAll() {
        updateUsers();
        updateEvents();
    }

    private void updateUsers() {
        Set<FacebookUser> userSet = pacyorkyService.getUsers();
        Set<String> userAtDBId = faceBookUserRepositoryJPA.findAll().stream().map(FacebookUser::getId).collect(Collectors.toSet());
        for (FacebookUser user : userSet) {
            if (!userAtDBId.contains(user.getId())) {
                faceBookUserRepositoryJPA.save(user);
            }
        }
    }

    private void updateEvents() {
        List<String> eventsIDs = eventRepositoryJPA.findAll().stream().map(PacyorkyEvent::getId).collect(Collectors.toList());
        pacyorkyService.getEvents().forEach(pacyorkyEvent -> {
            if (!eventsIDs.contains(pacyorkyEvent.getId())) locationPointService.updateLocationPoint(pacyorkyEvent);
            else {
                PacyorkyEvent eventAtDB = eventRepositoryJPA.findById(pacyorkyEvent.getId()).orElseThrow(RuntimeException::new);
                pacyorkyEvent.setLat(eventAtDB.getLat());
                pacyorkyEvent.setLng(eventAtDB.getLng());
                if (!eventAtDB.getPlace().equals(pacyorkyEvent.getPlace())) {
                    locationPointService.updateLocationPoint(pacyorkyEvent);
                }
            }
            try {
                eventRepositoryJPA.save(pacyorkyEvent);
            } catch (Exception e) {
                System.out.println("Exception when save event: id: " + pacyorkyEvent.getId() + " exception: " + e.toString());
            }
        });
    }

}
