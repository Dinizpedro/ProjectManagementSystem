package switchfive.project.domain.shared.valueObjects;

import switchfive.project.domain.shared.dddTypes.ValueObject;

import java.util.Objects;

/**
 * ResourcePercentageOfAllocation describes the data and the methods of its
 * objects.
 */
public class ResourcePercentageOfAllocation implements
        ValueObject<ResourcePercentageOfAllocation> {
    /**
     * An allocation for resource.
     */
    private final double allocation;

    /**
     * We use private constructor so that any target class could not
     * instantiate this class directly by calling constructor.
     *
     * @param allocationInput final double allocationInput
     */
    private ResourcePercentageOfAllocation(final double allocationInput) {
        final double MINIMUM = 0;
        final double MAXIMUM = 100;

        if (allocationInput >= MINIMUM
                && allocationInput <= MAXIMUM) {
            this.allocation = allocationInput;
        } else {
            throw new IllegalArgumentException("Illegal Percentage");
        }
    }

    /**
     * Call ResourcePercentageOfAllocation constructor method to instantiate
     * this class.
     *
     * @param allocationInput final double allocationInput
     * @return new instance.
     */
    public static ResourcePercentageOfAllocation create(
            final double allocationInput) {
        return new ResourcePercentageOfAllocation(allocationInput);
    }

    public double getAllocation() {
        return allocation;
    }

    /**
     * @param other The other value object.
     * @return true if other have same valued as this; otherwise, returns false.
     */
    @Override
    public boolean sameValueAs(final ResourcePercentageOfAllocation other) {
        return other != null && this.allocation == other.allocation;
    }

    /**
     * @param other final Object other
     * @return true if objects are equals; otherwise, returns false.
     */
    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ResourcePercentageOfAllocation)) {
            return false;
        }
        ResourcePercentageOfAllocation that =
                (ResourcePercentageOfAllocation) other;
        return sameValueAs(that);
    }

    /**
     * @return hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(allocation);
    }
}
