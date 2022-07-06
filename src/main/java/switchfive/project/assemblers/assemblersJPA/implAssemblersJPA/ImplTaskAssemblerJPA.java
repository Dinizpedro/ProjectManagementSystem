package switchfive.project.assemblers.assemblersJPA.implAssemblersJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchfive.project.assemblers.assemblersJPA.iAssemblersJPA.ITaskAssemblerJPA;
import switchfive.project.dataModel.dataJPA.TaskIDJPA;
import switchfive.project.dataModel.dataJPA.TaskJPA;
import switchfive.project.domain.aggregates.task.Task;
import switchfive.project.domain.factories.iFactories.ITaskFactory;
import switchfive.project.domain.shared.valueObjects.*;

import java.text.ParseException;
import java.util.List;

@Service
public class ImplTaskAssemblerJPA implements ITaskAssemblerJPA {

    private ITaskFactory taskFactory;

    @Autowired
    public ImplTaskAssemblerJPA(ITaskFactory taskFactory) {
        this.taskFactory = taskFactory;
    }

    public Task toDomain(TaskJPA taskJPA) throws ParseException {
        TaskCode taskCodeJPA = TaskCode.createTaskCode(taskJPA.getTaskCode());
        String projectCodeJPA = taskJPA.getProjectCode();

        TaskID taskID = null;
        if (taskJPA.getSprintNumber() != null) {
            Integer sprintNumberJPA = taskJPA.getSprintNumber();
            SprintID sprintIDJPA = SprintID.createSprintID(projectCodeJPA,
                    sprintNumberJPA);
            taskID = TaskID.createIDTask(taskCodeJPA, sprintIDJPA);
        } else {
            String userStoryCodeJPA = taskJPA.getUserStoryCode();
            UserStoryID userStoryIDJPA = UserStoryID.createUserStoryID(projectCodeJPA,
                    userStoryCodeJPA);
            taskID = TaskID.createIDTask(taskCodeJPA, userStoryIDJPA);
        }

        TaskName taskName = TaskName.createTaskName(taskJPA.getName());
        TaskDescription taskDescription = TaskDescription.createTaskDescription(taskJPA.getDescription());

        String startDate = taskJPA.getStartDate();
        String endDate = taskJPA.getEndDate();
        Time time = Time.create(startDate, endDate);

        Hour hoursSpent = Hour.createHour(taskJPA.getHoursSpent());
        EffortEstimate effortEstimate = EffortEstimate.createEffortEstimate(taskJPA.getEffortEstimate());
        TaskPercentageOfExecution taskPercentageOfExecution = TaskPercentageOfExecution.
                createPercentage(taskJPA.getPercentageOfExecution());
        List<TaskIDJPA> precedenceList = taskJPA.getPrecedenceList();
        TypeOfTask typeOfTask = TypeOfTask.valueOf(taskJPA.getTypeOfTask());
        ResourceID resourceID = ResourceID.createResourceID(taskJPA.getResourceResponsible());

        List<Log> logList = taskJPA.getTaskLogs();
        TaskStatus taskStatus = TaskStatus.valueOf(taskJPA.getTaskStatus());

        return taskFactory.createTaskFromDB(taskID, taskName, taskDescription, time,
                hoursSpent, effortEstimate, taskPercentageOfExecution, precedenceList,
                typeOfTask, resourceID, logList, taskStatus);

    }

    public TaskJPA toData(Task task) {

        TaskID taskID = task.getIdTask();
        String taskName = task.getName();
        String taskDescription = task.getDescription();
        String startDate = task.getStartDate();
        String endDate = task.getEndDate();
        double hoursSpent = task.getHoursSpent();
        Integer effortEstimate = task.getEffortEstimate();
        double percentageOfExecution = task.getPercentageOfExecution();
        List<TaskIDJPA> precedenceList = task.getPrecedenceList();
        String typeOfTask = task.getTypeOfTask();
        String responsible = task.getResponsible();
        List<Log> taskLogs = task.getTaskLogs();
        String taskStatus = task.getTaskStatus();
        String projectCode = taskID.getTaskContainer().getProjectCode();
        Object taskContainerID = taskID.getTaskContainer().getTaskContainerID();

        TaskJPA taskJPA = new TaskJPA();

        taskJPA.setTaskCode(taskID.getTaskCode());
        taskJPA.setName(taskName);
        taskJPA.setDescription(taskDescription);
        taskJPA.setStartDate(startDate);
        taskJPA.setEndDate(endDate);
        taskJPA.setHoursSpent(hoursSpent);
        taskJPA.setEffortEstimate(effortEstimate);
        taskJPA.setPercentageOfExecution(percentageOfExecution);
        taskJPA.setPrecedenceList(precedenceList);
        taskJPA.setTypeOfTask(typeOfTask);
        taskJPA.setResourceResponsible(responsible);
        taskJPA.setTaskLogs(taskLogs);
        taskJPA.setTaskStatus(taskStatus);
        taskJPA.setProjectCode(projectCode);

        if (taskContainerID.getClass() == SprintID.class) {
            SprintID sprintID = (SprintID) taskContainerID;
            Integer sprintNumber = sprintID.getSprintNumber();
            taskJPA.setSprintNumber(sprintNumber);
        } else if (taskContainerID.getClass() == UserStoryID.class) {
            UserStoryID userStoryID = (UserStoryID) taskContainerID;
            String userStoryCode = userStoryID.getUserStoryCode();
            taskJPA.setUserStoryCode(userStoryCode);
        }


        return taskJPA;

    }


}
