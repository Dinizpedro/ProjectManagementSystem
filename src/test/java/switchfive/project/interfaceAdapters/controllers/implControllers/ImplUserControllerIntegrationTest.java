package switchfive.project.interfaceAdapters.controllers.implControllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import switchfive.project.dtos.CustomerDTO;
import switchfive.project.dtos.ProjectDTO;
import switchfive.project.dtos.TypologyDTO;
import switchfive.project.dtos.UserDTO;
import switchfive.project.applicationServices.appServices.implAppServices.ImplAppUserService;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ImplUserControllerIntegrationTest {

    @SpyBean
    ImplAppUserService userService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getUsersByEmail_Succesfully() throws Exception {

        // Arrange
        UserDTO dtoOne = new UserDTO();
        dtoOne.userName = "oldName";
        dtoOne.email = "randomOne@email.com";
        dtoOne.userFunction = "oldFunction";
        dtoOne.password = "123456Aa@";

        UserDTO dtoTwo = new UserDTO();
        dtoTwo.userName = "oldName";
        dtoTwo.email = "randomTwo@email.com";
        dtoTwo.userFunction = "oldFunction";
        dtoTwo.password = "123456Aa@";

        userService.createAndSaveUser(dtoOne);
        userService.createAndSaveUser(dtoTwo);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/users/email/{email}",
                        "random").
                contentType("application/json")
                .content(objectMapper.writeValueAsString(null))
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }


    @Test
    void getUsersByProfile_Succesfully() throws Exception {
        // Arrange
        UserDTO dtoOne = new UserDTO();
        dtoOne.userName = "oldName";
        dtoOne.email = "randomOne@email.com";
        dtoOne.userFunction = "oldFunction";
        dtoOne.password = "123456Aa@";

        UserDTO dtoTwo = new UserDTO();
        dtoTwo.userName = "oldName";
        dtoTwo.email = "randomTwo@email.com";
        dtoTwo.userFunction = "oldFunction";
        dtoTwo.password = "123456Aa@";

        userService.createAndSaveUser(dtoOne);
        userService.createAndSaveUser(dtoTwo);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/users/profile/{profile}",
                        "visitor").
                contentType("application/json")
                .content(objectMapper.writeValueAsString(null))
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void getUserByEmail_Succesfully_UserFound() throws Exception {
        // Arrange
        UserDTO dtoOne = new UserDTO();
        dtoOne.userName = "oldName";
        dtoOne.email = "randomOne@email.com";
        dtoOne.userFunction = "oldFunction";
        dtoOne.password = "123456Aa@";


        userService.createAndSaveUser(dtoOne);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/users?email={email}",
                        "randomOne@email.com").
                contentType("application/json")
                .content(objectMapper.writeValueAsString(null))
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void getUserByEmail_Failling_NoUserFound() throws Exception {
        // Arrange
        UserDTO dtoOne = new UserDTO();
        dtoOne.userName = "oldName";
        dtoOne.email = "randomOne@email.com";
        dtoOne.userFunction = "oldFunction";
        dtoOne.password = "123456Aa@";


        userService.createAndSaveUser(dtoOne);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/users?email={email}",
                        "whatever@email.com").
                contentType("application/json")
                .content(objectMapper.writeValueAsString(null))
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
    }

    @Test
    void getAllUserStatus_Succesfully() throws Exception {
        // Arrange
        UserDTO dtoOne = new UserDTO();
        dtoOne.userName = "oldName";
        dtoOne.email = "randomOne@email.com";
        dtoOne.userFunction = "oldFunction";
        dtoOne.password = "123456Aa@";


        userService.createAndSaveUser(dtoOne);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/users/status").
                contentType("application/json")
                .content(objectMapper.writeValueAsString(null))
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void getAllUser_Succesfully() throws Exception {
        // Arrange


        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/users/allUsers").
                contentType("application/json")
                .content(objectMapper.writeValueAsString(null))
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void shouldReturnIsOkStatusWhenGetAllocatedProjectsSuccess() throws Exception {

        String projectCode = RandomStringUtils.random(5, true, true);
        String startDate = "25/04/2023";
        String endDate = "25/04/2025";

        TypologyDTO typologyDTO = new TypologyDTO();
        String for_free = "For free";
        typologyDTO.setDescription(for_free);

        String maranello_SA = "Maranello, SA";
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerName(maranello_SA);

        String pm_email = "7571121@isep.ipp.pt";
        UserDTO projectManager = new UserDTO();
        projectManager.userName = "DanielTorres";
        projectManager.email = pm_email;
        projectManager.userFunction = "projectmanager";
        projectManager.password = "123456Aa@";

        ProjectDTO creationDTO = new ProjectDTO();
        creationDTO.setProjectCode(projectCode);
        creationDTO.setProjectName("My First Project");
        creationDTO.setProjectDescription("To Test Project Creation");
        creationDTO.setProjectBusinessSector("Educational ");
        creationDTO.setProjectNumberOfPlannedSprints(9);
        creationDTO.setProjectSprintDuration(2);
        creationDTO.setProjectBudget(1000.0);
        creationDTO.setStartDate(startDate);
        creationDTO.setEndDate(endDate);
        creationDTO.setTypologyDescription(for_free);
        creationDTO.setCustomerName(maranello_SA);
        creationDTO.setCostPerHour(10);
        creationDTO.setUserEmail(pm_email);

        //Act and Assert

        mockMvc.perform(MockMvcRequestBuilders.post("/api/typologies")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(typologyDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/customers")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(customerDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.post("/users/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(projectManager))
                        .accept(MediaTypes.HAL_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/projects")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(creationDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.get("/users/{email}/projects",
                                projectManager.email)
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnBadRequestStatusWhenEmailIsInvalidInGetAllocatedProjects() throws Exception {

        //Arrange
        String email = "invalidEmail";

        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{email}/projects",
                                email)
                        .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnIsOkStatusWhenUserDoesNotExistInGetAllocatedProjects() throws Exception {

        //Arrange
        String email = "userDoesNotExist@Email.com";

        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{email}/projects",
                                email)
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnIsOkStatusWhenUserIsNotAllocatedToAnyProjectInGetAllocatedProjects() throws Exception {

        String pm_email = "7571121@isep.ipp.pt";
        UserDTO userDTO = new UserDTO();
        userDTO.userName = "DanielTorres";
        userDTO.email = pm_email;
        userDTO.userFunction = "projectmanager";
        userDTO.password = "123456Aa@";

        //Act

        mockMvc.perform(MockMvcRequestBuilders.post("/users/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(userDTO))
                        .accept(MediaTypes.HAL_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.get("/users/{email}/projects",
                                userDTO.email)
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

}
