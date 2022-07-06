package switchfive.project.domain.shared.valueObjects;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectDescriptionTest {

    @Test
    @DisplayName(
            "ProjectDescription creation fails because input length is lower"
                    + "than 5.")
    void projectDescriptionCreationFailsLowerLimit() {
        //Arrange
        String invalidInput = RandomStringUtils.random(
                4, true, false);

        //Act && Assert
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ProjectDescription.create(
                            invalidInput);
                });
    }

    @Test
    @DisplayName("ProjectDescription creation fails because input "
            + "length is greater than 50.")
    void projectDescriptionCreationFailsUpper() {
        //Arrange
        String invalidInput = RandomStringUtils.random(
                51, true, false);

        //Act && Assert
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ProjectDescription.create(
                            invalidInput);
                });
    }

    @Test
    @DisplayName("Two instances of ProjectDescription are equals.")
    void instancesAreEquals() {
        //Arrange
        String input = "Benfi";
        ProjectDescription thisDescription =
                ProjectDescription.create(input);
        ProjectDescription otherDescription =
                ProjectDescription.create(input);

        //Act
        boolean actual = thisDescription.equals(otherDescription);

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Two instances of ProjectDescription are not "
            + "equals.")
    void instancesAreNotEquals() {
        //Arrange
        String input = "Benfica Benfica Benfica Benfica Benfica Benfica go";
        String inputValue = "Porto";
        ProjectDescription thisDescription =
                ProjectDescription.create(input);
        ProjectDescription otherDescription =
                ProjectDescription.create(inputValue);

        //Act
        boolean actual = thisDescription.equals(otherDescription);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Compared instance is null. Expected false.")
    void instancesIsNull() {
        //Arrange
        String input = "Benfica";
        ProjectDescription thisDescription =
                ProjectDescription.create(input);

        //Act
        boolean actual = thisDescription.equals(null);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Two instances of ProjectDescription are the "
            + "same.")
    void instancesAreTheSame() {
        //Arrange
        String input = "Benfica";
        ProjectDescription thisDescription =
                ProjectDescription.create(input);
        ProjectDescription otherDescription =
                thisDescription;

        //Act
        boolean actual = thisDescription.equals(otherDescription);

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Compared object are not an instance of "
            + "ProjectDescription class.")
    void areNotAnInstanceOfThisClass() {
        //Arrange
        String input = "Benfica";
        ProjectDescription thisDescription =
                ProjectDescription.create(input);
        Object costPerHourTwo = new Object();

        //Act
        boolean actual = thisDescription.equals(costPerHourTwo);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("ThisDescription is the same as otherDescription.")
    void thisDescriptionTheSameAsOther() {
        //Arrange
        String input = "cost per hour";
        ProjectDescription thisDescription =
                ProjectDescription.create(input);
        ProjectDescription otherDescription = thisDescription;

        //Act
        boolean isTheSameValue =
                thisDescription.sameValueAs(otherDescription);

        //Assert
        assertTrue(isTheSameValue);
    }

    @Test
    @DisplayName("ThisDescription hashCode is the same as "
            + "otherDescription hashCode.")
    void hashCodeEquals() {
        //Arrange
        String input = "cost per hour";
        int thisDescriptionHash =
                ProjectDescription.create(input).hashCode();
        int otherDescriptionHash =
                ProjectDescription.create(input).hashCode();

        //Act
        boolean isTheSameHash = thisDescriptionHash
                == otherDescriptionHash;

        //Assert
        assertTrue(isTheSameHash);
    }

    @Test
    @DisplayName("ThisDescription hashCode is different as "
            + "otherDescription hashCode.")
    void hashCodeAreDifferent() {
        //Arrange
        String input = "cost per hour";
        String inputTwo = "costs";
        int thisDescriptionHash =
                ProjectDescription.create(input).hashCode();
        int otherDescriptionHash =
                ProjectDescription.create(inputTwo).hashCode();

        //Act
        boolean isTheSameHash = thisDescriptionHash == otherDescriptionHash;

        //Assert
        assertFalse(isTheSameHash);
    }

    @Test
    void getDescription() {
        //Arrange
        String input = "cost per hour";

        ProjectDescription description =
                ProjectDescription.create(input);

        String descriptionStrg = description.getDescription();

        //Act
        boolean actual = input == descriptionStrg;

        //Assert
        assertTrue(actual);
    }
}



