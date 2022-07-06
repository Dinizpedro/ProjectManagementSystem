package switchfive.project.model.sprint;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchfive.project.domain.shared.valueObjects.SprintDescription;

import static org.junit.jupiter.api.Assertions.*;

class SprintDescriptionTest {

    @Test
    @DisplayName("SprintDescription creation fails because input " +
            "length is lower than 5.")
    void sprintCreationFailsLowerLimit() {
        //Arrange
        String invalidInput = RandomStringUtils.random(4, true, false);

        //Act && Assert
        assertThrows(IllegalArgumentException.class, () -> {
            SprintDescription.create(invalidInput);
        });
    }

    @Test
    @DisplayName("SprintDescription creation fails because input " +
            "length is greater than 50.")
    void sprintCreationFailsUpper() {
        //Arrange
        String invalidInput = RandomStringUtils.random(51, true, false);

        //Act && Assert
        assertThrows(IllegalArgumentException.class, () -> {
            SprintDescription.create(invalidInput);
        });
    }

    @Test
    @DisplayName("ThisDescription is equals to otherDescription.")
    void thisDescriptionIsEqualsToOther() {
        //Arrange
        String input = "Improve skills.";
        SprintDescription thisDescription = SprintDescription.create(input);
        SprintDescription otherDescription = SprintDescription.create(input);

        //Act
        boolean isTheSameValue = thisDescription.equals(otherDescription);

        //Assert
        assertTrue(isTheSameValue);
    }

    @Test
    @DisplayName("ThisDescription is not equals to otherDescription.")
    void thisDescriptionIsNotEqualsToOther() {
        //Arrange
        String input = RandomStringUtils.random(5, true, true);
        String otherInput = RandomStringUtils.random(50, true, true);
        SprintDescription thisDescription = SprintDescription.create(input);
        SprintDescription otherDescription = SprintDescription.
                create(otherInput);

        //Act
        boolean isTheSameValue = thisDescription.equals(otherDescription);

        //Assert
        assertFalse(isTheSameValue);
    }

    @Test
    @DisplayName("OtherDescription is not an instance of " +
            "SprintDescription class.")
    void otherDescriptionIsNotAnInstance() {
        //Arrange
        String input = "improve skills";
        SprintDescription thisDescription = SprintDescription.create(input);
        Object otherDescription = new Object();

        //Act
        boolean isTheSameValue = thisDescription.equals(otherDescription);

        //Assert
        assertFalse(isTheSameValue);
    }

    @Test
    @DisplayName("OtherDescription is null.")
    void otherDescriptionIsNull() {
        //Arrange
        String input = "improve skills";
        SprintDescription thisDescription = SprintDescription.create(input);

        //Act
        boolean isTheSameValue = thisDescription.equals(null);

        //Assert
        assertFalse(isTheSameValue);
    }

    @Test
    @DisplayName("ThisDescription is the same as otherDescription.")
    void thisDescriptionTheSameAsOther() {
        //Arrange
        String input = "improve skills";
        SprintDescription thisDescription = SprintDescription.create(input);
        SprintDescription otherDescription = thisDescription;

        //Act
        boolean isTheSameValue = thisDescription.equals(otherDescription);

        //Assert
        assertTrue(isTheSameValue);
    }

    @Test
    @DisplayName("ThisDescription hashCode is the same as " +
            "otherDescription hashCode.")
    void hashCodeEquals() {
        //Arrange
        String input = "improve skills";
        int thisDescriptionHash = SprintDescription.create(input).hashCode();
        int otherDescriptionHash = SprintDescription.create(input).hashCode();

        //Act
        boolean isTheSameHash = thisDescriptionHash == otherDescriptionHash;

        //Assert
        assertTrue(isTheSameHash);
    }

    @Test
    @DisplayName("ThisDescription hashCode is not the same as " +
            "otherDescription hashCode.")
    void hashCodeNotEquals() {
        //Arrange
        String input = "improve skills";
        String otherInput = "skills improvement";

        int thisDescriptionHash = SprintDescription.create(input).hashCode();
        int otherDescriptionHash =
                SprintDescription.create(otherInput).hashCode();

        //Act
        boolean isTheSameHash = thisDescriptionHash == otherDescriptionHash;

        //Assert
        assertFalse(isTheSameHash);
    }

}
