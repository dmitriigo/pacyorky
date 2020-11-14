package ee.blakcat.pacyorky.services.pacyorky;

import ee.blakcat.pacyorky.dto.AddMailMessageDTO;
import ee.blakcat.pacyorky.models.MailLang;
import ee.blakcat.pacyorky.models.MailSendPeriod;

public interface UserService {
    void updateUser(String mail, String id, String control,
                    String confirmed, String lang, String period);

    void updateUser(String mail, String control,
                    String confirmed, String lang, String period);

    AddMailMessageDTO addUser (String eMail, MailLang mailLang, MailSendPeriod mailSendPeriod);

    boolean confirmUser(Long id, String confirmString);

    boolean deleteUser(String controlString);
}
