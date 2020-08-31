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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping ("/join")
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
            groups.add(new GroupDTO(pacyorkyGroup.getName(), pacyorkyGroup.getId()));
        }
        return groups;
    }

    @PostMapping("/fourstep")
    public String fourStep (@RequestBody GroupAnswerDTO groupAnswerDTO) {
        String token = getToken(groupAnswerDTO.getUrl());
        String id = groupAnswerDTO.getUserId();
        String groupId = groupAnswerDTO.getGroupId();
        if (StringUtils.isEmpty(token)||StringUtils.isEmpty(id)||StringUtils.isEmpty(groupId)) {
            logger.warn("something empty in fourstep: token isEmpty - " + StringUtils.isEmpty(token) + ", id isEmpty - " + StringUtils.isEmpty(id) + ", groupId isEmpty - " + StringUtils.isEmpty(groupId));
            return "error";
        }
        if (!pacyorkyGroupService.saveGroup(id, token, groupId)) return "error";
        return "fourstep";
    }

    @PostMapping("/foursteppage")
    public String fourStepPage (@RequestBody GroupAnswerDTO groupAnswerDTO) {
        String id = groupAnswerDTO.getUserId();
        String token = getToken(groupAnswerDTO.getUrl());
        if (StringUtils.isEmpty(token)||StringUtils.isEmpty(id)) {
            logger.warn("something empty in fourstep: token isEmpty - " + StringUtils.isEmpty(token) + ", id isEmpty - " + StringUtils.isEmpty(id));
            return "error";
        }
        if (facebookUserService.addUser(id, token, true) == null) return "error";
        return "fourstep";
    }

    private String getToken (String url) {
        url = url.split("#", 2)[1];
        String [] params = url.split("&");
        Map<String, String> paramsMap = new HashMap<>();
        for (String param : params) {
            String [] oneParam = param.split("=", 2);
            paramsMap.put(oneParam[0], oneParam[1]);
        }
        return paramsMap.get("access_token");
    }
}
