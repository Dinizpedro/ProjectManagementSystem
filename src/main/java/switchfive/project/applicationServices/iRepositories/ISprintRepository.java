package switchfive.project.applicationServices.iRepositories;

import switchfive.project.domain.aggregates.sprint.Sprint;
import switchfive.project.domain.shared.valueObjects.ProjectCode;
import switchfive.project.domain.shared.valueObjects.SprintID;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface ISprintRepository {

    Optional<Sprint> findBySprintID(SprintID sprintID) throws ParseException;

    Sprint saveSprint(Sprint sprint) throws ParseException;

    Optional<List<Sprint>> findAllSprintsByProjectCode(ProjectCode projectCode) throws ParseException;

    long countSprintsByProjectCode(ProjectCode code);

}
