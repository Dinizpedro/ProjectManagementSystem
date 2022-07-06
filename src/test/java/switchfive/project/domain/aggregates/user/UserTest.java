package switchfive.project.domain.aggregates.user;

import org.junit.jupiter.api.Test;
import switchfive.project.domain.shared.valueObjects.*;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserTest {

    @Test
    void getUserEmail() throws NoSuchAlgorithmException {
        // Arrange
        String userEmail = "ricardohorta@rangers.com";

        Email email = Email.create(userEmail);
        Password password = Password.createPassword("123456Aa%");
        UserName userName = UserName.createUsername("Ricardo");
        Function function = Function.createFunction("Magician");
        ProfileDescription profileID = ProfileDescription
                .createProfileDescription("Visitor");

        User newUser = new User(email, password,
                userName, function, profileID);

        // Act
        String result = newUser.getEmail();

        // Assert
        assertEquals(userEmail, result);
    }

    @Test
    void getUserName() throws NoSuchAlgorithmException {
        // Arrange

        String expected = "Ricardo";

        Email email = Email.create("ricardohorta@rangers.com");
        Password password = Password.createPassword("123456Aa%");
        UserName userName = UserName.createUsername(expected);
        Function function = Function.createFunction("Magician");
        ProfileDescription profileID = ProfileDescription
                .createProfileDescription("Visitor");

        User newUser = new User(email, password,
                userName, function, profileID);

        // Act
        String result = newUser.getUserName().getUserName();

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void getFunctionDescription() throws NoSuchAlgorithmException {
        // Arrange

        String expected = "Magician";

        Email email = Email.create("ricardohorta@rangers.com");
        Password password = Password.createPassword("123456Aa%");
        UserName userName = UserName.createUsername("Ricardo");
        Function function = Function.createFunction(expected);
        ProfileDescription profileID = ProfileDescription
                .createProfileDescription("Visitor");

        User newUser = new User(email, password,
                userName, function, profileID);

        // Act
        String result = newUser.getFunctionDescription();

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void getUserProfileList() throws NoSuchAlgorithmException {
        // Arrange
        Email email = Email.create("ricardohorta@rangers.com");
        Password password = Password.createPassword("123456Aa%");
        UserName userName = UserName.createUsername("Ricardo");
        Function function = Function.createFunction("Magician");
        ProfileDescription profileID = ProfileDescription
                .createProfileDescription("Visitor");

        User newUser = new User(email, password,
                userName, function, profileID);

        List<ProfileDescription> profileIDListExpected = new ArrayList<>();
        profileIDListExpected.add(profileID);

        // Act
        List<ProfileDescription> profileIDListResult = newUser.getUserProfileList();

        // Assert
        assertEquals(profileIDListExpected, profileIDListResult);

    }

    @Test
    void isPasswordChangedTrue() throws NoSuchAlgorithmException {
        // Arrange
        String oldPassword = "123456Aa%";
        Email email = Email.create("ricardohorta@rangers.com");
        Password password = Password.createPassword(oldPassword);
        UserName userName = UserName.createUsername("Ricardo");
        Function function = Function.createFunction("Magician");
        ProfileDescription profileID = ProfileDescription
                .createProfileDescription("Visitor");

        User newUser = new User(email, password,
                userName, function, profileID);

        // Act
        String newPassword = "1234567Aa%";
        boolean result = newUser.updatePassword(oldPassword, newPassword);

        // Assert
        assertTrue(result);
    }

    @Test
    void isPasswordChangedNullInput() throws NoSuchAlgorithmException {
        // Arrange
        String oldPassword = "123456Aa%";
        Email email = Email.create("ricardohorta@rangers.com");
        Password password = Password.createPassword(oldPassword);
        UserName userName = UserName.createUsername("Ricardo");
        Function function = Function.createFunction("Magician");
        ProfileDescription profileID = ProfileDescription
                .createProfileDescription("Visitor");

        User newUser = new User(email, password,
                userName, function, profileID);

        // Act
        String newPassword = null;
        boolean result = newUser.updatePassword(oldPassword, newPassword);

        // Assert
        assertFalse(result);
    }

    @Test
    void isPasswordChangedInputOldPasswordNotSameInSystemExceptionThrown() throws NoSuchAlgorithmException {
        // Arrange
        String oldPassword = "123456Aa%";
        Email email = Email.create("ricardohorta@rangers.com");
        Password password = Password.createPassword(oldPassword);
        UserName userName = UserName.createUsername("Ricardo");
        Function function = Function.createFunction("Magician");
        ProfileDescription profileID = ProfileDescription
                .createProfileDescription("Visitor");

        User newUser = new User(email, password,
                userName, function, profileID);

        // Act
        String newPassword = "1234567Aa%";

        // Assert
        assertThrows(Exception.class, () ->
                newUser.updatePassword("asd", newPassword));
    }

    @Test
    void isPasswordChangedInputOldPasswordNotSameInSystem() throws NoSuchAlgorithmException {
        // Arrange
        String oldPassword = "123456Aa%";
        Email email = Email.create("ricardohorta@rangers.com");
        Password password = Password.createPassword(oldPassword);
        UserName userName = UserName.createUsername("Ricardo");
        Function function = Function.createFunction("Magician");
        ProfileDescription profileID = ProfileDescription
                .createProfileDescription("Visitor");

        User newUser = new User(email, password,
                userName, function, profileID);

        // Act
        String newPassword = "1234567Aa%";
        boolean result = newUser.updatePassword(newPassword, newPassword);

        // Assert
        assertFalse(result);
    }

    @Test
    void isPasswordChangedInputInvalidNewPasswordExceptionThrown() throws NoSuchAlgorithmException {
        // Arrange
        String oldPassword = "123456Aa%";
        Email email = Email.create("ricardohorta@rangers.com");
        Password password = Password.createPassword(oldPassword);
        UserName userName = UserName.createUsername("Ricardo");
        Function function = Function.createFunction("Magician");
        ProfileDescription profileID = ProfileDescription
                .createProfileDescription("Visitor");

        User newUser = new User(email, password,
                userName, function, profileID);

        // Act
        String newPassword = "";

        // Assert
        assertThrows(Exception.class, () ->
                newUser.updatePassword(oldPassword, newPassword));
    }


    @Test
    void compareEmailTrue() throws NoSuchAlgorithmException {
        // Arrange
        Email email = Email.create("ricardohorta@rangers.com");
        Password password = Password.createPassword("123456Aa%");
        UserName userName = UserName.createUsername("Ricardo");
        Function function = Function.createFunction("Magician");
        ProfileDescription profileID = ProfileDescription
                .createProfileDescription("Visitor");

        User newUser = new User(email, password,
                userName, function, profileID);


        // Act
        String emailToCompare = "RICARDOHORTA@RANGERS.COM";
        boolean result = newUser.compareEmail(emailToCompare);

        // Assert
        assertTrue(result);
    }

    @Test
    void compareEmailFalse() throws NoSuchAlgorithmException {
        // Arrange
        Email email = Email.create("ricardohorta@rangers.com");
        Password password = Password.createPassword("123456Aa%");
        UserName userName = UserName.createUsername("Ricardo");
        Function function = Function.createFunction("Magician");
        ProfileDescription profileID = ProfileDescription
                .createProfileDescription("Visitor");

        User newUser = new User(email, password,
                userName, function, profileID);


        // Act
        String emailToCompare = "andreHORTA@RumoaSevilha";
        boolean result = newUser.compareEmail(emailToCompare);

        // Assert
        assertFalse(result);
    }

    @Test
    void ShouldReturnFalseWhenEmailIsBlank() throws NoSuchAlgorithmException {
        // Arrange
        Email email = Email.create("ricardohorta@rangers.com");
        Password password = Password.createPassword("123456Aa%");
        UserName userName = UserName.createUsername("Ricardo");
        Function function = Function.createFunction("Magician");
        ProfileDescription profileID = ProfileDescription
                .createProfileDescription("Visitor");

        User newUser = new User(email, password,
                userName, function, profileID);


        // Act
        String emailToCompare = "";
        boolean result = newUser.compareEmail(emailToCompare);

        // Assert
        assertFalse(result);
    }

    @Test
    void activateAccountFalse() throws NoSuchAlgorithmException {
        // Arrange
        Email email = Email.create("ricardohorta@rangers.com");
        Password password = Password.createPassword("123456Aa%");
        UserName userName = UserName.createUsername("Ricardo");
        Function function = Function.createFunction("Magician");
        ProfileDescription profileID = ProfileDescription
                .createProfileDescription("Visitor");

        User newUser = new User(email, password,
                userName, function, profileID);

        // Act
        boolean result = newUser.activateAccount("");

        // Assert
        assertFalse(result);
    }

    @Test
    void activateAccountTrue() throws NoSuchAlgorithmException {
        // Arrange
        Email email = Email.create("ricardohorta@rangers.com");
        Password password = Password.createPassword("123456Aa%");
        UserName userName = UserName.createUsername("Ricardo");
        Function function = Function.createFunction("Magician");
        ProfileDescription profileID = ProfileDescription
                .createProfileDescription("Visitor");

        User newUser = new User(email, password,
                userName, function, profileID);

        // Act
        String activationCode = newUser.getActivation().getCode();
        boolean result = newUser.activateAccount(activationCode);

        // Assert
        assertTrue(result);
    }

    @Test
    void updateProfileTrue() throws NoSuchAlgorithmException {
        // Arrange
        Email email = Email.create("ricardohorta@rangers.com");
        Password password = Password.createPassword("123456Aa%");
        UserName userName = UserName.createUsername("Ricardo");
        Function function = Function.createFunction("Magician");
        ProfileDescription profileID = ProfileDescription
                .createProfileDescription("Visitor");

        User newUser = new User(email, password,
                userName, function, profileID);

        // Act
        ProfileDescription newProfileId = ProfileDescription
                .createProfileDescription("Admin");
        boolean result = newUser.updateProfile(profileID, newProfileId);

        // Assert
        assertTrue(result);
    }

    @Test
    void updateProfileFalse() throws NoSuchAlgorithmException {
        // Arrange
        Email email = Email.create("ricardohorta@rangers.com");
        Password password = Password.createPassword("123456Aa%");
        UserName userName = UserName.createUsername("Ricardo");
        Function function = Function.createFunction("Magician");
        ProfileDescription profileID = ProfileDescription
                .createProfileDescription("Visitor");

        User newUser = new User(email, password,
                userName, function, profileID);

        // Act
        ProfileDescription newProfileId = ProfileDescription
                .createProfileDescription("Admin");
        boolean result = newUser.updateProfile(newProfileId, newProfileId);

        // Assert
        assertFalse(result);
    }

    @Test
    void hasProfileTrue() throws NoSuchAlgorithmException {
        // Arrange
        Email email = Email.create("ricardohorta@rangers.com");
        Password password = Password.createPassword("123456Aa%");
        UserName userName = UserName.createUsername("Ricardo");
        Function function = Function.createFunction("Magician");
        ProfileDescription profileID = ProfileDescription
                .createProfileDescription("Visitor");

        User newUser = new User(email, password,
                userName, function, profileID);

        // Act
        boolean result = newUser.hasProfile(profileID);

        // Assert
        assertTrue(result);
    }

    @Test
    void hasProfileFalse() throws NoSuchAlgorithmException {
        // Arrange
        Email email = Email.create("ricardohorta@rangers.com");
        Password password = Password.createPassword("123456Aa%");
        UserName userName = UserName.createUsername("Ricardo");
        Function function = Function.createFunction("Magician");
        ProfileDescription profileID = ProfileDescription
                .createProfileDescription("Visitor");

        User newUser = new User(email, password,
                userName, function, profileID);

        // Act
        ProfileDescription newProfileId = ProfileDescription
                .createProfileDescription("Admin");
        boolean result = newUser.hasProfile(newProfileId);

        // Assert
        assertFalse(result);
    }

    @Test
    void updateUserInformation() throws NoSuchAlgorithmException {
        // Arrange
        Email email = Email.create("ricardohorta@rangers.com");
        Password password = Password.createPassword("123456Aa%");
        UserName userName = UserName.createUsername("OldName");
        Function function = Function.createFunction("OldFunction");
        ProfileDescription profileID = ProfileDescription
                .createProfileDescription("Visitor");

        User newUser = new User(email, password,
                userName, function, profileID);

        // Act
        UserName newUserName = UserName.createUsername("NewName");
        Function newFunction = Function.createFunction("NewFunction");


        newUser.updateUserInformation(newUserName, newFunction);

        // Assert
        assertEquals(newUserName, newUser.getUserName());
        assertEquals(newFunction, newUser.getFunction());
    }

    @Test
    void doesUserAlreadyHaveRequestedProfile() throws NoSuchAlgorithmException {
        // Arrange
        Email email = Email.create("ricardohorta@rangers.com");
        Password password = Password.createPassword("123456Aa%");
        UserName userName = UserName.createUsername("Ricardo");
        Function function = Function.createFunction("Magician");
        ProfileDescription profileID = ProfileDescription
                .createProfileDescription("Visitor");

        User newUser = new User(email, password,
                userName, function, profileID);

        // Act
        boolean result = newUser.doesUserAlreadyHaveRequestedProfile(profileID);

        // Assert
        assertTrue(result);
    }

    @Test
    void doesUserAlreadyHaveRequestedProfileFalse() throws NoSuchAlgorithmException {
        // Arrange
        Email email = Email.create("ricardohorta@rangers.com");
        Password password = Password.createPassword("123456Aa%");
        UserName userName = UserName.createUsername("Ricardo");
        Function function = Function.createFunction("Magician");
        ProfileDescription profileID = ProfileDescription
                .createProfileDescription("Visitor");

        User newUser = new User(email, password,
                userName, function, profileID);

        // Act
        ProfileDescription profileIDToSearch = ProfileDescription
                .createProfileDescription("Admin");
        boolean result = newUser.doesUserAlreadyHaveRequestedProfile(profileIDToSearch);

        // Assert
        assertFalse(result);
    }


    @Test
    void testEqualsCaseSameObject() throws NoSuchAlgorithmException {
        // Arrange
        Email email = Email.create("ricardohorta@rangers.com");
        Password password = Password.createPassword("123456Aa%");
        UserName userName = UserName.createUsername("Ricardo");
        Function function = Function.createFunction("Magician");
        ProfileDescription profileID = ProfileDescription
                .createProfileDescription("Visitor");

        User newUser = new User(email, password,
                userName, function, profileID);

        //Assert
        assertEquals(newUser, newUser);
    }

    @Test
    void testEqualsCaseNull() throws NoSuchAlgorithmException {
        //Arrange
        Email email = Email.create("ricardohorta@rangers.com");
        Password password = Password.createPassword("123456Aa%");
        UserName userName = UserName.createUsername("Ricardo");
        Function function = Function.createFunction("Magician");
        ProfileDescription profileID = ProfileDescription
                .createProfileDescription("Visitor");

        User newUser = new User(email, password,
                userName, function, profileID);

        //Assert
        assertNotEquals(null, newUser);
    }

    @Test
    void testEqualsCaseDifferentClasses() throws NoSuchAlgorithmException {

        //Arrange
        String emailString = "mcValter@isep.pt";
        Email email = Email.create("ricardohorta@rangers.com");
        Password password = Password.createPassword("123456Aa%");
        UserName userName = UserName.createUsername("Ricardo");
        Function function = Function.createFunction("Magician");
        ProfileDescription profileID = ProfileDescription
                .createProfileDescription("Visitor");

        User newUser = new User(email, password,
                userName, function, profileID);

        //Act

        //Assert
        assertNotEquals(newUser, emailString);
    }

    @Test
    void testEqualsCaseTrue() throws NoSuchAlgorithmException {

        //Arrange
        Email email = Email.create("andre@braga.com");
        Password password = Password.createPassword("123456Aa%");
        UserName userName = UserName.createUsername("Ricardo");
        Function function = Function.createFunction("Magician");
        ProfileDescription profileID = ProfileDescription
                .createProfileDescription("Visitor");

        User userOne = new User(email, password,
                userName, function, profileID);

        Email emailTwo = Email.create("andre@braga.com");
        Password passwordTwo = Password.createPassword("12345632Aa%");
        UserName userNameTwo = UserName.createUsername("Andre");
        Function functionTwo = Function.createFunction("Reserve");
        ProfileDescription profileIDTwo = ProfileDescription
                .createProfileDescription("Director");

        User userTwo = new User(emailTwo, passwordTwo,
                userNameTwo, functionTwo, profileIDTwo);

        //Assert
        assertEquals(userOne, userTwo);
    }

    @Test
    void testEqualsCaseFalse() throws NoSuchAlgorithmException {

        //Arrange
        Email email = Email.create("ricardohorta@rangers.com");
        Password password = Password.createPassword("123456Aa%");
        UserName userName = UserName.createUsername("Ricardo");
        Function function = Function.createFunction("Magician");
        ProfileDescription profileID = ProfileDescription
                .createProfileDescription("Visitor");

        User userOne = new User(email, password,
                userName, function, profileID);

        Email emailTwo = Email.create("andre@braga.com");
        Password passwordTwo = Password.createPassword("12345632Aa%");
        UserName userNameTwo = UserName.createUsername("Andre");
        Function functionTwo = Function.createFunction("Reserve");
        ProfileDescription profileIDTwo = ProfileDescription
                .createProfileDescription("Director");

        User userTwo = new User(emailTwo, passwordTwo,
                userNameTwo, functionTwo, profileIDTwo);

        //Assert
        assertNotEquals(userOne, userTwo);
    }


    @Test
    void testHashCodeCaseTrue() throws NoSuchAlgorithmException {
        //Arrange
        Email email = Email.create("andre@braga.com");
        Password password = Password.createPassword("123456Aa%");
        UserName userName = UserName.createUsername("Ricardo");
        Function function = Function.createFunction("Magician");
        ProfileDescription profileID = ProfileDescription
                .createProfileDescription("Visitor");

        User userOne = new User(email, password,
                userName, function, profileID);

        Email emailTwo = Email.create("andre@braga.com");
        Password passwordTwo = Password.createPassword("12345632Aa%");
        UserName userNameTwo = UserName.createUsername("Andre");
        Function functionTwo = Function.createFunction("Reserve");
        ProfileDescription profileIDTwo = ProfileDescription
                .createProfileDescription("Director");

        User userTwo = new User(emailTwo, passwordTwo,
                userNameTwo, functionTwo, profileIDTwo);

        //Arrange
        assertEquals(userOne.hashCode(), userTwo.hashCode());
    }

    @Test
    void testHashCodeCaseFalse() throws NoSuchAlgorithmException {
        //Arrange
        Email email = Email.create("ricardohorta@rangers.com");
        Password password = Password.createPassword("123456Aa%");
        UserName userName = UserName.createUsername("Ricardo");
        Function function = Function.createFunction("Magician");
        ProfileDescription profileID = ProfileDescription
                .createProfileDescription("Visitor");

        User userOne = new User(email, password,
                userName, function, profileID);

        Email emailTwo = Email.create("andre@braga.com");
        Password passwordTwo = Password.createPassword("12345632Aa%");
        UserName userNameTwo = UserName.createUsername("Andre");
        Function functionTwo = Function.createFunction("Reserve");
        ProfileDescription profileIDTwo = ProfileDescription
                .createProfileDescription("Director");

        User userTwo = new User(emailTwo, passwordTwo,
                userNameTwo, functionTwo, profileIDTwo);

        //Arrange
        assertNotEquals(userOne.hashCode(), userTwo.hashCode());
    }

    @Test
    void shouldCallTheActivationMethodToSetToInactiveOnce() throws NoSuchAlgorithmException {
        // Arrange
        Email email = Email.create("ricardohorta@rangers.com");
        Password password = Password.createPassword("123456Aa%");
        UserName userName = UserName.createUsername("OldName");
        Function function = Function.createFunction("OldFunction");
        Activation activationMock = mock(Activation.class);
        List<ProfileDescription> emptyProfileList = new ArrayList<>();
        User newUser = new User(email, emptyProfileList, activationMock, password,
                userName, function);

        newUser.setToInactive();

        verify(activationMock, times(1)).setToInactive();
    }

    @Test
    void shouldCallTheActivationMethodToSetToInactivationDateOnce() throws NoSuchAlgorithmException {
        // Arrange
        Email email = Email.create("ricardohorta@rangers.com");
        Password password = Password.createPassword("123456Aa%");
        UserName userName = UserName.createUsername("OldName");
        Function function = Function.createFunction("OldFunction");
        Activation activationMock = mock(Activation.class);
        List<ProfileDescription> emptyProfileList = new ArrayList<>();
        User newUser = new User(email, emptyProfileList, activationMock, password,
                userName, function);

        newUser.setToInactive();

        verify(activationMock, times(1)).setInactivationDate();
    }

    @Test
    void shouldCallTheActivationMethodToSetToActivateOnce() throws NoSuchAlgorithmException {
        // Arrange
        Email email = Email.create("ricardohorta@rangers.com");
        Password password = Password.createPassword("123456Aa%");
        UserName userName = UserName.createUsername("OldName");
        Function function = Function.createFunction("OldFunction");

        Activation activationMock = mock(Activation.class);
        List<ProfileDescription> emptyProfileList = new ArrayList<>();
        User newUser = new User(email, emptyProfileList, activationMock, password,
                userName, function);

        newUser.setToActive();

        verify(activationMock, times(1)).setToActive();
    }

    @Test
    void shouldCallTheActivationMethodToSetActivationDateOnce() throws NoSuchAlgorithmException {
        // Arrange
        Email email = Email.create("ricardohorta@rangers.com");
        Password password = Password.createPassword("123456Aa%");
        UserName userName = UserName.createUsername("OldName");
        Function function = Function.createFunction("OldFunction");

        Activation activationMock = mock(Activation.class);
        List<ProfileDescription> emptyProfileList = new ArrayList<>();
        User newUser = new User(email, emptyProfileList, activationMock, password,
                userName, function);

        newUser.setToActive();

        verify(activationMock, times(1)).setActivationDate();
    }

    @Test
    void shouldReturnTrueWhenActualAndPretendedProfilesAreValid() throws NoSuchAlgorithmException {
        Email email = Email.create("ricardohorta@rangers.com");
        Password password = Password.createPassword("123456Aa%");
        UserName userName = UserName.createUsername("Ricardo");
        Function function = Function.createFunction("Magician");
        ProfileDescription profileID = ProfileDescription
                .createProfileDescription("Visitor");

        User userOne = new User(email, password,
                userName, function, profileID);
        ProfileDescription pretendedProfile = ProfileDescription.createProfileDescription("Super");

        assertTrue(userOne.changeProfile(profileID, pretendedProfile));
    }

    @Test
    void shouldReturnFalseWhenActualAndPretendedProfilesAreInvalid() throws NoSuchAlgorithmException {
        Email email = Email.create("ricardohorta@rangers.com");
        Password password = Password.createPassword("123456Aa%");
        UserName userName = UserName.createUsername("Ricardo");
        Function function = Function.createFunction("Magician");
        ProfileDescription profileID = ProfileDescription
                .createProfileDescription("Visitor");

        User userOne = new User(email, password,
                userName, function, profileID);

        assertFalse(userOne.changeProfile(null, null));
    }

    @Test
    void shouldReturnFalseWhenActualProfileIsInvalidAndPretendedProfileIsValid() throws NoSuchAlgorithmException {
        Email email = Email.create("ricardohorta@rangers.com");
        Password password = Password.createPassword("123456Aa%");
        UserName userName = UserName.createUsername("Ricardo");
        Function function = Function.createFunction("Magician");
        ProfileDescription profileID = ProfileDescription
                .createProfileDescription("Visitor");

        User userOne = new User(email, password,
                userName, function, profileID);
        ProfileDescription pretendedProfile = ProfileDescription.createProfileDescription("Super");

        assertFalse(userOne.changeProfile(null, pretendedProfile));
    }

    @Test
    void shouldReturnFalseWhenActualProfileIsValidAndPretendedProfileIsInvalid() throws NoSuchAlgorithmException {
        Email email = Email.create("ricardohorta@rangers.com");
        Password password = Password.createPassword("123456Aa%");
        UserName userName = UserName.createUsername("Ricardo");
        Function function = Function.createFunction("Magician");
        ProfileDescription profileID = ProfileDescription
                .createProfileDescription("Visitor");

        User userOne = new User(email, password,
                userName, function, profileID);
        ProfileDescription pretendedProfile = ProfileDescription.createProfileDescription("Super");

        assertFalse(userOne.changeProfile(null, pretendedProfile));
    }

    @Test
    void shouldReturnTrueWhenUserTriesToAddANewProfile() throws NoSuchAlgorithmException {
        Email email = Email.create("ricardohorta@rangers.com");
        Password password = Password.createPassword("123456Aa%");
        UserName userName = UserName.createUsername("Ricardo");
        Function function = Function.createFunction("Magician");
        ProfileDescription profileID = ProfileDescription
                .createProfileDescription("Visitor");

        User userOne = new User(email, password,
                userName, function, profileID);
        ProfileDescription pretendedProfile = ProfileDescription.createProfileDescription("Super");

        assertTrue(userOne.addProfile(pretendedProfile));
    }

    @Test
    void shouldReturnFalseWhenUserTriesToAddAnExistingProfile() throws NoSuchAlgorithmException {
        Email email = Email.create("ricardohorta@rangers.com");
        Password password = Password.createPassword("123456Aa%");
        UserName userName = UserName.createUsername("Ricardo");
        Function function = Function.createFunction("Magician");
        ProfileDescription profileID = ProfileDescription
                .createProfileDescription("Visitor");

        User userOne = new User(email, password,
                userName, function, profileID);
        ProfileDescription pretendedProfile = ProfileDescription.createProfileDescription("Visitor");

        assertFalse(userOne.addProfile(pretendedProfile));
    }

    @Test
    void shouldReturnTrueWhenUserTriesToRemoveAnExistingProfile() throws NoSuchAlgorithmException {
        Email email = Email.create("ricardohorta@rangers.com");
        Password password = Password.createPassword("123456Aa%");
        UserName userName = UserName.createUsername("Ricardo");
        Function function = Function.createFunction("Magician");
        ProfileDescription profileID = ProfileDescription
                .createProfileDescription("Visitor");

        User userOne = new User(email, password,
                userName, function, profileID);
        ProfileDescription pretendedProfile = ProfileDescription.createProfileDescription("Visitor");

        assertTrue(userOne.removeProfile(pretendedProfile));
    }

    @Test
    void shouldReturnFalseWhenUserTriesToRemoveAnExistingProfile() throws NoSuchAlgorithmException {
        Email email = Email.create("ricardohorta@rangers.com");
        Password password = Password.createPassword("123456Aa%");
        UserName userName = UserName.createUsername("Ricardo");
        Function function = Function.createFunction("Magician");
        ProfileDescription profileID = ProfileDescription
                .createProfileDescription("Visitor");

        User userOne = new User(email, password,
                userName, function, profileID);
        ProfileDescription pretendedProfile = ProfileDescription.createProfileDescription("Super");

        assertFalse(userOne.removeProfile(pretendedProfile));
    }
}
