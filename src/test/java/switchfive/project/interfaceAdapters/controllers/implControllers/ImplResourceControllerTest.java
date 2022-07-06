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
import switchfive.project.applicationServices.appServices.iappServices.IAppResourceService;
import switchfive.project.domain.shared.valueObjects.ProjectCode;
import switchfive.project.domain.shared.valueObjects.ResourceID;
import switchfive.project.domain.shared.valueObjects.Role;
import switchfive.project.dtos.ResourceCreationDTO;
import switchfive.project.dtos.ResourceDTO;
import switchfive.project.dtos.TimeDTO;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@ExtendWith(MockitoExtension.class)
class ImplResourceControllerTest {
    @Mock
    IAppResourceService iAppResourceService;

    @InjectMocks
    ImplResourceController implResourceController;

    @Test
    void definedTeamMemberOfAProjectSucessfully() throws ParseException, NoSuchAlgorithmException {
        // Arrange

        String startDate = "27/05/2025";
        String endDate = "27/05/2030";
        ResourceCreationDTO dto = new ResourceCreationDTO("email@gmail.com", "TEAM2", startDate, endDate, 10, 100);
        ResourceDTO resourceDTO = mock(ResourceDTO.class);
        resourceDTO.resourceID = "resourceID";

        when(iAppResourceService.definedTeamMemberOfAProject(dto))
                .thenReturn(Optional.of(resourceDTO));

        ResponseEntity<Object> expectedResponseEntity =
                new ResponseEntity<>(resourceDTO, HttpStatus.CREATED);

        // Act
        ResponseEntity<Object> resultResponseEntity =
                implResourceController.definedTeamMemberOfAProject(dto);

        // Assert
        assertEquals(expectedResponseEntity, resultResponseEntity);
    }

    @Test
    void definedTeamMemberOfAProjectFails() throws ParseException, NoSuchAlgorithmException {
        // Arrange

        String startDate = "27/05/2025";
        String endDate = "27/05/2030";
        ResourceCreationDTO dto = new ResourceCreationDTO("email@gmail.com", "TEAM2", startDate, endDate, 10, 100);

        when(iAppResourceService.definedTeamMemberOfAProject(dto)).thenReturn(Optional.empty());

        ResponseEntity<Object> expectedResponseEntity = new ResponseEntity<>("Resource team member creation failed.", HttpStatus.NOT_FOUND);

        // Act
        ResponseEntity<Object> resultResponseEntity = implResourceController.definedTeamMemberOfAProject(dto);

        // Assert
        assertEquals(expectedResponseEntity, resultResponseEntity);
    }

    @Test
    void getResource() throws ParseException {
        //Arrange
        ResourceDTO resourceDTO = mock(ResourceDTO.class);
        resourceDTO.resourceID = "resourceID";

        when(iAppResourceService.getResourceDTO(resourceDTO.resourceID)).thenReturn(Optional.of(resourceDTO));

        ResponseEntity<Object> expectedResponseEntity = new ResponseEntity<>(resourceDTO, HttpStatus.OK);

        //Act
        ResponseEntity<Object> resultResponseEntity = implResourceController.getResource(resourceDTO.resourceID);

        //Assert
        assertEquals(expectedResponseEntity, resultResponseEntity);
    }

    @Test
    void getResourceFailsNotInRepo() throws ParseException {
        //Arrange

        ResourceDTO resourceDTO = mock(ResourceDTO.class);
        resourceDTO.resourceID = "resourceID";

        when(iAppResourceService.getResourceDTO(resourceDTO.resourceID))
                .thenReturn(Optional.empty());

        ResponseEntity<Object> expectedResponseEntity =
                new ResponseEntity<>("Resource not found in Repository",
                        HttpStatus.NOT_FOUND);

        //Act
        ResponseEntity<Object> resultResponseEntity = implResourceController
                .getResource(resourceDTO.resourceID);

        //Assert
        assertEquals(expectedResponseEntity, resultResponseEntity);
    }

    @Test
    void definedProductOwnerOfAProjectSucessfully() throws ParseException, NoSuchAlgorithmException {
        // Arrange

        String startDate = "27/05/2025";
        String endDate = "27/05/2030";
        ResourceCreationDTO dto = new ResourceCreationDTO("email@gmail.com", "TEAM2", startDate, endDate, 10, 100);
        ResourceDTO resourceDTO = mock(ResourceDTO.class);
        resourceDTO.resourceID = "resourceID";


        when(iAppResourceService.definedProductOwnerOfAProject(dto)).thenReturn(Optional.of(resourceDTO));


        ResponseEntity<Object> expectedResponseEntity = new ResponseEntity<>(resourceDTO, HttpStatus.CREATED);

        // Act
        ResponseEntity<Object> resultResponseEntity = implResourceController.definedProductOwnerOfAProject(dto);

        // Assert
        assertEquals(expectedResponseEntity, resultResponseEntity);
    }

    @Test
    void definedProductOwnerOfAProjectFail() throws ParseException, NoSuchAlgorithmException {

        // Arrange
        String startDate = "27/05/2025";
        String endDate = "27/05/2030";
        ResourceCreationDTO dto = new ResourceCreationDTO("email@gmail.com", "TEAM2", startDate, endDate, 10, 100);

        when(iAppResourceService.definedProductOwnerOfAProject(dto)).thenReturn(Optional.empty());

        ResponseEntity<Object> expectedResponseEntity = new ResponseEntity<>("Resource product owner creation failed.", HttpStatus.NOT_FOUND);

        // Act
        ResponseEntity<Object> resultResponseEntity = implResourceController.definedProductOwnerOfAProject(dto);

        // Assert
        assertEquals(expectedResponseEntity, resultResponseEntity);
    }

    @Test
    void definedScrumMasterOfAProjectSucessfully() throws ParseException, NoSuchAlgorithmException {
        // Arrange

        String startDate = "27/05/2025";
        String endDate = "27/05/2030";
        ResourceCreationDTO dto = new ResourceCreationDTO("email@gmail.com", "TEAM2", startDate, endDate, 10, 100);
        ResourceDTO resourceDTO = mock(ResourceDTO.class);
        resourceDTO.resourceID = "resourceID";
        when(iAppResourceService.definedScrumMasterOfAProject(dto)).thenReturn(Optional.of(resourceDTO));


        ResponseEntity<Object> expectedResponseEntity = new ResponseEntity<>(resourceDTO, HttpStatus.CREATED);

        // Act
        ResponseEntity<Object> resultResponseEntity = implResourceController.definedScrumMasterOfAProject(dto);

        // Assert
        assertEquals(expectedResponseEntity, resultResponseEntity);
    }

    @Test
    void definedScrumMasterOfAProjectFail() throws ParseException, NoSuchAlgorithmException {
        // Arrange

        String startDate = "27/05/2025";
        String endDate = "27/05/2030";
        ResourceCreationDTO dto = new ResourceCreationDTO("email@gmail.com", "TEAM2", startDate, endDate, 10, 100);

        when(iAppResourceService.definedScrumMasterOfAProject(dto)).thenReturn(Optional.empty());

        ResponseEntity<Object> expectedResponseEntity = new ResponseEntity<>("Resource scrum master creation failed.", HttpStatus.NOT_FOUND);

        // Act
        ResponseEntity<Object> resultResponseEntity = implResourceController.definedScrumMasterOfAProject(dto);

        // Assert
        assertEquals(expectedResponseEntity, resultResponseEntity);
    }

    @Test
    void getResourcesException() throws ParseException {
        // Arrange
        IllegalArgumentException e = new IllegalArgumentException();
        ResourceID resourceID = ResourceID.createResourceID();
        when(iAppResourceService.getResourceDTO(resourceID.toString()))
                .thenThrow(e);

        ResponseEntity<Object> expectedResponseEntity =
                new ResponseEntity<>(
                        HttpStatus.BAD_REQUEST);

        // Act
        ResponseEntity<Object> resultResponseEntity =
                implResourceController.getResource(resourceID.toString());

        // Assert
        assertEquals(expectedResponseEntity, resultResponseEntity);
    }

    @Test
    void definedTeamMemberException() throws ParseException, NoSuchAlgorithmException {
        // Arrange
        IllegalArgumentException e = new IllegalArgumentException();
        ResourceCreationDTO resourceCreationDTO = mock(ResourceCreationDTO.class);
        when(iAppResourceService.definedTeamMemberOfAProject(resourceCreationDTO))
                .thenThrow(e);

        ResponseEntity<Object> expectedResponseEntity =
                new ResponseEntity<>(
                        HttpStatus.BAD_REQUEST);

        // Act
        ResponseEntity<Object> resultResponseEntity =
                implResourceController.definedTeamMemberOfAProject(resourceCreationDTO);

        // Assert
        assertEquals(expectedResponseEntity, resultResponseEntity);
    }

    @Test
    void definedProductOwnerException() throws ParseException, NoSuchAlgorithmException {
        // Arrange
        IllegalArgumentException e = new IllegalArgumentException();
        ResourceCreationDTO resourceCreationDTO = mock(ResourceCreationDTO.class);
        when(iAppResourceService.definedProductOwnerOfAProject(resourceCreationDTO))
                .thenThrow(e);

        ResponseEntity<Object> expectedResponseEntity =
                new ResponseEntity<>(
                        HttpStatus.BAD_REQUEST);

        // Act
        ResponseEntity<Object> resultResponseEntity =
                implResourceController.definedProductOwnerOfAProject(resourceCreationDTO);

        // Assert
        assertEquals(expectedResponseEntity, resultResponseEntity);
    }

    @Test
    void definedScrumMasterException() throws ParseException, NoSuchAlgorithmException {
        // Arrange
        IllegalArgumentException e = new IllegalArgumentException();
        ResourceCreationDTO resourceCreationDTO = mock(ResourceCreationDTO.class);
        when(iAppResourceService.definedScrumMasterOfAProject(resourceCreationDTO))
                .thenThrow(e);

        ResponseEntity<Object> expectedResponseEntity =
                new ResponseEntity<>(
                        HttpStatus.BAD_REQUEST);

        // Act
        ResponseEntity<Object> resultResponseEntity =
                implResourceController.definedScrumMasterOfAProject(resourceCreationDTO);

        // Assert
        assertEquals(expectedResponseEntity, resultResponseEntity);
    }

    @Test
    void getAllResourcesByProjectCode() throws ParseException {

        // Arrange
        List<ResourceDTO> resourceDTOList = new ArrayList<>();
        ProjectCode code = ProjectCode.create("DANI1");
        String resourceID = ResourceID.createResourceID().toString();
        String userID = "benfica@gmail.com";
        String projectCode = "DANI1";
        TimeDTO dates = new TimeDTO("27/02/2023", "27/02/2024");
        double costPerHour = 50;
        double allocation = 50;
        String role = Role.TEAM_MEMBER.toString();

        ResourceDTO resourceDTO = new ResourceDTO(resourceID, userID, projectCode, dates, costPerHour, allocation, role);

        Link expectedLink = linkTo(methodOn(ImplResourceController.class)
                .getResource(resourceDTO.resourceID)).withRel(resourceDTO.resourceID).withType("GET");
        resourceDTO.add(expectedLink);
        resourceDTOList.add(resourceDTO);

        when(iAppResourceService.getResourcesByProjectCode(code.getCode()))
                .thenReturn(Optional.of(resourceDTOList));

        RepresentationModel<ResourceDTO> resourceDTORepresentationModel =
                new RepresentationModel<>();
        resourceDTORepresentationModel.add(expectedLink);

        ResponseEntity<RepresentationModel<ResourceDTO>> expectedResponseEntity =
                new ResponseEntity<>(resourceDTORepresentationModel,
                        HttpStatus.OK);

        // Act
        ResponseEntity<Object> resultResponseEntity =
                implResourceController.getAllResourcesByProjectCode(code.getCode());

        // Assert
        assertEquals(expectedResponseEntity, resultResponseEntity);

    }

    @Test
    void getAllResourcesByProjectCodeFailedBecauseResourcesListIsEmpty() throws ParseException {

        // Arrange
        ProjectCode code = ProjectCode.create("DANI1");

        when(iAppResourceService.getResourcesByProjectCode(code.getCode()))
                .thenReturn(Optional.empty());


        ResponseEntity<String> expectedResponseEntity =
                new ResponseEntity<>("Project resources list is empty!",
                        HttpStatus.OK);

        // Act
        ResponseEntity<Object> resultResponseEntity =
                implResourceController.getAllResourcesByProjectCode(code.getCode());

        // Assert
        assertEquals(expectedResponseEntity, resultResponseEntity);

    }

    @Test
    void getAllResourcesByProjectCodeException() throws ParseException {

        // Arrange
        IllegalArgumentException e = new IllegalArgumentException();
        ProjectCode code = ProjectCode.create("DANI1");

        when(iAppResourceService.getResourcesByProjectCode(code.getCode()))
                .thenThrow(e);

        ResponseEntity<Object> expectedResponseEntity =
                new ResponseEntity<>(
                        HttpStatus.BAD_REQUEST);

        // Act
        ResponseEntity<Object> resultResponseEntity =
                implResourceController.getAllResourcesByProjectCode(code.getCode());

        // Assert
        assertEquals(expectedResponseEntity, resultResponseEntity);

    }

}


