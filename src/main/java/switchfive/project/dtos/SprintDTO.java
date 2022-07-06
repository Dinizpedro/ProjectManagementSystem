package switchfive.project.dtos;

import org.springframework.hateoas.RepresentationModel;

/**
 * DTO Pattern.
 */
public class SprintDTO extends RepresentationModel<SprintDTO> {

    // Attributes with private access modifier
    private String projectCode;
    private Integer sprintNumber;
    private String startDate;
    private String endDate;
    private String description;
    private String status;

    // Empty public constructor
    public SprintDTO() {
    }

    // Standard getters and setters
    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public Integer getSprintNumber() {
        return sprintNumber;
    }

    public void setSprintNumber(Integer sprintNumber) {
        this.sprintNumber = sprintNumber;
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
