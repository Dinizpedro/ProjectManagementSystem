package switchfive.project.domain.shared.valueObjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserNameTest {

    @Test
    void createUsernameSuccessfully() {

        //Arrange
        String userNameString = "mcValter";
        UserName userName = UserName.createUsername(userNameString);

        //Act

        //Assert
        assertEquals(userName, userName);
    }

    @Test
    void createUsernameCaseNull() {

        //Arrange

        //Act

        //Assert
        assertThrows(NullPointerException.class,
                () -> UserName.createUsername(null));
    }

    @Test
    void createUsernameCaseFalse() {

        //Arrange
        String userNameString = "mcValter@";

        //Act

        //Assert
        assertThrows(IllegalArgumentException.class,
                () -> UserName.createUsername(userNameString));
    }


    @Test
    void getUserName() {

        //Arrange
        String userNameString = "mcValter";
        UserName userName = UserName.createUsername(userNameString);
        String result = userName.getUserName();

        //Act

        //Assert
        assertEquals(result, userNameString);
    }

    @Test
    void testEqualsCaseSameObjects() {

        //Arrange
        String userNameString = "mcValter";
        UserName userName = UserName.createUsername(userNameString);

        //Act

        //Assert
        assertEquals(userName, userName);
    }

    @Test
    void testEqualsCaseNull() {

        //Arrange
        String userNameString = "mcValter";
        UserName userName = UserName.createUsername(userNameString);

        //Act

        //Assert
        assertNotEquals(null, userName);
    }

    @Test
    void testEqualsCaseDifferentClasses() {

        //Arrange
        String userNameString = "mcValter";
        UserName userName = UserName.createUsername(userNameString);

        //Act

        //Assert
        assertNotEquals(userName, userNameString);
    }

    @Test
    void testEqualsCaseTrue() {

        //Arrange
        String userNameString = "mcValter";
        UserName userName = UserName.createUsername(userNameString);
        UserName userNameTwo = UserName.createUsername(userNameString);

        //Act

        //Assert
        assertEquals(userName, userNameTwo);
    }

    @Test
    void testEqualsCaseFalse() {

        //Arrange
        String userNameString = "mcValter";
        UserName userName = UserName.createUsername(userNameString);
        String userNameStringTwo = "mcTania";
        UserName userNameTwo = UserName.createUsername(userNameStringTwo);

        //Act

        //Assert
        assertNotEquals(userName, userNameTwo);
    }

    @Test
    void testHashCodeCaseTrue() {

        //Arrange
        String userNameString = "mcValter";
        UserName userName = UserName.createUsername(userNameString);
        UserName userNameTwo = UserName.createUsername(userNameString);

        //Act

        //Assert
        assertEquals(userName.hashCode(), userNameTwo.hashCode());
    }

    @Test
    void testHashCodeCaseFalse() {

        //Arrange
        String userNameString = "mcValter";
        UserName userName = UserName.createUsername(userNameString);
        String userNameStringTwo = "mcTania";
        UserName userNameTwo = UserName.createUsername(userNameStringTwo);

        //Act

        //Assert
        assertNotEquals(userName.hashCode(), userNameTwo.hashCode());
    }

    @Test
    void sameValueAsCaseTrue() {

        //Arrange
        String userNameString = "mcValter";
        UserName userName = UserName.createUsername(userNameString);
        UserName userNameTwo = UserName.createUsername(userNameString);

        //Act

        //Assert
        assertTrue(userName.sameValueAs(userNameTwo));
    }

    @Test
    void sameValueAsCaseNull() {

        //Arrange
        String userNameString = "mcValter";
        UserName userName = UserName.createUsername(userNameString);

        //Act

        //Assert
        assertFalse(userName.sameValueAs(null));
    }

    @Test
    void sameValueAsCaseFalse() {

        //Arrange
        String userNameString = "mcValter";
        UserName userName = UserName.createUsername(userNameString);
        String userNameStringTwo = "mcTania";
        UserName userNameTwo = UserName.createUsername(userNameStringTwo);

        //Act

        //Assert
        assertFalse(userName.sameValueAs(userNameTwo));
    }
}
