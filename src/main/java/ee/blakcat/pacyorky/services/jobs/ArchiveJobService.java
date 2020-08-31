package ee.blakcat.pacyorky.services.jobs;

import ee.blakcat.pacyorky.models.PacyorkyEvent;
import ee.blakcat.pacyorky.repositories.database.EventRepositoryJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArchiveJobService {

    private final EventRepositoryJPA eventRepositoryJPA;
    private final Logger logger = LoggerFactory.getLogger(ArchiveJobService.class);

    @Autowired
    public ArchiveJobService(EventRepositoryJPA eventRepositoryJPA) {
        this.eventRepositoryJPA = eventRepositoryJPA;
    }

    @Scheduled (cron = "0 0 3 * * *")
    public void archiveEvents () {
        List<PacyorkyEvent> events = eventRepositoryJPA.findAll();
        int i = 0;
        for (PacyorkyEvent event : events) {
            if (event.getStartTime().plusMonths(6L).isBefore(LocalDateTime.now())) {
                event.setPacyorkyEventOwner(null);
                eventRepositoryJPA.save(event);
                eventRepositoryJPA.delete(event);
                i++;
            }
        }
        if (i > 0) {
            logger.info("Events archived: "+i);
        }
    }
}
