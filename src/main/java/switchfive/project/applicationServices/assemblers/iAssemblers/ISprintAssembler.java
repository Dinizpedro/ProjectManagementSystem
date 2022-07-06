package switchfive.project.applicationServices.assemblers.iAssemblers;

import switchfive.project.dtos.SprintDTO;
import switchfive.project.domain.aggregates.sprint.Sprint;

public interface ISprintAssembler {
    SprintDTO toDTO(Sprint sprint);
}
