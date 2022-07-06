package switchfive.project.infrastructure.persistence.repositories.iRepositoriesJPA;

import org.springframework.data.repository.CrudRepository;
import switchfive.project.dataModel.dataJPA.TaskIDJPA;
import switchfive.project.dataModel.dataJPA.TaskJPA;

import java.util.List;

public interface ITaskRepositoryJPA extends CrudRepository<TaskJPA, TaskIDJPA> {

    List<TaskJPA> findTaskJPAByProjectCode(String taskCode);

    List<TaskJPA> findTaskJPAByProjectCodeOrderByTaskStatus(String projectCode);

}
