package switchfive.project.model;

import org.junit.jupiter.api.Test;
import switchfive.project.domain.shared.valueObjects.SprintID;

import static org.junit.jupiter.api.Assertions.*;

class _SprintIDTest {

    @Test
    void equalsSameObject() {
        // Arrange
        SprintID idSprint = SprintID.createSprintID("1", 1);

        // Act

        // Arrange
        assertEquals(idSprint, idSprint);

    }

    @Test
    void notEqualsBecauseObjectsAreDifferent() {
        // Arrange
        SprintID idSprint = SprintID.createSprintID("1", 1);

        // Act

        // Arrange
        assertNotEquals(idSprint, 1);
    }

    @Test
    void sameObjectsBecauseAttributesAreSame() {
        // Arrange
        SprintID idSprintOne = SprintID.createSprintID("1", 1);
        SprintID idSprintTwo = SprintID.createSprintID("1", 1);

        // Act

        // Arrange
        assertEquals(idSprintOne, idSprintTwo);
    }


    @Test
    void sameHashCode() {
        // Arrange
        SprintID idSprintOne = SprintID.createSprintID("1", 1);
        SprintID idSprintTwo = SprintID.createSprintID("1", 1);

        // Act

        // Arrange
        assertEquals(idSprintOne.hashCode(), idSprintTwo.hashCode());
    }

    @Test
    void anInstanceShouldNotBeEqualToNull() {
        assertNotNull(SprintID.createSprintID("1", 1));
    }

    @Test
    void setSprintNumberShouldChangeTheInstanceSprintNumberField() {
     SprintID sprintID = SprintID.createSprintID("1", 1);
     sprintID.setSprintNumber(2);
     assertEquals(2, sprintID.getSprintNumber());
    }

    @Test
    void setProjectCodeShouldChangeTheInstanceProjectCodeField() {
        SprintID sprintID = SprintID.createSprintID("1", 1);
        sprintID.setProjectCode("2");
        assertEquals("2", sprintID.getProjectCode());
    }
}
