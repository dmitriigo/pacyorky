package ee.blakcat.pacyorky.services.email.implementations;

import ee.blakcat.pacyorky.models.PacyorkyEvent;
import ee.blakcat.pacyorky.models.PacyorkyUser;
import ee.blakcat.pacyorky.services.email.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;


public class MailSenderToUsersWithHTML implements MailSender<PacyorkyEvent> {
    private final String from = "no-reply@pacyorky.ee";
    private JavaMailSender javaMailSender;

    public MailSenderToUsersWithHTML(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendMail(Collection<PacyorkyEvent> data, PacyorkyUser pacyorkyUser) {
        javaMailSender.send(mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setFrom(from);
            message.setTo("lulkast@yandex.ru");
            // message.setTo(pacyorkyUser.geteMail());
            message.setSubject("New events!");
            String html = makeHtml(data);
            message.setText(html, true);
        });
    }

    private String makeHtml(Collection<PacyorkyEvent> data) {
        String head = makeHead();
        String body = "";
        for (PacyorkyEvent datum : data) {
            body += makeBody(datum);
        }
        String footer = makeFooter();
        return head + body + footer;
    }

    private String makeHead() {
        return "<html>\n" +
                " <head>\n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                "  <p>Hello! Pasyorky have next new events:</p>\n" +
                " </head>\n";
    }

    private String makeBody(PacyorkyEvent event) {
        URL URL = null;
        try {
            URL = new URL(event.getCover());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String eventName = event.getName();
        String place = event.getPlace();
        return
                " <body>\n" +
                        "  <h1>" + eventName + "</h1>\n" +
                        "  <p>" + place + "</p>\n" +
                        "<img src=\"" + URL + "\">";

    }

    private String makeFooter() {
        return "  <p>Best regards!</p>\n" +
                " </body>\n" +
                "</html>";
    }
}
