package ee.blakcat.pacyorky.repositories.database;

import ee.blakcat.pacyorky.models.FacebookPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacebookPageRepositoryJPA extends JpaRepository<FacebookPage, String> {
    List<FacebookPage> findAllByAllowedTrue();
}
