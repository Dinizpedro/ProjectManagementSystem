package switchfive.project.interfaceAdapters.implRepositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import switchfive.project.assemblers.assemblersJPA.iAssemblersJPA.IUserAssemblerJPA;
import switchfive.project.dataModel.dataJPA.UserJPA;
import switchfive.project.infrastructure.persistence.repositories.iRepositoriesJPA.IUserRepositoryJPA;
import switchfive.project.domain.aggregates.user.User;
import switchfive.project.domain.shared.valueObjects.Email;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImplUserRepositoryTest {

    @Spy
    IUserRepositoryJPA userStoreJPA;

    @Spy
    IUserAssemblerJPA userAssemblerJPA;

    @InjectMocks
    ImplUserRepository userRepository;

    @Test
    void updateShouldTryToAssembleTheUserIntoUserJPA() throws NoSuchAlgorithmException {
        User userMock = mock(User.class);
        UserJPA userJPAMock = mock(UserJPA.class);
        doReturn(userMock).when(userAssemblerJPA).toDomain(any());
        when(userStoreJPA.save(any())).thenReturn(userJPAMock);

        userRepository.update(userMock);

        verify(userAssemblerJPA, times(1)).toJPA(userMock);
    }

    @Test
    void updateShouldTryToSaveTheUser() throws NoSuchAlgorithmException {
        User userMock = mock(User.class);
        UserJPA userJPAMock = mock(UserJPA.class);

        doReturn(userJPAMock).when(userAssemblerJPA).toJPA(any());
        doReturn(userMock).when(userAssemblerJPA).toDomain(any());

        userRepository.update(userMock);

        verify(userStoreJPA, times(1)).save(any(UserJPA.class));
    }

    @Test
    void updateShouldTryToAssembleTheUserJPAIntoUser() throws NoSuchAlgorithmException {
        User userMock = mock(User.class);
        UserJPA userJPAMock = mock(UserJPA.class);
        doReturn(userJPAMock).when(userAssemblerJPA).toJPA(any());
        when(userStoreJPA.save(any())).thenReturn(userJPAMock);

        userRepository.update(userMock);

        verify(userAssemblerJPA, times(1)).toDomain(userJPAMock);
    }

    @Test
    void shouldReturnAnOptionalOfTheUserUpdated() throws NoSuchAlgorithmException {
        User userMock = mock(User.class);
        UserJPA userJPAMock = mock(UserJPA.class);
        doReturn(userJPAMock).when(userAssemblerJPA).toJPA(any());
        when(userStoreJPA.save(any())).thenReturn(userJPAMock);
        doReturn(userMock).when(userAssemblerJPA).toDomain(any());
        Optional<User> expected = Optional.of(userMock);

        Optional<User> actual = userRepository.update(userMock);

        assertEquals(expected, actual);
    }

    @Test
    void findAllUsers_NoUsersFound() throws NoSuchAlgorithmException {
        // Arrange
        List<UserJPA> users = new ArrayList<>();
        when(userStoreJPA.findAll()).thenReturn(users);

        // Act

        // Assert
        assertEquals(Optional.empty(),userRepository.findAllUsers());

    }

    @Test
    void userExistsCaseTrue(){

        //Arrange
        String email = "valterdesousa@gmail.com";
        when(userStoreJPA.existsByEmail(email)).thenReturn(true);

        //Act

        //Assert
        assertTrue(userRepository.userExists(email));

    }

    @Test
    void userExistsCaseFalse(){

        //Arrange
        String email = "valterdesousa@gmail.com";
        when(userStoreJPA.existsByEmail(email)).thenReturn(false);

        //Act

        //Assert
        assertFalse(userRepository.userExists(email));

    }

    @Test
    void checksIfUserExistsUsingEmailVOCaseTrue() {

        //Arrange
        String email = "valter@gmail.com";
        Email emailVO = Email.create(email);
        when(userStoreJPA.existsByEmail(email)).thenReturn(true);

        //Act

        //Assert
        assertTrue(userRepository.userExists(emailVO));
    }

    @Test
    void checksIfUserExistsUsingEmailVOCaseFalse() {

        //Arrange
        String email = "valter@gmail.com";
        Email emailVO = Email.create(email);
        when(userStoreJPA.existsByEmail(email)).thenReturn(false);

        //Act

        //Assert
        assertFalse(userRepository.userExists(emailVO));
    }
}
