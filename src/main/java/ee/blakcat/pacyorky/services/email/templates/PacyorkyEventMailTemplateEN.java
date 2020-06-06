package ee.blakcat.pacyorky.services.email.templates;

import ee.blakcat.pacyorky.models.MailLang;
import ee.blakcat.pacyorky.services.email.PacyorkyEventMailTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class PacyorkyEventMailTemplateEN implements PacyorkyEventMailTemplate {

    private final String title ="Hello! Pasyorky have next new events:\n";
    private final MailLang mailLang = MailLang.EN;
    private final String footer = "Best regards\n";

    @Override
    public SimpleMailMessage getTemplate() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("New events!");
        return simpleMailMessage;
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
