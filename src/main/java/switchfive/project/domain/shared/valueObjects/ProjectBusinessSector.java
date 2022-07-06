package switchfive.project.domain.shared.valueObjects;

import switchfive.project.domain.shared.dddTypes.ValueObject;

import java.util.Objects;

/**
 * ProjectBusinessSector class describes the data and the methods of its
 * objects.
 */
public class ProjectBusinessSector implements
        ValueObject<ProjectBusinessSector> {
    /**
     * Business sector inserted by actor.
     */
    private final String description;

    /**
     * We use private constructor so that any target class could not
     * instantiate this class directly by calling constructor.
     *
     * @param sectorInput final String sectorInput
     */
    private ProjectBusinessSector(final String sectorInput) {
        final int MINIMUM_LENGTH = 5;
        final int MAXIMUM_LENGTH = 50;
        int actualLength = sectorInput.length();

        if (actualLength >= MINIMUM_LENGTH && actualLength <= MAXIMUM_LENGTH) {
            this.description = sectorInput;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * @param sector business sector input
     * @return new instance of ProjectBusinessSector class.
     */
    public static ProjectBusinessSector create(final String sector) {
        return new ProjectBusinessSector(sector);
    }

    public String getBusinessSector() {
        return description;
    }

    /**
     * @param other The other value object.
     * @return true if an instance have same valued as other instance.
     */
    @Override
    public boolean sameValueAs(final ProjectBusinessSector other) {
        return description.equals(other.description);
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
        if (!(other instanceof ProjectBusinessSector)) {
            return false;
        }
        ProjectBusinessSector that = (ProjectBusinessSector) other;
        return sameValueAs(that);
    }

    /**
     * @return the hash code of an instance of ProjectBusinessSector class.
     */
    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
