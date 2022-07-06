package switchfive.project.model.userStory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import switchfive.project.domain.shared.valueObjects.UserStoryDescription;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserStoryDescriptionTest {

    @ParameterizedTest
    @ValueSource(strings = {"", "As Product Owner, I want create a user story description valid and add the new user story to user story store. DDDDDDDDDDDDDDDDDDDDDDDDDDDDDERFGTYHJUIK"})
    void createInvalidDescription(String str) {
        Exception exception = assertThrows(Exception.class, () -> {
            UserStoryDescription.createUserStoryDescription(str);
        });

        String expectedMessage = "The description must have between 1 and 150 charters!";
        String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @ParameterizedTest
    @ValueSource(strings = {"A", "As Product Owner, I want create a user story description valid and add the new user story to user story store. DDDDDDDDDDDDDDDDDDDDDDDDDDDDDERFGTYHJUI"})
    void createValidDescription(String str) {

        UserStoryDescription userStoryDescription = UserStoryDescription.createUserStoryDescription(str);

        Assertions.assertInstanceOf(UserStoryDescription.class, userStoryDescription);
    }

    @Test
    void createEqualsDescription() {
        UserStoryDescription userStoryDescription1 = UserStoryDescription.createUserStoryDescription("As");
        UserStoryDescription userStoryDescription2 = UserStoryDescription.createUserStoryDescription("As");

        Assertions.assertTrue(userStoryDescription1.sameValueAs(userStoryDescription2));
        Assertions.assertTrue(userStoryDescription1.equals(userStoryDescription2));
        Assertions.assertTrue(userStoryDescription1.hashCode() == userStoryDescription2.hashCode());
    }

    @Test
    void createNotEqualsDescription() {
        UserStoryDescription userStoryDescription1 = UserStoryDescription.createUserStoryDescription("As");
        UserStoryDescription userStoryDescription2 = UserStoryDescription.createUserStoryDescription("Astr");

        Assertions.assertFalse(userStoryDescription1.sameValueAs(userStoryDescription2));
        Assertions.assertFalse(userStoryDescription1.equals(userStoryDescription2));
        Assertions.assertFalse(userStoryDescription1.hashCode() == userStoryDescription2.hashCode());
    }

    @Test
    void compareWithSameAndOtherObject() {
        UserStoryDescription userStoryDescription1 = UserStoryDescription.createUserStoryDescription("As");
        String description = "As";

        Assertions.assertTrue(userStoryDescription1.equals(userStoryDescription1));
        Assertions.assertFalse(userStoryDescription1.equals(description));
    }

    @Test
    void getDescription() {
        //Arrange
        UserStoryDescription userStoryDescription = UserStoryDescription.
                createUserStoryDescription("ABCD");

        //Act
        String result = userStoryDescription.getDescription();

        //Assert
        assertEquals("ABCD", result);
    }
}
