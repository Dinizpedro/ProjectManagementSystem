package switchfive.project.domain.shared.valueObjects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectBudgetTest {

    @Test
    @DisplayName("Project Budget is greater than maximum bound.")
    void projectBudgetCreationFailsUpperBound() {
        //Arrange
        double budgetInput = 1000000000;

        //Act && Assert
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ProjectBudget.create(budgetInput);
                });
    }

    @Test
    @DisplayName("Project Budget is lower than minimum bound.")
    void projectBudgetCreationFailsLowerBound() {
        //Arrange
        double budgetInput = -1;

        //Act && Assert
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ProjectBudget.create(budgetInput);
                });
    }

    @Test
    @DisplayName("Two instances of ProjectBudget are equals.")
    void instancesAreEquals() {
        //Arrange
        double budgetInput = 1;

        ProjectBudget budgetOne =
                ProjectBudget.create(budgetInput);
        ProjectBudget budgetTwo =
                ProjectBudget.create(budgetInput);

        //Act
        boolean actual = budgetOne.equals(budgetTwo);

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Two instances of ProjectBudget are not equals.")
    void instancesAreNotEquals() {

        //Arrange
        double budgetInputOne = 5;
        double budgetInputTwo = 100;

        ProjectBudget budgetOne =
                ProjectBudget.create(budgetInputOne);
        ProjectBudget budgetTwo =
                ProjectBudget.create(budgetInputTwo);

        //Act
        boolean actual = budgetOne.equals(budgetTwo);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Compared instance is null. Expected false.")
    void instancesIsNull() {

        //Arrange
        double budgetInput = 0;
        ProjectBudget budgetOne =
                ProjectBudget.create(budgetInput);

        //Act
        boolean actual = budgetOne.equals(null);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Two instances of ProjectBudget are the " +
            "same.")
    void instancesAreTheSame() {
        //Arrange
        double budgetInput = 999999999.99;

        ProjectBudget budgetOne =
                ProjectBudget.create(budgetInput);
        ProjectBudget budgetTwo =
                budgetOne;

        //Act
        boolean actual = budgetOne.equals(budgetTwo);

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Compared object are not an instance of " +
            "ProjectBudget class.")
    void areNotAnInstanceOfThisClass() {
        //Arrange
        double budgetInput = 5;

        ProjectBudget budgetOne =
                ProjectBudget.create(budgetInput);
        Object budgetTwo = new Object();

        //Act
        boolean actual = budgetOne.equals(budgetTwo);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Instances are equals. Expected same hashcode.")
    void sameHashcode() {
        //Arrange
        double budgetInput = 5;

        ProjectBudget budgetOne =
                ProjectBudget.create(budgetInput);
        ProjectBudget budgetTwo =
                ProjectBudget.create(budgetInput);

        //Act
        boolean actual = budgetOne.hashCode() ==
                budgetTwo.hashCode();

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Instances are not equals. Expected different hashcode.")
    void differentHashcode() {
        //Arrange
        double budgetInputOne = 5;
        double budgetInputTwo = 50;

        ProjectBudget budgetOne =
                ProjectBudget.create(budgetInputOne);
        ProjectBudget budgetTwo =
                ProjectBudget.create(budgetInputTwo);

        //Act
        boolean actual = budgetOne.hashCode() == budgetTwo.hashCode();

        //Assert
        assertFalse(actual);
    }

    @Test
    void getBudget() {
        //Arrange
        double budgetInput = 1;

        ProjectBudget budget =
                ProjectBudget.create(budgetInput);

        double projectDouble = budget.getBudget();


        //Act
        boolean actual = budgetInput == projectDouble;

        //Assert
        assertTrue(actual);
    }
}
