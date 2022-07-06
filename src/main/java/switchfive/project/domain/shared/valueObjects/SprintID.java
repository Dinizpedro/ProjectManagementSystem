package switchfive.project.domain.shared.valueObjects;

import switchfive.project.domain.aggregates.task.TaskContainer;
import switchfive.project.domain.shared.dddTypes.ValueObject;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SprintID implements TaskContainer, ValueObject<SprintID>,
        Serializable {
    private String projectCode;
    private Integer sprintNumber;

    private SprintID(final String projectCode, final Integer sprintID) {
        this.projectCode = projectCode;
        this.sprintNumber = sprintID;
    }

    public SprintID() {
    }

    public static SprintID createSprintID(final String projectCode,
                                          final Integer sprintID) {
        return new SprintID(projectCode, sprintID);
    }

    @Override public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public Integer getSprintNumber() {
        return Objects.requireNonNullElse(this.sprintNumber, 0);
    }

    public void setSprintNumber(Integer sprintNumber) {
        this.sprintNumber = sprintNumber;
    }

    @Override
    public Object getTaskContainerID() {
        return this;
    }

    @Override
    public boolean sameValueAs(SprintID other) {
        return other != null && projectCode.equals(other.projectCode) &&
                sprintNumber.equals(other.sprintNumber);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        SprintID _sprintID = (SprintID) other;
        return sameValueAs(_sprintID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectCode, sprintNumber);
    }

}
