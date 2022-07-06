package switchfive.project.domain.shared.valueObjects;

import switchfive.project.domain.shared.dddTypes.ValueObject;

import java.util.Objects;

/**
 * ProjectBudget class describes the data and the methods of its objects.
 */
public class ProjectBudget implements ValueObject<ProjectBudget> {
    /**
     * Budget inserted by actor.
     */
    private final double budget;

    /**
     * We use private constructor so that any target class could not
     * instantiate this class directly by calling constructor.
     *
     * @param budgetInput budget inserted by user.
     */
    private ProjectBudget(final double budgetInput) {
        final double MINIMUM_VALUE = 0;
        final double MAXIMUM_VALUE = 999999999.99;

        if (budgetInput >= MINIMUM_VALUE && budgetInput <= MAXIMUM_VALUE) {
            this.budget = budgetInput;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * @param budgetInput budget inserted by actor.
     * @return new instance of ProjectBudget class.
     */
    public static ProjectBudget create(final double budgetInput) {
        return new ProjectBudget(budgetInput);
    }

    public double getBudget() {
        return budget;
    }

    /**
     * @param other The other value object.
     * @return true if an instance have same valued as other instance.
     */
    @Override
    public boolean sameValueAs(final ProjectBudget other) {
        return other != null && Double.compare(other.budget, budget) == 0;
    }

    /**
     * @param other final Object other
     * @return true if objects are equals; otherwise, returns false.
     */
    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ProjectBudget)) {
            return false;
        }
        ProjectBudget that = (ProjectBudget) other;
        return sameValueAs(that);
    }

    /**
     * @return the hash code of an instance of Name
     */
    @Override
    public int hashCode() {
        return Objects.hash(budget);
    }
}
