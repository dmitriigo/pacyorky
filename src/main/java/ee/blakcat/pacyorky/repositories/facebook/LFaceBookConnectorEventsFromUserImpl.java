package ee.blakcat.pacyorky.repositories.facebook;

import com.restfb.FacebookClient;
import com.restfb.types.Account;
import com.restfb.types.Event;
import ee.blakcat.pacyorky.models.FaceBookUser;
import ee.blakcat.pacyorky.models.PacyorkyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class LFaceBookConnectorEventsFromUserImpl implements LFaceBookConnector {

    @Autowired
    private FacebookClient faceBookClient;
    @Autowired
    private LFaceBookUsersConnector faceBookAppUsersConnector;
    @Autowired
    private LFaceBookEventsConnector faceBookEventsConnector;
    @Autowired
    private LFaceBookAccountsConnector faceBookAccountsConnector;

    @Override
    public List<PacyorkyEvent> getPacyorkyEvents() {
        ArrayList<PacyorkyEvent> pacyorkyEvents = new ArrayList<>();
        Set<FaceBookUser> userSet = faceBookAppUsersConnector.getFaceBookAppUsers(faceBookClient);
        userSet.forEach(System.out::println);
        Set<Account> accounts = faceBookAccountsConnector.getFacebookAppAccounts(faceBookClient);
        for (Account account : accounts) {
            if (userSet.stream().map(FaceBookUser::getId).collect(Collectors.toList()).contains(account.getId())) {
                List<Event> events = getUserEvents(account, faceBookClient);
                setNameFromUserToAccount(userSet, account);
                pacyorkyEvents.addAll(convertEventToPacyorkyEvent(account, events));
            }
        }
        System.out.println("Events: " + pacyorkyEvents.size());
        pacyorkyEvents.forEach(pacyorkyEvent -> System.out.println("piv " + pacyorkyEvent));
        return pacyorkyEvents;
    }

    private List<Event> getUserEvents(Account account, FacebookClient facebookClient) {
        FacebookClient client = faceBookAppUsersConnector.getUserClient(account, facebookClient);
        return faceBookEventsConnector.getEventList(client);
    }

    private List<PacyorkyEvent> convertEventToPacyorkyEvent(Account account, List<Event> eventsList) {
        return eventsList.stream().map(event -> AdapterToEvent.convertFaceBookEventToPacyorkyEvent(event, account)).collect(Collectors.toList());
    }

    private void setNameFromUserToAccount(Set<FaceBookUser> userSet, Account account) {
        for (FaceBookUser user : userSet) {
            if (user.getId().equals(account.getId())) account.setName(user.getName());
        }
    }
}
