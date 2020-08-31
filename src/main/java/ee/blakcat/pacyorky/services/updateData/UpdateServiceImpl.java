package ee.blakcat.pacyorky.services.updateData;

import ee.blakcat.pacyorky.models.PacyorkyEvent;
import ee.blakcat.pacyorky.repositories.database.EventRepositoryJPA;
import ee.blakcat.pacyorky.services.email.MailService;
import ee.blakcat.pacyorky.services.pacyorky.FacebookUserService;
import ee.blakcat.pacyorky.services.pacyorky.LocationPointService;
import ee.blakcat.pacyorky.services.pacyorky.PacyorkyGroupService;
import ee.blakcat.pacyorky.services.pacyorky.PacyorkyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@EnableScheduling
public class UpdateServiceImpl implements UpdateService {
    private final EventRepositoryJPA eventRepositoryJPA;
    private final PacyorkyService pacyorkyService;
    private final LocationPointService locationPointService;
    private final Logger logger = LoggerFactory.getLogger(UpdateServiceImpl.class);
    private final MailService<PacyorkyEvent> mailService;
    private final PacyorkyGroupService pacyorkyGroupService;
    private final FacebookUserService facebookUserService;

    @Autowired
    public UpdateServiceImpl(EventRepositoryJPA eventRepositoryJPA, PacyorkyService pacyorkyService, LocationPointService locationPointService, MailService<PacyorkyEvent> mailService, PacyorkyGroupService pacyorkyGroupService, FacebookUserService facebookUserService) {
        this.eventRepositoryJPA = eventRepositoryJPA;
        this.pacyorkyService = pacyorkyService;
        this.locationPointService = locationPointService;
        this.mailService = mailService;
        this.pacyorkyGroupService = pacyorkyGroupService;
        this.facebookUserService = facebookUserService;
    }

    @Override
    @Scheduled(fixedRate = 1800000)
    public void updateAll() {
        pacyorkyGroupService.updateGroups();
        facebookUserService.updateUsers();
        updateEvents();
    }

    private void sendMails(Set<PacyorkyEvent> events) {
        mailService.updateTask(events);
    }


    private void updateEvents() {
        List<String> eventsIDs = eventRepositoryJPA.findAll().stream().map(PacyorkyEvent::getId).collect(Collectors.toList());
        Set<PacyorkyEvent> events = pacyorkyService.getEvents();
        Set<PacyorkyEvent> eventsForMail = new HashSet<>();
        logger.info("Events for update: " + events.size());
        for (PacyorkyEvent pacyorkyEvent : events) {
            if (!eventsIDs.contains(pacyorkyEvent.getId())) {
                locationPointService.updateLocationPoint(pacyorkyEvent);
                eventsForMail.add(pacyorkyEvent);
            } else {
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
                logger.error("Exception when save event: id: " + pacyorkyEvent.getId() + " exception: " + e.toString());
            }
        }
        if (!eventsForMail.isEmpty()) sendMails(eventsForMail);
    }
}
