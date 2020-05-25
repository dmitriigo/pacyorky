package ee.blakcat.pacyorky.services.email;

import ee.blakcat.pacyorky.models.PacyorkyUser;

public interface MailSenderWelcomeLetter {
    void sendMail(PacyorkyUser pacyorkyUser);
}
