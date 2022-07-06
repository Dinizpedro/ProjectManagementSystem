package switchfive.project.domain.shared.valueObjects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectNumberOfPlannedSprintsTest {

    @Test
    @DisplayName("Number of Planned Sprints is greater than maximum "
            + "bound.")
    void plannedSprintsCreationFailsUpperBound() {
        //Arrange
        int plannedSprintsInput = 1000;

        //Act && Assert
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ProjectNumberOfPlannedSprints.create(plannedSprintsInput);
                });
    }

    @Test
    @DisplayName("Number of Planned Sprints is lower than minimum "
            + "bound.")
    void plannedSprintsCreationFailsLowerBound() {
        //Arrange
        int plannedSprintsInput = 0;

        //Act && Assert
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ProjectNumberOfPlannedSprints.create(plannedSprintsInput);
                });
    }

    @Test
    @DisplayName("Two instances of ProjectNumberOfPlannedSprints"
            + " are equals.")
    void instancesAreEquals() {
        //Arrange
        int plannedSprintsInput = 1;

        ProjectNumberOfPlannedSprints plannedSprintsOne =
                ProjectNumberOfPlannedSprints.create(plannedSprintsInput);
        ProjectNumberOfPlannedSprints plannedSprintsTwo =
                ProjectNumberOfPlannedSprints.create(plannedSprintsInput);

        //Act
        boolean actual = plannedSprintsOne.equals(plannedSprintsTwo);

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Two instances of ProjectNumberOfPlannedSprints" +
            " are not equals.")
    void instancesAreNotEquals() {

        //Arrange
        int plannedSprintsOne = 5;
        int plannedSprintsTwo = 999;

        ProjectNumberOfPlannedSprints sprintsOne =
                ProjectNumberOfPlannedSprints.create(plannedSprintsOne);
        ProjectNumberOfPlannedSprints sprintsTwo =
                ProjectNumberOfPlannedSprints.create(plannedSprintsTwo);

        //Act
        boolean actual = sprintsOne.equals(sprintsTwo);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Compared instance is null. Expected false.")
    void instancesIsNull() {

        //Arrange
        int plannedSprintsInput = 5;
        ProjectNumberOfPlannedSprints plannedSprintsOne =
                ProjectNumberOfPlannedSprints.create(plannedSprintsInput);

        //Act
        boolean actual = plannedSprintsOne.equals(null);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Two instances of ProjectNumberOfPlannedSprints are the " +
            "same.")
    void instancesAreTheSame() {
        //Arrange
        int plannedSprintsInput = 999;

        ProjectNumberOfPlannedSprints plannedSprintsOne =
                ProjectNumberOfPlannedSprints.create(plannedSprintsInput);
        ProjectNumberOfPlannedSprints plannedSprintsTwo =
                plannedSprintsOne;

        //Act
        boolean actual = plannedSprintsOne.equals(plannedSprintsTwo);

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Compared object are not an instance of " +
            "ProjectNumberOfPlannedSprints class.")
    void areNotAnInstanceOfThisClass() {
        //Arrange
        int plannedSprintsInput = 5;

        ProjectNumberOfPlannedSprints plannedSprintsOne =
                ProjectNumberOfPlannedSprints.create(plannedSprintsInput);
        Object plannedSprintsTwo = new Object();

        //Act
        boolean actual = plannedSprintsOne.equals(plannedSprintsTwo);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Instances are equals. Expected same hashcode.")
    void sameHashcode() {
        //Arrange
        int plannedSprintsInput = 5;

        ProjectNumberOfPlannedSprints plannedSprintsOne =
                ProjectNumberOfPlannedSprints.create(plannedSprintsInput);
        ProjectNumberOfPlannedSprints plannedSprintsTwo =
                ProjectNumberOfPlannedSprints.create(plannedSprintsInput);

        //Act
        boolean actual = plannedSprintsOne.hashCode() ==
                plannedSprintsTwo.hashCode();

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Instances are not equals. Expected different hashcode.")
    void differentHashcode() {
        //Arrange
        int plannedSprintsOne = 5;
        int plannedSprintsTwo = 50;

        ProjectNumberOfPlannedSprints sprintsOne =
                ProjectNumberOfPlannedSprints.create(plannedSprintsOne);
        ProjectNumberOfPlannedSprints sprintsTwo =
                ProjectNumberOfPlannedSprints.create(plannedSprintsTwo);

        //Act
        boolean actual = sprintsOne.hashCode() == sprintsTwo.hashCode();

        //Assert
        assertFalse(actual);
    }

    @Test
    void getNumber() {
        //Arrange
        int plannedSprintsOne = 5;

        ProjectNumberOfPlannedSprints sprintsOne =
                ProjectNumberOfPlannedSprints.create(plannedSprintsOne);

        int result = sprintsOne.getNumber();

        //Act
        boolean actual = plannedSprintsOne == result;

        //Assert
        assertTrue(actual);
    }
}
