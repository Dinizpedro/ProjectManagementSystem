package switchfive.project.domain.shared.valueObjects;

import switchfive.project.domain.shared.dddTypes.ValueObject;

import java.util.Objects;

/**
 * ProjectName class describes the data and the methods of its objects.
 */
public class ProjectName implements ValueObject<ProjectName> {
    /**
     * Name inserted by user.
     */
    private final String name;

    /**
     * We use private constructor so that any target class could not
     * instantiate this class directly by calling constructor.
     *
     * @param nameInput name inserted by user.
     */
    private ProjectName(final String nameInput) {
        String pattern = "^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _]*$";
        int length = nameInput.trim().length();
        final int MINIMUM_LENGTH = 5;
        final int MAXIMUM_LENGTH = 50;

        if (nameInput.matches(pattern) && length >= MINIMUM_LENGTH &&
                length <= MAXIMUM_LENGTH) {
            this.name = nameInput;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * @param nameInput name inserted by user.
     * @return new instance of ProjectName
     */
    public static ProjectName create(final String nameInput) {
        return new ProjectName(nameInput);
    }

    public String getName() {
        return name;
    }

    /**
     * @param other The other value object.
     * @return true if other have same valued as this.
     */
    @Override
    public boolean sameValueAs(final ProjectName other) {
        return other != null && name.equals(other.name);
    }

    /**
     * @param other other instance of ProjectName class.
     * @return true if instances are equals; otherwise, returns false.
     */
    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ProjectName)) {
            return false;
        }
        ProjectName that = (ProjectName) other;
        return sameValueAs(that);
    }

    /**
     * @return hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
