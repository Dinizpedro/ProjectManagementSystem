package switchfive.project.applicationServices.assemblers.implAssemblers;

import org.springframework.stereotype.Component;
import switchfive.project.dtos.ProjectDTO;
import switchfive.project.dtos.ProjectDTO_Domain;
import switchfive.project.applicationServices.assemblers.iAssemblers.IProjectAssembler;
import switchfive.project.domain.shared.valueObjects.*;

import java.text.ParseException;

/**
 * This java class contains the method toDomain, to map a ProjectDTO to a ProjectDtoDomain (a dto with value objects)
 */
@Component
public final class ImplProjectAssembler implements IProjectAssembler {

    ImplProjectAssembler() {
    }

    public ProjectDTO_Domain toDomain(final ProjectDTO dto) throws ParseException {

        ProjectDTO_Domain dtoResult = new ProjectDTO_Domain();

        ProjectCode code = ProjectCode.create(dto.getProjectCode());
        ProjectName name = ProjectName.create(dto.getProjectName());
        ProjectDescription description = ProjectDescription.create(dto.getProjectDescription());
        ProjectBudget budget = ProjectBudget.create(dto.getProjectBudget());
        ProjectBusinessSector business = ProjectBusinessSector.create(dto.getProjectBusinessSector());
        ProjectNumberOfPlannedSprints sprints = ProjectNumberOfPlannedSprints.create(dto.getProjectNumberOfPlannedSprints());
        ProjectSprintDuration sprintDuration = ProjectSprintDuration.create(dto.getProjectSprintDuration());
        Time dates = Time.create(dto.getStartDate(), dto.getEndDate());
        TypologyDescription typology = TypologyDescription.create(dto.getTypologyDescription());
        CustomerName customer = CustomerName.create(dto.getCustomerName());
        ProjectStatus projectStatus = ProjectStatus.Planned;
        Email managerEmail = Email.create(dto.getUserEmail());
        ResourceCostPerHour managerCost = ResourceCostPerHour.create(dto.getCostPerHour());
        ResourcePercentageOfAllocation managerAllocation = ResourcePercentageOfAllocation.create(dto.getPercentageOfAllocation());

        dtoResult.setProjectCode(code);
        dtoResult.setProjectName(name);
        dtoResult.setProjectDescription(description);
        dtoResult.setProjectBusinessSector(business);
        dtoResult.setProjectNumberOfPlannedSprints(sprints);
        dtoResult.setProjectSprintDuration(sprintDuration);
        dtoResult.setProjectBudget(budget);
        dtoResult.setDates(dates);
        dtoResult.setUserEmail(managerEmail);
        dtoResult.setCostPerHour(managerCost);
        dtoResult.setPercentageOfAllocation(managerAllocation);
        dtoResult.setCustomerName(customer);
        dtoResult.setTypologyDescription(typology);
        dtoResult.setStatus(projectStatus);

        return dtoResult;
    }
}
