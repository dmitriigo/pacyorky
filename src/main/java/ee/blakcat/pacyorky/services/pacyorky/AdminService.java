package ee.blakcat.pacyorky.services.pacyorky;

import ee.blakcat.pacyorky.models.PacyorkyAdmin;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface AdminService {
    boolean login(String login, String password, HttpSession httpSession);

    PacyorkyAdmin addAdmin(String login, String password);

    List<PacyorkyAdmin> findAll();

    PacyorkyAdmin getOne(Long id);
}
