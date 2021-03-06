package ee.blakcat.pacyorky.services.facebook.services;

import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.exception.FacebookOAuthException;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
                if (facebookUser == null) throw new RuntimeException("User at group not found!");
                FacebookClient client = facebookClient.createClientWithAccessToken(facebookUser.getAccessToken().getToken());
                List<Event> fromFB = fetchEventsFromFb(client, group.getId());
                fromFB.forEach(event -> {
                    Event.Owner owner = new Event.Owner();
                    owner.setId(group.getId());
                    owner.setName(group.getName());
                    event.setOwner(owner);
                });
                events.addAll(fromFB);
            } catch (FacebookOAuthException e) {
                logger.error("wrong token, exception: " + e.toString() + " user: " + (facebookUser == null ? "" : facebookUser.toString()), e);
                if (facebookUser != null) {
                    group.setFacebookUser(null);
                    group.setAllowed(false);
                    pacyorkyGroupService.updateGroup(group);
                }
            } catch (Exception e) {
                logger.error("Error when fetch group data", e);
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
                List<Account> pages = new ArrayList<>();
                try {
                    pages = facebookClient1.fetchConnection(user.getId() + "/accounts", Account.class).getData();
                } catch (FacebookOAuthException e) {
                    facebookUserRepositoryJPA.delete(user);
                    logger.error(user.getName() + "HARD DELETED TOKEN DEAD: " + e.getMessage());
                    logger.error(e.getMessage(), e.fillInStackTrace());
                } catch (Exception e) {
                    logger.error("Error when fetch page data", e);
                }
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
                    List<Event> fromPage = fetchEventsFromFb(facebookClient1, page.getId());
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
            logger.error("wrong token, exception: " + e.toString(), e);
        }
        return events;
    }

    private List<Event> fetchEventsFromFb(FacebookClient facebookClient, String id) {
        return facebookClient.fetchConnection(id + "/events", Event.class, Parameter.with("fields",
                "cover,description,end_time,name,owner,place,start_time"
        )).getData();
    }
}
