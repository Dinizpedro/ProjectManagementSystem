package switchfive.project.interfaceAdapters.controllers.implControllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import switchfive.project.applicationServices.appServices.iappServices.IAppUserStoryService;
import switchfive.project.applicationServices.appServices.implAppServices.*;
import switchfive.project.dtos.*;

import javax.transaction.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ImplUserStoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @SpyBean
    ImplAppProjectService implAppProjectService;

    @SpyBean
    ImplAppCustomerService implAppCustomerService;

    @SpyBean
    ImplAppTypologyService implAppTypologyService;

    @SpyBean
    ImplAppUserService implAppUserService;

    @Mock
    IAppUserStoryService iAppUserStoryService;

    @InjectMocks
    ImplUserStoryController implUserStoryController;

    @Test
    void createUserStory_Success() throws Exception {
        //Arrange
        UserDTO newUserDTO = new UserDTO();
        newUserDTO.userName = "oldName";
        newUserDTO.email = "random@email.com";
        newUserDTO.userFunction = "oldFunction";
        newUserDTO.password = "123456Aa@";
        implAppUserService.createAndSaveUser(newUserDTO);
        implAppUserService.getUsersByEmail("random@email.com");

        implAppCustomerService.createAndSaveCustomer("internal");
        implAppTypologyService.addNewTypology("Fixed");

        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectName("ProjectX");
        projectDTO.setProjectDescription("Test purposes");
        projectDTO.setProjectBusinessSector("Engineering");
        projectDTO.setProjectNumberOfPlannedSprints(10);
        projectDTO.setProjectSprintDuration(2);
        projectDTO.setProjectBudget(1000);
        projectDTO.setStartDate("25/04/2023");
        projectDTO.setEndDate("25/04/2025");
        projectDTO.setUserEmail("random@email.com");
        projectDTO.setCostPerHour(10);
        projectDTO.setPercentageOfAllocation(100);
        projectDTO.setProjectCode("P0101");
        projectDTO.setTypologyDescription("Fixed");
        projectDTO.setCustomerName("internal");
        implAppProjectService.createAndSaveProject(projectDTO);

        CreateUserStoryDTO description = new CreateUserStoryDTO();
        description.setDescription("ABC");
        //Act
        mockMvc.perform(MockMvcRequestBuilders.post("/userStories/projectCode/{projectCode}",
                                "P0101")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(description))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void createUserStory_faillingNonExistingProject() throws Exception {
        UserDTO newUserDTO = new UserDTO();
        newUserDTO.userName = "oldName";
        newUserDTO.email = "random@email.com";
        newUserDTO.userFunction = "oldFunction";
        newUserDTO.password = "123456Aa@";
        implAppUserService.createAndSaveUser(newUserDTO);

        implAppCustomerService.createAndSaveCustomer("internal");
        implAppTypologyService.addNewTypology("Fixed");

        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectName("ProjectX");
        projectDTO.setProjectDescription("Test purposes");
        projectDTO.setProjectBusinessSector("Engineering");
        projectDTO.setProjectNumberOfPlannedSprints(10);
        projectDTO.setProjectSprintDuration(2);
        projectDTO.setProjectBudget(1000);
        projectDTO.setStartDate("25/04/2023");
        projectDTO.setEndDate("25/04/2025");
        projectDTO.setUserEmail("random@email.com");
        projectDTO.setCostPerHour(10);
        projectDTO.setPercentageOfAllocation(100);
        projectDTO.setProjectCode("P0202");
        projectDTO.setTypologyDescription("Fixed");
        projectDTO.setCustomerName("internal");
        implAppProjectService.createAndSaveProject(projectDTO);

        CreateUserStoryDTO description = new CreateUserStoryDTO();
        description.setDescription(RandomString.make(300));

        mockMvc.perform(MockMvcRequestBuilders.post("/userStories/projectCode/{projectCode}",
                                "P0202")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(description))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createUserStory_faillingInvalidDescription() throws Exception {
        //Arrange
        UserDTO newUserDTO = new UserDTO();
        newUserDTO.userName = "oldName";
        newUserDTO.email = "random@email.com";
        newUserDTO.userFunction = "oldFunction";
        newUserDTO.password = "123456Aa@";
        implAppUserService.createAndSaveUser(newUserDTO);

        implAppCustomerService.createAndSaveCustomer("internal");
        implAppTypologyService.addNewTypology("Fixed");

        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectName("ProjectX");
        projectDTO.setProjectDescription("Test purposes");
        projectDTO.setProjectBusinessSector("Engineering");
        projectDTO.setProjectNumberOfPlannedSprints(10);
        projectDTO.setProjectSprintDuration(2);
        projectDTO.setProjectBudget(1000);
        projectDTO.setStartDate("25/04/2023");
        projectDTO.setEndDate("25/04/2025");
        projectDTO.setUserEmail("random@email.com");
        projectDTO.setCostPerHour(10);
        projectDTO.setPercentageOfAllocation(100);
        projectDTO.setProjectCode("P0303");
        projectDTO.setTypologyDescription("Fixed");
        projectDTO.setCustomerName("internal");
        implAppProjectService.createAndSaveProject(projectDTO);

        CreateUserStoryDTO description = new CreateUserStoryDTO();
        description.setDescription("ABC");

        mockMvc.perform(MockMvcRequestBuilders.post("/userStories/projectCode/{projectCode}",
                        "P0303")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(description))
                .accept(MediaType.APPLICATION_JSON));

        //Act
        mockMvc.perform(MockMvcRequestBuilders.
                        get("/userStories/{userStoryCode}/projectCode/{projectCode}",
                                "US1", "P0303")
                        .contentType("application/json")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

    }

    @Test
    void getUserStoryNonExists() throws Exception {
        //Arrange
        UserDTO newUserDTO = new UserDTO();
        newUserDTO.userName = "oldName";
        newUserDTO.email = "random@email.com";
        newUserDTO.userFunction = "oldFunction";
        newUserDTO.password = "123456Aa@";
        implAppUserService.createAndSaveUser(newUserDTO);

        implAppCustomerService.createAndSaveCustomer("internal");
        implAppTypologyService.addNewTypology("Fixed");

        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectName("ProjectX");
        projectDTO.setProjectDescription("Test purposes");
        projectDTO.setProjectBusinessSector("Engineering");
        projectDTO.setProjectNumberOfPlannedSprints(10);
        projectDTO.setProjectSprintDuration(2);
        projectDTO.setProjectBudget(1000);
        projectDTO.setStartDate("25/04/2023");
        projectDTO.setEndDate("25/04/2025");
        projectDTO.setUserEmail("random@email.com");
        projectDTO.setCostPerHour(10);
        projectDTO.setPercentageOfAllocation(100);
        projectDTO.setProjectCode("P0404");
        projectDTO.setTypologyDescription("Fixed");
        projectDTO.setCustomerName("internal");
        implAppProjectService.createAndSaveProject(projectDTO);

        CreateUserStoryDTO description = new CreateUserStoryDTO();
        description.setDescription("ABC");

        mockMvc.perform(MockMvcRequestBuilders.post("/userStories/projectCode/{projectCode}",
                        "P0404")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(description))
                .accept(MediaType.APPLICATION_JSON));

        //Act
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.
                        get("/userStories/{userStoryCode}/projectCode/{projectCode}",
                                "US2", "P0404")
                        .contentType("application/json")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        String resultContent = result.getResponse().getContentAsString();

        assertEquals("User Story doesn't exists!", resultContent);
    }

    @Test
    void getUserStoryWithoutProject() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/userStories/{userStoryCode}/projectCode/{projectCode}",
                                "US2", "P0909")
                        .contentType("application/json")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getUserStorySuccess() throws Exception {

        UserStoryDTO userStoryDTO = new UserStoryDTO();
        userStoryDTO.setStatus("RUNNING");
        userStoryDTO.setDescription("ABC");
        userStoryDTO.setPriority(1);
        userStoryDTO.setCode("US1");
        userStoryDTO.setEffort(6);

        when(iAppUserStoryService.getUserStoryDTO(any(), any())).
                thenReturn(Optional.of(userStoryDTO));

        ResponseEntity<Object> actual = implUserStoryController.getUserStory(
                "P0103", "US1");

        assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    @DisplayName("Should return an OK response")
    void refineUserStoryTest() throws Exception {
        //Arrange
        UserDTO newUserDTO = new UserDTO();
        newUserDTO.userName = "oldName";
        newUserDTO.email = "random@email.com";
        newUserDTO.userFunction = "oldFunction";
        newUserDTO.password = "123456Aa@";
        implAppUserService.createAndSaveUser(newUserDTO);

        implAppCustomerService.createAndSaveCustomer("internal");
        implAppTypologyService.addNewTypology("Fixed");

        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectName("ProjectX");
        projectDTO.setProjectDescription("Test purposes");
        projectDTO.setProjectBusinessSector("Engineering");
        projectDTO.setProjectNumberOfPlannedSprints(10);
        projectDTO.setProjectSprintDuration(2);
        projectDTO.setProjectBudget(1000);
        projectDTO.setStartDate("25/04/2023");
        projectDTO.setEndDate("25/04/2025");
        projectDTO.setUserEmail("random@email.com");
        projectDTO.setCostPerHour(10);
        projectDTO.setPercentageOfAllocation(100);
        projectDTO.setProjectCode("P0101");
        projectDTO.setTypologyDescription("Fixed");
        projectDTO.setCustomerName("internal");
        implAppProjectService.createAndSaveProject(projectDTO);

        CreateUserStoryDTO description = new CreateUserStoryDTO();
        description.setDescription("ABC");

        RefineUserStoryDTO refineUserStoryDTO = new RefineUserStoryDTO();
        refineUserStoryDTO.setUserStoryCode("US1");
        refineUserStoryDTO.setProjectCode("P0101");

        ArrayList<String> newUserStoryDescriptions = new ArrayList<>();
        newUserStoryDescriptions.add("Another description");
        newUserStoryDescriptions.add("Other description");
        refineUserStoryDTO.setNewUserStoryDescription(newUserStoryDescriptions);


        //Act && Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/userStories/projectCode/{projectCode}",
                                "P0101")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(description))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.post("/userStories/refineUS")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(refineUserStoryDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("Parent US not present in Repo. Should return a Bad Request response")
    void refineUserStoryTestParentUSNotPresent() throws Exception {
        //Arrange
        UserDTO newUserDTO = new UserDTO();
        newUserDTO.userName = "oldName";
        newUserDTO.email = "random@email.com";
        newUserDTO.userFunction = "oldFunction";
        newUserDTO.password = "123456Aa@";
        implAppUserService.createAndSaveUser(newUserDTO);

        implAppCustomerService.createAndSaveCustomer("internal");
        implAppTypologyService.addNewTypology("Fixed");

        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectName("ProjectX");
        projectDTO.setProjectDescription("Test purposes");
        projectDTO.setProjectBusinessSector("Engineering");
        projectDTO.setProjectNumberOfPlannedSprints(10);
        projectDTO.setProjectSprintDuration(2);
        projectDTO.setProjectBudget(1000);
        projectDTO.setStartDate("25/04/2023");
        projectDTO.setEndDate("25/04/2025");
        projectDTO.setUserEmail("random@email.com");
        projectDTO.setCostPerHour(10);
        projectDTO.setPercentageOfAllocation(100);
        projectDTO.setProjectCode("P0101");
        projectDTO.setTypologyDescription("Fixed");
        projectDTO.setCustomerName("internal");
        implAppProjectService.createAndSaveProject(projectDTO);

        RefineUserStoryDTO refineUserStoryDTO = new RefineUserStoryDTO();
        refineUserStoryDTO.setUserStoryCode("US1000");
        refineUserStoryDTO.setProjectCode("P0101");

        ArrayList<String> newUserStoryDescriptions = new ArrayList<>();
        newUserStoryDescriptions.add("Another description");
        newUserStoryDescriptions.add("Other description");
        refineUserStoryDTO.setNewUserStoryDescription(newUserStoryDescriptions);


        //Act && Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/userStories/refineUS")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(refineUserStoryDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    @DisplayName("Project not present in Repo. Should return a Bad Request response")
    void refineUserStoryTestProjectDoesntExist() throws Exception {
        //Arrange
        UserDTO newUserDTO = new UserDTO();
        newUserDTO.userName = "oldName";
        newUserDTO.email = "random@email.com";
        newUserDTO.userFunction = "oldFunction";
        newUserDTO.password = "123456Aa@";
        implAppUserService.createAndSaveUser(newUserDTO);

        implAppCustomerService.createAndSaveCustomer("internal");
        implAppTypologyService.addNewTypology("Fixed");

        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectName("ProjectX");
        projectDTO.setProjectDescription("Test purposes");
        projectDTO.setProjectBusinessSector("Engineering");
        projectDTO.setProjectNumberOfPlannedSprints(10);
        projectDTO.setProjectSprintDuration(2);
        projectDTO.setProjectBudget(1000);
        projectDTO.setStartDate("25/04/2023");
        projectDTO.setEndDate("25/04/2025");
        projectDTO.setUserEmail("random@email.com");
        projectDTO.setCostPerHour(10);
        projectDTO.setPercentageOfAllocation(100);
        projectDTO.setProjectCode("P0101");
        projectDTO.setTypologyDescription("Fixed");
        projectDTO.setCustomerName("internal");
        implAppProjectService.createAndSaveProject(projectDTO);

        CreateUserStoryDTO description = new CreateUserStoryDTO();
        description.setDescription("ABC");

        RefineUserStoryDTO refineUserStoryDTO = new RefineUserStoryDTO();
        refineUserStoryDTO.setUserStoryCode("US1");
        refineUserStoryDTO.setProjectCode("P1111");

        ArrayList<String> newUserStoryDescriptions = new ArrayList<>();
        newUserStoryDescriptions.add("Another description");
        newUserStoryDescriptions.add("Other description");
        refineUserStoryDTO.setNewUserStoryDescription(newUserStoryDescriptions);


        //Act && Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/userStories/projectCode/{projectCode}",
                                "P0101")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(description))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.post("/userStories/refineUS")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(refineUserStoryDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    @DisplayName("Invalid input descriptions. Should return a Bad Request response")
    void refineUserStoryTestInvalidInputDescriptions() throws Exception {
        //Arrange
        UserDTO newUserDTO = new UserDTO();
        newUserDTO.userName = "oldName";
        newUserDTO.email = "random@email.com";
        newUserDTO.userFunction = "oldFunction";
        newUserDTO.password = "123456Aa@";
        implAppUserService.createAndSaveUser(newUserDTO);

        implAppCustomerService.createAndSaveCustomer("internal");
        implAppTypologyService.addNewTypology("Fixed");

        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectName("ProjectX");
        projectDTO.setProjectDescription("Test purposes");
        projectDTO.setProjectBusinessSector("Engineering");
        projectDTO.setProjectNumberOfPlannedSprints(10);
        projectDTO.setProjectSprintDuration(2);
        projectDTO.setProjectBudget(1000);
        projectDTO.setStartDate("25/04/2023");
        projectDTO.setEndDate("25/04/2025");
        projectDTO.setUserEmail("random@email.com");
        projectDTO.setCostPerHour(10);
        projectDTO.setPercentageOfAllocation(100);
        projectDTO.setProjectCode("P0101");
        projectDTO.setTypologyDescription("Fixed");
        projectDTO.setCustomerName("internal");
        implAppProjectService.createAndSaveProject(projectDTO);

        CreateUserStoryDTO description = new CreateUserStoryDTO();
        description.setDescription("ABC");

        RefineUserStoryDTO refineUserStoryDTO = new RefineUserStoryDTO();
        refineUserStoryDTO.setUserStoryCode("US1");
        refineUserStoryDTO.setProjectCode("P1111");

        ArrayList<String> newUserStoryDescriptions = new ArrayList<>();
        newUserStoryDescriptions.add("Another description");
        newUserStoryDescriptions.add("");
        refineUserStoryDTO.setNewUserStoryDescription(newUserStoryDescriptions);


        //Act && Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/userStories/projectCode/{projectCode}",
                                "P0101")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(description))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.post("/userStories/refineUS")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(refineUserStoryDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    void shouldReturnARespoEntityWithHTTPStatus200AndTheUSDTOWhenTheRequestIsValid() throws ParseException {
        MoveUserStoryDTO expectedDTO = new MoveUserStoryDTO();
        expectedDTO.setSprintID(1);
        expectedDTO.setProjectCode("P0001");
        Link linkToSelf = linkTo(methodOn(ImplUserStoryController.class).getUserStory(
                expectedDTO.getProjectCode(),
                "US1"))
                .withSelfRel()
                .withType("GET");

        Link linkToSprint = linkTo(methodOn(ImplSprintController.class).getSprintById(
                expectedDTO.getSprintID(),
                expectedDTO.getProjectCode()))
                .withRel("sprint")
                .withType("GET");

        expectedDTO.add(linkToSelf, linkToSprint);
        ResponseEntity<Object> expected = new ResponseEntity<>(expectedDTO, HttpStatus.OK);
        when(iAppUserStoryService.moveUSFromProductBacklogToSprintBacklog(
                anyString(),
                anyString(),
                any(MoveUserStoryDTO.class))).
                thenReturn(Optional.of(expectedDTO));

        ResponseEntity<Object> actual = implUserStoryController.moveUSFromProductBacklogToSprintBacklog(
                expectedDTO.getProjectCode(),
                "US1",
                expectedDTO);

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnARespoEntityWithHTTPStatus400WhenTheRequestIsInvalid() throws ParseException {
        String validProjectCode = "P0001";
        ResponseEntity<Object> expected = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        when(iAppUserStoryService.moveUSFromProductBacklogToSprintBacklog(
                anyString(),
                anyString(),
                any(MoveUserStoryDTO.class))).
                thenReturn(Optional.empty());

        ResponseEntity<Object> actual = implUserStoryController.moveUSFromProductBacklogToSprintBacklog(
                validProjectCode,
                "US1",
                null);

        assertEquals(expected, actual);
    }


    @Test
    void userStoryUpdateStatusWithSuccess() throws Exception {

        UserStoryStatusDTO newStatus = new UserStoryStatusDTO();
        newStatus.setNewStatus("RUNNING");

        UserStoryDTO userStoryDTO = new UserStoryDTO();
        userStoryDTO.setStatus("RUNNING");
        userStoryDTO.setDescription("ABC");
        userStoryDTO.setPriority(1);
        userStoryDTO.setCode("US1");
        userStoryDTO.setEffort(6);

        when(iAppUserStoryService.userStoryChangeStatus(any(), any(), any())).
                thenReturn(Optional.of(userStoryDTO));

       ResponseEntity<Object> actual = implUserStoryController.userStoryUpdateStatus("P0102",
               "US1", newStatus);

       assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    void userStoryUpdateStatusWithoutValidStatus() throws Exception {

        UserStoryStatusDTO newStatus = new UserStoryStatusDTO();
        newStatus.setNewStatus("UNFINISHED");

        when(iAppUserStoryService.userStoryChangeStatus(any(), any(), any())).
                thenReturn(Optional.empty());

        ResponseEntity<Object> actual = implUserStoryController.userStoryUpdateStatus("P0102",
                "US1", newStatus);

        assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
    }

    @Test
    void getUserStoryByPriorityWithoutProject() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/userStories/projectCode/{projectCode}",
                                "P0909")
                        .contentType("application/json")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getUserStoryByPrioritySuccess() throws Exception {

        UserStoryDTO userStoryDTO = new UserStoryDTO();
        userStoryDTO.setStatus("RUNNING");
        userStoryDTO.setDescription("ABC");
        userStoryDTO.setPriority(1);
        userStoryDTO.setCode("US1");
        userStoryDTO.setEffort(6);

        List<UserStoryDTO> actualList = new ArrayList<>();
        actualList.add(userStoryDTO);

        when(iAppUserStoryService.getUserStoryListByPriority(any())).
                thenReturn(actualList);

        ResponseEntity<Object> actual = implUserStoryController.
                getUserStoryByPriority("P0103");

        assertEquals(HttpStatus.OK, actual.getStatusCode());
    }
}
