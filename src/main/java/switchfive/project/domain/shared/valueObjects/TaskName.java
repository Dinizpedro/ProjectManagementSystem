package switchfive.project.domain.shared.valueObjects;

import switchfive.project.domain.shared.dddTypes.ValueObject;

import java.util.Objects;

public class TaskName implements ValueObject<TaskName> {

    private final String name;

    private TaskName(final String name) {
        if (isValidName(name)) {
            this.name = name;
        } else {
            throw new IllegalArgumentException();
        }

    }

    /**
     * Creates a new taskName instance.
     *
     * @param taskName the task's name
     * @return new instance of TaskName
     */
    public static TaskName createTaskName(final String taskName) {
        return new TaskName(taskName);
    }

    private boolean isValidName(String name) {
        return name != null && isNameBetween1And50(name) && !isNameOnlyBlankSpaces(name);
    }

    private boolean isNameOnlyBlankSpaces(String name) {
        return (name.trim().length() <= 0);
    }

    private boolean isNameBetween1And50(String name) {
        final int MINIMUM_SIZE = 1;
        final int MAXIMUM_SIZE = 50;
        final int NAME_LENGTH = name.length();

        return NAME_LENGTH > MINIMUM_SIZE && NAME_LENGTH < MAXIMUM_SIZE;
    }

    /**
     * gets a name of a Task
     * @return a String for that Task Name
     */
    public String getName() {
        return name;
    }

    @Override
    public boolean sameValueAs(TaskName other) {
        return other != null && name.equals(other.name);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof TaskName)) return false;
        TaskName taskName = (TaskName) object;
        return sameValueAs(taskName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
