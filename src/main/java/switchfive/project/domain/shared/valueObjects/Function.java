package switchfive.project.domain.shared.valueObjects;

import switchfive.project.domain.shared.dddTypes.ValueObject;

import java.util.Objects;

public class Function implements ValueObject<Function> {
    /**
     * Function description.
     */
    private final String description;

    private static final int MINIMUM_SIZE = 0;

    private static final int MAXIMUM_SIZE = 16;


    /**
     * Create a Function instance.
     *
     * @param description the function description.
     */
    private Function(final String description) {
        if (isValidDescription(description)) {
            this.description = description;
        } else {
            throw new IllegalArgumentException("Invalid Function");
        }
    }

    /**
     * Static method to create function object.
     *
     * @param description the User's function description.
     * @return a new Function instance.
     */
    public static Function createFunction(String description) {

        return new Function(description);
    }

    private boolean isDescriptionNotNull(final String inputString) {

        return inputString != null;
    }

    private boolean isDescriptionOnlyBlankSpaces(String description) {
        return (description.trim().length() <= 0);
    }

    private boolean isValidDescription(final String description) {
        boolean isValid = false;

        if (isDescriptionNotNull(description)) {
            int stringSize = description.length();
            isValid = stringSize > MINIMUM_SIZE && stringSize < MAXIMUM_SIZE;
        }
        return isValid && !isDescriptionOnlyBlankSpaces(description);
    }


    public String getDescription() {

        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Function function = (Function) o;
        return sameValueAs(function);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    @Override
    public boolean sameValueAs(Function other) {
        return other != null && this.description.equals(other.description);
    }
}


