package switchfive.project.domain.shared.valueObjects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.junit.jupiter.api.Assertions.*;

class TaskNameTest {


    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "a"})
    @DisplayName("This test covers the exceptions of having empty or blank strings.")
    void shouldThrowAnIllegalArgumentExceptionForEmptyAndBlankStrings(String testName) {
        assertThrows(IllegalArgumentException.class, () -> TaskName.createTaskName(testName));
    }

    @Test
    @DisplayName("This test covers the exceptions of having more than 50 characters.")
    void shouldThrowAnIllegalArgumentExceptionForBiggerThan50charsStrings() {
        String random50Chars = random(50);
        assertThrows(IllegalArgumentException.class, () -> TaskName.createTaskName(random50Chars));
    }

    @Test
    @DisplayName("This test covers the exception of having a null input in the task name.")
    void shouldReturnTrueWhenComparingToNull() {
        TaskName taskName = TaskName.createTaskName("valid");
        assertNotEquals(taskName, null);
    }

    @Test
    @DisplayName("Task name has is equal to other.")
    void shouldReturnTrueWhenTaskNameIsEqualToOther() {
        TaskName taskNameOne = TaskName.createTaskName("ab");
        TaskName taskNameTwo = TaskName.createTaskName("ab");

        assertEquals(taskNameOne, taskNameTwo);
    }

    @Test
    @DisplayName("Task name has the same value as other.")
    void shouldReturnTrueWhenTaskNameHasTheSameValue() {
        //Arrange
        TaskName taskNameOne = TaskName.createTaskName("ab");
        TaskName taskNameTwo = TaskName.createTaskName("ab");

        //Act
        boolean actualResult = taskNameOne.sameValueAs(taskNameTwo);

        assertTrue(actualResult);
    }

    @Test
    @DisplayName("Task name doesn't have the same value as other.")
    void taskNameNotSameValue() {
        //Arrange
        TaskName taskNameOne = TaskName.createTaskName("ab");
        TaskName taskNameTwo = TaskName.createTaskName("abc");

        //Act
        boolean actualResult = taskNameOne.sameValueAs(taskNameTwo);

        assertFalse(actualResult);
    }

    @Test
    void shouldReturnTrueWhenComparingHashCodesWithTheSameValue() {
        String random49Chars = random(49);
        TaskName taskNameOne = TaskName.createTaskName(random49Chars);
        TaskName taskNameTwo = TaskName.createTaskName(random49Chars);

        assertEquals(taskNameOne.hashCode(), taskNameTwo.hashCode());
    }

    @Test
    void shouldReturnTrueWhenAssertingHashCodesOfDifferentValuesAreNotEquals() {
        TaskName taskNameOne = TaskName.createTaskName("Name One");
        TaskName taskNameTwo = TaskName.createTaskName("Name Two");

        assertNotEquals(taskNameOne.hashCode(), taskNameTwo.hashCode());
    }

    @Test
    void shouldReturnTrueWhenComparingWithAnotherObjects() {
        TaskName taskNameOne = TaskName.createTaskName("valid");
        String taskNameTwo = "valid";

        assertNotEquals(taskNameOne, taskNameTwo);
    }

    @Test
    void shouldReturnFalseComparingWithNull() {
        TaskName nameOne = TaskName.createTaskName("BB");

        boolean actual = nameOne.sameValueAs(null);

        assertFalse(actual);
    }

    @Test
    void shouldReturnTrueComparingTheSameInstance() {
        TaskName nameOne = TaskName.createTaskName("AA");

        assertEquals(nameOne, nameOne);
    }

    @Test
    void shouldReturnTrueComparingTheSameValue() {
        TaskName nameOne = TaskName.createTaskName("AA");
        TaskName nameTwo = TaskName.createTaskName("AA");

        assertTrue(nameOne.equals(nameTwo));
    }

    @Test
    void taskNameIsNotEqualsToOther() {
        TaskName nameOne = TaskName.createTaskName("AA");
        TaskName nameTwo = TaskName.createTaskName("ABC");

        assertNotEquals(nameOne, nameTwo);
    }

    @Test
    void taskNameIsNullNotSameValueAs(){
        //Arrange
        TaskName nameOne = TaskName.createTaskName("AA");
        TaskName otherName =null;

        //Act
        boolean result = nameOne.sameValueAs(otherName);

        //Assert
        assertFalse(result);

    }


    @Test
    void getTaskNameEquals() {
        // Arrange
        TaskName taskName = TaskName.createTaskName("Name");
        TaskName otherName = TaskName.createTaskName("Name");


        // Assert
        assertEquals(taskName.getName(),otherName.getName());
    }

    @Test
    void getTaskNameNotEquals() {
        // Arrange
        TaskName taskName = TaskName.createTaskName("Name");
        TaskName otherName = TaskName.createTaskName("Other Name");


        // Assert
        assertNotEquals(taskName.getName(),otherName.getName());
    }

}
