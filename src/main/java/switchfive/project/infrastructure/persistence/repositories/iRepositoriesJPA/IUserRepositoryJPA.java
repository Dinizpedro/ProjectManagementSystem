package switchfive.project.infrastructure.persistence.repositories.iRepositoriesJPA;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import switchfive.project.dataModel.dataJPA.UserJPA;

import java.util.List;
import java.util.Optional;

public interface IUserRepositoryJPA extends CrudRepository<UserJPA, String> {

    @Query("SELECT e FROM UserJPA e WHERE e.email like %:email%")
    List<UserJPA> findUsersByEmail(@Param("email") String email);

    Optional<UserJPA> findUserJPAByEmail(@Param("email") String email);

    @Query("SELECT e FROM UserJPA e join e.profileList p WHERE lower(p) = lower(:profile)")
    List<UserJPA> findUsersJPAByProfile(String profile);

    List<UserJPA> findAll();

    boolean existsByEmail(String email);
}
