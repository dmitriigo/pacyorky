package ee.blakcat.pacyorky.services.pacyorky;

import ee.blakcat.pacyorky.models.PacyorkyEvent;

import java.util.List;

public interface EventService {
    PacyorkyEvent findById(String id);
    List<PacyorkyEvent> findAll();

}
