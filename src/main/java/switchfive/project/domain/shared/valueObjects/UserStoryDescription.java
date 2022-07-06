package switchfive.project.domain.shared.valueObjects;

import switchfive.project.domain.shared.dddTypes.ValueObject;

import java.util.Objects;

/**
 * ProjectDescription class describes the data and the methods of its objects.
 */
public class UserStoryDescription implements ValueObject<UserStoryDescription> {
    /**
     * Description inserted by user.
     */
    private final String description;

    /**
     * Constructor with description input.
     *
     * @param description String descriptionInput
     */

    private UserStoryDescription(String description) {
        if (isValidDescriptionForUserStory(description)) {
            this.description = description;
        } else {
            throw new IllegalArgumentException("The description must have between 1 and 150 charters!");
        }
    }

    public static UserStoryDescription createUserStoryDescription(String description) {
        return new UserStoryDescription(description);
    }

    /**
     * A method that validates if the description to be inserted in a user story
     * has between one and one hundred and fifty characters.
     *
     * @param description String Description
     * @return true if the description meets the requirements and false
     * otherwise
     */
    public static boolean isValidDescriptionForUserStory(String description) {
        final int actualDescriptionLength = description.length();
        final int minimumDescriptionLength = 1;
        final int maximumDescriptionLength = 150;

        return actualDescriptionLength >= minimumDescriptionLength
                && actualDescriptionLength <= maximumDescriptionLength;
    }

    public String getDescription() {
        return description;
    }

    /**
     * @param other The other value object.
     * @return true if the given value object's and this value object's
     * attributes are the same.
     */
    @Override
    public boolean sameValueAs(UserStoryDescription other) {
        return description.equals(other.description);
    }

    /**
     * Check if two objects have the same data, as the classes in Java are
     * inherited from the object classes only.
     *
     * @param other final Object other
     * @return true if two objects have the same data; otherwise, returns false.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof UserStoryDescription)) return false;
        UserStoryDescription that = (UserStoryDescription) other;
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
        return Objects.hash(description);
    }
}
