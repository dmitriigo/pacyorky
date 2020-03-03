package ee.blakcat.pacyorky.services.updateData;

import ee.blakcat.pacyorky.models.PacyorkyEvent;
import ee.blakcat.pacyorky.repositories.database.EventRepositoryJPA;
import ee.blakcat.pacyorky.repositories.facebook.FaceBookConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UpdateService {
    private EventRepositoryJPA eventRepositoryJPA;
    private FaceBookConnector faceBookConnector;

    @Autowired
    public UpdateService(EventRepositoryJPA eventRepositoryJPA, FaceBookConnector faceBookConnector) {
        this.eventRepositoryJPA = eventRepositoryJPA;
        this.faceBookConnector = faceBookConnector;
    }

    public void updateAll () {
        updateEvents();
    }

    private void updateEvents () {
        Set<PacyorkyEvent> pacyorkyEvents = faceBookConnector.getPacyorkyEvents();
        eventRepositoryJPA.saveAll(pacyorkyEvents);
    }
}
