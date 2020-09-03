package ee.blakcat.pacyorky.repositories.database;

import ee.blakcat.pacyorky.models.FacebookUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacebookUserRepositoryJPA extends JpaRepository<FacebookUser, String> {
    List<FacebookUser> findByAccessTrue();
    List<FacebookUser> findByPageTrue();
}
