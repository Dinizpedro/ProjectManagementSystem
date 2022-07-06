package switchfive.project.domain.aggregates.resource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchfive.project.domain.shared.valueObjects.*;

import java.text.ParseException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ResourceTest {


    ResourceID resourceID = ResourceID.createResourceID("123e4567-e89b-12d3-a456-426614174000");
    Email userID = Email.create("benfica@campeao.pt");
    ProjectCode code = ProjectCode.create("ISEP1");
    Time dates = Time.create("26/04/2023", "27/04/2024");
    ResourceCostPerHour costPerHour = ResourceCostPerHour.create(50);
    ResourcePercentageOfAllocation allocation = ResourcePercentageOfAllocation.create(10);
    Role role = Role.PROJECT_MANAGER;
    Resource newResource;

    ResourceTest() throws ParseException {
    }

    @BeforeEach
    void setUp() {
        newResource = new Resource(resourceID, userID, code, dates, costPerHour, allocation, role);
    }

    @Test
    void getAllocation() {
        //Arrange
        double allocation = 10;

        //Act
        boolean actual = Objects.equals(newResource.getAllocation(), allocation);

        //Assert
        Assertions.assertTrue(actual);
    }

    @Test
    void getCostPerHour() {
        //Arrange
        double costPerHour = 50;

        //Act
        boolean actual = Objects.equals(newResource.getCostPerHour(), costPerHour);

        //Assert
        Assertions.assertTrue(actual);
    }

    @Test
    void getUserID() {
        //Arrange
        Email userIDInput = Email.create("benfica@campeao.pt");

        //Act
        boolean actual = Objects.equals(newResource.getUserID(), userIDInput);

        //Assert
        Assertions.assertTrue(actual);
    }

    @Test
    void getCode() {
        //Arrange
        String code = "ISEP1";

        //Act
        boolean actual = Objects.equals(newResource.getProjectCode(), code);

        //Assert
        Assertions.assertTrue(actual);
    }

    @Test
    void getRole() {
        //Arrange
        //Act
        boolean actual = Objects.equals(newResource.getRole(), role.toString());

        //Assert
        Assertions.assertTrue(actual);
    }

    @Test
    void thisResourceIsTheSameAsOther() {

        //Arrange
        Resource clone = newResource;

        //Act & Assert
        Assertions.assertEquals(newResource, clone);
    }

    @Test
    void thisResourceIsNotEqualsToOther() {
        //Arrange
        ResourceID resourceIDClone =
                ResourceID.createResourceID("123e4667-e89b-12d3-a456-426614174000");

        Resource clone = new Resource(resourceIDClone, userID, code, dates, costPerHour, allocation, role);

        //Act
        boolean actual = newResource.equals(clone);

        //Assert
        Assertions.assertFalse(actual);
    }

    @Test
    @DisplayName("Two compared instances are equals.")
    void comparedResourcesAreEquals() throws ParseException {
        //Arrange
        ResourceID resourceID = ResourceID.createResourceID();
        Email email = Email.create("valid@gmail.com");
        ProjectCode projectCode = ProjectCode.create("XPTO1");
        Time dates = Time.create("06/04/2023", "06/04/2024");
        ResourceCostPerHour costPerHour = ResourceCostPerHour.create(5);
        ResourcePercentageOfAllocation allocation =
                ResourcePercentageOfAllocation.create(50);
        Role role = Role.TEAM_MEMBER;

        Resource newResource = new Resource(resourceID, email, projectCode,
                dates, costPerHour, allocation, role);

        Resource otherResource = new Resource(resourceID, email, projectCode,
                dates, costPerHour, allocation, role);

        //Act
        boolean actual = newResource.equals(otherResource);

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Two compared instances are the same.")
    void comparedResourcesAreTheSame() throws ParseException {

        //Arrange
        ResourceID resourceID = ResourceID.createResourceID("123e4567-e89b-12d3-a456-426614174000");
        Email userID = Email.create("benfica@campeao.pt");
        ProjectCode code = ProjectCode.create("ISEP1");
        Time dates = Time.create("26/04/2023", "27/04/2024");
        ResourceCostPerHour costPerHour = ResourceCostPerHour.create(50);
        ResourcePercentageOfAllocation allocation = ResourcePercentageOfAllocation.create(10);
        Role role = Role.PROJECT_MANAGER;

        Resource newResourceOther = new Resource(resourceID, userID, code,
                dates, costPerHour, allocation, role);

        //Act
        boolean actual = newResource.equals(newResourceOther);

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Two compared instances are not equals.")
    void comparedResourcesAreNotEquals() throws ParseException {
        //Arrange
        ResourceID resourceID = ResourceID.createResourceID();
        ResourceID resourceIdOther = ResourceID.createResourceID();
        Email email = Email.create("valid@gmail.com");
        ProjectCode projectCode = ProjectCode.create("XPTO1");
        Time dates = Time.create("06/04/2023", "06/04/2024");
        ResourceCostPerHour costPerHour = ResourceCostPerHour.create(5);
        ResourcePercentageOfAllocation allocation =
                ResourcePercentageOfAllocation.create(50);
        Role role = Role.TEAM_MEMBER;

        Resource newResource = new Resource(resourceID, email, projectCode,
                dates, costPerHour, allocation, role);

        Resource otherResource =
                new Resource(resourceIdOther, email, projectCode,
                        dates, costPerHour, allocation, role);

        //Act
        boolean actual = newResource.equals(otherResource);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Two compared objects are not instances of same class.")
    void comparedObjectsAreNotInstancesOfSameClass() throws ParseException {
        //Arrange
        ResourceID resourceID = ResourceID.createResourceID();
        Email email = Email.create("valid@gmail.com");
        ProjectCode projectCode = ProjectCode.create("XPTO1");
        Time dates = Time.create("06/04/2023", "06/04/2024");
        ResourceCostPerHour costPerHour = ResourceCostPerHour.create(5);
        ResourcePercentageOfAllocation allocation =
                ResourcePercentageOfAllocation.create(50);
        Role role = Role.TEAM_MEMBER;

        Resource newResource = new Resource(resourceID, email, projectCode,
                dates, costPerHour, allocation, role);

        Object otherResource = new Object();

        //Act
        boolean actual = newResource.equals(otherResource);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Other object is null. Expected false.")
    void otherObjectIsNull() throws ParseException {
        //Arrange
        ResourceID resourceID = ResourceID.createResourceID();
        Email email = Email.create("valid@gmail.com");
        ProjectCode projectCode = ProjectCode.create("XPTO1");
        Time dates = Time.create("06/04/2023", "06/04/2024");
        ResourceCostPerHour costPerHour = ResourceCostPerHour.create(5);
        ResourcePercentageOfAllocation allocation =
                ResourcePercentageOfAllocation.create(50);
        Role role = Role.TEAM_MEMBER;

        Resource newResource = new Resource(resourceID, email, projectCode,
                dates, costPerHour, allocation, role);

        //Act && Assert
        assertNotEquals(null, newResource);
    }

    @Test
    @DisplayName("Objects are equals. Expected same hashcode.")
    void sameHashcode() throws ParseException {
        //Arrange
        ResourceID resourceID = ResourceID.createResourceID();
        Email email = Email.create("valid@gmail.com");
        ProjectCode projectCode = ProjectCode.create("XPTO1");
        Time dates = Time.create("06/04/2023", "06/04/2024");
        ResourceCostPerHour costPerHour = ResourceCostPerHour.create(5);
        ResourcePercentageOfAllocation allocation =
                ResourcePercentageOfAllocation.create(50);
        Role role = Role.TEAM_MEMBER;

        Resource newResource = new Resource(resourceID, email, projectCode,
                dates, costPerHour, allocation, role);

        Resource otherResource = new Resource(resourceID, email, projectCode,
                dates, costPerHour, allocation, role);

        //Act
        boolean actual = newResource.hashCode() == otherResource.hashCode();

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Objects are not equals. Expected different hashcode.")
    void differentHashcode() throws ParseException {
        //Arrange
        ResourceID resourceID = ResourceID.createResourceID();
        ResourceID resourceIdOther = ResourceID.createResourceID();
        Email email = Email.create("valid@gmail.com");
        ProjectCode projectCode = ProjectCode.create("XPTO1");
        Time dates = Time.create("06/04/2023", "06/04/2024");
        ResourceCostPerHour costPerHour = ResourceCostPerHour.create(5);
        ResourcePercentageOfAllocation allocation =
                ResourcePercentageOfAllocation.create(50);
        Role role = Role.TEAM_MEMBER;

        Resource newResource = new Resource(resourceID, email, projectCode,
                dates, costPerHour, allocation, role);

        Resource otherResource = new Resource(resourceIdOther, email, projectCode,
                dates, costPerHour, allocation, role);

        //Act
        boolean actual = newResource.hashCode() == otherResource.hashCode();

        //Assert
        assertFalse(actual);
    }

    @Test
    void compareUserAndSM() {

        // Act
        String userIDToCompare = "valid@gmail.com";
        boolean result = newResource.compareUserAndSM(userIDToCompare);

        // Assert
        assertFalse(result);
    }

    @Test
    void compareUserAndSMFailed() {

        // Act
        String userIDToCompare = "benfica@campeao.pt";
        boolean result = newResource.compareUserAndSM(userIDToCompare);

        // Assert
        assertFalse(result);
    }

    @Test
    void compareUserAndPO() {

        // Act
        String userIDToCompare = "valid@gmail.com";
        boolean result = newResource.compareUserAndPO(userIDToCompare);

        // Assert
        assertFalse(result);
    }

    @Test
    void compareUserAndPOFailed() {

        // Act
        String userIDToCompare = "benfica@campeao.pt";
        boolean result = newResource.compareUserAndPO(userIDToCompare);

        // Assert
        assertFalse(result);
    }

    @Test
    void compareUserAndTeamMember() {

        // Act
        String userIDToCompare = "valid@gmail.com";
        boolean result = newResource.compareUserAndTeamMember(userIDToCompare);
        // Assert
        assertFalse(result);

    }

    @Test
    void compareUserAndTeamMemberFailed() {

        // Act
        String userIDToCompare = "benfica@campeao.pt";
        boolean result = newResource.compareUserAndPO(userIDToCompare);

        // Assert
        assertFalse(result);
    }

    @Test
    void isTeamMemberTrue() throws ParseException {

        // Arrange
        ResourceID resourceID = ResourceID.createResourceID("123e4567-e89b-12d3-a456-426614174000");
        Email userID = Email.create("benfica@campeao.pt");
        ProjectCode code = ProjectCode.create("ISEP1");
        Time dates = Time.create("26/04/2023", "27/04/2024");
        ResourceCostPerHour costPerHour = ResourceCostPerHour.create(50);
        ResourcePercentageOfAllocation allocation = ResourcePercentageOfAllocation.create(10);
        Role role = Role.TEAM_MEMBER;

        Resource newResourceTeamMember = new Resource(resourceID, userID, code, dates, costPerHour, allocation, role);

        // Act
        boolean result = newResourceTeamMember.isTeamMember();

        // Assert
        assertTrue(result);
    }

    @Test
    void isTeamMemberFalse() throws ParseException {

        // Arrange
        ResourceID resourceID = ResourceID.createResourceID("123e4567-e89b-12d3-a456-426614174000");
        Email userID = Email.create("benfica@campeao.pt");
        ProjectCode code = ProjectCode.create("ISEP1");
        Time dates = Time.create("26/04/2023", "27/04/2024");
        ResourceCostPerHour costPerHour = ResourceCostPerHour.create(50);
        ResourcePercentageOfAllocation allocation = ResourcePercentageOfAllocation.create(10);
        Role role = Role.PRODUCT_OWNER;

        Resource newResourceTeamMember = new Resource(resourceID, userID, code, dates, costPerHour, allocation, role);

        // Act
        boolean result = newResourceTeamMember.isTeamMember();

        // Assert
        assertFalse(result);
    }

    @Test
    void isScrumMasterTrue() throws ParseException {

        // Arrange
        ResourceID resourceID = ResourceID.createResourceID("123e4567-e89b-12d3-a456-426614174000");
        Email userID = Email.create("benfica@campeao.pt");
        ProjectCode code = ProjectCode.create("ISEP1");
        Time dates = Time.create("26/04/2023", "27/04/2024");
        ResourceCostPerHour costPerHour = ResourceCostPerHour.create(50);
        ResourcePercentageOfAllocation allocation = ResourcePercentageOfAllocation.create(10);
        Role role = Role.SCRUM_MASTER;

        Resource newResourceScrumMaster = new Resource(resourceID, userID, code, dates, costPerHour, allocation, role);

        // Act
        boolean result = newResourceScrumMaster.isScrumMaster();

        // Assert
        assertTrue(result);
    }

    @Test
    void isScrumMasterFalse() throws ParseException {

        // Arrange
        ResourceID resourceID = ResourceID.createResourceID("123e4567-e89b-12d3-a456-426614174000");
        Email userID = Email.create("benfica@campeao.pt");
        ProjectCode code = ProjectCode.create("ISEP1");
        Time dates = Time.create("26/04/2023", "27/04/2024");
        ResourceCostPerHour costPerHour = ResourceCostPerHour.create(50);
        ResourcePercentageOfAllocation allocation = ResourcePercentageOfAllocation.create(10);
        Role role = Role.TEAM_MEMBER;

        Resource newResourceScrumMasterFalse = new Resource(resourceID, userID, code, dates, costPerHour, allocation, role);

        // Act
        boolean result = newResourceScrumMasterFalse.isScrumMaster();

        // Assert
        assertFalse(result);
    }

    @Test
    void isProjectManagerTrue() {

        // Act
        boolean result = newResource.isProjectManager();

        // Assert
        assertTrue(result);
    }

    @Test
    void isProjectManagerFalse() throws ParseException {

        //Arrange
        ResourceID resourceID = ResourceID.createResourceID();
        Email email = Email.create("valid@gmail.com");
        ProjectCode projectCode = ProjectCode.create("XPTO1");
        Time dates = Time.create("06/04/2023", "06/04/2024");
        ResourceCostPerHour costPerHour = ResourceCostPerHour.create(5);
        ResourcePercentageOfAllocation allocation =
                ResourcePercentageOfAllocation.create(50);
        Role role = Role.TEAM_MEMBER;

        Resource newResource1 = new Resource(resourceID, email, projectCode,
                dates, costPerHour, allocation, role);
        // Act
        boolean result = newResource1.isProjectManager();

        // Assert
        assertFalse(result);
    }

    @Test
    void getResourceId() {
        //Arrange
        ResourceID resourceID1 = ResourceID.createResourceID("123e4567-e89b-12d3-a456-426614174000");

        //Act
        boolean actual = Objects.equals(newResource.getResourceID(), resourceID1.toString());

        //Assert
        Assertions.assertTrue(actual);
    }

    @Test
    void shouldReturnFalseWhenResourceIsNotScrumMaster() {

        assertFalse(newResource.isScrumMaster());

    }


    @Test
    void shouldReturnTrueWhenResourceIsScrumMaster() {

        Role scrumMaster = Role.SCRUM_MASTER;

        Resource resourceWithSMRole = new Resource(resourceID, userID, code, dates, costPerHour, allocation, scrumMaster);


        assertTrue(resourceWithSMRole.isScrumMaster());

    }


    @Test
    void shouldReturnFalseWhenResourceIsNotTeamMember() {

        assertFalse(newResource.isTeamMember());

    }


    @Test
    void shouldReturnTrueWhenResourceIsTeamMember() {

        Role teamMember = Role.TEAM_MEMBER;

        Resource resourceWithTMRole = new Resource(resourceID, userID, code, dates, costPerHour, allocation, teamMember);


        assertTrue(resourceWithTMRole.isTeamMember());

    }


    @Test
    void shouldReturnTrueWhenResourceIsInProject() {

        assertTrue(newResource.isResourceInProject(code));

    }


    @Test
    void shouldReturnFalseWhenResourceIsNotInProject() {

        ProjectCode differentProjectCode = ProjectCode.create("P9999");

        assertFalse(newResource.isResourceInProject(differentProjectCode));

    }


    @Test
    void shouldReturnTrueWhenResourceHasTheSameIDAndIsTeamMember() {

        Role teamMember = Role.TEAM_MEMBER;

        Resource resourceWithTMRole = new Resource(resourceID, userID, code, dates, costPerHour, allocation, teamMember);


        assertTrue(resourceWithTMRole.compareUserAndTeamMember("benfica@campeao.pt"));

    }


    @Test
    void shouldReturnFalseWhenResourceHasDifferentIDButIsTeamMember() {

        Role teamMember = Role.TEAM_MEMBER;

        Resource resourceWithTMRole = new Resource(resourceID, userID, code, dates, costPerHour, allocation, teamMember);


        assertFalse(resourceWithTMRole.compareUserAndTeamMember("different@email.com"));

    }


    @Test
    void shouldReturnFalseWhenResourceHasTheSameIDButIsNotTeamMember() {

        Resource resourceWithTMRole = new Resource(resourceID, userID, code, dates, costPerHour, allocation, role);


        assertFalse(resourceWithTMRole.compareUserAndTeamMember("benfica@campeao.pt"));

    }


    @Test
    void shouldReturnFalseWhenResourceHasDifferentIDAndIsNotTeamMember() {

        Resource resourceWithTMRole = new Resource(resourceID, userID, code, dates, costPerHour, allocation, role);


        assertFalse(resourceWithTMRole.compareUserAndTeamMember("different@email.pt"));

    }


    @Test
    void shouldReturnTrueWhenResourceHasTheSameIDAndIsPO() {

        Role productOwner = Role.PRODUCT_OWNER;

        Resource resourceWithTMRole = new Resource(resourceID, userID, code, dates, costPerHour, allocation, productOwner);


        assertTrue(resourceWithTMRole.compareUserAndPO("benfica@campeao.pt"));

    }


    @Test
    void shouldReturnFalseWhenResourceHasDifferentIDButIsProductOwner() {

        Role productOwner = Role.PRODUCT_OWNER;

        Resource resourceWithTMRole = new Resource(resourceID, userID, code, dates, costPerHour, allocation, productOwner);


        assertFalse(resourceWithTMRole.compareUserAndPO("different@email.com"));

    }


    @Test
    void shouldReturnFalseWhenResourceHasTheSameIDButIsNotProductOwner() {

        Resource resourceWithTMRole = new Resource(resourceID, userID, code, dates, costPerHour, allocation, role);


        assertFalse(resourceWithTMRole.compareUserAndPO("benfica@campeao.pt"));

    }


    @Test
    void shouldReturnFalseWhenResourceHasDifferentIDAndIsProductOwner() {

        Resource resourceWithTMRole = new Resource(resourceID, userID, code, dates, costPerHour, allocation, role);


        assertFalse(resourceWithTMRole.compareUserAndPO("different@email.pt"));

    }

    @Test
    void shouldReturnTrueWhenUserIsScrumMasterAndHasTheSameUserID() {

        Role scrumMaster = Role.SCRUM_MASTER;

        Resource resourceWithSMRole = new Resource(resourceID, userID, code, dates, costPerHour, allocation, scrumMaster);


        assertTrue(resourceWithSMRole.compareUserAndSM("benfica@campeao.pt"));
    }


    @Test
    void shouldReturnFalseWhenUserIsScrumMasterButHasDifferentID() {
        Role scrumMaster = Role.SCRUM_MASTER;
        Resource resourceWithSMRole = new Resource(resourceID, userID, code, dates, costPerHour, allocation, scrumMaster);

        assertFalse(resourceWithSMRole.compareUserAndSM("valid@email.com"));
    }

}
