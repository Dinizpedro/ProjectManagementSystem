package switchfive.project.infrastructure.persistence.dataJPA;

import org.junit.jupiter.api.Test;
import switchfive.project.dataModel.dataJPA.TaskIDJPA;

import static org.junit.jupiter.api.Assertions.*;

class TaskIDJPATest {

    @Test
    void getTaskCode() {

        // Arrange
        TaskIDJPA taskIDJPA =
                TaskIDJPA.createTaskIDJPA("T1","P1010");
        taskIDJPA.setTaskCode("T2");

        // Act
        String actual = taskIDJPA.getTaskCode();

        // Assert
        assertEquals("T2", actual);

    }

    @Test
    void getProjectCode() {
        // Arrange
        TaskIDJPA taskIDJPA =
                TaskIDJPA.createTaskIDJPA("T1","P1010");
        taskIDJPA.setProjectCode("A0001");

        // Act
        String actual = taskIDJPA.getProjectCode();

        // Assert
        assertEquals("A0001", actual);
    }
}
