package switchfive.project.interfaceAdapters.dtos;

import org.junit.jupiter.api.Test;
import switchfive.project.dtos.AllocatedProjectDTO;

import static org.junit.jupiter.api.Assertions.*;

class AllocatedProjectDTOTest {

    AllocatedProjectDTO dtoOne = new AllocatedProjectDTO();
    AllocatedProjectDTO dtoTwo = new AllocatedProjectDTO();

    @Test
    void allGettersAndSetters() {

        String projectName = "Teste";
        dtoOne.setProjectName("Teste");
        dtoTwo.setProjectName(projectName);

        String projectCode = "A1234";
        dtoOne.setProjectCode("A1234");
        dtoTwo.setProjectCode(projectCode);

        String role = "PM";
        dtoOne.setRole("PM");
        dtoTwo.setRole(role);

        assertEquals(dtoOne,dtoTwo);
        assertEquals(dtoOne.getProjectName(),projectName);
        assertEquals(dtoOne.getRole(),role);
        assertEquals(dtoOne.getProjectCode(),projectCode);
    }
}
