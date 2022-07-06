package switchfive.project.interfaceAdapters.implRepositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchfive.project.assemblers.assemblersJPA.iAssemblersJPA.ITaskAssemblerJPA;
import switchfive.project.dataModel.dataJPA.TaskIDJPA;
import switchfive.project.dataModel.dataJPA.TaskJPA;
import switchfive.project.infrastructure.persistence.repositories.iRepositoriesJPA.ITaskRepositoryJPA;
import switchfive.project.domain.aggregates.task.Task;
import switchfive.project.domain.shared.valueObjects.ProjectCode;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ImplTaskRepositoryTest {

    @Mock
    ITaskRepositoryJPA taskRepositoryJPA;

    @Mock
    ITaskAssemblerJPA taskAssemblerJPA;

    @InjectMocks
    ImplTaskRepository taskRepository;

    @Test
    @DisplayName("The return should be the next number of the Task Code, starting with a T.")
    void shouldReturnNextTaskNumber() {

        //-----------ARRANGE-----------

        ProjectCode projectCodeObj = ProjectCode.create("A0001");
        String projectCode = projectCodeObj.getCode();;

        TaskJPA taskJPA = mock(TaskJPA.class);
        taskJPA.setTaskCode("T1");

        TaskJPA otherTaskJPA = mock(TaskJPA.class);
        otherTaskJPA.setTaskCode("T2");

        List<TaskJPA> taskJPAList = new ArrayList<>();
        taskJPAList.add(taskJPA);
        taskJPAList.add(otherTaskJPA);

        when(taskRepositoryJPA.findTaskJPAByProjectCode(projectCode))
                .thenReturn(taskJPAList);

        //----------ACT--------------

        String expected = "T3";
        String actual = taskRepository.nextTaskCode(projectCodeObj);

        //----------ASSERT--------------

        assertEquals(expected,actual);

    }

    @Test
    @DisplayName("Should return an Optional of Task if it's successfully found when looking for a TaskIDJPA. ")
    void findTaskById() throws ParseException {

        //-----------ARRANGE-----------

        TaskIDJPA taskIDJPAMock = mock(TaskIDJPA.class);
        TaskJPA taskJPAMock = mock(TaskJPA.class);
        Task taskMock = mock(Task.class);

        when(taskRepositoryJPA.findById(taskIDJPAMock)).thenReturn(Optional.of(taskJPAMock));

        taskJPAMock = Optional.of(taskJPAMock).get();

        when(taskAssemblerJPA.toDomain(taskJPAMock)).thenReturn(taskMock);

        //----------ACT--------------

        Optional<Task> optTaskExpected = Optional.ofNullable(taskMock);
        Optional<Task> optTaskActual = taskRepository.findTaskById(taskIDJPAMock);

        //----------ASSERT--------------

        assertEquals(optTaskExpected,optTaskActual);
    }

    @Test
    @DisplayName("Should return an Optional of Nullable for not finding the Task by ID. ")
    void findTaskByIdUnsuccessful() throws ParseException {

        //-----------ARRANGE-----------

        TaskIDJPA taskIDJPAMock = mock(TaskIDJPA.class);

        when(taskRepositoryJPA.findById(taskIDJPAMock)).thenReturn(Optional.empty());

        //----------ACT--------------

        Optional<Task> optTaskExpected = Optional.empty();
        Optional<Task> optTaskActual = taskRepository.findTaskById(taskIDJPAMock);

        //----------ASSERT--------------

        assertEquals(optTaskExpected,optTaskActual);
    }

    @Test
    @DisplayName("Should save successfully a task, returning a TaskJPA saved in the repositoryJPA. ")
    void saveTaskSuccessfully() throws ParseException {

        //-----------ARRANGE-----------

        TaskJPA taskJPAMock = mock(TaskJPA.class);
        TaskJPA taskJPAMockInRepo = mock(TaskJPA.class);
        Task taskMock = mock(Task.class);

        when(taskAssemblerJPA.toData(taskMock)).thenReturn(taskJPAMock);

        when(taskRepositoryJPA.save(taskJPAMock)).thenReturn(taskJPAMockInRepo);

        when(taskAssemblerJPA.toDomain(taskJPAMockInRepo)).thenReturn(taskMock);


        //----------ACT--------------

        Task expected = taskMock;
        Task actual = taskRepository.save(taskMock);

        //----------ASSERT--------------

        assertEquals(expected,actual);

    }

    @Test
    @DisplayName("Should return a list of all the tasks saved in the Repository. ")
    void findAllTasksSuccessfully() throws ParseException {

        //-----------ARRANGE-----------
        List<TaskJPA> taskJPAList = new ArrayList<>();
        List<Task> taskList = new ArrayList<>();
        TaskJPA taskJPAMock = mock(TaskJPA.class);
        TaskJPA taskJPAOtherMock = mock(TaskJPA.class);
        TaskJPA taskJPAThirdMock = mock(TaskJPA.class);


        Task taskMock = mock(Task.class);
        Task otherTaskMock = mock(Task.class);
        Task thirdTaskMock = mock(Task.class);

        Iterable<TaskJPA> tasksJPA = taskJPAList;

        taskJPAList.add(taskJPAMock);
        taskJPAList.add(taskJPAOtherMock);
        taskJPAList.add(taskJPAThirdMock);

        when(taskRepositoryJPA.findAll()).thenReturn(tasksJPA);

        when(taskAssemblerJPA.toDomain(taskJPAMock)).thenReturn(taskMock);
        when(taskAssemblerJPA.toDomain(taskJPAOtherMock)).thenReturn(otherTaskMock);
        when(taskAssemblerJPA.toDomain(taskJPAThirdMock)).thenReturn(thirdTaskMock);

        taskList.add(taskMock);
        taskList.add(otherTaskMock);
        taskList.add(thirdTaskMock);


        //----------ACT--------------

        List<Task> expectedList = taskList;
        List<Task> actualList = taskRepository.findAllTasks();

        //----------ASSERT--------------

        assertEquals(expectedList,actualList);

    }


    @Test
    @DisplayName("Should return a list of tasks in a Project ordered by status. ")
    void findAllTaskInProjectOrderedByStatusSuccessfully() throws ParseException {

        //-----------ARRANGE-----------

        ProjectCode projectCodeObj = ProjectCode.create("A0001");
        String projectCode = projectCodeObj.getCode();

        List<Task> taskList = new ArrayList<>();
        List<TaskJPA> taskJPAList = new ArrayList<>();

        TaskJPA taskJPA = mock(TaskJPA.class);
        taskJPA.setTaskCode("T1");
        taskJPA.setTaskStatus("PLANNED");
        taskJPA.setProjectCode(projectCode);
        taskJPA.setTypeOfTask("MEETING");

        TaskJPA otherTaskJPA = mock(TaskJPA.class);
        otherTaskJPA.setTaskCode("T2");
        otherTaskJPA.setTaskStatus("BLOCKED");
        otherTaskJPA.setProjectCode(projectCode);
        otherTaskJPA.setTypeOfTask("MEETING");

        TaskJPA thirdTaskJPA = mock(TaskJPA.class);
        thirdTaskJPA.setTaskCode("T3");
        thirdTaskJPA.setTaskStatus("PLANNED");
        thirdTaskJPA.setProjectCode(projectCode);
        thirdTaskJPA.setTypeOfTask("DESIGN");

        taskJPAList.add(taskJPA);
        taskJPAList.add(otherTaskJPA);
        taskJPAList.add(thirdTaskJPA);

        when(taskRepositoryJPA.findTaskJPAByProjectCodeOrderByTaskStatus(projectCode)).thenReturn(taskJPAList);

        Task taskMock = mock(Task.class);
        Task otherTaskMock = mock(Task.class);
        Task thirdTaskMock = mock(Task.class);

        when(taskAssemblerJPA.toDomain(taskJPA)).thenReturn(taskMock);
        when(taskAssemblerJPA.toDomain(otherTaskJPA)).thenReturn(otherTaskMock);
        when(taskAssemblerJPA.toDomain(thirdTaskJPA)).thenReturn(thirdTaskMock);

        taskList.add(taskMock);
        taskList.add(otherTaskMock);
        taskList.add(thirdTaskMock);

        //----------ACT--------------

        List<Task> expectedList = taskList;
        List<Task> actualList = taskRepository.findTaskAndOrderByStatus(projectCodeObj);


        //----------ASSERT--------------

        assertEquals(expectedList,actualList);


    }


}
