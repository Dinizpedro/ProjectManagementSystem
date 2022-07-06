package switchfive.project.domain.shared.valueObjects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchfive.project.domain.aggregates.task.TaskContainer;

import static org.junit.jupiter.api.Assertions.*;

class TaskIDTest {

    @Test
    @DisplayName("TaskID is created successfully for two sprints in the same project and sprintID, " +
            "using TaskContainer interface")
    void taskIdIsCreatedWithinSprint() {
        // Arrange
        SprintID sprintOne = SprintID.createSprintID("1", 2);
        SprintID sprintTwo = SprintID.createSprintID("1", 2);

        TaskContainer taskContainerOne = sprintOne;
        TaskContainer taskContainerTwo = sprintTwo;

        TaskCode taskCode = TaskCode.createTaskCode("T1");

        TaskID expected = TaskID.createIDTask(taskCode,taskContainerOne);
        TaskID actual = TaskID.createIDTask(taskCode, taskContainerTwo);


        // Assert
        assertEquals(expected, actual);
    }


    @Test
    @DisplayName("TaskID is created but is not equal to other because sprints have different ID's.")
    void taskIdIsCreatedWithinDifferentSprints() {
        // Arrange
        SprintID sprintID = SprintID.createSprintID("P0001",1);
        SprintID sprintIDOne = SprintID.createSprintID("P0001",2);

        TaskContainer taskContainerOne = sprintID;
        TaskContainer taskContainerTwo = sprintIDOne;

        TaskCode taskCode = TaskCode.createTaskCode("T1");

        TaskID expected = TaskID.createIDTask(taskCode,taskContainerOne);
        TaskID actual = TaskID.createIDTask(taskCode, taskContainerTwo);

        // Assert
        assertNotEquals(expected, actual);
    }

    @Test
    @DisplayName("TaskID is created successfully using TaskContainer for a UserStory with the same code.")
    void taskIdIsCreatedWithinUserStory() {
        // Arrange
        UserStoryID userStoryID = UserStoryID.createUserStoryID("P0001","US1");
        UserStoryID userStoryIDOne = UserStoryID.createUserStoryID("P0001","US1");

        TaskContainer taskContainerOne = userStoryID;
        TaskContainer taskContainerTwo = userStoryIDOne;

        TaskCode taskCode = TaskCode.createTaskCode("T1");

        TaskID expected = TaskID.createIDTask(taskCode,taskContainerOne);
        TaskID actual = TaskID.createIDTask(taskCode, taskContainerTwo);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("TaskID is created successfully using TaskContainer for two different USID which have different codes.")
    void taskIdIsCreatedWithinUserStoryNotEquals() {
        // Arrange
        UserStoryID userStoryID = UserStoryID.createUserStoryID("P0001","US1");
        UserStoryID userStoryIDOne = UserStoryID.createUserStoryID("P0001","US2");

        TaskContainer taskContainerOne = userStoryID;
        TaskContainer taskContainerTwo = userStoryIDOne;

        TaskCode taskCode = TaskCode.createTaskCode("T1");

        TaskID expected = TaskID.createIDTask(taskCode,taskContainerOne);
        TaskID actual = TaskID.createIDTask(taskCode, taskContainerTwo);

        // Assert
        assertNotEquals(expected, actual);
    }


    @Test
    @DisplayName("TaskID's have the same hashcode.")
    void hashCodeIsTheSame() {
        // Arrange
        SprintID sprintOne = SprintID.createSprintID("1", 2);
        SprintID sprintTwo = SprintID.createSprintID("1", 2);

        TaskCode taskCode = TaskCode.createTaskCode("T1");

        TaskID expected = TaskID.createIDTask(taskCode, sprintOne);
        TaskID actual = TaskID.createIDTask(taskCode, sprintTwo);

        // Assert
        assertEquals(expected.hashCode(), actual.hashCode());
    }

    @Test
    @DisplayName("TaskID are the sameInstanceOf.")
    void trueWhenAssertingTheSameInstance() {
        // Arrange
        SprintID sprintOne = SprintID.createSprintID("1", 2);

        TaskCode taskCode = TaskCode.createTaskCode("T1");

        TaskID expected = TaskID.createIDTask(taskCode, sprintOne);

        // Act

        // Assert
        assertEquals(expected, expected);
    }

    @Test
    @DisplayName("Object is not an instance of TaskID.")
    void returnsFalseWhenAssertingWithADifferentObject() {
        // Arrange
        SprintID sprintTwo = SprintID.createSprintID("1", 2);

        TaskCode taskCode = TaskCode.createTaskCode("T1");

        Object object = new Object();

        TaskID expected = TaskID.createIDTask(taskCode, sprintTwo);

        // Assert
        assertNotEquals(expected, object);
    }

    @Test
    @DisplayName("Two equal taskID's have the same hashcode.")
    void testHashCode() {
        //Arrange
        SprintID sprintOne = SprintID.createSprintID("1", 2);

        TaskCode taskCode = TaskCode.createTaskCode("T1");

        TaskID taskID = TaskID.createIDTask(taskCode, sprintOne);
        TaskID taskIDOne = TaskID.createIDTask(taskCode, sprintOne);

        //Act
        boolean actualResult = taskID.hashCode() == taskIDOne.hashCode();

        //Assert
        assertTrue(actualResult);
    }

    @Test
    @DisplayName("Two different taskID's have different hashcodes.")
    void testHashCodeFails() {
        //Arrange
        SprintID sprintOne = SprintID.createSprintID("1", 2);

        TaskCode taskCode = TaskCode.createTaskCode("T1");
        TaskCode taskCodeOne = TaskCode.createTaskCode("T2");

        TaskID taskID = TaskID.createIDTask(taskCode, sprintOne);
        TaskID taskIDOne = TaskID.createIDTask(taskCodeOne, sprintOne);

        //Act
        boolean actualResult = taskID.hashCode() == taskIDOne.hashCode();

        //Assert
        assertFalse(actualResult);
    }

    @Test
    @DisplayName("Get TaskContainer of two TaskID's which have the same TaskContainer.")
    void getTaskContainerAreEquals() {
        // Arrange
        SprintID sprintOne = SprintID.createSprintID("1", 2);

        TaskContainer taskContainerOne = sprintOne;
        TaskCode taskCode = TaskCode.createTaskCode("T1");

        TaskID expected = TaskID.createIDTask(taskCode,taskContainerOne);
        TaskID actual = TaskID.createIDTask(taskCode, taskContainerOne);

        // Assert
        assertEquals(expected.getTaskContainer(), actual.getTaskContainer());
    }

    @Test
    @DisplayName("TaskContainer has the same value as other.")
    void taskContainerIsTheSameValueAsOther() {
        // Arrange
        SprintID sprintOne = SprintID.createSprintID("1", 2);

        TaskContainer taskContainerOne = sprintOne;
        TaskContainer taskContainerTwo = sprintOne;
        TaskCode taskCode = TaskCode.createTaskCode("T1");

        TaskID expected = TaskID.createIDTask(taskCode,taskContainerOne);
        TaskID actual = TaskID.createIDTask(taskCode, taskContainerTwo);

        //Act
        boolean result = expected.sameValueAs(actual);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("TaskContainer doesn't have the same value as other.")
    void taskContainerIsNotTheSameValueAsOther() {
        // Arrange
        SprintID sprintOne = SprintID.createSprintID("1", 1);
        SprintID sprintTwo = SprintID.createSprintID("1", 2);

        TaskContainer taskContainerOne = sprintOne;
        TaskContainer taskContainerTwo = sprintTwo;
        TaskCode taskCode = TaskCode.createTaskCode("T1");

        TaskID expected = TaskID.createIDTask(taskCode,taskContainerOne);
        TaskID actual = TaskID.createIDTask(taskCode, taskContainerTwo);

        //Act
        boolean result = expected.sameValueAs(actual);

        // Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Get TaskContainer for two TaskID's which have two different TaskContainers.")
    void getTaskContainerAreNotEquals() {
        // Arrange
        SprintID sprintOne = SprintID.createSprintID("1", 2);

        UserStoryID userStoryID = UserStoryID.createUserStoryID("P0001","US1");

        TaskContainer taskContainerOne = sprintOne;
        TaskContainer taskContainerTwo = userStoryID;

        TaskCode taskCode = TaskCode.createTaskCode("T1");

        TaskID expected = TaskID.createIDTask(taskCode,taskContainerOne);
        TaskID actual = TaskID.createIDTask(taskCode, taskContainerTwo);

        // Assert
        assertNotEquals(expected.getTaskContainer(), actual.getTaskContainer());
    }

    @Test
    @DisplayName("Get TaskCode of two different TaskID's which have the same code.")
    void getTaskCodeAreEquals() {
        // Arrange
        SprintID sprintOne = SprintID.createSprintID("1", 2);

        TaskContainer taskContainerOne = sprintOne;

        TaskCode taskCode = TaskCode.createTaskCode("T1");

        TaskID expected = TaskID.createIDTask(taskCode,taskContainerOne);
        TaskID actual = TaskID.createIDTask(taskCode, taskContainerOne);

        // Assert
        assertEquals(expected.getTaskCode(), actual.getTaskCode());
    }


    @Test
    @DisplayName("Get TaskCode of TaskID's but code is not the same.")
    void getTaskCodeAreNotEquals() {
        // Arrange
        SprintID sprintOne = SprintID.createSprintID("1", 2);

        TaskContainer taskContainerOne = sprintOne;

        TaskCode taskCode = TaskCode.createTaskCode("T1");
        TaskCode taskCodeOne = TaskCode.createTaskCode("T2");

        TaskID expected = TaskID.createIDTask(taskCode,taskContainerOne);
        TaskID actual = TaskID.createIDTask(taskCodeOne, taskContainerOne);

       // Assert
        assertNotEquals(expected.getTaskCode(), actual.getTaskCode());
    }

    @Test
    @DisplayName("TaskCode doesn't have the same value as other code.")
    void taskCodeIsNotTheSameValueAsOther() {
        // Arrange
        TaskCode taskCode = TaskCode.createTaskCode("T1");
        TaskCode otherCode = TaskCode.createTaskCode("T2");

        //Act
        boolean result = taskCode.sameValueAs(otherCode);

        // Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("TaskCode has the same value as other code.")
    void taskCodeIsTheSameValueAsOther() {
        // Arrange
        TaskCode taskCode = TaskCode.createTaskCode("T1");
        TaskCode otherCode = TaskCode.createTaskCode("T1");

        //Act
        boolean result = taskCode.sameValueAs(otherCode);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("TaskID doesn't have same value as because other is Null.")
    void taskIDIsNull() {
        // Arrange
        SprintID sprintOne = SprintID.createSprintID("1", 2);

        TaskContainer taskContainerOne = sprintOne;

        TaskCode taskCode = TaskCode.createTaskCode("T1");
        TaskCode taskCodeOne = TaskCode.createTaskCode("T2");

        TaskID expected = TaskID.createIDTask(taskCode,taskContainerOne);
        TaskID actual = TaskID.createIDTask(null, null);

        //Act

        boolean result = expected.sameValueAs(actual);

        // Assert

        assertFalse(result);

    }

}

