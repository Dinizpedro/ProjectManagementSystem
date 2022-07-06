package switchfive.project.domain.shared.valueObjects;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {
    @Test
    void sameValuePassword() throws NoSuchAlgorithmException {
        //Arrange
        Password password1 = Password.createPassword("pizzaEbacon223@Aa");

        //Act
        boolean result = password1.sameValueAs(password1);

        //Assert
        assertTrue(result);
    }

    @Test
    void testNullPassword() {
        //Act and Assert
        assertThrows(NullPointerException.class, () ->
            Password.createPassword(null)
        );
    }

    @Test
    void differentPasswords() {
        assertThrows(IllegalArgumentException.class, () ->
            Password.createPassword("frangoEbcaon12@ipp.iesp,pt")
        );
    }

    @Test
    void validPassword() throws NoSuchAlgorithmException {
        Password password1 = Password.createPassword("portaTania12@isep.pt");

        boolean result = password1.sameValueAs(password1);

        assertTrue(result);

    }

    @Test
    void userPasswordCorrect() throws NoSuchAlgorithmException {

        //Arrange
        Password password1 = Password.createPassword("portaTania12@isep.pt");
        Password password2 = Password.createPassword("portaTania12@isep.pt");

        //Act
        String result = password1.getUserPassword();
        String expected = password2.getUserPassword();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void userPasswordIncorrect() throws NoSuchAlgorithmException {

        //Arrange
        Password password1 = Password.createPassword("portaTania12@isep.pt");
        Password password2 = Password.createPassword("portaTaia172@isep.pt");

        //Act
        String result = password1.getUserPassword();
        String expected = password2.getUserPassword();

        //Assert
        assertNotEquals(expected, result);
    }

    @Test
    void userPasswordsEquals() throws NoSuchAlgorithmException {
        Password password1 = Password.createPassword("portaTania12@isept");
        Password password2 = Password.createPassword("portaTania12@isept");

        boolean result = password1.sameValueAs(password2);

        assertTrue(result);
    }

    @Test
    void userPasswordsNotEquals() throws NoSuchAlgorithmException {
        Password password1 = Password.createPassword("portaTania12@isept");
        Password password2 = Password.createPassword("portaanIa12@isept");

        boolean result = password1.sameValueAs(password2);

        assertFalse(result);
    }
    @Test
    void testPasswordDBfailure() throws NoSuchAlgorithmException {


        //Arrange
        Password password1 = Password.createPasswordFromDB("509954fa09ab58286990bc50595b51f3");
        Password password2 = Password.createPassword("T@nia123bacon");

        //Act
        String result = password1.getUserPassword();
        String expected = password2.getUserPassword();

        //Assert
        assertNotEquals(expected, result);

    }
    @Test
    void testPasswordDB() throws NoSuchAlgorithmException {


        //Arrange
        Password password1 = Password.createPasswordFromDB("509954fa09ab58286990bc50595b51f3");
        Password password2 = Password.createPassword("taniaAnanas123@");

        //Act
        String result = password1.getUserPassword();
        String expected = password2.getUserPassword();

        //Assert
        assertEquals(expected, result);

    }
  @Test
    void returnUserPsswordSuccessduly() throws NoSuchAlgorithmException {

        // Arrange
        Password pw1= Password.createPasswordFromDB("509954fa09ab58286990bc50595b51f3");

        // Act
        String expected ="509954fa09ab58286990bc50595b51f3";
        String result = pw1.toString();

        // Assert
        assertEquals(expected, result);



    }

}

