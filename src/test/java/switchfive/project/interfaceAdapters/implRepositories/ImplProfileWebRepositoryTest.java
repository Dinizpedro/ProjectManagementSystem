/*
package switchfive.project.binterfaceAdapters.implRepositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchfive.project.ainfrastructure.persistence.assemblers.assemblersREST.iAssemblersREST.IRestProfileAssembler;
import switchfive.project.ainfrastructure.persistence.data.dataREST.ProfileRest;
import switchfive.project.ainfrastructure.persistence.repositories.repositoriesREST.iRepositoriesREST.IProfileRestRepository;
import switchfive.project.binterfaceAdapters.domainWS.ProfileWS;
import switchfive.project.binterfaceAdapters.implRepositoriesWS.ImplProfileWebRepository;
import switchfive.project.ddomain.shared.valueObjects.ProfileDescription;

import javax.net.ssl.SSLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ImplProfileWebRepositoryTest {

    @Mock
    IProfileRestRepository iProfileRestRepository;

    @Mock
    IRestProfileAssembler iRestProfileAssembler;

    @Mock
    ProfileRest profileRestMock;

    @Mock
    ProfileWS profileWSMock;

    @InjectMocks
    ImplProfileWebRepository implProfileWebRepository;

    @Test
    @DisplayName("Should return an optional object of a profile mock")
    void getProfileByDescription() throws SSLException {
        // Arrange
        ProfileDescription profileDescription = ProfileDescription
                .createProfileDescription("Valid description");
        String description = profileDescription.getDescription();

        ProfileRest profileRest = new ProfileRest();
        profileRest.setUserProfileName(description);

        ProfileWS profileMock = mock(ProfileWS.class);

        when(iProfileRestRepository.findProfileInExternalServiceByProfileDescription
                (description)).thenReturn(Optional.of(profileRest));
        when(iRestProfileAssembler.toDomain(profileRest)).thenReturn(profileMock);

        // Act
        Optional<ProfileWS> actual = implProfileWebRepository
                .getProfileByDescription(profileDescription);

        // Assert
        assertEquals(Optional.of(profileMock), actual);
    }

    @Test
    @DisplayName("Should return an optional empty object")
    void getProfileByDescriptioProfileNotFound() throws SSLException {
        // Arrange
        ProfileDescription profileDescription = ProfileDescription
                .createProfileDescription("Valid description");
        String description = profileDescription.getDescription();


        when(iProfileRestRepository.findProfileInExternalServiceByProfileDescription
                (description)).thenReturn(Optional.empty());

        // Act
        Optional<ProfileWS> actual = implProfileWebRepository
                .getProfileByDescription(profileDescription);

        // Assert
        assertEquals(Optional.empty(), actual);

    }

    @Test
    void getProfilesSuccessful() throws SSLException {
        //Arrange
        List<ProfileRest> profileRestList = new ArrayList<>();
        profileRestList.add(profileRestMock);

        List<ProfileWS> profileWSList = new ArrayList<>();
        profileWSList.add(profileWSMock);

        when(iProfileRestRepository.findAllProfilesInExternalService()).thenReturn(profileRestList);
        when(iRestProfileAssembler.toDomain(profileRestList)).thenReturn(profileWSList);

        //Act
        List<ProfileWS> expected = implProfileWebRepository.getProfiles();

        //Assert
        assertEquals(expected,profileWSList);
    }

    @Test
    void getProfilesEmpty() throws SSLException {
        //Arrange
        List<ProfileRest> profileRestList = new ArrayList<>();

        List<ProfileWS> profileWSList = new ArrayList<>();

        when(iProfileRestRepository.findAllProfilesInExternalService()).thenReturn(profileRestList);

        //Act
        List<ProfileWS> expected = implProfileWebRepository.getProfiles();

        //Assert
        assertEquals(expected,profileWSList);
    }
}
*/
