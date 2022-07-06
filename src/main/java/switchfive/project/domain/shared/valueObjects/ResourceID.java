package switchfive.project.domain.shared.valueObjects;

import switchfive.project.domain.shared.dddTypes.ValueObject;

import java.util.Objects;
import java.util.UUID;

import static java.util.UUID.randomUUID;

public class ResourceID implements ValueObject<ResourceID> {
    /**
     * UUID - Universally Unique Identifier.
     */
    private UUID uuid;

    /**
     * We use private constructor so that any target class could not
     * instantiate this class directly by calling constructor.
     */
    private ResourceID() {
        this.uuid = randomUUID();
    }

    /**
     * Call constructor method to instantiate this class.
     *
     * @return new instance.
     */
    public static ResourceID createResourceID() {
        return new ResourceID();
    }

    public static ResourceID createResourceID(String fromString) {
        ResourceID newResourceID = new ResourceID();
        newResourceID.uuid = UUID.fromString(fromString);
        return newResourceID;
    }

    @Override
    public String toString() {
        return uuid.toString();
    }

    /**
     * @param other The other value object.
     * @return true if the given value object's and this value object's
     * attributes are the same.
     */
    @Override
    public boolean sameValueAs(final ResourceID other) {
        return other != null && Objects.equals(uuid, other.uuid);
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
        if (!(other instanceof ResourceID)) {
            return false;
        }
        ResourceID that = (ResourceID) other;
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
        return Objects.hash(uuid);
    }

    public UUID getResourceID() {
        return uuid;
    }
}

