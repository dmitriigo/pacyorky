package ee.blakcat.pacyorky.repositories.database;

import ee.blakcat.pacyorky.models.PacyorkyAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacyorkyAdminRepository extends JpaRepository <PacyorkyAdmin, Long> {
    PacyorkyAdmin findBySession (String session);
    PacyorkyAdmin findByLogin (String login);
}
