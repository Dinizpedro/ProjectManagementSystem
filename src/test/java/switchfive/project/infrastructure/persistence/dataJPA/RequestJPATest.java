package switchfive.project.infrastructure.persistence.dataJPA;

import org.junit.jupiter.api.Test;
import switchfive.project.dataModel.dataJPA.RequestJPA;

import static org.junit.jupiter.api.Assertions.*;

class RequestJPATest {

    @Test
    void isApprovedTrue() {
        // Arrange
        RequestJPA requestJPA = new RequestJPA();
        requestJPA.setApproved(true);

        // Act
        boolean result = requestJPA.isApproved();

        // Assert
        assertTrue(result);
    }

    @Test
    void isApprovedFalse() {
        // Arrange
        RequestJPA requestJPA = new RequestJPA();
        requestJPA.setApproved(false);

        // Act
        boolean result = requestJPA.isApproved();

        // Assert
        assertFalse(result);
    }
}
