package switchfive.project.domain.shared.valueObjects;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfileDescriptionTest {

    @Test
    @DisplayName(
            "ProfileDescription creation fails because input length is zero")
    void profileDescriptionCreationFailsLowerLimit() {
        //Arrange
        String invalidInput = "V";

        //Act && Assert
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ProfileDescription.createProfileDescription(
                            invalidInput);
                });
    }

    @Test
    @DisplayName("ProfileDescription creation fails because input " +
            "length is greater than 50.")
    void profileDescriptionCreationFailsUpper() {
        //Arrange
        String invalidInput = RandomStringUtils.random(
                51, true, false);

        //Act && Assert
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ProfileDescription.createProfileDescription(
                            invalidInput);
                });
    }

    @Test
    @DisplayName("ProfileDescription creation fails description is only blank spaces")
    void profileDescriptionCreationFailsOnlyBlankSpaces() {
        //Arrange
        String invalidInput = "   ";

        //Act && Assert
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ProfileDescription.createProfileDescription(
                            invalidInput);
                });
    }

    @Test
    @DisplayName("Two instances of ProfileDescription are equals.")
    void instancesAreEquals() {
        //Arrange
        String input = "User";
        ProfileDescription thisDescription =
                ProfileDescription.createProfileDescription(input);
        ProfileDescription otherDescription =
                ProfileDescription.createProfileDescription(input);

        //Act
        boolean actual = thisDescription.equals(otherDescription);

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Two instances of ProfileDescription are not " +
            "equals.")
    void instancesAreNotEquals() {
        //Arrange
        String input = "Director";
        String inputValue = "Administrator";
        ProfileDescription thisDescription =
                ProfileDescription.createProfileDescription(input);
        ProfileDescription otherDescription =
                ProfileDescription.createProfileDescription(inputValue);

        //Act
        boolean actual = thisDescription.equals(otherDescription);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Compared instance is null. Expected false.")
    void instancesIsNull() {
        //Arrange
        String input = "Administrator";
        ProfileDescription thisDescription =
                ProfileDescription.createProfileDescription(input);

        //Act
        boolean actual = thisDescription.equals(null);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Two instances of ProfileDescription are the " +
            "same.")
    void instancesAreTheSame() {
        //Arrange
        String input = "Administrator";
        ProfileDescription thisDescription =
                ProfileDescription.createProfileDescription(input);
        ProfileDescription otherDescription =
                thisDescription;

        //Act
        boolean actual = thisDescription.equals(otherDescription);

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Compared object are not an instance of " +
            "TypologyDescription class.")
    void areNotAnInstanceOfThisClass() {
        //Arrange
        String input = "Administrator";
        ProfileDescription thisDescription =
                ProfileDescription.createProfileDescription(input);
        Object otherDescription = new Object();

        //Act
        boolean actual = thisDescription.equals(otherDescription);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("ThisDescription hashCode is the same as " +
            "otherDescription hashCode.")
    void hashCodeEquals() {
        //Arrange
        String input = "Administrator";
        int thisDescriptionHash =
                ProfileDescription.createProfileDescription(input).hashCode();
        int otherDescriptionHash =
                ProfileDescription.createProfileDescription(input).hashCode();

        //Act
        boolean isTheSameHash = (thisDescriptionHash == otherDescriptionHash);

        //Assert
        assertTrue(isTheSameHash);
    }

    @Test
    @DisplayName("ThisDescription hashCode is different as " +
            "otherDescription hashCode.")
    void hashCodeAreDifferent() {
        //Arrange
        String input = "Administrator";
        String inputTwo = "Director";
        int thisDescriptionHash =
                ProfileDescription.createProfileDescription(input).hashCode();
        int otherDescriptionHash =
                ProfileDescription.createProfileDescription(inputTwo).hashCode();

        //Act
        boolean isTheSameHash = (thisDescriptionHash == otherDescriptionHash);

        //Assert
        assertFalse(isTheSameHash);
    }


    @Test
    void getDescriptionTest() {
        //Arrange
        String input = "Visitor";
        ProfileDescription profileDescription =
                ProfileDescription.createProfileDescription(input);

        //Act
        String result = profileDescription.getDescription();

        //Assert
        assertEquals(input, result);
    }

    @Test
    void compareProfileDesignationTrue() {
        //Arrange
        String input = "Visitor";
        ProfileDescription profileDescription =
                ProfileDescription.createProfileDescription(input);

        //Act
        boolean result = profileDescription.compareProfileDesignation("ViSiTor");

        //Assert
        assertTrue(result);
    }

    @Test
    void compareProfileDesignationFalse() {
        //Arrange
        String input = "Visitor";
        ProfileDescription profileDescription =
                ProfileDescription.createProfileDescription(input);

        //Act
        boolean result = profileDescription.compareProfileDesignation("director");

        //Assert
        assertFalse(result);
    }
}
