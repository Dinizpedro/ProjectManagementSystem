package switchfive.project.domain.shared.valueObjects;

import switchfive.project.domain.shared.dddTypes.ValueObject;

import java.util.Objects;

/**
 * ProjectDescription class describes the data and the methods of its objects.
 */
public class ProjectNumberOfPlannedSprints implements
        ValueObject<ProjectNumberOfPlannedSprints> {
    /**
     * Number of planned sprints inserted by actor.
     */
    private final int numberOfPlannedSprints;

    /**
     * We use private constructor so that any target class could not
     * instantiate this class directly by calling constructor.
     *
     * @param sprints final int sprints
     */
    private ProjectNumberOfPlannedSprints(final int sprints) {
        final int MINIMUM_VALUE = 1;
        final int MAXIMUM_VALUE = 999;

        if (sprints >= MINIMUM_VALUE && sprints <= MAXIMUM_VALUE) {
            this.numberOfPlannedSprints = sprints;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * @param sprints input of number of sprints.
     * @return new instance of ProjectNumberOfPlannedSprints.
     */
    public static ProjectNumberOfPlannedSprints create(final int sprints) {
        return new ProjectNumberOfPlannedSprints(sprints);
    }

    public int getNumber() {
        return numberOfPlannedSprints;
    }

    /**
     * @param other The other value object.
     * @return true if an instance have the same value as other instance.
     */
    @Override
    public boolean sameValueAs(
            final ProjectNumberOfPlannedSprints other) {
        return other != null
                && numberOfPlannedSprints == other.numberOfPlannedSprints;
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
        if (!(other instanceof ProjectNumberOfPlannedSprints)) {
            return false;
        }
        ProjectNumberOfPlannedSprints that =
                (ProjectNumberOfPlannedSprints) other;
        return sameValueAs(that);
    }

    /**
     * @return the hash code of an instance of Name
     */
    @Override
    public int hashCode() {
        return Objects.hash(numberOfPlannedSprints);
    }
}
