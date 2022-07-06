package switchfive.project.model.userStory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import switchfive.project.domain.aggregates.userStory.UserStory;
import switchfive.project.domain.shared.valueObjects.*;

import static org.junit.jupiter.api.Assertions.*;

class UserStoryTest {

    @Test
    void updateStatus() {
        //Arrange
        UserStoryID userStoryID = UserStoryID.createUserStoryID("P01A2", "US1");
        ProjectCode projectCode = ProjectCode.create("P01A2");
        UserStoryCode userStoryCode = UserStoryCode.createUserStoryCode("US1");
        Priority priority = Priority.createPriority(1);
        UserStoryDescription userStoryDescription = UserStoryDescription.createUserStoryDescription("ASDFG GH");
        UserStory userStory = new UserStory(userStoryID,
                projectCode, userStoryCode, priority, userStoryDescription);

        //Act
        userStory.updateStatus("Running");

        //Assert
        assertEquals("RUNNING", userStory.getStatus());
    }

    @Test
    void getUserStoryCode() {
        //Arrange
        UserStoryID userStoryID = UserStoryID.createUserStoryID("P01A2", "US1");
        ProjectCode projectCode = ProjectCode.create("P01A2");
        UserStoryCode userStoryCode = UserStoryCode.createUserStoryCode("US1");
        Priority priority = Priority.createPriority(1);
        UserStoryDescription userStoryDescription = UserStoryDescription.createUserStoryDescription("ASDFG GH");
        UserStory userStory = new UserStory(userStoryID,
                projectCode, userStoryCode, priority, userStoryDescription);

        //Act & Assert
        assertEquals("US1", userStory.getUserStoryCode());
    }

    @Test
    void shouldReturnTrueWhenUserStorySprintIs0() {
        //Arrange
        UserStoryID userStoryID = UserStoryID.createUserStoryID("P01A2", "US1");
        ProjectCode projectCode = ProjectCode.create("P01A2");
        UserStoryCode userStoryCode = UserStoryCode.createUserStoryCode("US1");
        Priority priority = Priority.createPriority(1);
        UserStoryDescription userStoryDescription = UserStoryDescription.createUserStoryDescription("ASDFG GH");
        UserStory userStory = new UserStory(userStoryID,
                projectCode, userStoryCode, priority, userStoryDescription);
        userStory.setSprintID("P01A2", 0);

        assertTrue(userStory.isUserStoryInProductBacklog());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, -1, 15, Integer.MAX_VALUE, Integer.MIN_VALUE})
    void shouldReturnFalseWhenUserStorySprintIsNot0(int number) {
        //Arrange
        UserStoryID userStoryID = UserStoryID.createUserStoryID("P01A2", "US1");
        ProjectCode projectCode = ProjectCode.create("P01A2");
        UserStoryCode userStoryCode = UserStoryCode.createUserStoryCode("US1");
        Priority priority = Priority.createPriority(1);
        UserStoryDescription userStoryDescription = UserStoryDescription.createUserStoryDescription("ASDFG GH");
        UserStory userStory = new UserStory(userStoryID,
                projectCode, userStoryCode, priority, userStoryDescription);
        userStory.setSprintID("P01A2", number);

        assertFalse(userStory.isUserStoryInProductBacklog());
    }

    @Test
    void getProjectCodeOfUserStory() {
        //Arrange
        UserStoryID userStoryID = UserStoryID.createUserStoryID("P01A2", "US1");
        ProjectCode projectCode = ProjectCode.create("P01A2");
        ProjectCode projectCode2 = ProjectCode.create("P01A2");
        UserStoryCode userStoryCode = UserStoryCode.createUserStoryCode("US1");
        Priority priority = Priority.createPriority(1);
        UserStoryDescription userStoryDescription = UserStoryDescription.createUserStoryDescription("ASDFGGH");
        UserStory userStory = new UserStory(userStoryID,
                projectCode, userStoryCode, priority, userStoryDescription);

        //Act & Assert
        assertEquals(projectCode2.getCode(), userStory.getProjectCodeOfUserStory());
    }

    @Test
    void setPriority() {
        //Arrange
        UserStoryID userStoryID = UserStoryID.createUserStoryID("P01A2", "US1");
        ProjectCode projectCode = ProjectCode.create("P01A2");
        UserStoryCode userStoryCode = UserStoryCode.createUserStoryCode("US1");
        Priority priority = Priority.createPriority(1);
        UserStoryDescription userStoryDescription = UserStoryDescription.createUserStoryDescription("ASDFG GH");
        UserStory userStory = new UserStory(userStoryID,
                projectCode, userStoryCode, priority, userStoryDescription);

        //Act
        userStory.setPriority(Priority.createPriority(2));

        //Assert
        assertEquals(2, userStory.getPriority());
    }

    @Test
    void changeEffort() {
        //Arrange
        UserStoryID userStoryID = UserStoryID.createUserStoryID("P01A2", "US1");
        ProjectCode projectCode = ProjectCode.create("P01A2");
        UserStoryCode userStoryCode = UserStoryCode.createUserStoryCode("US1");
        Priority priority = Priority.createPriority(1);
        UserStoryDescription userStoryDescription = UserStoryDescription.createUserStoryDescription("ASDFG GH");
        UserStory userStory = new UserStory(userStoryID,
                projectCode, userStoryCode, priority, userStoryDescription);
        userStory.changeEffort(5);
        //Act & Assert
        assertEquals(5, userStory.getEffort());
    }

    @Test
    void getEffortNull() {
        // Arrange
        UserStoryID userStoryID1 = UserStoryID.createUserStoryID("P01A5", "US1");
        ProjectCode projectCode1 = ProjectCode.create("P01A5");
        UserStoryCode userStoryCode1 = UserStoryCode.createUserStoryCode("US1");
        Priority priority1 = Priority.createPriority(1);
        UserStoryDescription userStoryDescription1 = UserStoryDescription.createUserStoryDescription("ASDFG GH");

        UserStory userStory1 = new UserStory(userStoryID1,
                projectCode1, userStoryCode1, priority1, userStoryDescription1);

        // Act
        Integer result = userStory1.getEffort();

        // Assert
        assertNull(result);
    }

    @Test
    void testHashCode() {
        //Arrange
        UserStoryID userStoryID1 = UserStoryID.createUserStoryID("P01A2", "US1");
        ProjectCode projectCode1 = ProjectCode.create("P01A2");
        UserStoryCode userStoryCode1 = UserStoryCode.createUserStoryCode("US1");
        Priority priority1 = Priority.createPriority(1);
        UserStoryDescription userStoryDescription1 = UserStoryDescription.createUserStoryDescription("ASDFG GH");
        UserStory userStory1 = new UserStory(userStoryID1,
                projectCode1, userStoryCode1, priority1, userStoryDescription1);

        UserStoryID userStoryID2 = UserStoryID.createUserStoryID("P01A5", "US1");
        ProjectCode projectCode2 = ProjectCode.create("P01A5");
        UserStoryCode userStoryCode2 = UserStoryCode.createUserStoryCode("US1");
        Priority priority2 = Priority.createPriority(1);
        UserStoryDescription userStoryDescription2 = UserStoryDescription.createUserStoryDescription("ASDFG GH");
        UserStory userStory2 = new UserStory(userStoryID2,
                projectCode2, userStoryCode2, priority2, userStoryDescription2);

        UserStoryID userStoryID3 = UserStoryID.createUserStoryID("P01A2", "US1");
        ProjectCode projectCode3 = ProjectCode.create("P01A2");
        UserStoryCode userStoryCode3 = UserStoryCode.createUserStoryCode("US1");
        Priority priority3 = Priority.createPriority(1);
        UserStoryDescription userStoryDescription3 = UserStoryDescription.createUserStoryDescription("ASDFG GH");
        UserStory userStory3 = new UserStory(userStoryID3,
                projectCode3, userStoryCode3, priority3, userStoryDescription3);

        //Assert
        assertNotEquals(userStory1.hashCode(), userStory2.hashCode());
        assertEquals(userStory1.hashCode(), userStory3.hashCode());
    }

    @Test
    void testEquals() {
        UserStoryID userStoryID1 = UserStoryID.createUserStoryID("P01A2", "US1");
        ProjectCode projectCode1 = ProjectCode.create("P01A2");
        UserStoryCode userStoryCode1 = UserStoryCode.createUserStoryCode("US1");
        Priority priority1 = Priority.createPriority(1);
        UserStoryDescription userStoryDescription1 = UserStoryDescription.createUserStoryDescription("ASDFG GH");
        UserStory userStory1 = new UserStory(userStoryID1,
                projectCode1, userStoryCode1, priority1, userStoryDescription1);

        UserStoryID userStoryID2 = UserStoryID.createUserStoryID("P01A5", "US1");
        ProjectCode projectCode2 = ProjectCode.create("P01A5");
        UserStoryCode userStoryCode2 = UserStoryCode.createUserStoryCode("US1");
        Priority priority2 = Priority.createPriority(1);
        UserStoryDescription userStoryDescription2 = UserStoryDescription.createUserStoryDescription("ASDFG GH");
        UserStory userStory2 = new UserStory(userStoryID2,
                projectCode2, userStoryCode2, priority2, userStoryDescription2);

        UserStoryID userStoryID3 = UserStoryID.createUserStoryID("P01A2", "US1");
        ProjectCode projectCode3 = ProjectCode.create("P01A2");
        UserStoryCode userStoryCode3 = UserStoryCode.createUserStoryCode("US1");
        Priority priority3 = Priority.createPriority(1);
        UserStoryDescription userStoryDescription3 = UserStoryDescription.createUserStoryDescription("ASDFG GH");
        UserStory userStory3 = new UserStory(userStoryID3,
                projectCode3, userStoryCode3, priority3, userStoryDescription3);

        assertNotEquals(userStory1, projectCode1);
        assertEquals(userStory1, userStory1);
        assertNotEquals(userStory1, userStory2);
        assertEquals(userStory1, userStory3);
    }

    @Test
    void sameIdentityAs() {
        UserStoryID userStoryID1 = UserStoryID.createUserStoryID("P01A5", "US1");
        ProjectCode projectCode1 = ProjectCode.create("P01A5");
        UserStoryCode userStoryCode1 = UserStoryCode.createUserStoryCode("US1");
        Priority priority1 = Priority.createPriority(1);
        UserStoryDescription userStoryDescription1 = UserStoryDescription.createUserStoryDescription("ASDFG GH");
        UserStory userStory1 = new UserStory(userStoryID1,
                projectCode1, userStoryCode1, priority1, userStoryDescription1);

        UserStoryID userStoryID2 = UserStoryID.createUserStoryID("P01A5", "US1");
        ProjectCode projectCode2 = ProjectCode.create("P01A5");
        UserStoryCode userStoryCode2 = UserStoryCode.createUserStoryCode("US1");
        Priority priority2 = Priority.createPriority(1);
        UserStoryDescription userStoryDescription2 = UserStoryDescription.createUserStoryDescription("ASDFG GH");
        UserStory userStory2 = new UserStory(userStoryID2,
                projectCode2, userStoryCode2, priority2, userStoryDescription2);

        UserStoryID userStoryID3 = UserStoryID.createUserStoryID("P01A2", "US1");
        ProjectCode projectCode3 = ProjectCode.create("P01A2");
        UserStoryCode userStoryCode3 = UserStoryCode.createUserStoryCode("US1");
        Priority priority3 = Priority.createPriority(1);
        UserStoryDescription userStoryDescription3 = UserStoryDescription.createUserStoryDescription("ASDFG GH");
        UserStory userStory3 = new UserStory(userStoryID3,
                projectCode3, userStoryCode3, priority3, userStoryDescription3);

        assertFalse(userStory1.sameIdentityAs(userStory3));
        assertTrue(userStory1.sameIdentityAs(userStory2));
    }

    @Test
    void getUserStoryID() {
        // Arrange
        UserStoryID userStoryID1 = UserStoryID.createUserStoryID("P01A5", "US1");
        ProjectCode projectCode1 = ProjectCode.create("P01A5");
        UserStoryCode userStoryCode1 = UserStoryCode.createUserStoryCode("US1");
        Priority priority1 = Priority.createPriority(1);
        UserStoryDescription userStoryDescription1 = UserStoryDescription.createUserStoryDescription("ASDFG GH");
        UserStory userStory1 = new UserStory(userStoryID1,
                projectCode1, userStoryCode1, priority1, userStoryDescription1,
                userStoryCode1);


        // Act
        UserStoryID result = userStory1.getUserStoryID();

        // Assert
        assertEquals(userStoryID1, result);
    }

    @Test
    void getUserStoryDescription() {
        // Arrange
        UserStoryID userStoryID1 = UserStoryID.createUserStoryID("P01A5", "US1");
        ProjectCode projectCode1 = ProjectCode.create("P01A5");
        UserStoryCode userStoryCode1 = UserStoryCode.createUserStoryCode("US1");
        Priority priority1 = Priority.createPriority(1);
        UserStoryDescription userStoryDescription1 = UserStoryDescription.createUserStoryDescription("ASDFG GH");
        UserStory userStory1 = new UserStory(userStoryID1,
                projectCode1, userStoryCode1, priority1, userStoryDescription1,
                userStoryCode1);


        // Act
        String result = userStory1.getUserStoryDescription();

        // Assert
        assertEquals(userStoryDescription1.getDescription(), result);
    }

    @Test
    void getSprintIDNull() {
        // Arrange
        UserStoryID userStoryID1 = UserStoryID.createUserStoryID("P01A5", "US1");
        ProjectCode projectCode1 = ProjectCode.create("P01A5");
        UserStoryCode userStoryCode1 = UserStoryCode.createUserStoryCode("US1");
        Priority priority1 = Priority.createPriority(1);
        UserStoryDescription userStoryDescription1 = UserStoryDescription.createUserStoryDescription("ASDFG GH");
        UserStory userStory1 = new UserStory(userStoryID1,
                projectCode1, userStoryCode1, priority1, userStoryDescription1,
                userStoryCode1);


        // Act
        SprintID result = userStory1.getSprintID();

        // Assert
        assertNull(result);
    }

    @Test
    void getSprintID() {
        // Arrange
        UserStoryID userStoryID1 = UserStoryID.createUserStoryID("P01A5", "US1");
        ProjectCode projectCode1 = ProjectCode.create("P01A5");
        UserStoryCode userStoryCode1 = UserStoryCode.createUserStoryCode("US1");
        Priority priority1 = Priority.createPriority(1);
        UserStoryDescription userStoryDescription1 = UserStoryDescription.createUserStoryDescription("ASDFG GH");
        SprintID sprintID = SprintID.createSprintID("DANI1", 1);
        EffortEstimate effortEstimate = EffortEstimate.createEffortEstimate(1);
        UserStoryStatus userStoryStatus = UserStoryStatus.PLANNED;

        UserStory userStory1 = new UserStory(userStoryID1,
                projectCode1, userStoryCode1, priority1, userStoryDescription1,
                sprintID, effortEstimate, userStoryStatus, userStoryCode1);

        // Act
        SprintID result = userStory1.getSprintID();

        // Assert
        assertEquals(sprintID, result);
    }

    @Test
    void setSprintID() {
        // Arrange
        UserStoryID userStoryID1 = UserStoryID.createUserStoryID("P01A5", "US1");
        ProjectCode projectCode1 = ProjectCode.create("P01A5");
        UserStoryCode userStoryCode1 = UserStoryCode.createUserStoryCode("US1");
        Priority priority1 = Priority.createPriority(1);
        UserStoryDescription userStoryDescription1 = UserStoryDescription.createUserStoryDescription("ASDFG GH");
        SprintID sprintID = SprintID.createSprintID("DANI1", 1);
        EffortEstimate effortEstimate = EffortEstimate.createEffortEstimate(1);
        UserStoryStatus userStoryStatus = UserStoryStatus.PLANNED;

        UserStory userStory1 = new UserStory(userStoryID1,
                projectCode1, userStoryCode1, priority1, userStoryDescription1,
                sprintID, effortEstimate, userStoryStatus, userStoryCode1);

        // Act
        SprintID newSprintID = SprintID.createSprintID("DANI2", 2);
        userStory1.setSprintID("DANI2", 2);
        SprintID result = userStory1.getSprintID();

        // Assert
        assertEquals(newSprintID, result);
    }

    @Test
    void getParentUSNull() {
        // Arrange
        UserStoryID userStoryID1 = UserStoryID.createUserStoryID("P01A5", "US1");
        ProjectCode projectCode1 = ProjectCode.create("P01A5");
        UserStoryCode userStoryCode1 = UserStoryCode.createUserStoryCode("US1");
        Priority priority1 = Priority.createPriority(1);
        UserStoryDescription userStoryDescription1 = UserStoryDescription.createUserStoryDescription("ASDFG GH");

        UserStory userStory1 = new UserStory(userStoryID1,
                projectCode1, userStoryCode1, priority1, userStoryDescription1);

        // Act
        String result = userStory1.getParentUserStory();

        // Assert
        assertNull(result);
    }

    @Test
    void getParentUS() {
        // Arrange
        UserStoryID userStoryID1 = UserStoryID.createUserStoryID("P01A5", "US1");
        ProjectCode projectCode1 = ProjectCode.create("P01A5");
        UserStoryCode userStoryCode1 = UserStoryCode.createUserStoryCode("US1");
        Priority priority1 = Priority.createPriority(1);
        UserStoryDescription userStoryDescription1 = UserStoryDescription.createUserStoryDescription("ASDFG GH");

        UserStory userStory1 = new UserStory(userStoryID1,
                projectCode1, userStoryCode1, priority1, userStoryDescription1,
                userStoryCode1);

        // Act
        String result = userStory1.getParentUserStory();

        // Assert
        assertEquals(userStoryCode1.getIdentity(), result);
    }

    @Test
    void setStatusToRefined() {
        // Arrange
        UserStoryID userStoryID1 = UserStoryID.createUserStoryID("P01A5", "US1");
        ProjectCode projectCode1 = ProjectCode.create("P01A5");
        UserStoryCode userStoryCode1 = UserStoryCode.createUserStoryCode("US1");
        Priority priority1 = Priority.createPriority(1);
        UserStoryDescription userStoryDescription1 = UserStoryDescription.createUserStoryDescription("ASDFG GH");

        UserStory userStory1 = new UserStory(userStoryID1,
                projectCode1, userStoryCode1, priority1, userStoryDescription1);

        // Act
        userStory1.setStatusToRefined();
        String result = userStory1.getStatus();

        // Assert
        assertEquals("REFINED", result);
    }

    @Test
    void isUSavailableForRefinementTrue() {
        // Arrange
        UserStoryID userStoryID1 = UserStoryID.createUserStoryID("P01A5", "US1");
        ProjectCode projectCode1 = ProjectCode.create("P01A5");
        UserStoryCode userStoryCode1 = UserStoryCode.createUserStoryCode("US1");
        Priority priority1 = Priority.createPriority(1);
        UserStoryDescription userStoryDescription1 = UserStoryDescription.createUserStoryDescription("ASDFG GH");

        UserStory userStory1 = new UserStory(userStoryID1,
                projectCode1, userStoryCode1, priority1, userStoryDescription1);

        // Act
        boolean result = userStory1.isUserStoryAvailableForRefinement();

        // Assert
        assertTrue(result);
    }

    @Test
    void isUSavailableForRefinementFalse() {
        // Arrange
        UserStoryID userStoryID1 = UserStoryID.createUserStoryID("P01A5", "US1");
        ProjectCode projectCode1 = ProjectCode.create("P01A5");
        UserStoryCode userStoryCode1 = UserStoryCode.createUserStoryCode("US1");
        Priority priority1 = Priority.createPriority(1);
        UserStoryDescription userStoryDescription1 = UserStoryDescription.createUserStoryDescription("ASDFG GH");

        UserStory userStory1 = new UserStory(userStoryID1,
                projectCode1, userStoryCode1, priority1, userStoryDescription1);

        // Act
        userStory1.setStatusToRefined();
        boolean result = userStory1.isUserStoryAvailableForRefinement();

        // Assert
        assertFalse(result);
    }

    @Test
    void isUserStoryFinishedFalse() {
        //Arrange
        UserStory userStory = new UserStory(UserStoryID.createUserStoryID("P0101", "US1"),
                ProjectCode.create("P0101"),
                UserStoryCode.createUserStoryCode("US2"),
                Priority.createPriority(1),
                UserStoryDescription.createUserStoryDescription("ABC"),
                UserStoryCode.createUserStoryCode("US1"));

        //Act
        boolean expected = userStory.isUserStoryStatusFinished();

        //Assert
        assertFalse(expected);
    }

    @Test
    void isUserStoryFinishedtrue() {
        //Arrange
        UserStory userStory = new UserStory(UserStoryID.createUserStoryID("P0101", "US1"),
                ProjectCode.create("P0101"),
                UserStoryCode.createUserStoryCode("US2"),
                Priority.createPriority(1),
                UserStoryDescription.createUserStoryDescription("ABC"),
                UserStoryCode.createUserStoryCode("US1"));

        userStory.updateStatus("FINISHED");

        //Act
        boolean expected = userStory.isUserStoryStatusFinished();

        //Assert
        assertTrue(expected);
    }
}
