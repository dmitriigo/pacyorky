package ee.blakcat.pacyorky.services.pacyorky;

import com.restfb.types.Group;
import ee.blakcat.pacyorky.models.PacyorkyGroup;

import java.util.Set;

public interface PacyorkyGroupService {
    void updateGroups();


    boolean saveGroup(String userId, String token, String groupId);

    void updateGroup(PacyorkyGroup group);

    PacyorkyGroup findOne(String id);

    Set<PacyorkyGroup> findAll();

    Set<PacyorkyGroup> findAllAllowed();
}
