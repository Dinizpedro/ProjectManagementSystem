package switchfive.project.domain.shared.valueObjects;


import switchfive.project.domain.shared.dddTypes.ValueObject;

import java.util.Objects;

/**
 * ResourceCostPerHour describes the data and the methods of its objects.
 */
public class ResourceCostPerHour implements ValueObject<ResourceCostPerHour> {

    /**
     * Cost Per Hour inserted by actor.
     */
    private final double costPerHour;

    /**
     * We use private constructor so that any target class could not
     * instantiate this class directly by calling constructor.
     *
     * @param costPerHourInput final String costPerHourInput
     */
    private ResourceCostPerHour(final double costPerHourInput) {
        final double MINIMUM_VALUE = 1;
        final double MAXIMUM_VALUE = 9999.99;

        if (costPerHourInput >= MINIMUM_VALUE
                && costPerHourInput <= MAXIMUM_VALUE) {
            this.costPerHour = costPerHourInput;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * @param costPerHourInput final double costPerHourInput
     * @return new instance of ResourceCostPerHour.
     */
    public static ResourceCostPerHour create(final double costPerHourInput) {
        return new ResourceCostPerHour(costPerHourInput);
    }

    public double getCostPerHour() {
        return costPerHour;
    }

    /**
     * @param other The other value object.
     * @return true if other have same valued as this.
     */
    public boolean sameValueAs(final ResourceCostPerHour other) {
        return other != null && this.costPerHour == other.costPerHour;
    }

    /**
     * @param other other instance
     * @return true if compared object are equals; otherwise, returns false.
     */
    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        ResourceCostPerHour that = (ResourceCostPerHour) other;
        return sameValueAs(that);
    }

    /**
     * @return hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(costPerHour);
    }
}
