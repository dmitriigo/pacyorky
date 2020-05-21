package ee.blakcat.pacyorky.services.pacyorky;

import ee.blakcat.pacyorky.models.MailLang;
import ee.blakcat.pacyorky.models.MailSendPeriod;
import ee.blakcat.pacyorky.models.PacyorkyUser;
import ee.blakcat.pacyorky.repositories.database.PacyorkyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private PacyorkyUserRepository pacyorkyUserRepository;

    @Autowired
    public UserServiceImpl(PacyorkyUserRepository pacyorkyUserRepository) {
        this.pacyorkyUserRepository = pacyorkyUserRepository;
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
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean confirmUser(String controlString) {
        PacyorkyUser pacyorkyUser = pacyorkyUserRepository.findByControlString(controlString);
        if (pacyorkyUser==null) return false;
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
