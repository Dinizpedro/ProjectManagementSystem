package switchfive.project.applicationServices.appServices.iappServices;

import switchfive.project.dtos.SprintCreationDTO;
import switchfive.project.dtos.SprintDTO;
import switchfive.project.domain.shared.valueObjects.SprintID;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface IAppSprintService {

    SprintDTO createAndSaveSprint(SprintCreationDTO sprintCreationDTO,
                                  String projectCode)
            throws ParseException;

    Optional<SprintDTO> getSprintBySprintId(SprintID sprintID)
            throws ParseException;

    Optional<List<SprintDTO>> getSprintsByProjectCode(String projectCode) throws ParseException;
}
