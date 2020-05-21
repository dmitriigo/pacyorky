package ee.blakcat.pacyorky.services.pacyorky;

import ee.blakcat.pacyorky.models.MailLang;
import ee.blakcat.pacyorky.models.MailSendPeriod;

public interface UserService {
    boolean addUser (String eMail, MailLang mailLang, MailSendPeriod mailSendPeriod);

    boolean confirmUser(String controlString);

    boolean deleteUser(String controlString);
}
