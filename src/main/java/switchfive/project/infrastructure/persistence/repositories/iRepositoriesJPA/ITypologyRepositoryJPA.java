package switchfive.project.infrastructure.persistence.repositories.iRepositoriesJPA;

import org.springframework.data.repository.CrudRepository;
import switchfive.project.dataModel.dataJPA.TypologyJPA;

import java.util.Optional;

public interface ITypologyRepositoryJPA extends CrudRepository<TypologyJPA, Integer> {

    Optional<TypologyJPA> findByDescription(String description);

    boolean existsByDescription(String description);

}
