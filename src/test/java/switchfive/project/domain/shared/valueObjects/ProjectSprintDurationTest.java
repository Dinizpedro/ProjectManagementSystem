package switchfive.project.domain.shared.valueObjects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectSprintDurationTest {

    @Test
    @DisplayName("Sprint duration is greater than maximum bound.")
    void sprintsDurationCreationFailsUpperBound() {
        //Arrange
        int sprintDurationsInput = 10;

        //Act && Assert
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ProjectSprintDuration.create(sprintDurationsInput);
                });
    }

    @Test
    @DisplayName("Sprint duration is lower than minimum bound.")
    void sprintsDurationCreationFailsLowerBound() {
        //Arrange
        int sprintDurationsInput = 0;

        //Act && Assert
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ProjectSprintDuration.create(sprintDurationsInput);
                });
    }

    @Test
    @DisplayName("Two instances of ProjectSprintDuration"
            + " are equals.")
    void instancesAreEquals() {
        //Arrange
        int sprintDurationsInput = 1;

        ProjectSprintDuration sprintDurationOne =
                ProjectSprintDuration.create(sprintDurationsInput);
        ProjectSprintDuration sprintDurationTwo =
                ProjectSprintDuration.create(sprintDurationsInput);

        //Act
        boolean actual = sprintDurationOne.equals(sprintDurationTwo);

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Two instances of ProjectSprintDuration" +
            " are not equals.")
    void instancesAreNotEquals() {

        //Arrange
        int sprintDurationOne = 5;
        int sprintDurationTwo = 9;

        ProjectSprintDuration sprintsOne =
                ProjectSprintDuration.create(sprintDurationOne);
        ProjectSprintDuration sprintsTwo =
                ProjectSprintDuration.create(sprintDurationTwo);

        //Act
        boolean actual = sprintsOne.equals(sprintsTwo);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Compared instance is null. Expected false.")
    void instancesIsNull() {

        //Arrange
        int sprintDurationsInput = 5;

        ProjectSprintDuration sprintDurationOne =
                ProjectSprintDuration.create(sprintDurationsInput);

        //Act
        boolean actual = sprintDurationOne.equals(null);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Two instances of ProjectSprintDuration are the " +
            "same.")
    void instancesAreTheSame() {
        //Arrange
        int sprintDurationsInput = 5;

        ProjectSprintDuration sprintDurationOne =
                ProjectSprintDuration.create(sprintDurationsInput);
        ProjectSprintDuration sprintDurationTwo =
                sprintDurationOne;

        //Act
        boolean actual = sprintDurationOne.equals(sprintDurationTwo);

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Compared object are not an instance of " +
            "ProjectSprintDuration class.")
    void areNotAnInstanceOfThisClass() {
        //Arrange
        int sprintDurationsInput = 5;

        ProjectSprintDuration sprintDurationOne =
                ProjectSprintDuration.create(sprintDurationsInput);
        Object sprintDurationTwo = new Object();

        //Act
        boolean actual = sprintDurationOne.equals(sprintDurationTwo);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Instances are equals. Expected same hashcode.")
    void sameHashcode() {
        //Arrange
        int sprintDurationsInput = 5;

        ProjectSprintDuration sprintDurationOne =
                ProjectSprintDuration.create(sprintDurationsInput);
        ProjectSprintDuration sprintDurationTwo =
                ProjectSprintDuration.create(sprintDurationsInput);

        //Act
        boolean actual = sprintDurationOne.hashCode() ==
                sprintDurationTwo.hashCode();

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Instances are not equals. Expected different hashcode.")
    void differentHashcode() {
        //Arrange

        int sprintDurationOne = 5;
        int sprintDurationTwo = 9;

        ProjectSprintDuration sprintsOne =
                ProjectSprintDuration.create(sprintDurationOne);
        ProjectSprintDuration sprintsTwo =
                ProjectSprintDuration.create(sprintDurationTwo);

        //Act
        boolean actual = sprintsOne.hashCode() == sprintsTwo.hashCode();

        //Assert
        assertFalse(actual);
    }

    @Test
    void getDuration() {
        //Arrange
        int sprintDurationOne = 5;

        ProjectSprintDuration sprintsOne =
                ProjectSprintDuration.create(sprintDurationOne);

        int result = sprintsOne.getDuration();

        //Act
        boolean actual = sprintDurationOne == result;

        //Assert
        assertTrue(actual);
    }
}

