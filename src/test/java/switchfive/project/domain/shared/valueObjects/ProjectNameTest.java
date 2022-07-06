package switchfive.project.domain.shared.valueObjects;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectNameTest {

    @Test
    @DisplayName(
            "ProjectName creation fails because input length is lower" +
                    "than 5.")
    void projectNameCreationFailsLowerLimit() {
        //Arrange
        String invalidInput = RandomStringUtils.random(
                4, true, false);

        //Act && Assert
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ProjectName.create(
                            invalidInput);
                });
    }

    @Test
    @DisplayName("ProjectName creation fails because input " +
            "length is greater than 50.")
    void projectDescriptionCreationFailsUpper() {
        //Arrange
        String invalidInput = RandomStringUtils.random(
                51, true, false);

        //Act && Assert
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ProjectName.create(
                            invalidInput);
                });
    }

    @Test
    @DisplayName("ThisName is equals to otherName.")
    void thisNameIsEqualsToOther() {
        //Arrange
        String input = "Benfi";
        ProjectName thisName =
                ProjectName.create(input);
        ProjectName otherName =
                ProjectName.create(input);

        //Act
        boolean isTheSameValue = thisName.equals(otherName);

        //Assert
        assertTrue(isTheSameValue);
    }

    @Test
    @DisplayName(
            "otherName is not an instance of ProjectName class.")
    void otherNameIsNotAnInstance() {
        //Arrange
        String input = "Benfica Benfica Benfica Benfica Benfica Benfica go";
        ProjectName thisName =
                ProjectName.create(input);
        Object otherName = new Object();

        //Act
        boolean isTheSameValue = thisName.equals(otherName);

        //Assert
        assertFalse(isTheSameValue);
    }

    @Test
    @DisplayName("Two instances of ProjectName  are not " +
            "equals.")
    void instancesAreNotEquals() {
        //Arrange
        String input = "Benfica";
        String inputValue = "Porto";

        ProjectName inputOne = ProjectName.create(input);
        ProjectName inputTwo = ProjectName.create(inputValue);

        //Act
        boolean actual = inputOne.equals(inputTwo);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Compared instance is null. Expected false.")
    void instancesIsNull() {

        //Arrange
        String input = "benfas";
        ProjectName inputOne =
                ProjectName.create(input);

        //Act
        boolean actual = inputOne.equals(null);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("ThisName is the same as otherName.")
    void thisNameTheSameAsOther() {
        //Arrange
        String input = "Benfica tricampeao";
        ProjectName thisName =
                ProjectName.create(input);
        ProjectName otherName = thisName;

        //Act
        boolean isTheSameValue = thisName.equals(
                otherName);

        //Assert
        assertTrue(isTheSameValue);
    }


    @Test
    @DisplayName("ThisName hashCode is the same as " +
            "otherName hashCode.")
    void hashCodeEquals() {
        //Arrange
        String input = "Benfica tetra";
        int thisNameHash =
                ProjectName.create(input).hashCode();
        int otherNameHash =
                ProjectName.create(input).hashCode();

        //Act
        boolean isTheSameHash = thisNameHash == otherNameHash;

        //Assert
        assertTrue(isTheSameHash);
    }

    @Test
    @DisplayName("ThisName hashCode is different as " +
            "otherName hashCode.")
    void differentHashCode() {
        //Arrange
        String input = "Benfica tetra";
        String inputTwo = "Benfica penta";
        int thisNameHash =
                ProjectName.create(input).hashCode();
        int otherNameHash =
                ProjectName.create(inputTwo).hashCode();

        //Act
        boolean isTheSameHash = thisNameHash == otherNameHash;

        //Assert
        assertFalse(isTheSameHash);
    }

    @Test
    void getName() {
        //Arrange
        String input = "Benfica tetra";

        ProjectName name = ProjectName.create(input);

        String result = name.getName();

        //Act
        boolean isTheSameHash = input == result;

        //Assert
        assertTrue(isTheSameHash);

    }
}
