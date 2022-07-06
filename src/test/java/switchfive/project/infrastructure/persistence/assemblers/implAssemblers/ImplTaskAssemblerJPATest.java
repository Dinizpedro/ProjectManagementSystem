package switchfive.project.infrastructure.persistence.assemblers.implAssemblers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switchfive.project.assemblers.assemblersJPA.implAssemblersJPA.ImplTaskAssemblerJPA;
import switchfive.project.dataModel.dataJPA.TaskIDJPA;
import switchfive.project.dataModel.dataJPA.TaskJPA;
import switchfive.project.domain.aggregates.task.Task;
import switchfive.project.domain.factories.iFactories.ITaskFactory;
import switchfive.project.domain.shared.valueObjects.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@SpringBootTest
class ImplTaskAssemblerJPATest {


    @Autowired
    ITaskFactory taskFactory;

    @Autowired
    ImplTaskAssemblerJPA implTaskAssemblerJPA;

    @Test
    @DisplayName("Should create a Task in a Sprint from JPA to Domain values.")
    void toDomainTaskJPAInSprint() throws ParseException {

        // Arrange
        TaskJPA taskJPA = new TaskJPA();
        taskJPA.setTaskCode("T1");
        taskJPA.setProjectCode("P1010");
        taskJPA.setSprintNumber(1);
        taskJPA.setName("Task One");
        taskJPA.setDescription("Task One Description");
        taskJPA.setStartDate("01/01/2024");
        taskJPA.setEndDate("02/01/2024");
        taskJPA.setHoursSpent(2);
        taskJPA.setEffortEstimate(1);
        taskJPA.setPercentageOfExecution(2);

        List<TaskIDJPA> precedenceList = new ArrayList<>();
        TaskIDJPA taskPrecedenceOne = new TaskIDJPA();
        taskPrecedenceOne.setProjectCode("P1010");
        taskPrecedenceOne.setTaskCode("T2");

        taskJPA.setPrecedenceList(precedenceList);
        taskJPA.setTypeOfTask("MEETING");
        taskJPA.setResourceResponsible("123e4567-e89b-12d3-a456-426614174000");

        taskJPA.setTaskStatus("PLANNED");


        TaskCode taskCode = TaskCode.createTaskCode("T1");
        SprintID sprintID = SprintID.createSprintID("P1010", 1);
        TaskID taskID = TaskID.createIDTask(taskCode, sprintID);
        TaskName taskName = TaskName.createTaskName("Task One");
        TaskDescription taskDescription = TaskDescription.createTaskDescription("Task One Description");
        Time time = Time.create("01/01/2024", "02/01/2024");

        Hour hoursSpent = Hour.createHour(2);
        EffortEstimate effortEstimate = EffortEstimate.createEffortEstimate(1);
        TaskPercentageOfExecution taskPercentageOfExecution = TaskPercentageOfExecution.createPercentage(2);
        TypeOfTask typeOfTask = TypeOfTask.valueOf("MEETING");
        ResourceID resourceID = ResourceID.createResourceID("123e4567-e89b-12d3-a456-426614174000");

        List<Log> logList = null;
        TaskStatus taskStatus = TaskStatus.valueOf("PLANNED");


        Task expected = new Task(taskID, taskName, taskDescription, time,
                hoursSpent, effortEstimate, taskPercentageOfExecution, precedenceList,
                typeOfTask, resourceID, logList, taskStatus);

        // Act
        Task actual = implTaskAssemblerJPA.toDomain(taskJPA);

        // Assert
        assertEquals(expected, actual);

    }

    @Test
    @DisplayName("Should create a Task in a User Story from JPA to Domain values.")
    void toDomainTaskJPAInUserStory() throws ParseException {

        //-----ARRANGE----

        TaskJPA taskJPA = new TaskJPA();
        taskJPA.setTaskCode("T1");
        taskJPA.setProjectCode("P1010");
        taskJPA.setSprintNumber(null);
        taskJPA.setUserStoryCode("US1");
        taskJPA.setName("Task One");
        taskJPA.setDescription("Task One Description");
        taskJPA.setStartDate("01/01/2024");
        taskJPA.setEndDate("02/01/2024");
        taskJPA.setHoursSpent(2);
        taskJPA.setEffortEstimate(1);
        taskJPA.setPercentageOfExecution(2);

        List<TaskIDJPA> precedenceList = new ArrayList<>();
        TaskIDJPA taskPrecedenceOne = new TaskIDJPA();
        taskPrecedenceOne.setProjectCode("P1010");
        taskPrecedenceOne.setTaskCode("T2");

        taskJPA.setPrecedenceList(precedenceList);
        taskJPA.setTypeOfTask("MEETING");
        taskJPA.setResourceResponsible("123e4567-e89b-12d3-a456-426614174000");

        taskJPA.setTaskStatus("PLANNED");


        TaskCode taskCode = TaskCode.createTaskCode("T1");
        UserStoryID userStoryID = UserStoryID.createUserStoryID("P1010", "US1");
        TaskID taskID = TaskID.createIDTask(taskCode, userStoryID);
        TaskName taskName = TaskName.createTaskName("Task One");
        TaskDescription taskDescription = TaskDescription.createTaskDescription("Task One Description");
        Time time = Time.create("01/01/2024", "02/01/2024");

        Hour hoursSpent = Hour.createHour(2);
        EffortEstimate effortEstimate = EffortEstimate.createEffortEstimate(1);
        TaskPercentageOfExecution taskPercentageOfExecution = TaskPercentageOfExecution.createPercentage(2);
        TypeOfTask typeOfTask = TypeOfTask.valueOf("MEETING");
        ResourceID resourceID = ResourceID.createResourceID("123e4567-e89b-12d3-a456-426614174000");

        List<Log> logList = null;
        TaskStatus taskStatus = TaskStatus.valueOf("PLANNED");


        Task expected = new Task(taskID, taskName, taskDescription, time,
                hoursSpent, effortEstimate, taskPercentageOfExecution, precedenceList,
                typeOfTask, resourceID, logList, taskStatus);

        //-----ACT----

        Task actual = implTaskAssemblerJPA.toDomain(taskJPA);

        //-----ASSERT----

        assertEquals(expected, actual);

    }

    @Test
    @DisplayName("Should return a saved Task (data) in User Story without precedence List.")
    void toDataWithUserStoryWithoutPrecedence() throws ParseException {

        //-----ARRANGE----

        TaskName taskNameObj = TaskName.createTaskName("Dummy Task");
        TaskDescription taskDescriptionObj = TaskDescription.createTaskDescription("description");
        String startDate = "23/06/2023";
        String endDate = "23/07/2023";
        Time time = Time.create(startDate,endDate);
        Hour hoursSpentObj = Hour.createHour(20);
        EffortEstimate effortEstimateObj = EffortEstimate.createEffortEstimate(1);
        TaskPercentageOfExecution taskPercentageOfExecutionObj = TaskPercentageOfExecution.createPercentage(50);
        List<TaskIDJPA> precedenceList = null;
        TypeOfTask typeOfTaskObj = TypeOfTask.MEETING;
        ResourceID responsibleObj = ResourceID.createResourceID("123e4567-e89b-12d3-a456-426614174000");
        List<Log> logs = null;
        TaskStatus taskStatusObj = TaskStatus.PLANNED;
        TaskCode taskCodeObj = TaskCode.createTaskCode("T1");
        UserStoryID userStoryIDObj = UserStoryID.createUserStoryID("A010", "US1");
        TaskID taskIDObj = TaskID.createIDTask(taskCodeObj,userStoryIDObj);

        Task task = new Task(taskIDObj,taskNameObj,taskDescriptionObj,time,hoursSpentObj,effortEstimateObj,
                taskPercentageOfExecutionObj,precedenceList,typeOfTaskObj,responsibleObj,logs,taskStatusObj);

        String taskName = task.getName();
        String taskDescription = task.getDescription();
        double hoursSpent = task.getHoursSpent();
        Integer effortEstimate = task.getEffortEstimate();
        double percentageOfExecution = task.getPercentageOfExecution();
        String typeOfTask = task.getTypeOfTask();
        String responsible = task.getResponsible();
        String taskStatus = task.getTaskStatus();
        String projectCode = taskIDObj.getTaskContainer().getProjectCode();
        Object taskContainerID = taskIDObj.getTaskContainer().getTaskContainerID();

        TaskJPA taskJPA = new TaskJPA();
        taskJPA.setTaskCode(taskIDObj.getTaskCode());
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
        taskJPA.setTaskLogs(logs);
        taskJPA.setProjectCode(projectCode);
        taskJPA.setTaskStatus(taskStatus);

        taskContainerID.getClass();
        userStoryIDObj = (UserStoryID) taskContainerID;
        String userStoryCode = userStoryIDObj.getUserStoryCode();
        taskJPA.setUserStoryCode(userStoryCode);

        //-----ACT----

        TaskJPA expected = taskJPA;
        TaskJPA result = implTaskAssemblerJPA.toData(task);

        //-----ASSERT----

        assertEquals(expected,result);

    }

    @Test
    @DisplayName("Should return a saved Task (data) in User Story with a precedence List.")
    void toDataWithUserStoryWithPrecedence() throws ParseException {

        //-----ARRANGE----

        TaskName taskNameObj = TaskName.createTaskName("Dummy Task");
        TaskDescription taskDescriptionObj = TaskDescription.createTaskDescription("description");
        String startDate = "23/06/2023";
        String endDate = "23/07/2023";
        Time time = Time.create(startDate,endDate);
        Hour hoursSpentObj = Hour.createHour(20);
        EffortEstimate effortEstimateObj = EffortEstimate.createEffortEstimate(1);
        TaskPercentageOfExecution taskPercentageOfExecutionObj = TaskPercentageOfExecution.createPercentage(50);
        List<TaskIDJPA> precedence = new ArrayList<>();
        TypeOfTask typeOfTaskObj = TypeOfTask.MEETING;
        ResourceID responsibleObj = ResourceID.createResourceID("123e4567-e89b-12d3-a456-426614174000");
        List<Log> logs = null;
        TaskStatus taskStatusObj = TaskStatus.PLANNED;
        TaskCode taskCodeObj = TaskCode.createTaskCode("T2");
        UserStoryID userStoryIDObj = UserStoryID.createUserStoryID("A010", "US1");
        TaskID taskIDObj = TaskID.createIDTask(taskCodeObj,userStoryIDObj);
        String taskCodeObjOther = "T1";
        TaskIDJPA taskIDObjOther = TaskIDJPA.createTaskIDJPA(taskCodeObjOther,"A010");

        precedence.add(taskIDObjOther);

        Task task = new Task(taskIDObj,taskNameObj,taskDescriptionObj,time,hoursSpentObj,effortEstimateObj,
                taskPercentageOfExecutionObj,precedence,typeOfTaskObj,responsibleObj,logs,taskStatusObj);


        String taskName = task.getName();
        String taskDescription = task.getDescription();
        double hoursSpent = task.getHoursSpent();
        Integer effortEstimate = task.getEffortEstimate();
        double percentageOfExecution = task.getPercentageOfExecution();
        String typeOfTask = task.getTypeOfTask();
        String responsible = task.getResponsible();
        String taskStatus = task.getTaskStatus();
        List<TaskIDJPA> precedenceList = task.getPrecedenceList();
        String projectCode = taskIDObj.getTaskContainer().getProjectCode();
        Object taskContainerID = taskIDObj.getTaskContainer().getTaskContainerID();


        TaskJPA taskJPA = new TaskJPA();
        taskJPA.setTaskCode(taskIDObj.getTaskCode());
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
        taskJPA.setTaskLogs(logs);
        taskJPA.setProjectCode(projectCode);
        taskJPA.setTaskStatus(taskStatus);

        taskContainerID.getClass();
        userStoryIDObj = (UserStoryID) taskContainerID;
        String userStoryCode = userStoryIDObj.getUserStoryCode();
        taskJPA.setUserStoryCode(userStoryCode);

        //-----ACT----

        TaskJPA expected = taskJPA;
        TaskJPA result = implTaskAssemblerJPA.toData(task);

        //-----ASSERT----

        assertEquals(expected,result);

    }

    @Test
    @DisplayName("Should return a saved Task (data) in Sprint without precedence List.")
    void toDataInSprintWithoutPrecedence() throws ParseException {

        //-----ARRANGE----

        TaskName taskNameObj = TaskName.createTaskName("Dummy Task");
        TaskDescription taskDescriptionObj = TaskDescription.createTaskDescription("description");
        String startDate = "23/06/2023";
        String endDate = "23/07/2023";
        Time time = Time.create(startDate,endDate);
        Hour hoursSpentObj = Hour.createHour(20);
        EffortEstimate effortEstimateObj = EffortEstimate.createEffortEstimate(1);
        TaskPercentageOfExecution taskPercentageOfExecutionObj = TaskPercentageOfExecution.createPercentage(50);
        List<TaskIDJPA> precedenceList = null;
        TypeOfTask typeOfTaskObj = TypeOfTask.MEETING;
        ResourceID responsibleObj = ResourceID.createResourceID("123e4567-e89b-12d3-a456-426614174000");
        List<Log> logs = null;
        TaskStatus taskStatusObj = TaskStatus.PLANNED;
        TaskCode taskCodeObj = TaskCode.createTaskCode("T1");
        SprintID sprintID = SprintID.createSprintID("A010",1);
        TaskID taskIDObj = TaskID.createIDTask(taskCodeObj,sprintID);

        Task task = new Task(taskIDObj,taskNameObj,taskDescriptionObj,time,hoursSpentObj,effortEstimateObj,
                taskPercentageOfExecutionObj,precedenceList,typeOfTaskObj,responsibleObj,logs,taskStatusObj);

        String taskName = task.getName();
        String taskDescription = task.getDescription();
        double hoursSpent = task.getHoursSpent();
        Integer effortEstimate = task.getEffortEstimate();
        double percentageOfExecution = task.getPercentageOfExecution();
        String typeOfTask = task.getTypeOfTask();
        String responsible = task.getResponsible();
        String taskStatus = task.getTaskStatus();
        String projectCode = taskIDObj.getTaskContainer().getProjectCode();
        Object taskContainerID = taskIDObj.getTaskContainer().getTaskContainerID();

        TaskJPA taskJPA = new TaskJPA();
        taskJPA.setTaskCode(taskIDObj.getTaskCode());
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
        taskJPA.setTaskLogs(logs);
        taskJPA.setProjectCode(projectCode);
        taskJPA.setTaskStatus(taskStatus);

        taskContainerID.getClass();
        sprintID = (SprintID) taskContainerID;
        Integer sprintNumber = sprintID.getSprintNumber();
        taskJPA.setSprintNumber(sprintNumber);

        //-----ACT----

        TaskJPA expected = taskJPA;
        TaskJPA result = implTaskAssemblerJPA.toData(task);

        //-----ASSERT----

        assertEquals(expected,result);

    }

    @Test
    @DisplayName("Should return a saved Task (data) in Sprint with precedence List.")
    void toDataInSprintWithPrecedence() throws ParseException {

        //-----ARRANGE----

        TaskName taskNameObj = TaskName.createTaskName("Dummy Task");
        TaskDescription taskDescriptionObj = TaskDescription.createTaskDescription("description");
        String startDate = "23/06/2023";
        String endDate = "23/07/2023";
        Time time = Time.create(startDate,endDate);
        Hour hoursSpentObj = Hour.createHour(20);
        EffortEstimate effortEstimateObj = EffortEstimate.createEffortEstimate(1);
        TaskPercentageOfExecution taskPercentageOfExecutionObj = TaskPercentageOfExecution.createPercentage(50);
        List<TaskIDJPA> precedence = new ArrayList<>();
        TypeOfTask typeOfTaskObj = TypeOfTask.MEETING;
        ResourceID responsibleObj = ResourceID.createResourceID("123e4567-e89b-12d3-a456-426614174000");
        List<Log> logs = null;
        TaskStatus taskStatusObj = TaskStatus.PLANNED;
        TaskCode taskCodeObj = TaskCode.createTaskCode("T2");
        SprintID sprintID = SprintID.createSprintID("A010",1);
        TaskID taskIDObj = TaskID.createIDTask(taskCodeObj,sprintID);
        String taskCodeObjOther = "T1";
        TaskIDJPA taskIDObjOther = TaskIDJPA.createTaskIDJPA(taskCodeObjOther,"A010");

        precedence.add(taskIDObjOther);

        Task task = new Task(taskIDObj,taskNameObj,taskDescriptionObj,time,hoursSpentObj,effortEstimateObj,
                taskPercentageOfExecutionObj,precedence,typeOfTaskObj,responsibleObj,logs,taskStatusObj);

        String taskName = task.getName();
        String taskDescription = task.getDescription();
        double hoursSpent = task.getHoursSpent();
        Integer effortEstimate = task.getEffortEstimate();
        double percentageOfExecution = task.getPercentageOfExecution();
        String typeOfTask = task.getTypeOfTask();
        String responsible = task.getResponsible();
        String taskStatus = task.getTaskStatus();
        List<TaskIDJPA> precedenceList = task.getPrecedenceList();
        String projectCode = taskIDObj.getTaskContainer().getProjectCode();
        Object taskContainerID = taskIDObj.getTaskContainer().getTaskContainerID();

        TaskJPA taskJPA = new TaskJPA();
        taskJPA.setTaskCode(taskIDObj.getTaskCode());
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
        taskJPA.setTaskLogs(logs);
        taskJPA.setProjectCode(projectCode);
        taskJPA.setTaskStatus(taskStatus);

        taskContainerID.getClass();
        sprintID = (SprintID) taskContainerID;
        Integer sprintNumber = sprintID.getSprintNumber();
        taskJPA.setSprintNumber(sprintNumber);

        //-----ACT----

        TaskJPA expected = taskJPA;
        TaskJPA result = implTaskAssemblerJPA.toData(task);

        //-----ASSERT----

        assertEquals(expected,result);

    }


}
