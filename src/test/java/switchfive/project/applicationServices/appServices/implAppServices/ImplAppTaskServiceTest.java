package switchfive.project.applicationServices.appServices.implAppServices;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import switchfive.project.dtos.TaskCreationDTO;
import switchfive.project.dtos.TaskDTO;
import switchfive.project.dtos.TaskIdDTO;
import switchfive.project.applicationServices.assemblers.implAssemblers.TaskAssembler;
import switchfive.project.applicationServices.iRepositories.*;
import switchfive.project.domain.aggregates.resource.Resource;
import switchfive.project.domain.aggregates.sprint.Sprint;
import switchfive.project.domain.aggregates.task.Task;
import switchfive.project.domain.aggregates.userStory.UserStory;
import switchfive.project.domain.factories.iFactories.ITaskFactory;
import switchfive.project.domain.shared.valueObjects.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImplAppTaskServiceTest {

    @Mock
    ITaskRepository taskRepositoryMock;

    @Mock
    ITaskFactory taskFactoryMock;

    @Mock
    IResourceRepository resourceRepositoryMock;

    @Mock
    ISprintRepository sprintRepositoryMock;

    @Mock
    IUserStoryRepository userStoryRepositoryMock;

    @InjectMocks
    ImplAppTaskService taskService;

    @Test
    void createAndSaveTaskInUserStoryWithoutPrecedenceSuccessfully() throws ParseException {
        String projectCode = "A0001";
        String userStoryCode = "US1";

        TaskCreationDTO taskCreationDTO = new TaskCreationDTO();
        taskCreationDTO.setNameDto("Dummy task");
        taskCreationDTO.setProjectCodeDto(projectCode);
        taskCreationDTO.setUserStoryCode(userStoryCode);
        taskCreationDTO.setSprintNumberDto(null);
        taskCreationDTO.setTypeOfTaskDto("DESIGN");
        taskCreationDTO.setDescriptionDto("description");
        taskCreationDTO.setPrecedenceDto(null);
        taskCreationDTO.setStartDateDto("23/06/2023");
        taskCreationDTO.setEndDateDto("23/07/2023");
        taskCreationDTO.setResponsibleResourceUUIDDto("123e4567-e89b-12d3-a456-426614174000");

        ProjectCode projectCodeObj = ProjectCode.create(projectCode);
        String taskCode = "T1";
        when(taskRepositoryMock.nextTaskCode(projectCodeObj)).thenReturn(taskCode);

        TaskID taskID = mock(TaskID.class);
        MockedStatic<TaskID> taskIDMockedStatic = mockStatic(TaskID.class);
        when(TaskID.createIDTask(any(), any())).thenReturn(taskID);


        SprintID sprintIDMock = mock(SprintID.class);
        UserStory userStoryMock = mock(UserStory.class);
        when(userStoryMock.getSprintID()).thenReturn(sprintIDMock);
        when(userStoryRepositoryMock.getUserStory(any(), any())).thenReturn(Optional.of(userStoryMock));

        // Check Dates in Sprint
        Sprint sprintMock = mock(Sprint.class);
        when(sprintRepositoryMock.findBySprintID(any())).thenReturn(Optional.of(sprintMock));
        when(sprintMock.areDatesWithinSprintDates(any())).thenReturn(true);
        when(sprintMock.areSprintDatesInsideInputTime(any())).thenReturn(true);


        // Check Resource
        Resource resourceMock = mock(Resource.class);
        when(resourceRepositoryMock.getResourceByID(any())).thenReturn(Optional.of(resourceMock));
        when(resourceMock.isResourceInProject(any())).thenReturn(true);
        when(resourceMock.isTeamMember()).thenReturn(true);
        when(resourceMock.getStartDate()).thenReturn("22/06/2023");
        when(resourceMock.getEndDate()).thenReturn("24/07/2023");

        Task taskMock = mock(Task.class);
        when(taskFactoryMock.createTask(any(),any(),any(),any(),any(),anyList(),any(),any()))
                .thenReturn(taskMock);
        when(taskRepositoryMock.save(taskMock)).thenReturn(taskMock);


        TaskDTO taskDTOExpected = mock(TaskDTO.class);
        MockedStatic<TaskAssembler> taskAssemblerMockedStatic = mockStatic(TaskAssembler.class);
        when(TaskAssembler.toTaskDTO(taskMock)).thenReturn(taskDTOExpected);


        // -----------ACT------------------------
        Optional<TaskDTO> taskDTOResult = taskService.createAndSaveTask(taskCreationDTO);


        // ------------ASSERT--------------------
        taskIDMockedStatic.close();
        taskAssemblerMockedStatic.close();
        assertEquals(Optional.of(taskDTOExpected),taskDTOResult);



    }


    @Test
    void createAndSaveTaskInSprintWithoutPrecedenceSuccessfully() throws ParseException {
        String projectCode = "A0002";

        TaskCreationDTO taskCreationDTO = new TaskCreationDTO();
        taskCreationDTO.setNameDto("Dummy task");
        taskCreationDTO.setProjectCodeDto(projectCode);
        taskCreationDTO.setUserStoryCode(null);
        taskCreationDTO.setSprintNumberDto(1);
        taskCreationDTO.setTypeOfTaskDto("MEETING");
        taskCreationDTO.setDescriptionDto("description");
        taskCreationDTO.setPrecedenceDto(null);
        taskCreationDTO.setStartDateDto("23/06/2023");
        taskCreationDTO.setEndDateDto("23/07/2023");
        taskCreationDTO.setResponsibleResourceUUIDDto("123e4567-e89b-12d3-a456-426614174000");

        ProjectCode projectCodeObj = ProjectCode.create(projectCode);
        String taskCode = "T2";
        when(taskRepositoryMock.nextTaskCode(projectCodeObj)).thenReturn(taskCode);

        TaskID taskID = mock(TaskID.class);
        MockedStatic<TaskID> taskIDMockedStatic = mockStatic(TaskID.class);
        when(TaskID.createIDTask(any(), any())).thenReturn(taskID);


        SprintID sprintIDMock = mock(SprintID.class);

        // Check Dates in Sprint
        Sprint sprintMock = mock(Sprint.class);
        when(sprintRepositoryMock.findBySprintID(any())).thenReturn(Optional.of(sprintMock));
        when(sprintMock.areDatesWithinSprintDates(any())).thenReturn(true);
        when(sprintMock.areSprintDatesInsideInputTime(any())).thenReturn(true);


        // Check Resource
        Resource resourceMock = mock(Resource.class);
        when(resourceRepositoryMock.getResourceByID(any())).thenReturn(Optional.of(resourceMock));
        when(resourceMock.isResourceInProject(any())).thenReturn(true);
        when(resourceMock.isScrumMaster()).thenReturn(true);
        when(resourceMock.getStartDate()).thenReturn("22/06/2023");
        when(resourceMock.getEndDate()).thenReturn("24/07/2023");

        Task taskMock = mock(Task.class);
        when(taskFactoryMock.createTask(any(),any(),any(),any(),any(),anyList(),any(),any()))
                .thenReturn(taskMock);
        when(taskRepositoryMock.save(taskMock)).thenReturn(taskMock);


        TaskDTO taskDTOExpected = mock(TaskDTO.class);
        MockedStatic<TaskAssembler> taskAssemblerMockedStatic = mockStatic(TaskAssembler.class);
        when(TaskAssembler.toTaskDTO(taskMock)).thenReturn(taskDTOExpected);


        // -----------ACT------------------------
        Optional<TaskDTO> taskDTOResult = taskService.createAndSaveTask(taskCreationDTO);


        // ------------ASSERT--------------------
        taskIDMockedStatic.close();
        taskAssemblerMockedStatic.close();
        assertEquals(Optional.of(taskDTOExpected),taskDTOResult);


    }

    @Test
    void createAndSaveTaskInUserStoryWithPrecedenceSuccessfully() throws ParseException {
        String projectCode = "A0001";
        String userStoryCode = "US1";
        String taskCodeForPrecedence = "T1";
        String taskCode = "T2";

        TaskIdDTO taskIdDTO = new TaskIdDTO();
        taskIdDTO.setTaskCode(taskCodeForPrecedence);
        taskIdDTO.setProjectCode(projectCode);
        List<TaskIdDTO> precedenceList = new ArrayList<>();
        precedenceList.add(taskIdDTO);


        TaskCreationDTO taskCreationDTO = new TaskCreationDTO();
        taskCreationDTO.setNameDto("Dummy task");
        taskCreationDTO.setProjectCodeDto(projectCode);
        taskCreationDTO.setUserStoryCode(userStoryCode);
        taskCreationDTO.setSprintNumberDto(null);
        taskCreationDTO.setTypeOfTaskDto("DESIGN");
        taskCreationDTO.setDescriptionDto("description");
        taskCreationDTO.setPrecedenceDto(precedenceList);
        taskCreationDTO.setStartDateDto("23/06/2023");
        taskCreationDTO.setEndDateDto("23/07/2023");
        taskCreationDTO.setResponsibleResourceUUIDDto("123e4567-e89b-12d3-a456-426614174000");

        ProjectCode projectCodeObj = ProjectCode.create(projectCode);
        when(taskRepositoryMock.nextTaskCode(projectCodeObj)).thenReturn(taskCode);

        TaskID taskID = mock(TaskID.class);
        MockedStatic<TaskID> taskIDMockedStatic = mockStatic(TaskID.class);
        when(TaskID.createIDTask(any(), any())).thenReturn(taskID);

        SprintID sprintIDMock = mock(SprintID.class);
        UserStory userStoryMock = mock(UserStory.class);
        when(userStoryMock.getSprintID()).thenReturn(sprintIDMock);
        when(userStoryRepositoryMock.getUserStory(any(), any())).thenReturn(Optional.of(userStoryMock));

        // Check Dates in Sprint
        Sprint sprintMock = mock(Sprint.class);
        when(sprintRepositoryMock.findBySprintID(any())).thenReturn(Optional.of(sprintMock));
        when(sprintMock.areDatesWithinSprintDates(any())).thenReturn(true);
        when(sprintMock.areSprintDatesInsideInputTime(any())).thenReturn(true);

        // Check Resource
        Resource resourceMock = mock(Resource.class);
        when(resourceRepositoryMock.getResourceByID(any())).thenReturn(Optional.of(resourceMock));
        when(resourceMock.isResourceInProject(any())).thenReturn(true);
        when(resourceMock.isTeamMember()).thenReturn(true);
        when(resourceMock.getStartDate()).thenReturn("22/06/2023");
        when(resourceMock.getEndDate()).thenReturn("24/07/2023");

        Task taskMock = mock(Task.class);
        when(taskFactoryMock.createTask(any(),any(),any(),any(),any(),anyList(),any(),any()))
                .thenReturn(taskMock);
        when(taskRepositoryMock.save(taskMock)).thenReturn(taskMock);

        // Check Precedence

        when(taskRepositoryMock.findTaskById(any())).thenReturn(Optional.of(taskMock));


        TaskDTO taskDTOExpected = mock(TaskDTO.class);
        MockedStatic<TaskAssembler> taskAssemblerMockedStatic = mockStatic(TaskAssembler.class);
        when(TaskAssembler.toTaskDTO(taskMock)).thenReturn(taskDTOExpected);


        // -----------ACT------------------------
        Optional<TaskDTO> taskDTOResult = taskService.createAndSaveTask(taskCreationDTO);


        // ------------ASSERT--------------------
        taskIDMockedStatic.close();
        taskAssemblerMockedStatic.close();
        assertEquals(Optional.of(taskDTOExpected),taskDTOResult);


    }


    @Test
    void createAndSaveTaskInSprintWithPrecedenceSuccessfully() throws ParseException {
        String projectCode = "A0004";
        String taskCodeForPrecedence = "T4";
        String taskCode = "T5";

        TaskIdDTO taskIdDTO = new TaskIdDTO();
        taskIdDTO.setTaskCode(taskCodeForPrecedence);
        taskIdDTO.setProjectCode(projectCode);
        List<TaskIdDTO> precedenceList = new ArrayList<>();
        precedenceList.add(taskIdDTO);


        TaskCreationDTO taskCreationDTO = new TaskCreationDTO();
        taskCreationDTO.setNameDto("Dummy task");
        taskCreationDTO.setProjectCodeDto(projectCode);
        taskCreationDTO.setUserStoryCode(null);
        taskCreationDTO.setSprintNumberDto(1);
        taskCreationDTO.setTypeOfTaskDto("DESIGN");
        taskCreationDTO.setDescriptionDto("description");
        taskCreationDTO.setPrecedenceDto(precedenceList);
        taskCreationDTO.setStartDateDto("23/06/2023");
        taskCreationDTO.setEndDateDto("23/07/2023");
        taskCreationDTO.setResponsibleResourceUUIDDto("123e4567-e89b-12d3-a456-426614174000");

        ProjectCode projectCodeObj = ProjectCode.create(projectCode);
        when(taskRepositoryMock.nextTaskCode(projectCodeObj)).thenReturn(taskCode);

        TaskID taskID = mock(TaskID.class);
        MockedStatic<TaskID> taskIDMockedStatic = mockStatic(TaskID.class);
        when(TaskID.createIDTask(any(), any())).thenReturn(taskID);

        // Check Dates in Sprint
        Sprint sprintMock = mock(Sprint.class);
        when(sprintRepositoryMock.findBySprintID(any())).thenReturn(Optional.of(sprintMock));
        when(sprintMock.areDatesWithinSprintDates(any())).thenReturn(true);
        when(sprintMock.areSprintDatesInsideInputTime(any())).thenReturn(true);


        // Check Resource
        Resource resourceMock = mock(Resource.class);
        when(resourceRepositoryMock.getResourceByID(any())).thenReturn(Optional.of(resourceMock));
        when(resourceMock.isResourceInProject(any())).thenReturn(true);
        when(resourceMock.isTeamMember()).thenReturn(true);
        when(resourceMock.getStartDate()).thenReturn("22/06/2023");
        when(resourceMock.getEndDate()).thenReturn("24/07/2023");

        Task taskMock = mock(Task.class);
        when(taskFactoryMock.createTask(any(),any(),any(),any(),any(),anyList(),any(),any()))
                .thenReturn(taskMock);
        when(taskRepositoryMock.save(taskMock)).thenReturn(taskMock);

        // Check Precedence

        when(taskRepositoryMock.findTaskById(any())).thenReturn(Optional.of(taskMock));


        TaskDTO taskDTOExpected = mock(TaskDTO.class);
        MockedStatic<TaskAssembler> taskAssemblerMockedStatic = mockStatic(TaskAssembler.class);
        when(TaskAssembler.toTaskDTO(taskMock)).thenReturn(taskDTOExpected);


        // -----------ACT------------------------
        Optional<TaskDTO> taskDTOResult = taskService.createAndSaveTask(taskCreationDTO);


        // ------------ASSERT--------------------
        taskIDMockedStatic.close();
        taskAssemblerMockedStatic.close();
        assertEquals(Optional.of(taskDTOExpected),taskDTOResult);


    }

    @Test
    @DisplayName("Create Task in UserStory Is unsuccessful because it's created with a sprint and a user story.")
    void createAndSaveTaskInUserStoryWithoutPrecedenceUnsuccessfully() throws ParseException {
        String projectCode = "A0001";
        String userStoryCode = "US1";

        TaskCreationDTO taskCreationDTO = new TaskCreationDTO();
        taskCreationDTO.setNameDto("Dummy task");
        taskCreationDTO.setProjectCodeDto(projectCode);
        taskCreationDTO.setUserStoryCode(userStoryCode);
        taskCreationDTO.setSprintNumberDto(1);
        taskCreationDTO.setTypeOfTaskDto("DESIGN");
        taskCreationDTO.setDescriptionDto("description");
        taskCreationDTO.setPrecedenceDto(null);
        taskCreationDTO.setStartDateDto("23/06/2023");
        taskCreationDTO.setEndDateDto("23/07/2023");
        taskCreationDTO.setResponsibleResourceUUIDDto("123e4567-e89b-12d3-a456-426614174000");



        Task taskMock = mock(Task.class);
        TaskDTO taskDTOExpected = mock(TaskDTO.class);
        MockedStatic<TaskAssembler> taskAssemblerMockedStatic = mockStatic(TaskAssembler.class);
        when(TaskAssembler.toTaskDTO(taskMock)).thenReturn(taskDTOExpected);

        // ------------ACT--------------------

        taskAssemblerMockedStatic.close();


        // ------------ASSERT--------------------
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            taskService.createAndSaveTask(taskCreationDTO);
        });

        String expectedMessage = "SprintID and User Story Code invalid";
        String actualMessage = exception.getMessage();

        //Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void getTaskSuccessfully() throws ParseException {

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTaskCode("T1");
        taskDTO.setTypeOfTaskDto("MEETING");
        taskDTO.setPrecedenceDto(null);
        taskDTO.setDescriptionDto("description");
        taskDTO.setStartDateDto("23/03/2023");
        taskDTO.setEndDateDto("23/04/2023");
        taskDTO.setResponsibleResourceUUIDDto("123e4567-e89b-12d3-a456-426614174000");
        taskDTO.setProjectCodeDto("A0001");
        taskDTO.setNameDto("Dummy Task");
        taskDTO.setSprintNumberDto(1);
        taskDTO.setUserStoryCode(null);

        Task taskMock = mock(Task.class);

        when(taskRepositoryMock.findTaskById(any())).thenReturn(Optional.of(taskMock));

        TaskDTO taskDTOExpected = mock(TaskDTO.class);
        MockedStatic<TaskAssembler> taskAssemblerMockedStatic = mockStatic(TaskAssembler.class);
        when(TaskAssembler.toTaskDTO(taskMock)).thenReturn(taskDTOExpected);

        // -----------ACT------------------------
        Optional<TaskDTO> taskDTOResult = taskService.getTask(taskDTO.getProjectCodeDto(),taskDTO.getTaskCode());


        // ------------ASSERT--------------------
        taskAssemblerMockedStatic.close();
        assertEquals(Optional.of(taskDTOExpected),taskDTOResult);
    }


    @Test
    void getMoreThanOneTaskSuccessfully() throws ParseException {

        List<Task> listOfTasks = new ArrayList<>();
        List<TaskDTO> listOfDTOTasks = new ArrayList<>();

        when(taskRepositoryMock.findAllTasks()).thenReturn(listOfTasks);

        assertEquals(listOfDTOTasks,taskService.getTasks());

    }

}
