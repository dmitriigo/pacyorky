package ee.blakcat.pacyorky.services.email.templates;

import ee.blakcat.pacyorky.models.MailLang;
import ee.blakcat.pacyorky.services.email.WelcomeLetterTemplate;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class WelcomeLetterTemplateEE implements WelcomeLetterTemplate {
    private final String title = "Tere!\n" +
            "Oma konto kinnitamiseks kasutage allolevat linki:\n";
    private final MailLang mailLang = MailLang.UA;
    private final String footer = "Parimate soovidega";
    private final String link = "linki";

    @Override
    public MimeMessageHelper getTemplate(MimeMessage mimeMessage) {
        MimeMessageHelper message = null;
        try {
            message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setSubject("Registreerimise kinnitus");
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

    @Override
    public String getLink() { return link; }
}
