package switchfive.project.domain.shared.valueObjects;

import switchfive.project.domain.aggregates.task.TaskContainer;
import switchfive.project.domain.shared.dddTypes.ValueObject;

import java.io.Serializable;
import java.util.Objects;

public class TaskID implements ValueObject<TaskID>, Serializable {

    private TaskContainer taskContainer;
    private TaskCode taskCode;

    private TaskID(TaskCode taskCode, TaskContainer taskContainer) {
        this.taskCode = taskCode;
        this.taskContainer = taskContainer;
    }

    /**
     * create a TaskID from a TaskCode and a TaskContainer.
     * The TaskContainer is what differentiates a Type of Task, which can have a SprintID
     * or a UserStoryID
     * @param taskCode - task code and identifier
     * @param taskContainer - which differentiates a task in a sprint from a task in a user story
     * @return a domain object TaskID
     */
    public static TaskID createIDTask(TaskCode taskCode, TaskContainer taskContainer) {
        return new TaskID(taskCode, taskContainer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskCode, taskContainer);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof TaskID)) {
            return false;
        }
        TaskID that = (TaskID) object;
        return sameValueAs(that);
    }

    @Override
    public boolean sameValueAs(TaskID other) {
        return other != null && taskCode.equals(other.taskCode) && taskContainer.equals(other.taskContainer);
    }

    /**
     * method to get a Task Container, which will be a sprintID or a UserStoryID
     * @return the interface implementation domain object - SprintID or UserStoryID
     */
    public TaskContainer getTaskContainer() {
        return taskContainer;
    }

    /**
     * method to get TaskCode of a Task
     * @return a String of that taskCode
     */
    public String getTaskCode() {
        return taskCode.getCode();
    }
}
