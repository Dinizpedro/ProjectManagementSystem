package switchfive.project.interfaceAdapters.dtos;

import org.junit.jupiter.api.Test;
import switchfive.project.dtos.RegisterWorkDoneOnTaskDTO;

import static org.junit.jupiter.api.Assertions.*;

class RegisterWorkDoneOnTaskDTOTest {

    @Test
    void anInstanceShouldCorrectlyAssignProjectCode() {
        RegisterWorkDoneOnTaskDTO dto = new RegisterWorkDoneOnTaskDTO();

        dto.projectCode = "validProjectCode";

        assertEquals("validProjectCode", dto.projectCode);
    }

    @Test
    void anInstanceShouldCorrectlyAssignTaskCode() {
        RegisterWorkDoneOnTaskDTO dto = new RegisterWorkDoneOnTaskDTO();

        dto.taskCode = 2;

        assertEquals(2, dto.taskCode);
    }

    @Test
    void anInstanceShouldCorrectlyAssignSprintNumber() {
        RegisterWorkDoneOnTaskDTO dto = new RegisterWorkDoneOnTaskDTO();

        dto.sprintNumber = 2;

        assertEquals(2, dto.sprintNumber);
    }

    @Test
    void anInstanceShouldCorrectlyAssignWorkDescription() {
        RegisterWorkDoneOnTaskDTO dto = new RegisterWorkDoneOnTaskDTO();

        dto.workDescription = "description";

        assertEquals("description", dto.workDescription);
    }

    @Test
    void anInstanceShouldCorrectlyAssignTimeSpent() {
        RegisterWorkDoneOnTaskDTO dto = new RegisterWorkDoneOnTaskDTO();

        dto.timeSpent = 2;

        assertEquals(2, dto.timeSpent);
    }

}