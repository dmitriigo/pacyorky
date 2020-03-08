package ee.blakcat.pacyorky.repositories.database;

import ee.blakcat.pacyorky.models.PacyorkyEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepositoryJPA extends JpaRepository<PacyorkyEvent, String> {

}
