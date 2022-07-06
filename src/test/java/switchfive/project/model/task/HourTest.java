package switchfive.project.model.task;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import switchfive.project.domain.shared.valueObjects.Hour;

import static org.junit.jupiter.api.Assertions.*;

class HourTest {

    @ParameterizedTest
    @ValueSource(doubles = {-0.01, -5})
    void shouldThrowAnIllegalArgumentExceptionForNegativeValues(double value) {
        assertThrows(IllegalArgumentException.class, () -> Hour.createHour(value));
    }

    @Test
    void shouldReturnTrueComparingInstancesWithTheSameValue() {
        Hour hourOne = Hour.createHour(3);
        Hour hourTwo = Hour.createHour(3);

        assertEquals(hourOne, hourTwo);
    }

    @Test
    void shouldReturnTrueAssertingAnInstanceNotEqualToNull() {
        Hour validHour = Hour.createHour(0);

        assertNotEquals(null, validHour);
    }

    @Test
    void shouldReturnTrueComparingTheSameInstance() {
        Hour validHour = Hour.createHour(3);

        assertEquals(validHour, validHour);
    }

    @Test
    void shouldReturnTrueAssertingTwoDifferentObjectsAreNotEquals() {
        Hour hourOne = Hour.createHour(1);
        Hour hourTwo = Hour.createHour(2);

        assertNotEquals(hourOne, hourTwo);
    }

    @Test
    void shouldReturnTrueComparingHashCodesOfTheSameValue() {
        Hour hourOne = Hour.createHour(3);
        Hour hourTwo = Hour.createHour(3);

        assertEquals(hourOne.hashCode(), hourTwo.hashCode());
    }

    @Test
    void shouldReturnTrueAssertingHashCodesOfTheSameValueAreNotEquals() {
        Hour hourOne = Hour.createHour(1);
        Hour hourTwo = Hour.createHour(2);

        assertNotEquals(hourOne.hashCode(), hourTwo.hashCode());
    }
}
