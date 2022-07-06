package switchfive.project.mappers.mappersApp.iMappers;

import switchfive.project.dtos.AllocatedProjectDTO;
import switchfive.project.dtos.ResourceDTO;
import switchfive.project.domain.aggregates.resource.Resource;

public interface IResourceMapper {
    ResourceDTO toDto(Resource resource);
    AllocatedProjectDTO toAllocatedProjectDTO(Resource resourece, String projectName);
}
