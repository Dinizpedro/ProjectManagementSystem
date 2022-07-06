package switchfive.project.interfaceAdapters.implRepositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchfive.project.assemblers.assemblersJPA.iAssemblersJPA.IResourceAssemblerJPA;
import switchfive.project.dataModel.dataJPA.ResourceJPA;
import switchfive.project.infrastructure.persistence.repositories.iRepositoriesJPA.IResourceRepositoryJPA;
import switchfive.project.domain.aggregates.resource.Resource;
import switchfive.project.domain.shared.valueObjects.Email;
import switchfive.project.domain.shared.valueObjects.ProjectCode;
import switchfive.project.domain.shared.valueObjects.ResourceID;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ImplResourceRepositoryTest {

    @Mock
    IResourceRepositoryJPA iResourceRepositoryJPA;

    @Mock
    IResourceAssemblerJPA iResourceAssemblerJPA;

    @InjectMocks
    ImplResourceRepository implResourceRepository;


    @Test
    void saveResource() throws ParseException {
        //Arrange
        Resource resourceMock = mock(Resource.class);
        ResourceJPA resourceJPAMock = mock(ResourceJPA.class);

        IResourceRepositoryJPA resourceRepositoryJpaMock =
                mock(IResourceRepositoryJPA.class);
        IResourceAssemblerJPA resourceAssemblerJPAMock =
                mock(IResourceAssemblerJPA.class);

        ImplResourceRepository resourceRepository =
                new ImplResourceRepository(resourceRepositoryJpaMock,
                        resourceAssemblerJPAMock);

        when(resourceAssemblerJPAMock.toData(
                any(Resource.class))).thenReturn(resourceJPAMock);

        when(resourceRepositoryJpaMock.save(any(ResourceJPA.class))).thenReturn(
                resourceJPAMock);

        //Act
        resourceRepository.save(resourceMock);

        //Assert
        verify(resourceAssemblerJPAMock, times(1)).toData(
                any(Resource.class));
        verify(resourceRepositoryJpaMock, times(1)).save(
                any(ResourceJPA.class));
    }

    @Test
    void getProjectManager() throws ParseException {
        // Arrange
        Resource resourceMock = mock(Resource.class);
        ResourceJPA resourceJPAMock = mock(ResourceJPA.class);

        when(iResourceAssemblerJPA.toDomain(any())).thenReturn(resourceMock);
        when(iResourceRepositoryJPA.getResourceJPAByProjectCodeAndRole(anyString(), anyString()))
                .thenReturn(Optional.of(resourceJPAMock));

        // Act
        ProjectCode code = ProjectCode
                .create("ISEP1");
        Optional<Resource> actual =
                implResourceRepository.getProjectManager(code);


        // Assert
        assertEquals(Optional.of(resourceMock), actual);

    }

    @Test
    void getProjectManagerPMNotFound() throws ParseException {

        // Arrange
        when(iResourceRepositoryJPA.getResourceJPAByProjectCodeAndRole(anyString(), anyString()))
                .thenReturn(Optional.empty());

        // Act
        ProjectCode code = ProjectCode
                .create("ISEP1");
        Optional<Resource> actual =
                implResourceRepository.getProjectManager(code);


        // Assert
        assertEquals(Optional.empty(), actual);

    }

    @Test
    void getResourceByID() throws ParseException {

        // Arrange
        Resource resourceMock = mock(Resource.class);
        ResourceJPA resourceJPAMock = mock(ResourceJPA.class);

        when(iResourceAssemblerJPA.toDomain(any())).thenReturn(resourceMock);
        when(iResourceRepositoryJPA.findById((anyString()))).thenReturn(Optional.of(resourceJPAMock));

        // Act
        ResourceID resourceID = ResourceID.createResourceID("123e4567-e89b-12d3-a456-426614174000");
        Optional<Resource> actual =
                implResourceRepository.getResourceByID(resourceID);


        // Assert
        assertEquals(Optional.of(resourceMock), actual);

    }

    @Test
    void getResourcesByEmailSuccessfully() throws ParseException {

        //Arrange
        String email = "valter@gmail.com";
        Email emailVO = Email.create(email);
        ResourceJPA resourceJPAmock = mock(ResourceJPA.class);
        Resource resourceMock = mock(Resource.class);
        List<ResourceJPA> resourceJPAList = new ArrayList<>();
        resourceJPAList.add(resourceJPAmock);

        when(iResourceRepositoryJPA.getResourcesJPAByEmail(any())).thenReturn(resourceJPAList);
        when(iResourceAssemblerJPA.toDomain(resourceJPAmock)).thenReturn(resourceMock);

        List<Resource> resources = new ArrayList<>();
        resources.add(resourceMock);

        //Act
        List<Resource> expected = resources;
        List<Resource> actual = implResourceRepository.getResourcesByEmail(emailVO);

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnEmptyResourcesListWhenUserIsNotAResource() throws ParseException {

        //Arrange
        String email = "valter@gmail.com";
        Email emailVO = Email.create(email);
        List<ResourceJPA> resourceJPAList = new ArrayList<>();
        when(iResourceRepositoryJPA.getResourcesJPAByEmail(any())).thenReturn(resourceJPAList);
        List<Resource> resources = new ArrayList<>();

        //Act
        List<Resource> expected = resources;
        List<Resource> actual = implResourceRepository.getResourcesByEmail(emailVO);

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void getResourcesByProjectCode() throws ParseException {

        //Arrange
        ResourceJPA resourceJPAmock = mock(ResourceJPA.class);
        Resource resourceMock = mock(Resource.class);
        ArrayList<ResourceJPA> resourceJPAList = new ArrayList<>();
        resourceJPAList.add(resourceJPAmock);

        when(iResourceRepositoryJPA.getResourceJPAByProjectCode(any())).thenReturn(resourceJPAList);
        when(iResourceAssemblerJPA.toDomain(resourceJPAmock)).thenReturn(resourceMock);

        //Act
        List<Resource> expected = implResourceRepository.getResourcesByProjectCode(any());

        //Assert
        assertEquals(expected.size(), 1);
    }

    @Test
    void findAllResourcesByProjectCode() throws ParseException {

        // Arrange
        Resource resourceMock = mock(Resource.class);
        ResourceJPA resourceJPAMock = mock(ResourceJPA.class);
        ArrayList<ResourceJPA> resourceJPAList = new ArrayList<>();
        resourceJPAList.add(resourceJPAMock);

        when(iResourceRepositoryJPA.getResourceJPAByProjectCode(ProjectCode.create("DANI1").getCode())).thenReturn(resourceJPAList);
        when(iResourceAssemblerJPA.toDomain(resourceJPAMock)).thenReturn(resourceMock);
        List<Resource> resourceList = new ArrayList<>();
        resourceList.add(resourceMock);

        // Act
        Optional<List<Resource>> actual =
                implResourceRepository.findAllResourcesByProjectCode(ProjectCode.create("DANI1"));


        // Assert
        assertEquals(Optional.of(resourceList), actual);

    }

    @Test
    void findAllResourcesByProjectCode_FailedEmptyList() throws ParseException {

        // Arrange
        ArrayList<ResourceJPA> resourceJPAList = new ArrayList<>();
        when(iResourceRepositoryJPA.getResourceJPAByProjectCode(any())).thenReturn(resourceJPAList);

        // Act
        ProjectCode code = ProjectCode
                .create("ISEP1");
        Optional<List<Resource>> actual =
                implResourceRepository.findAllResourcesByProjectCode(code);


        // Assert
        assertEquals(Optional.empty(), actual);

    }
}
