package switchfive.project.infrastructure.persistence.data.dataJPA;

import org.junit.jupiter.api.Test;
import switchfive.project.dataModel.dataJPA.ResourceJPA;

import static org.junit.jupiter.api.Assertions.*;

class ResourceJPATest {

    @Test
    void getResourceID() {
        // Arrange
        String expected = "4c045378-1c25-4cc0-8e94-49aae08027a2";
        ResourceJPA resourceJPA = new ResourceJPA();
        resourceJPA.setResourceID(expected);

        // Act
        String actual = resourceJPA.getResourceID();

        // Assert
        assertEquals(expected, actual);

    }

    @Test
    void getUserID() {
        // Arrange
        String expected = "benfica@campeao.pt";
        ResourceJPA resourceJPA = new ResourceJPA();
        resourceJPA.setUserID(expected);

        // Act
        String actual = resourceJPA.getUserID();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void getProjectCode() {
        // Arrange
        String expected = "DANI2";
        ResourceJPA resourceJPA = new ResourceJPA();
        resourceJPA.setProjectCode(expected);

        // Act
        String actual = resourceJPA.getProjectCode();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void getStartDate() {
        // Arrange
        String expected = "27/04/2023";
        ResourceJPA resourceJPA = new ResourceJPA();
        resourceJPA.setStartDate(expected);

        // Act
        String actual = resourceJPA.getStartDate();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void getEndDate() {
        // Arrange
        String expected = "27/04/2023";
        ResourceJPA resourceJPA = new ResourceJPA();
        resourceJPA.setEndDate(expected);

        // Act
        String actual = resourceJPA.getEndDate();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void getCostPerHour() {

        // Arrange
        double expected = 50;
        ResourceJPA resourceJPA = new ResourceJPA();
        resourceJPA.setCostPerHour(expected);

        // Act
        double actual = resourceJPA.getCostPerHour();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void getPercentageOfAllocation() {

        // Arrange
        double expected = 50;
        ResourceJPA resourceJPA = new ResourceJPA();
        resourceJPA.setPercentageOfAllocation(expected);

        // Act
        double actual = resourceJPA.getPercentageOfAllocation();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void getRole() {
        // Arrange
        String expected = "TeamMember";
        ResourceJPA resourceJPA = new ResourceJPA();
        resourceJPA.setRole(expected);

        // Act
        String  actual = resourceJPA.getRole();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void getEmail() {
        // Arrange
        String expected = "benfica@campeao.pt";
        ResourceJPA resourceJPA = new ResourceJPA();
        resourceJPA.setEmail(expected);

        // Act
        String  actual = resourceJPA.getEmail();

        // Assert
        assertEquals(expected, actual);
    }
}