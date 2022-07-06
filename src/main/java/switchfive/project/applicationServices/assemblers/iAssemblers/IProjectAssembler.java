package switchfive.project.applicationServices.assemblers.iAssemblers;

import switchfive.project.dtos.ProjectDTO;
import switchfive.project.dtos.ProjectDTO_Domain;

import java.text.ParseException;

public interface IProjectAssembler {

    ProjectDTO_Domain toDomain(final ProjectDTO dto) throws ParseException;
}
