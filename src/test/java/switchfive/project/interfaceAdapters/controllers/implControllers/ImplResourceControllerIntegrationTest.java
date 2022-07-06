package switchfive.project.interfaceAdapters.controllers.implControllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import switchfive.project.applicationServices.appServices.implAppServices.ImplAppResourceService;
import switchfive.project.applicationServices.appServices.implAppServices.ImplAppTaskService;
import switchfive.project.domain.shared.valueObjects.Role;
import switchfive.project.dtos.*;
import switchfive.project.dtos.ResourceCreationDTO;


import javax.transaction.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ImplResourceControllerIntegrationTest {

    @Mock
    ImplAppResourceService resourceService;
    @InjectMocks
    ImplResourceController resourceController;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void definedTeamMemberOfProject() throws Exception {
        // Arrange
        ResourceCreationDTO resourceCreationDTO = new ResourceCreationDTO();
        resourceCreationDTO.userIdDto = "as@mymail.com";
        resourceCreationDTO.projectCodeDto = "A0001";
        resourceCreationDTO.startDateDto = "27/05/2023";
        resourceCreationDTO.endDateDto = "27/06/2023";
        resourceCreationDTO.costPerHourDto = 50;
        resourceCreationDTO.percentageOfAllocationDto = 50;

        //Act
        mockMvc.perform(MockMvcRequestBuilders.post("/api/resources/teamMember")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(resourceCreationDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void definedTeamMemberOfProjectFail() throws Exception {
        // Arrange
        ResourceCreationDTO resourceCreationDTO = new ResourceCreationDTO();
        resourceCreationDTO.userIdDto = "as@mymail.com";
        resourceCreationDTO.projectCodeDto = "A0001";
        resourceCreationDTO.startDateDto = "27/05/2023";
        resourceCreationDTO.endDateDto = "27/06/2023";
        resourceCreationDTO.costPerHourDto = 50;
        resourceCreationDTO.percentageOfAllocationDto = 50;

        //Act
        mockMvc.perform(MockMvcRequestBuilders.post("/api/resources/teamMember")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(resourceCreationDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        //Act
        mockMvc.perform(MockMvcRequestBuilders.post("/api/resources/teamMember")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(resourceCreationDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getResourceNotFound() throws Exception {
        //Act
        mockMvc.perform(MockMvcRequestBuilders.get("/api/resources/54820d16-fb30-11ec-b939-0242ac120002"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getResourceIDObjFail() throws Exception {
        //Act
        mockMvc.perform(MockMvcRequestBuilders.get("/api/resources/12314235245fghjklxcv" +
                        "bnm,sldfuiahsdfiohasfjasiofjas53251412odfijpdfjaspfioasjdfpoasidjkfmasdokflçma" +
                        "sdfçasdkfjmasiodfjasmdo+fkamsldf+askdfa"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Must return a Response Entity of an HTTPStatus.BadRequest when all human resources of a project are not succesfully fetched.")
    void listOfAllHumanResourcesIsNotSuccessfullyFetched_ProjectDoesNotExist() throws Exception {

        //Act
        MvcResult result =
                mockMvc.perform(MockMvcRequestBuilders.get("/api/resources/projectCode/{projectCode}/", "B0009")
                                .contentType("application/json")
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest()).andReturn();

        //Assert
        String expected = "Selected project does not exist!";
        String actual = result.getResponse().getContentAsString();

        assertEquals(expected, actual);

    }

    @Test
    @DisplayName("Must return a Response Entity of an HTTPStatus.Ok when all human resources of a project are successfully found.")
    void getAllHumanResourceOfAProjectSuccessfullyWithResponseHTTPStatusOK() throws ParseException {

        // Arrange
        RepresentationModel<ResourceDTO> resourcesLinks = new RepresentationModel<>();
        List<ResourceDTO> resourceDTOList = new ArrayList<>();

        String resourceID = "725b4690-f75d-4e15-9aa2-4f78e8c82227";
        String projectCode = "A0666";
        String userID = "to.f@mymail.com";
        String startDate = "10/03/2023";
        String endDate = "29/04/2024";
        TimeDTO time = new TimeDTO(startDate, endDate);
        double cost = 20;
        double allocation = 100;
        String role = Role.TEAM_MEMBER.toString();


        ResourceDTO resourceDTO = new ResourceDTO(resourceID, projectCode, userID, time, cost, allocation, role);

        String resourceIDTwo = "f00adc4f-96ed-48c1-8026-242a6a5ab00e";
        String projectCodeTwo = "A0666";
        String userIDTwo = "tdc@mymail.com";
        String startDateTwo = "11/03/2023";
        String endDateTwo = "29/04/2024";
        TimeDTO timeTwo = new TimeDTO(startDateTwo, endDateTwo);
        double costTwo = 20;
        double allocationTwo = 100;
        String roleTwo = Role.TEAM_MEMBER.toString();

        ResourceDTO resourceDTOTwo = new ResourceDTO(resourceIDTwo, projectCodeTwo, userIDTwo, timeTwo, costTwo, allocationTwo, roleTwo);


        resourceDTOList.add(resourceDTO);
        resourceDTOList.add(resourceDTOTwo);


        when(resourceService.getResourcesByProjectCode("A0666")).thenReturn(Optional.of(resourceDTOList));

        Link link = linkTo(methodOn(ImplResourceController.class).getResource(resourceDTO.resourceID)).withRel(resourceDTO.resourceID).withType("GET");

        Link otherlink = linkTo(methodOn(ImplResourceController.class).getResource(resourceDTOTwo.resourceID)).withRel(resourceDTOTwo.resourceID).withType("GET");


        resourcesLinks.add(link);
        resourcesLinks.add(otherlink);

        // Act

        ResponseEntity<Object> expected = new ResponseEntity<>(resourcesLinks, HttpStatus.OK);
        ResponseEntity<Object> actual = resourceController.getAllResourcesByProjectCode("A0666");


        // Assert

        assertEquals(expected, actual);

    }
}

