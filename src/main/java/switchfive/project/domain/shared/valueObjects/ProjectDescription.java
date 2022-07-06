package switchfive.project.domain.shared.valueObjects;

import switchfive.project.domain.shared.dddTypes.ValueObject;

import java.util.Locale;
import java.util.Objects;

public class ProjectDescription implements ValueObject<ProjectDescription> {
    /**
     * A description for project.
     */
    private final String description;

    /**
     * We use private constructor so that any target class could not
     * instantiate this class directly by calling constructor.
     *
     * @param descriptionInput final String descriptionInput
     */
    private ProjectDescription(final String descriptionInput) {
        final int MINIMUM_LENGTH = 5;
        final int MAXIMUM_LENGTH = 50;
        int actualLength = descriptionInput.length();

        if (actualLength >= MINIMUM_LENGTH && actualLength <= MAXIMUM_LENGTH) {
            this.description = descriptionInput;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Call ProjectDescription constructor method to instantiate this class.
     *
     * @param descriptionInput final String descriptionInput
     * @return new instance.
     */
    public static ProjectDescription create(final String descriptionInput) {
        return new ProjectDescription(descriptionInput);
    }

    public String getDescription() {
        return description;
    }

    /**
     * Value objects compare by the values of their attributes, they don't
     * have an identity.
     *
     * @param other The other value object.
     * @return <code>true</code> if the given value object's and this value
     * object's attributes are the same.
     */
    @Override
    public boolean sameValueAs(final ProjectDescription other) {
        return other != null && description.toLowerCase(Locale.ROOT).trim()
                .equals(other.description.toLowerCase(Locale.ROOT).trim());
    }

    /**
     * Check if two objects have the same data, as the classes in Java are
     * inherited from the object classes only.
     *
     * @param other final Object other
     * @return true if two objects have the same data; otherwise, returns false.
     */
    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ProjectDescription)) {
            return false;
        }
        ProjectDescription that = (ProjectDescription) other;
        return sameValueAs(that);
    }

    /**
     * Whenever hashcode is invoked on the same object more than once
     * during an execution of a Java application, the hashCode method must
     * consistently return the same integer, provided no information used in
     * equals comparisons on the object is modified.
     *
     * @return true if two objects have the same hashcode; otherwise, returns
     * false.
     */
    @Override
    public int hashCode() {
        return Objects.hash(description.toLowerCase(Locale.ROOT));
    }
}
