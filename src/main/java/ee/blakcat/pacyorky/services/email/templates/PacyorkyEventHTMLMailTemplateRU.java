package ee.blakcat.pacyorky.services.email.templates;

import ee.blakcat.pacyorky.models.MailLang;
import ee.blakcat.pacyorky.services.email.PacyorkyEventHTMLMailTemplate;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Component
public class PacyorkyEventHTMLMailTemplateRU implements PacyorkyEventHTMLMailTemplate {

    private final String title = "Привет! Пацёрки нашли несколько новых мероприятий:\n";
    private final MailLang mailLang = MailLang.RU;
    private final String footer = "С наилучшими пожеланиями,\n";


    @Override
    public MimeMessageHelper getTemplate(MimeMessage mimeMessage) {
        MimeMessageHelper message = null;
        try {
            message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setSubject("Новые события!");
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
