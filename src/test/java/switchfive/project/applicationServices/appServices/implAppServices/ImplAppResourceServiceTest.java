package switchfive.project.applicationServices.appServices.implAppServices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchfive.project.dtos.ResourceCreationDTO;
import switchfive.project.dtos.ResourceDTO;
import switchfive.project.applicationServices.iRepositories.IProjectRepository;
import switchfive.project.applicationServices.iRepositories.IResourceRepository;
import switchfive.project.applicationServices.iRepositories.IUserRepository;
import switchfive.project.mappers.mappersApp.iMappers.IResourceMapper;
import switchfive.project.mappers.mappersApp.implMappers.ImplResourceMapper;
import switchfive.project.domain.aggregates.project.Project;
import switchfive.project.domain.aggregates.resource.Resource;
import switchfive.project.domain.aggregates.user.User;
import switchfive.project.domain.domainServices.iDomainServices.IResourceDomainService;
import switchfive.project.domain.factories.iFactories.IResourceFactory;
import switchfive.project.domain.shared.valueObjects.*;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImplAppResourceServiceTest {

    @Mock
    IResourceFactory iResourceFactory;
    @Mock
    IUserRepository iUserRepository;
    @Mock
    IResourceRepository iResourceRepository;
    @Mock
    IProjectRepository iProjectRepository;
    @Mock
    IResourceDomainService iResourceDomainService;
    @Mock
    IResourceMapper iResourceMapper;
    @InjectMocks
    ImplAppResourceService implAppResourceService;

    @Test
    void definedTeamMemberOfAProjectTrue() throws ParseException, NoSuchAlgorithmException {

        //Arrange
        String startDate = "27/05/2025";
        String endDate = "27/05/2030";
        ResourceCreationDTO dto = new ResourceCreationDTO("isep@ipp.pt", "TEAM2", startDate, endDate, 10, 100);
        Project project = mock(Project.class);
        ArrayList<Resource> resources = new ArrayList<>();
        Resource resource = mock(Resource.class);
        ResourceDTO resourceDTO = mock(ResourceDTO.class);

        when(iProjectRepository.projectExists(any())).thenReturn(true);
        when(iUserRepository.userExists((String) any())).thenReturn(true);

        when(iResourceRepository.getResourcesByProjectCode(dto.projectCodeDto)).thenReturn(resources);

        when(iProjectRepository.findByCode(any())).thenReturn(Optional.of(project));

        when(iResourceDomainService.validateNewTeamMemberResource(resources, project, dto)).thenReturn(true);
        when(iResourceFactory.createResource(any(), any(), any(), any(), any(), any(), any())).thenReturn(resource);
        when(iResourceRepository.save(resource)).thenReturn(resource);
        ImplResourceMapper resourceAssembler = mock(ImplResourceMapper.class);
        when(iResourceMapper.toDto(any())).thenReturn(resourceDTO);

        Optional<ResourceDTO> expected = Optional.of(resourceDTO);

        //Act
        Optional<ResourceDTO> result = implAppResourceService.definedTeamMemberOfAProject(dto);

        //Assert
        assertEquals(expected, result);
        //resourceAssembler.close();
    }

    @Test
    void definedTeamMemberOfAProjectFailedBecauseNoProject() throws ParseException, NoSuchAlgorithmException {

        //Arrange
        String startDate = "27/05/2025";
        String endDate = "27/05/2030";
        ResourceCreationDTO dto = new ResourceCreationDTO("isep@ipp.pt", "TEAM2", startDate, endDate, 10, 100);

        when(iProjectRepository.projectExists(any())).thenReturn(false);

        Optional<ResourceDTO> expected = Optional.empty();

        //Act
        Optional<ResourceDTO> result = implAppResourceService.definedTeamMemberOfAProject(dto);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void definedTeamMemberOfAProjectFailedBecauseNoUser() throws ParseException, NoSuchAlgorithmException {


        //Arrange
        String startDate = "27/05/2025";
        String endDate = "27/05/2030";
        ResourceCreationDTO dto = new ResourceCreationDTO("isep@ipp.pt", "TEAM2", startDate, endDate, 10, 100);

        when(iProjectRepository.projectExists(any())).thenReturn(true);
        when(iUserRepository.userExists((String) any())).thenReturn(false);

        Optional<ResourceDTO> expected = Optional.empty();

        //Act
        Optional<ResourceDTO> result = implAppResourceService.definedTeamMemberOfAProject(dto);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void definedProductOwnerOfAProjectTrue() throws ParseException, NoSuchAlgorithmException {

        //Arrange
        String startDate = "27/05/2025";
        String endDate = "27/05/2030";
        ResourceCreationDTO dto = new ResourceCreationDTO("isep@ipp.pt", "TEAM2", startDate, endDate, 10, 100);
        Project project = mock(Project.class);
        ArrayList<Resource> resources = new ArrayList<>();
        Resource resource = mock(Resource.class);
        ResourceDTO resourceDTO = mock(ResourceDTO.class);
        User user = mock(User.class);

        when(iUserRepository.findUserByEmail(anyString())).thenReturn(Optional.of(user));
        when(iResourceRepository.getResourcesByProjectCode(dto.projectCodeDto)).thenReturn(resources);
        when(iProjectRepository.findByCode(ProjectCode.create(dto.projectCodeDto))).thenReturn(Optional.of(project));
        when(iResourceDomainService.validateNewProductOwnerResource(resources, project, dto)).thenReturn(true);
        when(iResourceFactory.createResource(any(), any(), any(), any(), any(), any(), any())).thenReturn(resource);
        when(iResourceRepository.save(resource)).thenReturn(resource);
        ImplResourceMapper resourceAssembler = mock(ImplResourceMapper.class);
        when(iResourceMapper.toDto(any())).thenReturn(resourceDTO);

        Optional<ResourceDTO> expected = Optional.of(resourceDTO);

        //Act
        Optional<ResourceDTO> result = implAppResourceService.definedProductOwnerOfAProject(dto);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void definedProductOwnerOfAProjectFailedBecauseNoProject() throws ParseException, NoSuchAlgorithmException {

        //Arrange
        String startDate = "27/05/2025";
        String endDate = "27/05/2030";
        ResourceCreationDTO dto = new ResourceCreationDTO("isep@ipp.pt", "TEAM2", startDate, endDate, 10, 100);
        Project project = mock(Project.class);

        when(iProjectRepository.findByCode(ProjectCode.create(dto.projectCodeDto))).thenReturn(Optional.of(project));

        Optional<ResourceDTO> expected = Optional.empty();

        //Act
        Optional<ResourceDTO> result = implAppResourceService.definedProductOwnerOfAProject(dto);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void definedProductOwnerOfAProjectFailedBecauseNoUser() throws ParseException, NoSuchAlgorithmException {

        //Arrange
        String startDate = "27/05/2025";
        String endDate = "27/05/2030";
        ResourceCreationDTO dto = new ResourceCreationDTO("isep@ipp.pt", "TEAM2", startDate, endDate, 10, 100);
        Project project = mock(Project.class);

        when(iProjectRepository.findByCode(ProjectCode.create(dto.projectCodeDto))).thenReturn(Optional.of(project));
        when(iUserRepository.findUserByEmail(anyString())).thenReturn(Optional.empty());

        Optional<ResourceDTO> expected = Optional.empty();

        //Act
        Optional<ResourceDTO> result = implAppResourceService.definedProductOwnerOfAProject(dto);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getResourceDTO() throws ParseException {

        //Arrange
        Resource resourceMock = mock(Resource.class);
        ResourceDTO resourceDTO = mock(ResourceDTO.class);
        ImplResourceMapper resourceAssembler = mock(ImplResourceMapper.class);

        when(iResourceRepository.getResourceByID(any())).thenReturn(Optional.of(resourceMock));
        when(iResourceMapper.toDto(any())).thenReturn(resourceDTO);

        Optional<ResourceDTO> expected = Optional.of(resourceDTO);

        //Act
        String resourceUUID = "123e4567-e89b-12d3-a456-426614174000";
        Optional<ResourceDTO> result = implAppResourceService.getResourceDTO(resourceUUID);

        //Assert
        assertEquals(expected, result);

    }

    @Test
    void getResourceDTOFails() throws ParseException {

        //Arrange
        when(iResourceRepository.getResourceByID(any())).thenReturn(Optional.empty());

        Optional<ResourceDTO> expected = Optional.empty();

        //Act
        String resourceUUID = "123e4567-e89b-12d3-a456-426614174000";
        Optional<ResourceDTO> result = implAppResourceService.getResourceDTO(resourceUUID);

        //Assert
        assertEquals(expected, result);

    }

    @Test
    void getProjectManager() throws ParseException {

        //Arrange
        Resource resourceMock = mock(Resource.class);
        when(iResourceRepository.getProjectManager(any(ProjectCode.class))).thenReturn(Optional.of(resourceMock));

        Optional<Resource> expected = Optional.of(resourceMock);

        //Act
        Optional<Resource> result = implAppResourceService.getProjectManager(ProjectCode.create("ABCDE"));

        //Assert
        assertEquals(expected, result);

    }

    @Test
    void getProjectManagerFailed() throws ParseException {

        //Arrange
        when(iResourceRepository.getProjectManager(any(ProjectCode.class))).thenReturn(Optional.empty());

        Optional<Resource> expected = Optional.empty();

        //Act
        Optional<Resource> result = implAppResourceService.getProjectManager(ProjectCode.create("ABCDE"));

        //Assert
        assertEquals(expected, result);

    }

    @Test
    void findUser() throws NoSuchAlgorithmException {

        //Arrange
        User userMock = mock(User.class);
        when(iUserRepository.findUserByEmail("isep@ipp.pt")).thenReturn(Optional.of(userMock));

        Optional<User> expected = Optional.of(userMock);

        //Act
        Optional<User> result = implAppResourceService.findUser("isep@ipp.pt");

        //Assert
        assertEquals(expected, result);

    }

    @Test
    void findUserFailed() throws NoSuchAlgorithmException {

        //Arrange
        when(iUserRepository.findUserByEmail("isep@ipp.pt")).thenReturn(Optional.empty());

        Optional<User> expected = Optional.empty();

        //Act
        Optional<User> result = implAppResourceService.findUser("isep@ipp.pt");

        //Assert
        assertEquals(expected, result);

    }

    @Test
    void definedScrumMasterOfAProjectTrue() throws ParseException, NoSuchAlgorithmException {

        //Arrange
        String startDate = "27/05/2025";
        String endDate = "27/05/2030";
        ResourceCreationDTO dto = new ResourceCreationDTO("isep@ipp.pt", "TEAM2", startDate, endDate, 10, 100);
        Project project = mock(Project.class);
        ArrayList<Resource> resources = new ArrayList<>();
        Resource resource = mock(Resource.class);
        ResourceDTO resourceDTO = mock(ResourceDTO.class);
        User user = mock(User.class);

        when(iUserRepository.findUserByEmail(dto.userIdDto)).thenReturn(Optional.of(user));
        when(iResourceRepository.getResourcesByProjectCode(dto.projectCodeDto)).thenReturn(resources);
        when(iProjectRepository.findByCode(any(ProjectCode.class))).thenReturn(Optional.of(project));
        when(iResourceDomainService.validateNewScrumMasterResource(resources, project, dto)).thenReturn(true);
        when(iResourceFactory.createResource(any(), any(), any(), any(), any(), any(), any())).thenReturn(resource);
        when(iResourceRepository.save(resource)).thenReturn(resource);
        ImplResourceMapper resourceAssembler = mock(ImplResourceMapper.class);
        when(iResourceMapper.toDto(any())).thenReturn(resourceDTO);

        Optional<ResourceDTO> expected = Optional.of(resourceDTO);

        //Act
        Optional<ResourceDTO> result = implAppResourceService.definedScrumMasterOfAProject(dto);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void definedScrumMasterOfAProjectFailedBecauseNoProject() throws ParseException, NoSuchAlgorithmException {

        //Arrange
        String startDate = "27/05/2025";
        String endDate = "27/05/2030";
        ResourceCreationDTO dto = new ResourceCreationDTO("isep@ipp.pt", "TEAM2", startDate, endDate, 10, 100);
        Project project = mock(Project.class);

        when(iProjectRepository.findByCode(any(ProjectCode.class))).thenReturn(Optional.of(project));

        Optional<ResourceDTO> expected = Optional.empty();

        //Act
        Optional<ResourceDTO> result = implAppResourceService.definedScrumMasterOfAProject(dto);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void definedScrumMasterOfAProjectFailedBecauseNoUser() throws ParseException, NoSuchAlgorithmException {

        //Arrange
        String startDate = "27/05/2025";
        String endDate = "27/05/2030";
        ResourceCreationDTO dto = new ResourceCreationDTO("isep@ipp.pt", "TEAM2", startDate, endDate, 10, 100);
        Project project = mock(Project.class);
        User user = mock(User.class);

        when(iProjectRepository.findByCode(any(ProjectCode.class))).thenReturn(Optional.of(project));

        when(iUserRepository.findUserByEmail(dto.userIdDto)).thenReturn(Optional.of(user));
        Optional<ResourceDTO> expected = Optional.empty();

        //Act
        Optional<ResourceDTO> result = implAppResourceService.definedScrumMasterOfAProject(dto);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void createProjectManager() throws ParseException {

        //Arrange
        Email emailMock = mock(Email.class);
        ProjectCode codeMock = mock(ProjectCode.class);
        Time timeMock = mock(Time.class);
        ResourceCostPerHour costPerHourMock = mock(ResourceCostPerHour.class);
        ResourcePercentageOfAllocation allocation = mock(ResourcePercentageOfAllocation.class);
        Resource resourceMock = mock(Resource.class);

        when(iUserRepository.userExists(emailMock.getUserEmail())).thenReturn(true);
        when(iResourceFactory.createResource(any(), any(), any(), any(), any(), any(), any())).thenReturn(resourceMock);

        //Act
        Resource result = implAppResourceService.createProjectManager(emailMock, codeMock, timeMock, costPerHourMock, allocation);

        //Assert
        assertEquals(resourceMock, result);

    }

    @Test
    void createProjectManagerFailedNoUser() {

        //Arrange
        Email emailMock = mock(Email.class);
        ProjectCode codeMock = mock(ProjectCode.class);
        Time timeMock = mock(Time.class);
        ResourceCostPerHour costPerHourMock = mock(ResourceCostPerHour.class);
        ResourcePercentageOfAllocation allocation = mock(ResourcePercentageOfAllocation.class);

        when(iUserRepository.userExists(emailMock.getUserEmail())).thenReturn(false);

        //Act

        //Assert
        assertThrows(IllegalArgumentException.class, () -> implAppResourceService.createProjectManager(emailMock, codeMock, timeMock, costPerHourMock, allocation));

    }

    @Test
    void getResourcesByProjectCode_Succesfully() throws ParseException {

        // Arrange
        ProjectCode code = ProjectCode.create("AB123");

        List<Resource> resources = new ArrayList<>();

        Resource resourceToFind = mock(Resource.class);
        resources.add(resourceToFind);

        when(iProjectRepository.projectExists(code)).thenReturn(true);
        when(iResourceRepository.findAllResourcesByProjectCode(code)).thenReturn(Optional.of(resources));

        ResourceDTO resourceDTOexpected = mock(ResourceDTO.class);
        ImplResourceMapper resourceAssembler = mock(ImplResourceMapper.class);

        when(iResourceMapper.toDto(any())).thenReturn(resourceDTOexpected);

        // Act
        List<ResourceDTO> expected = new ArrayList<>();
        expected.add(resourceDTOexpected);

        List<ResourceDTO> result = implAppResourceService.getResourcesByProjectCode("AB123").get();

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void getResourcesByProjectCode_ResourceListIsEmpty() throws ParseException {

        // Arrange
        ProjectCode code = ProjectCode.create("AB123");

        when(iProjectRepository.projectExists(code)).thenReturn(true);
        when(iResourceRepository.findAllResourcesByProjectCode(code)).thenReturn(Optional.empty());

        // Act && Assert
        assertEquals(Optional.empty(), implAppResourceService.getResourcesByProjectCode("AB123"));
    }

    @Test
    void getResourcesByProjectCode_Failling_ProjectDoesNotExist() {

        // Arrange
        ProjectCode code = ProjectCode.create("AB123");

        when(iProjectRepository.projectExists(code)).thenReturn(false);

        // Assert
        assertThrows(IllegalArgumentException.class, () -> this.implAppResourceService.getResourcesByProjectCode("AB123"));
    }
}


