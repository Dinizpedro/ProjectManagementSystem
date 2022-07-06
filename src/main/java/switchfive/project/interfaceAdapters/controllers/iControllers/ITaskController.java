package switchfive.project.interfaceAdapters.controllers.iControllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import switchfive.project.dtos.TaskCreationDTO;

import java.text.ParseException;

public interface ITaskController {

    ResponseEntity<Object> createTask(@RequestBody TaskCreationDTO taskCreationDTO) throws ParseException;

    ResponseEntity<Object> getTask(@PathVariable(value = "taskCode") String taskCode,
                                   @PathVariable(value = "projectCode") String projectCode);

    ResponseEntity<Object> getTasks();
}
