package ee.blakcat.pacyorky.controllers;

import com.google.common.base.Strings;
import ee.blakcat.pacyorky.models.FacebookUser;
import ee.blakcat.pacyorky.models.PacyorkyAdmin;
import ee.blakcat.pacyorky.models.PacyorkyEvent;
import ee.blakcat.pacyorky.models.PacyorkyUser;
import ee.blakcat.pacyorky.repositories.database.EventRepositoryJPA;
import ee.blakcat.pacyorky.repositories.database.FacebookUserRepositoryJPA;
import ee.blakcat.pacyorky.repositories.database.PacyorkyAdminRepository;
import ee.blakcat.pacyorky.repositories.database.PacyorkyUserRepository;
import ee.blakcat.pacyorky.services.pacyorky.AdminService;
import ee.blakcat.pacyorky.services.pacyorky.UserService;
import ee.blakcat.pacyorky.services.updateData.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final EventRepositoryJPA eventRepositoryJPA;
    private final FacebookUserRepositoryJPA facebookUserRepositoryJPA;
    private final PacyorkyUserRepository pacyorkyUserRepository;
    private final UserService userService;
    private final UpdateService updateService;
    private final AdminService adminService;
    private final PacyorkyAdminRepository pacyorkyAdminRepository;
    @Value("${updateSecret}")
    private String updateKey;

    @Autowired
    public AdminController(EventRepositoryJPA eventRepositoryJPA, FacebookUserRepositoryJPA facebookUserRepositoryJPA, PacyorkyUserRepository pacyorkyUserRepository, UserService userService, UpdateService updateService, AdminService adminService, PacyorkyAdminRepository pacyorkyAdminRepository) {
        this.eventRepositoryJPA = eventRepositoryJPA;
        this.facebookUserRepositoryJPA = facebookUserRepositoryJPA;
        this.pacyorkyUserRepository = pacyorkyUserRepository;
        this.userService = userService;
        this.updateService = updateService;
        this.adminService = adminService;
        this.pacyorkyAdminRepository = pacyorkyAdminRepository;
    }


    @GetMapping("")
    public String adminPane(HttpSession session) {
        if (pacyorkyAdminRepository.findBySession(session.getId()) == null) return "login";
        return "index";
    }

    @PostMapping("/login")
    public String login(Model model, HttpSession httpSession, @RequestParam String login, @RequestParam String password) {
        if (adminService.login(login, password, httpSession)) return adminPane(httpSession);
        model.addAttribute("error", "login failed");
        return "error";
    }

    @PostMapping("/secret")
    public String secret(Model model, HttpSession httpSession, @RequestParam String secret) {
        if (pacyorkyAdminRepository.findByLogin("primary") == null) {
            adminService.addAdmin("primary", updateKey);
        }
        if (secret.equals(updateKey)) {
            PacyorkyAdmin pacyorkyAdmin = pacyorkyAdminRepository.findByLogin("primary");
            pacyorkyAdmin.setSession(httpSession.getId());
            pacyorkyAdminRepository.save(pacyorkyAdmin);
            return adminPane(httpSession);
        }
        model.addAttribute("error", "login failed");
        return "error";
    }

    @PostMapping("/mail-users")
    @Transactional
    public String editMailUser(@RequestParam String mail, @RequestParam(required = false) String id, @RequestParam String control,
                               @RequestParam String confirmed, @RequestParam String lang, @RequestParam String period, Model model, HttpSession session) {
        if (pacyorkyAdminRepository.findBySession(session.getId()) == null) return "login";
        if (Strings.isNullOrEmpty(id)) {
            userService.updateUser(mail, control, confirmed, lang, period);
        } else {
            userService.updateUser(mail, id, control, confirmed, lang, period);
        }

        return getMailUsers(id, model, session, false);
    }

    @GetMapping(value = {"/mail-users", "/mail-users/{id}"})
    public String getMailUsers(@PathVariable(required = false) String id, Model model, HttpSession session, @RequestParam(required = false) boolean add) {
        if (pacyorkyAdminRepository.findBySession(session.getId()) == null) return "login";
        model.addAttribute("path", "/mail-users");
        if (Strings.isNullOrEmpty(id)) {
            if (add) {
                Map<String, String> data = new HashMap<>();
                data.put("mail", "");
                data.put("id", "");
                data.put("control", "");
                data.put("confirmed", "");
                data.put("lang", "");
                data.put("period", "");
                model.addAttribute("data", data);
                return "edit";
            } else {

                model.addAttribute("data", pacyorkyUserRepository.findAll());
                return "data";
            }
        } else {
            Map<String, String> data = new HashMap<>();
            PacyorkyUser pacyorkyUser = pacyorkyUserRepository.getOne(Long.parseLong(id));
            data.put("mail", pacyorkyUser.geteMail());
            data.put("id", String.valueOf(pacyorkyUser.getId()));
            data.put("control", pacyorkyUser.getControlString());
            data.put("confirmed", String.valueOf(pacyorkyUser.isConfirmed()));
            data.put("lang", String.valueOf(pacyorkyUser.getMailLang().ordinal()));
            data.put("period", String.valueOf(pacyorkyUser.getMailSendPeriod().ordinal()));
            model.addAttribute("data", data);
            return "edit";
        }
    }

    @GetMapping("/update")
    public String update(Model model, HttpSession session) {
        if (pacyorkyAdminRepository.findBySession(session.getId()) == null) return "login";
        updateService.updateAll();
        return "index";
    }

    @PostMapping("/fb-users")
    public String editBFUsers(@RequestParam(required = false) String id, @RequestParam String name, @RequestParam String access, Model model, HttpSession session) {
        if (pacyorkyAdminRepository.findBySession(session.getId()) == null) return "login";
        if (Strings.isNullOrEmpty(id)) return "error";
        else {
            FacebookUser facebookUser = facebookUserRepositoryJPA.getOne(id);
            facebookUser.setName(name);
            facebookUser.setAccess(Boolean.parseBoolean(access));
            facebookUserRepositoryJPA.save(facebookUser);
        }
        return getFBUsers(id, model, session, false);
    }

    @GetMapping(value = {"/fb-users", "/fb-users/{id}"})
    public String getFBUsers(@PathVariable(required = false) String id, Model model, HttpSession session, @RequestParam(required = false) boolean add) {
        if (pacyorkyAdminRepository.findBySession(session.getId()) == null) return "login";
        model.addAttribute("path", "/fb-users");
        if (Strings.isNullOrEmpty(id)) {
            if (add) {
                Map<String, String> data = new HashMap<>();
                data.put("id", "");
                data.put("name", "");
                data.put("access", "");
                model.addAttribute("data", data);
                return "edit";
            } else {
                model.addAttribute("data", facebookUserRepositoryJPA.findAll());
                return "data";
            }
        } else {
            Map<String, String> data = new HashMap<>();
            FacebookUser facebookUser = facebookUserRepositoryJPA.getOne(id);
            data.put("id", facebookUser.getId());
            data.put("name", facebookUser.getName());
            data.put("access", String.valueOf(facebookUser.isAccess()));
            model.addAttribute("data", data);
            return "edit";
        }


    }

    @GetMapping(value = {"/groups", "/groups/{id}"})
    public String getGroups(@PathVariable(required = false) String id, Model model, HttpSession session, @RequestParam(required = false) boolean add) {
        model.addAttribute("error", "in development");
        return "error";
       /*
        if (pacyorkyAdminRepository.findBySession(session.getId())==null) return "login";
        model.addAttribute("path", "/groups");
        if (Strings.isNullOrEmpty(id)){
            if (add) {
                return "edit";
            } else {
                model.addAttribute("data", new String[]{"in development"});
                return "data";
            }
        } else {
            model.addAttribute("data", pacyorkyUserRepository.getOne(Long.parseLong(id)));
            return "edit";
        }
*/

    }

    @GetMapping(value = {"/admins", "/admins/{id}"})
    public String getAdmins(@PathVariable(required = false) String id, Model model, HttpSession session, @RequestParam(required = false) boolean add) {
        if (pacyorkyAdminRepository.findBySession(session.getId()) == null) return "login";
        model.addAttribute("path", "/admins");
        if (Strings.isNullOrEmpty(id)) {
            if (add) {
                Map<String, String> data = new HashMap<>();
                data.put("login", "");
                data.put("id", "");
                data.put("password", "");
                data.put("session", "");
                model.addAttribute("data", data);
                return "edit";
            } else {

                model.addAttribute("data", pacyorkyAdminRepository.findAll());
                return "data";
            }
        } else {
            Map<String, String> data = new HashMap<>();
            PacyorkyAdmin pacyorkyAdmin = pacyorkyAdminRepository.getOne(Long.parseLong(id));
            data.put("login", pacyorkyAdmin.getLogin());
            data.put("id", String.valueOf(pacyorkyAdmin.getId()));
            data.put("password", pacyorkyAdmin.getPassword());
            data.put("session", pacyorkyAdmin.getSession());
            model.addAttribute("data", data);
            return "edit";
        }
    }

    @GetMapping(value = {"/events", "/events/{id}"})
    public String getEvents(@PathVariable(required = false) String id, Model model, HttpSession session, @RequestParam(required = false) boolean add) {
        if (pacyorkyAdminRepository.findBySession(session.getId()) == null) return "login";
        model.addAttribute("path", "/events");
        if (Strings.isNullOrEmpty(id)) {
            if (add) {
                Map<String, String> data = new HashMap<>();
                data.put("name", "");
                data.put("id", "");
                model.addAttribute("data", data);
                return "edit";
            } else {

                model.addAttribute("data", eventRepositoryJPA.findAll());
                return "data";
            }
        } else {
            Map<String, String> data = new HashMap<>();
            PacyorkyEvent pacyorkyEvent = eventRepositoryJPA.getOne(id);
            data.put("name", pacyorkyEvent.getName());
            data.put("id", pacyorkyEvent.getId());
            data.put("description", pacyorkyEvent.getDescription());
            model.addAttribute("data", data);
            return "edit";
        }
    }
}
