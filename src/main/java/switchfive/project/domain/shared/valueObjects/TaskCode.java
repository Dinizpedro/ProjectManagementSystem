package switchfive.project.domain.shared.valueObjects;

import switchfive.project.domain.shared.dddTypes.ValueObject;

import java.io.Serializable;
import java.util.Objects;

public class TaskCode implements ValueObject<TaskCode>, Serializable {

    private final String code;

    private TaskCode(String taskCode) {
        if (validateTaskCode(taskCode)) {
            this.code = taskCode;
        } else {
            throw new IllegalArgumentException("Invalid Task Code");
        }
    }

    /**
     * Creation of a Task Code
     * @param taskCode a String which will be validated and converted to a domain object
     * @return a TaskCode
     */
    public static TaskCode createTaskCode(String taskCode) {
        return new TaskCode(taskCode);
    }

    private boolean validateTaskCode(String taskCode) {

        boolean validateTaskCode = false;

        if (taskCode.matches("^T[0-9]+")) {
            validateTaskCode = true;
        }

        return validateTaskCode;

    }


    public String getCode() {
        return code;
    }

    /**
     * @param other The other value object.
     * @return true if the given value object's and this value object's
     * attributes are the same.
     */
    @Override
    public boolean sameValueAs(TaskCode other) {
        return Objects.equals(code, other.code);
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
        if (!(other instanceof TaskCode)) return false;
        TaskCode that = (TaskCode) other;
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
        return Objects.hash(code);
    }

}
