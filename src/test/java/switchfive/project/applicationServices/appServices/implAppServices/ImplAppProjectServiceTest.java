package switchfive.project.applicationServices.appServices.implAppServices;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import switchfive.project.applicationServices.iRepositoriesWS.IProjectWebRepository;
import switchfive.project.dtos.ActivityDTO;
import switchfive.project.dtos.ProjectDTO;
import switchfive.project.dtos.ProjectDTO_Domain;
import switchfive.project.dtos.UpdateProjectDTO;

import switchfive.project.applicationServices.appServices.iappServices.IAppResourceService;
import switchfive.project.mappers.mappersApp.iMappers.IProjectMapper;
import switchfive.project.applicationServices.assemblers.iAssemblers.IProjectAssembler;
import switchfive.project.applicationServices.iRepositories.*;
import switchfive.project.domain.aggregates.customer.Customer;
import switchfive.project.domain.aggregates.project.ImplProjectBuilder;
import switchfive.project.domain.aggregates.project.Project;
import switchfive.project.domain.aggregates.resource.Resource;
import switchfive.project.domain.aggregates.task.Task;
import switchfive.project.domain.aggregates.typology.Typology;
import switchfive.project.domain.aggregates.userStory.UserStory;
import switchfive.project.domain.shared.valueObjects.*;

import javax.net.ssl.SSLException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

class ImplAppProjectServiceTest {

    ProjectDTO dto;
    ProjectDTO_Domain dtoDomain;
    UpdateProjectDTO updateProjectDto;

    @Mock
    Project projectMock;
    @Mock
    ImplProjectBuilder projectBuilderMock;
    @Mock
    IProjectMapper projectMapper;
    @Mock
    IProjectAssembler projectAssembler;
    @Mock
    IProjectRepository projectRepoMock;

    @Mock
    Customer customerMock;
    @Mock
    ICustomerRepository customerRepoMock;

    @Mock
    Typology typologyMock;
    @Mock
    ITypologyRepository typologyRepoMock;

    @Mock
    Resource projectManagerMock;
    @Mock
    IAppResourceService resourceServiceMock;
    @Mock
    IResourceRepository resourceRepoMock;
    @Mock
    IUserRepository userRepoMock;

    @Mock
    IUserStoryRepository userStoryRepoMock;

    @Mock
    ITaskRepository taskRepoMock;

    @Mock
    IProjectWebRepository projectWebRepository;

    @InjectMocks
    ImplAppProjectService implAppProjectService;


    @BeforeEach
    void setUp() throws ParseException {
        MockitoAnnotations.openMocks(this);

        dto = new ProjectDTO();
        dto.setProjectCode("OTHER");
        dto.setProjectName("First Project");
        dto.setProjectDescription("Some description");
        dto.setProjectBusinessSector("Educational");
        dto.setProjectNumberOfPlannedSprints(10);
        dto.setProjectSprintDuration(2);
        dto.setProjectBudget(1000);
        dto.setStartDate("25/04/2023");
        dto.setEndDate("25/04/2025");
        dto.setTypologyDescription("new description");
        dto.setCustomerName("ISEP");
        dto.setUserEmail("1211757@isep.ipp.pt");
        dto.setCostPerHour(10);
        dto.setPercentageOfAllocation(100);

        dtoDomain = new ProjectDTO_Domain();
        dtoDomain.setProjectCode(ProjectCode.create(dto.getProjectCode()));
        dtoDomain.setProjectName(ProjectName.create(dto.getProjectName()));
        dtoDomain.setProjectDescription(ProjectDescription.create((dto.getProjectDescription())));
        dtoDomain.setProjectBusinessSector(ProjectBusinessSector.create(dto.getProjectBusinessSector()));
        dtoDomain.setProjectNumberOfPlannedSprints(ProjectNumberOfPlannedSprints.create(dto.getProjectNumberOfPlannedSprints()));
        dtoDomain.setProjectSprintDuration(ProjectSprintDuration.create(dto.getProjectSprintDuration()));
        dtoDomain.setProjectBudget(ProjectBudget.create(dto.getProjectBudget()));
        dtoDomain.setDates(Time.create(dto.getStartDate(), dto.getEndDate()));
        dtoDomain.setTypologyDescription(TypologyDescription.create(dto.getTypologyDescription()));
        dtoDomain.setCustomerName(CustomerName.create(dto.getCustomerName()));
        dtoDomain.setUserEmail(Email.create(dto.getUserEmail()));
        dtoDomain.setCostPerHour(ResourceCostPerHour.create(dto.getCostPerHour()));
        dtoDomain.setPercentageOfAllocation(ResourcePercentageOfAllocation.create(dto.getPercentageOfAllocation()));

        updateProjectDto = new UpdateProjectDTO();
        updateProjectDto.setProjectName("First Project");
        updateProjectDto.setProjectDescription("Other description");
        updateProjectDto.setProjectBusinessSector("Other Educational");
        updateProjectDto.setProjectNumberOfPlannedSprints(1);
        updateProjectDto.setProjectSprintDuration(5);
        updateProjectDto.setProjectBudget(0);
        updateProjectDto.setStartDate("26/04/2023");
        updateProjectDto.setEndDate("26/04/2025");
        updateProjectDto.setTypologyDescription("Other description");
        updateProjectDto.setCustomerName("torres");
        updateProjectDto.setStatus("Planned");
    }

    @Test
    void getEmptyProjectBecauseProjectDoesNotExist() throws ParseException, SSLException, NoSuchAlgorithmException {
        //Arrange
        when(projectRepoMock.findByCode(any(ProjectCode.class))).thenReturn(Optional.empty());
        when(projectWebRepository.findByCode(anyString())).thenReturn(Optional.empty());

        //Act
        Optional<ProjectDTO> actual = implAppProjectService.getProjectDTO("test1");

        // Assert
        assertEquals(Optional.empty(), actual);
    }

    @Test
    void projectIsSuccessfullyCreated()
            throws ParseException, NoSuchAlgorithmException {
        // Arrange

        when(projectAssembler.toDomain(dto)).thenReturn(dtoDomain);

        when(projectRepoMock.projectExists(dtoDomain.getProjectCode())).thenReturn(false);
        when(userRepoMock.userExists(dto.getUserEmail())).thenReturn(true);
        when(customerRepoMock.customerExists(dtoDomain.getCustomerName())).thenReturn(true);
        when(typologyRepoMock.typologyExists(dtoDomain.getTypologyDescription())).thenReturn(true);

        when(projectBuilderMock.setCode(dtoDomain.getProjectCode())).thenReturn(projectBuilderMock);
        when(projectBuilderMock.setName(dtoDomain.getProjectName())).thenReturn(projectBuilderMock);
        when(projectBuilderMock.setDescription(dtoDomain.getProjectDescription())).thenReturn(projectBuilderMock);
        when(projectBuilderMock.setBusinessSector(dtoDomain.getProjectBusinessSector())).thenReturn(projectBuilderMock);
        when(projectBuilderMock.setTime(dtoDomain.getDates())).thenReturn(projectBuilderMock);
        when(projectBuilderMock.setSprints(dtoDomain.getProjectNumberOfPlannedSprints())).thenReturn(projectBuilderMock);
        when(projectBuilderMock.setSprintDuration(dtoDomain.getProjectSprintDuration())).thenReturn(projectBuilderMock);
        when(projectBuilderMock.setBudget(dtoDomain.getProjectBudget())).thenReturn(projectBuilderMock);
        when(projectBuilderMock.setTypology(dtoDomain.getTypologyDescription())).thenReturn(projectBuilderMock);
        when(projectBuilderMock.setCustomer(dtoDomain.getCustomerName())).thenReturn(projectBuilderMock);
        when(projectBuilderMock.setStatus(dtoDomain.getStatus())).thenReturn(projectBuilderMock);
        when(projectBuilderMock.build()).thenReturn(projectMock);

        when(resourceServiceMock.createProjectManager(dtoDomain.getUserEmail(), dtoDomain.getProjectCode(), dtoDomain.getDates(),
                dtoDomain.getCostPerHour(), dtoDomain.getPercentageOfAllocation())).thenReturn(projectManagerMock);

        when(projectRepoMock.save(projectMock)).thenReturn(projectMock);
        when(resourceRepoMock.save(projectManagerMock)).thenReturn(projectManagerMock);

        when(projectMapper.toDTO(projectMock, projectManagerMock)).thenReturn(dto);

        // Act
        ProjectDTO actual = implAppProjectService.createAndSaveProject(dto);

        // Assert
        assertEquals(dto, actual);
    }

    @Test
    void projectCreationFailsBecauseProjectAlreadyExists() throws ParseException {
        // Arrange
        when(projectAssembler.toDomain(dto)).thenReturn(dtoDomain);

        when(projectRepoMock.projectExists(dtoDomain.getProjectCode())).thenReturn(true);

        // Act

        // Assert
        assertThrows(IllegalArgumentException.class, () -> implAppProjectService.createAndSaveProject(dto));
    }

    @Test
    void projectCreationFailsBecauseCustomerDoesNotExists() throws ParseException {
        // Arrange
        when(projectAssembler.toDomain(dto)).thenReturn(dtoDomain);

        when(projectRepoMock.projectExists(dtoDomain.getProjectCode())).thenReturn(false);
        when(customerRepoMock.customerExists(dtoDomain.getCustomerName())).thenReturn(false);

        // Act

        // Assert
        assertThrows(IllegalArgumentException.class, () -> implAppProjectService.createAndSaveProject(dto));
    }

    @Test
    void projectCreationFailsBecauseTypologyDoesNotExists() throws ParseException {
        // Arrange
        when(projectAssembler.toDomain(dto)).thenReturn(dtoDomain);

        when(projectRepoMock.projectExists(dtoDomain.getProjectCode())).thenReturn(false);
        when(customerRepoMock.customerExists(dtoDomain.getCustomerName())).thenReturn(true);
        when(typologyRepoMock.typologyExists(dtoDomain.getTypologyDescription())).thenReturn(false);

        // Act

        // Assert
        assertThrows(IllegalArgumentException.class, () -> implAppProjectService.createAndSaveProject(dto));
    }

    @Test
    void projectCreationFailsBecauseUserDoesNotExists()
            throws NoSuchAlgorithmException, ParseException {
        // Arrange
        when(projectAssembler.toDomain(dto)).thenReturn(dtoDomain);

        when(projectRepoMock.projectExists(dtoDomain.getProjectCode())).thenReturn(false);
        when(customerRepoMock.customerExists(dtoDomain.getCustomerName())).thenReturn(true);
        when(typologyRepoMock.typologyExists(dtoDomain.getTypologyDescription())).thenReturn(true);
        when(userRepoMock.findUserByEmail(dto.getUserEmail())).thenReturn(Optional.empty());

        // Act

        // Assert
        assertThrows(IllegalArgumentException.class, () -> implAppProjectService.createAndSaveProject(dto));
    }

    @Test
    void resourceCreationFails()
            throws ParseException, NoSuchAlgorithmException {
        // Arrange
        when(projectAssembler.toDomain(dto)).thenReturn(dtoDomain);

        when(projectRepoMock.projectExists(dtoDomain.getProjectCode())).thenReturn(false);
        when(customerRepoMock.customerExists(dtoDomain.getCustomerName())).thenReturn(true);
        when(typologyRepoMock.typologyExists(dtoDomain.getTypologyDescription())).thenReturn(true);
        when(userRepoMock.findUserByEmail(dto.getUserEmail())).thenReturn(Optional.empty());

        when(projectBuilderMock.setCode(dtoDomain.getProjectCode())).thenReturn(projectBuilderMock);
        when(projectBuilderMock.setName(dtoDomain.getProjectName())).thenReturn(projectBuilderMock);
        when(projectBuilderMock.setDescription(dtoDomain.getProjectDescription())).thenReturn(projectBuilderMock);
        when(projectBuilderMock.setBusinessSector(dtoDomain.getProjectBusinessSector())).thenReturn(projectBuilderMock);
        when(projectBuilderMock.setTime(dtoDomain.getDates())).thenReturn(projectBuilderMock);
        when(projectBuilderMock.setSprints(dtoDomain.getProjectNumberOfPlannedSprints())).thenReturn(projectBuilderMock);
        when(projectBuilderMock.setSprintDuration(dtoDomain.getProjectSprintDuration())).thenReturn(projectBuilderMock);
        when(projectBuilderMock.setBudget(dtoDomain.getProjectBudget())).thenReturn(projectBuilderMock);
        when(projectBuilderMock.setTypology(dtoDomain.getTypologyDescription())).thenReturn(projectBuilderMock);
        when(projectBuilderMock.setCustomer(dtoDomain.getCustomerName())).thenReturn(projectBuilderMock);
        when(projectBuilderMock.setStatus(dtoDomain.getStatus())).thenReturn(projectBuilderMock);
        when(projectBuilderMock.build()).thenReturn(projectMock);

        when(resourceServiceMock.createProjectManager(dtoDomain.getUserEmail(), dtoDomain.getProjectCode(), dtoDomain.getDates(),
                dtoDomain.getCostPerHour(), dtoDomain.getPercentageOfAllocation())).thenThrow(new IllegalArgumentException());

        // Act

        // Assert
        assertThrows(IllegalArgumentException.class, () -> implAppProjectService.createAndSaveProject(dto));
    }

    @Test
    void updateProjectNotSuccessfulBecauseProjectDoesntExist()
            throws ParseException {
        // Arrange
        when(projectRepoMock.findByCode(any(ProjectCode.class))).thenReturn(Optional.empty());

        // Act
        Optional<ProjectDTO> actual =
                implAppProjectService.updateProject(
                        dto.getProjectCode(),
                        updateProjectDto);

        // Assert
        assertEquals(Optional.empty(), actual);

    }

    @Test
    void updateProjectNotSuccessfulBecauseTypologyDoesntExist()
            throws ParseException {
        // Arrange
        when(projectRepoMock.findByCode(any(ProjectCode.class))).thenReturn(
                Optional.of(mock(Project.class)));

        when(customerRepoMock.customerExists(
                any(CustomerName.class))).thenReturn(true);

        when(typologyRepoMock.typologyExists(
                any(TypologyDescription.class))).thenReturn(false);


        // Act

        // Assert
        assertThrows(IllegalArgumentException.class, () -> implAppProjectService.updateProject(
                dto.getProjectCode(),
                updateProjectDto));

    }

    @Test
    void updateProjectSuccess() throws ParseException {
        // Arrange
        Project project =
                new Project(ProjectCode.create(dto.getProjectCode()),
                        ProjectName.create(dto.getProjectName()),
                        ProjectDescription.create(dto.getProjectDescription()),
                        CustomerName.create(dto.getCustomerName()));
        project.addBusinessSector(ProjectBusinessSector.create(dto.getProjectBusinessSector()));
        project.addDates(Time.create(dto.getStartDate(), dto.getEndDate()));
        project.addTypologyDescription(TypologyDescription.create(dto.getTypologyDescription()));
        project.addNumberOfPlannedSprints(ProjectNumberOfPlannedSprints.create(dto.getProjectNumberOfPlannedSprints()));
        project.addSprintDuration(ProjectSprintDuration.create(dto.getProjectSprintDuration()));
        project.addBudget(ProjectBudget.create(dto.getProjectBudget()));

        Project projectSpy = spy(project);

        when(projectRepoMock.findByCode(any(ProjectCode.class))).thenReturn(Optional.of(projectSpy));

        when(customerRepoMock.customerExists(any(CustomerName.class))).thenReturn(true);

        when(customerRepoMock.findCustomerByName(any(CustomerName.class))).thenReturn(Optional.of(customerMock));

        when(typologyRepoMock.typologyExists(any(TypologyDescription.class))).thenReturn(true);

        when(typologyRepoMock.findTypology(any(TypologyDescription.class))).thenReturn(Optional.of(typologyMock));

        when(projectMapper.toDTO(any(Project.class))).thenReturn(dto);

        // Act
        Optional<ProjectDTO> actual = implAppProjectService.updateProject(dto.getProjectCode(), updateProjectDto);

        // Assert
        assertEquals(Optional.of(dto), actual);

        verify(projectRepoMock, times(1)).save(any(Project.class));

        verify(projectSpy, times(1)).addName(any(ProjectName.class));
        verify(projectSpy, times(1)).addDescription(
                any(ProjectDescription.class));
        verify(projectSpy, times(1)).addBusinessSector(
                any(ProjectBusinessSector.class));
        verify(projectSpy, times(1)).addDates(any(Time.class));
        verify(projectSpy, times(1)).addCustomer(any(CustomerName.class));
        verify(projectSpy, times(1)).addTypologyDescription(
                any(TypologyDescription.class));
        verify(projectSpy, times(1)).addNumberOfPlannedSprints(
                any(ProjectNumberOfPlannedSprints.class));
        verify(projectSpy, times(1)).addSprintDuration(
                any(ProjectSprintDuration.class));
        verify(projectSpy, times(1)).addBudget(any(ProjectBudget.class));


    }

    @Test
    void getProjectDTO() throws ParseException, SSLException, NoSuchAlgorithmException {
        //Arrange
        when(projectRepoMock.projectExists(dtoDomain.getProjectCode())).thenReturn(true);

        when(projectRepoMock.findByCode(any(ProjectCode.class))).thenReturn(Optional.of(projectMock));

        when(resourceRepoMock.getProjectManager(dtoDomain.getProjectCode())).thenReturn(Optional.of(projectManagerMock));

        when(projectMapper.toDTO(projectMock, projectManagerMock)).thenReturn(dto);

        // Act
        Optional<ProjectDTO> actual = implAppProjectService.getProjectDTO(dto.getProjectCode());

        // Assert
        assertEquals(dto.getCustomerName(), actual.get().getCustomerName());
    }

    @Test
    void getAllProjects() throws ParseException, SSLException {

        //Arrange
        List<Project> projects = new ArrayList<>();
        when(projectRepoMock.findAll()).thenReturn(projects);

        List<ProjectDTO> projectsDTO = new ArrayList<>();

        assertEquals(projectsDTO, implAppProjectService.getAllProjects());

    }

    @Test
    void updateProjectNotSuccessfulBecauseCustomerDoesntExist()
            throws ParseException {
        // Arrange
        updateProjectDto.setCustomerName("ABCDE");

        when(projectRepoMock.findByCode(any(ProjectCode.class))).thenReturn(Optional.of(mock(Project.class)));

        when(customerRepoMock.customerExists(any(CustomerName.class))).thenReturn(true);

        when(typologyRepoMock.typologyExists(any(TypologyDescription.class))).thenReturn(true);

        CustomerName customer = CustomerName.create("ABCDE");
        when(customerRepoMock.customerExists(customer)).thenReturn(false);


        // Act

        // Assert
        assertThrows(IllegalArgumentException.class, () -> implAppProjectService.updateProject(
                dto.getProjectCode(),
                updateProjectDto));

    }


    @Test
    void getUserStoriesInProjectStatusSuccessfully() {

        //------------ARRANGE--------------

        String projectCode = "A0001";
        String activityCode = "US1";
        String typeOfActivity = "User Story";
        String activityStatus = "PLANNED";
        String activityCodeOther = "US2";

        ProjectCode projectCodeObj = ProjectCode.create(projectCode);
        List<UserStory> userStories = new ArrayList<>();

        // Project exists in Repository

        when(projectRepoMock.projectExists(projectCodeObj)).thenReturn(true);

        //Find UserStory List in a Project

        when(userStoryRepoMock.getUserStoriesOrderedByStatus(projectCodeObj))
                .thenReturn(userStories);

        // UserStories in a UserStoryList

        UserStory userStoryMock = mock(UserStory.class);
        UserStory otherUserStoryMock = mock(UserStory.class);
        when(userStoryMock.getUserStoryCode()).thenReturn(activityCode);
        when(userStoryMock.getStatus()).thenReturn(activityStatus);

        when(otherUserStoryMock.getUserStoryCode()).thenReturn(activityCodeOther);
        when(otherUserStoryMock.getStatus()).thenReturn(activityStatus);

        userStories.add(userStoryMock);
        userStories.add(otherUserStoryMock);

        // Add statusOfActivities

        ActivityDTO activityDTO = new ActivityDTO(typeOfActivity,
                activityCode, activityStatus);
        ActivityDTO otherActivityDTO = new ActivityDTO(typeOfActivity,
                activityCodeOther, activityStatus);


        List<ActivityDTO> activitiesDTO = new ArrayList<>();

        activitiesDTO.add(activityDTO);
        activitiesDTO.add(otherActivityDTO);

        //------------ACT--------------

        List<ActivityDTO> activitiesDTOActual = implAppProjectService.getUserStoriesStatus
                (projectCode, activitiesDTO);


        //------------ASSERT--------------

        assertEquals(activitiesDTO, activitiesDTOActual);

    }


    @Test
    void getTasksInProjectStatusSuccessfully() throws ParseException {

        //------------ARRANGE--------------

        String projectCode = "A0001";
        String activityCode = "T01";
        String typeOfActivity = "Task";
        String activityStatus = "PLANNED";
        String activityCodeOther = "T02";

        ProjectCode projectCodeObj = ProjectCode.create(projectCode);
        List<Task> taskList = new ArrayList<>();

        // Project exists in Repository

        when(projectRepoMock.projectExists(projectCodeObj)).thenReturn(true);

        //Find Task List in a Project

        when(taskRepoMock.findTaskAndOrderByStatus(projectCodeObj)).thenReturn(taskList);

        // Tasks in a TaskList

        Task taskMock = mock(Task.class);
        Task otherTaskMock = mock(Task.class);

        taskList.add(taskMock);
        taskList.add(otherTaskMock);

        TaskID taskIDMock = mock(TaskID.class);

        when(taskMock.getIdTask()).thenReturn(taskIDMock);
        when(taskIDMock.getTaskCode()).thenReturn(activityCode);
        when(taskMock.getTaskStatus()).thenReturn(activityStatus);

        TaskID otherTaskIDMock = mock(TaskID.class);

        when(otherTaskMock.getIdTask()).thenReturn(otherTaskIDMock);
        when(otherTaskIDMock.getTaskCode()).thenReturn(activityCodeOther);
        when(otherTaskMock.getTaskStatus()).thenReturn(activityStatus);


        // Add statusOfActivities

        ActivityDTO activityDTO = new ActivityDTO(typeOfActivity,
                activityCode, activityStatus);
        ActivityDTO otherActivityDTO = new ActivityDTO(typeOfActivity,
                activityCodeOther, activityStatus);


        List<ActivityDTO> activitiesDTO = new ArrayList<>();
        activitiesDTO.add(activityDTO);
        activitiesDTO.add(otherActivityDTO);

        //------------ACT--------------

        List<ActivityDTO> activitiesDTOActual = implAppProjectService.getTasksStatus
                (projectCode, activitiesDTO);


        //------------ASSERT--------------

        assertEquals(activitiesDTO, activitiesDTOActual);

    }

    @Test
    void getStatusOfAllActivitiesInAProject() throws ParseException {
        // --------- Arrange ------------------------
        String projectCode = "A0001";

        // Project exists in Repository
        ProjectCode projectCodeObj = ProjectCode.create(projectCode);
        when(projectRepoMock.projectExists(projectCodeObj)).thenReturn(true);

        // User Story Status
        List<UserStory> userStories = new ArrayList<>();
        UserStory userStoryMockOne = mock(UserStory.class);
        when(userStoryMockOne.getUserStoryCode()).thenReturn("US1");
        when(userStoryMockOne.getStatus()).thenReturn("Planned");


        UserStory userStoryMockTwo = mock(UserStory.class);
        when(userStoryMockTwo.getUserStoryCode()).thenReturn("US2");
        when(userStoryMockTwo.getStatus()).thenReturn("Planned");

        userStories.add(userStoryMockOne);
        userStories.add(userStoryMockTwo);

        when(userStoryRepoMock.getUserStoriesOrderedByStatus(any()))
                .thenReturn(userStories);


        //Task Status
        List<Task> taskList = new ArrayList<>();
        Task taskMockOne = mock(Task.class);
        TaskID taskIDMockOne = mock(TaskID.class);

        when(taskMockOne.getIdTask()).thenReturn(taskIDMockOne);
        when(taskIDMockOne.getTaskCode()).thenReturn("T1");
        when(taskMockOne.getTaskStatus()).thenReturn("Planned");

        Task taskMockTwo = mock(Task.class);
        TaskID taskIDMockTwo = mock(TaskID.class);
        when(taskMockTwo.getIdTask()).thenReturn(taskIDMockTwo);
        when(taskIDMockTwo.getTaskCode()).thenReturn("T2");
        when(taskMockTwo.getTaskStatus()).thenReturn("Planned");

        taskList.add(taskMockOne);
        taskList.add(taskMockTwo);

        when(taskRepoMock.findTaskAndOrderByStatus(any()))
                .thenReturn(taskList);

        // Create List Of ActivitiesDTO and add each ActivityDTO

        List<ActivityDTO> expected =
                new ArrayList<>();

        ActivityDTO activityDTO_US1
                = new ActivityDTO("User Story",
                "US1", "Planned");

        ActivityDTO activityDTO_US2
                = new ActivityDTO("User Story",
                "US2", "Planned");

        ActivityDTO activityDTO_T1
                = new ActivityDTO("Task", "T1",
                "Planned");

        ActivityDTO activityDTO_T2
                = new ActivityDTO("Task", "T2",
                "Planned");

        expected.add(activityDTO_US1);
        expected.add(activityDTO_US2);
        expected.add(activityDTO_T1);
        expected.add(activityDTO_T2);


        // ----------- Act ---------------------------
        List<ActivityDTO> actual =
                implAppProjectService.getActivitiesStatuses(projectCode);

        // ---------- Assert -------------------------
        Assertions.assertEquals(expected, actual);

    }

    @Test
    void getStatusOfAllActivitiesInAProject_FailsProjectDoesNotExist() throws ParseException {

        // --------- ARRANGE ------------------------
        String projectCode = "B7890";
        ProjectCode projectCodeObj = ProjectCode.create(projectCode);
        when(projectRepoMock.projectExists(projectCodeObj)).thenReturn(false);

        // ----------- ASSERT ---------------------------
        assertThrows(IllegalArgumentException.class, () ->
                implAppProjectService.getActivitiesStatuses(projectCode));

    }



    @Test
    void getUserStoriesStatusInAProject_FailsProjectDoesNotExist() throws ParseException {

        // --------- ARRANGE ------------------------
        List<ActivityDTO> activitiesDTO = new ArrayList<>();
        String projectCode = "B7890";
        ProjectCode projectCodeObj = ProjectCode.create(projectCode);
        when(projectRepoMock.projectExists(projectCodeObj)).thenReturn(false);

        // ----------- ASSERT ---------------------------
        assertThrows(IllegalArgumentException.class, () ->
                implAppProjectService.getUserStoriesStatus(projectCode,activitiesDTO));
    }


    @Test
    void getTaskStatusInAProject_FailsProjectDoesNotExist() throws ParseException {

        // --------- ARRANGE ------------------------
        List<ActivityDTO> activitiesDTO = new ArrayList<>();
        String projectCode = "B7890";
        ProjectCode projectCodeObj = ProjectCode.create(projectCode);
        when(projectRepoMock.projectExists(projectCodeObj)).thenReturn(false);

        // ----------- ASSERT ---------------------------
        assertThrows(IllegalArgumentException.class, () ->
                implAppProjectService.getTasksStatus(projectCode,activitiesDTO));
    }
}