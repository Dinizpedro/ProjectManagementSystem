package switchfive.project.domain.shared.valueObjects;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TypologyDescriptionTest {

/*    @Test
    @DisplayName(
            "TypologyDescription creation fails because input length is lower" +
                    "than 5.")
    void typologyDescriptionCreationFailsLowerLimit() {
        //Arrange
        String invalidInput = RandomStringUtils.random(
                4, true, false);

        //Act && Assert
        assertThrows(IllegalArgumentException.class,
                () -> {
                    TypologyDescription.create(
                            invalidInput);
                });
    }*/

    @Test
    @DisplayName("TypologyDescription creation fails because input " +
            "length is greater than 50.")
    void typologyDescriptionCreationFailsUpper() {
        //Arrange
        String invalidInput = RandomStringUtils.random(
                51, true, false);

        //Act && Assert
        assertThrows(IllegalArgumentException.class,
                () -> {
                    TypologyDescription.create(
                            invalidInput);
                });
    }

    @Test
    @DisplayName("Two instances of TypologyDescription are equals.")
    void instancesAreEquals() {
        //Arrange
        String input = "Benfi";
        TypologyDescription thisDescription =
                TypologyDescription.create(input);
        TypologyDescription otherDescription =
                TypologyDescription.create(input);

        //Act
        boolean actual = thisDescription.equals(otherDescription);

        //Assert
        assertTrue(actual);
    }

/*    @Test
    @DisplayName("Two instances of TypologyDescription are not " +
            "equals.")
    void instancesAreNotEquals() {
        //Arrange
        String input = "Benfica Benfica Benfica Benfica Benfica Benfica go";
        String inputValue = "Porto";
        TypologyDescription thisDescription =
                TypologyDescription.create(input);
        TypologyDescription otherDescription =
                TypologyDescription.create(inputValue);

        //Act
        boolean actual = thisDescription.equals(otherDescription);

        //Assert
        assertFalse(actual);
    }*/

    @Test
    @DisplayName("Compared instance is null. Expected false.")
    void instancesIsNull() {
        //Arrange
        String input = "Benfica";
        TypologyDescription thisDescription =
                TypologyDescription.create(input);

        //Act
        boolean actual = thisDescription.equals(null);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Two instances of TypologyDescription are the " +
            "same.")
    void instancesAreTheSame() {
        //Arrange
        String input = "Benfica";
        TypologyDescription thisDescription =
                TypologyDescription.create(input);
        TypologyDescription otherDescription =
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
        String input = "Benfica";
        TypologyDescription thisDescription =
                TypologyDescription.create(input);
        Object otherDescription = new Object();

        //Act
        boolean actual = thisDescription.equals(otherDescription);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("ThisDescription is the same as otherDescription.")
    void thisDescriptionTheSameAsOther() {
        //Arrange
        String input = "cost per hour";
        TypologyDescription thisDescription =
                TypologyDescription.create(input);
        TypologyDescription otherDescription = thisDescription;

        //Act
        boolean isTheSameValue = thisDescription.sameValueAs(otherDescription);

        //Assert
        assertTrue(isTheSameValue);
    }

    @Test
    @DisplayName("ThisDescription hashCode is the same as " +
            "otherDescription hashCode.")
    void hashCodeEquals() {
        //Arrange
        String input = "cost per hour";
        int thisDescriptionHash =
                TypologyDescription.create(input).hashCode();
        int otherDescriptionHash =
                TypologyDescription.create(input).hashCode();

        //Act
        boolean isTheSameHash = thisDescriptionHash ==
                otherDescriptionHash;

        //Assert
        assertTrue(isTheSameHash);
    }

    @Test
    @DisplayName("ThisDescription hashCode is different as " +
            "otherDescription hashCode.")
    void hashCodeAreDifferent() {
        //Arrange
        String input = "cost per hour";
        String inputTwo = "costs";
        int thisDescriptionHash =
                TypologyDescription.create(input).hashCode();
        int otherDescriptionHash =
                TypologyDescription.create(inputTwo).hashCode();

        //Act
        boolean isTheSameHash = thisDescriptionHash == otherDescriptionHash;

        //Assert
        assertFalse(isTheSameHash);
    }
}
