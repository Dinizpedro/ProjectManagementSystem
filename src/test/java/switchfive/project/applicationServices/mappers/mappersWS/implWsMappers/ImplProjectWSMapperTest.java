package switchfive.project.applicationServices.mappers.mappersWS.implWsMappers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import switchfive.project.interfaceAdapters.domainWS.ProjectWS;
import switchfive.project.dtos.ProjectDTO;
import switchfive.project.mappers.mappersWS.implWsMappers.ImplProjectWSMapper;

@SpringBootTest
class ImplProjectWSMapperTest {

    @InjectMocks
    ImplProjectWSMapper projectWSMapper;

    @Test void toDTO() {
        //Arrange
        ProjectWS projectWS = ProjectWS.create("Planned", "time and materials",
                "Adidas", "XPTO1", "PortoTechHub", "Educational",
                "Requalification", "2023-06-29", "2024-06-29",
                2, 1, 1);

        ProjectDTO expectedDTO = new ProjectDTO();
        expectedDTO.setProjectCode("XPTO1");
        expectedDTO.setProjectName("PortoTechHub");
        expectedDTO.setProjectDescription("Educational");
        expectedDTO.setProjectBusinessSector("Requalification");
        expectedDTO.setProjectNumberOfPlannedSprints(2);
        expectedDTO.setProjectSprintDuration(1);
        expectedDTO.setProjectBudget(1);
        expectedDTO.setStartDate("2023-06-29");
        expectedDTO.setEndDate("2024-06-29");
        expectedDTO.setTypologyDescription("time and materials");
        expectedDTO.setCustomerName("Adidas");
        expectedDTO.setStatus("Planned");

        //Act
        ProjectDTO actual = projectWSMapper.toDTO(projectWS);

        //Assert
        Assertions.assertEquals(expectedDTO, actual);
    }
}
