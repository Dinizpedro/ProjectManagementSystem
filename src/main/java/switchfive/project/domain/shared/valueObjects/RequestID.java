package switchfive.project.domain.shared.valueObjects;

import switchfive.project.domain.shared.dddTypes.ValueObject;

import java.util.Objects;
import java.util.UUID;

import static java.util.UUID.randomUUID;

public class RequestID implements ValueObject<RequestID> {
    /**
     * Request Identification Number.
     */
    private UUID identity;

    private RequestID() {
        this.identity = randomUUID();
    }

    /**
     * Call constructor method to create an Instance with a random UUID identity.
     *
     * @return new RequestID object.
     */
    public static RequestID createRequestID() {
        return new RequestID();
    }

    /**
     * Call constructor method to create an Instance with a defined  UUID identity.
     *
     * @param identity Integer identity
     * @return new instance.
     */
    public static RequestID createRequestID(final String identity) {
        RequestID requestID = new RequestID();
        requestID.identity = UUID.fromString(identity);
        return requestID;
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
        if (other == null || getClass() != other.getClass()) return false;
        RequestID that = (RequestID) other;
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

    @Override
    public boolean sameValueAs(RequestID other) {
        return other != null && this.identity.equals(other.identity);
    }

    @Override
    public String toString() {
        return this.identity.toString();
    }
}
