package switchfive.project.interfaceAdapters.controllers.implControllers;

import org.junit.jupiter.api.Test;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import switchfive.project.dtos.SprintCreationDTO;
import switchfive.project.dtos.SprintDTO;
import switchfive.project.applicationServices.appServices.implAppServices.ImplAppSprintService;
import switchfive.project.domain.shared.valueObjects.SprintID;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

class ImplSprintControllerTest {

    @Test
    void getAllSprintsByProjectCode() throws ParseException {

        ImplAppSprintService sprintServiceMock = mock(ImplAppSprintService.class);

        ImplSprintController sprintController = new ImplSprintController(sprintServiceMock);

        List<SprintDTO> sprintDTOList = new ArrayList<>();
        SprintDTO sprintDTO = new SprintDTO();
        sprintDTO.setProjectCode("MAU1");
        sprintDTO.setSprintNumber(1);
        sprintDTO.setStartDate("15/05/2023");
        sprintDTO.setEndDate("20/06/2023");
        sprintDTO.setDescription("First Sprint");
        sprintDTO.setStatus("RUNNING");

        Link expectedLink = linkTo(methodOn(ImplSprintController.class).getSprintById(sprintDTO.getSprintNumber(),sprintDTO.getProjectCode())).withRel(sprintDTO.getSprintNumber().toString()).withType("GET");
        sprintDTO.add(expectedLink);
        sprintDTOList.add(sprintDTO);

        when(sprintServiceMock.getSprintsByProjectCode("MAU1")).thenReturn(Optional.of(sprintDTOList));

        RepresentationModel<SprintDTO> sprintDTORepresentationModel = new RepresentationModel<>();
        sprintDTORepresentationModel.add(expectedLink);


        ResponseEntity expectedResponseEntity =
                new ResponseEntity<>(sprintDTORepresentationModel,
                        HttpStatus.OK);

        ResponseEntity resultResponseEntity = sprintController.getAllSprintsByProjectCode("MAU1");

        assertEquals(expectedResponseEntity,resultResponseEntity);
    }


    @Test
    void getAllSprintsByProjectCodeNoSprintsInProject() {
    ImplAppSprintService sprintServiceMock = mock(ImplAppSprintService.class);

    ImplSprintController sprintController = new ImplSprintController(sprintServiceMock);

    ResponseEntity<Object> expectedResponseEntity =
            new ResponseEntity<>("Project sprint list is empty!",
                    HttpStatus.OK);

    ResponseEntity<Object> resultResponseEntity = sprintController.getAllSprintsByProjectCode("MAU1");
    assertEquals(expectedResponseEntity,resultResponseEntity);
    }

    @Test
    void getAllSprintsByProjectCodeNoLinks() throws ParseException {
        ImplAppSprintService sprintServiceMock = mock(ImplAppSprintService.class);
        ImplSprintController sprintController = new ImplSprintController(sprintServiceMock);

        IllegalArgumentException e = new IllegalArgumentException();

        when(sprintServiceMock.getSprintsByProjectCode("MAU1")).thenThrow(e);

        ResponseEntity<Object> expectedResponseEntity =
                new ResponseEntity<>(
                        HttpStatus.BAD_REQUEST);

        ResponseEntity<Object> resultResponseEntity = sprintController.getAllSprintsByProjectCode("MAU1");

        assertEquals(expectedResponseEntity,resultResponseEntity);
    }

    @Test
    void getASprintByIdSuccessfully() throws ParseException {
        //Arrange
        ImplAppSprintService sprintServiceMock = mock(ImplAppSprintService.class);
        ImplSprintController sprintController = new ImplSprintController(sprintServiceMock);
        SprintID sprintID = SprintID.createSprintID("MAU1",1);
        SprintDTO sprintDTO = mock(SprintDTO.class);
        sprintDTO.setSprintNumber(1);
        sprintDTO.setProjectCode("MAU1");

        when(sprintServiceMock.getSprintBySprintId(sprintID)).thenReturn(Optional.ofNullable(sprintDTO));

        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(sprintDTO,HttpStatus.OK);
        //Act
        ResponseEntity<Object> resultResponseEntity = sprintController.getSprintById(1,"MAU1");
        //Assert
        assertEquals(expectedResponse,resultResponseEntity);
    }

    @Test
    void getANonExistantSprintById() throws ParseException {
        //Arrange
        ImplAppSprintService sprintServiceMock = mock(ImplAppSprintService.class);
        ImplSprintController sprintController = new ImplSprintController(sprintServiceMock);
        SprintID sprintID = SprintID.createSprintID("MAU1",1);
        SprintDTO sprintDTO = mock(SprintDTO.class);
        sprintDTO.setSprintNumber(1);
        sprintDTO.setProjectCode("MAU1");

        when(sprintServiceMock.getSprintBySprintId(sprintID)).thenReturn(Optional.empty());

        ResponseEntity<Object> expectedResponse = new ResponseEntity<>("Sprint does not exist!",HttpStatus.OK);
        //Act
        ResponseEntity<Object> resultResponseEntity = sprintController.getSprintById(1,"MAU1");
        //Assert
        assertEquals(expectedResponse,resultResponseEntity);
    }
    @Test
    void getSprintByIdInvalidSprintID() throws ParseException {
        //Arrange
        ImplAppSprintService sprintServiceMock = mock(ImplAppSprintService.class);
        ImplSprintController sprintController = new ImplSprintController(sprintServiceMock);
        SprintID sprintID = SprintID.createSprintID("MAU1",1);
        SprintDTO sprintDTO = mock(SprintDTO.class);
        sprintDTO.setSprintNumber(1);
        sprintDTO.setProjectCode("MAU1");

        when(sprintServiceMock.getSprintBySprintId(sprintID)).thenThrow(IllegalArgumentException.class);

        IllegalArgumentException e = new IllegalArgumentException();

        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        //Act
        ResponseEntity<Object> resultResponseEntity = sprintController.getSprintById(1,"MAU1");
        //Assert
        assertEquals(expectedResponse,resultResponseEntity);
    }

    @Test
    void createAndAddNewSprint() throws ParseException {
    ImplAppSprintService sprintServiceMock = mock(ImplAppSprintService.class);
    ImplSprintController sprintController = new ImplSprintController(sprintServiceMock);

        SprintDTO sprintDTO = mock(SprintDTO.class);
        sprintDTO.setSprintNumber(1);
        sprintDTO.setProjectCode("MAU1");
        SprintCreationDTO sprintCreationDTO = mock(SprintCreationDTO.class);
        sprintCreationDTO.setStartDate("15/05/2023");
        sprintCreationDTO.setEndDate("20/06/2023");
        sprintCreationDTO.setDescription("First Sprint");

    when(sprintServiceMock.createAndSaveSprint(sprintCreationDTO,"MAU1")).thenReturn(sprintDTO);

    //ACT
        ResponseEntity<Object> resultResponseEntity = sprintController.createAndAddNewSprint(sprintCreationDTO,"MAU1");

    }

    @Test
    void failsCreationOfSprint() throws ParseException {
        ImplAppSprintService sprintServiceMock = mock(ImplAppSprintService.class);
        ImplSprintController sprintController = new ImplSprintController(sprintServiceMock);

        SprintDTO sprintDTO = mock(SprintDTO.class);
        sprintDTO.setSprintNumber(1);
        sprintDTO.setProjectCode("MAU1");

        SprintCreationDTO sprintCreationDTO = mock(SprintCreationDTO.class);
        sprintCreationDTO.setStartDate("15/05/2023");
        sprintCreationDTO.setEndDate("20/06/2023");
        sprintCreationDTO.setDescription("First Sprint");

        IllegalArgumentException e = new IllegalArgumentException();

        when(sprintServiceMock.createAndSaveSprint(sprintCreationDTO,"MAU1")).thenThrow(IllegalArgumentException.class);

        ResponseEntity<Object> expectedResponseEntity =
                new ResponseEntity<>(
                        HttpStatus.BAD_REQUEST);

        ResponseEntity<Object> resultResponseEntity = sprintController.createAndAddNewSprint(sprintCreationDTO,"MAU1");

        assertEquals(expectedResponseEntity,resultResponseEntity);
    }

}