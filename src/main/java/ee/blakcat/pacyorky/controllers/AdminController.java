package ee.blakcat.pacyorky.controllers;

import ee.blakcat.pacyorky.models.PacyorkyGroup;
import ee.blakcat.pacyorky.repositories.database.PacyorkyGroupRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final PacyorkyGroupRepositoryJPA pacyorkyGroupRepositoryJPA;
    @Value("${updateSecret}")
    private String skey;

    @Autowired
    public AdminController(PacyorkyGroupRepositoryJPA pacyorkyGroupRepositoryJPA) {
        this.pacyorkyGroupRepositoryJPA = pacyorkyGroupRepositoryJPA;
    }

    @GetMapping("/all")
    public List<PacyorkyGroup> getAll (@RequestParam String key) {
        if (key.equals(skey)) return pacyorkyGroupRepositoryJPA.findAll();
        return null;
    }

    @GetMapping("/change")
    public void changeStatus (@RequestParam String groupId, @RequestParam String key) {
        if (key.equals(skey)) {
            PacyorkyGroup pacyorkyGroup = pacyorkyGroupRepositoryJPA.findById(groupId).orElseThrow(RuntimeException::new);
            pacyorkyGroup.setAllowed(!pacyorkyGroup.isAllowed());
            pacyorkyGroupRepositoryJPA.save(pacyorkyGroup);
        }
    }

}
