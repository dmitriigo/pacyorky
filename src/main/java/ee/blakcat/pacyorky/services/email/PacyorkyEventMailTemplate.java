package ee.blakcat.pacyorky.services.email;

import ee.blakcat.pacyorky.models.MailLang;
import org.springframework.mail.SimpleMailMessage;

public interface PacyorkyEventMailTemplate {
    SimpleMailMessage getTemplate();

    MailLang getMailLang();

    String getTitle();

    String getFooter();
}
