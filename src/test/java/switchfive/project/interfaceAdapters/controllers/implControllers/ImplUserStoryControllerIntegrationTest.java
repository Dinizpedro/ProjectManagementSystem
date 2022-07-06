package switchfive.project.interfaceAdapters.controllers.implControllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import switchfive.project.dtos.CreateUserStoryDTO;
import switchfive.project.dtos.MoveUserStoryDTO;
import switchfive.project.dtos.SprintDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = HypermediaAutoConfiguration.class)
class ImplUserStoryControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnTheMovedUSAsADTOAndAnHTTPStatus200() throws Exception {
        SprintDTO sprintDTO = new SprintDTO();
        sprintDTO.setProjectCode("A0666");
        sprintDTO.setSprintNumber(0);
        sprintDTO.setStartDate("27/04/2023");
        sprintDTO.setEndDate("27/06/2023");
        sprintDTO.setDescription("First Sprint");
        sprintDTO.setStatus("RUNNING");

        CreateUserStoryDTO createUserStoryDTO = new CreateUserStoryDTO();
        createUserStoryDTO.setDescription("ABC");

        MoveUserStoryDTO expectedDTO = new MoveUserStoryDTO();
        expectedDTO.setProjectCode("A0666");
        expectedDTO.setCode("US1");
        expectedDTO.setPriority(1);
        expectedDTO.setDescription("ABC");
        expectedDTO.setSprintID(1);
        expectedDTO.setStatus("PLANNED");

        Link linkToSelf = linkTo(methodOn(ImplUserStoryController.class).getUserStory(
                expectedDTO.getProjectCode(),
                expectedDTO.getCode()))
                .withSelfRel();

        Link linkToSprint = linkTo(methodOn(ImplSprintController.class).getSprintById(
                expectedDTO.getSprintID(),
                expectedDTO.getProjectCode()))
                .withRel("sprint");

        expectedDTO.add(linkToSelf, linkToSprint);

        mockMvc.perform(MockMvcRequestBuilders.post("/userStories/projectCode/{projectCode}",
                        expectedDTO.getProjectCode())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(createUserStoryDTO)));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/sprints/projectCode/{projectCode}",
                        expectedDTO.getProjectCode())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(sprintDTO)));

        MvcResult result =
                mockMvc.perform(MockMvcRequestBuilders.patch("/projects/{projectCode}/productBacklog/{userStoryCode}",
                                        expectedDTO.getProjectCode(),
                                        expectedDTO.getCode())
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(expectedDTO))
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        String expected = objectMapper.writeValueAsString(expectedDTO);

        assertEquals(expected, resultContent);
    }

    @Test
    void shouldReturnAnHTTPStatus400WhenTheProjectDoesNotExist() throws Exception {
        SprintDTO sprintDTO = new SprintDTO();
        sprintDTO.setProjectCode("A0666");
        sprintDTO.setSprintNumber(0);
        sprintDTO.setStartDate("27/04/2023");
        sprintDTO.setEndDate("27/06/2023");
        sprintDTO.setDescription("abacate");
        sprintDTO.setStatus("running");

        CreateUserStoryDTO description = new CreateUserStoryDTO();
        description.setDescription("ABC");

        MoveUserStoryDTO expectedDTO = new MoveUserStoryDTO();
        expectedDTO.setSprintID(1);
        expectedDTO.setProjectCode("A0666");
        expectedDTO.setCode("US1");

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

        mockMvc.perform(MockMvcRequestBuilders.post("/userStories/projectCode/A0666")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(description)));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/sprints/projectCode/A0666")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(sprintDTO)));

        mockMvc.perform(MockMvcRequestBuilders.patch("/projects/{projectCode}/productBacklog/{userStoryCode}",
                                "ABD02", "US1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(expectedDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    void shouldReturnAnHTTPStatus400WhenTheSprintDoesNotExist() throws Exception {
        SprintDTO sprintDTO = new SprintDTO();
        sprintDTO.setProjectCode("A0666");
        sprintDTO.setSprintNumber(0);
        sprintDTO.setStartDate("27/04/2023");
        sprintDTO.setEndDate("27/06/2023");
        sprintDTO.setDescription("abacate");
        sprintDTO.setStatus("running");

        CreateUserStoryDTO description = new CreateUserStoryDTO();
        description.setDescription("ABC");

        MoveUserStoryDTO expectedDTO = new MoveUserStoryDTO();
        expectedDTO.setSprintID(-10);
        expectedDTO.setProjectCode("A0666");
        expectedDTO.setCode("US1");

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

        mockMvc.perform(MockMvcRequestBuilders.post("/userStories/projectCode/A0666")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(description)));


        mockMvc.perform(MockMvcRequestBuilders.post("/api/sprints/projectCode/A0666")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(sprintDTO)));

        mockMvc.perform(MockMvcRequestBuilders.patch("/projects/{projectCode}/productBacklog/{userStoryCode}",
                                "A0666", "US1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(expectedDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    void shouldReturnAnHTTPStatus400WhenTheUserStoryNotExist() throws Exception {
        SprintDTO sprintDTO = new SprintDTO();
        sprintDTO.setProjectCode("A0666");
        sprintDTO.setSprintNumber(0);
        sprintDTO.setStartDate("27/04/2023");
        sprintDTO.setEndDate("27/06/2023");
        sprintDTO.setDescription("abacate");
        sprintDTO.setStatus("running");

        CreateUserStoryDTO description = new CreateUserStoryDTO();
        description.setDescription("ABC");

        MoveUserStoryDTO expectedDTO = new MoveUserStoryDTO();
        expectedDTO.setSprintID(-10);
        expectedDTO.setProjectCode("A0666");
        expectedDTO.setCode("US1");

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

        mockMvc.perform(MockMvcRequestBuilders.post("/userStories/projectCode/A0666")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(description)));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/sprints/projectCode/A0666")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(sprintDTO)));

        mockMvc.perform(MockMvcRequestBuilders.patch("/projects/{projectCode}/productBacklog/{userStoryCode}",
                                "A0666", "US500")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(expectedDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn();
    }
}
