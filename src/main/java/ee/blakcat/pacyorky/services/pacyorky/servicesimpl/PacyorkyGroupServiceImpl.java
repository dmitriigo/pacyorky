package ee.blakcat.pacyorky.services.pacyorky.servicesimpl;

import com.restfb.types.Group;
import ee.blakcat.pacyorky.models.AccessToken;
import ee.blakcat.pacyorky.models.FacebookUser;
import ee.blakcat.pacyorky.models.PacyorkyGroup;
import ee.blakcat.pacyorky.repositories.database.PacyorkyGroupRepositoryJPA;
import ee.blakcat.pacyorky.services.facebook.FacebookService;
import ee.blakcat.pacyorky.services.pacyorky.FacebookUserService;
import ee.blakcat.pacyorky.services.pacyorky.PacyorkyGroupService;
import ee.blakcat.pacyorky.services.pacyorky.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PacyorkyGroupServiceImpl implements PacyorkyGroupService {
    private final FacebookService<Group> facebookService;
    private final PacyorkyGroupRepositoryJPA pacyorkyGroupRepositoryJPA;
    private final FacebookUserService facebookUserService;
    private final TokenService tokenService;
    private final Logger logger = LoggerFactory.getLogger(PacyorkyGroupServiceImpl.class);
    @Autowired
    public PacyorkyGroupServiceImpl(FacebookService<Group> facebookService, PacyorkyGroupRepositoryJPA pacyorkyGroupRepositoryJPA, FacebookUserService facebookUserService, TokenService tokenService) {
        this.facebookService = facebookService;
        this.pacyorkyGroupRepositoryJPA = pacyorkyGroupRepositoryJPA;
        this.facebookUserService = facebookUserService;
        this.tokenService = tokenService;
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
                pacyorkyGroup = createGroup(group.getId());
            }
            pacyorkyGroup.setName(group.getName());
            pacyorkyGroupRepositoryJPA.save(pacyorkyGroup);
        }
    }

    @Override
    public boolean saveGroup(String userId, String token, String groupId) {
        FacebookUser facebookUser = facebookUserService.getUser(userId);
        if (facebookUser==null) facebookUser = facebookUserService.addUser(userId, token, false);
        PacyorkyGroup pacyorkyGroup = pacyorkyGroupRepositoryJPA.findById(groupId).get();
        if (pacyorkyGroup == null) pacyorkyGroup = createGroup(groupId);
        tokenService.checkToken(facebookUser, token);
        pacyorkyGroup.setFacebookUser(facebookUser);
        pacyorkyGroupRepositoryJPA.save(pacyorkyGroup);
        logger.info("Group id= " + groupId + " saved successfully");
        return true;
    }



    private PacyorkyGroup createGroup(String groupId) {
        PacyorkyGroup pacyorkyGroup = new PacyorkyGroup();
        pacyorkyGroup.setId(groupId);
        pacyorkyGroupRepositoryJPA.save(pacyorkyGroup);
        updateGroups();
        pacyorkyGroup = pacyorkyGroupRepositoryJPA.findById(groupId).get();
        if (pacyorkyGroup==null) {
            logger.error("Group id=" + groupId + " is wrong!");
            throw new RuntimeException();
        }
        return pacyorkyGroup;
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
