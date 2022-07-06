package switchfive.project.interfaceAdapters.controllers.implControllers;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import switchfive.project.applicationServices.appServices.implAppServices.ImplAppUserService;
import switchfive.project.dtos.*;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@ExtendWith(MockitoExtension.class)
class ImplUserControllerTest {

    private final static String ERROR_MESSAGE = "Operation failed.";

    @Mock
    UserDTO dtoMock;

    @Mock
    SearchUserDTO searchUserDTOMock;

    @Mock
    ImplAppUserService userService;

    @InjectMocks
    ImplUserController userController;


    @Test
    void getUserByEmail_Succesfully() throws NoSuchAlgorithmException {

        // Arrange
        when(userService.getUserByEmail(anyString())).thenReturn(Optional.of(searchUserDTOMock));

        // Act
        ResponseEntity<Object> expected = new ResponseEntity<>(searchUserDTOMock, HttpStatus.OK);
        ResponseEntity<Object> result = userController.getUserByEmail(anyString());

        // Assert
        assertEquals(expected, result);

    }

    @Test
    void getUserByEmail_Failling() throws NoSuchAlgorithmException {

        // Arrange
        when(userService.getUserByEmail(anyString())).thenReturn(Optional.empty());
        String NO_USER_FOUND = "User not found.";

        // Act
        ResponseEntity<Object> expected = new ResponseEntity<>(NO_USER_FOUND, HttpStatus.NO_CONTENT);
        ResponseEntity<Object> result = userController.getUserByEmail(anyString());

        // Assert
        assertEquals(expected, result);

    }


    @Test
    void getUsersByProfile_Succesfully() throws NoSuchAlgorithmException {

        // Arrange
        List<SearchUserDTO> dtoList = new ArrayList<>();

        when(searchUserDTOMock.getUserName()).thenReturn("Tester");
        when(searchUserDTOMock.getEmail()).thenReturn("123@isep.ipp.pt");

        dtoList.add(searchUserDTOMock);

        when(userService.getUsersByProfile(anyString())).thenReturn(dtoList);

        RepresentationModel<SearchUserDTO> linkList = new RepresentationModel<>();

        Link link = linkTo(methodOn(ImplUserController.class).
                getUserByEmail(searchUserDTOMock.getEmail())).
                withRel(searchUserDTOMock.getUserName())
                .withType("GET");

        linkList.add(link);

        // Act
        ResponseEntity<Object> expected = new ResponseEntity<>(linkList, HttpStatus.OK);
        ResponseEntity<Object> result = userController.getUsersByProfile(anyString());

        // Arrange
        assertEquals(expected, result);
    }

    @Test
    void getUsersByEmail_Succesfully() throws NoSuchAlgorithmException {

        // Arrange
        List<SearchUserDTO> dtoList = new ArrayList<>();

        when(searchUserDTOMock.getEmail()).thenReturn("123@isep.ipp.pt");

        dtoList.add(searchUserDTOMock);

        when(userService.getUsersByEmail(anyString())).thenReturn(dtoList);

        RepresentationModel<SearchUserDTO> linkList = new RepresentationModel<>();
        linkList.add(linkTo(methodOn(ImplUserController.class).getUserByEmail(searchUserDTOMock.getEmail())).withRel(searchUserDTOMock.getEmail()).withType("GET"));

        // Act
        ResponseEntity<Object> expected = new ResponseEntity<>(linkList, HttpStatus.OK);
        ResponseEntity<Object> result = userController.getUsersByEmail("123@isep.ipp.pt");

        // Arrange
        assertEquals(expected, result);
    }

    @Test
    void getUsersByEmail_OneCharacterInput() throws NoSuchAlgorithmException {

        // Arrange
        RepresentationModel<SearchUserDTO> linkList = new RepresentationModel<>();

        // Act
        ResponseEntity<Object> expected = new ResponseEntity<>(linkList, HttpStatus.OK);
        ResponseEntity<Object> result = userController.getUsersByEmail("a");

        // Arrange
        assertEquals(expected, result);
    }

    @Test
    void getUsersByEmail_TwoCharacterInput() throws NoSuchAlgorithmException {

        // Arrange
        List<SearchUserDTO> dtoList = new ArrayList<>();

        when(searchUserDTOMock.getEmail()).thenReturn("123@isep.ipp.pt");

        dtoList.add(searchUserDTOMock);

        when(userService.getUsersByEmail(anyString())).thenReturn(dtoList);

        RepresentationModel<SearchUserDTO> linkList = new RepresentationModel<>();

        linkList.add(linkTo(methodOn(ImplUserController.class).getUserByEmail(searchUserDTOMock.getEmail())).withRel(searchUserDTOMock.getEmail()).withType("GET"));


        // Act
        ResponseEntity<Object> expected = new ResponseEntity<>(linkList, HttpStatus.OK);
        ResponseEntity<Object> result = userController.getUsersByEmail("as");

        // Arrange
        assertEquals(expected, result);
    }


/*
   @Test
    void createUser() throws NoSuchAlgorithmException {
        UserDTO userDTO = new UserDTO();
        userDTO.email = "abc@email.com";
        userDTO.password = "123546Aa@";
        userDTO.userName = "validName";
        userDTO.userFunction = "Tester";
        when(userService.createAndSaveUser(userDTO)).thenReturn(any());

        ResponseEntity<Object> actual = userController.createUser(userDTO);
        ResponseEntity<Object> expected = new ResponseEntity<>("User created Successfully.", HttpStatus.CREATED);

        assertEquals(expected, actual);
    }
*/


    @Test
    void shouldReturnAnHttpStatusConflict() throws NoSuchAlgorithmException {

        UserDTO userDTO = new UserDTO();
        userDTO.email = "abc@email.com";
        userDTO.password = "123546Aa@";
        userDTO.userName = "validName";
        userDTO.userFunction = "Tester";

        doThrow(IllegalArgumentException.class).when(userService).createAndSaveUser(userDTO);

        userController.createUser(userDTO);
        ResponseEntity<Object> actual = userController.createUser(userDTO);
        ResponseEntity<Object> expected = new ResponseEntity<>(ERROR_MESSAGE, HttpStatus.CONFLICT);

        assertEquals(expected, actual);
    }

    @Test
    void activationAccountIsSuccessful() throws NoSuchAlgorithmException {
        UserDTO userDTO = new UserDTO();
        userDTO.email = "abc@email.com";
        userDTO.password = "123546Aa@";
        userDTO.userName = "validName";
        userDTO.userFunction = "Tester";
        userDTO.activation = true;

        userDTO.add(linkTo(methodOn(ImplUserController.class).getUserByEmail(userDTO.email)).withSelfRel());
        when(userService.activateAccount(userDTO)).thenReturn(Optional.of(userDTO));

        ResponseEntity<Object> actual = userController.activateAccount(userDTO);
        ResponseEntity<Object> expected = new ResponseEntity<>(userDTO, HttpStatus.OK);

        assertEquals(expected, actual);
    }


    @Test
    void activationAccountIsUnsuccessful() throws NoSuchAlgorithmException {
        UserDTO userDTO = new UserDTO();
        userDTO.email = "abc@email.com";
        userDTO.password = "123546Aa@";
        userDTO.userName = "validName";
        userDTO.userFunction = "Tester";
        when(userService.activateAccount(userDTO)).thenReturn(Optional.of(userDTO));

        ResponseEntity<Object> actual = userController.activateAccount(userDTO);
        ResponseEntity<Object> expected = new ResponseEntity<>(ERROR_MESSAGE, HttpStatus.UNAUTHORIZED);

        assertEquals(expected, actual);
    }

  /*  @Test
    void shouldReturnAn200HTTPStatusOkAndTheUserDTOWhenTheUserWasUpdated() throws NoSuchAlgorithmException {
        UserDTO userDTO = new UserDTO();
        userDTO.email = "abc@email.com";
        userDTO.userName = "validName";
        userDTO.function = "Tester";
        doReturn(true).when(userService).updateUser(anyInt(), anyString(), anyString());
        MockedStatic<ImplUserMapper> mockedMapper = mockStatic(ImplUserMapper.class);
        mockedMapper.when(() -> ImplUserMapper.toDto(any())).thenReturn(userDTO);

        ResponseEntity<Object> expected = new ResponseEntity<>(userDTO, HttpStatus.OK);

        ResponseEntity<Object> actual = userController.updateUser(1, userDTO);
        mockedMapper.close();

        assertEquals(expected, actual);
    }*/


   /*@Test
    void shouldReturnA404HTTPSCodeWhenTheUserWasNotUpdated() throws NoSuchAlgorithmException {
        UserDTO userDTO = new UserDTO();
        userDTO.email = "abc@email.com";
        userDTO.password = "123546Aa@";
        userDTO.userName = "validName";
        userDTO.function = "Tester";
        doReturn(false).when(userService).updateUser(any(UserDTO.class));
        ResponseEntity<Object> expected = new ResponseEntity<>(HttpStatus.NOT_FOUND);

        ResponseEntity<Object> actual = userController.updateUser(userDTO);

        assertEquals(expected, actual);
    }*/


   /* @Test
    void getValidUser() throws NoSuchAlgorithmException {

        //Arrange
        when(userService.getUserByEmail(anyString())).thenReturn(Optional.of(searchUserDTOMock));
        when(searchUserDTOMock.add(any(Link.class))).thenReturn(searchUserDTOMock);

        //Act
        SearchUserDTO dto = searchUserDTOMock;
        ResponseEntity<Object> expected = new ResponseEntity<>(Optional.of(dto), HttpStatus.OK);
        ResponseEntity<Object> actual = userController.getUserByEmail(anyString());

        //Assert
        assertEquals(expected, actual);
    }*/

  /*  @Test
    void getValidUsers() throws NoSuchAlgorithmException {

        //Arrange
        List<SearchUserDTO> searchUserDTOList = new ArrayList<>();
        searchUserDTOList.add(searchUserDTOMock);

        when(userService.getUsersByEmail(anyString())).thenReturn(searchUserDTOList);

        //Act
        ResponseEntity<Object> expected = new ResponseEntity<>(searchUserDTOList, HttpStatus.OK);
        ResponseEntity<Object> actual = userController.getUsersByEmail(anyString());

        //Assert
        assertEquals(expected, actual);
    }*/

    //changePassword Controller test


    @Test
    void changePasswordSuccessfully() throws NoSuchAlgorithmException {
        UserDTO userDTO = new UserDTO();
        ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO();

        userDTO.add(linkTo(methodOn(ImplUserController.class).getUsersByEmail(changePasswordDTO.email)).withSelfRel());
        when(userService.changePassword(changePasswordDTO)).thenReturn(Optional.of(userDTO));

        ResponseEntity<Object> actual = userController.changePassword(changePasswordDTO);
        ResponseEntity<Object> expected = new ResponseEntity<>(userDTO, HttpStatus.OK);

        assertEquals(expected, actual);
    }

    @Test
    void changePasswordFailure() throws NoSuchAlgorithmException {
        ChangePasswordDTO changePasswordDTO = null;

        when(userService.changePassword(changePasswordDTO)).thenReturn(Optional.empty());

        ResponseEntity<Object> actual = userController.changePassword(changePasswordDTO);
        ResponseEntity<Object> expected = new ResponseEntity<>(ERROR_MESSAGE, HttpStatus.BAD_REQUEST);

        assertEquals(expected, actual);
    }

    @Test
    void changePasswordException() throws NoSuchAlgorithmException {
        ChangePasswordDTO changePasswordDTO = null;
        when(userService.changePassword(changePasswordDTO)).thenThrow(IllegalArgumentException.class);

        ResponseEntity<Object> actual = userController.changePassword(changePasswordDTO);
        ResponseEntity<Object> expected = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        assertEquals(expected, actual);
    }

    @Test
    void addProfile_Successfully() throws NoSuchAlgorithmException {

        //Arrange
        UpdateUserProfileDTO dto = mock(UpdateUserProfileDTO.class);
        when(userService.validateAndAddProfile("123@isep.ipp.pt", dto.getProfile())).thenReturn((Optional.of(searchUserDTOMock)));

        //Act
        ResponseEntity<Object> expected = new ResponseEntity<>(Optional.of(searchUserDTOMock), HttpStatus.OK);
        ResponseEntity<Object> actual = userController.addProfile("123@isep.ipp.pt", dto);

        //Assert
        System.out.println(expected);
        System.out.println(actual);
        assertEquals(expected, actual);
    }

    @Test
    void addProfile_Failling_ProfileDoesNotExist() {

        //Arrange
        UpdateUserProfileDTO dto = mock(UpdateUserProfileDTO.class);
        String error = "Operation failed.";

        //Act
        ResponseEntity<Object> expected = new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        ResponseEntity<Object> actual = userController.addProfile("123@isep.ipp.pt", dto);

        //Assert
        assertEquals(expected, actual);
    }


    @Test
    void addProfile_Failling_UserDoesNotExist() throws NoSuchAlgorithmException {

        //Arrange
        UpdateUserProfileDTO dto = mock(UpdateUserProfileDTO.class);
        String error = "Operation failed.";

        //Act
        ResponseEntity<Object> expected = new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        ResponseEntity<Object> actual = userController.addProfile("123@isep.ipp.pt", dto);

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void addProfile_Failling_NoUserProfilesFound() throws NoSuchAlgorithmException {

        //Arrange
        UpdateUserProfileDTO dto = mock(UpdateUserProfileDTO.class);
        String error = "Operation failed.";

        //Act
        ResponseEntity<Object> expected = new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        ResponseEntity<Object> actual = userController.addProfile("123@isep.ipp.pt", dto);

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void removeProfile_Successfully() throws NoSuchAlgorithmException {

        //Arrange
        UpdateUserProfileDTO dto = mock(UpdateUserProfileDTO.class);
        when(userService.validateAndRemoveProfile("123@isep.ipp.pt", dto.getProfile())).thenReturn((Optional.of(searchUserDTOMock)));

        //Act
        ResponseEntity<Object> expected = new ResponseEntity<>(Optional.of(searchUserDTOMock), HttpStatus.OK);
        ResponseEntity<Object> actual = userController.removeProfile("123@isep.ipp.pt", dto);

        //Assert
        assertEquals(expected, actual);

    }

    @Test
    void removeProfile_Failling_UserDoesNotExist() throws NoSuchAlgorithmException {

        //Arrange
        UpdateUserProfileDTO dto = mock(UpdateUserProfileDTO.class);
        String error = "Operation failed.";

        //Act
        ResponseEntity<Object> expected = new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        ResponseEntity<Object> actual = userController.removeProfile("123@isep.ipp.pt", dto);

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void removeProfile_Failling_NoUserProfilesFound() throws NoSuchAlgorithmException {

        //Arrange
        UpdateUserProfileDTO dto = mock(UpdateUserProfileDTO.class);
        String error = "Operation failed.";

        //Act
        ResponseEntity<Object> expected = new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        ResponseEntity<Object> actual = userController.removeProfile("123@isep.ipp.pt", dto);

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void removeProfile_Failling_ProfileDoesNotExist() throws NoSuchAlgorithmException {

        //Arrange
        UpdateUserProfileDTO dto = mock(UpdateUserProfileDTO.class);
        String error = "Operation failed.";

        //Act
        ResponseEntity<Object> expected = new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        ResponseEntity<Object> actual = userController.removeProfile("123@isep.ipp.pt", dto);

        //Assert
        assertEquals(expected, actual);
    }

/*
    @Test
    void getUserByEmail_WhenUserExists() throws NoSuchAlgorithmException {

        // Arrange
        SearchUserDTO dto = searchUserDTOMock;
        Optional<SearchUserDTO> optDTO = Optional.of(dto);
        when(userService.getUserByEmail("123@gmail.com")).thenReturn(optDTO);

        // Assert
        ResponseEntity<Object> actual = userController.getUserByEmail("123@gmail.com");
        ResponseEntity<Object> expected = new ResponseEntity<>(optDTO, HttpStatus.OK);

        assertEquals(actual, expected);
    }
*/

    @Test
    void setUserToActive_Success() throws Exception {

        // Arrange
        when(userService.setUserToActive("123@gmail.com")).thenReturn(true);
        when(userService.getUserByEmail("123@gmail.com")).thenReturn(Optional.of(searchUserDTOMock));

        // Act
        ResponseEntity<Object> actual = userController.setUserToActive("123@gmail.com");
        ResponseEntity<Object> expected = new ResponseEntity<>(searchUserDTOMock, HttpStatus.OK);

        // Assert
        assertEquals(actual, expected);
    }

    @Test
    void setUserToActive_Failling() throws Exception {

        // Arrange
        when(userService.setUserToActive("123@gmail.com")).thenReturn(false);
        when(userService.getUserByEmail("123@gmail.com")).thenReturn(Optional.of(searchUserDTOMock));

        // Act
        ResponseEntity<Object> actual = userController.setUserToActive("123@gmail.com");
        ResponseEntity<Object> expected = new ResponseEntity<>("Operation failed.", HttpStatus.BAD_REQUEST);

        // Assert
        assertEquals(actual, expected);
    }

    @Test
    void setUserToInactive_Success() throws Exception {

        // Arrange
        when(userService.setUserToInactive("123@gmail.com")).thenReturn(true);
        when(userService.getUserByEmail("123@gmail.com")).thenReturn(Optional.of(searchUserDTOMock));

        // Act
        ResponseEntity<Object> actual = userController.setUserToInactive("123@gmail.com");
        ResponseEntity<Object> expected = new ResponseEntity<>(searchUserDTOMock, HttpStatus.OK);

        // Assert
        assertEquals(actual, expected);
    }

    @Test
    void setUserToInactive_Failling() throws Exception {

        // Arrange
        when(userService.setUserToInactive("123@gmail.com")).thenReturn(false);
        when(userService.getUserByEmail("123@gmail.com")).thenReturn(Optional.of(searchUserDTOMock));

        // Act
        ResponseEntity<Object> actual = userController.setUserToInactive("123@gmail.com");
        ResponseEntity<Object> expected = new ResponseEntity<>("Operation failed.", HttpStatus.BAD_REQUEST);

        // Assert
        assertEquals(actual, expected);
    }

    @Test
    void createUserSuccessfully() throws NoSuchAlgorithmException {
        //Arrange
        UserDTO dto = new UserDTO();
        dto.setEmail("valter@gmail.com");
        dto.setPassword("Valter@123");
        dto.setUserName("ValterSousa");
        dto.setUserFunction("developer");
        dto.setCode("act1");
        dto.setActivation(false);

        when(userService.createAndSaveUser(any(UserDTO.class))).thenReturn(dtoMock);
        when(dtoMock.add(any(Link.class))).thenReturn(dtoMock);

        //Act
        ResponseEntity<Object> expected = new ResponseEntity<>(dtoMock, HttpStatus.CREATED);
        ResponseEntity<Object> actual = userController.createUser(dto);

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void invalidUserIsNotCreated() throws NoSuchAlgorithmException {

        //Arrange
        UserDTO dto = new UserDTO();
        dto.setEmail("valter@gmail.com");
        dto.setPassword("Valter@123");
        dto.setUserName("ValterSousa");
        dto.setUserFunction("developer");
        dto.setCode("act1");
        dto.setActivation(false);

        when(userService.createAndSaveUser(any(UserDTO.class))).thenThrow(IllegalArgumentException.class);

        //Act
        ResponseEntity<Object> expected = new ResponseEntity<>(ERROR_MESSAGE, HttpStatus.CONFLICT);
        ResponseEntity<Object> actual = userController.createUser(dto);

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void addLinksToDTO() throws NoSuchAlgorithmException {

        // Arrange
        List<SearchUserDTO> dtoList = new ArrayList<>();
        when(searchUserDTOMock.getEmail()).thenReturn("123@isep.ipp.pt");
        dtoList.add(searchUserDTOMock);
        when(userService.getUsersByEmail("123@isep.ipp.pt")).thenReturn(dtoList);
        RepresentationModel<SearchUserDTO> linkList = new RepresentationModel<>();

        Link link = linkTo(methodOn(ImplUserController.class).getUserByEmail(searchUserDTOMock.getEmail())).withRel(searchUserDTOMock.getEmail()).withType("GET");
        linkList.add(link);

        // Act
        ResponseEntity<Object> expected = new ResponseEntity<>(linkList, HttpStatus.OK);
        ResponseEntity<Object> result = userController.getUsersByEmail("123@isep.ipp.pt");

        // Arrange
        assertEquals(expected, result);
    }

    @Test
    void shouldReturnHttpStatusOkWhenGetAllocatedProjectsSuccessfully() throws NoSuchAlgorithmException, ParseException {

        //Assert
        AllocatedProjectDTO allocatedProjectDTOMock = mock(AllocatedProjectDTO.class);
        List<AllocatedProjectDTO> listOfAllocatedProjectDTO = new ArrayList<>();
        listOfAllocatedProjectDTO.add(allocatedProjectDTOMock);

        when(userService.getAllocatedProjects(anyString())).thenReturn(listOfAllocatedProjectDTO);
        when(allocatedProjectDTOMock.add(any(Link.class))).thenReturn(allocatedProjectDTOMock);

        //Act
        ResponseEntity<Object> expected = new ResponseEntity<>(listOfAllocatedProjectDTO, HttpStatus.OK);
        ResponseEntity<Object> actual = userController.getAllocatedProjects(anyString());

        //Assert
        assertEquals(expected,actual);
    }

    @Test
    void shouldReturnHttpStatusOkWhenAllocatedProjectDTOsListIsEmpty() throws NoSuchAlgorithmException, ParseException {

        //Arrange
        List<AllocatedProjectDTO> listOfAllocatedProjectDTO = new ArrayList<>();

        when(userService.getAllocatedProjects(anyString())).thenReturn(listOfAllocatedProjectDTO);

        //Act
        ResponseEntity<Object> expected = new ResponseEntity<>(listOfAllocatedProjectDTO, HttpStatus.OK);
        ResponseEntity<Object> actual = userController.getAllocatedProjects(anyString());

        //Assert
        assertEquals(expected,actual);
    }

    @Test
    void shouldReturnHttpStatusBadRequestWhenEmailIsInvalid() throws NoSuchAlgorithmException, ParseException {

        //Arrange

        when(userService.getAllocatedProjects(anyString())).thenThrow(IllegalArgumentException.class);

        //Act
        ResponseEntity<Object> expected = new ResponseEntity<>(ERROR_MESSAGE, HttpStatus.BAD_REQUEST);
        ResponseEntity<Object> actual = userController.getAllocatedProjects(anyString());

        //Assert
        assertEquals(expected,actual);
    }
}
