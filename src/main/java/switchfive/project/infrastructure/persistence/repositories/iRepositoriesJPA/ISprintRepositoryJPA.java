package switchfive.project.infrastructure.persistence.repositories.iRepositoriesJPA;

import org.springframework.data.repository.CrudRepository;
import switchfive.project.dataModel.dataJPA.SprintJPA;
import switchfive.project.domain.shared.valueObjects.SprintID;

import java.util.Optional;

public interface ISprintRepositoryJPA extends
        CrudRepository<SprintJPA, SprintID> {

    boolean existsById(SprintID sprintID);

    Optional<SprintJPA> findById(SprintID sprintID);

    <S extends SprintJPA> S save(S entity);

    Iterable<SprintJPA> findAllBySprintID_projectCode(String projectCode);

    long countSprintJPABySprintID_projectCode(String projectCode);
}
