package switchfive.project.applicationServices.iRepositories;

import switchfive.project.domain.aggregates.project.Project;
import switchfive.project.domain.shared.valueObjects.ProjectCode;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface IProjectRepository {

    Project save(Project entity) throws ParseException;

    Optional<Project> findByCode(ProjectCode code) throws ParseException;

    boolean projectExists(ProjectCode code);

    List<Project> findAll() throws ParseException;

}
