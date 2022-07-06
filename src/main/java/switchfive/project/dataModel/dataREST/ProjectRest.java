package switchfive.project.dataModel.dataREST;

import java.util.Objects;

/**
 * DTO received from external REST API.
 */
public class ProjectRest {

    private String code;
    private String projectName;
    private String description;
    private String customer;
    private String businessSector;
    private String typo;
    private Integer numberOfSprints;
    private Double budget;
    private String status;
    private String startDate;
    private String endDate;
    private Integer sprintDuration;

    public ProjectRest() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getBusinessSector() {
        return businessSector;
    }

    public void setBusinessSector(String businessSector) {
        this.businessSector = businessSector;
    }

    public String getTypo() {
        return typo;
    }

    public void setTypo(String typo) {
        this.typo = typo;
    }

    public Integer getNumberOfSprints() {
        return numberOfSprints;
    }

    public void setNumberOfSprints(Integer numberOfSprints) {
        this.numberOfSprints = numberOfSprints;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Integer getSprintDuration() {
        return sprintDuration;
    }

    public void setSprintDuration(Integer sprintDuration) {
        this.sprintDuration = sprintDuration;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectRest that = (ProjectRest) o;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
