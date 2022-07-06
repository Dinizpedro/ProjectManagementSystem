package switchfive.project.model.userStory;

import org.junit.jupiter.api.Test;
import switchfive.project.domain.shared.valueObjects.UserStoryCode;

import static org.junit.jupiter.api.Assertions.*;

class UserStoryCodeTest {

    @Test
    void createNotEqualUserStoryCode() {
        UserStoryCode userStoryCode1 = UserStoryCode.createUserStoryCode("US1");
        UserStoryCode userStoryCode2 = UserStoryCode.createUserStoryCode("US2");

        assertFalse(userStoryCode1.sameValueAs(userStoryCode2));
        assertFalse(userStoryCode1.equals(userStoryCode2));
        assertFalse(userStoryCode1.hashCode() == userStoryCode2.hashCode());
    }

    @Test
    void createEqualUserStoryCode() {
        UserStoryCode userStoryCode1 = UserStoryCode.createUserStoryCode("US3");
        UserStoryCode userStoryCode2 = UserStoryCode.createUserStoryCode("US3");

        assertTrue(userStoryCode1.sameValueAs(userStoryCode2));
        assertEquals(userStoryCode1, userStoryCode2);
        assertEquals(userStoryCode1.hashCode(), userStoryCode2.hashCode());
    }

    @Test
    void compareUserStoryCodeWithSameAndOtherObject() {
        UserStoryCode userStoryCode1 = UserStoryCode.createUserStoryCode("US1");
        String userStoryCode = "US1";

        assertNotEquals(userStoryCode1, userStoryCode);
        assertEquals(userStoryCode1, userStoryCode1);
    }

    @Test
    void getCode() {
        UserStoryCode userStoryCode1 = UserStoryCode.createUserStoryCode("US1");
        String userStoryCode = "US1";

        assertEquals(userStoryCode, userStoryCode1.getIdentity());
    }
}
