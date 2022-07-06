package switchfive.project.interfaceAdapters.dtos;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import switchfive.project.dtos.TaskCreationDTO;

import static org.mockito.Mockito.mock;

class TaskCreationDTOTest {

    @Disabled // Reason: org.mockito.exceptions.misusing.UnfinishedVerificationException
    @Test
    void TaskCreationDTO() {

        // Arrange
        TaskCreationDTO dto = new TaskCreationDTO();
        dto = Mockito.spy(dto);

        dto.setEffortDto(1);

        // Act

        // Assert
        Mockito.verify(dto);

    }

}