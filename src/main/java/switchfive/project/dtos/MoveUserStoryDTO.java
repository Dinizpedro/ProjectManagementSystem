package switchfive.project.dtos;

import org.springframework.hateoas.RepresentationModel;

public class MoveUserStoryDTO extends RepresentationModel<MoveUserStoryDTO> {
    private String projectCode;
    private String code;
    private Integer effort;
    private Integer priority;
    private String description;
    private Integer sprintID;
    private String status;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getEffort() {
        return effort;
    }

    public void setEffort(Integer effort) {
        this.effort = effort;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSprintID() {
        return sprintID;
    }

    public void setSprintID(Integer sprintID) {
        this.sprintID = sprintID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
