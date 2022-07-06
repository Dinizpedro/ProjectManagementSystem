package switchfive.project.domain.shared.valueObjects;

import switchfive.project.domain.shared.dddTypes.ValueObject;

import java.util.Objects;

public class SprintNumber implements ValueObject<SprintNumber> {

    private int sprintNumber;

    private SprintNumber(final int sprintNumberInput) {
        final int MINIMUM_VALUE = 1;
        final int MAXIMUM_VALUE = 999;

        if (sprintNumberInput >= MINIMUM_VALUE &&
                sprintNumberInput <= MAXIMUM_VALUE) {
            this.sprintNumber = sprintNumberInput;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static SprintNumber create(final int sprintNumber) {
        return new SprintNumber(sprintNumber);
    }

    public int getSprintNumber() {
        return sprintNumber;
    }

    @Override public boolean sameValueAs(SprintNumber that) {
        return that != null && sprintNumber == that.sprintNumber;
    }

    @Override public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SprintNumber)) {
            return false;
        }
        SprintNumber that = (SprintNumber) other;
        return sameValueAs(that);
    }

    @Override public int hashCode() {
        return Objects.hash(sprintNumber);
    }
}
