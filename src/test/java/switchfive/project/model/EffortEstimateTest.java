package switchfive.project.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import switchfive.project.domain.shared.valueObjects.EffortEstimate;

import static org.junit.jupiter.api.Assertions.*;

class EffortEstimateTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 5, 8, 13, 21, 34})
    void shouldReturnTrueWhenComparingWithTheSameValue(int value) {
        EffortEstimate effort = EffortEstimate.createEffortEstimate(value);
        Integer actual = effort.getEffort();

        assertEquals(value, actual);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 4, 6, 7, 9, 11, 12, 17, 33})
    void shouldThrowAnIllegalArgumentExceptionForValuesOutOfTheFibonacciScale(int value) {
        assertThrows(IllegalArgumentException.class, () -> EffortEstimate.createEffortEstimate(value));
    }

    @Test
    void shouldReturnTrueWhenComparingTheSameInstance() {
        EffortEstimate effortOne = EffortEstimate.createEffortEstimate(1);

        assertEquals(effortOne, effortOne);
    }

    @Test
    void shouldReturnTrueWhenComparingInstancesWithTheSameValue() {
        EffortEstimate effortOne = EffortEstimate.createEffortEstimate(1);
        EffortEstimate effortTwo = EffortEstimate.createEffortEstimate(1);

        assertEquals(effortOne, effortTwo);
    }

    @Test
    void shouldReturnTrueWhenComparingHashCodesOfTheSameValues() {
        EffortEstimate effortOne = EffortEstimate.createEffortEstimate(1);
        EffortEstimate effortTwo = EffortEstimate.createEffortEstimate(1);

        assertEquals(effortOne.hashCode(), effortTwo.hashCode());
    }

    @Test
    void shouldReturnFalseWhenComparingHashCodesOfTheDifferentValues() {
        EffortEstimate effortOne = EffortEstimate.createEffortEstimate(0);
        EffortEstimate effortTwo = EffortEstimate.createEffortEstimate(1);

        assertNotEquals(effortOne.hashCode(), effortTwo.hashCode());
    }

    @Test
    void shouldReturnFalseWhenComparingInstancesWithNull() {
        EffortEstimate effortOne = EffortEstimate.createEffortEstimate(0);

        assertNotEquals(null, effortOne);
    }

    @Test
    void shouldReturnFalseWhenComparingAnEffortWithAnotherObject() {
        EffortEstimate effortOne = EffortEstimate.createEffortEstimate(0);
        Object anotherClass = new Object();

        boolean areEqual = effortOne.equals(anotherClass);

        assertFalse(areEqual);
    }

    @Test
    void shouldReturnTrueWhenInstancesHaveTheSameValue() {
        EffortEstimate effortOne = EffortEstimate.createEffortEstimate(0);
        EffortEstimate effortTwo = EffortEstimate.createEffortEstimate(0);


        boolean areEqual = effortOne.sameValueAs(effortTwo);

        assertTrue(areEqual);
    }

    @Test
    void shouldReturnFalseWhenInstancesDontHaveTheSameValue() {
        EffortEstimate effortOne = EffortEstimate.createEffortEstimate(0);
        EffortEstimate effortTwo = EffortEstimate.createEffortEstimate(1);


        boolean areEqual = effortOne.sameValueAs(effortTwo);

        assertFalse(areEqual);
    }

    @Test
    void shouldReturnTrueWhenInstancesAreEqual() {
        EffortEstimate effortOne = EffortEstimate.createEffortEstimate(0);
        EffortEstimate effortTwo = EffortEstimate.createEffortEstimate(1);

        assertNotEquals(effortOne, effortTwo);
    }
}
