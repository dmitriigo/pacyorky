package ee.blakcat.pacyorky.services.pacyorky;


import ee.blakcat.pacyorky.models.FacebookUser;
import ee.blakcat.pacyorky.models.PacyorkyEvent;

import java.util.Set;

public interface PacyorkyService {
    Set<FacebookUser> getUsers();
    Set<PacyorkyEvent> getEvents();
}
