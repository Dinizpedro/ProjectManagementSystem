package switchfive.project.infrastructure.persistence.repositories.iRepositoriesJPA;

import org.springframework.data.repository.CrudRepository;
import switchfive.project.dataModel.dataJPA.ProfileJPA;

import java.util.Optional;

public interface IProfileRepositoryJPA extends CrudRepository<ProfileJPA, Integer> {

    Optional<ProfileJPA> findProfileJPAByProfileDescription(String profileDescription);

    boolean existsProfileJPAByProfileDescription(String profileDescription);

}
