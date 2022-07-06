package switchfive.project.assemblers.assemblersJPA.iAssemblersJPA;

import switchfive.project.dataModel.dataJPA.SprintJPA;
import switchfive.project.domain.aggregates.sprint.Sprint;

import java.text.ParseException;

public interface ISprintAssemblerJPA {

    Sprint toDomain(SprintJPA sprintJPA) throws ParseException;

}
