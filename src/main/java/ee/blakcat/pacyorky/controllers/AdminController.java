package ee.blakcat.pacyorky.controllers;

import ee.blakcat.pacyorky.models.FacebookPage;
import ee.blakcat.pacyorky.models.PacyorkyGroup;
import ee.blakcat.pacyorky.repositories.database.FacebookPageRepositoryJPA;
import ee.blakcat.pacyorky.repositories.database.PacyorkyGroupRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final PacyorkyGroupRepositoryJPA pacyorkyGroupRepositoryJPA;
    private final FacebookPageRepositoryJPA facebookPageRepositoryJPA;
    private final Logger logger = LoggerFactory.getLogger(AdminController.class);
    @Value("${updateSecret}")
    private String skey;

    @Autowired
    public AdminController(PacyorkyGroupRepositoryJPA pacyorkyGroupRepositoryJPA, FacebookPageRepositoryJPA facebookPageRepositoryJPA) {
        this.pacyorkyGroupRepositoryJPA = pacyorkyGroupRepositoryJPA;
        this.facebookPageRepositoryJPA = facebookPageRepositoryJPA;
    }

    @GetMapping("/all")
    public List<PacyorkyGroup> getAll (@RequestParam String key) {
        if (key.equals(skey)) return pacyorkyGroupRepositoryJPA.findAll();
        return null;
    }

    @GetMapping("/pages")
    public List<FacebookPage> getPages (@RequestParam String key) {
        if (key.equals(skey)) return facebookPageRepositoryJPA.findAll();
        return null;
    }

    @GetMapping("/change")
    public void changeStatus (@RequestParam String groupId, @RequestParam String key) {
        if (key.equals(skey)) {
            PacyorkyGroup pacyorkyGroup = pacyorkyGroupRepositoryJPA.findById(groupId).orElseThrow(RuntimeException::new);
            pacyorkyGroup.setAllowed(!pacyorkyGroup.isAllowed());
            logger.info("group id = " + groupId + " set access " + pacyorkyGroup.isAllowed());
            pacyorkyGroupRepositoryJPA.save(pacyorkyGroup);
        }
    }

    @GetMapping("/hide")
    public void hide (@RequestParam String groupId, @RequestParam String key) {
        if (key.equals(skey)) {
            PacyorkyGroup pacyorkyGroup = pacyorkyGroupRepositoryJPA.findById(groupId).orElseThrow(RuntimeException::new);
            pacyorkyGroup.setHidden(!pacyorkyGroup.isHidden());
            logger.info("group id = " + groupId + " set hidden " + pacyorkyGroup.isHidden());
            pacyorkyGroupRepositoryJPA.save(pacyorkyGroup);
        }
    }

    @GetMapping("/changepage")
    public void changeStatusPage (@RequestParam String pageId, @RequestParam String key) {
        if (key.equals(skey)) {
            FacebookPage facebookPage = facebookPageRepositoryJPA.findById(pageId).orElseThrow(RuntimeException::new);
            facebookPage.setAllowed(!facebookPage.isAllowed());
            logger.info("group id = " + pageId + " set access " + facebookPage.isAllowed());
            facebookPageRepositoryJPA.save(facebookPage);
        }
    }
}
