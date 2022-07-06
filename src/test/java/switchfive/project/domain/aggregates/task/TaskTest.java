package switchfive.project.domain.aggregates.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import switchfive.project.dataModel.dataJPA.TaskIDJPA;
import switchfive.project.domain.shared.valueObjects.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class TaskTest {

    // For testing purposes
    SprintID sprintOne = SprintID.createSprintID("1", 2);
    SprintID sprintTwo = SprintID.createSprintID("1", 5);

    @Test
    void shouldReturnTrueWhenComparingTasksThatAreEquals() throws ParseException {
        TaskCode taskCode = TaskCode.createTaskCode("T001");
        TaskID idTask = TaskID.createIDTask(taskCode, sprintOne);
        TaskName taskName = TaskName.createTaskName("T1");
        TaskDescription taskDescription = TaskDescription.createTaskDescription("description");
        Time taskTime = Time.create("10/10/2023", "10/11/2023");
        Hour hoursSpent = Hour.createHour(20);
        EffortEstimate effortEstimate = EffortEstimate.createEffortEstimate(1);
        List<TaskIDJPA> precedenceList = new ArrayList<>();
        TypeOfTask typeOfTask = TypeOfTask.TESTING;
        ResourceID responsible = ResourceID.createResourceID("123e4567-e89b-12d3-a456-426614174000");
        Task taskOne = new Task(
                idTask,
                taskName,
                taskDescription,
                taskTime,
                effortEstimate,
                precedenceList,
                typeOfTask,
                responsible);
        Task taskTwo = new Task(
                idTask,
                taskName,
                taskDescription,
                taskTime,
                effortEstimate,
                precedenceList,
                typeOfTask,
                responsible);

        taskOne.addTaskLog(hoursSpent, taskDescription);
        taskTwo.addTaskLog(hoursSpent, taskDescription);

        assertEquals(taskOne, taskTwo);
    }

    @Test
    void shouldReturnTrueWhenAssertingThatTheTaskOnlyHasOneLog() throws ParseException {
        TaskCode taskCode = TaskCode.createTaskCode("T001");
        TaskID idTask = TaskID.createIDTask(taskCode, sprintOne);
        TaskName taskName = TaskName.createTaskName("T1");
        TaskDescription taskDescription = TaskDescription.createTaskDescription("description");
        Time taskTime = Time.create("10/10/2023", "10/11/2023");
        Hour hoursSpent = Hour.createHour(20);
        EffortEstimate effortEstimate = EffortEstimate.createEffortEstimate(1);
        List<TaskIDJPA> precedenceList = new ArrayList<>();
        TypeOfTask typeOfTask = TypeOfTask.TESTING;
        ResourceID responsible = ResourceID.createResourceID("123e4567-e89b-12d3-a456-426614174000");
        Log log = Log.createLog(1, hoursSpent, taskDescription);
        List<Log> expectedLogList = new ArrayList<>();
        expectedLogList.add(log);
        Task task = new Task(
                idTask,
                taskName,
                taskDescription,
                taskTime,
                effortEstimate,
                precedenceList,
                typeOfTask,
                responsible);
        task.addTaskLog(hoursSpent, taskDescription);

        int actualSize = task.getTaskLogs().size();

        assertEquals(1, actualSize);
        assertEquals(expectedLogList, task.getTaskLogs());
    }

    @Test
    void shouldReturnTrueWhenVerifyingThatTheTimeIsUpdated() throws ParseException {
        TaskCode taskCode = TaskCode.createTaskCode("T001");
        TaskID idTask = TaskID.createIDTask(taskCode, sprintOne);
        TaskName taskName = TaskName.createTaskName("T1");
        TaskDescription taskDescription = TaskDescription.createTaskDescription("description");
        Time taskTime = Time.create("10/10/2023", "10/11/2023");
        Hour hoursSpent = Hour.createHour(20);
        EffortEstimate effortEstimate = EffortEstimate.createEffortEstimate(1);
        List<TaskIDJPA> precedenceList = new ArrayList<>();
        TypeOfTask typeOfTask = TypeOfTask.TESTING;
        ResourceID responsible = ResourceID.createResourceID("123e4567-e89b-12d3-a456-426614174000");
        Task task = new Task(
                idTask,
                taskName,
                taskDescription,
                taskTime,
                effortEstimate,
                precedenceList,
                typeOfTask,
                responsible);
        task.addTaskLog(hoursSpent, taskDescription);
        task.addTaskLog(hoursSpent, taskDescription);

        double expected = Hour.createHour(40).getAmountOfHours();

        assertEquals(expected, task.getHoursSpent(), 0.01);
    }

    @Test
    void addTaskLog() throws ParseException {
        TaskCode taskCode = TaskCode.createTaskCode("T001");
        TaskID idTask = TaskID.createIDTask(taskCode, sprintOne);
        TaskName taskName = TaskName.createTaskName("T1");
        TaskDescription taskDescription = TaskDescription.createTaskDescription("description");
        Time taskTime = Time.create("10/10/2023", "10/11/2023");
        Hour hoursSpent = Hour.createHour(20);
        EffortEstimate effortEstimate = EffortEstimate.createEffortEstimate(1);
        List<TaskIDJPA> precedenceList = new ArrayList<>();
        TypeOfTask typeOfTask = TypeOfTask.TESTING;
        ResourceID responsible = ResourceID.createResourceID("123e4567-e89b-12d3-a456-426614174000");
        Task expected = new Task(
                idTask,
                taskName,
                taskDescription,
                taskTime,
                effortEstimate,
                precedenceList,
                typeOfTask,
                responsible);
        Task actual = new Task(
                idTask, taskName,
                taskDescription,
                taskTime,
                effortEstimate,
                precedenceList,
                typeOfTask,
                responsible);
        expected.addTaskLog(hoursSpent, taskDescription);

        actual.addTaskLog(hoursSpent, taskDescription);

        assertEquals(expected, actual);
    }

    @Test
    void tasksAreEquals() throws ParseException {
        TaskCode taskCode = TaskCode.createTaskCode("T001");
        TaskID idTask = TaskID.createIDTask(taskCode, sprintOne);
        TaskName taskName = TaskName.createTaskName("T2");
        TaskDescription taskDescription = TaskDescription.createTaskDescription("description");
        Time taskTime = Time.create("10/10/2024", "10/11/2024");
        Hour hoursSpent = Hour.createHour(20);
        EffortEstimate effortEstimate = EffortEstimate.createEffortEstimate(1);
        List<TaskIDJPA> precedenceList = new ArrayList<>();
        TypeOfTask typeOfTask = TypeOfTask.DEPLOYMENT;
        ResourceID responsible = ResourceID.createResourceID("123e4567-e89b-12d3-a456-426614174000");
        Log log = Log.createLog(1, hoursSpent, taskDescription);
        List<Log> logList = new ArrayList<>();
        logList.add(log);
        TaskStatus taskStatus = TaskStatus.PLANNED;
        TaskPercentageOfExecution taskPercentageOfExecution = TaskPercentageOfExecution.createPercentage(2);


        Task task = new Task(idTask, taskName, taskDescription, taskTime, hoursSpent,
                effortEstimate, taskPercentageOfExecution, precedenceList, typeOfTask, responsible, logList, taskStatus);
        Task taskOne = new Task(idTask, taskName, taskDescription, taskTime, hoursSpent,
                effortEstimate, taskPercentageOfExecution, precedenceList, typeOfTask, responsible, logList, taskStatus);


        assertEquals(task, taskOne);


    }


    @Test
    void verifyIfTaskIsFinished() {
        //Arrange

        TaskID idTask = mock(TaskID.class);
        TaskName taskName = mock(TaskName.class);
        TaskDescription taskDescription = mock(TaskDescription.class);
        Time taskTime = mock(Time.class);
        Hour hoursSpent = mock(Hour.class);
        EffortEstimate effortEstimate = mock(EffortEstimate.class);
        List<TaskIDJPA> precedenceList = new ArrayList<>();
        TypeOfTask typeOfTask = mock(TypeOfTask.class);
        ResourceID responsible = mock(ResourceID.class);
        Log log = Log.createLog(1, hoursSpent, taskDescription);
        List<Log> logList = new ArrayList<>();
        logList.add(log);
        TaskStatus taskStatus = TaskStatus.FINISHED;
        TaskPercentageOfExecution taskPercentageOfExecution = TaskPercentageOfExecution.createPercentage(20);

        Task task = new Task(idTask, taskName, taskDescription, taskTime, hoursSpent, effortEstimate,
                taskPercentageOfExecution, precedenceList, typeOfTask, responsible, logList, taskStatus);

        //Act

        boolean result = task.isTaskFinished();

        //Assert

        assertTrue(result);
    }

    @Test
    void testEqualsWithTheSameObject() {
        // Arrange
        TaskID idTask = mock(TaskID.class);
        Task taskOne = new Task(idTask, null, null, null, null,
                null, null, null);

        //Act

        // Arrange
        assertEquals(taskOne, taskOne);
    }


    @Test
    void EqualsWithDifferentObjects() {
        // Arrange
        TaskID idTask = mock(TaskID.class);
        TaskID idTaskOne = mock(TaskID.class);
        Task taskOne = new Task(idTask, null, null, null,
                null, null, null, null);
        Task taskTwo = new Task(idTaskOne, null, null, null,
                null, null, null, null);

        //Act

        // Arrange
        assertNotEquals(taskOne, taskTwo);
    }

    @Test
    void EqualsSameIdentity() {
        // Arrange
        TaskCode taskCode = mock(TaskCode.class);
        TaskID idTask = TaskID.createIDTask(taskCode, sprintOne);

        Task taskOne = new Task(idTask, null, null, null,
                null, null, null, null);
        Task taskTwo = new Task(idTask, null, null, null,
                null, null, null, null);

        //Act

        // Arrange
        assertEquals(taskOne, taskTwo);
    }

    @Test
    void sameHashCode() {
        // Arrange
        TaskCode taskCode = TaskCode.createTaskCode("T001");
        TaskID idTask = TaskID.createIDTask(taskCode, sprintOne);

        Task taskOne = new Task(idTask, null, null, null,
                null, null, null, null);
        Task taskTwo = new Task(idTask, null, null, null,
                null, null, null, null);

        //Act

        // Arrange
        assertEquals(taskOne.hashCode(), taskTwo.hashCode());
    }

    @Test
    void compareHashWithZero() {
        // Arrange
        TaskCode taskCode = mock(TaskCode.class);
        TaskID idTask = TaskID.createIDTask(taskCode, sprintOne);
        Task taskOne = new Task(idTask, null, null, null,
                null, null, null, null);

        //Act

        // Arrange
        assertNotEquals(taskOne.hashCode(), 0);
    }

    @Test
    void EqualsWhenIdentityIsDifferent() {
        // Arrange
        TaskCode taskCode = mock(TaskCode.class);
        TaskID idTaskOne = TaskID.createIDTask(taskCode, sprintOne);
        TaskID idTaskTwo = TaskID.createIDTask(taskCode, sprintTwo);
        Task taskOne = new Task(idTaskOne, null, null, null,
                null, null, null, null);
        Task taskTwo = new Task(idTaskTwo, null, null, null,
                null, null, null, null);

        //Act

        // Arrange
        assertNotEquals(taskOne, taskTwo);
    }

    @Test
    void getTaskID() {
        //Arrange
        TaskCode taskCode = TaskCode.createTaskCode("T1");

        TaskContainer taskContainer = sprintOne;
        TaskID taskID = TaskID.createIDTask(taskCode, taskContainer);
        Task taskOne = new Task(taskID, null, null, null,
                null, null, null, null);


        //Act
        boolean actual = Objects.equals(taskOne.getIdTask(), taskOne.idTask);

        //Assert
        Assertions.assertTrue(actual);
    }

    @Test
    void getTaskName() {
        //Arrange
        TaskCode taskCode = TaskCode.createTaskCode("T1");
        TaskContainer taskContainer = sprintOne;
        TaskID taskID = TaskID.createIDTask(taskCode, taskContainer);
        TaskName taskName = TaskName.createTaskName("taskName");
        Task taskOne = new Task(taskID, taskName, null, null,
                null, null, null, null);


        //Act
        boolean actual = Objects.equals(taskOne.getName(), taskOne.name.getName());

        //Assert
        Assertions.assertTrue(actual);
    }

    @Test
    void getTaskDescription() {
        //Arrange
        TaskCode taskCode = TaskCode.createTaskCode("T1");
        TaskContainer taskContainer = sprintOne;
        TaskID taskID = TaskID.createIDTask(taskCode, taskContainer);
        TaskName taskName = TaskName.createTaskName("taskName");
        TaskDescription taskDescription = TaskDescription.createTaskDescription("description");
        Task taskOne = new Task(taskID, taskName, taskDescription, null,
                null, null, null, null);


        //Act
        boolean actual = Objects.equals(taskOne.getDescription(), taskOne.description.getDescription());

        //Assert
        Assertions.assertTrue(actual);
    }

    @Test
    void getHoursSpentInATask() {
        //Arrange
        TaskCode taskCode = TaskCode.createTaskCode("T1");
        TaskContainer taskContainer = sprintOne;
        TaskID taskID = TaskID.createIDTask(taskCode, taskContainer);
        TaskName taskName = TaskName.createTaskName("taskName");
        TaskDescription taskDescription = TaskDescription.createTaskDescription("description");
        Hour taskHours = Hour.createHour(20);
        TaskPercentageOfExecution taskPercentageOfExecution = TaskPercentageOfExecution.createPercentage(0);

        Task taskOne = new Task(taskID, taskName, taskDescription, null, taskHours,
                null, taskPercentageOfExecution, null, null, null,
                null, null);


        //Act
        boolean actual = Objects.equals(taskOne.getHoursSpent(), taskOne.hoursSpent.getAmountOfHours());

        //Assert
        Assertions.assertTrue(actual);
    }

    @Test
    void getEffortEstimate() {
        //Arrange
        TaskCode taskCode = TaskCode.createTaskCode("T1");
        TaskContainer taskContainer = sprintOne;
        TaskID taskID = TaskID.createIDTask(taskCode, taskContainer);
        TaskName taskName = TaskName.createTaskName("taskName");
        TaskDescription taskDescription = TaskDescription.createTaskDescription("description");
        Hour taskHours = Hour.createHour(20);
        TypeOfTask typeOfTask = TypeOfTask.TESTING;
        EffortEstimate effortEstimate = EffortEstimate.createEffortEstimate(1);
        TaskPercentageOfExecution taskPercentageOfExecution = TaskPercentageOfExecution.createPercentage(0);

        Task taskOne = new Task(taskID, taskName, taskDescription, null, taskHours, effortEstimate,
                taskPercentageOfExecution, null, typeOfTask, null, null, null);


        //Act
        boolean actual = Objects.equals(taskOne.getEffortEstimate(), taskOne.effortEstimate.getEffort());

        //Assert
        Assertions.assertTrue(actual);
    }

    @Test
    void getTypeOfTask() {
        //Arrange
        TaskCode taskCode = TaskCode.createTaskCode("T1");
        TaskContainer taskContainer = sprintOne;
        TaskID taskID = TaskID.createIDTask(taskCode, taskContainer);
        TaskName taskName = TaskName.createTaskName("taskName");
        TaskDescription taskDescription = TaskDescription.createTaskDescription("description");
        Hour taskHours = Hour.createHour(20);
        TypeOfTask typeOfTask = TypeOfTask.TESTING;
        TaskPercentageOfExecution taskPercentageOfExecution = TaskPercentageOfExecution.createPercentage(0);

        Task taskOne = new Task(taskID, taskName, taskDescription, null, taskHours, null,
                taskPercentageOfExecution, null, typeOfTask, null, null, null);


        //Act
        boolean actual = Objects.equals(taskOne.getTypeOfTask(), taskOne.typeOfTask.toString());

        //Assert
        Assertions.assertTrue(actual);
    }

    @Test
    void getTaskStatus() {
        //Arrange
        TaskCode taskCode = TaskCode.createTaskCode("T1");
        TaskContainer taskContainer = sprintOne;
        TaskID taskID = TaskID.createIDTask(taskCode, taskContainer);
        TaskName taskName = TaskName.createTaskName("taskName");
        TaskDescription taskDescription = TaskDescription.createTaskDescription("description");
        Hour taskHours = Hour.createHour(20);
        TypeOfTask typeOfTask = TypeOfTask.TESTING;
        TaskStatus taskStatus = TaskStatus.PLANNED;
        TaskPercentageOfExecution taskPercentageOfExecution = TaskPercentageOfExecution.createPercentage(0);

        Task taskOne = new Task(taskID, taskName, taskDescription, null, taskHours, null,
                taskPercentageOfExecution, null, typeOfTask, null, null, taskStatus);


        //Act
        boolean actual = Objects.equals(taskOne.getTaskStatus(), taskOne.taskStatus.toString());

        //Assert
        Assertions.assertTrue(actual);
    }

    @Test
    void getPercentageOfExecution() {
        //Arrange
        TaskCode taskCode = TaskCode.createTaskCode("T1");
        TaskContainer taskContainer = sprintOne;
        TaskID taskID = TaskID.createIDTask(taskCode, taskContainer);
        TaskName taskName = TaskName.createTaskName("taskName");
        TaskDescription taskDescription = TaskDescription.createTaskDescription("description");
        Hour taskHours = Hour.createHour(20);
        TypeOfTask typeOfTask = TypeOfTask.TESTING;
        TaskStatus taskStatus = TaskStatus.PLANNED;
        TaskPercentageOfExecution taskPercentageOfExecution = TaskPercentageOfExecution.createPercentage(20);

        Task taskOne = new Task(taskID, taskName, taskDescription, null, taskHours, null,
                taskPercentageOfExecution, null, typeOfTask, null, null, taskStatus);


        //Act
        boolean actual = Objects.equals(taskOne.getPercentageOfExecution(),
                taskOne.taskPercentageOfExecution.getPercentageOfExecution());

        //Assert
        Assertions.assertTrue(actual);
    }


    @Test
    void getResponsibleInTask() {
        //Arrange
        ResourceID resourceID = ResourceID.createResourceID("123e4567-e89b-12d3-a456-426614174000");
        TaskCode taskCode = TaskCode.createTaskCode("T1");
        TaskContainer taskContainer = sprintOne;
        TaskID taskID = TaskID.createIDTask(taskCode, taskContainer);
        TaskName taskName = TaskName.createTaskName("taskName");
        TaskDescription taskDescription = TaskDescription.createTaskDescription("description");
        Hour taskHours = Hour.createHour(20);
        TypeOfTask typeOfTask = TypeOfTask.TESTING;
        TaskStatus taskStatus = TaskStatus.PLANNED;
        TaskPercentageOfExecution taskPercentageOfExecution = TaskPercentageOfExecution.createPercentage(0);

        Task taskOne = new Task(taskID, taskName, taskDescription, null, taskHours, null,
                taskPercentageOfExecution, null, typeOfTask, resourceID, null, taskStatus);


        //Act
        boolean actual = Objects.equals(taskOne.getResponsible(), taskOne.responsible.toString());

        //Assert
        Assertions.assertTrue(actual);
    }


    @Test
    void getPrecedenceList() {
        //Arrange

        TaskIDJPA taskIDJPA = TaskIDJPA.createTaskIDJPA("T1", "A0001");
        TaskIDJPA taskIDJPAOne = TaskIDJPA.createTaskIDJPA("T2", "A0001");
        List<TaskIDJPA> precedenceList = new ArrayList<>();
        precedenceList.add(taskIDJPA);
        precedenceList.add(taskIDJPAOne);
        ResourceID resourceID = ResourceID.createResourceID("123e4567-e89b-12d3-a456-426614174000");
        TaskCode taskCode = TaskCode.createTaskCode("T3");
        TaskContainer taskContainer = sprintOne;
        TaskID taskID = TaskID.createIDTask(taskCode, taskContainer);
        TaskName taskName = TaskName.createTaskName("taskName");
        TaskDescription taskDescription = TaskDescription.createTaskDescription("description");
        Hour taskHours = Hour.createHour(20);
        TypeOfTask typeOfTask = TypeOfTask.TESTING;
        TaskStatus taskStatus = TaskStatus.PLANNED;
        TaskPercentageOfExecution taskPercentageOfExecution = TaskPercentageOfExecution.createPercentage(0);

        Task taskOne = new Task(taskID, taskName, taskDescription, null, taskHours, null,
                taskPercentageOfExecution, precedenceList, typeOfTask, resourceID, null, taskStatus);


        //Act
        boolean actual = Objects.equals(taskOne.getPrecedenceList(), taskOne.precedenceList);

        //Assert
        Assertions.assertTrue(actual);
    }

    @Test
    void getTaskLogs() {
        //Arrange
        ResourceID resourceID = ResourceID.createResourceID("123e4567-e89b-12d3-a456-426614174000");
        TaskCode taskCode = TaskCode.createTaskCode("T1");
        TaskContainer taskContainer = sprintOne;
        TaskID taskID = TaskID.createIDTask(taskCode, taskContainer);
        TaskName taskName = TaskName.createTaskName("taskName");
        TaskDescription taskDescription = TaskDescription.createTaskDescription("description");
        Hour taskHours = Hour.createHour(20);
        TypeOfTask typeOfTask = TypeOfTask.TESTING;
        TaskStatus taskStatus = TaskStatus.PLANNED;
        TaskPercentageOfExecution taskPercentageOfExecution = TaskPercentageOfExecution.createPercentage(0);
        Log log = Log.createLog(1, taskHours, taskDescription);
        List<Log> logList = new ArrayList<>();
        logList.add(log);


        Task taskOne = new Task(taskID, taskName, taskDescription, null, taskHours, null,
                taskPercentageOfExecution, null, typeOfTask, resourceID, logList, taskStatus);


        //Act
        boolean actual = Objects.equals(taskOne.getTaskLogs(), taskOne.taskLogs);

        //Assert
        Assertions.assertTrue(actual);
    }


    @Test
    void getStartDate() throws ParseException {
        //Arrange
        ResourceID resourceID = ResourceID.createResourceID("123e4567-e89b-12d3-a456-426614174000");
        TaskCode taskCode = TaskCode.createTaskCode("T1");
        TaskContainer taskContainer = sprintOne;
        TaskID taskID = TaskID.createIDTask(taskCode, taskContainer);
        TaskName taskName = TaskName.createTaskName("taskName");
        TaskDescription taskDescription = TaskDescription.createTaskDescription("description");
        Hour taskHours = Hour.createHour(20);
        TypeOfTask typeOfTask = TypeOfTask.TESTING;
        TaskStatus taskStatus = TaskStatus.PLANNED;
        TaskPercentageOfExecution taskPercentageOfExecution = TaskPercentageOfExecution.createPercentage(0);
        Time taskTime = Time.create("10/10/2023", "10/11/2023");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = taskTime.getStartDate();
        Date endDate = taskTime.getEndDate();


        Task taskOne = new Task(taskID, taskName, taskDescription, taskTime, taskHours, null,
                taskPercentageOfExecution, null, typeOfTask, resourceID, null, taskStatus);


        //Act
        boolean actualStartDate = Objects.equals(taskOne.getStartDate(), dateFormat.format(startDate));
        boolean actualEndDate = Objects.equals(taskOne.getEndDate(), dateFormat.format(endDate));

        //Assert
        Assertions.assertTrue(actualStartDate);
        Assertions.assertTrue(actualEndDate);

    }

    @Test
    void taskIsNotAnInstanceOf() throws ParseException {

        //Arrange
        TaskCode taskCode = TaskCode.createTaskCode("T001");
        TaskID idTask = TaskID.createIDTask(taskCode, sprintOne);
        TaskName taskName = TaskName.createTaskName("T1");
        TaskDescription taskDescription = TaskDescription.createTaskDescription("description");
        Time taskTime = Time.create("10/10/2023", "10/11/2023");
        Hour hoursSpent = Hour.createHour(20);
        EffortEstimate effortEstimate = EffortEstimate.createEffortEstimate(1);
        List<TaskIDJPA> precedenceList = new ArrayList<>();
        TypeOfTask typeOfTask = TypeOfTask.TESTING;
        ResourceID responsible = ResourceID.createResourceID("123e4567-e89b-12d3-a456-426614174000");
        Log log = Log.createLog(1, hoursSpent, taskDescription);
        List<Log> logList = new ArrayList<>();
        logList.add(log);

        Task task = new Task(idTask, taskName, taskDescription, taskTime, effortEstimate, precedenceList,
                typeOfTask, responsible);
        Object other = new Object();

        //Act
        boolean result = task.equals(other);

        //Assert
        assertFalse(result);

    }

    @Test
    void taskIsAnInstanceOf() throws ParseException {

        //Arrange
        TaskCode taskCode = TaskCode.createTaskCode("T001");
        TaskID idTask = TaskID.createIDTask(taskCode, sprintOne);
        TaskName taskName = TaskName.createTaskName("T1");
        TaskDescription taskDescription = TaskDescription.createTaskDescription("description");
        Time taskTime = Time.create("10/10/2023", "10/11/2023");
        Hour hoursSpent = Hour.createHour(20);
        EffortEstimate effortEstimate = EffortEstimate.createEffortEstimate(1);
        List<TaskIDJPA> precedenceList = new ArrayList<>();
        TypeOfTask typeOfTask = TypeOfTask.TESTING;
        ResourceID responsible = ResourceID.createResourceID("123e4567-e89b-12d3-a456-426614174000");
        Log log = Log.createLog(1, hoursSpent, taskDescription);
        List<Log> logList = new ArrayList<>();
        logList.add(log);

        Task task = new Task(idTask, taskName, taskDescription, taskTime, effortEstimate,
                precedenceList, typeOfTask, responsible);
        Task otherTask = new Task(idTask, taskName, taskDescription, taskTime, effortEstimate,
                precedenceList, typeOfTask, responsible);

        //Act
        boolean result = task.equals(otherTask);

        //Assert
        assertTrue(result);

    }

    @Test
    void taskDoesNotHaveSameIdentityAsOtherTask() throws ParseException {

        //Arrange
        TaskCode taskCode = TaskCode.createTaskCode("T001");
        TaskID idTask = TaskID.createIDTask(taskCode, sprintOne);
        TaskName taskName = TaskName.createTaskName("T1");
        TaskDescription taskDescription = TaskDescription.createTaskDescription("description");
        Time taskTime = Time.create("10/10/2023", "10/11/2023");
        Hour hoursSpent = Hour.createHour(20);
        EffortEstimate effortEstimate = EffortEstimate.createEffortEstimate(1);
        List<TaskIDJPA> precedenceList = new ArrayList<>();
        TypeOfTask typeOfTask = TypeOfTask.TESTING;
        ResourceID responsible = ResourceID.createResourceID("123e4567-e89b-12d3-a456-426614174000");
        Log log = Log.createLog(1, hoursSpent, taskDescription);
        List<Log> logList = new ArrayList<>();
        logList.add(log);

        Task task = new Task(idTask, taskName, taskDescription, taskTime, effortEstimate,
                precedenceList, typeOfTask, responsible);
        Task otherTask = new Task(null, taskName, taskDescription, taskTime, effortEstimate,
                precedenceList, typeOfTask, responsible);

        //Act
        boolean result = task.sameIdentityAs(otherTask);

        //Assert
        assertFalse(result);

    }

    @Test
    void taskHasSameIdentityAsOtherTask() throws ParseException {

        //Arrange
        TaskCode taskCode = TaskCode.createTaskCode("T001");
        TaskID idTask = TaskID.createIDTask(taskCode, sprintOne);
        TaskName taskName = TaskName.createTaskName("T1");
        TaskDescription taskDescription = TaskDescription.createTaskDescription("description");
        Time taskTime = Time.create("10/10/2023", "10/11/2023");
        Hour hoursSpent = Hour.createHour(20);
        EffortEstimate effortEstimate = EffortEstimate.createEffortEstimate(1);
        List<TaskIDJPA> precedenceList = new ArrayList<>();
        TypeOfTask typeOfTask = TypeOfTask.TESTING;
        ResourceID responsible = ResourceID.createResourceID("123e4567-e89b-12d3-a456-426614174000");
        Log log = Log.createLog(1, hoursSpent, taskDescription);
        List<Log> logList = new ArrayList<>();
        logList.add(log);

        Task task = new Task(idTask, taskName, taskDescription, taskTime, effortEstimate,
                precedenceList, typeOfTask, responsible);
        Task otherTask = new Task(idTask, taskName, taskDescription, taskTime, effortEstimate,
                precedenceList, typeOfTask, responsible);

        //Act
        boolean result = task.sameIdentityAs(otherTask);

        //Assert
        assertTrue(result);

    }

    @Test
    void taskIdentityIsNotEqualToOtherTask() throws ParseException {

        //Arrange
        TaskCode taskCode = TaskCode.createTaskCode("T001");
        TaskID idTask = TaskID.createIDTask(taskCode, sprintOne);
        TaskName taskName = TaskName.createTaskName("T1");
        TaskDescription taskDescription = TaskDescription.createTaskDescription("description");
        Time taskTime = Time.create("10/10/2023", "10/11/2023");
        Hour hoursSpent = Hour.createHour(20);
        EffortEstimate effortEstimate = EffortEstimate.createEffortEstimate(1);
        List<TaskIDJPA> precedenceList = new ArrayList<>();
        TypeOfTask typeOfTask = TypeOfTask.TESTING;
        ResourceID responsible = ResourceID.createResourceID("123e4567-e89b-12d3-a456-426614174000");
        Log log = Log.createLog(1, hoursSpent, taskDescription);
        List<Log> logList = new ArrayList<>();
        logList.add(log);

        Task task = new Task(idTask, taskName, taskDescription, taskTime, effortEstimate,
                precedenceList, typeOfTask, responsible);
        Task otherTask = new Task(null, taskName, taskDescription, taskTime, effortEstimate,
                precedenceList, typeOfTask, responsible);

        //Act

        //Assert
        assertNotEquals(task, otherTask);
    }


}
