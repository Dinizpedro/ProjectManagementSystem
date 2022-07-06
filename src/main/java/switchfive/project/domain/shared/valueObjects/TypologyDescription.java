package switchfive.project.domain.shared.valueObjects;

import switchfive.project.domain.shared.dddTypes.ValueObject;

import java.util.Locale;
import java.util.Objects;

/**
 * TypologyDescription describes the data and the methods of its objects.
 */
public final class TypologyDescription implements
        ValueObject<TypologyDescription> {

    private final String description;

    /**
     * We use private constructor so that any target class could not
     * instantiate this class directly by calling constructor.
     *
     * @param description final String description
     */
    private TypologyDescription(final String description) {
       if (isValidDescription(description)) {
           this.description = description;
        } else {
            throw new IllegalArgumentException("Description not valid");
        }
    }

    private boolean isValidDescription(String description) {
        return description != null && isDescriptionBetween1And50(description) && !isDescriptionOnlyBlankSpaces(description);
    }

    private boolean isDescriptionOnlyBlankSpaces(String description) {
        return !(description.trim().length() > 0);
    }

    private boolean isDescriptionBetween1And50(String description) {
        final int MINIMUM_SIZE = 1;
        final int MAXIMUM_SIZE = 50;
        final int NAME_LENGTH = description.length();

        return NAME_LENGTH > MINIMUM_SIZE && NAME_LENGTH < MAXIMUM_SIZE;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Call TypologyDescription constructor method to instantiate this class.
     *
     * @param description final String description
     * @return new instance.
     */
    public static TypologyDescription create(final String description) {
        return new TypologyDescription(description);
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
    public boolean sameValueAs(final TypologyDescription other) {
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
        if (!(other instanceof TypologyDescription)) {
            return false;
        }
        TypologyDescription that = (TypologyDescription) other;
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
