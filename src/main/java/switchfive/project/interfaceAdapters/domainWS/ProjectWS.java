package switchfive.project.interfaceAdapters.domainWS;

import java.util.Objects;

/**
 * Domain class for a Project that is fetched in an external API service
 */
public class ProjectWS {
    private String status;
    private String typologyDescription;
    private String customerName;
    private String projectCode;
    private String projectName;
    private String projectDescription;
    private String projectBusinessSector;
    private String startDate;
    private String endDate;
    private int projectNumberOfPlannedSprints;
    private double projectBudget;
    private int projectSprintDuration;

    public ProjectWS(String status, String typologyDescription, String customerName, String projectCode, String projectName, String projectDescription, String projectBusinessSector, String startDate,
                     String endDate, int projectNumberOfPlannedSprints, double projectBudget, int projectSprintDuration) {
        this.status = status;
        this.typologyDescription = typologyDescription;
        this.customerName = customerName;
        this.projectCode = projectCode;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectBusinessSector = projectBusinessSector;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectNumberOfPlannedSprints = projectNumberOfPlannedSprints;
        this.projectBudget = projectBudget;
        this.projectSprintDuration = projectSprintDuration;
    }

    public static ProjectWS create(String status, String typologyDescription, String customerName, String code, String name, String description, String businessSector, String startDate,
                                   String endDate, int numberOfPlannedSprints, double budget, int sprintDuration) {

        return new ProjectWS(status, typologyDescription, customerName, code, name, description, businessSector, startDate, endDate, numberOfPlannedSprints, budget, sprintDuration);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public int getProjectNumberOfPlannedSprints() {
        return projectNumberOfPlannedSprints;
    }

    public void setProjectNumberOfPlannedSprints(int projectNumberOfPlannedSprints) {
        this.projectNumberOfPlannedSprints = projectNumberOfPlannedSprints;
    }

    public double getProjectBudget() {
        return projectBudget;
    }

    public void setProjectBudget(double projectBudget) {
        this.projectBudget = projectBudget;
    }

    public int getProjectSprintDuration() {
        return projectSprintDuration;
    }

    public void setProjectSprintDuration(int projectSprintDuration) {
        this.projectSprintDuration = projectSprintDuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectWS projectWS = (ProjectWS) o;
        return Objects.equals(projectCode, projectWS.projectCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectCode);
    }
}
