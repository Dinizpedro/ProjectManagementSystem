package switchfive.project.applicationServices.iRepositories;

import switchfive.project.dataModel.dataJPA.TaskIDJPA;
import switchfive.project.domain.aggregates.task.Task;
import switchfive.project.domain.shared.valueObjects.ProjectCode;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface ITaskRepository {

    String nextTaskCode (ProjectCode projectCode);

    Optional<Task> findTaskById (TaskIDJPA taskId) throws ParseException;

    Task save(Task task) throws ParseException;

    List<Task> findAllTasks() throws ParseException;

    List<Task> findTaskAndOrderByStatus(ProjectCode projectCode) throws ParseException;
}
