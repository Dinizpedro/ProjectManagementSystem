package switchfive.project.interfaceAdapters.controllers.implControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switchfive.project.interfaceAdapters.controllers.iControllers.ITaskController;
import switchfive.project.dtos.TaskCreationDTO;
import switchfive.project.dtos.TaskDTO;
import switchfive.project.applicationServices.appServices.iappServices.IAppTaskService;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ImplTaskController implements ITaskController {

    private final static String ERROR_MESSAGE = "Operation failed.";

    private final IAppTaskService iAppTaskService;

    @Autowired
    public ImplTaskController(IAppTaskService iAppTaskService) {
        this.iAppTaskService = iAppTaskService;
    }

    @PostMapping("/tasks")
    @ResponseBody
    public ResponseEntity<Object> createTask(@RequestBody TaskCreationDTO taskCreationDTO) throws ParseException {

        try {
            Optional<TaskDTO> DTOOpt = iAppTaskService.createAndSaveTask(taskCreationDTO);

            if (DTOOpt.isPresent()) {

                TaskDTO taskDTOOutput = DTOOpt.get();

                Link link = linkTo(methodOn(ImplTaskController.class).getTask(taskDTOOutput.getTaskCode(),
                        taskDTOOutput.getProjectCodeDto())).withSelfRel();
                taskDTOOutput.add(link);

                return new ResponseEntity<>(taskDTOOutput, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(ERROR_MESSAGE, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/tasks/{taskCode}/projectCode/{projectCode}")
    public ResponseEntity<Object> getTask(@PathVariable(value = "taskCode") String taskCode,
                                          @PathVariable(value = "projectCode") String projectCode) {

        try {
            Optional<TaskDTO> taskDTO = this.iAppTaskService.getTask(projectCode, taskCode);

            if (taskDTO.isPresent()) {
                TaskDTO task = taskDTO.get();

                Link link = linkTo(methodOn(ImplTaskController.class).
                        getTask(task.getTaskCode(),task.getProjectCodeDto()))
                        .withSelfRel();
                task.add(link);

                return new ResponseEntity<>(task, HttpStatus.OK);
            } else {

            return new ResponseEntity<>("Task doesn't exist!", HttpStatus.NOT_FOUND);
        } }
        catch (IllegalArgumentException | ParseException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/tasks")
    public ResponseEntity<Object> getTasks() {

        try {
            List<TaskDTO> taskDTOList = this.iAppTaskService.getTasks();

            for (TaskDTO taskDTO : taskDTOList) {
                Link link = linkTo(methodOn(ImplTaskController.class).getTask(taskDTO.getTaskCode(),
                        taskDTO.getProjectCodeDto())).withSelfRel();
                taskDTO.add(link);
            }

            return new ResponseEntity<>(taskDTOList, HttpStatus.OK);
        } catch (IllegalArgumentException | ParseException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
