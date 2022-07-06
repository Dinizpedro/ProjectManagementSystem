package switchfive.project.domain.shared.valueObjects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourcePercentageOfAllocationTest {

    @Test
    @DisplayName("Percentage of allocation is greater than maximum bound.")
    void allocationCreationFailsUpperBound() {
        //Arrange
        double allocationInput = 101;

        //Act && Assert
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ResourcePercentageOfAllocation.create(allocationInput);
                });
    }

    @Test
    @DisplayName("Percentage of allocation is lower than minimum bound.")
    void allocationCreationFailsLowerBound() {
        //Arrange
        double allocationInput = -1;

        //Act && Assert
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ResourcePercentageOfAllocation.create(allocationInput);
                });
    }

    @Test
    @DisplayName("Two instances of ResourcePercentageOfAllocation are equals.")
    void instancesAreEquals() {
        //Arrange
        double allocationInput = 0;
        ResourcePercentageOfAllocation allocationOne =
                ResourcePercentageOfAllocation.create(allocationInput);
        ResourcePercentageOfAllocation allocationTwo =
                ResourcePercentageOfAllocation.create(allocationInput);

        //Act
        boolean actual = allocationOne.equals(allocationTwo);

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Two instances of ResourcePercentageOfAllocation are not " +
            "equals.")
    void instancesAreNotEquals() {
        //Arrange
        double allocationInputOne = 5;
        double allocationInputTwo = 100;
        ResourcePercentageOfAllocation allocationOne =
                ResourcePercentageOfAllocation.create(allocationInputOne);
        ResourcePercentageOfAllocation allocationTwo =
                ResourcePercentageOfAllocation.create(allocationInputTwo);

        //Act
        boolean actual = allocationOne.equals(allocationTwo);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Compared instance is null. Expected false.")
    void instancesIsNull() {
        //Arrange
        double allocationInputOne = 5;
        ResourcePercentageOfAllocation allocationOne =
                ResourcePercentageOfAllocation.create(allocationInputOne);

        //Act
        boolean actual = allocationOne.equals(null);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Two instances of ResourcePercentageOfAllocation are the " +
            "same.")
    void instancesAreTheSame() {
        //Arrange
        double allocationInput = 5;
        ResourcePercentageOfAllocation allocationOne =
                ResourcePercentageOfAllocation.create(allocationInput);
        ResourcePercentageOfAllocation allocationTwo =
                allocationOne;

        //Act
        boolean actual = allocationOne.equals(allocationTwo);

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Compared object are not an instance of " +
            "ResourcePercentageOfAllocation class.")
    void areNotAnInstanceOfThisClass() {
        //Arrange
        double allocationInput = 5;
        ResourcePercentageOfAllocation allocationOne =
                ResourcePercentageOfAllocation.create(allocationInput);
        Object allocationTwo = new Object();

        //Act
        boolean actual = allocationOne.equals(allocationTwo);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Instances are equals. Expected same hashcode.")
    void sameHashcode() {
        //Arrange
        double allocationInput = 5;
        ResourcePercentageOfAllocation allocationOne =
                ResourcePercentageOfAllocation.create(allocationInput);
        ResourcePercentageOfAllocation allocationTwo =
                ResourcePercentageOfAllocation.create(allocationInput);

        //Act
        boolean actual = allocationOne.hashCode() == allocationTwo.hashCode();

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Instances are not equals. Expected different hashcode.")
    void differentHashcode() {
        //Arrange
        double allocationInputOne = 5;
        double allocationInputTwo = 50;
        ResourcePercentageOfAllocation allocationOne =
                ResourcePercentageOfAllocation.create(allocationInputOne);
        ResourcePercentageOfAllocation allocationTwo =
                ResourcePercentageOfAllocation.create(allocationInputTwo);

        //Act
        boolean actual = allocationOne.hashCode() == allocationTwo.hashCode();

        //Assert
        assertFalse(actual);
    }

    @Test
    void getAllocation() {
        //Arrange
        double allocationInputOne = 5;

        ResourcePercentageOfAllocation allocationOne =
                ResourcePercentageOfAllocation.create(allocationInputOne);

        double result = allocationOne.getAllocation();

        //Act
        boolean actual = allocationInputOne == result;

        //Assert
        assertTrue(actual);
    }
}
