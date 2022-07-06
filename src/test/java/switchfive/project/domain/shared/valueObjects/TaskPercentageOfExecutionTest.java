package switchfive.project.domain.shared.valueObjects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskPercentageOfExecutionTest {

    @Test
    @DisplayName("Should return a Percentage Of Execution successfully created when values are within the minimum " +
            "and maximum value.")
    void createSuccessfullyPercentageOfExecution() {

        //-----ARRANGE----

        double percentageOfExecutionValue = 10;


        //-----ACT--------

        TaskPercentageOfExecution taskPercentageOfExecutionOne = TaskPercentageOfExecution.
                createPercentage(percentageOfExecutionValue);

        TaskPercentageOfExecution taskPercentageOfExecutionTwo = TaskPercentageOfExecution.
                createPercentage(percentageOfExecutionValue);

        //-----ASSERT-----

        assertEquals(taskPercentageOfExecutionOne, taskPercentageOfExecutionTwo);

    }

    @Test
    @DisplayName("Should fail when percentage of execution value is less than zero, " +
            "throwing a Illegal Argument Exception.")
    void percentageOfAllocationFailsBecauseIsBelowMINIMUM() {

        //-----ARRANGE----

        double percentageOfExecutionValue = -1;

        //-----ASSERT-----

        assertThrows(IllegalArgumentException.class, () ->
                TaskPercentageOfExecution.
                        createPercentage(percentageOfExecutionValue));

   }


    @Test
    @DisplayName("Should fail when percentage of execution value is above 100 which is the maximum value, " +
            "throwing a Illegal Argument Exception.")
    void percentageOfAllocationFailsBecauseIsAboveMAXIMUM() {

        //-----ARRANGE----

        double percentageOfExecutionValue = 101;

        //-----ASSERT-----

        assertThrows(IllegalArgumentException.class, () ->
                TaskPercentageOfExecution.
                        createPercentage(percentageOfExecutionValue));

    }


    @Test
    @DisplayName("Should return the value of the percentage of execution, and also verifying if 0 is successfully" +
            "accepted for the creation of Percentage of Execution.")
    void getPercentageOfExecution(){

        //-----ARRANGE----

        double percentageOfExecutionValue = 0;
        TaskPercentageOfExecution taskPercentageOfExecution = TaskPercentageOfExecution.createPercentage(percentageOfExecutionValue);

        //-----ACT------

        double expected = 0;
        double actual = taskPercentageOfExecution.getPercentageOfExecution();

        //-----ASSERT---

        assertEquals(expected,actual);

    }

    @Test
    @DisplayName("Create successfully a Percentage of Execution for the Maximum Value allowed.")
    void createSuccessfullyPercentageOfExecutionFor100Percentage() {

        //-----ARRANGE----

        double percentageOfExecutionValue = 100;


        //-----ACT--------

        TaskPercentageOfExecution taskPercentageOfExecutionOne = TaskPercentageOfExecution.
                createPercentage(percentageOfExecutionValue);

        TaskPercentageOfExecution taskPercentageOfExecutionTwo = TaskPercentageOfExecution.
                createPercentage(percentageOfExecutionValue);

        //-----ASSERT-----

        assertEquals(taskPercentageOfExecutionOne, taskPercentageOfExecutionTwo);

    }

    @Test
    @DisplayName("Should return true when percentage of execution is equals to other.")
    void percentageOfExecutionIsTrueWhenEqualsToOther() {

        //-----ARRANGE----

        double percentageOfExecutionValue = 100;


        //-----ACT--------

        TaskPercentageOfExecution taskPercentageOfExecutionOne = TaskPercentageOfExecution.
                createPercentage(percentageOfExecutionValue);

        TaskPercentageOfExecution taskPercentageOfExecutionTwo = TaskPercentageOfExecution.
                createPercentage(percentageOfExecutionValue);

        //-----ASSERT-----

        assertTrue(taskPercentageOfExecutionOne.equals(taskPercentageOfExecutionTwo));

    }

    @Test
    @DisplayName("Should return false when percentage of execution is not equal to other.")
    void percentageOfExecutionIsFalseWhenEqualsToOther() {

        //-----ARRANGE----

        double percentageOfExecutionValue = 100;
        double percentageOfExecutionOtherValue = 10;


        //-----ACT--------

        TaskPercentageOfExecution taskPercentageOfExecutionOne = TaskPercentageOfExecution.
                createPercentage(percentageOfExecutionValue);

        TaskPercentageOfExecution taskPercentageOfExecutionTwo = TaskPercentageOfExecution.
                createPercentage(percentageOfExecutionOtherValue);

        //-----ASSERT-----

        assertFalse(taskPercentageOfExecutionOne.equals(taskPercentageOfExecutionTwo));

    }

    @Test
    @DisplayName("Should return false because percentage of execution is null and other has value.")
    void percentageOfExecutionIsNullNotSameValueAs(){
        //-----ARRANGE----

        double percentageOfExecutionValue = 100;

        TaskPercentageOfExecution taskPercentageOfExecutionOne = TaskPercentageOfExecution.
                createPercentage(percentageOfExecutionValue);

        TaskPercentageOfExecution taskPercentageOfExecutionTwo = null;
        //-----ACT--------
        boolean result = taskPercentageOfExecutionOne.sameValueAs(taskPercentageOfExecutionTwo);

        //-----ASSERT-----
        assertFalse(result);

    }

    @Test
    @DisplayName("Should return false because percentage of execution is null and other has value.")
    void percentageOfExecutionIsTheSameValueAs(){
        //-----ARRANGE----

        double percentageOfExecutionValue = 100;

        TaskPercentageOfExecution taskPercentageOfExecutionOne = TaskPercentageOfExecution.
                createPercentage(percentageOfExecutionValue);

        TaskPercentageOfExecution taskPercentageOfExecutionTwo = TaskPercentageOfExecution.
                createPercentage(percentageOfExecutionValue);
        //-----ACT--------
        boolean result = taskPercentageOfExecutionOne.sameValueAs(taskPercentageOfExecutionTwo);

        //-----ASSERT-----
        assertTrue(result);

    }
    @Test
    @DisplayName("Percentage Of Execution have the same hashcode.")
    void hashCodeIsTheSame() {
        //-----ARRANGE----

        double percentageOfExecutionValue = 100;

        TaskPercentageOfExecution taskPercentageOfExecutionOne = TaskPercentageOfExecution.
                createPercentage(percentageOfExecutionValue);

        TaskPercentageOfExecution taskPercentageOfExecutionTwo = TaskPercentageOfExecution.
                createPercentage(percentageOfExecutionValue);

        //-----ASSERT-----
        assertEquals(taskPercentageOfExecutionOne.hashCode(), taskPercentageOfExecutionTwo.hashCode());
    }

    @Test
    @DisplayName("Percentage Of Execution are the sameInstanceOf.")
    void trueWhenAssertingTheSameInstance() {
        //-----ARRANGE----

        double percentageOfExecutionValue = 100;

        TaskPercentageOfExecution taskPercentageOfExecutionOne = TaskPercentageOfExecution.
                createPercentage(percentageOfExecutionValue);

        //-----ASSERT-----
        assertEquals(taskPercentageOfExecutionOne, taskPercentageOfExecutionOne);
    }


    @Test
    @DisplayName("Object is not an instance of Percentage Of Execution.")
    void returnsFalseWhenAssertingWithADifferentObject() {
        //-----ARRANGE----

        double percentageOfExecutionValue = 100;

        TaskPercentageOfExecution taskPercentageOfExecutionOne = TaskPercentageOfExecution.
                createPercentage(percentageOfExecutionValue);

        Object object = new Object();

        //-----ASSERT-----
        assertNotEquals(taskPercentageOfExecutionOne, object);
    }

}