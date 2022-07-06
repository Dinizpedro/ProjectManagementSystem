/*
package switchfive.project.d_domain.shared.valueObjects;

import switchfive.project.d_domain.shared.dddTypes.ValueObject;

import java.util.Objects;
import java.util.UUID;

public final class TypologyID implements ValueObject<TypologyID> {
    */
/**
     * Typology Identification Number.
     *//*

    private final UUID identity;

    */
/**
     * We use private constructor so that any target class could not
     * instantiate this class directly by calling constructor.
     *
     * @param identityInput final int identityInput
     *//*

    private TypologyID(final UUID identityInput) {
        this.identity = identityInput;
    }

    */
/**
     * Call constructor method to instantiate this class.
     *
     * @return new instance.
     *//*

    public static TypologyID createTypologyID() {
        return new TypologyID(UUID.randomUUID());
    }

    public static TypologyID stringToTypologyID(String typologyIdInput) {
        return new TypologyID(UUID.fromString(typologyIdInput));
    }

    public String getIdentity() {
        return identity.toString();
    }

    */
/**
     * @param other The other value object.
     * @return true if the given value object's and this value object's
     * attributes are the same.
     *//*

    @Override
    public boolean sameValueAs(final TypologyID other) {
        return other != null && identity == other.identity;
    }

    */
/**
     * Check if two objects have the same data, as the classes in Java are
     * inherited from the object classes only.
     *
     * @param other final Object other
     * @return true if two objects have the same data; otherwise, returns false.
     *//*

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TypologyID)) {
            return false;
        }
        TypologyID that = (TypologyID) other;
        return sameValueAs(that);
    }

    */
/**
     * Whenever hashcode is invoked on the same object more than once
     * during an execution of a Java application, the hashCode method must
     * consistently return the same integer, provided no information used in
     * equals comparisons on the object is modified.
     *
     * @return true if two objects have the same hashcode; otherwise, returns
     * false.
     *//*

    @Override
    public int hashCode() {
        return Objects.hash(identity);
    }
}
*/
