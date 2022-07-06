package switchfive.project.infrastructure.persistence.dataJPA;

import org.junit.jupiter.api.Test;
import switchfive.project.dataModel.dataJPA.TaskIDJPA;
import switchfive.project.dataModel.dataJPA.TaskJPA;
import switchfive.project.domain.shared.valueObjects.Log;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskJPATest {

    @Test
    void getTaskCode() {

        // Arrange
        String expected = "T1";
        TaskJPA taskJPA = new TaskJPA();
        taskJPA.setTaskCode(expected);

        // Act
        String actual = taskJPA.getTaskCode();

        // Assert
        assertEquals(expected, actual);

    }

    @Test
    void getName() {

        // Arrange
        String expected = "Valid name";
        TaskJPA taskJPA = new TaskJPA();
        taskJPA.setName(expected);

        // Act
        String actual = taskJPA.getName();

        // Assert
        assertEquals(expected, actual);

    }

    @Test
    void getDescription() {

        // Arrange
        String expected = "Valid description";
        TaskJPA taskJPA = new TaskJPA();
        taskJPA.setDescription(expected);

        // Act
        String actual = taskJPA.getDescription();

        // Assert
        assertEquals(expected, actual);

    }

    @Test
    void getStartDate() {
        // Arrange
        String expected = "01/01/2022";
        TaskJPA taskJPA = new TaskJPA();
        taskJPA.setStartDate(expected);

        // Act
        String actual = taskJPA.getStartDate();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void getEndDate() {
        // Arrange
        String expected = "01/01/2022";
        TaskJPA taskJPA = new TaskJPA();
        taskJPA.setEndDate(expected);

        // Act
        String actual = taskJPA.getEndDate();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void getHoursSpent() {
        // Arrange
        double expected = 2.0;
        TaskJPA taskJPA = new TaskJPA();
        taskJPA.setHoursSpent(expected);

        // Act
        double actual = taskJPA.getHoursSpent();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void getEffortEstimate() {
        // Arrange
        int expected = 2;
        TaskJPA taskJPA = new TaskJPA();
        taskJPA.setEffortEstimate(expected);

        // Act
        int actual = taskJPA.getEffortEstimate();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void getPercentageOfExecution() {
        // Arrange
        double expected = 2.0;
        TaskJPA taskJPA = new TaskJPA();
        taskJPA.setPercentageOfExecution(expected);

        // Act
        double actual = taskJPA.getPercentageOfExecution();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void getPrecedenceList() {
        // Arrange
        List<TaskIDJPA> expected = new ArrayList<>();
        TaskJPA taskJPA = new TaskJPA();
        taskJPA.setPrecedenceList(expected);

        // Act
        List<TaskIDJPA> actual = taskJPA.getPrecedenceList();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void getTypeOfTask() {

        // Arrange
        String expected = "MEETING";
        TaskJPA taskJPA = new TaskJPA();
        taskJPA.setTypeOfTask(expected);

        // Act
        String actual = taskJPA.getTypeOfTask();

        // Assert
        assertEquals(expected, actual);

    }

    @Test
    void getResourceResponsible() {
        // Arrange
        String expected = "responsible";
        TaskJPA taskJPA = new TaskJPA();
        taskJPA.setResourceResponsible(expected);

        // Act
        String actual = taskJPA.getResourceResponsible();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void getTaskLogs() {
        // Arrange
        List<Log> expected = new ArrayList<>();
        TaskJPA taskJPA = new TaskJPA();
        taskJPA.setTaskLogs(expected);

        // Act
        List<Log> actual = taskJPA.getTaskLogs();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void getTaskStatus() {
        // Arrange
        String expected = "Planned";
        TaskJPA taskJPA = new TaskJPA();
        taskJPA.setTaskStatus(expected);

        // Act
        String actual = taskJPA.getTaskStatus();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void getProjectCode() {
        // Arrange
        String expected = "P1010";
        TaskJPA taskJPA = new TaskJPA();
        taskJPA.setProjectCode(expected);

        // Act
        String actual = taskJPA.getProjectCode();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void getSprintNumber() {
        // Arrange
        int expected = 1;
        TaskJPA taskJPA = new TaskJPA();
        taskJPA.setSprintNumber(expected);

        // Act
        int actual = taskJPA.getSprintNumber();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void getUserStoryCode() {
        // Arrange
        String expected = "US1";
        TaskJPA taskJPA = new TaskJPA();
        taskJPA.setUserStoryCode(expected);

        // Act
        String actual = taskJPA.getUserStoryCode();

        // Assert
        assertEquals(expected, actual);
    }
}
