package switchfive.project.interfaceAdapters.controllers.implControllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import switchfive.project.dtos.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ImplProjectControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void projectIsSuccessfullyCreated() throws Exception {
        // Arrange
        String projectCode = RandomStringUtils.random(5,true,true);
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

        //Act
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
    }

    @Test
    void projectCreationFailsBecauseTypologyDoesNotExits() throws Exception {
        // Arrange
        String projectCode = RandomStringUtils.random(5,true,true);
        String startDate = "25/04/2023";
        String endDate = "25/04/2025";

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
        creationDTO.setTypologyDescription("Non existent");
        creationDTO.setCustomerName(maranello_SA);
        creationDTO.setCostPerHour(10);
        creationDTO.setUserEmail(pm_email);

        //Act

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
                .andExpect(status().isBadRequest());
    }

    @Test
    void projectCreationFailsBecauseCustomerDoesNotExits() throws Exception {
        // Arrange
        String projectCode = RandomStringUtils.random(5,true,true);
        String startDate = "25/04/2023";
        String endDate = "25/04/2025";

        TypologyDTO typologyDTO = new TypologyDTO();
        String for_free = "For free";
        typologyDTO.setDescription(for_free);

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
        creationDTO.setCustomerName("Non existent");
        creationDTO.setCostPerHour(10);
        creationDTO.setUserEmail(pm_email);

        //Act
        mockMvc.perform(MockMvcRequestBuilders.post("/api/typologies")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(typologyDTO))
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
                .andExpect(status().isBadRequest());
    }

    @Test
    void projectCreationFailsBecauseUserDoesNotExits() throws Exception {
        // Arrange
        String projectCode = RandomStringUtils.random(5,true,true);
        String startDate = "25/04/2023";
        String endDate = "25/04/2025";

        TypologyDTO typologyDTO = new TypologyDTO();
        String for_free = "For free";
        typologyDTO.setDescription(for_free);

        String maranello_SA = "Maranello, SA";
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerName(maranello_SA);

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
        creationDTO.setUserEmail("not-exist@isep.ipp.pt");

        //Act
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

        mockMvc.perform(MockMvcRequestBuilders.post("/api/projects")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(creationDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void statusOfActivitiesIsNotSuccessfullyFetched_ProjectDoesNotExist() throws Exception {

        //Act
        MvcResult result =
                mockMvc.perform(MockMvcRequestBuilders.get("/api/projects/{projectCode}/activities","B0009")
                        .contentType("application/json")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn();

        //Assert
        String expected = "Project doesn't exist!";
        String actual = result.getResponse().getContentAsString();

        assertEquals(expected, actual);

    }

    @Test
    void activitiesStatusSuccessfullyFetched() throws Exception {
        // --- ARRANGE ---
        SprintDTO sprintDTO = new SprintDTO();
        sprintDTO.setProjectCode("A0666");
        sprintDTO.setSprintNumber(0);
        sprintDTO.setStartDate("27/04/2023");
        sprintDTO.setEndDate("27/06/2023");
        sprintDTO.setDescription("First Sprint");
        sprintDTO.setStatus("RUNNING");

        CreateUserStoryDTO createUserStoryDTO = new CreateUserStoryDTO();
        createUserStoryDTO.setDescription("ABC");

        MoveUserStoryDTO userStoryDTO = new MoveUserStoryDTO();
        userStoryDTO.setProjectCode("A0666");
        userStoryDTO.setCode("US1");
        userStoryDTO.setPriority(1);
        userStoryDTO.setDescription("ABC");
        userStoryDTO.setSprintID(1);
        userStoryDTO.setStatus("PLANNED");


        mockMvc.perform(MockMvcRequestBuilders.post("/userStories/projectCode/{projectCode}",
                        userStoryDTO.getProjectCode())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(createUserStoryDTO)));


        //Create ActivityDTO and add to list

        Link linkUS = linkTo(methodOn(ImplUserStoryController.class)
                .getUserStory("A0666",
                        userStoryDTO.getCode())).withSelfRel();


        ActivityDTO activityDTOUS = new ActivityDTO();
        activityDTOUS.add(linkUS);
        activityDTOUS.setActivityStatus(userStoryDTO.getStatus());
        activityDTOUS.setTypeOfActivity("User Story");
        activityDTOUS.setActivityCode(userStoryDTO.getCode());

        List<ActivityDTO> activitiesDTO = new ArrayList<>();
        activitiesDTO.add(activityDTOUS);

        // --- ACT ----

        MvcResult result =
                mockMvc.perform(MockMvcRequestBuilders.get("/api/projects/{projectCode}/activities","A0666")
                                .contentType("application/json")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk()).andReturn();

        String expected = objectMapper.writeValueAsString(activitiesDTO);
        String actual = result.getResponse().getContentAsString();

        assertEquals(expected, actual);


    }



}
