package ee.blakcat.pacyorky.services.email.implementations;

import com.google.common.base.Strings;
import ee.blakcat.pacyorky.models.PacyorkyEvent;
import ee.blakcat.pacyorky.models.PacyorkyUser;
import ee.blakcat.pacyorky.services.email.MailSender;
import ee.blakcat.pacyorky.services.email.PacyorkyEventHTMLMailTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.List;

@Service
public class MailSenderToUsersWithHTML implements MailSender<PacyorkyEvent> {
    private final String from = "no-reply@pacyorky.ee";
    private JavaMailSender javaMailSender;
    private List<PacyorkyEventHTMLMailTemplate> templates;
    private final Logger logger = LoggerFactory.getLogger(MailSenderToUsersWithHTML.class);

    @Autowired
    public MailSenderToUsersWithHTML(JavaMailSender javaMailSender, List<PacyorkyEventHTMLMailTemplate> templates) {
        this.javaMailSender = javaMailSender;
        this.templates = templates;
    }

    @Override
    public void sendMail(Collection<PacyorkyEvent> data, PacyorkyUser pacyorkyUser) {
        PacyorkyEventHTMLMailTemplate template = templates.stream().filter(pacyorkyEventHTMLMailTemplate -> pacyorkyEventHTMLMailTemplate.getMailLang() == pacyorkyUser.getMailLang()).findAny().orElseThrow(RuntimeException::new);
        javaMailSender.send(mimeMessage -> {
            MimeMessageHelper message = template.getTemplate(mimeMessage);
            message.setFrom(from);
            message.setTo(pacyorkyUser.geteMail());
            String html = MailSenderToUsersWithHTML.this.makeHtml(data, template);
            message.setText(html, true);
        });
    }

    private String makeHtml(Collection<PacyorkyEvent> data, PacyorkyEventHTMLMailTemplate template) {
        String head = makeHead(template.getTitle());
        String body = "";
        for (PacyorkyEvent datum : data) {
            body += makeBody(datum);
        }
        String footer = makeFooter(template.getFooter());
        return head + body + footer;
    }

    private String makeHead(String title) {
        return "<html>\n" +
                " <head>\n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                "  <p>" + title + "</p>\n" +
                " </head>\n";
    }

    private String makeBody(PacyorkyEvent event) {
        URL URL = null;
        try {
            if (!Strings.isNullOrEmpty(event.getCover())) URL = new URL(event.getCover());
        } catch (MalformedURLException e) {
            logger.warn(e.toString());
        }
        String eventName = event.getName();
        String place = event.getPlace();
        return
                " <body>\n" +
                        "  <h1>" + eventName + "</h1>\n" +
                        "  <p>" + place + "</p>\n" +
                        (URL!=null ? "<img src=\"" + URL + "\">" : "");

    }

    private String makeFooter(String footer) {
        return "  <p>" + footer + "</p>\n" +
                " </body>\n" +
                "</html>";
    }
}
