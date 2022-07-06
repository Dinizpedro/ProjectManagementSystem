package switchfive.project.interfaceAdapters.implRepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchfive.project.assemblers.assemblersJPA.iAssemblersJPA.ITaskAssemblerJPA;
import switchfive.project.dataModel.dataJPA.TaskIDJPA;
import switchfive.project.dataModel.dataJPA.TaskJPA;
import switchfive.project.infrastructure.persistence.repositories.iRepositoriesJPA.ITaskRepositoryJPA;
import switchfive.project.applicationServices.iRepositories.ITaskRepository;
import switchfive.project.domain.aggregates.task.Task;
import switchfive.project.domain.shared.valueObjects.ProjectCode;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImplTaskRepository implements ITaskRepository {

    private ITaskRepositoryJPA iTaskRepositoryJPA;

    private ITaskAssemblerJPA iTaskAssemblerJPA;

    @Autowired
    public ImplTaskRepository(ITaskRepositoryJPA iTaskRepositoryJPA, ITaskAssemblerJPA iTaskAssemblerJPA) {
        this.iTaskRepositoryJPA = iTaskRepositoryJPA;
        this.iTaskAssemblerJPA = iTaskAssemblerJPA;
    }

    /**
     * Find the next Task Code in a group of tasks.
     * @param projectCode - selected project by project code.
     * @return a String of the task code sequentially, starting with a T
     */
    public String nextTaskCode(ProjectCode projectCode) {
        List<TaskJPA> tasksJPA = iTaskRepositoryJPA.
                findTaskJPAByProjectCode(projectCode.getCode());
        int nextTaskNumber = tasksJPA.size() + 1;
        return "T" + nextTaskNumber;
    }

    /**
     * Find a Task by an TaskID JPA
     * @param taskId - task identity
     * @return an Optional<Task>
     * @throws ParseException
     */
    public Optional<Task> findTaskById (TaskIDJPA taskId) throws ParseException {
        Optional<TaskJPA> taskJPAOpt = iTaskRepositoryJPA.findById(taskId);

        Task task = null;
        if (taskJPAOpt.isPresent()) {
            TaskJPA taskJPA = taskJPAOpt.get();
            task = iTaskAssemblerJPA.toDomain(taskJPA);
        }

        return  Optional.ofNullable(task);
    }

    /**
     * Save a Task in Repository
     * @param task - to be saved
     * @return a domain object Task
     * @throws ParseException
     */
    @Override
    public Task save(Task task) throws ParseException {
        TaskJPA taskJPA = iTaskAssemblerJPA.toData(task);
        TaskJPA taskJPAInRepo = iTaskRepositoryJPA.save(taskJPA);
        return iTaskAssemblerJPA.toDomain(taskJPAInRepo);
    }

    /**
     * Find all tasks in repository
     * @return a List of domain objects Tasks
     * @throws ParseException
     */
    public List<Task> findAllTasks() throws ParseException {
        List<Task> tasks = new ArrayList<>();
        Iterable<TaskJPA> tasksJPA = iTaskRepositoryJPA.findAll();

        for (TaskJPA taskJPA : tasksJPA){
            Task task = iTaskAssemblerJPA.toDomain(taskJPA);
            tasks.add(task);
        }

        return tasks;
    }

    /**
     * Find all tasks in a project and order them by status
     * @param projectCode - selected project
     * @return a list of domain object tasks in a project
     * @throws ParseException
     */

    public List<Task> findTaskAndOrderByStatus(ProjectCode projectCode
    ) throws ParseException {

        List<Task> tasks = new ArrayList<>();
        Iterable<TaskJPA> tasksJPA = iTaskRepositoryJPA
                .findTaskJPAByProjectCodeOrderByTaskStatus(projectCode.getCode());

        for (TaskJPA taskJPA : tasksJPA){
            Task task = iTaskAssemblerJPA.toDomain(taskJPA);
            tasks.add(task);
        }

        return tasks;
    }
}
