package ee.blakcat.pacyorky.controllers;

import ee.blakcat.pacyorky.services.pacyorky.FacebookUserService;
import ee.blakcat.pacyorky.services.pacyorky.PacyorkyGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping ("/join")
public class JoinController {

    private final PacyorkyGroupService pacyorkyGroupService;

    @Autowired
    public JoinController(PacyorkyGroupService pacyorkyGroupService) {
        this.pacyorkyGroupService = pacyorkyGroupService;
    }

    @GetMapping("/first-step")
    public String firstStep () {
        return "/join/first-step";
    }

    @GetMapping("/second-step")
    public String secondStep () {
        return "/join/second-step";
    }

    @GetMapping("/third-step")
    public String thirdStep(Model model) {
        pacyorkyGroupService.updateGroups();
        model.addAttribute("groups", pacyorkyGroupService.findAll());
        return "/join/third-step";
    }

    @PostMapping("/four-step")
    public String fourStep (@RequestParam String id,  @RequestParam String link, @RequestParam String groupId) {
       link = link.split("#", 2)[1];
       String [] params = link.split("&");
        Map<String, String> paramsMap = new HashMap<>();
        for (String param : params) {
            String [] oneParam = param.split("=", 2);
            paramsMap.put(oneParam[0], oneParam[1]);
        }
        String token = paramsMap.get("access_token");
        if (StringUtils.isEmpty(token)||StringUtils.isEmpty(id)||StringUtils.isEmpty(groupId)) return "error";
        if (!pacyorkyGroupService.saveGroup(id, token, groupId)) return "error";
        return "/join/four-step";
    }
}
