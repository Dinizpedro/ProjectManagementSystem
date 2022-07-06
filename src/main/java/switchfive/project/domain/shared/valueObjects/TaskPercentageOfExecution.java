package switchfive.project.domain.shared.valueObjects;

import switchfive.project.domain.shared.dddTypes.ValueObject;

import java.util.Objects;

public class TaskPercentageOfExecution implements ValueObject<TaskPercentageOfExecution> {

    private final double percentageOfExecution;

    private TaskPercentageOfExecution(double percentageOfExecutionInput) {
        final double MINIMUM_PERCENTAGE = 0;
        final double MAXIMUM_PERCENTAGE = 100;

        if (percentageOfExecutionInput >=MINIMUM_PERCENTAGE && percentageOfExecutionInput <= MAXIMUM_PERCENTAGE) {
            this.percentageOfExecution = percentageOfExecutionInput;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * method which creates a new instance for a Task percentage of execution
     * @param percentageOfExecutionInput - the inserted percentage of execution
     * @return a new instance of TaskPercentageExecution
     */
    public static TaskPercentageOfExecution createPercentage(final double percentageOfExecutionInput) {
        return new TaskPercentageOfExecution(percentageOfExecutionInput);
    }

    /**
     * method to get the percentage of execution of a task
     * @return the value of percentage of execution of a task
     */
    public double getPercentageOfExecution() {
        return percentageOfExecution;
    }

    @Override
    public boolean sameValueAs(final TaskPercentageOfExecution other) {
        return other != null && Double.compare(other.percentageOfExecution, percentageOfExecution) == 0;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if(!(other instanceof TaskPercentageOfExecution)){
            return false;
        }
        TaskPercentageOfExecution that = (TaskPercentageOfExecution) other;
        return sameValueAs(that);

    }

    @Override
    public int hashCode() {
        return Objects.hash(percentageOfExecution);
    }
}
