package switchfive.project.mappers.mappersApp.implMappers;

import org.springframework.stereotype.Component;
import switchfive.project.dtos.ProjectDTO;
import switchfive.project.mappers.mappersApp.iMappers.IProjectMapper;
import switchfive.project.domain.aggregates.project.Project;
import switchfive.project.domain.aggregates.resource.Resource;

@Component
public final class ImplProjectMapper implements IProjectMapper {

    public ImplProjectMapper() {
    }

    public ProjectDTO toDTO(final Project project) {
        ProjectDTO dto = new ProjectDTO();

        dto.setProjectCode(project.getCode());
        dto.setProjectName(project.getName());
        dto.setProjectDescription(project.getDescription());
        dto.setProjectBusinessSector(project.getBusinessSector());
        dto.setProjectNumberOfPlannedSprints(project.getNumberOfPlannedSprints());
        dto.setProjectSprintDuration(project.getSprintDuration());
        dto.setProjectBudget(project.getBudget());
        dto.setStartDate(project.getDates().startDate);
        dto.setEndDate(project.getDates().endDate);
        dto.setTypologyDescription(project.getTypologyDescription());
        dto.setCustomerName(project.getCustomerName());
        dto.setStatus(project.getStatus());

        return dto;
    }

    public ProjectDTO toDTO(final Project newProject,
                            final Resource newResource) {

        ProjectDTO dto = new ProjectDTO();

        dto.setProjectCode(newProject.getCode());
        dto.setProjectName(newProject.getName());
        dto.setProjectDescription(newProject.getDescription());
        dto.setProjectBusinessSector(newProject.getBusinessSector());
        dto.setProjectNumberOfPlannedSprints(newProject.getNumberOfPlannedSprints());
        dto.setProjectSprintDuration(newProject.getSprintDuration());
        dto.setProjectBudget(newProject.getBudget());
        dto.setStartDate(newProject.getDates().startDate);
        dto.setEndDate(newProject.getDates().endDate);
        dto.setUserEmail(newResource.getUserEmail());
        dto.setCostPerHour(newResource.getCostPerHour());
        dto.setPercentageOfAllocation(newResource.getAllocation());
        dto.setCustomerName(newProject.getCustomerName());
        dto.setTypologyDescription(newProject.getTypologyDescription());
        dto.setStatus(newProject.getStatus());

        return dto;
    }

}
