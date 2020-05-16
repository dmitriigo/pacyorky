package ee.blakcat.pacyorky.repositories.facebook;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.json.JsonObject;
import com.restfb.types.Account;
import com.restfb.types.Event;
import com.restfb.types.User;
import ee.blakcat.pacyorky.models.PacyorkyEvent;
import ee.blakcat.pacyorky.repositories.database.EventRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;


//@Repository
public class FaceBookConnector {

    private DefaultFacebookClient facebookClient;
    @Value("${appId}")
    private String appId;
    @Value("${appSecret}")
    private String appSecret;

    public FaceBookConnector() {
        facebookClient = new DefaultFacebookClient(Version.LATEST);
    }


    public List<PacyorkyEvent> getPacyorkyEvents() {
        List<PacyorkyEvent> pacyorkyEvents = new ArrayList<>();
        Map<Account, List<Event>> events = getAllUserEvents();
        events.forEach((account, eventsList) -> {
            eventsList.forEach(event -> pacyorkyEvents.add(getPacyorkyEvent(event, account)));
        });

        System.out.println("Events: "+pacyorkyEvents.size());
        return pacyorkyEvents;

    }

    public PacyorkyEvent getPacyorkyEvent(Event event, Account account) {
        return AdapterToEvent.convertFaceBookEventToPacyorkyEvent(event, account);
    }

    private FacebookClient getAppClient() {
        return facebookClient.createClientWithAccessToken(
                facebookClient.obtainAppAccessToken(appId, appSecret)
                        .getAccessToken());
    }

    private FacebookClient getUserClient(Account account) {
        return facebookClient.createClientWithAccessToken(account.getAccessToken());
    }

    private Set<Account> getFacebookAppAccounts() {
        FacebookClient appClient = getAppClient();
        return new HashSet<>(appClient.fetchConnection(appId + "/accounts", Account.class).getData());
    }

    private Set<User> getFaceBookAppUsers() {
        FacebookClient appClient = getAppClient();
        Set<User> usersToReturn = new HashSet<>();
        List<User> users = appClient.fetchConnection(appId + "/accounts", User.class).getData();
        for (User user : users) {
            usersToReturn.add(appClient.fetchObject(user.getId(), User.class));
        }
        System.out.println("Users: " + usersToReturn.size());
        return usersToReturn;
    }

    private Map<Account, List<Event>> getAllUserEvents() {
        Set<User> users = getFaceBookAppUsers();
        Map<Account, List<Event>> accountListMap = new HashMap<>();
        Set<Account> accounts = getFacebookAppAccounts();
        for (Account account : accounts) {
            FacebookClient client = getUserClient(account);
            List<Event> events = new ArrayList<>(client.fetchConnection("me/events", Event.class,
                    Parameter.with("fields",
                            "cover,description,end_time,name,owner,place,start_time"
                    )).getData());
            for (User user : users) {
                if (user.getId().equals(account.getId())) account.setName(user.getName());
            }
            accountListMap.put(account, events);

        }
        return accountListMap;
    }


}
