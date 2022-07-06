package switchfive.project.applicationServices.assemblers.implAssemblers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import switchfive.project.dtos.ProjectDTO;
import switchfive.project.mappers.mappersApp.implMappers.ImplProjectMapper;
import switchfive.project.domain.aggregates.project.Project;
import switchfive.project.domain.shared.valueObjects.*;

import java.text.ParseException;

class ImplProjectAssemblerTest {

    @Test
    void toDTO() throws ParseException {
        //Arrange
        ImplProjectMapper mapper = new ImplProjectMapper();

        Project test = new Project(ProjectCode.create("DANI1"),
                ProjectName.create("MyFirstProject"),
                ProjectDescription.create("ToTestProjectCreation"),
                CustomerName.create("TestCustomer"));
        test.addBudget(ProjectBudget.create(1000000));
        test.addNumberOfPlannedSprints(
                ProjectNumberOfPlannedSprints.create(5));
        test.addTypologyDescription(
                TypologyDescription.create("MyDescription"));
        test.addDates(Time.create("09/05/2023", "09/05/2024"));
        test.addBusinessSector(ProjectBusinessSector.create(
                "EducationalPurposes"));
        test.addSprintDuration(ProjectSprintDuration.create(5));
        test.addStatus(ProjectStatus.Planned);

        ProjectDTO dto = new ProjectDTO();

        dto.setProjectCode("DANI1");
        dto.setProjectName("MyFirstProject");
        dto.setProjectDescription("ToTestProjectCreation");
        dto.setProjectBusinessSector("EducationalPurposes");
        dto.setProjectNumberOfPlannedSprints(5);
        dto.setProjectSprintDuration(5);
        dto.setProjectBudget(1000000);
        dto.setStartDate("09/05/2023");
        dto.setEndDate("09/05/2024");
        dto.setStatus("Planned");

        //Act
        ProjectDTO actual = mapper.toDTO(test);

        //Assert
        Assertions.assertEquals(dto, actual);
    }

    /*@Test
    void toDTOWithPM() throws ParseException {
        //Arrange
        ImplProjectMapper mapper = new ImplProjectMapper();

        Project test = new Project(ProjectCode.create("DANI1"),
                ProjectName.create("MyFirstProject"),
                ProjectDescription.create("ToTestProjectCreation"),
                CustomerName.create("TestCustomer"));
        test.addBudget(ProjectBudget.create(1000000));
        test.addNumberOfPlannedSprints(
                ProjectNumberOfPlannedSprints.create(5));
        test.addTypologyDescription(
                TypologyDescription.create("MyDescription"));
        test.addDates(Time.create("09/05/2023", "09/05/2024"));
        test.addBusinessSector(ProjectBusinessSector.create(
                "EducationalPurposes"));
        test.addSprintDuration(ProjectSprintDuration.create(5));
        test.addStatus(ProjectStatus.Planned);


        String projectManagerEmail = "daniel@isep.ipp.pt";
        double projectManagerCostPerHour = 10;
        double projectManagerAllocation = 10;

        ProjectDTO dto = new ProjectDTO();

        dto.setProjectCode("DANI1");
        dto.setProjectName("MyFirstProject");
        dto.setProjectDescription("ToTestProjectCreation");
        dto.setProjectBusinessSector("EducationalPurposes");
        dto.setProjectNumberOfPlannedSprints(5);
        dto.setProjectSprintDuration(5);
        dto.setProjectBudget(1000000);
        dto.setStartDate("09/05/2023");
        dto.setEndDate("09/05/2024");
        dto.setStatus("Planned");

        //Act
        ProjectDTO actual = mapper.toDTO(test, projectManagerEmail,
                projectManagerCostPerHour, projectManagerAllocation);

        //Assert
        Assertions.assertEquals(dto, actual);
    }*/
}
