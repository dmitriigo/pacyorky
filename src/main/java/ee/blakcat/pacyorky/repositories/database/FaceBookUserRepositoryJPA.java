package ee.blakcat.pacyorky.repositories.database;

import ee.blakcat.pacyorky.models.FaceBookUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaceBookUserRepositoryJPA extends JpaRepository<FaceBookUser, String> {
    List<FaceBookUser> findByAccessTrue();
}
