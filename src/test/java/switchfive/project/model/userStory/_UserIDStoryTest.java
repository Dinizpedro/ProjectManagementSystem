package switchfive.project.model.userStory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import switchfive.project.domain.shared.valueObjects.UserStoryID;

class _UserIDStoryTest {

    @Test
    void createUserStoryID() {
        //Arrange
        String projectCode = "P0020";
        String userStoryCode = "US1";
        UserStoryID idUserStory =
                UserStoryID.createUserStoryID(projectCode, userStoryCode);

        //Act
        String projectCodeExpected = idUserStory.getProjectCode();
        String userStoryCodeExpected = idUserStory.getUserStoryCode();

        //Assert
        Assertions.assertEquals(projectCodeExpected, projectCode);
        Assertions.assertEquals(userStoryCodeExpected, userStoryCode);
    }

    @Test
    void createDifferentProjectCodeUserStoryID() {
        //Arrange
        String projectCode1 = "P0020";
        String userStoryCode1 = "US1";
        UserStoryID idUserStory1 =
                UserStoryID.createUserStoryID(projectCode1, userStoryCode1);

        String projectCode2 = "P0030";
        String userStoryCode2 = "US1";
        UserStoryID idUserStory2 =
                UserStoryID.createUserStoryID(projectCode2, userStoryCode2);

        //Act && Assert
        Assertions.assertFalse(idUserStory1.equals(idUserStory2));
    }

    @Test
    void createDifferentUserStoryCodeUserStoryID() {
        //Arrange
        String projectCode1 = "P0030";
        String userStoryCode1 = "US1";
        UserStoryID idUserStory1 =
                UserStoryID.createUserStoryID(projectCode1, userStoryCode1);

        String projectCode2 = "P0030";
        String userStoryCode2 = "US2";
        UserStoryID idUserStory2 =
                UserStoryID.createUserStoryID(projectCode2, userStoryCode2);

        //Act && Assert
        Assertions.assertFalse(idUserStory1.equals(idUserStory2));
    }

    @Test
    void createTotallyDifferentUserStoryID() {
        //Arrange
        String projectCode1 = "P0040";
        String userStoryCode1 = "US5";
        UserStoryID idUserStory1 =
                UserStoryID.createUserStoryID(projectCode1, userStoryCode1);

        String projectCode2 = "P0030";
        String userStoryCode2 = "US2";
        UserStoryID idUserStory2 =
                UserStoryID.createUserStoryID(projectCode2, userStoryCode2);

        //Act && Assert
        Assertions.assertFalse(idUserStory1.equals(idUserStory2));
    }

    @Test
    void createEqualUserStoryID() {
        //Arrange
        String projectCode1 = "P0020";
        String userStoryCode1 = "US1";
        UserStoryID idUserStory1 =
                UserStoryID.createUserStoryID(projectCode1, userStoryCode1);

        String projectCode2 = "P0020";
        String userStoryCode2 = "US1";
        UserStoryID idUserStory2 =
                UserStoryID.createUserStoryID(projectCode2, userStoryCode2);

        //Act && Assert
        Assertions.assertTrue(idUserStory1.equals(idUserStory2));
    }

    @Test
    void compareUserStoryIDWithSameAndOtherObject() {
        //Arrange
        String projectCode1 = "P0020";
        String userStoryCode1 = "US1";
        UserStoryID idUserStory1 =
                UserStoryID.createUserStoryID(projectCode1, userStoryCode1);

        //Act && Assert
        Assertions.assertFalse(idUserStory1.equals(projectCode1));
        Assertions.assertTrue(idUserStory1.equals(idUserStory1));
    }

    @Test
    void hashTotallyDifferentUserStoryID() {
        //Arrange
        String projectCode1 = "P0040";
        String userStoryCode1 = "US5";
        UserStoryID idUserStory1 =
                UserStoryID.createUserStoryID(projectCode1, userStoryCode1);

        String projectCode2 = "P0030";
        String userStoryCode2 = "US2";
        UserStoryID idUserStory2 =
                UserStoryID.createUserStoryID(projectCode2, userStoryCode2);

        //Act
        boolean result = idUserStory1.hashCode() == idUserStory2.hashCode();

        // Assert
        Assertions.assertFalse(result);
    }

    @Test
    void equalsHashUserStoryID() {
        //Arrange
        String projectCode1 = "P0040";
        String userStoryCode1 = "US5";
        UserStoryID idUserStory1 =
                UserStoryID.createUserStoryID(projectCode1, userStoryCode1);

        String projectCode2 = "P0040";
        String userStoryCode2 = "US5";
        UserStoryID idUserStory2 =
                UserStoryID.createUserStoryID(projectCode2, userStoryCode2);

        //Act
        boolean result = idUserStory1.hashCode() == idUserStory2.hashCode();

        // Assert
        Assertions.assertTrue(result);
    }
}
