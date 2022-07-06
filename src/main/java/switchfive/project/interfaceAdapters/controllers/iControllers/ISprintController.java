package switchfive.project.interfaceAdapters.controllers.iControllers;

import org.springframework.http.ResponseEntity;
import switchfive.project.dtos.SprintCreationDTO;

import java.text.ParseException;

public interface ISprintController {

    ResponseEntity<Object> createAndAddNewSprint(SprintCreationDTO sprintCreationDTO, String projectCode)
            throws ParseException;

}
