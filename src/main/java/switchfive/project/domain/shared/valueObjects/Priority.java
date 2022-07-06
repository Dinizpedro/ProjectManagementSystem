package switchfive.project.domain.shared.valueObjects;

import switchfive.project.domain.shared.dddTypes.ValueObject;

import java.util.Objects;

public class Priority implements ValueObject<Priority> {

    //Attribute
    public final int priority;

    //Constructor
    private Priority(int priority) {
        if (priority > 0) {
            this.priority = priority;
        } else {
            throw new IllegalArgumentException("Priority must be above zero!");
        }
    }

    /**
     * Call constructor method to instantiate this class.
     *
     * @param priority final int priority
     * @return new instance.
     */
    public static Priority createPriority(final int priority) {
        return new Priority(priority);
    }

    /**
     * @param other The other value object.
     * @return true if the given value object's and this value object's
     * attributes are the same.
     */
    @Override
    public boolean sameValueAs(Priority other) {
        return priority == other.priority;
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
        if (!(other instanceof Priority)) return false;
        Priority priority1 = (Priority) other;
        return sameValueAs(priority1);
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
        return Objects.hash(priority);
    }

}
