package switchfive.project.domain.aggregates.project;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import switchfive.project.dtos.TimeDTO;
import switchfive.project.domain.shared.valueObjects.CustomerName;
import switchfive.project.domain.shared.valueObjects.ProjectBudget;
import switchfive.project.domain.shared.valueObjects.ProjectBusinessSector;
import switchfive.project.domain.shared.valueObjects.ProjectCode;
import switchfive.project.domain.shared.valueObjects.ProjectDescription;
import switchfive.project.domain.shared.valueObjects.ProjectName;
import switchfive.project.domain.shared.valueObjects.ProjectNumberOfPlannedSprints;
import switchfive.project.domain.shared.valueObjects.ProjectSprintDuration;
import switchfive.project.domain.shared.valueObjects.Time;
import switchfive.project.domain.shared.valueObjects.TypologyDescription;

import java.text.ParseException;
import java.util.Objects;

class ProjectTest {

    TypologyDescription typologyDescription = TypologyDescription.create("Time and materials");
    CustomerName customerName = CustomerName.create("torres");
    ProjectCode code = ProjectCode.create("ISEP1");
    ProjectName name = ProjectName.create("ISEPEngineering");
    ProjectDescription description = ProjectDescription.create("Engineering school");
    ProjectBusinessSector businessSector = ProjectBusinessSector.create("Educational");
    Time dates = Time.create("26/04/2023", "26/04/2024");
    ProjectNumberOfPlannedSprints numberOfPlannedSprints = ProjectNumberOfPlannedSprints.create(1);
    ProjectBudget budget = ProjectBudget.create(500);
    ProjectSprintDuration sprintDuration = ProjectSprintDuration.create(1);

    Project newProject;

    ProjectTest() throws ParseException {
    }

    @BeforeEach
    void setUp() {
        newProject = new Project(code, name, description, customerName);
        newProject.addTypologyDescription(typologyDescription);
        newProject.addBusinessSector(businessSector);
        newProject.addDates(dates);
        newProject.addNumberOfPlannedSprints(numberOfPlannedSprints);
        newProject.addBudget(budget);
        newProject.addSprintDuration(sprintDuration);
    }

    @Test
    void thisProjectIsEqualsToOther() {
        //Arrange
        Project clone = new Project(code, name, description, customerName);
        clone.addTypologyDescription(typologyDescription);
        clone.addBusinessSector(businessSector);
        clone.addDates(dates);
        clone.addNumberOfPlannedSprints(numberOfPlannedSprints);
        clone.addBudget(budget);
        clone.addSprintDuration(sprintDuration);

        //Act
        boolean actual = newProject.equals(clone);

        //Assert
        Assertions.assertTrue(actual);
    }

    @Test
    void otherProjectIsNotAnInstanceOfProject() {
        //Arrange
        Object clone = new Object();

        //Act
        boolean actual = newProject.equals(clone);

        //Assert
        Assertions.assertFalse(actual);
    }

    @Test
    void thisProjectIsTheSameAsOther() {
        //Arrange
        Project clone = newProject;

        //Act
        boolean actual = newProject.equals(clone);

        //Assert
        Assertions.assertTrue(actual);
    }

    @Test
    void thisProjectIsNotEqualsToOther() {
        //Arrange
        ProjectCode projectCodeOther = ProjectCode.create("1PESI");

        Project clone = new Project(projectCodeOther, name, description, customerName);
        clone.addTypologyDescription(typologyDescription);
        clone.addBusinessSector(businessSector);
        clone.addDates(dates);
        clone.addNumberOfPlannedSprints(numberOfPlannedSprints);
        clone.addBudget(budget);
        clone.addSprintDuration(sprintDuration);

        //Act
        boolean actual = newProject.equals(clone);

        //Assert
        Assertions.assertFalse(actual);
    }

    @Test
    void thisProjectHaveTheSameHashAsOther() {
        //Arrange
        Project clone = new Project(code, name, description, customerName);
        clone.addTypologyDescription(typologyDescription);
        clone.addBusinessSector(businessSector);
        clone.addDates(dates);
        clone.addNumberOfPlannedSprints(numberOfPlannedSprints);
        clone.addBudget(budget);
        clone.addSprintDuration(sprintDuration);

        //Act
        boolean actual = newProject.hashCode() == (clone.hashCode());

        //Assert
        Assertions.assertTrue(actual);
    }

    @Test
    void thisProjectDoesNotHaveTheSameHashAsOther() {
        //Arrange
        ProjectCode projectCodeOther =
                ProjectCode.create("1PESI");

        Project clone = new Project(projectCodeOther, name, description, customerName);
        clone.addTypologyDescription(typologyDescription);
        clone.addBusinessSector(businessSector);
        clone.addDates(dates);
        clone.addNumberOfPlannedSprints(numberOfPlannedSprints);
        clone.addBudget(budget);
        clone.addSprintDuration(sprintDuration);

        //Act
        boolean actual = newProject.hashCode() == (clone.hashCode());

        //Assert
        Assertions.assertFalse(actual);
    }

    @Test
    void areDatesWithinProjectDates() throws ParseException {
        //Arrange
        String startDate_x = "27/04/2023";
        String endDate_x = "27/05/2023";

        //Act
        boolean actual = this.newProject.areDatesWithinProjectDates(startDate_x, endDate_x);

        //Assert
        Assertions.assertTrue(actual);

    }

    @Test
    void areNotDatesWithinProjectDates() throws ParseException {
        //Arrange
        String startDate_x = "01/01/2029";
        String endDate_x = "02/01/2030";

        //Act
        boolean actual = this.newProject.areDatesWithinProjectDates(startDate_x, endDate_x);

        //Assert
        Assertions.assertFalse(actual);

    }

    @Test
    void getTypologyDescription() {
        //Arrange
        String description = "Time and materials";

        //Act
        boolean actual = description.equals(newProject.getTypologyDescription());

        //Assert
        Assertions.assertTrue(actual);
    }

    @Test
    void getCode() {
        //Arrange
        String code = "ISEP1";

        //Act
        boolean actual = Objects.equals(newProject.getCode(), code);

        //Assert
        Assertions.assertTrue(actual);
    }

    @Test
    void getName() {
        //Arrange
        String name = "ISEPEngineering";

        //Act
        boolean actual = Objects.equals(newProject.getName(), name);

        //Assert
        Assertions.assertTrue(actual);
    }

    @Test
    void getDescription() {
        //Arrange
        String description = "Engineering school";

        //Act
        boolean actual =
                Objects.equals(newProject.getDescription(), description);

        //Assert
        Assertions.assertTrue(actual);
    }

    @Test
    void getBusinessSector() {
        //Arrange
        String businessSector = "Educational";

        //Act
        boolean actual =
                Objects.equals(newProject.getBusinessSector(), businessSector);

        //Assert
        Assertions.assertTrue(actual);
    }

    @Test
    void getDates() {
        //Arrange
        TimeDTO time = new TimeDTO("26/04/2023", "26/04/2024");
        TimeDTO projectTime = newProject.getDates();

        //Act
        boolean actual = Objects.equals(projectTime.startDate, time.startDate)
                && Objects.equals(projectTime.endDate, time.endDate);

        //Assert
        Assertions.assertTrue(actual);
    }

    @Test
    void getNumberOfPlannedSprints() {
        //Arrange
        int number = 1;

        //Act
        boolean actual = newProject.getNumberOfPlannedSprints() == number;

        //Assert
        Assertions.assertTrue(actual);
    }

    @Test
    void getBudget() {
        //Arrange
        double number = 500;

        //Act
        boolean actual = newProject.getBudget() == number;

        //Assert
        Assertions.assertTrue(actual);
    }

    @Test
    void getSprintDuration() {
        //Arrange
        double number = 1;

        //Act
        boolean actual = newProject.getSprintDuration() == number;

        //Assert
        Assertions.assertTrue(actual);
    }

    @Test
    void addName() {
        //Arrange
        String expected = "otherName";
        ProjectName name1 = ProjectName.create(expected);
        Project newProject = new Project(code, name1, description, customerName);

        //Act
        newProject.addName(name1);
        String actual = newProject.getName();

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void addDescription() {
        //Arrange
        String expected = "otherDescription";
        ProjectDescription description1 = ProjectDescription.create(expected);
        Project newProject = new Project(code, name, description1, customerName);

        //Act
        newProject.addDescription(description1);
        String actual = newProject.getDescription();

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void addCustomer() {
        //Arrange
        String expected = "torres";
        Project newProject = new Project(code, name, description, customerName);
        CustomerName customerName = CustomerName.create(expected);

        //Act
        newProject.addCustomer(customerName);
        String actual = newProject.getCustomerName();

        //Assert
        Assertions.assertEquals(expected, actual);
    }
}
