package switchfive.project.applicationServices.assemblers.implAssemblers;

import org.junit.jupiter.api.Test;
import switchfive.project.dataModel.dataJPA.TaskIDJPA;
import switchfive.project.dtos.TaskDTO;
import switchfive.project.domain.aggregates.task.Task;
import switchfive.project.domain.shared.valueObjects.SprintID;
import switchfive.project.domain.shared.valueObjects.TaskCode;
import switchfive.project.domain.shared.valueObjects.TaskID;
import switchfive.project.domain.shared.valueObjects.UserStoryID;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TaskAssemblerTest {

    @Test
    void toTaskDTOinUserStory() {

        // Arrange
        Task taskMock = mock(Task.class);
        UserStoryID userStoryID = UserStoryID.createUserStoryID("P0101", "US1");
        TaskCode taskCode = TaskCode.createTaskCode("T1");
        TaskID taskID = TaskID.createIDTask(taskCode, userStoryID);

        when(taskMock.getIdTask()).thenReturn(taskID);
        when(taskMock.getName()).thenReturn("First task");
        when(taskMock.getDescription()).thenReturn("First task to complete");
        when(taskMock.getStartDate()).thenReturn("01/01/2022");
        when(taskMock.getEndDate()).thenReturn("02/01/2022");
        when(taskMock.getHoursSpent()).thenReturn(2.0);
        when(taskMock.getEffortEstimate()).thenReturn(1);
        when(taskMock.getPercentageOfExecution()).thenReturn(2.0);

        List<TaskIDJPA> precedenceList = new ArrayList<>();
        TaskIDJPA taskIDJPA = new TaskIDJPA();
        taskIDJPA.setTaskCode("T2");
        taskIDJPA.setProjectCode("P1111");
        precedenceList.add(taskIDJPA);

        when(taskMock.getPrecedenceList()).thenReturn(precedenceList);
        when(taskMock.getTypeOfTask()).thenReturn("MEETING");
        when(taskMock.getResponsible()).thenReturn("responsible");
        when(taskMock.getTaskStatus()).thenReturn("Planned");

        // Act
        TaskDTO actual = TaskAssembler.toTaskDTO(taskMock);

        // Assertions (Multiple assertions in order to avert the creation of equals method in TaskDTO)
        assertEquals("T1", actual.getTaskCode());
        assertEquals("First task", actual.getNameDto());
        assertEquals("First task to complete", actual.getDescriptionDto());
        assertEquals("01/01/2022", actual.getStartDateDto());
        assertEquals("02/01/2022", actual.getEndDateDto());
        assertEquals(2.0, actual.getHourSpent());
        assertEquals(1, actual.getEffortDto());
        assertEquals(2.0, actual.getPercentageOfExecution());
        assertEquals("MEETING", actual.getTypeOfTaskDto());
        assertEquals("responsible", actual.getResponsibleResourceUUIDDto());
        assertEquals("Planned", actual.getStatusOfTask());
        assertEquals("P0101", actual.getProjectCodeDto());
        assertEquals("US1", actual.getUserStoryCode());
        assertNull(actual.getSprintNumberDto());
    }

    @Test
    void toTaskDTOinSprint() {

        // Arrange
        Task taskMock = mock(Task.class);
        SprintID sprintID = SprintID.createSprintID("P0101", 1);
        TaskCode taskCode = TaskCode.createTaskCode("T1");
        TaskID taskID = TaskID.createIDTask(taskCode, sprintID);

        when(taskMock.getIdTask()).thenReturn(taskID);
        when(taskMock.getName()).thenReturn("First task");
        when(taskMock.getDescription()).thenReturn("First task to complete");
        when(taskMock.getStartDate()).thenReturn("01/01/2022");
        when(taskMock.getEndDate()).thenReturn("02/01/2022");
        when(taskMock.getHoursSpent()).thenReturn(2.0);
        when(taskMock.getEffortEstimate()).thenReturn(1);
        when(taskMock.getPercentageOfExecution()).thenReturn(2.0);

        List<TaskIDJPA> precedenceList = new ArrayList<>();
        when(taskMock.getPrecedenceList()).thenReturn(precedenceList);
        when(taskMock.getTypeOfTask()).thenReturn("MEETING");
        when(taskMock.getResponsible()).thenReturn("responsible");
        when(taskMock.getTaskStatus()).thenReturn("Planned");

        // Act
        TaskDTO actual = TaskAssembler.toTaskDTO(taskMock);

        // Assertions (Multiple assertions in order to avert the creation of equals method in TaskDTO)
        assertEquals("T1", actual.getTaskCode());
        assertEquals("First task", actual.getNameDto());
        assertEquals("First task to complete", actual.getDescriptionDto());
        assertEquals("01/01/2022", actual.getStartDateDto());
        assertEquals("02/01/2022", actual.getEndDateDto());
        assertEquals(2.0, actual.getHourSpent());
        assertEquals(1, actual.getEffortDto());
        assertEquals(2.0, actual.getPercentageOfExecution());
        assertEquals("MEETING", actual.getTypeOfTaskDto());
        assertEquals("responsible", actual.getResponsibleResourceUUIDDto());
        assertEquals("Planned", actual.getStatusOfTask());
        assertEquals("P0101", actual.getProjectCodeDto());
        assertEquals(1, actual.getSprintNumberDto());
        assertNull(actual.getUserStoryCode());
        assertEquals(0, actual.getPrecedenceDto().size());

    }
}
