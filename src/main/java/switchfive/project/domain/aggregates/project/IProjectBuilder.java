package switchfive.project.domain.aggregates.project;


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

public interface IProjectBuilder {

    Project build();

    ImplProjectBuilder setCode(final ProjectCode code);

    ImplProjectBuilder setName(final ProjectName name);

    ImplProjectBuilder setDescription(final ProjectDescription description);

    ImplProjectBuilder setBusinessSector(final ProjectBusinessSector businessSector);

    ImplProjectBuilder setTime(final Time dates);

    ImplProjectBuilder setSprints(final ProjectNumberOfPlannedSprints numberOfPlannedSprints);

    ImplProjectBuilder setSprintDuration(final ProjectSprintDuration sprintDuration);

    ImplProjectBuilder setBudget(final ProjectBudget budget);

    ImplProjectBuilder setTypology(final TypologyDescription typologyDescription);

    ImplProjectBuilder setCustomer(final CustomerName customerName);

    ImplProjectBuilder setStatus(final ProjectStatus status);

}
