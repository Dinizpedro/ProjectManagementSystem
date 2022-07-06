package switchfive.project.domain.shared.valueObjects;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectCodeTest {

    @Test
    @DisplayName(
            "ProjectCode creation fails because input length is lower"
                    + "than 5.")
    void projectDescriptionCreationFailsLowerLimit() {
        //Arrange
        String invalidInput = RandomStringUtils.random(
                4, true, true);

        //Act && Assert
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ProjectCode.create(
                            invalidInput);
                });
    }

    @Test
    @DisplayName("ProjectDescription creation fails because input "
            + "length is greater than 5.")
    void projectDescriptionCreationFailsUpper() {
        //Arrange
        String invalidInput = RandomStringUtils.random(
                6, true, true);

        //Act && Assert
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ProjectCode.create(
                            invalidInput);
                });
    }

    @Test
    @DisplayName("Two instances of ProjectCode are equals.")
    void instancesAreEquals() {
        //Arrange
        String input = "XPTO1";
        ProjectCode thisCode =
                ProjectCode.create(input);
        ProjectCode otherCode =
                ProjectCode.create(input);

        //Act
        boolean actual = thisCode.equals(otherCode);

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Two instances of ProjectCode are not "
            + "equals.")
    void instancesAreNotEquals() {
        //Arrange
        String input = "XPTO1";
        String inputValue = "XPTO2";
        ProjectCode thisCode =
                ProjectCode.create(input);
        ProjectCode otherCode =
                ProjectCode.create(inputValue);

        //Act
        boolean actual = thisCode.equals(otherCode);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Compared instance is null. Expected false.")
    void instancesIsNull() {
        //Arrange
        String input = "XPTO1";
        ProjectCode thisCode =
                ProjectCode.create(input);

        //Act
        boolean actual = thisCode.equals(null);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Two instances of ProjectCode are the "
            + "same.")
    void instancesAreTheSame() {
        //Arrange
        String input = "XPTO1";
        ProjectCode thisCode =
                ProjectCode.create(input);
        ProjectCode otherCode =
                thisCode;

        //Act
        boolean actual = thisCode.equals(otherCode);

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Compared object are not an instance of "
            + "ProjectCode class.")
    void areNotAnInstanceOfThisClass() {
        //Arrange
        String input = "XPTO1";
        ProjectCode thisCode =
                ProjectCode.create(input);
        Object otherCode = new Object();

        //Act
        boolean actual = thisCode.equals(otherCode);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("ThisCode hashCode is the same as "
            + "otherCode hashCode.")
    void hashCodeEquals() {
        //Arrange
        String input = "XPTO1";
        int thisCodeHash =
                ProjectCode.create(input).hashCode();
        int otherCodeHash =
                ProjectCode.create(input).hashCode();

        //Act
        boolean isTheSameHash = thisCodeHash
                == otherCodeHash;

        //Assert
        assertTrue(isTheSameHash);
    }

    @Test
    @DisplayName("ThisCode hashCode is different as "
            + "otherCode hashCode.")
    void hashCodeAreDifferent() {
        //Arrange
        String input = "XPTO1";
        String inputTwo = "XPTO2";
        int thisCodeHash =
                ProjectCode.create(input).hashCode();
        int otherCodeHash =
                ProjectCode.create(inputTwo).hashCode();

        //Act
        boolean isTheSameHash = thisCodeHash == otherCodeHash;

        //Assert
        assertFalse(isTheSameHash);
    }

    @Test
    @DisplayName("ThisCode getCode is the same as "
            + "otherCode getCode.")
    void getCodeEquals() {
        //Arrange
        String input = "XPTO1";
        String thisCode =
                ProjectCode.create(input).getCode();
        String otherCode =
                ProjectCode.create(input).getCode();

        //Act
        boolean isTheSame = thisCode
                == otherCode;

        //Assert
        assertTrue(isTheSame);
    }

    @Test
    @DisplayName("ThisCode getCode is different as "
            + "otherCode getCode.")
    void getCodeDifferente() {
        //Arrange
        String input = "XPTO1";
        String inputTwo = "XPTO2";
        String thisCode =
                ProjectCode.create(input).getCode();
        String otherCode =
                ProjectCode.create(inputTwo).getCode();

        //Act
        boolean isTheSame = thisCode
                == otherCode;

        //Assert
        assertFalse(isTheSame);
    }

}
