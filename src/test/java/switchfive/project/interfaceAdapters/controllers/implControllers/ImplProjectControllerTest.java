package switchfive.project.interfaceAdapters.controllers.implControllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import switchfive.project.dtos.ActivityDTO;
import switchfive.project.dtos.ProjectDTO;
import switchfive.project.dtos.UpdateProjectDTO;
import switchfive.project.applicationServices.appServices.iappServices.IAppProjectService;

import javax.net.ssl.SSLException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

class ImplProjectControllerTest {

    final String WRONG_INFORMATION = "Wrong information";
    final String PROJECT_DO_NOT_EXIST = "Project do not exist";

    ProjectDTO dto;
    UpdateProjectDTO updateDto;
    ProjectDTO dtoMock;
    IAppProjectService projectServiceMock;
    ImplProjectController projectControllerMock;

    @BeforeEach
    void setUp() {
        dtoMock = Mockito.mock(ProjectDTO.class);
        projectServiceMock = Mockito.mock(IAppProjectService.class);
        projectControllerMock = new ImplProjectController(projectServiceMock);

        MockitoAnnotations.openMocks(this);

        dto = new ProjectDTO();
        dto.setProjectCode("ISEP1");
        dto.setUserEmail("daniel@isep.ipp.pt");
        dto.setCostPerHour(10);
        dto.setPercentageOfAllocation(100);

        updateDto = new UpdateProjectDTO();
        dto.setProjectName("otherProject");
        dto.setProjectDescription("other description");
        dto.setProjectBusinessSector("otherEducational");
        dto.setProjectNumberOfPlannedSprints(1);
        dto.setProjectSprintDuration(20);
        dto.setProjectBudget(0);
        dto.setStartDate("26/04/2023");
        dto.setEndDate("26/04/2025");
        dto.setTypologyDescription("123e4567-e89b-12d3-a456-556642440000");
        dto.setCustomerName("torres");
    }

    @Test
    void projectIsSuccessfullyCreated()
            throws ParseException, NoSuchAlgorithmException {
        //Arrange
        Mockito.when(projectServiceMock.createAndSaveProject(Mockito.any(ProjectDTO.class))).thenReturn(dtoMock);

        Mockito.when(dtoMock.add(Mockito.any(Link.class))).thenReturn(dtoMock);

        ResponseEntity<Object> expected = new ResponseEntity<>(dtoMock, HttpStatus.CREATED);

        //Act
        ResponseEntity<Object> actual = projectControllerMock.createProject(dto);

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void invalidProjectIsNotCreated()
            throws ParseException, NoSuchAlgorithmException {
        //Arrange
        final String WRONG_INFORMATION = "Wrong information";

        Mockito.when(projectServiceMock.createAndSaveProject(Mockito.any(ProjectDTO.class))).thenThrow(IllegalArgumentException.class);

        ResponseEntity<Object> expected = new ResponseEntity<>(WRONG_INFORMATION, HttpStatus.BAD_REQUEST);

        //Act
        ResponseEntity<Object> actual = projectControllerMock.createProject(dto);

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getValidProject() throws ParseException, NoSuchAlgorithmException, SSLException {
        //Arrange
        Mockito.when(projectServiceMock.getProjectDTO(Mockito.anyString())).thenReturn(Optional.of(dto));

        ResponseEntity<Object> expected = new ResponseEntity<>(dto, HttpStatus.OK);


        //Act
        ResponseEntity<Object> actual = projectControllerMock.getProject(dto.getProjectCode());

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getProjectThatDoNotExist() throws ParseException, NoSuchAlgorithmException, SSLException {
        //Arrange
        Mockito.when(projectServiceMock.getProjectDTO(Mockito.anyString())).thenReturn(Optional.empty());

        ResponseEntity<Object> expected = new ResponseEntity<>(PROJECT_DO_NOT_EXIST, HttpStatus.NOT_FOUND);

        //Act
        ResponseEntity<Object> actual = projectControllerMock.getProject(dto.getProjectCode());

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void tryToGetInvalidProject() throws ParseException, NoSuchAlgorithmException, SSLException {
        //Arrange
        Mockito.when(projectServiceMock.getProjectDTO(Mockito.anyString())).thenThrow(IllegalArgumentException.class);

        ResponseEntity<Object> expected = new ResponseEntity<>(WRONG_INFORMATION, HttpStatus.BAD_REQUEST);

        //Act
        ResponseEntity<Object> actual = projectControllerMock.getProject(dto.getProjectCode());

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void updateProjectFailsBecauseProjectDoesNotExist()
            throws ParseException, NoSuchAlgorithmException {
        //Arrange
        Mockito.when(projectServiceMock.updateProject(Mockito.anyString(), Mockito.any(UpdateProjectDTO.class))).thenReturn(Optional.empty());

        ResponseEntity<Object> expected = new ResponseEntity<>(PROJECT_DO_NOT_EXIST, HttpStatus.NOT_FOUND);

        //Act
        ResponseEntity<Object> actual = projectControllerMock.updateProject(dto.getProjectCode(), updateDto);

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void updateProjectSuccess() throws ParseException, NoSuchAlgorithmException {
        //Arrange
        Mockito.when(projectServiceMock.updateProject(Mockito.anyString(), Mockito.any(UpdateProjectDTO.class))).thenReturn(Optional.of(dtoMock));

        Mockito.when(dtoMock.add(Mockito.any(Link.class))).thenReturn(dtoMock);

        ResponseEntity<Object> expected = new ResponseEntity<>(dtoMock, HttpStatus.OK);

        //Act
        ResponseEntity<Object> actual = projectControllerMock.updateProject(dto.getProjectCode(), updateDto);

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void updateProjectGenerateIllegalArgumentException()
            throws ParseException, NoSuchAlgorithmException {
        //Arrange
        Mockito.when(projectServiceMock.updateProject(Mockito.anyString(), Mockito.any(UpdateProjectDTO.class))).thenThrow(new IllegalArgumentException());

        ResponseEntity<Object> expected = new ResponseEntity<>(WRONG_INFORMATION, HttpStatus.BAD_REQUEST);

        //Act
        ResponseEntity<Object> actual = projectControllerMock.updateProject(dto.getProjectCode(), updateDto);

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getAllProjects() throws ParseException, NoSuchAlgorithmException, SSLException {

        // Arrange
        List<ProjectDTO> projectDTOList =
                new ArrayList<>();
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectCode("A0001");
        projectDTOList.add(projectDTO);

        Mockito.when(projectServiceMock.getAllProjects())
                .thenReturn(projectDTOList);

        RepresentationModel<ProjectDTO> linkListExpected =
                new RepresentationModel<>();
        Link linkExpected = linkTo(methodOn(ImplProjectController.class).
                getProject(projectDTO.getProjectCode()))
                .withRel(projectDTO.getProjectCode())
                .withType("GET");
        linkListExpected.add(linkExpected);

        ResponseEntity<Object> expected =
                new ResponseEntity<>(linkListExpected, HttpStatus.OK);

        // Act
        ResponseEntity<Object> actual =
                projectControllerMock.getAllProjects();

        // Assert
        Assertions.assertEquals(expected, actual);

    }

    @Test
    void getStatusOfActivities() throws ParseException, NoSuchAlgorithmException, SSLException {

        // ----ARRANGE----

        ImplProjectController projectController = new ImplProjectController(projectServiceMock);

        //Create Project
        String projectCode = "A0001";

        //Create ActivityDTO, List of ActivityDTO

        List<ActivityDTO> activitiesDTO =
                new ArrayList<>();

        String activityCodeUS = "US1";
        String typeOfActivityUS = "User Story";
        String activityStatusUS = "PLANNED";

        String activityCodeTask = "T1";
        String typeOfActivityTask = "Task";
        String activityStatusTask = "PLANNED";

        ActivityDTO activityDTOUS = new ActivityDTO(typeOfActivityUS,activityCodeUS,activityStatusUS);
        ActivityDTO activityDTOTask = new ActivityDTO(typeOfActivityTask,activityCodeTask,activityStatusTask);


        //Create Links

        Link linkUS = linkTo(methodOn(ImplUserStoryController.class)
                .getUserStory(projectCode,
                        activityDTOUS.getActivityCode())).withSelfRel();
        Link linkTask = linkTo(methodOn(ImplTaskController.class).getTask(activityDTOTask.getActivityCode(),
                projectCode)).withSelfRel();

        activityDTOUS.add(linkUS);
        activityDTOTask.add(linkTask);

        // ADD activities to List

        activitiesDTO.add(activityDTOUS);
        activitiesDTO.add(activityDTOTask);


        Mockito.when(projectServiceMock.getActivitiesStatuses(Mockito.any()))
                .thenReturn(activitiesDTO);

        ResponseEntity<Object> expected =
                new ResponseEntity<>(activitiesDTO, HttpStatus.OK);

        // ----ACT----
        ResponseEntity<Object> actual =
                projectController.getActivitiesStatus(projectCode);

        // ----ASSERT----
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getStatusOfActivitiesFailes_ProjectDoesNotExist() throws ParseException, NoSuchAlgorithmException, SSLException {
        // Arrange
        ImplProjectController projectController = new ImplProjectController(projectServiceMock);

        IllegalArgumentException e = new IllegalArgumentException("Project doesn't exist!");

        Mockito.when(projectServiceMock.getActivitiesStatuses(Mockito.anyString())).thenThrow(
                e);

        ResponseEntity<Object> expected = new ResponseEntity<>("Project doesn't exist!",
                HttpStatus.BAD_REQUEST);

        // Act
        ResponseEntity<Object> actual =
                projectController.getActivitiesStatus(dto.getProjectCode());

        // Assert
        Assertions.assertEquals(expected, actual);
    }
}
