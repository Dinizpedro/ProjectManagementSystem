package switchfive.project.infrastructure.persistence.dataJPA;

import org.junit.jupiter.api.Test;
import switchfive.project.dataModel.dataJPA.UserStoryJPA;
import switchfive.project.domain.shared.valueObjects.UserStoryID;

import static org.junit.jupiter.api.Assertions.*;

class UserStoryJPATest {

    @Test
    void userStoryJPA() {
        //Arrange
        UserStoryID userStoryID = UserStoryID.createUserStoryID("P0101", "US2");
        String parentUserStoryCode = "US1";
        int priority = 2;
        String description = "ABC";
        int effort = 4;
        String status = "PLANNED";
        Integer sprintNumber = null;

        //Act
        UserStoryJPA userStoryJPA = new UserStoryJPA();
        userStoryJPA.setUserStoryID(userStoryID);
        userStoryJPA.setParentUserStoryCode(parentUserStoryCode);
        userStoryJPA.setPriority(priority);
        userStoryJPA.setDescription(description);
        userStoryJPA.setEffort(effort);
        userStoryJPA.setStatus(status);
        userStoryJPA.setSprintNumber(sprintNumber);

        //Assert
        assertEquals(userStoryID, userStoryJPA.getUserStoryID());
        assertEquals(parentUserStoryCode, userStoryJPA.getParentUserStoryCode());
        assertEquals(priority, userStoryJPA.getPriority());
        assertEquals(description, userStoryJPA.getDescription());
        assertEquals(effort, userStoryJPA.getEffort());
        assertEquals(status, userStoryJPA.getStatus());
        assertEquals(sprintNumber, userStoryJPA.getSprintNumber());
    }
}
