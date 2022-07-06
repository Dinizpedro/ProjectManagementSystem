package switchfive.project.domain.shared.valueObjects;

import switchfive.project.domain.shared.dddTypes.ValueObject;

import java.util.Objects;

public class TaskDescription implements ValueObject<TaskDescription> {

    private final String description;

    private TaskDescription(final String description) {
        if (isValidDescription(description)) {
            this.description = description;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Method which creates a Task Description from a String, and converts it, after validation
     * into a domain object.
     * @param taskDescription - the inserted description in String format
     * @return a TaskDescription domain object
     */
    public static TaskDescription createTaskDescription(final String taskDescription) {
        return new TaskDescription(taskDescription);
    }

    private boolean isValidDescription(String description) {
        return description != null && isDescriptionBetween1And50(description) && !isDescriptionOnlyBlankSpaces(description);
    }

    private boolean isDescriptionOnlyBlankSpaces(String description) {
        return (description.trim().length() <= 0);
    }

    private boolean isDescriptionBetween1And50(String description) {
        final int MINIMUM_SIZE = 1;
        final int MAXIMUM_SIZE = 150;
        final int NAME_LENGTH = description.length();

        return NAME_LENGTH > MINIMUM_SIZE && NAME_LENGTH < MAXIMUM_SIZE;
    }

    /**
     * method to get a description from a task
     * @return a String of this description
     */
    public String getDescription() {
        return description;
    }

    @Override
    public boolean sameValueAs(TaskDescription other) {
        return other != null && description.equals(other.description);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof TaskDescription)) return false;
        TaskDescription that = (TaskDescription) object;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
