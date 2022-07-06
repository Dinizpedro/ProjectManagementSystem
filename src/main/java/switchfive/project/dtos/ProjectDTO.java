package switchfive.project.dtos;

import org.springframework.hateoas.RepresentationModel;

/**
 * This dto carry data between route and controller.
 */
public class ProjectDTO extends RepresentationModel<ProjectDTO> {

    private String projectCode;
    private String projectName;
    private String projectDescription;
    private String projectBusinessSector;
    private int projectNumberOfPlannedSprints;
    private int projectSprintDuration;
    private double projectBudget;
    private String startDate;
    private String endDate;
    private String typologyDescription;
    private String customerName;
    private String userEmail;
    private double costPerHour;
    private double percentageOfAllocation;
    private String status;


    public ProjectDTO() {
    }


    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

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

    public int getProjectNumberOfPlannedSprints() {
        return projectNumberOfPlannedSprints;
    }

    public void setProjectNumberOfPlannedSprints(int projectNumberOfPlannedSprints) {
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public double getCostPerHour() {
        return costPerHour;
    }

    public void setCostPerHour(double costPerHour) {
        this.costPerHour = costPerHour;
    }

    public double getPercentageOfAllocation() {
        return percentageOfAllocation;
    }

    public void setPercentageOfAllocation(double percentageOfAllocation) {
        this.percentageOfAllocation = percentageOfAllocation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
