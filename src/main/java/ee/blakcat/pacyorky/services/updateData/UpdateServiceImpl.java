package ee.blakcat.pacyorky.services.updateData;

import ee.blakcat.pacyorky.models.FacebookUser;
import ee.blakcat.pacyorky.models.MailLang;
import ee.blakcat.pacyorky.models.PacyorkyEvent;
import ee.blakcat.pacyorky.models.PacyorkyUser;
import ee.blakcat.pacyorky.repositories.database.EventRepositoryJPA;
import ee.blakcat.pacyorky.repositories.database.FacebookUserRepositoryJPA;
import ee.blakcat.pacyorky.repositories.database.PacyorkyUserRepository;
import ee.blakcat.pacyorky.services.email.MailSender;
import ee.blakcat.pacyorky.services.email.MailService;
import ee.blakcat.pacyorky.services.pacyorky.LocationPointService;
import ee.blakcat.pacyorky.services.pacyorky.PacyorkyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UpdateServiceImpl implements UpdateService {
    private final EventRepositoryJPA eventRepositoryJPA;
    private final FacebookUserRepositoryJPA faceBookUserRepositoryJPA;
    private final PacyorkyService pacyorkyService;
    private final LocationPointService locationPointService;
    private final Logger logger = LoggerFactory.getLogger(UpdateServiceImpl.class);
    private final MailService<PacyorkyEvent> mailService;

    @Autowired
    public UpdateServiceImpl(EventRepositoryJPA eventRepositoryJPA, FacebookUserRepositoryJPA faceBookUserRepositoryJPA, PacyorkyService pacyorkyService, LocationPointService locationPointService, MailService<PacyorkyEvent> mailService) {
        this.eventRepositoryJPA = eventRepositoryJPA;
        this.faceBookUserRepositoryJPA = faceBookUserRepositoryJPA;
        this.pacyorkyService = pacyorkyService;
        this.locationPointService = locationPointService;
        this.mailService = mailService;
    }

    @Override
    public void updateAll() {
        updateUsers();
        updateEvents();
    }

    private void sendMails (Set<PacyorkyEvent> events) {
       mailService.updateTask(events);
    }

    private void updateUsers() {
        Set<FacebookUser> userSet = pacyorkyService.getUsers();
        logger.info("Users for update: " + userSet.size());
        Set<String> userAtDBId = faceBookUserRepositoryJPA.findAll().stream().map(FacebookUser::getId).collect(Collectors.toSet());
        for (FacebookUser user : userSet) {
            if (!userAtDBId.contains(user.getId())) {
                faceBookUserRepositoryJPA.save(user);
            }
        }
    }

    private void updateEvents() {
        List<String> eventsIDs = eventRepositoryJPA.findAll().stream().map(PacyorkyEvent::getId).collect(Collectors.toList());
        Set<PacyorkyEvent> events = pacyorkyService.getEvents();
        Set<PacyorkyEvent> eventsForMail = new HashSet<>();
        logger.info("Events for update: "+events.size());
        for (PacyorkyEvent pacyorkyEvent : events) {
            if (!eventsIDs.contains(pacyorkyEvent.getId())) {
                locationPointService.updateLocationPoint(pacyorkyEvent);
                eventsForMail.add(pacyorkyEvent);
            }
            else {
                PacyorkyEvent eventAtDB = eventRepositoryJPA.findById(pacyorkyEvent.getId()).orElseThrow(RuntimeException::new);
                pacyorkyEvent.setLat(eventAtDB.getLat());
                pacyorkyEvent.setLng(eventAtDB.getLng());
                if (!eventAtDB.getPlace().equals(pacyorkyEvent.getPlace())) {
                    locationPointService.updateLocationPoint(pacyorkyEvent);
                }
            }
            try {
                eventRepositoryJPA.save(pacyorkyEvent);
            } catch (Exception e) {
                System.out.println("Exception when save event: id: " + pacyorkyEvent.getId() + " exception: " + e.toString());
            }
        }
        if (!eventsForMail.isEmpty())  sendMails(eventsForMail);

    }

}
