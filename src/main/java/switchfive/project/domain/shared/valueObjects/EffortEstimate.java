package switchfive.project.domain.shared.valueObjects;

import switchfive.project.domain.shared.dddTypes.ValueObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * EstimateEffort class describes the data  and the methods of its objects.
 *
 * @author Tânia Mota
 */

public class EffortEstimate implements ValueObject<EffortEstimate> {

    //Attributes

    /**
     * Integer effort
     */
    private final Integer effort;

    //Constructor

    /**
     * Estimated effort constructor
     */
    private EffortEstimate(Integer estimatedEffort) {

        if (estimatedEffort == null) {
            this.effort = null;
        } else if (fibonacciValidation(estimatedEffort)) {
            this.effort = estimatedEffort;
        } else {
            throw new IllegalArgumentException("Input isn't part of fibonnaci scale.");
        }
    }

    public static EffortEstimate createEffortEstimate(Integer effortEstimate) {
        return new EffortEstimate(effortEstimate);
    }

    /**
     * Method that allows validating fibonacci sequence
     *
     * @param effort is the attribute to be validated
     * @return true if the fibonacci sequence is valid, false otherwise
     */
    private boolean fibonacciValidation(int effort) {

        ArrayList<Integer> fibonacciScale = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 5, 8, 13, 21, 34));

        return fibonacciScale.contains(effort);
    }

    /**
     * Return the effort estimate as an int.
     *
     * @return int
     */
    public Integer getEffort() {
        return effort;
    }

    /**
     * @param other The other value object.
     * @return true if the given value object's and this value object's
     * attributes are the same.
     */
    @Override
    public boolean sameValueAs(EffortEstimate other) {
        return effort.equals(other.effort);
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
        if (!(other instanceof EffortEstimate)) return false;
        EffortEstimate that = (EffortEstimate) other;
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
        return Objects.hash(effort);
    }

}
