package switchfive.project.domain.shared.valueObjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    void createEmailException() {

        //Arrange
        String emailString = "mcValterisep.pt";

        //Act

        //Assert
        assertThrows(IllegalArgumentException.class,
                () -> Email.create(emailString));
    }

    @Test
    void createEmailSuccessfully() {

        //Arrange
        String emailString = "mcValter@isep.pt";

        //Act
        Email email = Email.create(emailString);

        //Assert
        assertEquals(email, email);
    }

    @Test
    void createEmailNull() {

        //Arrange

        //Act

        //Assert
        assertThrows(NullPointerException.class,
                () -> Email.create(null));
    }

    @Test
    void getUserEmail() {

        //Assert
        String emailString = "mcValter@isep.pt";
        Email email = Email.create(emailString);

        //Act
        String expected = email.getUserEmail();

        //Assert
        assertEquals(expected, emailString);
    }

    @Test
    void testEqualsCaseSameObject() {

        //Arrange
        String emailString = "mcValter@isep.pt";
        Email email = Email.create(emailString);

        //Act

        //Assert
        assertEquals(email, email);
    }

    @Test
    void testEqualsCaseNull() {

        //Arrange
        String emailString = "mcValter@isep.pt";
        Email email = Email.create(emailString);

        //Act

        //Assert
        assertNotEquals(null, email);
    }

    @Test
    void testEqualsCaseDifferentClasses() {

        //Arrange
        String emailString = "mcValter@isep.pt";
        Email email = Email.create(emailString);

        //Act

        //Assert
        assertNotEquals(email, emailString);
    }

    @Test
    void testEqualsCaseTrue() {

        //Arrange
        String emailString = "mcValter@isep.pt";
        Email email = Email.create(emailString);
        Email emailTwo = Email.create(emailString);

        //Act

        //Assert
        assertEquals(email, emailTwo);
    }

    @Test
    void testEqualsCaseFalse() {

        //Arrange
        String emailString = "mcValter@isep.pt";
        Email email = Email.create(emailString);
        String emailStringTwo = "mcTania@isep.pt";
        Email emailTwo = Email.create(emailStringTwo);

        //Act

        //Assert
        assertNotEquals(email, emailTwo);
    }


    @Test
    void testHashCodeCaseTrue() {
        //Arrange
        String emailString = "mcValter@isep.pt";
        Email email = Email.create(emailString);
        Email emailTwo = Email.create(emailString);

        //Act

        //Arrange
        assertEquals(email.hashCode(), emailTwo.hashCode());
    }

    @Test
    void testHashCodeCaseFalse() {
        //Arrange
        String emailString = "mcValter@isep.pt";
        String emailStringTwo = "mcTania@isep.pt";
        Email email = Email.create(emailString);
        Email emailTwo = Email.create(emailStringTwo);

        //Act

        //Arrange
        assertNotEquals(email.hashCode(), emailTwo.hashCode());
    }

    @Test
    void sameValueAsCaseTrue() {
        //Arrange
        String emailString = "mcValter@isep.pt";
        Email email = Email.create(emailString);
        Email emailTwo = Email.create(emailString);

        //Act

        //Assert
        assertTrue(email.sameValueAs(emailTwo));
    }

    @Test
    void sameValueAsCaseFalse() {
        //Arrange
        String emailString = "mcValter@isep.pt";
        Email email = Email.create(emailString);
        String emailStringTwo = "mcTania@isep.pt";
        Email emailTwo = Email.create(emailStringTwo);

        //Act

        //Assert
        assertFalse(email.sameValueAs(emailTwo));
    }

    @Test
    void sameValueAsCaseNull() {
        //Arrange
        String emailString = "mcValter@isep.pt";
        Email email = Email.create(emailString);

        //Act

        //Assert
        assertFalse(email.sameValueAs(null));
    }
}
