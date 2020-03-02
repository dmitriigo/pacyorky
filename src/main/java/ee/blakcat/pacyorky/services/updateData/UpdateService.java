package ee.blakcat.pacyorky.services.updateData;

import ee.blakcat.pacyorky.models.PacyorkyEvent;
import ee.blakcat.pacyorky.repositories.database.RepositoryJPA;
import ee.blakcat.pacyorky.repositories.facebook.FaceBookConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UpdateService {
    private RepositoryJPA repositoryJPA;
    private FaceBookConnector faceBookConnector;

    @Autowired
    public UpdateService(RepositoryJPA repositoryJPA, FaceBookConnector faceBookConnector) {
        this.repositoryJPA = repositoryJPA;
        this.faceBookConnector = faceBookConnector;
    }

    public void updateAll () {
        updateEvents();
    }

    private void updateEvents () {
        Set<PacyorkyEvent> pacyorkyEvents = faceBookConnector.getPacyorkyEvents();
        repositoryJPA.saveAll(pacyorkyEvents);
    }
}
