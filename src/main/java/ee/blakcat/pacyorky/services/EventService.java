package ee.blakcat.pacyorky.services;

import ee.blakcat.pacyorky.models.PacyorkyEvent;
import ee.blakcat.pacyorky.repositories.database.EventRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class EventService {
    private EventRepositoryJPA eventRepositoryJPA;

    @Autowired
    public EventService(EventRepositoryJPA eventRepositoryJPA) {
        this.eventRepositoryJPA = eventRepositoryJPA;
    }


    public PacyorkyEvent findById (String id) {
        return eventRepositoryJPA.findById(id).get();
    }

    public Set<PacyorkyEvent> findAll () {
        Set<PacyorkyEvent> pacyorkyEvents = new HashSet<>();
      eventRepositoryJPA.findAll().forEach(pacyorkyEvents::add);
        return pacyorkyEvents;
    }

}
