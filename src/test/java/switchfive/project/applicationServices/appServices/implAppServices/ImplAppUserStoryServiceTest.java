package switchfive.project.applicationServices.appServices.implAppServices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import switchfive.project.dtos.MoveUserStoryDTO;
import switchfive.project.dtos.RefineUserStoryDTO;
import switchfive.project.dtos.UserStoryDTO;
import switchfive.project.mappers.mappersApp.implMappers.UserStoryMapper;
import switchfive.project.applicationServices.iRepositories.IProjectRepository;
import switchfive.project.applicationServices.iRepositories.ISprintRepository;
import switchfive.project.applicationServices.iRepositories.IUserStoryRepository;
import switchfive.project.domain.aggregates.project.Project;
import switchfive.project.domain.aggregates.sprint.Sprint;
import switchfive.project.domain.aggregates.userStory.UserStory;
import switchfive.project.domain.domainServices.iDomainServices.IUserStoryDomainService;
import switchfive.project.domain.factories.iFactories.UserStoryFactory;
import switchfive.project.domain.shared.valueObjects.*;
import switchfive.project.domain.shared.valueObjects.ProjectCode;
import switchfive.project.domain.shared.valueObjects.SprintID;
import switchfive.project.domain.shared.valueObjects.UserStoryCode;
import switchfive.project.domain.shared.valueObjects.UserStoryDescription;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImplAppUserStoryServiceTest {

    @Mock
    UserStoryFactory userStoryFactory;
    @Mock
    IUserStoryRepository iUserStoryStore;
    @Mock
    IProjectRepository iProjectStore;

    @Mock
    ISprintRepository iSprintRepository;
    @Mock
    IUserStoryDomainService iUserStoryDomainService;

    @InjectMocks
    ImplAppUserStoryService implUserStoryService;


    @Test
    void createAndAddUserStorySuccessful() throws ParseException {
        //Arrange
        UserStoryDTO userStoryDTOmock = mock(UserStoryDTO.class);
        Project projectMock = mock(Project.class);
        UserStory userStoryMock = mock(UserStory.class);

        Optional<Project> optionalProject = Optional.of(projectMock);
        Optional<UserStory> optionalUserStory = Optional.of(userStoryMock);

        when(iProjectStore.findByCode(any(ProjectCode.class))).thenReturn(optionalProject);
        when(iUserStoryStore.generatorCode(any(ProjectCode.class))).thenReturn("US1");
        when(iUserStoryStore.nextUserStoryNumber(any(ProjectCode.class))).thenReturn(1);
        when(userStoryFactory.createUserStory(any(), any(), any(), any(), any())).thenReturn(userStoryMock);
        when(iUserStoryStore.save(any())).thenReturn(optionalUserStory);

        MockedStatic userStoryAssemblerMockedStatic = mockStatic(UserStoryMapper.class);
        when(UserStoryMapper.toUserStoryDTO(userStoryMock)).thenReturn(userStoryDTOmock);

        //Act
        implUserStoryService.
                createAndAddUserStory("P0101", "ABC");

        //Assert
        userStoryAssemblerMockedStatic.close();
        verify(iUserStoryStore, times(1)).save(userStoryMock);

    }

    @Test
    void createAndAddUserStoryWithoutProject() throws ParseException {

        //Assert
        assertThrows(IllegalArgumentException.class,
                () -> {
                    implUserStoryService.createAndAddUserStory("P0101", "ABC");
                });
    }

    @Test
    void getUserStoryListByPriority() throws ParseException {
        //Arrange
        UserStoryDTO userStoryDTOmock = mock(UserStoryDTO.class);
        Project projectMock = mock(Project.class);
        UserStory userStoryMock = mock(UserStory.class);
        List<UserStory> userStoryList = new ArrayList<>();
        userStoryList.add(userStoryMock);

        Optional<Project> optionalProject = Optional.of(projectMock);

        when(iProjectStore.findByCode(any(ProjectCode.class))).thenReturn(optionalProject);
        when(iUserStoryStore.getUserStoryListProductBacklog(any())).thenReturn(userStoryList);

        MockedStatic userStoryAssemblerMockedStatic = mockStatic(UserStoryMapper.class);
        when(UserStoryMapper.toUserStoryDTO(userStoryMock)).thenReturn(userStoryDTOmock);

        //Act

        List<UserStoryDTO> productBacklog = implUserStoryService.
                getUserStoryListByPriority("P0101");

        //Assert
        userStoryAssemblerMockedStatic.close();
        assertEquals(1, productBacklog.size());
    }

    @Test
    void getUserStoryListByPriorityWithoutProject() throws ParseException {

        //Assert
        assertThrows(IllegalArgumentException.class,
                () -> {
                    implUserStoryService.getUserStoryListByPriority("P0101");
                });
    }

    @Test
    void getUserStoryDTOtrue() throws ParseException {
        //Arrange
        Project projectMock = mock(Project.class);
        Optional<Project> optionalProject = Optional.of(projectMock);
        UserStory userStoryMock = mock(UserStory.class);
        Optional<UserStory> optionalUserStory = Optional.of(userStoryMock);
        UserStoryDTO userStoryDTOmock = mock(UserStoryDTO.class);
        MockedStatic userStoryAssemblerMockedStatic = mockStatic(UserStoryMapper.class);

        when(iProjectStore.findByCode(any())).thenReturn(optionalProject);
        when(iUserStoryStore.getUserStory(any(), any())).thenReturn(optionalUserStory);
        when(UserStoryMapper.toUserStoryDTO(userStoryMock)).thenReturn(userStoryDTOmock);

        //Act
        Optional<UserStoryDTO> userStoryDTO = implUserStoryService.getUserStoryDTO("P0102", "US1");

        //Assert
        userStoryAssemblerMockedStatic.close();
        assertTrue(userStoryDTO.isPresent());

    }

    @Test
    void getUserStoryDTOWithoutProject() throws ParseException {

        //Assert
        assertThrows(IllegalArgumentException.class,
                () -> {
                    implUserStoryService.getUserStoryDTO("P0102", "US1");
                });

    }

    /*@Test
    void refineUserStory() throws ParseException {
        // Arrange
        ArrayList<String> newDescriptions = new ArrayList<>();
        newDescriptions.add("First Description");
        newDescriptions.add("Second Description");

        RefineUserStoryDTO refineUserStoryDTO = new RefineUserStoryDTO();
        refineUserStoryDTO.setUserStoryCode("US1");
        refineUserStoryDTO.setNewUserStoryDescription(newDescriptions);
        refineUserStoryDTO.setProjectCode("PROJ1");

        Project projectMock = mock(Project.class);
        when(iProjectStore.findByCode(any()))
                .thenReturn(Optional.of(projectMock));
        when(projectMock.isProjectClosed()).thenReturn(false);

        UserStory parentUSmock = mock(UserStory.class);
        when(iUserStoryStore.getUserStory(any(), any()))
                .thenReturn(Optional.of(parentUSmock));
        when(parentUSmock.isUserStoryAvailableForRefinement()).thenReturn(true);


        UserStoryDTO userStoryDTO1 = new UserStoryDTO();
        UserStoryDTO userStoryDTO2 = new UserStoryDTO();

        ProjectCode projectCode = ProjectCode.createProjectCode("PROJ1");
        UserStoryCode usCode = UserStoryCode.createUserStoryCode("US1");
        UserStoryDescription firstUSDescription = UserStoryDescription
                .createUserStoryDescription("First Description");
        UserStoryDescription secondUSDescription = UserStoryDescription
                .createUserStoryDescription("Second Description");

        doReturn(userStoryDTO1).when(implAppCreateUserStoryService).createNewUSWithParentCode(projectCode, usCode
                , firstUSDescription);
        doReturn(userStoryDTO2).when(implAppCreateUserStoryService).createNewUSWithParentCode(projectCode, usCode
                , secondUSDescription);

        MockedStatic userStoryAssemblerMockStatic = mockStatic(UserStoryAssembler.class);
        UserStoryDTO parentUSDTO = new UserStoryDTO();
        when(UserStoryAssembler.toUserStoryDTO(parentUSmock)).thenReturn(parentUSDTO);


        ArrayList<UserStoryDTO> expected = new ArrayList<>();
        expected.add(userStoryDTO1);
        expected.add(userStoryDTO2);
        expected.add(parentUSDTO);

        // Act

        ArrayList<UserStoryDTO> result =
                implAppCreateUserStoryService.refineUserStory(refineUserStoryDTO);


        // Assert
        userStoryAssemblerMockStatic.close();
        assertEquals(expected, result);
        verify(parentUSmock).setStatusToRefined();
        verify(iUserStoryStore).save(parentUSmock);
    }*/

    @Test
    void refineUserStoryEmptyInputDescriptions() throws ParseException {
        // Arrange
        ArrayList<String> newDescriptions = new ArrayList<>();

        RefineUserStoryDTO refineUserStoryDTO = new RefineUserStoryDTO();
        refineUserStoryDTO.setUserStoryCode("US1");
        refineUserStoryDTO.setNewUserStoryDescription(newDescriptions);
        refineUserStoryDTO.setProjectCode("PROJ1");

        Project projectMock = mock(Project.class);
        when(iProjectStore.findByCode(any()))
                .thenReturn(Optional.of(projectMock));
        when(projectMock.isProjectClosed()).thenReturn(false);

        UserStory parentUSmock = mock(UserStory.class);
        when(iUserStoryStore.getUserStory(any(), any()))
                .thenReturn(Optional.of(parentUSmock));
        when(parentUSmock.isUserStoryAvailableForRefinement()).thenReturn(true);

        // Act

        assertThrows(IllegalArgumentException.class,
                () -> implUserStoryService.refineUserStory(refineUserStoryDTO));
    }

    @Test
    void refineUserStoryUSNotAvailableForRefinement() throws ParseException {
        // Arrange
        ArrayList<String> newDescriptions = new ArrayList<>();

        RefineUserStoryDTO refineUserStoryDTO = new RefineUserStoryDTO();
        refineUserStoryDTO.setUserStoryCode("US1");
        refineUserStoryDTO.setNewUserStoryDescription(newDescriptions);
        refineUserStoryDTO.setProjectCode("PROJ1");

        Project projectMock = mock(Project.class);
        when(iProjectStore.findByCode(any()))
                .thenReturn(Optional.of(projectMock));
        when(projectMock.isProjectClosed()).thenReturn(false);

        UserStory parentUSmock = mock(UserStory.class);
        when(iUserStoryStore.getUserStory(any(), any()))
                .thenReturn(Optional.of(parentUSmock));
        when(parentUSmock.isUserStoryAvailableForRefinement()).thenReturn(false);

        // Act

        assertThrows(UnsupportedOperationException.class,
                () -> implUserStoryService.refineUserStory(refineUserStoryDTO));

    }

    @Test
    void refineUserStoryParentUSNotFound() throws ParseException {
        // Arrange
        ArrayList<String> newDescriptions = new ArrayList<>();

        RefineUserStoryDTO refineUserStoryDTO = new RefineUserStoryDTO();
        refineUserStoryDTO.setUserStoryCode("US1");
        refineUserStoryDTO.setNewUserStoryDescription(newDescriptions);
        refineUserStoryDTO.setProjectCode("PROJ1");

        Project projectMock = mock(Project.class);
        when(iProjectStore.findByCode(any()))
                .thenReturn(Optional.of(projectMock));
        when(projectMock.isProjectClosed()).thenReturn(false);

        UserStory parentUSmock = mock(UserStory.class);
        when(iUserStoryStore.getUserStory(any(), any()))
                .thenReturn(Optional.empty());

        // Act

        assertThrows(IllegalArgumentException.class,
                () -> implUserStoryService.refineUserStory(refineUserStoryDTO));

    }

    @Test
    void refineUserStoryProjectFinished() throws ParseException {
        // Arrange
        ArrayList<String> newDescriptions = new ArrayList<>();

        RefineUserStoryDTO refineUserStoryDTO = new RefineUserStoryDTO();
        refineUserStoryDTO.setUserStoryCode("US1");
        refineUserStoryDTO.setNewUserStoryDescription(newDescriptions);
        refineUserStoryDTO.setProjectCode("PROJ1");

        Project projectMock = mock(Project.class);
        when(iProjectStore.findByCode(any()))
                .thenReturn(Optional.of(projectMock));
        when(projectMock.isProjectClosed()).thenReturn(true);

        // Act

        assertThrows(UnsupportedOperationException.class,
                () -> implUserStoryService.refineUserStory(refineUserStoryDTO));

    }

    @Test
    void refineUserStoryProjectNotFound() throws ParseException {
        // Arrange
        ArrayList<String> newDescriptions = new ArrayList<>();

        RefineUserStoryDTO refineUserStoryDTO = new RefineUserStoryDTO();
        refineUserStoryDTO.setUserStoryCode("US1");
        refineUserStoryDTO.setNewUserStoryDescription(newDescriptions);
        refineUserStoryDTO.setProjectCode("PROJ1");

        Project projectMock = mock(Project.class);
        when(iProjectStore.findByCode(any()))
                .thenReturn(Optional.empty());

        // Act

        assertThrows(IllegalArgumentException.class,
                () -> implUserStoryService.refineUserStory(refineUserStoryDTO));

    }

    @Test
    void createNewUSWithParentCode() throws ParseException {
        // Arrange
        ProjectCode projectCode = ProjectCode.create("PROJ1");
        UserStoryCode parentUserStoryCode = UserStoryCode.createUserStoryCode("US1");
        UserStoryDescription userStoryDescription = UserStoryDescription
                .createUserStoryDescription("Valid description");

        Project projectMock = mock(Project.class);
        when(iProjectStore.findByCode(any()))
                .thenReturn(Optional.of(projectMock));

        when(iUserStoryStore.generatorCode(projectCode)).thenReturn("US2");
        when(iUserStoryStore.nextUserStoryNumber(projectCode)).thenReturn(1);

        UserStoryDTO userStoryDTOexpected = new UserStoryDTO();
        MockedStatic<UserStoryMapper> userStoryAssemblerMockStatic = mockStatic(UserStoryMapper.class);
        when(UserStoryMapper.toUserStoryDTO(any()))
                .thenReturn(userStoryDTOexpected);


        // Assert
        userStoryAssemblerMockStatic.close();
        assertThrows(UnsupportedOperationException.class,
                () -> implUserStoryService.createNewUSWithParentCode(projectCode,
                        parentUserStoryCode, userStoryDescription));
    }

    @Test
    void createNewUSWithParentCodeProjectNotFound() throws ParseException {
        // Arrange
        ProjectCode projectCode = ProjectCode.create("PROJ1");
        UserStoryCode parentUserStoryCode = UserStoryCode.createUserStoryCode("US1");
        UserStoryDescription userStoryDescription = UserStoryDescription
                .createUserStoryDescription("Valid description");

        Project projectMock = mock(Project.class);
        when(iProjectStore.findByCode(any()))
                .thenReturn(Optional.empty());

        // Act
        assertThrows(IllegalArgumentException.class,
                () -> implUserStoryService.createNewUSWithParentCode(projectCode,
                        parentUserStoryCode, userStoryDescription));
    }

    @Test
    void shouldReturnAnEmptyDTOWhenProjectCodeDoesNotExists() throws ParseException {
        Optional<Project> emptyProject = Optional.empty();
        MoveUserStoryDTO moveUserStoryDTO = new MoveUserStoryDTO();
        moveUserStoryDTO.setSprintID(1);
        doReturn(emptyProject).when(iProjectStore).findByCode(any(ProjectCode.class));
        Optional<MoveUserStoryDTO> expected = Optional.empty();

        Optional<MoveUserStoryDTO> actual = implUserStoryService.moveUSFromProductBacklogToSprintBacklog(
                "A9999",
                "US1",
                moveUserStoryDTO);

        assertEquals(expected, actual);
        verify(iProjectStore, times(1)).findByCode(any(ProjectCode.class));

    }

    @Test
    void shouldReturnAnEmptyDTOWhenSprintDoesNotExists() throws ParseException {
        Project projectMock = mock(Project.class);
        Optional<Project> presentProject = Optional.of(projectMock);
        Optional<Sprint> emptySprint = Optional.empty();
        MoveUserStoryDTO moveUserStoryDTO = new MoveUserStoryDTO();
        moveUserStoryDTO.setSprintID(1);
        doReturn(presentProject).when(iProjectStore).findByCode(any(ProjectCode.class));
        doReturn(emptySprint).when(iSprintRepository).findBySprintID(any(SprintID.class));
        Optional<MoveUserStoryDTO> expected = Optional.empty();

        Optional<MoveUserStoryDTO> actual = implUserStoryService.moveUSFromProductBacklogToSprintBacklog(
                "A9999",
                "US1",
                moveUserStoryDTO);

        assertEquals(expected, actual);
        verify(iSprintRepository, times(1)).findBySprintID(any(SprintID.class));
    }

    @Test
    void shouldReturnAnEmptyDTOWhenUserStoryDoesNotExists() throws ParseException {
        Project projectMock = mock(Project.class);
        Sprint sprintMock = mock(Sprint.class);
        Optional<Project> presentProject = Optional.of(projectMock);
        Optional<Sprint> presentSprint = Optional.of(sprintMock);
        Optional<UserStory> emptyUserStory = Optional.empty();
        MoveUserStoryDTO moveUserStoryDTO = new MoveUserStoryDTO();
        moveUserStoryDTO.setSprintID(1);
        doReturn(presentProject).when(iProjectStore).findByCode(any(ProjectCode.class));
        doReturn(presentSprint).when(iSprintRepository).findBySprintID(any(SprintID.class));
        doReturn(emptyUserStory).when(iUserStoryStore).getUserStory(any(ProjectCode.class), any(UserStoryCode.class));
        Optional<MoveUserStoryDTO> expected = Optional.empty();

        Optional<MoveUserStoryDTO> actual = implUserStoryService.moveUSFromProductBacklogToSprintBacklog(
                "P0001",
                "US1",
                moveUserStoryDTO);

        assertEquals(expected, actual);
        verify(iUserStoryStore, times(1)).getUserStory(any(ProjectCode.class), any(UserStoryCode.class));
    }

    @Test
    void shouldReturnAnEmptyDTOWhenUserStoryIsNotMovable() throws ParseException {
        Project projectMock = mock(Project.class);
        Sprint sprintMock = mock(Sprint.class);
        UserStory userStoryMock = mock(UserStory.class);
        Optional<Project> presentProject = Optional.of(projectMock);
        Optional<Sprint> presentSprint = Optional.of(sprintMock);
        Optional<UserStory> presentUserStory = Optional.of(userStoryMock);
        MoveUserStoryDTO moveUserStoryDTO = new MoveUserStoryDTO();
        moveUserStoryDTO.setSprintID(1);
        doReturn(presentProject).when(iProjectStore).findByCode(any(ProjectCode.class));
        doReturn(presentSprint).when(iSprintRepository).findBySprintID(any(SprintID.class));
        doReturn(presentUserStory).when(iUserStoryStore).getUserStory(any(ProjectCode.class), any(UserStoryCode.class));
        doReturn(false).when(iUserStoryDomainService).canUSBeMovedFromProductBacklogToSprintBacklog(
                any(Project.class),
                any(Sprint.class),
                any(UserStory.class));
        Optional<MoveUserStoryDTO> expected = Optional.empty();

        Optional<MoveUserStoryDTO> actual = implUserStoryService.moveUSFromProductBacklogToSprintBacklog(
                "P0001",
                "US1",
                moveUserStoryDTO);

        assertEquals(expected, actual);
        verify(iUserStoryDomainService, times(1)).canUSBeMovedFromProductBacklogToSprintBacklog(
                any(Project.class),
                any(Sprint.class),
                any(UserStory.class));
    }

    @Test
    void shouldReturnTheSavedUserStoryWhenTheUserStoryIsMovable() throws ParseException {
        Project projectMock = mock(Project.class);
        Sprint sprintMock = mock(Sprint.class);
        UserStory userStoryMock = mock(UserStory.class);
        Optional<Project> presentProject = Optional.of(projectMock);
        Optional<Sprint> presentSprint = Optional.of(sprintMock);
        Optional<UserStory> presentUserStory = Optional.of(userStoryMock);
        MockedStatic userStoryAssembler = mockStatic(UserStoryMapper.class);
        MoveUserStoryDTO moveUserStoryDTO = new MoveUserStoryDTO();
        moveUserStoryDTO.setSprintID(1);
        doReturn(presentProject).when(iProjectStore).findByCode(any(ProjectCode.class));
        doReturn(presentSprint).when(iSprintRepository).findBySprintID(any(SprintID.class));
        doReturn(presentUserStory).when(iUserStoryStore).getUserStory(any(ProjectCode.class), any(UserStoryCode.class));
        doReturn(true).when(iUserStoryDomainService).canUSBeMovedFromProductBacklogToSprintBacklog(
                any(Project.class),
                any(Sprint.class),
                any(UserStory.class));
        doNothing().when(userStoryMock).setSprintID(anyString(), anyInt());
        doReturn(presentUserStory).when(iUserStoryStore).save(any(UserStory.class));
        when(UserStoryMapper.toMoveUserStoryDTO(any())).thenReturn(moveUserStoryDTO);

        Optional<MoveUserStoryDTO> expected = Optional.of(moveUserStoryDTO);

        Optional<MoveUserStoryDTO> actual = implUserStoryService.moveUSFromProductBacklogToSprintBacklog(
                "P0001",
                "US1",
                moveUserStoryDTO);

        assertEquals(expected, actual);
        verify(iUserStoryStore, times(1)).save(any(UserStory.class));
        userStoryAssembler.close();
    }

    @Test
    void userStoryChangeStatusWithoutProject() {
        //Assert
        assertThrows(IllegalArgumentException.class,
                () -> {
                    implUserStoryService.userStoryChangeStatus("P0101",
                            "US1",
                            "RUNNING");
                });
    }

    @Test
    void userStoryChangeStatusWithoutUserStory() throws ParseException {
        //Assert
        Optional<UserStory> optionalUserStory = Optional.empty();

        when(iProjectStore.projectExists(any())).thenReturn(true);
        when(iUserStoryStore.getUserStory(any(), any())).thenReturn(optionalUserStory);

        //Act
        Optional<UserStoryDTO> userStoryDTOExpected =
                implUserStoryService.userStoryChangeStatus("P0101",
                        "US1",
                        "RUNNING");

        //Assert
        assertTrue(userStoryDTOExpected.isEmpty());
    }

    @Test
    void userStoryChangeStatusWithoutSprint() throws ParseException {
        //Assert
        UserStory userStory = new UserStory(UserStoryID.createUserStoryID("P0101", "US2"),
                ProjectCode.create("P0101"),
                UserStoryCode.createUserStoryCode("US2"),
                Priority.createPriority(1),
                UserStoryDescription.createUserStoryDescription("ABC"),
                SprintID.createSprintID("P0101", 0),
                EffortEstimate.createEffortEstimate(8), UserStoryStatus.PLANNED,
                UserStoryCode.createUserStoryCode("US1"));

        //UserStory userStoryMock = mock(UserStory.class);
        Optional<UserStory> optionalUserStory = Optional.of(userStory);

        when(iProjectStore.projectExists(any())).thenReturn(true);
        when(iUserStoryStore.getUserStory(any(), any())).thenReturn(optionalUserStory);

        //Act
        Optional<UserStoryDTO> userStoryDTOExpected =
                implUserStoryService.userStoryChangeStatus("P0101",
                        "US2",
                        "RUNNING");

        //Assert
        assertTrue(userStoryDTOExpected.isEmpty());
    }

    @Test
    void userStoryChangeStatusWithSameStatus() throws ParseException {
        //Assert
        UserStory userStory = new UserStory(UserStoryID.createUserStoryID("P0101", "US2"),
                ProjectCode.create("P0101"),
                UserStoryCode.createUserStoryCode("US2"),
                Priority.createPriority(1),
                UserStoryDescription.createUserStoryDescription("ABC"),
                SprintID.createSprintID("P0101", 1),
                EffortEstimate.createEffortEstimate(8), UserStoryStatus.PLANNED,
                UserStoryCode.createUserStoryCode("US1"));

        Optional<UserStory> optionalUserStory = Optional.of(userStory);

        when(iProjectStore.projectExists(any())).thenReturn(true);
        when(iUserStoryStore.getUserStory(any(), any())).thenReturn(optionalUserStory);

        //Act
        Optional<UserStoryDTO> userStoryDTOExpected =
                implUserStoryService.userStoryChangeStatus("P0101",
                        "US2",
                        "PLANNED");

        //Assert
        assertTrue(userStoryDTOExpected.isEmpty());
    }

    @Test
    void userStoryChangeStatusSuccessful() throws ParseException {
        //Assert
        UserStory userStory = new UserStory(UserStoryID.createUserStoryID("P0101", "US2"),
                ProjectCode.create("P0101"),
                UserStoryCode.createUserStoryCode("US2"),
                Priority.createPriority(1),
                UserStoryDescription.createUserStoryDescription("ABC"),
                SprintID.createSprintID("P0101", 1),
                EffortEstimate.createEffortEstimate(8), UserStoryStatus.PLANNED,
                UserStoryCode.createUserStoryCode("US1"));

        Optional<UserStory> optionalUserStory = Optional.of(userStory);
        UserStory userStoryMock = mock(UserStory.class);
        Optional<UserStory> userStoryOptionalMock = Optional.of(userStoryMock);

        when(iProjectStore.projectExists(any())).thenReturn(true);
        when(iUserStoryStore.getUserStory(any(), any())).thenReturn(optionalUserStory);
        when(iUserStoryStore.save(any())).thenReturn(userStoryOptionalMock);

        MockedStatic userStoryAssembler = mockStatic(UserStoryMapper.class);

        UserStoryDTO userStoryDTOMock = mock(UserStoryDTO.class);
        when(UserStoryMapper.toUserStoryDTO(any())).thenReturn(userStoryDTOMock);

        //Act
        Optional<UserStoryDTO> userStoryDTOExpected =
                implUserStoryService.userStoryChangeStatus("P0101",
                        "US2",
                        "RUNNING");

        //Assert
        userStoryAssembler.close();
        assertTrue(userStoryDTOExpected.isPresent());
        assertEquals(optionalUserStory.get().getStatus(), "RUNNING");

    }

    @Test
    void userStoryChangePriority() {
        // Arrange
        String projectCode = "P1010";
        ProjectCode projectCodeObj = ProjectCode.create(projectCode);
        int newPriority = 4;

        List<UserStory> userStoriesInProductBacklog = new ArrayList<>();

        UserStoryID userStoryID1 = UserStoryID.createUserStoryID(projectCode, "US1");
        UserStoryCode userStoryCode1 = UserStoryCode.createUserStoryCode("US1");
        Priority priorityUS1 = Priority.createPriority(1);
        UserStoryDescription userStoryDescriptionUS1 = UserStoryDescription
                .createUserStoryDescription("First User Story");

        UserStory userStoryOne = new UserStory(userStoryID1,projectCodeObj,
                userStoryCode1,priorityUS1,userStoryDescriptionUS1);

        UserStoryID userStoryID2 = UserStoryID.createUserStoryID(projectCode, "US2");
        UserStoryCode userStoryCode2 = UserStoryCode.createUserStoryCode("US2");
        Priority priorityUS2 = Priority.createPriority(2);
        UserStoryDescription userStoryDescriptionUS2 = UserStoryDescription
                .createUserStoryDescription("Second User Story");

        UserStory userStoryTwo = new UserStory(userStoryID2,projectCodeObj,
                userStoryCode2,priorityUS2,userStoryDescriptionUS2);

        UserStoryID userStoryID3 = UserStoryID.createUserStoryID(projectCode, "US3");
        UserStoryCode userStoryCode3 = UserStoryCode.createUserStoryCode("US3");
        Priority priorityUS3 = Priority.createPriority(3);
        UserStoryDescription userStoryDescriptionUS3 = UserStoryDescription
                .createUserStoryDescription("Third User Story");

        UserStory userStoryThree = new UserStory(userStoryID3,projectCodeObj,
                userStoryCode3,priorityUS3,userStoryDescriptionUS3);

        UserStoryID userStoryID4 = UserStoryID.createUserStoryID(projectCode, "US4");
        UserStoryCode userStoryCode4 = UserStoryCode.createUserStoryCode("US4");
        Priority priorityUS4 = Priority.createPriority(4);
        UserStoryDescription userStoryDescriptionUS4 = UserStoryDescription
                .createUserStoryDescription("Fourth User Story");

        UserStory userStoryFour = new UserStory(userStoryID4,projectCodeObj,
                userStoryCode4,priorityUS4,userStoryDescriptionUS4);

        UserStoryID userStoryID5 = UserStoryID.createUserStoryID(projectCode, "US5");
        UserStoryCode userStoryCode5 = UserStoryCode.createUserStoryCode("US5");
        Priority priorityUS5 = Priority.createPriority(5);
        UserStoryDescription userStoryDescriptionUS5 = UserStoryDescription
                .createUserStoryDescription("Fifth User Story");

        UserStory userStoryFive = new UserStory(userStoryID5,projectCodeObj,
                userStoryCode5,priorityUS5,userStoryDescriptionUS5);


        userStoriesInProductBacklog.add(userStoryOne);
        userStoriesInProductBacklog.add(userStoryTwo);
        userStoriesInProductBacklog.add(userStoryThree);
        userStoriesInProductBacklog.add(userStoryFour);
        userStoriesInProductBacklog.add(userStoryFive);


        when(iUserStoryStore.getUserStory(any(), any()))
                .thenReturn(Optional.of(userStoryTwo));

        when(iUserStoryStore.getUserStoryListProductBacklog(any()))
                .thenReturn(userStoriesInProductBacklog);

        when(iUserStoryStore.save(any()))
                .thenReturn(Optional.of(userStoryTwo));

        UserStoryDTO expected = new UserStoryDTO();
        expected.setPriority(4);

        // Act
        Optional<UserStoryDTO> actual = implUserStoryService
                .userStoryChangePriority(projectCode, "US2", newPriority);

        // Assert
        assertEquals(expected.getPriority(), actual.get().getPriority());
        assertEquals(2, userStoryThree.getPriority());
        assertEquals(3, userStoryFour.getPriority());
    }

    @Test
    void userStoryChangePriorityNewPriorityBelowOld() {
        // Arrange
        String projectCode = "P1010";
        ProjectCode projectCodeObj = ProjectCode.create(projectCode);
        int newPriority = 2;

        List<UserStory> userStoriesInProductBacklog = new ArrayList<>();

        UserStoryID userStoryID1 = UserStoryID.createUserStoryID(projectCode, "US1");
        UserStoryCode userStoryCode1 = UserStoryCode.createUserStoryCode("US1");
        Priority priorityUS1 = Priority.createPriority(1);
        UserStoryDescription userStoryDescriptionUS1 = UserStoryDescription
                .createUserStoryDescription("First User Story");

        UserStory userStoryOne = new UserStory(userStoryID1,projectCodeObj,
                userStoryCode1,priorityUS1,userStoryDescriptionUS1);

        UserStoryID userStoryID2 = UserStoryID.createUserStoryID(projectCode, "US2");
        UserStoryCode userStoryCode2 = UserStoryCode.createUserStoryCode("US2");
        Priority priorityUS2 = Priority.createPriority(2);
        UserStoryDescription userStoryDescriptionUS2 = UserStoryDescription
                .createUserStoryDescription("Second User Story");

        UserStory userStoryTwo = new UserStory(userStoryID2,projectCodeObj,
                userStoryCode2,priorityUS2,userStoryDescriptionUS2);

        UserStoryID userStoryID3 = UserStoryID.createUserStoryID(projectCode, "US3");
        UserStoryCode userStoryCode3 = UserStoryCode.createUserStoryCode("US3");
        Priority priorityUS3 = Priority.createPriority(3);
        UserStoryDescription userStoryDescriptionUS3 = UserStoryDescription
                .createUserStoryDescription("Third User Story");

        UserStory userStoryThree = new UserStory(userStoryID3,projectCodeObj,
                userStoryCode3,priorityUS3,userStoryDescriptionUS3);

        UserStoryID userStoryID4 = UserStoryID.createUserStoryID(projectCode, "US4");
        UserStoryCode userStoryCode4 = UserStoryCode.createUserStoryCode("US4");
        Priority priorityUS4 = Priority.createPriority(4);
        UserStoryDescription userStoryDescriptionUS4 = UserStoryDescription
                .createUserStoryDescription("Fourth User Story");

        UserStory userStoryFour = new UserStory(userStoryID4,projectCodeObj,
                userStoryCode4,priorityUS4,userStoryDescriptionUS4);

        UserStoryID userStoryID5 = UserStoryID.createUserStoryID(projectCode, "US5");
        UserStoryCode userStoryCode5 = UserStoryCode.createUserStoryCode("US5");
        Priority priorityUS5 = Priority.createPriority(5);
        UserStoryDescription userStoryDescriptionUS5 = UserStoryDescription
                .createUserStoryDescription("Fifth User Story");

        UserStory userStoryFive = new UserStory(userStoryID5,projectCodeObj,
                userStoryCode5,priorityUS5,userStoryDescriptionUS5);

        userStoriesInProductBacklog.add(userStoryOne);
        userStoriesInProductBacklog.add(userStoryTwo);
        userStoriesInProductBacklog.add(userStoryThree);
        userStoriesInProductBacklog.add(userStoryFour);
        userStoriesInProductBacklog.add(userStoryFive);

        when(iUserStoryStore.getUserStory(any(), any()))
                .thenReturn(Optional.of(userStoryFour));

        when(iUserStoryStore.getUserStoryListProductBacklog(any()))
                .thenReturn(userStoriesInProductBacklog);

        when(iUserStoryStore.save(any()))
                .thenReturn(Optional.of(userStoryFour));

        UserStoryDTO expected = new UserStoryDTO();
        expected.setPriority(2);

        // Act
        Optional<UserStoryDTO> actual = implUserStoryService
                .userStoryChangePriority(projectCode, "US4", newPriority);

        // Assert
        assertEquals(expected.getPriority(), actual.get().getPriority());
        assertEquals(3, userStoryTwo.getPriority());
        assertEquals(4, userStoryThree.getPriority());

    }

    @Test
    void userStoryChangePriorityFailurePriorityAboveListSize() {

        // Arrange
        String projectCode = "P1010";
        ProjectCode projectCodeObj = ProjectCode.create(projectCode);
        int newPriority = 3;

        UserStoryID userStoryID1 = UserStoryID.createUserStoryID(projectCode, "US1");
        UserStoryCode userStoryCode1 = UserStoryCode.createUserStoryCode("US1");
        Priority priorityUS1 = Priority.createPriority(1);
        UserStoryDescription userStoryDescriptionUS1 = UserStoryDescription
                .createUserStoryDescription("First User Story");

        UserStory userStoryOne = new UserStory(userStoryID1,projectCodeObj,
                userStoryCode1,priorityUS1,userStoryDescriptionUS1);

        List<UserStory> userStoriesInProductBacklog = new ArrayList<>();
        userStoriesInProductBacklog.add(userStoryOne);

        when(iUserStoryStore.getUserStory(any(), any()))
                .thenReturn(Optional.of(userStoryOne));

        when(iUserStoryStore.getUserStoryListProductBacklog(any()))
                .thenReturn(userStoriesInProductBacklog);

        // Act
        Optional<UserStoryDTO> actual = implUserStoryService
                .userStoryChangePriority(projectCode, "US1", newPriority);

        // Assert
        assertEquals(Optional.empty(), actual);
    }

    @Test
    void userStoryChangePriorityNewPrioritySamePriority() {
        // Arrange
        String projectCode = "P1010";
        ProjectCode projectCodeObj = ProjectCode.create(projectCode);
        int newPriority = 1;

        List<UserStory> userStoriesInProductBacklog = new ArrayList<>();

        UserStoryID userStoryID1 = UserStoryID.createUserStoryID(projectCode, "US1");
        UserStoryCode userStoryCode1 = UserStoryCode.createUserStoryCode("US1");
        Priority priorityUS1 = Priority.createPriority(1);
        UserStoryDescription userStoryDescriptionUS1 = UserStoryDescription
                .createUserStoryDescription("First User Story");

        UserStory userStoryOne = new UserStory(userStoryID1,projectCodeObj,
                userStoryCode1,priorityUS1,userStoryDescriptionUS1);

        userStoriesInProductBacklog.add(userStoryOne);

        when(iUserStoryStore.getUserStory(any(), any()))
                .thenReturn(Optional.of(userStoryOne));

        when(iUserStoryStore.getUserStoryListProductBacklog(any()))
                .thenReturn(userStoriesInProductBacklog);

        when(iUserStoryStore.save(any()))
                .thenReturn(Optional.of(userStoryOne));

        UserStoryDTO expected = new UserStoryDTO();
        expected.setPriority(1);

        // Act
        Optional<UserStoryDTO> actual = implUserStoryService
                .userStoryChangePriority(projectCode, "US1", newPriority);

        // Assert
        assertEquals(expected.getPriority(), actual.get().getPriority());
    }



}


