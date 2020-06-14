package ee.blakcat.pacyorky.services.pacyorky.servicesimpl;

import ee.blakcat.pacyorky.models.PacyorkyEvent;
import ee.blakcat.pacyorky.repositories.database.EventRepositoryJPA;
import ee.blakcat.pacyorky.services.pacyorky.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepositoryJPA eventRepositoryJPA;

    @Autowired
    public EventServiceImpl(EventRepositoryJPA eventRepositoryJPA) {
        this.eventRepositoryJPA = eventRepositoryJPA;
    }


    public PacyorkyEvent findById(String id) {
        return eventRepositoryJPA.getOne(id);
    }

    public List<PacyorkyEvent> findAll() {
        List<PacyorkyEvent> pacyorkyEvents = new ArrayList<>(eventRepositoryJPA.findAll());
        pacyorkyEvents.sort(Comparator.comparing(PacyorkyEvent::getStartTime));
        return pacyorkyEvents;
    }

}
