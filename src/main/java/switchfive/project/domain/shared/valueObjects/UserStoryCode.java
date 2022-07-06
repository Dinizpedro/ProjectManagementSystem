package switchfive.project.domain.shared.valueObjects;

import switchfive.project.domain.shared.dddTypes.ValueObject;

import java.util.Objects;

public class UserStoryCode implements ValueObject<UserStoryCode> {
    /**
     * Typology Identification Number.
     */
    private final String identity;

    /**
     * We use private constructor so that any target class could not
     * instantiate this class directly by calling constructor.
     *
     * @param identityInput final int identityInput
     */
    private UserStoryCode(final String identityInput) {
        this.identity = identityInput;
    }

    /**
     * Call constructor method to instantiate this class.
     *
     * @param identity final int identity
     * @return new instance.
     */
    public static UserStoryCode createUserStoryCode(final String identity) {
        return new UserStoryCode(identity);
    }

    public String getIdentity() {
        return identity;
    }

    /**
     * @param other The other value object.
     * @return true if the given value object's and this value object's
     * attributes are the same.
     */
    @Override
    public boolean sameValueAs(UserStoryCode other) {
        return Objects.equals(identity, other.identity);
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
        if (!(other instanceof UserStoryCode)) return false;
        UserStoryCode that = (UserStoryCode) other;
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
        return Objects.hash(identity);
    }
}
