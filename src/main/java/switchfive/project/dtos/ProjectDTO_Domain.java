package switchfive.project.dtos;

import org.springframework.hateoas.RepresentationModel;
import switchfive.project.domain.shared.valueObjects.*;

/**
 * This dto carry domain value objects. It's to be used in domain.
 */
public class ProjectDTO_Domain extends RepresentationModel<ProjectDTO_Domain> {

    private ProjectCode projectCode;
    private ProjectName projectName;
    private ProjectDescription projectDescription;
    private ProjectBusinessSector projectBusinessSector;
    private ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints;
    private ProjectSprintDuration projectSprintDuration;
    private ProjectBudget projectBudget;
    private Time dates;
    private TypologyDescription typologyDescription;
    private CustomerName customerName;
    private Email userEmail;
    private ResourceCostPerHour costPerHour;
    private ResourcePercentageOfAllocation percentageOfAllocation;
    private ProjectStatus status;

    public ProjectDTO_Domain() {
    }

    public ProjectCode getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(ProjectCode projectCode) {
        this.projectCode = projectCode;
    }

    public ProjectName getProjectName() {
        return projectName;
    }

    public void setProjectName(ProjectName projectName) {
        this.projectName = projectName;
    }

    public ProjectDescription getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(ProjectDescription projectDescription) {
        this.projectDescription = projectDescription;
    }

    public ProjectBusinessSector getProjectBusinessSector() {
        return projectBusinessSector;
    }

    public void setProjectBusinessSector(ProjectBusinessSector projectBusinessSector) {
        this.projectBusinessSector = projectBusinessSector;
    }

    public ProjectNumberOfPlannedSprints getProjectNumberOfPlannedSprints() {
        return projectNumberOfPlannedSprints;
    }

    public void setProjectNumberOfPlannedSprints(ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints) {
        this.projectNumberOfPlannedSprints = projectNumberOfPlannedSprints;
    }

    public ProjectSprintDuration getProjectSprintDuration() {
        return projectSprintDuration;
    }

    public void setProjectSprintDuration(ProjectSprintDuration projectSprintDuration) {
        this.projectSprintDuration = projectSprintDuration;
    }

    public ProjectBudget getProjectBudget() {
        return projectBudget;
    }

    public void setProjectBudget(ProjectBudget projectBudget) {
        this.projectBudget = projectBudget;
    }

    public Time getDates() {
        return dates;
    }

    public void setDates(Time dates) {
        this.dates = dates;
    }

    public TypologyDescription getTypologyDescription() {
        return typologyDescription;
    }

    public void setTypologyDescription(TypologyDescription typologyDescription) {
        this.typologyDescription = typologyDescription;
    }

    public CustomerName getCustomerName() {
        return customerName;
    }

    public void setCustomerName(CustomerName customerName) {
        this.customerName = customerName;
    }

    public Email getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(Email userEmail) {
        this.userEmail = userEmail;
    }

    public ResourceCostPerHour getCostPerHour() {
        return costPerHour;
    }

    public void setCostPerHour(ResourceCostPerHour costPerHour) {
        this.costPerHour = costPerHour;
    }

    public ResourcePercentageOfAllocation getPercentageOfAllocation() {
        return percentageOfAllocation;
    }

    public void setPercentageOfAllocation(ResourcePercentageOfAllocation percentageOfAllocation) {
        this.percentageOfAllocation = percentageOfAllocation;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }
}
