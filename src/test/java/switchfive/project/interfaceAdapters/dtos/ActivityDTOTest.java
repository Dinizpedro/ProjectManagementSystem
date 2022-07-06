package switchfive.project.interfaceAdapters.dtos;

import org.junit.jupiter.api.Test;
import switchfive.project.dtos.ActivityDTO;

import static org.junit.jupiter.api.Assertions.*;

class ActivityDTOTest {

    @Test
    void ActivityDTO() {

        // Arrange
        ActivityDTO dto = new ActivityDTO();
        dto.setActivityCode("123");
        dto.setActivityStatus("Teste");
        dto.setTypeOfActivity("123");

        // Act

        // Assert
        assertEquals("123",dto.getActivityCode());
        assertEquals("123",dto.getTypeOfActivity());
        assertEquals("Teste",dto.getActivityStatus());
    }

}