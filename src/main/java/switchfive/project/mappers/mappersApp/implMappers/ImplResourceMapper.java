package switchfive.project.mappers.mappersApp.implMappers;

import org.springframework.stereotype.Component;
import switchfive.project.dtos.AllocatedProjectDTO;
import switchfive.project.dtos.ResourceDTO;
import switchfive.project.dtos.TimeDTO;
import switchfive.project.mappers.mappersApp.iMappers.IResourceMapper;
import switchfive.project.domain.aggregates.resource.Resource;

@Component
public class ImplResourceMapper implements IResourceMapper {

    public ResourceDTO toDto(Resource resource) {

        String resourceID = resource.getResourceID();
        String userID = resource.getUserEmail();
        String projectCode = resource.getProjectCode();
        String startDate = resource.getStartDate();
        String endDate = resource.getEndDate();
        TimeDTO dates = new TimeDTO(startDate, endDate);
        double costPerHour = resource.getCostPerHour();
        double allocation = resource.getAllocation();
        String role = resource.getRole();

        ResourceDTO resourceDTO = new ResourceDTO(resourceID, userID, projectCode, dates
                , costPerHour, allocation, role);

        return resourceDTO;

    }

    public AllocatedProjectDTO toAllocatedProjectDTO (Resource resource, String projectName){

        String role = resource.getRole();
        String projectCode = resource.getProjectCode();

        AllocatedProjectDTO allocatedProjectDTO =
                new AllocatedProjectDTO();
        allocatedProjectDTO.setProjectName(projectName);
        allocatedProjectDTO.setRole(role);
        allocatedProjectDTO.setProjectCode(projectCode);

        return  allocatedProjectDTO;
    }
}
