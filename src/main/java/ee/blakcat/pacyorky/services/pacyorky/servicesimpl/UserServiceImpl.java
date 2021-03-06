package ee.blakcat.pacyorky.services.pacyorky.servicesimpl;

import ee.blakcat.pacyorky.dto.AddMailMessageDTO;
import ee.blakcat.pacyorky.models.MailLang;
import ee.blakcat.pacyorky.models.MailSendPeriod;
import ee.blakcat.pacyorky.models.PacyorkyUser;
import ee.blakcat.pacyorky.repositories.database.PacyorkyUserRepository;
import ee.blakcat.pacyorky.services.email.MailSenderWelcomeLetter;
import ee.blakcat.pacyorky.services.pacyorky.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashSet;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private PacyorkyUserRepository pacyorkyUserRepository;
    private final MailSenderWelcomeLetter mailSenderWelcomeLetter;
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

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
    public AddMailMessageDTO addUser(String eMail, MailLang mailLang, MailSendPeriod mailSendPeriod) {
        AddMailMessageDTO addMailMessageDTO = new AddMailMessageDTO();
        PacyorkyUser existMail = pacyorkyUserRepository.findByeMail(eMail);
        if (existMail == null) {
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
                addMailMessageDTO.setResult(true);
                addMailMessageDTO.setMail(eMail);
                return addMailMessageDTO;
            } catch (Exception e) {
                logger.error("add user exception: " + e.toString(), e);
                addMailMessageDTO.setResult(false);
                return addMailMessageDTO;
            }
        } else {
            addMailMessageDTO.setResult(false);
            addMailMessageDTO.setMessage("exist");
            return addMailMessageDTO;
        }
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
