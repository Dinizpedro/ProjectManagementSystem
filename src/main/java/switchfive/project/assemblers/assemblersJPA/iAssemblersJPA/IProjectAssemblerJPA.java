package switchfive.project.assemblers.assemblersJPA.iAssemblersJPA;

import switchfive.project.dataModel.dataJPA.ProjectJPA;
import switchfive.project.domain.aggregates.project.Project;
import switchfive.project.domain.shared.valueObjects.*;

import java.text.ParseException;

/**
 * Interface with the methods needed to map a ProjectJPA to Project (domain).
 */
public interface IProjectAssemblerJPA {

    TypologyDescription getTypologyDescriptionFromProjectJPA(ProjectJPA projectJPA);

    CustomerName getCustomerNameFromProjectJPA(ProjectJPA projectJPA);

    ProjectCode getProjectCodeFromProjectJPA(ProjectJPA projectJPA);

    ProjectName getProjectNameFromProjectJPA(ProjectJPA projectJPA);

    ProjectDescription getDescriptionFromProjectJPA(ProjectJPA projectJPA);

    ProjectBusinessSector getBusinessSectorFromProjectJPA(ProjectJPA projectJPA);

    Time getDatesFromProjectJPA(ProjectJPA projectJPA) throws ParseException;

    ProjectNumberOfPlannedSprints getNumberOfSprintsFromProjectJPA(ProjectJPA projectJPA);

    ProjectBudget getBudgetFromProjectJPA(ProjectJPA projectJPA);

    ProjectSprintDuration getSprintDurationFromProjectJPA(ProjectJPA projectJPA);

    Project toDomain(ProjectJPA projectJPA) throws ParseException;

}
