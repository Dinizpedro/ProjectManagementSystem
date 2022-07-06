package switchfive.project.interfaceAdapters.controllers.implControllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import switchfive.project.dtos.ChangePasswordDTO;
import switchfive.project.dtos.UserDTO;
import switchfive.project.applicationServices.appServices.implAppServices.ImplAppUserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = HypermediaAutoConfiguration.class)
@Transactional
public class UserControllerIntegrationTest implements WebMvcConfigurer {

    @SpyBean
    ImplAppUserService implAppUserService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //TODO
    /*    @Test
    void getUserByEmail_WhenUserExistsdss() throws NoSuchAlgorithmException {

        // Arrange
        ImplUserRepository store = new ImplUserRepository();
        UserFactoryImplementation factory = new UserFactoryImplementation();

        ImplAppUserService service = new ImplAppUserService(store,factory);

        ImplUserController controller = new ImplUserController(service);

        User user = new User(UserID.createUserID(1), Email.createEmail("pedro@gmail.com"), Password.createPassword("Pedro1@."), UserName.createUsername("Pedro"), Function.createFunction("Developer"));
        store.addAccount(user);

        List<Integer> dtoProfileList = new ArrayList<>();
        dtoProfileList.add(1);

        SearchUserDTO UserDTO = SearchUserDTO.createSearchUserDTO("pedro@gmail.com",dtoProfileList,false,"Pedro","Developer");
        List<SearchUserDTO> expectedList = new ArrayList<>();
        expectedList.add(UserDTO);


        // Act
        ResponseEntity<Object> expected = new ResponseEntity<>(expectedList, HttpStatus.OK);
        ResponseEntity<Object> result = controller.getUsersByEmail("pedro@gmail.com");

        // Assert
        assertEquals(expected,result);
    }

    @Test
    void getUserByEmail_WhenUserDoesNotExistsssd() {

        // Arrange
        ImplUserRepository store = new ImplUserRepository();
        UserFactoryImplementation factory = new UserFactoryImplementation();

        ImplAppUserService service = new ImplAppUserService(store,factory);

        ImplUserController controller = new ImplUserController(service);

        List<SearchUserDTO> expectedList = new ArrayList<>();

        ResponseEntity<Object> expected = new ResponseEntity<>(expectedList, HttpStatus.OK);

        // Act
        ResponseEntity<Object> result = controller.getUsersByEmail("pedro@gmail.com");

        // Assert
        assertEquals(expected,result);
    }

    @Test
    void getUserByProfile_WhenProfileExistsss() throws NoSuchAlgorithmException {

        // Arrange
        ImplUserRepository store = new ImplUserRepository();
        UserFactoryImplementation factory = new UserFactoryImplementation();

        ImplAppUserService service = new ImplAppUserService(store,factory);

        ImplUserController controller = new ImplUserController(service);

        User user = new User(UserID.createUserID(1), Email.createEmail("pedro@gmail.com"), Password.createPassword("Pedro1@."), UserName.createUsername("Pedro"), Function.createFunction("Developer"));
        store.addAccount(user);

        List<Integer> dtoProfileList = new ArrayList<>();
        dtoProfileList.add(1);

        SearchUserDTO userDTO = SearchUserDTO.createSearchUserDTO("pedro@gmail.com",dtoProfileList,false,"Pedro","Developer");
        List<SearchUserDTO> expectedList = new ArrayList<>();

        Link link = linkTo(methodOn(ImplUserController.class).getUsersByEmail("pedro@gmail.com")).withSelfRel();
        userDTO.add(link);
        expectedList.add(userDTO);

        // Act
        ResponseEntity<Object> expected = new ResponseEntity<>(expectedList, HttpStatus.OK);
        ResponseEntity<Object> result = controller.getUsersByProfile(1);

        // Assert
        assertEquals(expected,result);
    }

    @Test
    void getUserByProfile_WhenProfileDoestNotExisdsdst() {
        // Arrange
        ImplUserRepository store = new ImplUserRepository();
        UserFactoryImplementation factory = new UserFactoryImplementation();

        ImplAppUserService service = new ImplAppUserService(store,factory);

        ImplUserController controller = new ImplUserController(service);
        List<SearchUserDTO> expectedList = new ArrayList<>();

        // Act
        ResponseEntity<Object> expected = new ResponseEntity<>(expectedList, HttpStatus.OK);
        ResponseEntity<Object> result = controller.getUsersByProfile(2);

        // Assert
        assertEquals(expected,result);
    }*/

    /*@Test
    void shouldCreateAnUser() throws Exception {
        UserDTO newUserDTO = new UserDTO();
        newUserDTO.userName = "newName";
        newUserDTO.email = "random@email.com";
        newUserDTO.function = "tester";
        newUserDTO.password = "123456Aa@";

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("/users/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newUserDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        String expected = "User created Successfully.";

        assertEquals(expected, resultContent);
    }*/

    @Test
    void shouldReturnTheUserInformationAndHttpStatusOKWhenUpdateWasSuccessful() throws Exception {
        // Create User DTO
        UserDTO newUserDTO = new UserDTO();
        newUserDTO.userName = "oldName";
        newUserDTO.email = "random@email.com";
        newUserDTO.userFunction = "oldFunction";
        newUserDTO.password = "123456Aa@";

        // Update User DTO
        UserDTO updatedUserDTO = new UserDTO();
        updatedUserDTO.userName = "updatedName";
        updatedUserDTO.email = "random@email.com";
        updatedUserDTO.userFunction = "updatedFunction";
        updatedUserDTO.password = "123456Aa@";

        // Expected DTO
        UserDTO expectedDTO = new UserDTO();
        expectedDTO.userName = "updatedName";
        expectedDTO.email = "random@email.com";
        expectedDTO.userFunction = "updatedFunction";
        expectedDTO.password = null;
        expectedDTO.code = null;
        Link link = linkTo(methodOn(ImplUserController.class).getUserByEmail(expectedDTO.email)).withSelfRel();
        expectedDTO.add(link);

        // Creating the user
        mockMvc.perform(MockMvcRequestBuilders.post("/users/")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(newUserDTO)));

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.put("/users")
                        .contentType(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(updatedUserDTO)))
                .andExpect(status().isOk())
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        String expected = objectMapper.writeValueAsString(expectedDTO);

        assertEquals(expected, resultContent);
    }

    @Test
    void shouldReturnTheUserInformationAndHttpStatus404WhenUpdateFailed() throws Exception {
        // Put method DTO
        UserDTO updatedUserDTO = new UserDTO();
        updatedUserDTO.userName = "updatedName";
        updatedUserDTO.email = "nonExistent@email.com";
        updatedUserDTO.userFunction = "updatedFunction";
        updatedUserDTO.password = "123456Aa@";


        // Actual method being tested
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.put("/users")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(updatedUserDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();

        assertEquals("", resultContent);
    }

    //TODO
/*    @Test
    void getUserByEmail_WhenUserExists() throws NoSuchAlgorithmException {

        // Arrange
        ImplUserRepository store = new ImplUserRepository();
        UserFactoryImplementation factory = new UserFactoryImplementation();

        ImplAppUserService service = new ImplAppUserService(store,factory);

        ImplUserController controller = new ImplUserController(service);

        User user = new User(UserID.createUserID(1), Email.createEmail("pedro@gmail.com"), Password.createPassword("Pedro1@."), UserName.createUsername("Pedro"), Function.createFunction("Developer"));
        store.addAccount(user);

        List<Integer> dtoProfileList = new ArrayList<>();
        dtoProfileList.add(1);

        SearchUserDTO UserDTO = SearchUserDTO.createSearchUserDTO("pedro@gmail.com",dtoProfileList,false,"Pedro","Developer");
        List<SearchUserDTO> expectedList = new ArrayList<>();
        expectedList.add(UserDTO);


        // Act
        ResponseEntity<Object> expected = new ResponseEntity<>(expectedList, HttpStatus.OK);
        ResponseEntity<Object> result = controller.getUsersByEmail("pedro@gmail.com");

        // Assert
        assertEquals(expected,result);
    }

    @Test
    void getUserByEmail_WhenUserDoesNotExist() {

        // Arrange
        ImplUserRepository store = new ImplUserRepository();
        UserFactoryImplementation factory = new UserFactoryImplementation();

        ImplAppUserService service = new ImplAppUserService(store,factory);

        ImplUserController controller = new ImplUserController(service);

        List<SearchUserDTO> expectedList = new ArrayList<>();

        ResponseEntity<Object> expected = new ResponseEntity<>(expectedList, HttpStatus.OK);

        // Act
        ResponseEntity<Object> result = controller.getUsersByEmail("pedro@gmail.com");

        // Assert
        assertEquals(expected,result);
    }

    @Test
    void getUserByEmail_WhenSearchingByEmptyString() throws NoSuchAlgorithmException {

        // Arrange
        ImplUserRepository store = new ImplUserRepository();
        UserFactoryImplementation factory = new UserFactoryImplementation();

        ImplAppUserService service = new ImplAppUserService(store,factory);

        ImplUserController controller = new ImplUserController(service);

        User user = new User(UserID.createUserID(1), Email.createEmail("pedro@gmail.com"), Password.createPassword("Pedro1@."), UserName.createUsername("Pedro"), Function.createFunction("Developer"));
        store.addAccount(user);

        List<SearchUserDTO> expectedList = new ArrayList<>();

        // Act
        ResponseEntity<Object> expected = new ResponseEntity<>(expectedList, HttpStatus.OK);
        ResponseEntity<Object> result = controller.getUsersByEmail("");

        // Assert
        assertEquals(expected,result);
    }

    @Test
    void getUserByProfile_WhenProfileExists() throws NoSuchAlgorithmException {

        // Arrange
        ImplUserRepository store = new ImplUserRepository();
        UserFactoryImplementation factory = new UserFactoryImplementation();

        ImplAppUserService service = new ImplAppUserService(store,factory);

        ImplUserController controller = new ImplUserController(service);

        User user = new User(UserID.createUserID(1), Email.createEmail("pedro@gmail.com"), Password.createPassword("Pedro1@."), UserName.createUsername("Pedro"), Function.createFunction("Developer"));
        store.addAccount(user);

        List<Integer> dtoProfileList = new ArrayList<>();
        dtoProfileList.add(1);

        SearchUserDTO userDTO = SearchUserDTO.createSearchUserDTO("pedro@gmail.com",dtoProfileList,false,"Pedro","Developer");
        List<SearchUserDTO> expectedList = new ArrayList<>();
        expectedList.add(userDTO);

        // Act
        ResponseEntity<Object> expected = new ResponseEntity<>(expectedList, HttpStatus.OK);
        ResponseEntity<Object> result = controller.getUsersByProfile(1);

        // Assert
        assertEquals(expected,result);
    }

    @Test
    void getUserByProfile_WhenProfileDoestNotExist() {
        // Arrange
        ImplUserRepository store = new ImplUserRepository();
        UserFactoryImplementation factory = new UserFactoryImplementation();

        ImplAppUserService service = new ImplAppUserService(store,factory);

        ImplUserController controller = new ImplUserController(service);
        List<SearchUserDTO> expectedList = new ArrayList<>();

        // Act
        ResponseEntity<Object> expected = new ResponseEntity<>(expectedList, HttpStatus.OK);
        ResponseEntity<Object> result = controller.getUsersByProfile(2);

        // Assert
        assertEquals(expected,result);
    }

    @Test
    void addProfile_Succesfully() throws NoSuchAlgorithmException {
        // Arrange
        ImplUserRepository store = new ImplUserRepository();
        UserFactoryImplementation factory = new UserFactoryImplementation();

        ImplAppUserService service = new ImplAppUserService(store,factory);

        ImplUserController controller = new ImplUserController(service);

        User user = new User(UserID.createUserID(1), Email.createEmail("pedro@gmail.com"), Password.createPassword("Pedro1@."), UserName.createUsername("Pedro"), Function.createFunction("Developer"));
        store.addAccount(user);

        List<Integer> dtoProfileList = new ArrayList<>();
        dtoProfileList.add(1);
        dtoProfileList.add(2);

        SearchUserDTO userDTO = SearchUserDTO.createSearchUserDTO("pedro@gmail.com",dtoProfileList,false,"Pedro","Developer");

        Link link = linkTo(methodOn(ImplUserController.class).getUsersByEmail("pedro@gmail.com")).withSelfRel();
        userDTO.add(link);

        // Act
        ResponseEntity<Object> expected = new ResponseEntity<>(userDTO, HttpStatus.OK);
        ResponseEntity<Object> result = controller.addProfile("pedro@gmail.com",2);

        // Assert
        assertEquals(expected,result);
    }

    @Test
    void addProfile_Failling() throws NoSuchAlgorithmException {
        // Arrange
        ImplUserRepository store = new ImplUserRepository();
        UserFactoryImplementation factory = new UserFactoryImplementation();

        ImplAppUserService service = new ImplAppUserService(store,factory);

        ImplUserController controller = new ImplUserController(service);

        User user = new User(UserID.createUserID(1), Email.createEmail("pedro@gmail.com"), Password.createPassword("Pedro1@."), UserName.createUsername("Pedro"), Function.createFunction("Developer"));
        store.addAccount(user);

        List<Integer> dtoProfileList = new ArrayList<>();
        dtoProfileList.add(1);

        SearchUserDTO userDTO = SearchUserDTO.createSearchUserDTO("pedro@gmail.com",dtoProfileList,false,"Pedro","Developer");

        Link link = linkTo(methodOn(ImplUserController.class).getUsersByEmail("pedro@gmail.com")).withSelfRel();
        userDTO.add(link);

        // Act
        ResponseEntity<Object> expected = new ResponseEntity<>("Operation failed.", HttpStatus.BAD_REQUEST);
        ResponseEntity<Object> result = controller.addProfile("pedro@gmail.com",1);

        // Assert
        assertEquals(expected,result);
    }

    @Test
    void removeProfile_Succesfully() throws NoSuchAlgorithmException {
        // Arrange
        ImplUserRepository store = new ImplUserRepository();
        UserFactoryImplementation factory = new UserFactoryImplementation();

        ImplAppUserService service = new ImplAppUserService(store,factory);

        ImplUserController controller = new ImplUserController(service);

        User user = new User(UserID.createUserID(1), Email.createEmail("pedro@gmail.com"), Password.createPassword("Pedro1@."), UserName.createUsername("Pedro"), Function.createFunction("Developer"));
        store.addAccount(user);

        List<Integer> dtoProfileList = new ArrayList<>();

        SearchUserDTO userDTO = SearchUserDTO.createSearchUserDTO("pedro@gmail.com",dtoProfileList,false,"Pedro","Developer");

        Link link = linkTo(methodOn(ImplUserController.class).getUsersByEmail("pedro@gmail.com")).withSelfRel();
        userDTO.add(link);

        // Act
        ResponseEntity<Object> expected = new ResponseEntity<>(userDTO, HttpStatus.OK);
        ResponseEntity<Object> result = controller.removeProfile("pedro@gmail.com",1);

        // Assert
        assertEquals(expected,result);
    }

    @Test
    void removeProfile_Failling() throws NoSuchAlgorithmException {
        // Arrange
        ImplUserRepository store = new ImplUserRepository();
        UserFactoryImplementation factory = new UserFactoryImplementation();

        ImplAppUserService service = new ImplAppUserService(store,factory);

        ImplUserController controller = new ImplUserController(service);

        User user = new User(UserID.createUserID(1), Email.createEmail("pedro@gmail.com"), Password.createPassword("Pedro1@."), UserName.createUsername("Pedro"), Function.createFunction("Developer"));
        store.addAccount(user);

        List<Integer> dtoProfileList = new ArrayList<>();

        SearchUserDTO userDTO = SearchUserDTO.createSearchUserDTO("pedro@gmail.com",dtoProfileList,false,"Pedro","Developer");

        Link link = linkTo(methodOn(ImplUserController.class).getUsersByEmail("pedro@gmail.com")).withSelfRel();
        userDTO.add(link);

        // Act
        ResponseEntity<Object> expected = new ResponseEntity<>("Operation failed.", HttpStatus.BAD_REQUEST);
        ResponseEntity<Object> result = controller.removeProfile("pedro@gmail.com",3);

        // Assert
        assertEquals(expected,result);
    }*/
    /*@Test
    void getUsersByProfileSuccessfully() throws Exception {

        // Post method DTO
        UserDTO newUserDTO = new UserDTO();
        newUserDTO.userName = "oldName";
        newUserDTO.email = "random@email.com";
        newUserDTO.userFunction = "oldFunction";
        newUserDTO.password = "123456Aa@";
        Link link = linkTo(methodOn(ImplUserController.class).getUsersByEmail(newUserDTO.getEmail())).withSelfRel();


        // Creating the user
        mockMvc.perform(MockMvcRequestBuilders.post("/users/")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(newUserDTO))
                .accept(MediaType.APPLICATION_JSON));


        // Put method DTO
        SearchUserDTO searchUserDTO = SearchUserDTO.createSearchUserDTO("random@email.com",
                                                                                new ArrayList<>(Collections.singleton("Visitor")),
                                                                        false,
                                                                        "oldName",
                                                                         "oldFunction").add(link);

        List<SearchUserDTO> searchUserDTOList = new ArrayList<>();
        searchUserDTOList.add(searchUserDTO);

        // Actual method being tested
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/users/profiles/Visitor")
                        .contentType("application/json")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        String expectedContent = objectMapper.writeValueAsString(searchUserDTOList);

        assertEquals(expectedContent, resultContent);


    }*/

    //Integration Test to Password

    @Test
    void changePasswordSuccessfuly() throws Exception {

        // Post method DTO
        UserDTO userDTO = new UserDTO();
        userDTO.userName = "oldName";
        userDTO.email = "random@email.com";
        userDTO.userFunction = "oldFunction";
        userDTO.password = "123456Aa@";

        // Patch method DTO
        ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO();
        changePasswordDTO.email = "random@email.com";
        changePasswordDTO.oldPassword = "123456Aa@";
        changePasswordDTO.newPassword = "123456ferAa@";

        // Expected output DTO
        UserDTO userDTOOpt = new UserDTO();
        userDTOOpt.email = "random@email.com";
        userDTOOpt.password = "081704c3220b810271b1a50f37e9589d";
        userDTOOpt.userName = "oldName";

        Link link = linkTo(methodOn(ImplUserController.class).getUsersByEmail(userDTO.email)).withSelfRel();
        userDTOOpt.add(link);

        // Creating the user
        mockMvc.perform(MockMvcRequestBuilders.post("/users/")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userDTO)));


        // Actual method being tested
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.patch("/users/password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(changePasswordDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        String expected = objectMapper.writeValueAsString(userDTOOpt);

        assertEquals(expected, resultContent);
    }

    @Test
    void changePasswordFailure() throws Exception {

        // Patch method DTO
        ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO();
        changePasswordDTO.email = "tRex@jurassicpark.pt";
        changePasswordDTO.oldPassword = "123456Aa@";
        changePasswordDTO.newPassword = "123456ferAa@";

        // Expected output DTO
        UserDTO userDTOOpt = new UserDTO();
        userDTOOpt.email = "random@email.com";
        userDTOOpt.password = "081704c3220b810271b1a50f37e9589d";
        userDTOOpt.userName = "oldName";

        // Actual method being tested
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.patch("/users/password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(changePasswordDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();


        assertEquals("Operation failed.", resultContent);
    }

    @Test
    void createUserSuccessfully() throws Exception {

        //Arrange
        UserDTO dto = new UserDTO();
        dto.setEmail("valter@gmail.com");
        dto.setPassword("Valter@123");
        dto.setUserName("ValterSousa");
        dto.setUserFunction("developer");
        dto.setCode("act1");
        dto.setActivation(false);

        //ACT
        mockMvc.perform(MockMvcRequestBuilders.post("/users/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    void createUserFailsInvalidUser() throws Exception {

        //Arrange
        UserDTO dto = new UserDTO();
        dto.setEmail("valter@gmail.com");
        dto.setPassword("Valter@123");
        dto.setUserName("valter@ousa");
        dto.setUserFunction("developer");
        dto.setCode("act1");
        dto.setActivation(false);

        //ACT
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/users/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isConflict())
                .andReturn();

        String expected = mvcResult.getResponse().getContentAsString();
        String actual = "Operation failed.";

        //Assert
        assertEquals(expected, actual);
    }
}
