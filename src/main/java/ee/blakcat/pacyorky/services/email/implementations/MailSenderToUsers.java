package ee.blakcat.pacyorky.services.email.implementations;

import ee.blakcat.pacyorky.models.MailLang;
import ee.blakcat.pacyorky.models.PacyorkyEvent;
import ee.blakcat.pacyorky.models.PacyorkyUser;
import ee.blakcat.pacyorky.services.email.MailSender;
import ee.blakcat.pacyorky.services.email.PacyorkyEventMailTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

//@Service
public class MailSenderToUsers implements MailSender <PacyorkyEvent> {
    private final String from = "no-reply@pacyorky.ee";
    private JavaMailSender javaMailSender;
    private List<PacyorkyEventMailTemplate> templates;

    @Autowired
    public MailSenderToUsers(JavaMailSender javaMailSender, List<PacyorkyEventMailTemplate> templates) {
        this.javaMailSender = javaMailSender;
        this.templates = templates;
    }

    @Override
    public void sendMail(Collection<PacyorkyEvent> data, PacyorkyUser pacyorkyUser) {
        PacyorkyEventMailTemplate template = templates.stream().filter(pacyorkyEventMailTemplate -> pacyorkyEventMailTemplate.getMailLang()==pacyorkyUser.getMailLang()).findAny().orElseThrow(RuntimeException::new);
        SimpleMailMessage simpleMailMessage = template.getTemplate();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(pacyorkyUser.geteMail());
        String text = template.getTitle();
        for (PacyorkyEvent event : data) {
            text += event.getName()+" "+ event.getPlace()+"\n";
        }
        text+=template.getFooter();
        simpleMailMessage.setText(text);
        javaMailSender.send(simpleMailMessage);
    }

}
