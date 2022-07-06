package switchfive.project.dtos;

import org.springframework.hateoas.RepresentationModel;

/**
 * DTO pattern.
 */
public class UpdateProjectDTO extends RepresentationModel<ProjectDTO> {

    /**
     * Attributes with private access modifier
     */
    private String projectName;
    private String projectDescription;
    private String projectBusinessSector;
    private String customerName;
    private int projectNumberOfPlannedSprints;
    private int projectSprintDuration;
    private double projectBudget;
    private String startDate;
    private String endDate;
    private String typologyDescription;
    private String status;


    /**
     * Empty public constructor
     */
    public UpdateProjectDTO() {
    }

    /**
     * Standard getters and setters
     */
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getProjectBusinessSector() {
        return projectBusinessSector;
    }

    public void setProjectBusinessSector(String projectBusinessSector) {
        this.projectBusinessSector = projectBusinessSector;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getProjectNumberOfPlannedSprints() {
        return projectNumberOfPlannedSprints;
    }

    public void setProjectNumberOfPlannedSprints(
            int projectNumberOfPlannedSprints) {
        this.projectNumberOfPlannedSprints = projectNumberOfPlannedSprints;
    }

    public int getProjectSprintDuration() {
        return projectSprintDuration;
    }

    public void setProjectSprintDuration(int projectSprintDuration) {
        this.projectSprintDuration = projectSprintDuration;
    }

    public double getProjectBudget() {
        return projectBudget;
    }

    public void setProjectBudget(double projectBudget) {
        this.projectBudget = projectBudget;
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

    public String getTypologyDescription() {
        return typologyDescription;
    }

    public void setTypologyDescription(String typologyDescription) {
        this.typologyDescription = typologyDescription;
    }


    public String getStatus() { return status;}

    public void setStatus(String status) { this.status = status;}


}
