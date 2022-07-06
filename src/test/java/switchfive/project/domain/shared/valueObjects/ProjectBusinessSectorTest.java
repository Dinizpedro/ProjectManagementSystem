package switchfive.project.domain.shared.valueObjects;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectBusinessSectorTest {
    @Test
    @DisplayName(
            "BusinessSector creation fails because input length is lower" +
                    "than 5.")
    void businessSectorCreationFailsLowerLimit() {
        //Arrange
        String invalidInput = RandomStringUtils.random(
                4, true, false);

        //Act && Assert
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ProjectBusinessSector.create(
                            invalidInput);
                });
    }

    @Test
    @DisplayName("BusinessSector creation fails because input " +
            "length is greater than 50.")
    void projectDescriptionCreationFailsUpper() {
        //Arrange
        String invalidInput = RandomStringUtils.random(
                51, true, false);

        //Act && Assert
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ProjectBusinessSector.create(
                            invalidInput);
                });
    }

    @Test
    @DisplayName("Two instances of ResourceCostPerHour are equals..")
    void instancesAreEquals() {
        //Arrange
        String input = "sport";
        ProjectBusinessSector thisDescription =
                ProjectBusinessSector.create(input);
        ProjectBusinessSector otherDescription =
                ProjectBusinessSector.create(input);

        //Act
        boolean isTheSameValue = thisDescription.equals(otherDescription);

        //Assert
        assertTrue(isTheSameValue);
    }

    @Test
    @DisplayName("Two instances of ProjectBusinessSector  are not " +
            "equals.")
    void instancesAreNotEquals() {
        //Arrange
        String input = "sport";
        String inputTwo = "sportif";

        ProjectBusinessSector businessOne =
                ProjectBusinessSector.create(input);
        ProjectBusinessSector businessTwo =
                ProjectBusinessSector.create(inputTwo);

        //Act
        boolean actual = businessOne.equals(businessTwo);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Compared instance is null. Expected false.")
    void instancesIsNull() {
        //Arrange
        String input = "sport";
        ProjectBusinessSector thisDescription =
                ProjectBusinessSector.create(input);

        //Act
        boolean actual = thisDescription.equals(null);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName(
            "Compared object are not an instance of ProjectBusinessSector "
                    + "class.")
    void otherDescriptionIsNotAnInstance() {
        //Arrange
        String input = "prof prof prof prof prof prof prof prof prof prof ";
        ProjectBusinessSector thisDescription =
                ProjectBusinessSector.create(input);
        Object otherDescription = new Object();

        //Act
        boolean isTheSameValue = thisDescription.equals(otherDescription);

        //Assert
        assertFalse(isTheSameValue);
    }

    @Test
    @DisplayName("ThisDescription is the same as otherDescription.")
    void thisDescriptionTheSameAsOther() {
        //Arrange
        String input = "logistics";
        ProjectBusinessSector thisDescription =
                ProjectBusinessSector.create(input);
        ProjectBusinessSector otherDescription = thisDescription;

        //Act
        boolean isTheSameValue = thisDescription.equals(
                otherDescription);

        //Assert
        assertTrue(isTheSameValue);
    }

    @Test
    @DisplayName("ThisDescription hashCode is the same as " +
            "otherDescription hashCode.")
    void hashCodeEquals() {
        //Arrange
        String input = "logistics";
        int thisDescriptionHash =
                ProjectBusinessSector.create(input).hashCode();
        int otherDescriptionHash =
                ProjectBusinessSector.create(input).hashCode();

        //Act
        boolean isTheSameHash = thisDescriptionHash == otherDescriptionHash;

        //Assert
        assertTrue(isTheSameHash);
    }

    @Test
    @DisplayName("ThisDescription hashCode is different as " +
            "otherDescription hashCode.")
    void differentHashCode() {
        //Arrange
        String input = "logistics";
        String inputTwo = "marketing";
        int thisDescriptionHash =
                ProjectBusinessSector.create(input).hashCode();
        int otherDescriptionHash =
                ProjectBusinessSector.create(inputTwo).hashCode();

        //Act
        boolean isTheSameHash = thisDescriptionHash == otherDescriptionHash;

        //Assert
        assertFalse(isTheSameHash);
    }

    @Test
    void getBusinessSector() {
        //Arrange
        String input = "logistics";

        ProjectBusinessSector newPBS = ProjectBusinessSector.create(input);

        String result = newPBS.getBusinessSector();

        //Act
        boolean actual = input == result;

        //Assert
        assertTrue(actual);
    }
}
