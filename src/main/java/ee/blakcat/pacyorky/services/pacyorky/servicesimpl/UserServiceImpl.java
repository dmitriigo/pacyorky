package ee.blakcat.pacyorky.services.pacyorky.servicesimpl;

import ee.blakcat.pacyorky.models.MailLang;
import ee.blakcat.pacyorky.models.MailSendPeriod;
import ee.blakcat.pacyorky.models.PacyorkyUser;
import ee.blakcat.pacyorky.repositories.database.PacyorkyUserRepository;
import ee.blakcat.pacyorky.services.email.MailSenderWelcomeLetter;
import ee.blakcat.pacyorky.services.pacyorky.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private PacyorkyUserRepository pacyorkyUserRepository;
    private final MailSenderWelcomeLetter mailSenderWelcomeLetter;
    

    @Autowired
    public UserServiceImpl(PacyorkyUserRepository pacyorkyUserRepository, MailSenderWelcomeLetter mailSenderWelcomeLetter) {
        this.pacyorkyUserRepository = pacyorkyUserRepository;
        this.mailSenderWelcomeLetter = mailSenderWelcomeLetter;
    }

    @Override
    public void updateUser(String mail, String id, String control,
                           String confirmed, String lang, String period) {
        Long i = Long.parseLong(id);
        PacyorkyUser  pacyorkyUser = pacyorkyUserRepository.getOne(i);
        pacyorkyUser.setConfirmed(Boolean.parseBoolean(confirmed));
        pacyorkyUser.setControlString(control);
        pacyorkyUser.setMailLang(MailLang.values()[Integer.parseInt(lang)]);
        pacyorkyUser.setMailSendPeriod(MailSendPeriod.values()[Integer.parseInt(period)]);
        pacyorkyUser.seteMail(mail);
        pacyorkyUserRepository.save(pacyorkyUser);

    }
    @Override
    public void updateUser(String mail, String control,
                           String confirmed, String lang, String period) {
        PacyorkyUser  pacyorkyUser = new PacyorkyUser();
        pacyorkyUser.setPacyorkyEventsToSend(new HashSet<>());
        pacyorkyUser.setConfirmed(Boolean.parseBoolean(confirmed));
        pacyorkyUser.setControlString(control);
        pacyorkyUser.setMailSendPeriod(MailSendPeriod.values()[Integer.parseInt(period)]);
        pacyorkyUser.setMailLang(MailLang.values()[Integer.parseInt(lang)]);
        pacyorkyUser.seteMail(mail);
        pacyorkyUserRepository.save(pacyorkyUser);
    }

    @Override
    public boolean addUser(String eMail, MailLang mailLang, MailSendPeriod mailSendPeriod) {
        PacyorkyUser pacyorkyUser = new PacyorkyUser();
        pacyorkyUser.seteMail(eMail);
        pacyorkyUser.setMailLang(mailLang);
        pacyorkyUser.setMailSendPeriod(mailSendPeriod);
        pacyorkyUser.setPacyorkyEventsToSend(new HashSet<>());
        pacyorkyUser.setConfirmed(false);
        pacyorkyUser.setControlString(UUID.randomUUID().toString());
        try {
            pacyorkyUserRepository.save(pacyorkyUser);
            mailSenderWelcomeLetter.sendMail(pacyorkyUser);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean confirmUser(Long id, String confirmString) {
        PacyorkyUser pacyorkyUser = pacyorkyUserRepository.findById(id).orElseThrow(RuntimeException::new);
        if (pacyorkyUser==null) return false;
        if (!pacyorkyUser.getControlString().equals(confirmString)) return false;
        pacyorkyUser.setConfirmed(true);
        pacyorkyUserRepository.save(pacyorkyUser);
        return true;
    }

    @Override
    public boolean deleteUser (String controlString) {
        PacyorkyUser pacyorkyUser = pacyorkyUserRepository.findByControlString(controlString);
        if (pacyorkyUser==null) return false;
        pacyorkyUserRepository.delete(pacyorkyUser);
        return true;
    }

}
