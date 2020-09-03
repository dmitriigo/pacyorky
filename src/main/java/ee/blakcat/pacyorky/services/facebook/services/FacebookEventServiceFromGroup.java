package ee.blakcat.pacyorky.services.facebook.services;

import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Account;
import com.restfb.types.Event;
import ee.blakcat.pacyorky.models.FacebookPage;
import ee.blakcat.pacyorky.models.FacebookUser;
import ee.blakcat.pacyorky.models.PacyorkyGroup;
import ee.blakcat.pacyorky.repositories.database.FacebookPageRepositoryJPA;
import ee.blakcat.pacyorky.repositories.database.FacebookUserRepositoryJPA;
import ee.blakcat.pacyorky.repositories.database.PacyorkyGroupRepositoryJPA;
import ee.blakcat.pacyorky.services.facebook.FacebookService;
import ee.blakcat.pacyorky.services.pacyorky.PacyorkyGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FacebookEventServiceFromGroup implements FacebookService<Event> {
    private final FacebookClient facebookClient;
    private final PacyorkyGroupService pacyorkyGroupService;
    private final FacebookUserRepositoryJPA facebookUserRepositoryJPA;
    private final PacyorkyGroupRepositoryJPA pacyorkyGroupRepositoryJPA;
    private final FacebookPageRepositoryJPA facebookPageRepositoryJPA;
    private final Logger logger = LoggerFactory.getLogger(FacebookEventServiceFromGroup.class);

    @Autowired
    public FacebookEventServiceFromGroup(FacebookClient facebookClient, PacyorkyGroupService pacyorkyGroupService, FacebookUserRepositoryJPA facebookUserRepositoryJPA, PacyorkyGroupRepositoryJPA pacyorkyGroupRepositoryJPA, FacebookPageRepositoryJPA facebookPageRepositoryJPA) {
        this.facebookClient = facebookClient;
        this.pacyorkyGroupService = pacyorkyGroupService;
        this.facebookUserRepositoryJPA = facebookUserRepositoryJPA;
        this.pacyorkyGroupRepositoryJPA = pacyorkyGroupRepositoryJPA;
        this.facebookPageRepositoryJPA = facebookPageRepositoryJPA;
    }

    @Override
    @Transactional
    public Set<Event> getAllowedData() {
        Set<PacyorkyGroup> groups = pacyorkyGroupService.findAllAllowed();
        logger.info("allowed groups: " + groups.size());
        Set<Event> events = new HashSet<>();
        for (PacyorkyGroup group : groups) {
            FacebookUser facebookUser = group.getFacebookUser();
            try {
                FacebookClient client = facebookClient.createClientWithAccessToken(facebookUser.getAccessToken().getToken());
                List<Event> fromFB = client.fetchConnection(group.getId() + "/events", Event.class, Parameter.with("fields",
                        "cover,description,end_time,name,owner,place,start_time"
                )).getData();
                fromFB.forEach(event -> {
                    Event.Owner owner = new Event.Owner();
                    owner.setId(group.getId());
                    owner.setName(group.getName());
                    event.setOwner(owner);
                });
                events.addAll(fromFB);
            } catch (Exception e) {
                logger.error("wrong token, exception: " + e.toString());
            }
        }
        events.addAll(getFromPages());
        return events;
    }
    
    private Set<Event> getFromPages() {
        Set<Event> events = new HashSet<>();
        try {
            List<FacebookUser> users = facebookUserRepositoryJPA.findByPageTrue();
            List<String> allowedPages = facebookPageRepositoryJPA.findAllByAllowedTrue().stream()
                    .map(FacebookPage::getId).collect(Collectors.toList());
            for (FacebookUser user : users) {
                FacebookClient facebookClient1 = facebookClient.createClientWithAccessToken(user.getAccessToken().getToken());
                List<Account> pages = facebookClient1.fetchConnection(user.getId()+"/accounts", Account.class).getData();
                for (Account page : pages) {
                    if (facebookPageRepositoryJPA.findById(page.getId()).orElse(null) == null) {
                        FacebookPage facebookPage = new FacebookPage();
                        facebookPage.setId(page.getId());
                        facebookPage.setName(page.getName());
                        facebookPageRepositoryJPA.save(facebookPage);
                    }
                    if (!allowedPages.contains(page.getId())) {
                        continue;
                    }
                    List<Event> fromPage = facebookClient1.fetchConnection(page.getId()+"/events", Event.class).getData();
                    for (Event event : fromPage) {
                        Event.Owner owner = new Event.Owner();
                        owner.setId(page.getId());
                        owner.setName(page.getName());
                        event.setOwner(owner);
                        PacyorkyGroup pacyorkyGroup = new PacyorkyGroup();
                        pacyorkyGroup.setId(page.getId());
                        pacyorkyGroup.setName(page.getName());
                        pacyorkyGroup.setPage(true);
                        pacyorkyGroupRepositoryJPA.save(pacyorkyGroup);
                        events.add(event);
                    }
                }
            }
            
        } catch (Exception e) {
            logger.error("wrong token, exception: " + e.toString());
        }
        return events;
    }
}
