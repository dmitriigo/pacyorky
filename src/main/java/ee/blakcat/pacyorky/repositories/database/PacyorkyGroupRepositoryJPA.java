package ee.blakcat.pacyorky.repositories.database;

import ee.blakcat.pacyorky.models.PacyorkyGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PacyorkyGroupRepositoryJPA extends JpaRepository<PacyorkyGroup, String> {
    Set<PacyorkyGroup> findAllByAllowedTrueAndPageIsFalse();
}
