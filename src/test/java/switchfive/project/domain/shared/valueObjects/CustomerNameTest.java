package switchfive.project.domain.shared.valueObjects;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerNameTest {

    @Test
    @DisplayName("CustomerName creation fails because input length is lower" +
            "than 5.")
    void projectNameCreationFailsLowerLimit() {
        //Arrange
        String invalidInput = RandomStringUtils.random(
                1, true, false);

        //Act && Assert
        assertThrows(IllegalArgumentException.class,
                () -> {
                    CustomerName.create(
                            invalidInput);
                });
    }

    @Test
    @DisplayName("CustomerName creation fails because input length is filled " +
            "with blank spaces.")
    void projectNameCreationFailsBlanks() {
        //Arrange
        String invalidInput = "          ";

        //Act && Assert
        assertThrows(IllegalArgumentException.class,
                () -> {
                    CustomerName.create(invalidInput);
                });
    }

    @Test
    @DisplayName("CustomerName creation fails because input " +
            "length is greater than 50.")
    void projectDescriptionCreationFailsUpper() {
        //Arrange
        String invalidInput = RandomStringUtils.random(
                51, true, false);

        //Act && Assert
        assertThrows(IllegalArgumentException.class,
                () -> {
                    CustomerName.create(
                            invalidInput);
                });
    }

    @Test
    @DisplayName("ThisName is equals to otherName.")
    void thisNameIsEqualsToOther() {
        //Arrange
        String input = "Portugal";
        CustomerName thisName =
                CustomerName.create(input);
        CustomerName otherName =
                CustomerName.create(input);

        //Act
        boolean isTheSameValue = thisName.equals(otherName);

        //Assert
        assertTrue(isTheSameValue);
    }

    @Test
    @DisplayName("otherName is not an instance of ProjectName class.")
    void otherNameIsNotAnInstance() {
        //Arrange
        String input = "Portugal";
        CustomerName thisName = CustomerName.create(input);
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
        String input = "Portugal";
        String inputValue = "Spain";

        CustomerName inputOne = CustomerName.create(input);
        CustomerName inputTwo = CustomerName.create(inputValue);

        //Act
        boolean actual = inputOne.equals(inputTwo);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Compared instance is null. Expected false.")
    void instancesIsNull() {

        //Arrange
        String input = "Portugal";
        CustomerName inputOne =
                CustomerName.create(input);

        //Act
        boolean actual = inputOne.equals(null);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("ThisName is the same as otherName.")
    void thisNameTheSameAsOther() {
        //Arrange
        String input = "Portugal";
        CustomerName thisName =
                CustomerName.create(input);
        CustomerName otherName = thisName;

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
        String input = "Portugal";
        int thisNameHash =
                CustomerName.create(input).hashCode();
        int otherNameHash =
                CustomerName.create(input).hashCode();

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
        String input = "Portugal";
        String inputTwo = "Spain";
        int thisNameHash =
                CustomerName.create(input).hashCode();
        int otherNameHash =
                CustomerName.create(inputTwo).hashCode();

        //Act
        boolean isTheSameHash = thisNameHash == otherNameHash;

        //Assert
        assertFalse(isTheSameHash);
    }

    @Test
    void getName() {
        //Arrange
        String input = "Portugal";

        CustomerName name = CustomerName.create(input);

        String result = name.getName();

        //Act
        boolean isTheSameHash = input.equals(result);

        //Assert
        assertTrue(isTheSameHash);

    }

}
