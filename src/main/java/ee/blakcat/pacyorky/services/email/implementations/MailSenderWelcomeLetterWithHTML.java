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
        return "<!DOCTYPE html>\n" +
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
                "<table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\" style=\"margin: 0; background: #0382Ce; border-radius: 2px;\">\n" +
                "  <tr>\n" +
                "  \t<td width=\"19\" style=\"width: 19px;\">&nbsp;</td>\n" +
                "        <td style=\"height: 38px;\">\n" +
                "            <a href=\""+URL+"\" style=\"height: 38px; text-align: center; font-family: Arial, Tahoma, Geneva, sans-serif; font-size: 12px; line-height: 38px; text-decoration: none; padding: 0; display: block; border-radius: 4px;\">\n" +
                "                 <font face=\"Arial, sans-serif\" color=\"#ffffff\" style=\"font-size: 12px; line-height: 38px;\">\n" +
                "                           <span style=\"font-family: Arial, Tahoma, Geneva, sans-serif; color: #ffffff; font-size: 12px; line-height: 38px; font-weight: bold; letter-spacing: 0.05em; -webkit-text-size-adjust:none;\">"+text+"</span>\n" +
                "                </font>\n" +
                "             </a>\n" +
                "  </td>\n" +
                "   <td width=\"19\" style=\"width: 19px;\">&nbsp;</td>\n" +
                "  </tr>\n" +
                "</table>";


    }

    private String makeFooter(String footer) {
        return
                "<p>" + footer + "</p>\n" +
                        "</body>\n" +
                        "</html>";
    }
}
