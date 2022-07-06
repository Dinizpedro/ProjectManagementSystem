package switchfive.project.domain.shared.valueObjects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskCodeTest {

    @Test
    @DisplayName("Create TaskCode Successfully.")
    void createTaskCodeSuccessfully(){
        //Arrange
        TaskCode taskCode = TaskCode.createTaskCode("T01");
        TaskCode taskCodeOther = TaskCode.createTaskCode("T01");

        //Assert
        assertEquals(taskCode,taskCodeOther);
    }


    @Test
    @DisplayName("Create TaskCode Not Equals But Successfully.")
    void createTaskCodeNotEqualsSuccessfully(){
        //Arrange
        TaskCode taskCode = TaskCode.createTaskCode("T01");
        TaskCode taskCodeOther = TaskCode.createTaskCode("T02");

        //Assert
        assertNotEquals(taskCode,taskCodeOther);
    }

    @Test
    @DisplayName("Create TaskCode is not successful because input code is not valid.")
    void createTaskCodeUnsuccessful(){

        //Assert

        assertThrows(IllegalArgumentException.class, () ->
                TaskCode.createTaskCode("P01"));

    }
    @Test
    @DisplayName("Create TaskCode is not successful because input code is not valid, receiving exception message.")
    void createTaskCodeUnsuccessfulWithMessage(){

        //Arrange & Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            TaskCode.createTaskCode("P0");
        });

        String expectedMessage = "Invalid Task Code";
        String actualMessage = exception.getMessage();

        //Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("TaskCode is not an instance of other object.")
    void notAnInstanceOfTaskCode(){
        TaskCode taskCode = TaskCode.createTaskCode("T01");
        Object other = new Object();

        //Act
        boolean result = taskCode.equals(other);

        //Assert
        assertFalse(result);
    }


    @Test
    @DisplayName("TaskCode is not an instance of other object.")
    void taskCodeIsNotAnInstanceOf(){
        TaskCode taskCode = TaskCode.createTaskCode("T01");
        Object other = new Object();

        //Assert
        assertNotEquals(taskCode,other);
    }

    @Test
    @DisplayName("TaskCode is not an instance of other object.")
    void taskCodeIsEqualToOtherObject(){
        TaskCode taskCode = TaskCode.createTaskCode("T01");
        Object other = taskCode;

        boolean result = other.equals(taskCode);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("TaskCode is equal to other object, returning true.")
    void taskCodeIsEqualToOther(){
        TaskCode taskCode = TaskCode.createTaskCode("T01");
        TaskCode otherCode = TaskCode.createTaskCode("T01");

        //Act
        boolean result = taskCode.equals(otherCode);

        //Assert
        assertTrue(result);
    }
}