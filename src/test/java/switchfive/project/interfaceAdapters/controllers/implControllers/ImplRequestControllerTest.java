package switchfive.project.interfaceAdapters.controllers.implControllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import switchfive.project.dtos.RequestCreationDTO;
import switchfive.project.dtos.RequestDTO;
import switchfive.project.applicationServices.appServices.iappServices.IAppRequestService;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ImplRequestControllerTest {

    @Mock
    IAppRequestService iAppRequestService;

    @InjectMocks
    ImplRequestController implRequestController;

    @Test
    void createNewProfileRequest() throws NoSuchAlgorithmException {
        // Arrange
        String userID = "isep@ipp.pt";
        String profileDesignation = "Visitor";
        RequestDTO requestDTOMock = mock(RequestDTO.class);

        when(iAppRequestService.createNewProfileRequest(userID, profileDesignation))
                .thenReturn(Optional.of(requestDTOMock));

        RequestCreationDTO requestCreationDTO = new RequestCreationDTO(userID,
                profileDesignation);

        ResponseEntity<Object> expected =
                new ResponseEntity<>(requestDTOMock, HttpStatus.CREATED);

        // Act
        ResponseEntity<Object> result = implRequestController
                .createNewProfileRequest(requestCreationDTO);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void createNewProfileRequestFails() throws NoSuchAlgorithmException {
        // Arrange
        String userID = "isep@ipp.pt";
        String profileDesignation = "Visitor";
        when(iAppRequestService.createNewProfileRequest(userID, profileDesignation))
                .thenReturn(Optional.empty());

        RequestCreationDTO requestCreationDTO = new RequestCreationDTO(userID,profileDesignation);

        ResponseEntity<Object> expected =
                new ResponseEntity<>("Request creation failed",
                        HttpStatus.BAD_REQUEST);

        // Act
        ResponseEntity<Object> result = implRequestController
                .createNewProfileRequest(requestCreationDTO);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void getRequest() {
        // Arrange
        String requestID = "anyRequestID";
        RequestDTO requestDTO = mock(RequestDTO.class);

        when(iAppRequestService.getRequestDTO(requestID))
                .thenReturn(Optional.of(requestDTO));

        implRequestController.getRequest(requestID);

        ResponseEntity<Object> expected =
                new ResponseEntity<>(requestDTO, HttpStatus.OK);

        // Act
        ResponseEntity<Object> result = implRequestController
                .getRequest(requestID);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void getRequestFailsNotInRepo() {
        // Arrange
        String requestID = "anyRequestID";

        when(iAppRequestService.getRequestDTO(requestID))
                .thenReturn(Optional.empty());

        implRequestController.getRequest(requestID);

        ResponseEntity<Object> expected =
                new ResponseEntity<>("Request not found in repository",
                        HttpStatus.NOT_FOUND);

        // Act
        ResponseEntity<Object> result = implRequestController
                .getRequest(requestID);

        // Assert
        assertEquals(expected, result);
    }
}
