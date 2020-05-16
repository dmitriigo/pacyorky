package ee.blakcat.pacyorky.repositories.facebook;

import ee.blakcat.pacyorky.models.PacyorkyEvent;

import java.util.List;

public interface LFaceBookConnector {
    List<PacyorkyEvent> getPacyorkyEvents();
}
