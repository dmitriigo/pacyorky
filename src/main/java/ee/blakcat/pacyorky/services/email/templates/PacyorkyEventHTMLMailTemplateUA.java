package ee.blakcat.pacyorky.services.email.templates;

import ee.blakcat.pacyorky.models.MailLang;
import ee.blakcat.pacyorky.services.email.PacyorkyEventHTMLMailTemplate;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Component
public class PacyorkyEventHTMLMailTemplateUA implements PacyorkyEventHTMLMailTemplate {

    private final String title = "Вітання! Пацьорки знайшли кілька нових подій:\n";
    private final MailLang mailLang = MailLang.UA;
    private final String footer = "З повагою,\n";


    @Override
    public MimeMessageHelper getTemplate(MimeMessage mimeMessage) {
        MimeMessageHelper message = null;
        try {
            message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setSubject("Нові події!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return message;
    }

    @Override
    public MailLang getMailLang() {
        return mailLang;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getFooter() {
        return footer;
    }
}
