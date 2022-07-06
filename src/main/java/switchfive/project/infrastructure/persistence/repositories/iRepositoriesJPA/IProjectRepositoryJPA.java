package switchfive.project.infrastructure.persistence.repositories.iRepositoriesJPA;

import org.springframework.data.repository.CrudRepository;
import switchfive.project.dataModel.dataJPA.ProjectJPA;

import java.util.List;
import java.util.Optional;

/**
 * Abstracts the data store and enables our business logic to define read and write operations on a logical level
 */
public interface IProjectRepositoryJPA extends CrudRepository<ProjectJPA, String> {

    boolean existsByCode(String code);

    Optional<ProjectJPA> findByCode(String code);

    List<ProjectJPA> findAll();

}
