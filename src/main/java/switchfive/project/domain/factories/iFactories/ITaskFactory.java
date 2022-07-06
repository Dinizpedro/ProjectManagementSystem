package switchfive.project.domain.factories.iFactories;

import switchfive.project.dataModel.dataJPA.TaskIDJPA;
import switchfive.project.domain.aggregates.task.Task;
import switchfive.project.domain.shared.valueObjects.*;

import java.util.List;

public interface ITaskFactory {

    Task createTask(TaskID taskID, TaskName taskName,
                    TaskDescription taskDescription, Time taskTime, EffortEstimate effortEstimate,
                    List<TaskIDJPA> precedenceList, TypeOfTask typeOfTask, ResourceID responsible);

    Task createTaskFromDB(TaskID idTask, TaskName name,
                    TaskDescription description,
                    Time taskTime, Hour hoursSpent,
                    EffortEstimate effortEstimate,
                    TaskPercentageOfExecution taskPercentageOfExecution,
                    List<TaskIDJPA> precedenceList, TypeOfTask typeOfTask,
                    ResourceID responsible, List<Log> taskLogs,
                    TaskStatus taskStatus);

}
