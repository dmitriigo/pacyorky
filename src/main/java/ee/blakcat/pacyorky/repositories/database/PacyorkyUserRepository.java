package ee.blakcat.pacyorky.repositories.database;

import ee.blakcat.pacyorky.models.PacyorkyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PacyorkyUserRepository extends JpaRepository<PacyorkyUser, Long> {
  //  List<PacyorkyUser> findAllConfirmed();
    List<PacyorkyUser> findAllByConfirmedIsTrue();
    PacyorkyUser findByControlString(String controlString);
    PacyorkyUser findByeMail(String eMail);
}
