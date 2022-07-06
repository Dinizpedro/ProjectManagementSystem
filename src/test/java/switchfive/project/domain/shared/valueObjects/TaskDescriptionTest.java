package switchfive.project.domain.shared.valueObjects;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.junit.jupiter.api.Assertions.*;

class TaskDescriptionTest {

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "a"})
    void shouldThrowAnIllegalArgumentExceptionForEmptyAndBlankStrings(String testName) {
        assertThrows(IllegalArgumentException.class, () -> TaskDescription.createTaskDescription(testName));
    }

    @Test
    void shouldThrowAnIllegalArgumentExceptionForStringsOver149Chars() {
        //Arrange
        String random150Chars = random(150);

        //Assert
        assertThrows(IllegalArgumentException.class, () -> TaskDescription.createTaskDescription(random150Chars));
    }

    @Test
    void shouldReturnTrueComparingInstancesWithTheSameValue() {
        //Arrange
        String random149Chars = random(149);

        TaskDescription descriptionOne = TaskDescription.createTaskDescription(random149Chars);
        TaskDescription descriptionTwo = TaskDescription.createTaskDescription(random149Chars);

        //Assert
        assertEquals(descriptionOne, descriptionTwo);
    }

    @Test
    void shouldReturnTrueAssertingAnInstanceNotEqualToNull() {
        //Arrange
        TaskDescription validDescription = TaskDescription.createTaskDescription("A Description");

        //Assert
        assertNotEquals(null, validDescription);
    }

    @Test
    void shouldReturnTrueComparingTheSameInstance() {
        //Arrange
        TaskDescription validDescription = TaskDescription.createTaskDescription("A Description");

        //Assert
        assertEquals(validDescription, validDescription);
    }

    @Test
    void shouldReturnTrueAssertingTwoDifferentObjectsAreNotEquals() {
        //Arrange
        TaskDescription descriptionOne = TaskDescription.createTaskDescription("Description One");
        TaskDescription descriptionTwo = TaskDescription.createTaskDescription("Description Two");

        //Assert
        assertNotEquals(descriptionOne, descriptionTwo);
    }

    @Test
    void shouldReturnTrueComparingHashCodesOfTheSameValue() {
        //Arrange
        TaskDescription descriptionOne = TaskDescription.createTaskDescription("Description");
        TaskDescription descriptionTwo = TaskDescription.createTaskDescription("Description");

        //Assert
        assertEquals(descriptionOne.hashCode(), descriptionTwo.hashCode());
    }

    @Test
    void shouldReturnTrueAssertingHashCodesOfTheSameValueAreNotEquals() {
        //Arrange
        TaskDescription descriptionOne = TaskDescription.createTaskDescription("Description");
        TaskDescription descriptionTwo = TaskDescription.createTaskDescription("Different Description");

        //Assert
        assertNotEquals(descriptionOne.hashCode(), descriptionTwo.hashCode());
    }

    @Test
    void getTaskDescription() {
        //Arrange
        String description = TaskDescription.createTaskDescription("Description").getDescription();
        String descriptionTwo = TaskDescription.createTaskDescription("Different Description").getDescription();

        //Assert
        assertNotEquals(description,descriptionTwo);
    }

    @Test
    void taskDescriptionIsNull(){
        assertThrows(IllegalArgumentException.class, () -> {
            TaskDescription.createTaskDescription(null);
        });
    }
    @Test
    void taskDescriptionIsNullNotSameValueAs(){
        //Arrange
        TaskDescription taskDescription = TaskDescription.createTaskDescription("Description");
        TaskDescription otherDescription = null;

        //Act
        boolean result = taskDescription.sameValueAs(otherDescription);

        //Assert
        assertFalse(result);

    }

    @Test
    void taskDescriptionIsSameValueAsOtherTaskDescription(){
        //Arrange
        TaskDescription taskDescription = TaskDescription.createTaskDescription("Description");
        TaskDescription otherDescription = TaskDescription.createTaskDescription("Description");

        //Act
        boolean result = taskDescription.sameValueAs(otherDescription);

        //Assert
        assertTrue(result);

    }

    @Test
    void notAnInstanceOfTaskDescription(){
        //Arrange
        TaskDescription taskDescription = TaskDescription.createTaskDescription("Description");
        Object other = new Object();

        //Act
        boolean result = taskDescription.equals(other);

        //Assert
        assertFalse(result);
    }

    @Test
    void isAnInstanceOfTaskDescription(){
        //Arrange
        TaskDescription taskDescription = TaskDescription.createTaskDescription("Description");
        TaskDescription otherTaskDescription = TaskDescription.createTaskDescription("Description");

        //Act
        boolean result = taskDescription.equals(otherTaskDescription);

        //Assert
        assertTrue(result);
    }

}
