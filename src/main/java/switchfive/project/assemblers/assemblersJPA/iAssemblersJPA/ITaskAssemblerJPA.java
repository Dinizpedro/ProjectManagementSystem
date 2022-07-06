package switchfive.project.assemblers.assemblersJPA.iAssemblersJPA;

import switchfive.project.dataModel.dataJPA.TaskJPA;
import switchfive.project.domain.aggregates.task.Task;

import java.text.ParseException;

public interface ITaskAssemblerJPA {

    Task toDomain(TaskJPA taskJPA) throws ParseException;

    TaskJPA toData(Task task);

}
