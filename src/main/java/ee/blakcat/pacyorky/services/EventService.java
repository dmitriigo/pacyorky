package ee.blakcat.pacyorky.services;

import ee.blakcat.pacyorky.models.PacyorkyEvent;
import ee.blakcat.pacyorky.repositories.database.EventRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EventService {
    private EventRepositoryJPA eventRepositoryJPA;

    @Autowired
    public EventService(EventRepositoryJPA eventRepositoryJPA) {
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
