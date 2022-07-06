package switchfive.project.applicationServices.appServices.implAppServices;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import switchfive.project.dataModel.dataJPA.UserJPA;
import switchfive.project.applicationServices.iRepositories.IProfileRepository;
import switchfive.project.applicationServices.iRepositories.IProjectRepository;
import switchfive.project.applicationServices.iRepositories.IResourceRepository;
import switchfive.project.applicationServices.iRepositories.IUserRepository;
import switchfive.project.mappers.mappersApp.iMappers.IResourceMapper;
import switchfive.project.mappers.mappersApp.implMappers.ImplUserMapper;
import switchfive.project.domain.aggregates.project.Project;
import switchfive.project.domain.aggregates.resource.Resource;
import switchfive.project.domain.aggregates.user.User;
import switchfive.project.domain.factories.iFactories.IUserFactory;
import switchfive.project.domain.shared.valueObjects.*;
import switchfive.project.dtos.*;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImplAppUserServiceTest {

    private final UserDTO userDTO = new UserDTO();

    @Mock
    SearchUserDTO searchUserDTO;

    @Mock
    IResourceMapper resourceMapper;

    @Mock
    IProfileRepository profileStore;

    @Mock
    IUserRepository userStoreMock;

    @Mock
    IUserFactory userFactory;

    @Mock
    IResourceRepository resourceStore;

    @Mock
    IProjectRepository projectStore;

    @InjectMocks
    ImplAppUserService userService;

    @Spy
    User userMock;

    ImplAppUserServiceTest() {
    }

    @Test
    void userStoreShouldTryToFindAnUser() throws NoSuchAlgorithmException {
        userService.updateUser(userDTO);

        verify(userStoreMock, times(1)).findUserByEmail(userDTO.email);
    }

    @Test
    void userStoreShouldTryToUpdateTheUserIfFound() throws NoSuchAlgorithmException {
        userDTO.userName = "newName";
        userDTO.userFunction = "newFunction";
        doReturn(Optional.ofNullable(userMock)).when(userStoreMock).findUserByEmail(userDTO.email);

        userService.updateUser(userDTO);

        verify(userStoreMock, times(1)).update(userMock);
    }

    @Test
    void selectedUserShouldUpdateInformation() throws NoSuchAlgorithmException {
        userDTO.email = "isep@ipp.pt";
        String newName = "newName";
        userDTO.userName = newName;
        UserName userName = UserName.createUsername(newName);
        String newFunction = "newFunction";
        userDTO.userFunction = newFunction;
        Function function = Function.createFunction(newFunction);
        doReturn(Optional.ofNullable(userMock)).when(userStoreMock).findUserByEmail(userDTO.email);
        doReturn(Optional.ofNullable(userMock)).when(userStoreMock).update(userMock);
        MockedStatic<ImplUserMapper> userMapperStatic = mockStatic(ImplUserMapper.class);
        when(ImplUserMapper.toDTO(userMock)).thenReturn(userDTO);

        userService.updateUser(userDTO);
        userMapperStatic.close();

        verify(userMock, times(1)).updateUserInformation(userName, function);
    }

    @Test
    void shouldReturnTheArgumentDTOWhenTheUpdateFailed() throws NoSuchAlgorithmException {
        userDTO.email = "isep@ipp.pt";
        userDTO.userName = "newName";
        userDTO.userFunction = "newFunction";
        doReturn(Optional.empty()).when(userStoreMock).findUserByEmail(userDTO.email);

        Optional<UserDTO> actual = userService.updateUser(userDTO);

        assertEquals(Optional.empty(), actual);
    }

    @Test
    void shouldReturnTheUpdatedUserDTOWhenTheUpdateWasSuccessful() throws NoSuchAlgorithmException {
        userDTO.email = "isep@ipp.pt";
        userDTO.userName = "newName";
        userDTO.userFunction = "newFunction";
        doReturn(Optional.of(userMock)).when(userStoreMock).findUserByEmail(userDTO.email);
        doReturn(Optional.of(userMock)).when(userStoreMock).update(userMock);
        MockedStatic<ImplUserMapper> userMapperStatic = mockStatic(ImplUserMapper.class);
        when(ImplUserMapper.toDTO(userMock)).thenReturn(userDTO);

        Optional<UserDTO> actual = userService.updateUser(userDTO);
        userMapperStatic.close();

        assertEquals(Optional.of(userDTO), actual);
    }


    @Test
    void validateAndAddProfile_Failling_WhenUserDoesNotExist() throws NoSuchAlgorithmException {

        //Arrange
        when(this.userStoreMock.findUserByEmail("123@isep.pt")).thenReturn(Optional.empty());

        //Act
        assertThrows(IllegalArgumentException.class, () -> this.userService.validateAndAddProfile("123@isep.pt", "user"));

    }

    @Test
    void validateAndAddProfile_Failling_WhenProfileDoesNotExist() throws NoSuchAlgorithmException {

        //Arrange
        User user = mock(User.class);

        when(this.userStoreMock.findUserByEmail("123@isep.pt")).thenReturn(Optional.of(user));

        when(this.profileStore.profileExists("Visitor")).thenReturn(false);

        //Assert
        assertThrows(IllegalArgumentException.class, () -> this.userService.validateAndAddProfile("123@isep.pt", "Visitor"));

    }

    @Test
    void validateAndAddProfile_Failling_WhenUserDoesNotHaveProfile() throws NoSuchAlgorithmException {

        //Arrange
        User user = mock(User.class);

        ProfileDescription profile = ProfileDescription.createProfileDescription("Admin");

        when(this.userStoreMock.findUserByEmail("123@isep.pt")).thenReturn(Optional.of(user));

        when(this.profileStore.profileExists("Admin")).thenReturn(true);

        when(user.hasProfile(profile)).thenReturn(true);

        //Assert
        assertThrows(IllegalArgumentException.class, () -> this.userService.validateAndAddProfile("123@isep.pt", "Admin"));
    }

    @Test
    void validateAndAddProfile_Success() throws NoSuchAlgorithmException {

        //Arrange
        User user = mock(User.class);
        SearchUserDTO dto = mock(SearchUserDTO.class);

        ProfileDescription profile = ProfileDescription.createProfileDescription("Admin");

        when(this.userStoreMock.findUserByEmail("123@isep.pt")).thenReturn(Optional.of(user));

        when(this.profileStore.profileExists("Admin")).thenReturn(true);

        when(user.hasProfile(profile)).thenReturn(false);

        MockedStatic<ImplUserMapper> userMapperStatic = mockStatic(ImplUserMapper.class);

        when(ImplUserMapper.toSearchUserDTO(user)).thenReturn(dto);

        //Assert
        assertEquals(Optional.of(dto), this.userService.validateAndAddProfile("123@isep.pt", "Admin"));
        userMapperStatic.close();

    }

    @Test
    void validateAndRemoveProfile_Failling_WhenUserDoesNotExist() throws NoSuchAlgorithmException {

        //Arrange
        when(this.userStoreMock.findUserByEmail("123@isep.pt")).thenReturn(Optional.empty());

        //Assert
        assertThrows(IllegalArgumentException.class, () -> this.userService.validateAndRemoveProfile("123@isep.pt", "user"));
    }

    @Test
    void validateAndRemoveProfile_Failling_WhenUserDoesNotHaveProfile() throws NoSuchAlgorithmException {

        //Arrange
        User user = mock(User.class);

        ProfileDescription profile = ProfileDescription.createProfileDescription("Admin");

        when(this.userStoreMock.findUserByEmail("123@isep.pt")).thenReturn(Optional.of(user));

        when(user.hasProfile(profile)).thenReturn(false);

        //Assert
        assertThrows(IllegalArgumentException.class, () -> this.userService.validateAndRemoveProfile("123@isep.pt", "Admin"));
    }

    @Test
    void validateAndRemoveProfile_Success() throws NoSuchAlgorithmException {

        //Arrange
        User user = mock(User.class);
        SearchUserDTO dto = mock(SearchUserDTO.class);

        ProfileDescription profile = ProfileDescription.createProfileDescription("Admin");

        when(this.userStoreMock.findUserByEmail("123@isep.pt")).thenReturn(Optional.of(user));

        when(user.hasProfile(profile)).thenReturn(true);

        MockedStatic<ImplUserMapper> userMapperStatic = mockStatic(ImplUserMapper.class);

        when(ImplUserMapper.toSearchUserDTO(user)).thenReturn(dto);

        //Assert
        assertEquals(Optional.of(dto), this.userService.validateAndRemoveProfile("123@isep.pt", "Admin"));
        userMapperStatic.close();

    }

    @Test
    void getUsersByEmail_OneUserFound() throws NoSuchAlgorithmException {
        // Arrange
        List<SearchUserDTO> dtoList = new ArrayList<>();
        dtoList.add(searchUserDTO);

        List<User> usersList = new ArrayList<>();
        usersList.add(userMock);

        when(this.userStoreMock.findUsersByEmail("123@isep.pt")).thenReturn(usersList);

        MockedStatic<ImplUserMapper> userMapperStatic = mockStatic(ImplUserMapper.class);
        when(ImplUserMapper.toSearchDTOList(usersList)).thenReturn(dtoList);

        // Assert
        assertEquals(dtoList, this.userService.getUsersByEmail("123@isep.pt"));
        userMapperStatic.close();
    }

    @Test
    void getUsersByEmail_NoUserFound() throws NoSuchAlgorithmException {
        // Arrange
        List<SearchUserDTO> dtoList = new ArrayList<>();
        List<User> usersList = new ArrayList<>();

        when(this.userStoreMock.findUsersByEmail("123@isep.pt")).thenReturn(usersList);

        MockedStatic<ImplUserMapper> userMapperStatic = mockStatic(ImplUserMapper.class);
        when(ImplUserMapper.toSearchDTOList(usersList)).thenReturn(dtoList);

        // Assert
        assertEquals(dtoList, this.userService.getUsersByEmail("123@isep.pt"));
        userMapperStatic.close();
    }

    @Test
    void getUsersByProfile_OneUserFound() throws NoSuchAlgorithmException {
        // Arrange
        List<SearchUserDTO> dtoList = new ArrayList<>();
        dtoList.add(searchUserDTO);

        List<User> usersList = new ArrayList<>();
        usersList.add(userMock);

        when(this.userStoreMock.findUsersByProfile("admin")).thenReturn(usersList);

        MockedStatic<ImplUserMapper> userMapperStatic = mockStatic(ImplUserMapper.class);
        when(ImplUserMapper.toSearchDTOList(this.userStoreMock.findUsersByProfile("admin"))).thenReturn(dtoList);

        // Assert
        assertEquals(dtoList, this.userService.getUsersByProfile("admin"));
        userMapperStatic.close();
    }

    @Test
    void getUsersByProfile_NoUserFound() throws NoSuchAlgorithmException {
        // Arrange
        List<SearchUserDTO> dtoList = new ArrayList<>();
        List<User> usersList = new ArrayList<>();

        when(this.userStoreMock.findUsersByProfile("admin")).thenReturn(usersList);

        MockedStatic<ImplUserMapper> userMapperStatic = mockStatic(ImplUserMapper.class);
        when(ImplUserMapper.toSearchDTOList(this.userStoreMock.findUsersByProfile("admin"))).thenReturn(dtoList);

        // Assert
        assertEquals(dtoList, this.userService.getUsersByProfile("admin"));
        userMapperStatic.close();
    }

    @Test
    void getUserByEmail() throws NoSuchAlgorithmException {
        // Arrange
        MockedStatic<ImplUserMapper> userMapperStatic = mockStatic(ImplUserMapper.class);
        when(this.userStoreMock.findUserByEmail("123@isep.pt")).thenReturn(Optional.of(userMock));

        when(ImplUserMapper.toSearchUserDTO(userMock)).thenReturn(searchUserDTO);

        // Assert
        assertEquals(Optional.of(searchUserDTO), this.userService.getUserByEmail("123@isep.pt"));
        userMapperStatic.close();
    }

    @Test
    void getUserStore() {

        assertEquals(userService.getUserStore(), userStoreMock);
    }

    @Test
    void userStoreShouldTryToFindAnUserToActivateAccount() throws NoSuchAlgorithmException {
        userService.activateAccount(userDTO);

        verify(userStoreMock, times(1)).findUserByEmail(userDTO.email);
    }

    @Test
    void userMustHaveAccountActivated2() throws NoSuchAlgorithmException {
        doReturn(Optional.of(userMock)).when(userStoreMock).findUserByEmail(userDTO.email);
        doReturn(Optional.of(userMock)).when(userStoreMock).update(any());

        doReturn(true).when(userMock).activateAccount(any());
        MockedStatic<ImplUserMapper> userMapperStatic = mockStatic(ImplUserMapper.class);
        when(ImplUserMapper.toDTO(any())).thenReturn(userDTO);

        userService.activateAccount(userDTO);
        userMapperStatic.close();

        verify(userStoreMock, times(1)).update(userMock);
    }

    @Test
    void shouldReturnAnEmptyOptionalWhileActivatingAccount() throws NoSuchAlgorithmException {

        Optional<UserDTO> expected = Optional.of(userDTO);
        Optional<UserDTO> result = userService.activateAccount(userDTO);

        assertEquals(expected, result);
    }

    @Test
    void changePasswordSuccessfuly() throws NoSuchAlgorithmException {
        User userMock = mock(User.class);

        doReturn(Optional.of(userMock)).when(userStoreMock).findUserByEmail(userDTO.email);
        doReturn(Optional.of(userMock)).when(userStoreMock).update(any());


        doReturn(true).when(userMock).updatePassword(userDTO.password, "TâniaSALsicha2@");
        MockedStatic<ImplUserMapper> userMapperStatic = mockStatic(ImplUserMapper.class);
        when(ImplUserMapper.toDTO(any())).thenReturn(userDTO);

        ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO(userDTO.password, "TâniaSALsicha2@", userDTO.email);

        Optional<UserDTO> userDTOOptional = userService.changePassword(changePasswordDTO);
        userMapperStatic.close();

        verify(userStoreMock, times(1)).update(userMock);

    }

    @Test
    void changePasswordFailureWithAnException() throws NoSuchAlgorithmException {
        ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO(userDTO.password, "", userDTO.email);
        doReturn(false).when(userMock).updatePassword(any(), any());
        doReturn(Optional.of(userMock)).when(userStoreMock).findUserByEmail(userDTO.email);

        assertThrows(UnsupportedOperationException.class, () -> userService.changePassword(changePasswordDTO));


    }

    @Test
    void findById() throws NoSuchAlgorithmException {

        Optional<User> optionalUser = Optional.of(userMock);
        String email = any();
        when(userService.findById(email)).thenReturn(optionalUser);

        Optional<User> actualUser = userService.findById(email);

        assertEquals(optionalUser, actualUser);

    }

    @Test
    void createAndSaveUserSuccessfully() throws NoSuchAlgorithmException {

        Email email = mock(Email.class);
        Password password = mock(Password.class);
        UserName userName = mock(UserName.class);
        Function function = mock(Function.class);
        User newUser = mock(User.class);
        UserJPA userJPA = mock(UserJPA.class);
        UserDTO userDTO = mock(UserDTO.class);
        MockedStatic mockedStaticUserAssembler = mockStatic(ImplUserMapper.class);
        MockedStatic mockedStaticEmail = mockStatic(Email.class);
        MockedStatic mockedStaticPassword = mockStatic(Password.class);
        MockedStatic mockedStaticUsername = mockStatic(UserName.class);
        MockedStatic mockedStaticFunction = mockStatic(Function.class);

        when(userStoreMock.findUserByEmail(any())).thenReturn(Optional.empty());
        when(Email.create(any())).thenReturn(email);
        when(Password.createPassword(any())).thenReturn(password);
        when(UserName.createUsername(any())).thenReturn(userName);
        when(Function.createFunction(any())).thenReturn(function);
        when(userFactory.createUser(any(), any(), any(), any())).thenReturn(newUser);
        when(userStoreMock.save(newUser)).thenReturn(newUser);
        when(ImplUserMapper.toDTO(newUser)).thenReturn(userDTO);

        UserDTO userDTOExpected = userDTO;
        UserDTO userDTOActual = userService.createAndSaveUser(userDTO);

        assertEquals(userDTOExpected, userDTOActual);

        mockedStaticEmail.close();
        mockedStaticPassword.close();
        mockedStaticUsername.close();
        mockedStaticFunction.close();
        mockedStaticUserAssembler.close();
    }

    @Test
    void shouldReturnAnIllegalArgumentExceptionWhenTheEmailIsTaken() throws NoSuchAlgorithmException {

        //Arrange
        User user = mock(User.class);
        UserDTO userDTO = mock(UserDTO.class);

        //Act
        when(userStoreMock.findUserByEmail(any())).thenReturn(Optional.of(user));

        //Assert
        assertThrows(IllegalArgumentException.class, () -> userService.createAndSaveUser(userDTO));
    }

    @Test
    void getUsersByEmail_InvalidInput() throws NoSuchAlgorithmException {

        //Arrange
        List<SearchUserDTO> resultList = new ArrayList<>();

        //Act

        //Assert
        assertEquals(resultList, userService.getUsersByEmail(""));
    }

    @Test
    void setUserToActive_Success() throws NoSuchAlgorithmException {

        //Arrange
        User user = new User(null, null, null, null);
        when(userStoreMock.findUserByEmail(anyString())).thenReturn(Optional.of(user));


        //Act

        //Assert
        Assertions.assertTrue(userService.setUserToActive(anyString()));
    }

    @Test
    void setUserToActive_Failling() throws NoSuchAlgorithmException {

        //Arrange
        User user = new User(null, null, null, null);
        user.setToActive();
        when(userStoreMock.findUserByEmail(anyString())).thenReturn(Optional.of(user));


        //Act

        //Assert
        Assertions.assertFalse(userService.setUserToActive(anyString()));
    }

    @Test
    void setUserToInactive_Success() throws NoSuchAlgorithmException {

        //Arrange
        User user = new User(null, null, null, null);
        user.setToActive();
        when(userStoreMock.findUserByEmail(anyString())).thenReturn(Optional.of(user));


        //Act

        //Assert
        Assertions.assertTrue(userService.setUserToInactive(anyString()));
    }

    @Test
    void setUserToInactive_Failling() throws NoSuchAlgorithmException {

        //Arrange
        User user = new User(null, null, null, null);
        when(userStoreMock.findUserByEmail(anyString())).thenReturn(Optional.of(user));


        //Act

        //Assert
        Assertions.assertFalse(userService.setUserToInactive(anyString()));
    }

    @Test
    void activateAllUsers_whenNoUsers() throws NoSuchAlgorithmException {

        //Arrange
        when(userStoreMock.findAllUsers()).thenReturn(Optional.empty());

        //Act

        //Assert
        assertEquals(Optional.empty(), userService.activateAll());
    }

    @Test
    void getAllUserStatus() throws NoSuchAlgorithmException {

        //Arrange
        List<User> users = new ArrayList<>();
        List<UserStatusDTO> userStatusDTOList = new ArrayList<>();
        when(userStoreMock.findAllUsers()).thenReturn(Optional.of(users));

        MockedStatic<ImplUserMapper> userMapperStatic = mockStatic(ImplUserMapper.class);
        when(ImplUserMapper.toDTOListWithStatus(any())).thenReturn(userStatusDTOList);

        //Act

        //Assert
        assertEquals(userStatusDTOList, userService.getAllUserStatus());
        userMapperStatic.close();
    }

    @Test
    void getAllUserStatus_Empty() throws NoSuchAlgorithmException, ParseException {

        //Arrange
        List<UserDTO> DTOList = new ArrayList<>();
        List<User> users = new ArrayList<>();

        when(userStoreMock.findAllUsers()).thenReturn(Optional.of(users));


        //Assert
        assertEquals(DTOList, userService.getAllUsers());

    }

    @Test
    void getAllocatedProjectsSuccessfully() throws ParseException, NoSuchAlgorithmException {

        //Arrange

        String email = "email@email.com";
        Email emailVO = Email.create(email);
        String projectCodeString = "Isep2";
        String projectName = "Dell";
        String role = "dev";

        when(userStoreMock.userExists(emailVO)).thenReturn(true);
        Resource resource = mock(Resource.class);
        List<Resource> resources = new ArrayList<>();
        resources.add(resource);
        when(resourceStore.getResourcesByEmail(emailVO)).thenReturn(resources);
        when(resource.getProjectCode()).thenReturn(projectCodeString);

        ProjectCode projectCode = ProjectCode.create(projectCodeString);

        Project project = mock(Project.class);
        Optional<Project> optionalProject = Optional.of(project);
        when(projectStore.findByCode(projectCode)).thenReturn(optionalProject);
        when(Optional.of(project).get().getName()).thenReturn(projectName);

        AllocatedProjectDTO allocatedProjectDTO = new AllocatedProjectDTO();
        allocatedProjectDTO.setProjectCode(projectCodeString);
        allocatedProjectDTO.setProjectName(projectName);
        allocatedProjectDTO.setRole(role);
        when(resourceMapper.toAllocatedProjectDTO(resource,projectName)).thenReturn(allocatedProjectDTO);

        List<AllocatedProjectDTO> allocatedProjectDTOS = new ArrayList<>();
        allocatedProjectDTOS.add(allocatedProjectDTO);

        //Act
        List<AllocatedProjectDTO> expected = allocatedProjectDTOS;
        List<AllocatedProjectDTO> actual = userService.getAllocatedProjects(email);

        //Assert
        assertEquals(expected, actual);

    }
    @Test
    void shouldReturnEmptyAllocatedProjectDTOListWhenUserDoesNotExistInDB() throws ParseException, NoSuchAlgorithmException {

        //Arrange

        String email = "doesNotExistInDB@gmail.com";
        Email emailVO = Email.create(email);
        when(userStoreMock.userExists(emailVO)).thenReturn(false);
        List<AllocatedProjectDTO> allocatedProjectDTOS = new ArrayList<>();

        //Act
        List<AllocatedProjectDTO> expected = allocatedProjectDTOS;
        List<AllocatedProjectDTO> actual = userService.getAllocatedProjects(email);

        //Assert
        assertEquals(expected, actual);

    }

    @Test
    void shouldReturnEmptyAllocatedProjectDTOListWhenUserIsNotAllocatedToAnyProject() throws ParseException, NoSuchAlgorithmException {

        //Arrange

        String email = "notAllocated@gmail.com";
        Email emailVO = Email.create(email);
        when(userStoreMock.userExists(emailVO)).thenReturn(true);
        List<AllocatedProjectDTO> allocatedProjectDTOS = new ArrayList<>();
        List<Resource> resourcesList = new ArrayList<>();
        when(resourceStore.getResourcesByEmail(emailVO)).thenReturn(resourcesList);

        //Act
        List<AllocatedProjectDTO> expected = allocatedProjectDTOS;
        List<AllocatedProjectDTO> actual = userService.getAllocatedProjects(email);

        //Assert
        assertEquals(expected, actual);

    }

    @Test
    void shouldThrowExceptionWhenEmailIsInvalid() throws ParseException, NoSuchAlgorithmException {

        //Arrange

        String email = "invalidemail";

        //Act

        //Assert
        assertThrows(IllegalArgumentException.class, () -> userService.getAllocatedProjects(email));

    }
    //@Test
   /* void changePasswordReturnEmpty () throws NoSuchAlgorithmException {

        User userMock = mock(User.class);


        doReturn(Optional.of(userMock)).when(userStoreMock).findUserByEmail(null);
        doReturn(Optional.of(userMock)).when(userStoreMock).update(any());


        doReturn(true).when(userMock).updatePassword(userDTO.password, "TâniaSALsicha2@");
        MockedStatic<ImplUserMapper> userMapperStatic = mockStatic(ImplUserMapper.class);
        when(ImplUserMapper.toDTO(any())).thenReturn(userDTO);

        ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO(userDTO.password, "TâniaSALsicha2@", userDTO.email);

        Optional<UserDTO> userDTOOptional = userService.changePassword(changePasswordDTO);
        userMapperStatic.close();

        verify(userStoreMock, times(1)).update(userMock);

    }*/
}


    /*@Test
    void verifiesIfMethodSaveIsBeingInvokedOnce() throws NoSuchAlgorithmException {
        UserDTO userDTO = new UserDTO();
        userDTO.email = "abc@email.com";
        userDTO.password = "123546Aa@";
        userDTO.userName = "validName";
        userDTO.function = "Tester";

        when(userFactory.createUser(any(), any(), any(), any(), any())).thenReturn(userMock);

        userService.createAndSaveUser(userDTO);

        verify(userStoreMock, times(1)).save(userMock);
    }*/



    /*@Test
    void userDTOIsEqualToUserMock() throws NoSuchAlgorithmException {
        when(userStoreMock.findByEmail(any()))
                .thenReturn(Optional.empty());
        UserDTO userDTO = new UserDTO();
        userDTO.email = "abc@email.com";
        userDTO.password = "123546Aa@";
        userDTO.userName = "validName";
        userDTO.function = "Tester";
        when(userFactory.createUser(any(), any(), any(), any(), any())).thenReturn(userMock);
        when(userStoreMock.save(userMock)).thenReturn(userMock);

        User actual = userService.createAndSaveUser(userDTO);

        assertEquals(userMock, actual);
    }*/


    /*@Test
    void shouldBeThrownAnExceptionWhenEmailIsAlreadyInExistence() {
        when(userStoreMock.findByEmail(any()))
                .thenReturn(Optional.of(userMock));
        UserDTO userDTO = new UserDTO();
        userDTO.email = "abc@email.com";
        userDTO.password = "123546Aa@";
        userDTO.userName = "validName";
        userDTO.function = "Tester";

        assertThrows(IllegalArgumentException.class, () -> userService.createAndSaveUser(userDTO));
    }*/


    /*@Test
    void getUserByEmail_Succesfully() {
        // Arrange
        List<SearchUserDTO> expected = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        when(userStoreMock.findUsersByEmail("123@gmail.com")).thenReturn(userList);

        MockedStatic<ImplUserMapper> userMapperStatic = mockStatic(ImplUserMapper.class);
        when(ImplUserMapper.toSearchDTOList(userList)).thenReturn(expected);

        // Act

        // Assert
        assertEquals(expected, userService.getUsersByEmail("123@gmail.com"));
        userMapperStatic.close();
    }*/

    /*@Test
    void getUserByProfile_Succesfully() {
        // Arrange
        List<SearchUserDTO> expected = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        when(userStoreMock.findByProfile(1)).thenReturn(userList);

        MockedStatic<ImplUserMapper> userMapperStatic = mockStatic(ImplUserMapper.class);
        when(ImplUserMapper.toSearchDTOList(userList)).thenReturn(expected);

        // Act

        // Assert
        assertEquals(expected, userService.getUserByProfile(1));
        userMapperStatic.close();
    }*/


    /*@Test
    void removeProfile_FaillingBecauseUserDoesNotExist() {
        // Arrange
        User mockedUser = mock(User.class);
        ProfileID mockedProfileID = mock(ProfileID.class);

        MockedStatic<ProfileID> profileIDmockedStatic = mockStatic(ProfileID.class);
        when(ProfileID.createProfileID(1)).thenReturn(mockedProfileID);

        when(userStoreMock.findUser("123@gmail.com")).thenReturn(mockedUser);
        when(userStoreMock.userExistsInStore(mockedUser)).thenReturn(false);

        // Assert
        assertFalse(userService.removeProfile("123@gmail.com", 1));
        profileIDmockedStatic.close();
    }*/


    /*@Test
    void removeProfile_FaillingBecauseUserDoesNotHaveProfile() {
        // Arrange
        User mockedUser = mock(User.class);
        ProfileID mockedProfileID = mock(ProfileID.class);

        MockedStatic<ProfileID> profileIDmockedStatic = mockStatic(ProfileID.class);
        when(ProfileID.createProfileID(1)).thenReturn(mockedProfileID);

        when(userStoreMock.findUser("123@gmail.com")).thenReturn(mockedUser);
        when(userStoreMock.userExistsInStore(mockedUser)).thenReturn(true);
        when(mockedUser.hasProfile(mockedProfileID)).thenReturn(false);

        // Assert
        assertFalse(userService.removeProfile("123@gmail.com", 1));
        profileIDmockedStatic.close();
    }*/


    /*@Test
    void addProfile_FaillingBecauseUserDoesNotExist() {

        // Arrange
        UpdateProfileDTO updateProfileDTO = new UpdateProfileDTO("123@gmail.com", 1);
        ProfileID id = ProfileID.createProfileID(updateProfileDTO.getProfileID());

        when(userStoreMock.userExistsInStore(userMock)).thenReturn(false);
        when(userStoreMock.findUser(updateProfileDTO.getEmail())).thenReturn(userMock);

        // Act

        // Assert
        assertFalse(userService.addProfile("123@gmail.com", 1));
    }*/

 /*   @Test
    void passwordIsChangedSuccessfully() throws NoSuchAlgorithmException {
        ChangePasswordDTO changePasswordDTO = mock(ChangePasswordDTO.class);

        doReturn(Optional.of(userMock)).when(userStoreMock).findUserByEmail("isep@ipp.pt");

        doReturn(true).when(userMock).updatePassword(changePasswordDTO.oldPassword,
                changePasswordDTO.newPassword);

        assertTrue(userService.changePassword(1, changePasswordDTO));
    }

    @Test
    void changePasswordFailure() throws NoSuchAlgorithmException {
        ChangePasswordDTO changePasswordDTO = mock(ChangePasswordDTO.class);
        doReturn(Optional.empty()).when(userStoreMock).findUserByEmail("isep@ipp.pt");

        assertFalse(userService.changePassword("isep@ipp.pt", changePasswordDTO));
    }*/

    /*@Test
    void addProfile_FaillingBecauseUserAlreadyHasProfile() {

        // Arrange
        ProfileID id = ProfileID.createProfileID(1);

        when(userStoreMock.userExistsInStore(userMock)).thenReturn(true);
        when(userMock.hasProfile(id)).thenReturn(true);
        when(userStoreMock.findUser("123@gmail.com")).thenReturn(userMock);

        // Act

        // Assert
        assertFalse(userService.addProfile("123@gmail.com", 1));
    }*/
