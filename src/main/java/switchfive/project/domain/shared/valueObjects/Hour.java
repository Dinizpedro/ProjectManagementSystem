package switchfive.project.domain.shared.valueObjects;

import switchfive.project.domain.shared.dddTypes.ValueObject;

import java.io.Serializable;
import java.util.Objects;

public class Hour implements ValueObject<Hour>, Serializable {

    /**
     * Represents a certain amount of hours.
     */
    private final double amountOfHours;


    private Hour(final double hour) {
        if (isValidHour(hour)) {
            this.amountOfHours = hour;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Creates a new hour. Can have decimals values but must be positive.
     *
     * @param hours the amount of hours.
     * @return a new hour instance.
     * @throws IllegalArgumentException if inserted a negative value.
     */
    public static Hour createHour(double hours) {
        return new Hour(hours);
    }

    private boolean isValidHour(final double hourInput) {
        // Hours must be positive
        final double MIN = 0;
        return hourInput >= MIN;
    }

    public double getAmountOfHours() {
        return amountOfHours;
    }

    /**
     * Value objects compare by the values of their attributes, they don't have
     * an identity.
     *
     * @param other The other value object.
     * @return <code>true</code> if the given value object's and this value
     * object's attributes are the same.
     */
    @Override
    public boolean sameValueAs(Hour other) {
        return other != null && Double.compare(amountOfHours, other.amountOfHours) == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hour hour = (Hour) o;
        return sameValueAs(hour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amountOfHours);
    }
}
