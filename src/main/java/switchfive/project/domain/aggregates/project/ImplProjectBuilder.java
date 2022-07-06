package switchfive.project.domain.aggregates.project;

import org.springframework.stereotype.Component;
import switchfive.project.domain.shared.valueObjects.CustomerName;
import switchfive.project.domain.shared.valueObjects.ProjectBudget;
import switchfive.project.domain.shared.valueObjects.ProjectBusinessSector;
import switchfive.project.domain.shared.valueObjects.ProjectCode;
import switchfive.project.domain.shared.valueObjects.ProjectDescription;
import switchfive.project.domain.shared.valueObjects.ProjectName;
import switchfive.project.domain.shared.valueObjects.ProjectNumberOfPlannedSprints;
import switchfive.project.domain.shared.valueObjects.ProjectSprintDuration;
import switchfive.project.domain.shared.valueObjects.ProjectStatus;
import switchfive.project.domain.shared.valueObjects.Time;
import switchfive.project.domain.shared.valueObjects.TypologyDescription;

@Component
public class ImplProjectBuilder implements IProjectBuilder {

    private ProjectCode code;
    private ProjectName name;
    private ProjectDescription description;
    private ProjectBusinessSector businessSector;
    private Time dates;
    private ProjectNumberOfPlannedSprints numberOfPlannedSprints;
    private ProjectSprintDuration sprintDuration;
    private ProjectBudget budget;
    private TypologyDescription typologyDescription;
    private CustomerName customerName;
    private ProjectStatus status;

    public ImplProjectBuilder() {
    }

    public Project build() {
        Project newProject = new Project();

        newProject.addCode(this.code);
        newProject.addName(this.name);
        newProject.addDescription(this.description);
        newProject.addBusinessSector(this.businessSector);
        newProject.addDates(this.dates);
        newProject.addNumberOfPlannedSprints(this.numberOfPlannedSprints);
        newProject.addSprintDuration(this.sprintDuration);
        newProject.addBudget(this.budget);
        newProject.addTypologyDescription(this.typologyDescription);
        newProject.addCustomer(this.customerName);
        newProject.addStatus(this.status);

        return newProject;
    }

    public ImplProjectBuilder setCode(final ProjectCode code) {
        this.code = code;
        return this;
    }

    public ImplProjectBuilder setName(final ProjectName name) {
        this.name = name;
        return this;
    }

    public ImplProjectBuilder setDescription(final ProjectDescription description) {
        this.description = description;
        return this;
    }

    public ImplProjectBuilder setBusinessSector(final ProjectBusinessSector businessSector) {
        this.businessSector = businessSector;
        return this;
    }

    public ImplProjectBuilder setTime(final Time dates) {
        this.dates = dates;
        return this;
    }

    public ImplProjectBuilder setSprints(final ProjectNumberOfPlannedSprints numberOfPlannedSprints) {
        this.numberOfPlannedSprints = numberOfPlannedSprints;
        return this;
    }

    public ImplProjectBuilder setSprintDuration(final ProjectSprintDuration sprintDuration) {
        this.sprintDuration = sprintDuration;
        return this;
    }

    public ImplProjectBuilder setBudget(final ProjectBudget budget) {
        this.budget = budget;
        return this;
    }

    public ImplProjectBuilder setTypology(final TypologyDescription typologyDescription) {
        this.typologyDescription = typologyDescription;
        return this;
    }

    public ImplProjectBuilder setCustomer(final CustomerName customerName) {
        this.customerName = customerName;
        return this;
    }

    public ImplProjectBuilder setStatus(final ProjectStatus status) {
        this.status = status;
        return this;
    }
}
