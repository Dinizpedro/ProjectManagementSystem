package switchfive.project.applicationServices.appServices.implAppServices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import switchfive.project.dtos.RequestDTO;
import switchfive.project.applicationServices.assemblers.implAssemblers.RequestAssembler;
import switchfive.project.applicationServices.iRepositories.IProfileRepository;
import switchfive.project.applicationServices.iRepositories.IRequestRepository;
import switchfive.project.applicationServices.iRepositories.IUserRepository;
import switchfive.project.domain.aggregates.profile.Profile;
import switchfive.project.domain.aggregates.request.Request;
import switchfive.project.domain.aggregates.user.User;
import switchfive.project.domain.factories.iFactories.RequestFactory;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImplAppRequestServiceTest {

    @Mock
    IRequestRepository iRequestRepository;

    @Mock
    IUserRepository iUserRepository;

    @Mock
    IProfileRepository iProfileRepository;

    @Mock
    RequestFactory requestFactory;

    @InjectMocks
    ImplAppRequestService implAppRequestService;


    @Test
    void createNewProfileRequest() throws NoSuchAlgorithmException {

        // Arrange
        Profile profileMock = mock(Profile.class);
        when(iProfileRepository.getProfileByDescription(any())).thenReturn(Optional.of(profileMock));

        User userMock = mock(User.class);
        when(iUserRepository.findUserByEmail(anyString())).thenReturn(Optional.of(userMock));
        when(userMock.hasProfile(any())).thenReturn(false);

        RequestDTO requestDTOMock = mock(RequestDTO.class);
        MockedStatic requestDTOAssembler = mockStatic(RequestAssembler.class);
        when(RequestAssembler.toDto(any())).thenReturn(requestDTOMock);

        when(iRequestRepository
                .getRequestByUserIDAndProfileID(any(), any())).thenReturn(Optional.empty());

        Request request = mock(Request.class);
        when(requestFactory.createNewRequest(any(), any(), any())).thenReturn(request);

        Optional<RequestDTO> expected = Optional.of(requestDTOMock);

        // Act
        Optional<RequestDTO> result = implAppRequestService
                .createNewProfileRequest("asd@ipp.pt", "Director");

        // Assert
        assertEquals(expected, result);
        requestDTOAssembler.close();
    }

    @Test
    void createNewProfileRequestProfileNotInRepository() throws NoSuchAlgorithmException {

        // Arrange
        when(iProfileRepository.getProfileByDescription(any())).thenReturn(Optional.empty());

        User userMock = mock(User.class);
        when(iUserRepository.findUserByEmail(anyString())).thenReturn(Optional.of(userMock));

        when(iRequestRepository
                .getRequestByUserIDAndProfileID(any(), any())).thenReturn(Optional.empty());

        Optional<String> expected = Optional.empty();

        // Act
        Optional<RequestDTO> result = implAppRequestService
                .createNewProfileRequest("isep@ipp.pt", "Director");

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void createNewProfileRequestUserNotInRepository() throws NoSuchAlgorithmException {

        // Arrange
        Profile profile = mock(Profile.class);
        when(iProfileRepository.getProfileByDescription(any())).thenReturn(Optional.of(profile));

        when(iUserRepository.findUserByEmail(anyString())).thenReturn(Optional.empty());

        when(iRequestRepository
                .getRequestByUserIDAndProfileID(any(), any())).thenReturn(Optional.empty());

        Optional<String> expected = Optional.empty();

        // Act
        Optional<RequestDTO> result = implAppRequestService
                .createNewProfileRequest("asd@ipp.pt", "Visitor");

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void createNewProfileRequestRequestAlreadyInRepository() throws NoSuchAlgorithmException {

        // Arrange
        Profile profile = mock(Profile.class);
        when(iProfileRepository.getProfileByDescription(any())).thenReturn(Optional.of(profile));

        User userMock = mock(User.class);
        when(iUserRepository.findUserByEmail(anyString())).thenReturn(Optional.of(userMock));

        Request request = mock(Request.class);
        when(iRequestRepository
                .getRequestByUserIDAndProfileID(any(), any()))
                .thenReturn(Optional.of(request));

        Optional<String> expected = Optional.empty();

        // Act
        Optional<RequestDTO> result = implAppRequestService
                .createNewProfileRequest("asd@ipp.pt", "Director");

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void createNewProfileRequestUserAlreadyHasProfile() throws NoSuchAlgorithmException {

        // Arrange
        Profile profileMock = mock(Profile.class);
        when(iProfileRepository.getProfileByDescription(any())).thenReturn(Optional.of(profileMock));

        User userMock = mock(User.class);
        when(iUserRepository.findUserByEmail(anyString())).thenReturn(Optional.of(userMock));
        when(userMock.hasProfile(any())).thenReturn(true);

        when(iRequestRepository
                .getRequestByUserIDAndProfileID(any(), any())).thenReturn(Optional.empty());

        Optional<String> expected = Optional.empty();

        // Act
        Optional<RequestDTO> result = implAppRequestService
                .createNewProfileRequest("asd@ipp.pt", "Director");

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void getRequestDTOTrue() {
        // Assert
        Request requestMock = mock(Request.class);
        when(iRequestRepository
                .getRequestByID(any())).thenReturn(Optional.of(requestMock));

        RequestDTO requestDTO = mock(RequestDTO.class);
        MockedStatic requestAssemblerStaticMock = mockStatic(RequestAssembler.class);
        when(RequestAssembler.toDto(requestMock)).thenReturn(requestDTO);

        Optional<RequestDTO> expected = Optional.of(requestDTO);

        // Act
        String requestUUID = "123e4567-e89b-12d3-a456-426614174000";
        Optional<RequestDTO> result = implAppRequestService.getRequestDTO(requestUUID);

        // Assert
        assertEquals(expected, result);
        requestAssemblerStaticMock.close();
    }

    @Test
    void getRequestDTOFails() {
        // Assert
        when(iRequestRepository
                .getRequestByID(any())).thenReturn(Optional.empty());

        Optional<RequestDTO> expected = Optional.empty();

        // Act
        String requestUUID = "123e4567-e89b-12d3-a456-426614174000";
        Optional<RequestDTO> result = implAppRequestService.getRequestDTO(requestUUID);

        // Assert
        assertEquals(expected, result);
    }
}
