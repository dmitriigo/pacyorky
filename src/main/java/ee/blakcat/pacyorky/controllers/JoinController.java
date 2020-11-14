package ee.blakcat.pacyorky.controllers;

import ee.blakcat.pacyorky.dto.GroupAnswerDTO;
import ee.blakcat.pacyorky.dto.GroupDTO;
import ee.blakcat.pacyorky.models.PacyorkyGroup;
import ee.blakcat.pacyorky.services.pacyorky.FacebookUserService;
import ee.blakcat.pacyorky.services.pacyorky.PacyorkyGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/join")
public class JoinController {

    private final PacyorkyGroupService pacyorkyGroupService;
    private final FacebookUserService facebookUserService;
    private final JavaMailSender javaMailSender;
    private final Logger logger = LoggerFactory.getLogger(JoinController.class);
    @Value("${log.sendto}")
    private String mail;
    private final String from = "no-reply@pacyorky.ee";

    @Autowired
    public JoinController(PacyorkyGroupService pacyorkyGroupService, FacebookUserService facebookUserService, JavaMailSender javaMailSender) {
        this.pacyorkyGroupService = pacyorkyGroupService;
        this.facebookUserService = facebookUserService;
        this.javaMailSender = javaMailSender;
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

    @GetMapping("/notify")
    public ResponseEntity<String> notifyMe() {
        notifyMe("Somebody try to join");
        return new ResponseEntity<String>(HttpStatus.OK);
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
        notifyMe("New group! "+id);
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
        notifyMe("New Page! "+id);
        return "fourstep";
    }

    private void notifyMe(String msg) {
        javaMailSender.send(mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            message.setSubject("new!");
            message.setFrom(from);
            message.setTo(mail);;
            message.setText(msg, false);
        });
    }
}
