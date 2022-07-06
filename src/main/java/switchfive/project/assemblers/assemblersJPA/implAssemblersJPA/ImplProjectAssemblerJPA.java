package switchfive.project.assemblers.assemblersJPA.implAssemblersJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switchfive.project.assemblers.assemblersJPA.iAssemblersJPA.IProjectAssemblerJPA;
import switchfive.project.dataModel.dataJPA.ProjectJPA;
import switchfive.project.domain.aggregates.project.IProjectBuilder;
import switchfive.project.domain.aggregates.project.Project;
import switchfive.project.domain.shared.valueObjects.*;

import java.text.ParseException;

/**
 * This java class implements the methods needed to map a ProjectJPA to Project (domain).
 */
@Component
public class ImplProjectAssemblerJPA implements IProjectAssemblerJPA {

    IProjectBuilder builder;

    @Autowired
    public ImplProjectAssemblerJPA(IProjectBuilder builder) {
        this.builder = builder;
    }

    public Project toDomain(final ProjectJPA projectJPA) throws ParseException {

        ProjectCode code = this.getProjectCodeFromProjectJPA(projectJPA);
        ProjectName name = this.getProjectNameFromProjectJPA(projectJPA);
        ProjectDescription description = this.getDescriptionFromProjectJPA(projectJPA);
        CustomerName customerID = this.getCustomerNameFromProjectJPA(projectJPA);
        TypologyDescription typologyDescription = this.getTypologyDescriptionFromProjectJPA(projectJPA);
        Time dates = this.getDatesFromProjectJPA(projectJPA);
        ProjectSprintDuration sprintDuration = this.getSprintDurationFromProjectJPA(projectJPA);
        ProjectNumberOfPlannedSprints numberOfSprints = this.getNumberOfSprintsFromProjectJPA(projectJPA);
        ProjectBudget budget = this.getBudgetFromProjectJPA(projectJPA);
        ProjectBusinessSector businessSector = this.getBusinessSectorFromProjectJPA(projectJPA);
        ProjectStatus status = this.getProjectStatusFromProjectJPA(projectJPA);

        return this.builder.setCode(code)
                .setName(name)
                .setDescription(description)
                .setBusinessSector(businessSector)
                .setTime(dates)
                .setSprints(numberOfSprints)
                .setSprintDuration(sprintDuration)
                .setBudget(budget)
                .setTypology(typologyDescription)
                .setCustomer(customerID)
                .setStatus(status)
                .build();
    }

    public TypologyDescription getTypologyDescriptionFromProjectJPA(ProjectJPA projectJPA) {
        return TypologyDescription.create(projectJPA.getTypologyDescription());
    }

    public CustomerName getCustomerNameFromProjectJPA(ProjectJPA projectJPA) {
        return CustomerName.create(projectJPA.getCustomerName());
    }

    public ProjectCode getProjectCodeFromProjectJPA(ProjectJPA projectJPA) {
        return ProjectCode.create(projectJPA.getCode());
    }

    public ProjectName getProjectNameFromProjectJPA(final ProjectJPA projectJPA) {
        return ProjectName.create(projectJPA.getName());
    }

    public ProjectDescription getDescriptionFromProjectJPA(final ProjectJPA projectJPA) {
        return ProjectDescription.create(projectJPA.getDescription());
    }

    public ProjectBusinessSector getBusinessSectorFromProjectJPA(final ProjectJPA projectJPA) {
        return ProjectBusinessSector.create(projectJPA.getBusinessSector());
    }

    public Time getDatesFromProjectJPA(final ProjectJPA projectJPA) throws ParseException {
        return Time.create(projectJPA.getStartDate(), projectJPA.getEndDate());
    }

    public ProjectNumberOfPlannedSprints getNumberOfSprintsFromProjectJPA(final ProjectJPA projectJPA) {
        return ProjectNumberOfPlannedSprints.create(projectJPA.getNumberOfPlannedSprints());
    }

    public ProjectBudget getBudgetFromProjectJPA(final ProjectJPA projectJPA) {
        return ProjectBudget.create(projectJPA.getBudget());
    }

    public ProjectSprintDuration getSprintDurationFromProjectJPA(final ProjectJPA projectJPA) {
        return ProjectSprintDuration.create(projectJPA.getSprintDuration());
    }

    public ProjectStatus getProjectStatusFromProjectJPA(final ProjectJPA projectJPA) {
        return ProjectStatus.valueOf(projectJPA.getStatus());
    }
}
