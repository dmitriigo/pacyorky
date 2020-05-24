package ee.blakcat.pacyorky.services.email;

import ee.blakcat.pacyorky.models.MailLang;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

public interface PacyorkyEventHTMLMailTemplate {
    MimeMessageHelper getTemplate(MimeMessage mimeMessage);

    MailLang getMailLang();

    String getTitle();

    String getFooter();
}
