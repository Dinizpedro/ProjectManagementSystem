package switchfive.project.domain.shared.valueObjects;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class SprintNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {Integer.MAX_VALUE, 0, 1000, -5})
    void sprintNumberShouldBeBetween1And999(int newSprintNumber) {
        assertThrows(IllegalArgumentException.class, () -> SprintNumber.create(newSprintNumber));
    }

    @Test
    void anInstanceShouldNotBeEqualToNull() {
        assertNotNull(SprintNumber.create(1));
    }
    @Test
    void shouldReturnTrueWhenAllFieldsAreEquals() {
        SprintNumber expected = SprintNumber.create(1);
        SprintNumber actual = SprintNumber.create(1);

        assertEquals(expected, actual);
    }
    @Test
    void hashCodesOfEqualInstancesShouldBeEqual() {
        SprintNumber expected = SprintNumber.create(1);
        SprintNumber actual = SprintNumber.create(1);

        assertEquals(expected.hashCode(), actual.hashCode());
    }
}