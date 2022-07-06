package switchfive.project.mappers.mappersApp.iMappers;

import switchfive.project.dtos.ProjectDTO;
import switchfive.project.domain.aggregates.project.Project;
import switchfive.project.domain.aggregates.resource.Resource;

public interface IProjectMapper {

    ProjectDTO toDTO(Project project);

    ProjectDTO toDTO(Project newProject, Resource newResource);

}
