package ee.blakcat.pacyorky.services.email.implementations;

import ee.blakcat.pacyorky.models.PacyorkyUser;
import ee.blakcat.pacyorky.services.email.MailSenderWelcomeLetter;
import ee.blakcat.pacyorky.services.email.WelcomeLetterTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Service
public class MailSenderWelcomeLetterWithHTML implements MailSenderWelcomeLetter {
    private final String from = "no-reply@pacyorky.ee";
    private JavaMailSender javaMailSender;
    private List<WelcomeLetterTemplate> templates;

    @Autowired
    public MailSenderWelcomeLetterWithHTML(JavaMailSender javaMailSender, List<WelcomeLetterTemplate> templates) {
        this.javaMailSender = javaMailSender;
        this.templates = templates;
    }

    @Override
    public void sendMail(PacyorkyUser pacyorkyUser) {
        WelcomeLetterTemplate template = templates.stream().filter(welkomeLetterTemplate -> welkomeLetterTemplate.getMailLang() == pacyorkyUser.getMailLang()).findAny().orElseThrow(RuntimeException::new);
        javaMailSender.send(mimeMessage -> {
            MimeMessageHelper message = template.getTemplate(mimeMessage);
            message.setFrom(from);
            message.setTo(pacyorkyUser.geteMail());
            String html = makeHtml(pacyorkyUser, template);
            message.setText(html, true);
        });
    }

    private String makeHtml(PacyorkyUser pacyorkyUser, WelcomeLetterTemplate template) {
        String head = makeHead(template.getTitle());
        String body = makeBody(pacyorkyUser.getControlString(), pacyorkyUser.getId(), template.getLink());
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

    private String makeBody(String controlString, Long id, String text) {
        URL URL = null;
        String link = "https://pacyorky.ee/confirm?user=" + id + "&token=" + controlString;
        try {
            URL = new URL(link);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return "<body>\n" +
                "<a href=\"" + URL + "\">" + text + "</a>";
    }

    private String makeFooter(String footer) {
        return
                "<p>" + footer + "</p>\n" +
                        "</body>\n" +
                        "</html>";
    }
}
