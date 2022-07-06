package switchfive.project.interfaceAdapters.controllers.implControllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import switchfive.project.dtos.TaskCreationDTO;
import switchfive.project.dtos.TaskDTO;
import switchfive.project.applicationServices.appServices.implAppServices.ImplAppTaskService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@ExtendWith(MockitoExtension.class)
class ImplTaskControllerTest {

    private final static String ERROR_MESSAGE = "Operation failed.";

    @Mock
    ImplAppTaskService taskService;

    @InjectMocks
    ImplTaskController taskController;

    @Test
    @DisplayName("Must return a Response Entity of an HTTPStatus.Ok when Task created successfully.")
    void createTaskSuccessfullyWithHTTPStatusOk() throws ParseException {

        //------ARRANGE---------
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTaskCode("T1");

        TaskCreationDTO taskCreationDTO = new TaskCreationDTO();

        Optional<TaskDTO> optionalTaskDTO = Optional.of(taskDTO);

        when(taskService.createAndSaveTask(taskCreationDTO)).thenReturn(optionalTaskDTO);

        taskDTO.add(linkTo(methodOn(ImplTaskController.class).getTask(taskDTO.getTaskCode(),
                taskDTO.getProjectCodeDto())).withSelfRel());

        // ----ACT----

        ResponseEntity<Object> expected = new ResponseEntity<>(taskDTO, HttpStatus.OK);
        ResponseEntity<Object> actual = taskController.createTask(taskCreationDTO);


        // ----ASSERT----

        assertEquals(expected,actual);

    }

    @Test
    @DisplayName("Must return a Response Entity of an HTTPStatus.BAD_REQUEST and message when Task created unsuccessfully.")
    void createTaskUnsuccessfullyWithHTTPSStatusBAD_REQUESTAndErrorMessage() throws ParseException {

        //------ARRANGE---------
        TaskCreationDTO taskCreationDTO = new TaskCreationDTO();

        Optional<TaskDTO> optionalTaskDTO = Optional.empty();

        when(taskService.createAndSaveTask(taskCreationDTO)).thenReturn(optionalTaskDTO);

        // ----ACT----
        ResponseEntity<Object> expected = new ResponseEntity<>(ERROR_MESSAGE, HttpStatus.BAD_REQUEST);
        ResponseEntity<Object> actual = taskController.createTask(taskCreationDTO);

        // ----ASSERT----

        assertEquals(expected,actual);

    }



    @Test
    @DisplayName("Must return a Response Entity of an HTTPStatus.BAD_REQUEST throwing exception" +
            " when Task created unsuccessfully.")
    void createTaskUnsuccessfullyWithException() throws ParseException {

        //------ARRANGE---------
        IllegalArgumentException e = new IllegalArgumentException();

        TaskCreationDTO taskCreationDTO = new TaskCreationDTO();

        when(taskService.createAndSaveTask(taskCreationDTO)).thenThrow(e);

        // ----ACT----
        ResponseEntity<Object> expected = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        ResponseEntity<Object> actual = taskController.createTask(taskCreationDTO);

        // ----ASSERT----

        assertEquals(expected,actual);

    }


    @Test
    @DisplayName("Must return a Response Entity of an HTTPStatus.Ok when Task is successfully found.")
    void getTaskSuccessfullyWithResponseHTTPStatusOK() throws ParseException {

        //------ARRANGE---------
        String taskCode = "T1";
        String projectCode = "A0001";

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTaskCode(taskCode);
        taskDTO.setProjectCodeDto(projectCode);

        Optional<TaskDTO> optionalTaskDTO = Optional.of(taskDTO);

        when(taskService.getTask(projectCode,taskCode)).thenReturn(optionalTaskDTO);

        taskDTO.add(linkTo(methodOn(ImplTaskController.class).getTask(taskDTO.getTaskCode(),
                taskDTO.getProjectCodeDto())).withSelfRel());

        // ----ACT----

        ResponseEntity<Object> expected = new ResponseEntity<>(taskDTO, HttpStatus.OK);
        ResponseEntity<Object> actual = taskController.getTask(taskCode,projectCode);


        // ----ASSERT----

        assertEquals(expected,actual);

    }


    @Test
    @DisplayName("Must return a Response Entity of an HTTPStatus.NOT_FOUND when Task is not successfully found.")
    void getTaskUnsuccessfullyWithResponseHTTPStatusNotFound() throws ParseException {

        //------ARRANGE---------
        String taskCode = "T1";
        String projectCode = "A0001";

        TaskDTO taskDTO = new TaskDTO();

        Optional<TaskDTO> optionalTaskDTO = Optional.empty();

        when(taskService.getTask(projectCode,taskCode)).thenReturn(optionalTaskDTO);

        taskDTO.add(linkTo(methodOn(ImplTaskController.class).getTask(taskDTO.getTaskCode(),
                taskDTO.getProjectCodeDto())).withSelfRel());

        // ----ACT----

        ResponseEntity<Object> expected = new ResponseEntity<>("Task doesn't exist!", HttpStatus.NOT_FOUND);
        ResponseEntity<Object> actual = taskController.getTask(taskCode,projectCode);


        // ----ASSERT----

        assertEquals(expected,actual);

    }

    @Test
    @DisplayName("Must return a Response Entity of an HTTPStatus.BAD_REQUEST throwing exception" +
            " when Task can't be found.")
    void getTaskUnsuccessfullyThrowingIllegalException() throws ParseException {
        //------ARRANGE---------

        String taskCode = "T1";
        String projectCode = "A0001";
        IllegalArgumentException e = new IllegalArgumentException();

        when(taskService.getTask(projectCode,taskCode)).thenThrow(e);;

        // ----ACT----
        ResponseEntity<Object> expected = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        ResponseEntity<Object> actual = taskController.getTask(taskCode,projectCode);

        // ----ASSERT----

        assertEquals(expected,actual);

    }

    @Test
    @DisplayName("Must return a Response Entity of an HTTPStatus.BAD_REQUEST throwing exception" +
            " when Task can't be found, for Parse Exception.")
    void getTaskUnsuccessfullyThrowingParseException() throws ParseException {
        //------ARRANGE---------

        String taskCode = "T1";
        String projectCode = "A0001";
        ParseException e = new ParseException("error",1);

        when(taskService.getTask(projectCode,taskCode)).thenThrow(e);;

        // ----ACT----
        ResponseEntity<Object> expected = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        ResponseEntity<Object> actual = taskController.getTask(taskCode,projectCode);

        // ----ASSERT----

        assertEquals(expected,actual);

    }

    @Test
    @DisplayName("Must return a Response Entity of an HTTPStatus.Ok when all tasks are successfully found.")
    void getAllTasksSuccessfullyWithResponseHTTPStatusOK() throws ParseException {

        //------ARRANGE---------
        List<TaskDTO> taskDTOList = new ArrayList<>();

        String taskCode = "T1";
        String projectCode = "A0001";
        String otherTaskCode = "T2";


        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTaskCode(taskCode);
        taskDTO.setProjectCodeDto(projectCode);

        TaskDTO taskDTOOther = new TaskDTO();
        taskDTOOther.setTaskCode(otherTaskCode);
        taskDTOOther.setProjectCodeDto(projectCode);

        taskDTOList.add(taskDTO);
        taskDTOList.add(taskDTOOther);


        when(taskService.getTasks()).thenReturn(taskDTOList);

        Link link = linkTo(methodOn(ImplTaskController.class).getTask(taskDTO.getTaskCode(),
                taskDTO.getProjectCodeDto())).withSelfRel();

        Link otherlink = linkTo(methodOn(ImplTaskController.class).getTask(taskDTOOther.getTaskCode(),
                taskDTOOther.getProjectCodeDto())).withSelfRel();


        taskDTO.add(link);
        taskDTOOther.add(otherlink);

        // ----ACT----

        ResponseEntity<Object> expected = new ResponseEntity<>(taskDTOList, HttpStatus.OK);
        ResponseEntity<Object> actual = taskController.getTasks();


        // ----ASSERT----

        assertEquals(expected,actual);

    }

    @Test
    @DisplayName("Must return a Response Entity of an HTTPStatus.BAD_REQUEST when tasks can't be successfully found," +
            "throwing Illegal Argument Exception.")
    void getAllTasksUnsuccessfullyWithResponseHTTPStatusBad_RequestThrowingIllegalArgumentException() throws ParseException {

        //------ARRANGE---------

        IllegalArgumentException e = new IllegalArgumentException();

        when(taskService.getTasks()).thenThrow(e);

        // ----ACT----

        ResponseEntity<Object> expected = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        ResponseEntity<Object> actual = taskController.getTasks();

        // ----ASSERT----

        assertEquals(expected,actual);

    }

    @Test
    @DisplayName("Must return a Response Entity of an HTTPStatus.BAD_REQUEST when tasks can't be successfully found," +
            "throwing Parse Exception.")
    void getAllTasksUnsuccessfullyWithResponseHTTPStatusBad_RequestThrowingParseException() throws ParseException {

        //------ARRANGE---------

        ParseException e = new ParseException("error",1);

        when(taskService.getTasks()).thenThrow(e);

        // ----ACT----

        ResponseEntity<Object> expected = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        ResponseEntity<Object> actual = taskController.getTasks();

        // ----ASSERT----

        assertEquals(expected,actual);

    }



}
