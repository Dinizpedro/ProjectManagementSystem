package switchfive.project.dataModel.dataJPA;

import switchfive.project.domain.aggregates.sprint.Sprint;
import switchfive.project.domain.shared.valueObjects.SprintID;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sprints")
public class SprintJPA {
    @EmbeddedId
    private SprintID sprintID;
    private String startDate;
    private String endDate;
    private String description;
    private String status;

    public SprintJPA() {
    }

    public SprintJPA(final Sprint sprint) {
        this.sprintID = sprint.getSprintID();
        this.startDate = sprint.getDates().startDate;
        this.endDate = sprint.getDates().endDate;
        this.description = sprint.getDescription();
        this.status = sprint.getStatus();
    }

    public SprintID getSprintID() {
        return sprintID;
    }

    public void setSprintID(
            SprintID sprintID) {
        this.sprintID = sprintID;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
