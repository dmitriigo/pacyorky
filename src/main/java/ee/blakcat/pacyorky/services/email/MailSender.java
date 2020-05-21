package ee.blakcat.pacyorky.services.email;

import ee.blakcat.pacyorky.models.MailLang;
import ee.blakcat.pacyorky.models.PacyorkyEvent;
import ee.blakcat.pacyorky.models.PacyorkyUser;
import org.springframework.mail.SimpleMailMessage;

import java.util.Collection;

public interface MailSender <ENT> {
    void sendMail(Collection<PacyorkyEvent> data, PacyorkyUser pacyorkyUser);
}
