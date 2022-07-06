package switchfive.project.domain.aggregates.project;

import switchfive.project.dtos.TimeDTO;
import switchfive.project.domain.shared.dddTypes.AggregateRoot;
import switchfive.project.domain.shared.valueObjects.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Project implements AggregateRoot<Project> {
    private ProjectStatus status;
    private TypologyDescription typologyDescription;
    private CustomerName customerName;
    private ProjectCode code;
    private ProjectName name;
    private ProjectDescription description;
    private ProjectBusinessSector businessSector;
    private Time dates;
    private ProjectNumberOfPlannedSprints numberOfPlannedSprints;
    private ProjectBudget budget;
    private ProjectSprintDuration sprintDuration;

    protected Project() {
    }

    public Project(ProjectCode code, ProjectName name, ProjectDescription description, CustomerName customerName) {
        this.customerName = customerName;
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public void addCode(ProjectCode code) {
        this.code = code;
    }

    public void addTypologyDescription(
            final TypologyDescription typologyInput) {
        this.typologyDescription = typologyInput;
    }

    public void addBusinessSector(
            final ProjectBusinessSector businessSectorInput) {
        this.businessSector = businessSectorInput;
    }

    public void addDates(final Time datesInput) {
        this.dates = datesInput;
    }

    public void addNumberOfPlannedSprints(
            final ProjectNumberOfPlannedSprints plannedSprintsInput) {
        this.numberOfPlannedSprints = plannedSprintsInput;
    }

    public void addBudget(final ProjectBudget budgetInput) {
        this.budget = budgetInput;
    }

    public void addSprintDuration(
            final ProjectSprintDuration sprintDurationInput) {
        this.sprintDuration = sprintDurationInput;
    }

    public void addName(final ProjectName nameInput) {
        this.name = nameInput;
    }

    public void addDescription(final ProjectDescription descriptionInput) {
        this.description = descriptionInput;
    }

    public void addCustomer(final CustomerName customerNameInput) {
        this.customerName = customerNameInput;
    }

    public void addStatus(final ProjectStatus projectStatusInput) {this.status = projectStatusInput;}

    public boolean areDatesWithinProjectDates(String startDateInput,
                                              String endDateInput)
            throws ParseException {
        Time resourceTime = Time.create(startDateInput, endDateInput);

        return this.dates.areInputDatesInsideTimeDates(resourceTime);
    }

    public boolean isProjectClosed() {
        return this.status.equals(ProjectStatus.Closed);
    }

    public String getTypologyDescription() {
        return typologyDescription.getDescription();
    }

    public String getCustomerName() {
        return customerName.getName();
    }

    public String getCode() {
        return this.code.getCode();
    }

    public String getName() {
        return this.name.getName();
    }

    public String getDescription() {
        return description.getDescription();
    }

    public String getBusinessSector() {
        return businessSector.getBusinessSector();
    }

    public TimeDTO getDates() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date startDate = this.dates.getStartDate();
        Date endDate = this.dates.getEndDate();

        String startDateString = dateFormat.format(startDate);
        String endDateString = dateFormat.format(endDate);

        return new TimeDTO(startDateString, endDateString);
    }

    public int getNumberOfPlannedSprints() {
        return numberOfPlannedSprints.getNumber();
    }

    public double getBudget() {
        return budget.getBudget();
    }

    public int getSprintDuration() {
        return sprintDuration.getDuration();
    }

    public String getStatus() {
        return status.toString();
    }

    /**
     * @param other The other entity.
     * @return true if instance «other» have the same identity as this;
     * otherwise, returns false.
     */
    @Override
    public boolean sameIdentityAs(final Project other) {
        return other != null && code.sameValueAs(other.code);
    }

    /**
     * @param other object to compare
     * @return true if compared objects are equals; otherwise, returns false.
     */
    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Project)) {
            return false;
        }
        Project that = (Project) other;
        return sameIdentityAs(that);
    }

    /**
     * @return new hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}

