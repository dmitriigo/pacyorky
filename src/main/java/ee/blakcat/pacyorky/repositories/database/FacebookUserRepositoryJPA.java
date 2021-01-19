package ee.blakcat.pacyorky.repositories.database;

import ee.blakcat.pacyorky.models.FacebookUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FacebookUserRepositoryJPA extends JpaRepository<FacebookUser, String> {
    List<FacebookUser> findByAccessTrue();
    List<FacebookUser> findByPageTrue();
    @Query(value = "select count(*) from facebook_user left join access_token t on t.id=access_token_id where t.exp_date < :exp",
    nativeQuery = true)
    Long getWrongTokenUsersCount(@Param("exp")LocalDateTime exp);
}
