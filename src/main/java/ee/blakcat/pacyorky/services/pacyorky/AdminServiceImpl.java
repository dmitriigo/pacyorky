package ee.blakcat.pacyorky.services.pacyorky;

import ee.blakcat.pacyorky.models.PacyorkyAdmin;
import ee.blakcat.pacyorky.repositories.database.PacyorkyAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class AdminServiceImpl  implements AdminService{
    private final PacyorkyAdminRepository pacyorkyAdminRepository;
private final PasswordEncoder passwordEncoder;

@Autowired
    public AdminServiceImpl(PacyorkyAdminRepository pacyorkyAdminRepository, PasswordEncoder passwordEncoder) {
        this.pacyorkyAdminRepository = pacyorkyAdminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean login(String login, String password, HttpSession httpSession) {
    PacyorkyAdmin pacyorkyAdmin = pacyorkyAdminRepository.findByLogin(login);
    if (pacyorkyAdmin==null) return false;
    if  (passwordEncoder.matches(password, pacyorkyAdmin.getPassword())) {
      pacyorkyAdmin.setSession(httpSession.getId());
      pacyorkyAdminRepository.save(pacyorkyAdmin);
      return true;
    } return false;
    }

    @Override
    public PacyorkyAdmin addAdmin(String login, String password) {
    PacyorkyAdmin pacyorkyAdmin = new PacyorkyAdmin();
    pacyorkyAdmin.setLogin(login);
    pacyorkyAdmin.setPassword(passwordEncoder.encode(password));
    return pacyorkyAdminRepository.save(pacyorkyAdmin);
    }

    @Override
    public List<PacyorkyAdmin> findAll() {
    return pacyorkyAdminRepository.findAll();
    }

    @Override
    public PacyorkyAdmin getOne (Long id) {
    return pacyorkyAdminRepository.getOne(id);
    }
}
