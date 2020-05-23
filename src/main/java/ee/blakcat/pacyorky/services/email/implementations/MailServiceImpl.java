package ee.blakcat.pacyorky.services.email.implementations;

import ee.blakcat.pacyorky.models.MailSendPeriod;
import ee.blakcat.pacyorky.models.PacyorkyEvent;
import ee.blakcat.pacyorky.models.PacyorkyUser;
import ee.blakcat.pacyorky.repositories.database.PacyorkyUserRepository;
import ee.blakcat.pacyorky.services.email.MailSender;
import ee.blakcat.pacyorky.services.email.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MailServiceImpl implements MailService<PacyorkyEvent> {

    private final PacyorkyUserRepository pacyorkyUserRepository;
    private final MailSender<PacyorkyEvent> mailSender;

    @Autowired
    public MailServiceImpl(PacyorkyUserRepository pacyorkyUserRepository, MailSender<PacyorkyEvent> mailSender) {
        this.pacyorkyUserRepository = pacyorkyUserRepository;
        this.mailSender = mailSender;
    }


    @Override
    public void updateTask(Set<PacyorkyEvent> events) {
        List<PacyorkyUser> users = pacyorkyUserRepository.findAllByConfirmedTrue();
        for (PacyorkyUser user : users) {
            if (user.getMailSendPeriod()== MailSendPeriod.HOURLY) sendToOneUser(events, user);
            else if (user.getMailSendPeriod()==MailSendPeriod.WEEKLY) {
              if (user.getPacyorkyEventsToSend()==null) user.setPacyorkyEventsToSend(new HashSet<>());
              user.getPacyorkyEventsToSend().addAll(events);
              pacyorkyUserRepository.save(user);
            }
        }
    }

    private void sendToOneUser(Set<PacyorkyEvent> events, PacyorkyUser user) {
        mailSender.sendMail(events, user);
    }
}
