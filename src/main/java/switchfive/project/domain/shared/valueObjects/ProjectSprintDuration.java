package switchfive.project.domain.shared.valueObjects;

import switchfive.project.domain.shared.dddTypes.ValueObject;

import java.util.Objects;

/**
 * ProjectSprintDuration class describes the duration of a certain sprint in
 * a certain project.
 */
public class ProjectSprintDuration implements
        ValueObject<ProjectSprintDuration> {

    /**
     * The duration of a certain sprint.
     */
    private final int sprintDuration;

    /**
     * We use private constructor so that any target class could not
     * instantiate this class directly by calling constructor.
     *
     * @param duration final int duration
     */
    private ProjectSprintDuration(final int duration) {
        final int MINIMUM_VALUE = 1;
        final int MAXIMUM_VALUE = 9;

        if (duration >= MINIMUM_VALUE && duration <= MAXIMUM_VALUE) {
            this.sprintDuration = duration;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * @param sprintDuration duration of a certain sprint
     * @return new instance of ProjectSprintDuration class.
     */
    public static ProjectSprintDuration create(final int sprintDuration) {
        return new ProjectSprintDuration(sprintDuration);
    }

    public int getDuration() {
        return sprintDuration;
    }

    /**
     * @param other The other value object.
     * @return true if other have same valued as this.sprintDuration.
     */
    @Override
    public boolean sameValueAs(final ProjectSprintDuration other) {
        return other != null && sprintDuration == other.sprintDuration;
    }

    /**
     * @param other final Object other
     * @return true if objects are equals; otherwise, returns false;
     */
    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ProjectSprintDuration)) {
            return false;
        }
        ProjectSprintDuration that = (ProjectSprintDuration) other;
        return sameValueAs(that);
    }

    /**
     * @return the hashcode of an instance of ProjectSprintDuration class.
     */
    @Override
    public int hashCode() {
        return Objects.hash(sprintDuration);
    }

}
