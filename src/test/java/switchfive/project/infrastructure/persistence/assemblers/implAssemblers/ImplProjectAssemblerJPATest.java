package switchfive.project.infrastructure.persistence.assemblers.implAssemblers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switchfive.project.assemblers.assemblersJPA.iAssemblersJPA.IProjectAssemblerJPA;
import switchfive.project.dataModel.dataJPA.ProjectJPA;
import switchfive.project.domain.aggregates.project.IProjectBuilder;
import switchfive.project.domain.aggregates.project.ImplProjectBuilder;
import switchfive.project.domain.aggregates.project.Project;
import switchfive.project.domain.shared.valueObjects.CustomerName;
import switchfive.project.domain.shared.valueObjects.ProjectBudget;
import switchfive.project.domain.shared.valueObjects.ProjectBusinessSector;
import switchfive.project.domain.shared.valueObjects.ProjectCode;
import switchfive.project.domain.shared.valueObjects.ProjectDescription;
import switchfive.project.domain.shared.valueObjects.ProjectName;
import switchfive.project.domain.shared.valueObjects.ProjectNumberOfPlannedSprints;
import switchfive.project.domain.shared.valueObjects.ProjectSprintDuration;
import switchfive.project.domain.shared.valueObjects.ProjectStatus;
import switchfive.project.domain.shared.valueObjects.Time;
import switchfive.project.domain.shared.valueObjects.TypologyDescription;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class ImplProjectAssemblerJPATest {

    @Autowired
    IProjectBuilder projectBuilder;

    @Autowired
    IProjectAssemblerJPA projectAssemblerJPA;

    @Test
    void getTypologyDescriptionFromProjectJPA() {
        //Arrange
        TypologyDescription expected = TypologyDescription.create("testing");

        ProjectJPA projectJPAMock = mock(ProjectJPA.class);

        when(projectJPAMock.getTypologyDescription()).thenReturn("testing");

        //Act
        TypologyDescription actual = projectAssemblerJPA.getTypologyDescriptionFromProjectJPA(projectJPAMock);

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void getCustomerNameFromProjectJPA() {
        //Arrange
        CustomerName expected = CustomerName.create("Ferrari");

        ProjectJPA projectJPAMock = mock(ProjectJPA.class);

        when(projectJPAMock.getCustomerName()).thenReturn("Ferrari");

        //Act
        CustomerName actual = projectAssemblerJPA.getCustomerNameFromProjectJPA(projectJPAMock);

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void getProjectCodeFromProjectJPA() {
        //Arrange
        ProjectCode expected = ProjectCode.create("ISEP1");

        ProjectJPA projectJPAMock = mock(ProjectJPA.class);

        when(projectJPAMock.getCode()).thenReturn("ISEP1");

        //Act
        ProjectCode actual = projectAssemblerJPA.getProjectCodeFromProjectJPA(projectJPAMock);

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void getProjectNameFromProjectJPA() {
        //Arrange
        ProjectName expected = ProjectName.create("SWITCH");

        ProjectJPA projectJPAMock = mock(ProjectJPA.class);

        when(projectJPAMock.getName()).thenReturn("SWITCH");

        //Act
        ProjectName actual = projectAssemblerJPA.getProjectNameFromProjectJPA(projectJPAMock);

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void getDescriptionFromProjectJPA() {
        //Arrange
        ProjectDescription expected = ProjectDescription.create("Testing");

        ProjectJPA projectJPAMock = mock(ProjectJPA.class);

        when(projectJPAMock.getDescription()).thenReturn("Testing");

        //Act
        ProjectDescription actual = projectAssemblerJPA.getDescriptionFromProjectJPA(projectJPAMock);

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void getBusinessSectorFromProjectJPA() {
        //Arrange
        ProjectBusinessSector expected = ProjectBusinessSector.create("Education");

        ProjectJPA projectJPAMock = mock(ProjectJPA.class);

        when(projectJPAMock.getBusinessSector()).thenReturn("Education");

        //Act
        ProjectBusinessSector actual = projectAssemblerJPA.getBusinessSectorFromProjectJPA(projectJPAMock);

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void getDatesFromProjectJPA() throws ParseException {
        //Arrange
        Time expected = Time.create("17/05/2023", "17/08/2024");

        ProjectJPA projectJPAMock = mock(ProjectJPA.class);

        when(projectJPAMock.getStartDate()).thenReturn("17/05/2023");

        when(projectJPAMock.getEndDate()).thenReturn("17/08/2024");

        //Act
        Time actual = projectAssemblerJPA.getDatesFromProjectJPA(projectJPAMock);

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void getNumberOfSprintsFromProjectJPA() {
        //Arrange
        ProjectNumberOfPlannedSprints expected = ProjectNumberOfPlannedSprints.create(1);

        ProjectJPA projectJPAMock = mock(ProjectJPA.class);

        when(projectJPAMock.getNumberOfPlannedSprints()).thenReturn(1);

        //Act
        ProjectNumberOfPlannedSprints actual = projectAssemblerJPA.getNumberOfSprintsFromProjectJPA(projectJPAMock);

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void getBudgetFromProjectJPA() {
        //Arrange
        ProjectBudget expected = ProjectBudget.create(1.0);

        ProjectJPA projectJPAMock = mock(ProjectJPA.class);

        when(projectJPAMock.getBudget()).thenReturn(1.0);

        //Act
        ProjectBudget actual = projectAssemblerJPA.getBudgetFromProjectJPA(projectJPAMock);

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void getSprintDurationFromProjectJPA() {
        //Arrange
        ProjectSprintDuration expected = ProjectSprintDuration.create(1);

        ProjectJPA projectJPAMock = mock(ProjectJPA.class);

        when(projectJPAMock.getSprintDuration()).thenReturn(1);

        //Act
        ProjectSprintDuration actual = projectAssemblerJPA.getSprintDurationFromProjectJPA(projectJPAMock);

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void getProjectFromProjectJPA() throws ParseException {
        //Arrange
        ProjectJPA projectJPA = new ProjectJPA();
        projectJPA.setCode("Monza");
        projectJPA.setName("Ferrari");
        projectJPA.setDescription("Sports");
        projectJPA.setCustomerName("Maranello");
        projectJPA.setTypologyDescription("Fast and furious");
        projectJPA.setStartDate("18/05/2023");
        projectJPA.setEndDate("18/05/2024");
        projectJPA.setSprintDuration(1);
        projectJPA.setNumberOfPlannedSprints(1);
        projectJPA.setBudget(0.0);
        projectJPA.setBusinessSector("Automotive");
        projectJPA.setStatus("Planned");

        ProjectCode code = ProjectCode.create("Monza");
        ProjectName name = ProjectName.create("Ferrari");
        ProjectDescription description = ProjectDescription.create("Sports");
        CustomerName customerName = CustomerName.create("Maranello");
        TypologyDescription typologyDescription = TypologyDescription.create("Fast and furious");
        Time dates = Time.create("18/05/2023", "18/05/2024");
        ProjectSprintDuration sprintDuration = ProjectSprintDuration.create(1);
        ProjectNumberOfPlannedSprints numberOfPlannedSprints = ProjectNumberOfPlannedSprints.create(1);
        ProjectBudget budget = ProjectBudget.create(0.0);
        ProjectBusinessSector businessSector = ProjectBusinessSector.create("Automotive");
        ProjectStatus status = ProjectStatus.valueOf("Planned");

        Project expected = new ImplProjectBuilder().setCode(code)
                .setName(name)
                .setDescription(description)
                .setName(name)
                .setCustomer(customerName)
                .setTypology(typologyDescription)
                .setTime(dates)
                .setSprintDuration(sprintDuration)
                .setSprints(numberOfPlannedSprints)
                .setBudget(budget)
                .setBusinessSector(businessSector)
                .setStatus(status)
                .build();

        //Act
        Project actual = projectAssemblerJPA.toDomain(projectJPA);

        //Assert
        assertEquals(expected, actual);

    }

}
