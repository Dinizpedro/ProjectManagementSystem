package switchfive.project.applicationServices.assemblers.implAssemblers;

import org.junit.jupiter.api.Test;
import switchfive.project.dtos.SearchUserDTO;
import switchfive.project.dtos.UserDTO;
import switchfive.project.mappers.mappersApp.implMappers.ImplUserMapper;
import switchfive.project.domain.aggregates.user.User;
import switchfive.project.domain.shared.valueObjects.*;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserAssemblerTest {

    @Test
    void userToDTO() throws NoSuchAlgorithmException {
        UserName userName = UserName.createUsername("Antonio");
        Email email = Email.create("isep@ipp.pt");
        Password password = Password.createPassword("ant@ni0oO");
        Function function = Function.createFunction("function");
        Activation activation = Activation.createActivation();

        User user = new User(email,new ArrayList<>(),activation,password,userName,function);
        UserDTO expected = new UserDTO();
        expected.setUserName(userName.toString());
        expected.setUserFunction(function.toString());
        expected.setEmail(email.toString());
        expected.setActivation(activation.isActivated());

        //Act
        UserDTO actual = ImplUserMapper.toDTO(user);

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void userToDTOWithPassword() throws NoSuchAlgorithmException {
        UserName userName = UserName.createUsername("Antonio");
        Email email = Email.create("isep@ipp.pt");
        Password password = Password.createPassword("ant@ni0oO");
        Function function = Function.createFunction("function");
        Activation activation = Activation.createActivation();

        User user = new User(email,new ArrayList<>(),activation,password,userName,function);
        UserDTO expected = new UserDTO();
        expected.setUserName(userName.toString());
        expected.setUserFunction(function.toString());
        expected.setEmail(email.toString());
        expected.setPassword(password.toString());
        expected.setActivation(activation.isActivated());

        //Act
        UserDTO actual = ImplUserMapper.toDTOWithPassword(user);

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void userToSearchUserDTO() throws NoSuchAlgorithmException {
        UserName userName = UserName.createUsername("Antonio");
        Email email = Email.create("isep@ipp.pt");
        ProfileDescription profileDescription = ProfileDescription.createProfileDescription("Visitor");
        Password password = Password.createPassword("ant@ni0oO");
        Function function = Function.createFunction("function");
        Activation activation = Activation.createActivation();
        List<ProfileDescription> profileList = new ArrayList<>();
        profileList.add(profileDescription);
        List<String> profileStringList = new ArrayList<>();
        profileStringList.add("Visitor");

        User user = new User(email,profileList,activation,password,userName,function);

        SearchUserDTO expected = new SearchUserDTO();

        expected.setUserProfileList(user.getProfileListAsDescription());
        expected.setFunction(user.getFunctionDescription());
        expected.setEmail(user.getEmail());
        expected.setActivation(user.getAccountStatus());
        expected.setUserName(user.getUserName().getUserName());


        //Act
        SearchUserDTO actual = ImplUserMapper.toSearchUserDTO(user);

        //Assert
        assertEquals(expected, actual);
    }


    @Test
    void userToSearchUserDTOList() throws NoSuchAlgorithmException {
        List<SearchUserDTO> expected = new ArrayList<>();

        UserName userName = UserName.createUsername("Antonio");
        Email email = Email.create("isep@ipp.pt");
        ProfileDescription profileDescription = ProfileDescription.createProfileDescription("Visitor");
        Password password = Password.createPassword("ant@ni0oO");
        Function function = Function.createFunction("function");
        Activation activation = Activation.createActivation();
        List<ProfileDescription> profileList = new ArrayList<>();
        profileList.add(profileDescription);
        List<String> profileStringList = new ArrayList<>();
        profileStringList.add("Visitor");

        User user = new User(email,profileList,activation,password,userName,function);

        SearchUserDTO dto = new SearchUserDTO();

        dto.setUserProfileList(user.getProfileListAsDescription());
        dto.setFunction(user.getFunctionDescription());
        dto.setEmail(user.getEmail());
        dto.setActivation(user.getAccountStatus());
        dto.setUserName(user.getUserName().getUserName());

        List<User> userList = new ArrayList<>();
        userList.add(user);

        expected.add(dto);


        //Act
        List<SearchUserDTO> actual = ImplUserMapper.toSearchDTOList(userList);

        //Assert
        assertEquals(expected, actual);
    }

}
