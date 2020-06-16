package ee.blakcat.pacyorky.services.pacyorky.servicesimpl;

import com.restfb.types.Group;
import ee.blakcat.pacyorky.models.FacebookUser;
import ee.blakcat.pacyorky.models.PacyorkyGroup;
import ee.blakcat.pacyorky.repositories.database.PacyorkyGroupRepositoryJPA;
import ee.blakcat.pacyorky.services.facebook.FacebookService;
import ee.blakcat.pacyorky.services.pacyorky.FacebookUserService;
import ee.blakcat.pacyorky.services.pacyorky.PacyorkyGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PacyorkyGroupServiceImpl implements PacyorkyGroupService {
    private final FacebookService<Group> facebookService;
    private final PacyorkyGroupRepositoryJPA pacyorkyGroupRepositoryJPA;
    private final FacebookUserService facebookUserService;

    @Autowired
    public PacyorkyGroupServiceImpl(FacebookService<Group> facebookService, PacyorkyGroupRepositoryJPA pacyorkyGroupRepositoryJPA, FacebookUserService facebookUserService) {
        this.facebookService = facebookService;
        this.pacyorkyGroupRepositoryJPA = pacyorkyGroupRepositoryJPA;
        this.facebookUserService = facebookUserService;
    }

    @Override
    @Transactional
    public void updateGroups() {
        Set<Group> groups = facebookService.getAllowedData();
        List<String> groupsAtDBId = pacyorkyGroupRepositoryJPA.findAll().stream().map(PacyorkyGroup::getId).collect(Collectors.toList());
        for (Group group : groups) {
            PacyorkyGroup pacyorkyGroup;
            if (groupsAtDBId.contains(group.getId())) {
               pacyorkyGroup = pacyorkyGroupRepositoryJPA.getOne(group.getId());
            } else {
                pacyorkyGroup = new PacyorkyGroup();
                pacyorkyGroup.setFacebookUsers(new HashSet<>());
            }
            pacyorkyGroup.setId(group.getId());
            pacyorkyGroup.setName(group.getName());
            pacyorkyGroupRepositoryJPA.save(pacyorkyGroup);
        }
    }

    @Override
    public boolean saveGroup(String userId, String token, String groupId) {
        FacebookUser facebookUser = facebookUserService.getUser(userId);
        if (facebookUser==null) facebookUser = facebookUserService.addUser(userId, token);
        PacyorkyGroup pacyorkyGroup = pacyorkyGroupRepositoryJPA.findById(groupId).orElse(createGroup(groupId));
        if (pacyorkyGroup==null) {
            throw new RuntimeException("Group id is wrong!");
        }
        if (pacyorkyGroup.getFacebookUsers()==null) pacyorkyGroup.setFacebookUsers(new HashSet<>());
        pacyorkyGroup.addUser(facebookUser);
        pacyorkyGroupRepositoryJPA.save(pacyorkyGroup);
        return true;
    }

    private PacyorkyGroup createGroup(String groupId) {
        PacyorkyGroup pacyorkyGroup = new PacyorkyGroup();
        pacyorkyGroup.setId(groupId);
        pacyorkyGroup.setFacebookUsers(new HashSet<>());
        pacyorkyGroupRepositoryJPA.save(pacyorkyGroup);
        updateGroups();
        return pacyorkyGroupRepositoryJPA.findById(groupId).orElse(null);
    }

    @Override
    public PacyorkyGroup findOne (String id) {
        return pacyorkyGroupRepositoryJPA.getOne(id);
    }

    @Override
    public Set<PacyorkyGroup> findAll() {
        return new HashSet<>(pacyorkyGroupRepositoryJPA.findAll());
    }

    @Override
    public Set<PacyorkyGroup> findAllAllowed() {
        return pacyorkyGroupRepositoryJPA.findAllByAllowedTrue();
    }
}
