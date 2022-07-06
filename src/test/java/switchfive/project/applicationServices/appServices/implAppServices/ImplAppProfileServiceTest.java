package switchfive.project.applicationServices.appServices.implAppServices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchfive.project.interfaceAdapters.domainWS.ProfileWS;
import switchfive.project.dtos.ProfileDTO;
import switchfive.project.mappers.mappersApp.iMappers.IProfileMapper;
import switchfive.project.applicationServices.iRepositories.IProfileRepository;
import switchfive.project.applicationServices.iRepositoriesWS.IProfileWebRepository;
import switchfive.project.domain.aggregates.profile.Profile;
import switchfive.project.domain.factories.iFactories.ProfileFactory;
import switchfive.project.domain.shared.valueObjects.ProfileDescription;

import javax.net.ssl.SSLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImplAppProfileServiceTest {

    @Mock
    IProfileRepository iProfileRepository;

    @Mock
    ProfileFactory profileFactory;

    @Mock
    IProfileWebRepository iProfileWebRepository;

    @Mock
    IProfileMapper profileAssembler;

    @InjectMocks
    ImplAppProfileService implAppProfileService;

    @Test
    void addNewProfileTrue() {
        // Arrange
        Profile profileMock = mock(Profile.class);
        ProfileDTO profileDTOMock = mock(ProfileDTO.class);

        when(iProfileRepository.profileExists(any(ProfileDescription.class)))
                .thenReturn(false);

        when(profileFactory.createProfile(any())).thenReturn(profileMock);

        when(iProfileRepository.save(profileMock)).thenReturn(profileMock);

        when(profileAssembler.toDTO(any(Profile.class))).thenReturn(profileDTOMock);

        Optional<ProfileDTO> expected = Optional.of(profileDTOMock);

        // Act
        Optional<ProfileDTO> result =
                implAppProfileService.addNewProfile("New Profile");

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void addNewProfileProfileAlreadyInStore() {
        // Arrange
        when(iProfileRepository.profileExists(any(ProfileDescription.class)))
                .thenReturn(true);

        Optional<ProfileDTO> expected = Optional.empty();

        // Act
        Optional<ProfileDTO> result =
                implAppProfileService.addNewProfile("New Profile");

        // Assert
        assertEquals(expected, result);

    }


    @Test
    void getProfileTrueInDB() throws SSLException {
        // Arrange
        Profile profileToFind = mock(Profile.class);

        when(iProfileRepository.getProfileByDescription(any()))
                .thenReturn(Optional.of(profileToFind));

        ProfileDTO profileDTOexpected = mock(ProfileDTO.class);
        when(profileAssembler.toDTO(any(Profile.class))).thenReturn(profileDTOexpected);

        Optional<ProfileDTO> expected = Optional.of(profileDTOexpected);

        // Act
        String profileID = "Visitor";
        Optional<ProfileDTO> result = implAppProfileService
                .getProfile(profileID);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void getProfileTrueInWebService() throws SSLException {
        // Arrange
        ProfileWS profileToFind = mock(ProfileWS.class);

        when(iProfileRepository.getProfileByDescription(any()))
                .thenReturn(Optional.empty());
        when(iProfileWebRepository.getProfileByDescription(any()))
                .thenReturn(Optional.of(profileToFind));

        ProfileDTO profileDTOexpected = mock(ProfileDTO.class);
        when(profileAssembler.toDTO(any(ProfileWS.class))).thenReturn(profileDTOexpected);

        Optional<ProfileDTO> expected = Optional.of(profileDTOexpected);

        // Act
        String profileID = "Visitor";
        Optional<ProfileDTO> result = implAppProfileService
                .getProfile(profileID);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void getProfileProfileNotInRepo() throws SSLException {
        // Arrange
        when(iProfileRepository.getProfileByDescription(any()))
                .thenReturn(Optional.empty());
        when(iProfileWebRepository.getProfileByDescription(any()))
                .thenReturn(Optional.empty());

        Optional<ProfileDTO> expected = Optional.empty();

        // Act
        String profileID = "Visitor";
        Optional<ProfileDTO> result = implAppProfileService
                .getProfile(profileID);

        // Assert
        assertEquals(expected, result);
    }


    @Test
    void getProfiles() throws SSLException {
        // Arrange
        List<Profile> profileList = new ArrayList<>();
        Profile profileToFind = mock(Profile.class);
        profileList.add(profileToFind);

        List<ProfileWS> profileWSList = new ArrayList<>();
        ProfileWS profileWSToFind = mock(ProfileWS.class);
        profileWSList.add(profileWSToFind);

        when(iProfileRepository.getProfiles())
                .thenReturn(profileList);

        when(iProfileWebRepository.getProfiles())
                .thenReturn(profileWSList);

        ProfileDTO profileDTOexpected = mock(ProfileDTO.class);
        when(profileAssembler.toDTO(any(Profile.class))).thenReturn(profileDTOexpected);
        when(profileAssembler.toDTO(any(ProfileWS.class))).thenReturn(profileDTOexpected);


        Set<ProfileDTO> expected = new HashSet<>();
        expected.add(profileDTOexpected);

        // Act
        Set<ProfileDTO> result = implAppProfileService
                .getProfiles();

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void getProfilesNotInRepo() throws SSLException {
        // Arrange
        List<Profile> profileList = new ArrayList<>();
        List<ProfileWS> profileWSList = new ArrayList<>();


        when(iProfileRepository.getProfiles())
                .thenReturn(profileList);
        when(iProfileWebRepository.getProfiles())
                .thenReturn(profileWSList);

        Set<ProfileDTO> expected = new HashSet<>();

        // Act
        Set<ProfileDTO> result = implAppProfileService
                .getProfiles();

        // Assert
        assertEquals(expected, result);}
}
