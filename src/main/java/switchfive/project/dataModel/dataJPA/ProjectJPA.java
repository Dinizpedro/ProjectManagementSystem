package switchfive.project.dataModel.dataJPA;

import switchfive.project.domain.aggregates.project.Project;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class is a POJO representing data that can be persisted to the database.
 */
@Entity
@Table(name = "projects")
public class ProjectJPA implements JPA {
    @Id
    private String code;
    private String typologyDescription;
    private String customerName;
    private String name;
    private String description;
    private String businessSector;
    private String startDate;
    private String endDate;
    private Integer numberOfPlannedSprints;
    private Double budget;
    private Integer sprintDuration;
    private String status;


    public ProjectJPA() {
    }

    /**
     * Notes: from Persistence layer we can access to Domain.
     *
     * @param project from domain.
     */
    public ProjectJPA(final Project project) {
        this.code = project.getCode();
        this.typologyDescription = project.getTypologyDescription();
        this.customerName = project.getCustomerName();
        this.name = project.getName();
        this.description = project.getDescription();
        this.businessSector = project.getBusinessSector();
        this.startDate = project.getDates().startDate;
        this.endDate = project.getDates().endDate;
        this.numberOfPlannedSprints = project.getNumberOfPlannedSprints();
        this.budget = project.getBudget();
        this.sprintDuration = project.getSprintDuration();
        this.status = project.getStatus();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTypologyDescription() {
        return typologyDescription;
    }

    public void setTypologyDescription(String typologyID) {
        this.typologyDescription = typologyID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerID) {
        this.customerName = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBusinessSector() {
        return businessSector;
    }

    public void setBusinessSector(String businessSector) {
        this.businessSector = businessSector;
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

    public Integer getNumberOfPlannedSprints() {
        return numberOfPlannedSprints;
    }

    public void setNumberOfPlannedSprints(Integer numberOfPlannedSprints) {
        this.numberOfPlannedSprints = numberOfPlannedSprints;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public Integer getSprintDuration() {
        return sprintDuration;
    }

    public void setSprintDuration(Integer sprintDuration) {
        this.sprintDuration = sprintDuration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
