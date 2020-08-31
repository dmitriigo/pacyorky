package ee.blakcat.pacyorky.repositories.database;

import ee.blakcat.pacyorky.models.PacyorkyPage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacyorkyPageRepository extends JpaRepository<PacyorkyPage, String> {
}
