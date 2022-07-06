package switchfive.project.domain.shared.valueObjects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourceCostPerHourTest {

    @Test
    @DisplayName("Cost Per Hour is greater than maximum bound.")
    void costPerHourCreationFailsUpperBound() {
        //Arrange
        double costPerHourInput = 10000000;

        //Act && Assert
        assertThrows(IllegalArgumentException.class,
                () ->
                        ResourceCostPerHour.create(
                                costPerHourInput));
    }

    @Test
    @DisplayName("Cost per hour is lower than minimum bound.")
    void costPerHourCreationFailsLowerBound() {
        //Arrange
        double costPerHourInput = -1;

        //Act && Assert
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ResourceCostPerHour.create(costPerHourInput);
                });
    }


    @Test
    @DisplayName("Two instances of ResourceCostPerHour are equals.")
    void instancesAreEquals() {
        //Arrange
        double costPerHourInput = 1;
        ResourceCostPerHour costPerHourOne =
                ResourceCostPerHour.create(costPerHourInput);
        ResourceCostPerHour costPerHourTwo =
                ResourceCostPerHour.create(costPerHourInput);

        //Act
        boolean actual = costPerHourOne.equals(costPerHourTwo);

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Two instances of ResourceCostPerHour are not " +
            "equals.")
    void instancesAreNotEquals() {
        //Arrange
        double costPerHourInputOne = 5;
        double costPerHourInputTwo = 9999.99;
        ResourceCostPerHour costPerHourOne =
                ResourceCostPerHour.create(costPerHourInputOne);
        ResourceCostPerHour costPerHourTwo =
                ResourceCostPerHour.create(costPerHourInputTwo);

        //Act
        boolean actual = costPerHourOne.equals(costPerHourTwo);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Compared instance is null. Expected false.")
    void instancesIsNull() {
        //Arrange
        double costPerHourInputOne = 5;
        ResourceCostPerHour costPerHourOne =
                ResourceCostPerHour.create(costPerHourInputOne);

        //Act
        boolean actual = costPerHourOne.equals(null);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Two instances of ResourceCostPerHour are the " +
            "same.")
    void instancesAreTheSame() {
        //Arrange
        double costPerHourInput = 5;
        ResourceCostPerHour costPerHourOne =
                ResourceCostPerHour.create(costPerHourInput);
        ResourceCostPerHour costPerHourTwo =
                costPerHourOne;

        //Act
        boolean actual = costPerHourOne.equals(costPerHourTwo);

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Compared object are not an instance of " +
            "ResourceCostPerHour class.")
    void areNotAnInstanceOfThisClass() {
        //Arrange
        double costPerHourInput = 5;
        ResourceCostPerHour allocationOne =
                ResourceCostPerHour.create(costPerHourInput);
        Object costPerHourTwo = new Object();

        //Act
        boolean actual = allocationOne.equals(costPerHourTwo);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Instances are equals. Expected same hashcode.")
    void sameHashcode() {
        //Arrange
        double costPerHourInput = 5;
        ResourceCostPerHour costPerHourOne =
                ResourceCostPerHour.create(costPerHourInput);
        ResourceCostPerHour costPerHourTwo =
                ResourceCostPerHour.create(costPerHourInput);

        //Act
        boolean actual = costPerHourOne.hashCode() == costPerHourTwo.hashCode();

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Instances are not equals. Expected different hashcode.")
    void differentHashcode() {
        //Arrange
        double costPerHourInputOne = 5;
        double costPerHourInputTwo = 50;
        ResourceCostPerHour costPerHourOne =
                ResourceCostPerHour.create(costPerHourInputOne);
        ResourceCostPerHour costPerHourTwo =
                ResourceCostPerHour.create(costPerHourInputTwo);

        //Act
        boolean actual = costPerHourOne.hashCode() == costPerHourTwo.hashCode();

        //Assert
        assertFalse(actual);
    }

    @Test
    void getCostPerHour() {
        //Arrange
        double costPerHourInputOne = 5;

        ResourceCostPerHour costPerHourOne =
                ResourceCostPerHour.create(costPerHourInputOne);

        double result = costPerHourOne.getCostPerHour();

        //Act
        boolean actual = costPerHourInputOne == result;

        //Assert
        assertTrue(actual);
    }
}
