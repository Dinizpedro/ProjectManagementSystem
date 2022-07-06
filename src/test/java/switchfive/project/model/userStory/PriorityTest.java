package switchfive.project.model.userStory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import switchfive.project.domain.shared.valueObjects.Priority;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PriorityTest {

    @Test
    void createValidPriority() {
        //Arrange
        Priority priority = Priority.createPriority(1);

        //Act && Assert
        Assertions.assertEquals(priority.priority, 1);
    }

    @Test
    void createInvalidPriority() {
        //Act && Assert
        assertThrows(IllegalArgumentException.class,
                () -> {
                    Priority.createPriority(0);
                });
    }

    @Test
    void createDifferentValidPriority() {
        //Arrange
        Priority priority1 = Priority.createPriority(1);
        Priority priority2 = Priority.createPriority(2);

        //Act && Assert
        Assertions.assertFalse(priority1.equals(priority2));
        Assertions.assertFalse(priority1.sameValueAs(priority2));
        Assertions.assertFalse(priority1.hashCode() == priority2.hashCode());
    }

    @Test
    void createEqualValidPriority() {
        //Arrange
        Priority priority1 = Priority.createPriority(3);
        Priority priority2 = Priority.createPriority(3);

        //Act && Assert
        Assertions.assertTrue(priority1.equals(priority2));
        Assertions.assertTrue(priority1.sameValueAs(priority2));
        Assertions.assertTrue(priority1.hashCode() == priority2.hashCode());
    }

    @Test
    void compareWithSameAndOtherObject() {
        //Arrange
        Priority priority = Priority.createPriority(8);
        int priorityToTest = 8;

        //Act && Assert
        Assertions.assertFalse(priority.equals(priorityToTest));
        Assertions.assertTrue(priority.equals(priority));
    }
}
