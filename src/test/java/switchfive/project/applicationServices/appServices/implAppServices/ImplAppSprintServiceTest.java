package switchfive.project.applicationServices.appServices.implAppServices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchfive.project.dtos.SprintCreationDTO;
import switchfive.project.dtos.SprintDTO;
import switchfive.project.applicationServices.assemblers.iAssemblers.ISprintAssembler;
import switchfive.project.applicationServices.iRepositories.IProjectRepository;
import switchfive.project.applicationServices.iRepositories.ISprintRepository;
import switchfive.project.domain.aggregates.sprint.Sprint;
import switchfive.project.domain.domainServices.iDomainServices.ISprintDomainService;
import switchfive.project.domain.factories.iFactories.ISprintFactory;
import switchfive.project.domain.shared.valueObjects.ProjectCode;
import switchfive.project.domain.shared.valueObjects.SprintID;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImplAppSprintServiceTest {

    @Mock
    ProjectCode projectCode;
    @Mock
     ISprintRepository sprintRepository;
    @Mock
     IProjectRepository projectRepository;
    @Mock
     ISprintFactory sprintFactory;
    @Mock
     ISprintDomainService sprintDomainService;
    @Mock
     ISprintAssembler sprintAssembler;
    @InjectMocks
    ImplAppSprintService sprintService;

    @Test
    void getSprintBySprintId_Failling_ProjectDoesNotExist()  {
        // Arrange
        SprintID sprintID = SprintID.createSprintID("AB123",1);
        ProjectCode code = ProjectCode.create("AB123");

        when(projectRepository.projectExists(code)).thenReturn(false);

        // Assert
        assertThrows(IllegalArgumentException.class, () -> this.sprintService.getSprintBySprintId(sprintID));

    }

    @Test
    void getSprintBySprintId_Failling_SprintDoesNotExist() throws ParseException {
        // Arrange
        SprintID sprintID = SprintID.createSprintID("AB123",1);
        ProjectCode code = ProjectCode.create("AB123");

        when(projectRepository.projectExists(code)).thenReturn(true);
        when(sprintRepository.findBySprintID(sprintID)).thenReturn(Optional.empty());

        // Assert
        assertEquals(Optional.empty(),sprintService.getSprintBySprintId(sprintID));
    }

    @Test
    void getSprintBySprintId_Sucessfully() throws ParseException {
        // Arrange
        SprintID sprintID = SprintID.createSprintID("AB123",1);
        ProjectCode code = ProjectCode.create("AB123");
        Sprint sprint = mock(Sprint.class);
        SprintDTO sprintDTO = mock(SprintDTO.class);

        when(projectRepository.projectExists(code)).thenReturn(true);
        when(sprintRepository.findBySprintID(sprintID)).thenReturn(Optional.of(sprint));
        when(sprintAssembler.toDTO(sprint)).thenReturn(sprintDTO);

        // Assert
        assertEquals(Optional.of(sprintDTO),sprintService.getSprintBySprintId(sprintID));
    }

    @Test
    void getSprintsByProjectCode_Failling_ProjectDoesNotExist() {
        // Arrange
        ProjectCode code = ProjectCode.create("AB123");

        when(projectRepository.projectExists(code)).thenReturn(false);

        // Assert
        assertThrows(IllegalArgumentException.class, () -> this.sprintService.getSprintsByProjectCode("AB123"));
    }

    @Test
    void getSprintsByProjectCode_NoSprints() throws ParseException {
        // Arrange
        ProjectCode code = ProjectCode.create("AB123");

        when(projectRepository.projectExists(code)).thenReturn(true);
        when(sprintRepository.findAllSprintsByProjectCode(code)).thenReturn(Optional.empty());

        // Assert
        assertEquals(Optional.empty(),sprintService.getSprintsByProjectCode("AB123"));
    }

    @Test
    void getSprintsByProjectCode_Succesfully() throws ParseException {
        // Arrange
        ProjectCode code = ProjectCode.create("AB123");
        List<Sprint> sprints = new ArrayList<>();

        when(projectRepository.projectExists(code)).thenReturn(true);
        when(sprintRepository.findAllSprintsByProjectCode(code)).thenReturn(Optional.of(sprints));

        // Assert
        assertEquals(Optional.of(sprints),sprintService.getSprintsByProjectCode("AB123"));
    }

    @Test
    void createAndSaveSprint_Failling_ProjectDoesNoExist() throws ParseException {
        // Arrange
        ProjectCode code = ProjectCode.create("AB123");
        SprintCreationDTO dto = new SprintCreationDTO();
        dto.setStartDate("26/04/2023");
        dto.setEndDate("26/05/2023");
        dto.setDescription("For testing purposes.");

        when(projectRepository.projectExists(code)).thenReturn(false);

        // Assert
        assertThrows(IllegalArgumentException.class, () -> this.sprintService.createAndSaveSprint(dto,"AB123"));

    }

}
