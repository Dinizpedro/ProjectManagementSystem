
package switchfive.project.interfaceAdapters.implRepositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchfive.project.assemblers.assemblersJPA.iAssemblersJPA.IProfileAssemblerJPA;
import switchfive.project.dataModel.dataJPA.ProfileJPA;
import switchfive.project.infrastructure.persistence.repositories.iRepositoriesJPA.IProfileRepositoryJPA;
import switchfive.project.domain.aggregates.profile.Profile;
import switchfive.project.domain.shared.valueObjects.ProfileDescription;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImplProfileRepositoryTest {

    @Mock
    IProfileRepositoryJPA iProfileRepositoryJPA;

    @Mock
    IProfileAssemblerJPA iProfileAssemblerJPA;

    @InjectMocks
    ImplProfileRepository implProfileRepository;


    @Test
    void save() {
        // Arrange
        Profile profileMock = mock(Profile.class);
        ProfileJPA profileJPAMock = mock(ProfileJPA.class);

        // Act
        when(iProfileAssemblerJPA.toData(any())).thenReturn(profileJPAMock);
        when(iProfileRepositoryJPA.save(profileJPAMock)).thenReturn(profileJPAMock);
        when(iProfileAssemblerJPA.toDomain(profileJPAMock)).thenReturn(profileMock);

        Profile result = implProfileRepository.save(profileMock);

        // Assert
        assertEquals(profileMock, result);
    }

    @Test
    void getProfileByDescriptionProfileFound() {
        // Arrange
        Profile profileMock = mock(Profile.class);
        ProfileJPA profileJPAMock = mock(ProfileJPA.class);

        when(iProfileAssemblerJPA.toDomain(any())).thenReturn(profileMock);
        when(iProfileRepositoryJPA.findProfileJPAByProfileDescription(anyString()))
                .thenReturn(Optional.of(profileJPAMock));

        // Act
        ProfileDescription profileDescription = ProfileDescription
                .createProfileDescription("Valid description");
        Optional<Profile> actual =
                implProfileRepository.getProfileByDescription(profileDescription);


        // Assert
        assertEquals(Optional.of(profileMock), actual);

    }

    @Test
    void getProfileByDescriptionProfileNotFound() {
        // Arrange
        when(iProfileRepositoryJPA.findProfileJPAByProfileDescription(anyString()))
                .thenReturn(Optional.empty());

        // Act
        ProfileDescription profileDescription = ProfileDescription
                .createProfileDescription("Valid description");
        Optional<Profile> actual =
                implProfileRepository.getProfileByDescription(profileDescription);

        // Assert
        assertEquals(Optional.empty(), actual);

    }

    @Test
    void getProfiles() {
        // Arrange
        Profile profileMock = mock(Profile.class);
        ProfileJPA profileJPAMock = mock(ProfileJPA.class);
        List<ProfileJPA> profileJPAList = new ArrayList<>();
        profileJPAList.add(profileJPAMock);

        when(iProfileAssemblerJPA.toDomain(profileJPAMock)).thenReturn(profileMock);
        when(iProfileRepositoryJPA.findAll())
                .thenReturn(profileJPAList);


        List<Profile> expected = new ArrayList<>();
        expected.add(profileMock);

        // Act
        List<Profile> actual =
                implProfileRepository.getProfiles();


        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void getProfilesNotInJPARepo() {
        // Arrange
        List<ProfileJPA> profileJPAList = new ArrayList<>();
        when(iProfileRepositoryJPA.findAll())
                .thenReturn(profileJPAList);

        List<Profile> expected = new ArrayList<>();

        // Act
        List<Profile> actual =
                implProfileRepository.getProfiles();


        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void profileExists() {
        // Arrange
        when(iProfileRepositoryJPA.existsProfileJPAByProfileDescription(any()))
                .thenReturn(true);

        // Act
        boolean actual = implProfileRepository.profileExists("Visitor");

        // Assert
        assertTrue(actual);
    }

    @Test
    void profileExistsFalse() {
        // Arrange
        when(iProfileRepositoryJPA.existsProfileJPAByProfileDescription(any()))
                .thenReturn(false);

        // Act
        boolean actual = implProfileRepository.profileExists("Visitor");

        // Assert
        assertFalse(actual);
    }
}
