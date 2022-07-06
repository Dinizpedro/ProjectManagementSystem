package switchfive.project.domain.factories.implFactories;

import org.springframework.stereotype.Component;
import switchfive.project.dataModel.dataJPA.TaskIDJPA;
import switchfive.project.domain.aggregates.task.Task;
import switchfive.project.domain.factories.iFactories.ITaskFactory;
import switchfive.project.domain.shared.valueObjects.*;

import java.util.List;

@Component
public class ImplTaskFactory implements ITaskFactory {

    /**
     * @param taskID          task identifier;
     * @param taskName        task name;
     * @param taskDescription description for task;
     * @param taskTime        time estimated for doing task;
     * @param effortEstimate  effort estimated for doing task;
     * @param precedenceList  list of tasks which must precede the just created task;
     * @param typeOfTask      type of task;
     * @param responsible     resource responsible for working in the task.
     * @return new Instance of Task class
     */
    @Override
    public Task createTask(final TaskID taskID,
                           final TaskName taskName,
                           final TaskDescription taskDescription,
                           final Time taskTime,
                           final EffortEstimate effortEstimate,
                           final List<TaskIDJPA> precedenceList,
                           final TypeOfTask typeOfTask,
                           final ResourceID responsible) {

        return new Task(taskID, taskName,
                taskDescription, taskTime,
                effortEstimate, precedenceList,
                typeOfTask, responsible);
    }


    @Override
    public Task createTaskFromDB(TaskID idTask, TaskName name, TaskDescription description,
                                 Time taskTime, Hour hoursSpent, EffortEstimate effortEstimate,
                                 TaskPercentageOfExecution taskPercentageOfExecution, List<TaskIDJPA> precedenceList,
                                 TypeOfTask typeOfTask, ResourceID responsible, List<Log> taskLogs,
                                 TaskStatus taskStatus) {
        return new Task(idTask, name, description, taskTime, hoursSpent,
                effortEstimate, taskPercentageOfExecution, precedenceList, typeOfTask,
                responsible, taskLogs, taskStatus);
    }
}
