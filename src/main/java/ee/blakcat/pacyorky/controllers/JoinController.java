package ee.blakcat.pacyorky.controllers;

import ee.blakcat.pacyorky.dto.GroupAnswerDTO;
import ee.blakcat.pacyorky.dto.GroupDTO;
import ee.blakcat.pacyorky.models.PacyorkyGroup;
import ee.blakcat.pacyorky.services.pacyorky.FacebookUserService;
import ee.blakcat.pacyorky.services.pacyorky.PacyorkyGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/join")
public class JoinController {

    private final PacyorkyGroupService pacyorkyGroupService;
    private final FacebookUserService facebookUserService;
    private final Logger logger = LoggerFactory.getLogger(JoinController.class);

    @Autowired
    public JoinController(PacyorkyGroupService pacyorkyGroupService, FacebookUserService facebookUserService) {
        this.pacyorkyGroupService = pacyorkyGroupService;
        this.facebookUserService = facebookUserService;
    }


    @GetMapping("/thirdstep")
    public Set<GroupDTO> thirdStep() {
        pacyorkyGroupService.updateGroups();
        Set<PacyorkyGroup> pacyorkyGroups = pacyorkyGroupService.findAll();
        Set<GroupDTO> groups = new HashSet<>();
        for (PacyorkyGroup pacyorkyGroup : pacyorkyGroups) {
            if (pacyorkyGroup.isHidden() || pacyorkyGroup.isPage()) {
                continue;
            }
            groups.add(new GroupDTO(pacyorkyGroup.getName(), pacyorkyGroup.getId()));
        }
        return groups;
    }

    @PostMapping("/fourstep")
    public String fourStep(@RequestBody GroupAnswerDTO groupAnswerDTO) {
        String token = groupAnswerDTO.getToken();
        String id = groupAnswerDTO.getUserId();
        String groupId = groupAnswerDTO.getGroupId();
        if (StringUtils.isEmpty(token) || StringUtils.isEmpty(id) || StringUtils.isEmpty(groupId)) {
            logger.warn("something empty in fourstep: token isEmpty - " + StringUtils.isEmpty(token) + ", id isEmpty - " + StringUtils.isEmpty(id) + ", groupId isEmpty - " + StringUtils.isEmpty(groupId));
            throw new RuntimeException("Wrong data!");
        }
        if (!pacyorkyGroupService.saveGroup(id, token, groupId)) return "error";
        return "fourstep";
    }

    @PostMapping("/foursteppage")
    public String fourStepPage(@RequestBody GroupAnswerDTO groupAnswerDTO) {
        String id = groupAnswerDTO.getUserId();
        String token = groupAnswerDTO.getToken();
        if (StringUtils.isEmpty(token) || StringUtils.isEmpty(id)) {
            logger.warn("something empty in fourstep: token isEmpty - " + StringUtils.isEmpty(token) + ", id isEmpty - " + StringUtils.isEmpty(id));
            throw new RuntimeException("Wrong data!");
        }
        if (facebookUserService.addUser(id, token, true) == null) return "error";
        return "fourstep";
    }
}
