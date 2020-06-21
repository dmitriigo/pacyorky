package ee.blakcat.pacyorky.services.email.templates;

import ee.blakcat.pacyorky.models.MailLang;
import ee.blakcat.pacyorky.services.email.WelcomeLetterTemplate;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class WelcomeLetterTemplateEN implements WelcomeLetterTemplate {
    private final String title =
            "Hello!\n" +
                    "To confirm your account, use the link below:\n";
    private final MailLang mailLang = MailLang.EN;
    private final String footer = "Best regards";
    private final String link = "link";
    private final Logger logger = LoggerFactory.getLogger(WelcomeLetterTemplateEN.class);
    @Override
    public MimeMessageHelper getTemplate(MimeMessage mimeMessage) {
        MimeMessageHelper message = null;
        try {
            message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setSubject("Confirmation of registration");
        } catch (MessagingException e) {
            logger.error("can not create mimeMessageHelper: " + e.toString());
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

    @Override
    public String getLink() { return link; }
}
