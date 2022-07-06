package switchfive.project.mappers.mappersWS.implWsMappers;

import org.springframework.stereotype.Component;
import switchfive.project.interfaceAdapters.domainWS.ProjectWS;
import switchfive.project.dtos.ProjectDTO;
import switchfive.project.mappers.mappersWS.iWsMappers.IProjectWSMapper;

@Component
public class ImplProjectWSMapper implements IProjectWSMapper {

    public ProjectDTO toDTO(ProjectWS project) {
        ProjectDTO dto = new ProjectDTO();

        dto.setProjectCode(project.getProjectCode());
        dto.setProjectName(project.getProjectName());
        dto.setProjectDescription(project.getProjectDescription());
        dto.setProjectBusinessSector(project.getProjectBusinessSector());
        dto.setProjectNumberOfPlannedSprints(project.getProjectNumberOfPlannedSprints());
        dto.setProjectSprintDuration(project.getProjectSprintDuration());
        dto.setProjectBudget(project.getProjectBudget());
        dto.setStartDate(project.getStartDate());
        dto.setEndDate(project.getEndDate());
        dto.setTypologyDescription(project.getTypologyDescription());
        dto.setCustomerName(project.getCustomerName());
        dto.setStatus(project.getStatus());

        return dto;
    }
}
