package switchfive.project.mappers.mappersWS.iWsMappers;

import switchfive.project.dtos.ProjectDTO;
import switchfive.project.interfaceAdapters.domainWS.ProjectWS;

public interface IProjectWSMapper {

    ProjectDTO toDTO(ProjectWS project);

}
