package switchfive.project.interfaceAdapters.implRepositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchfive.project.assemblers.assemblersJPA.iAssemblersJPA.IRequestAssemblerJPA;
import switchfive.project.dataModel.dataJPA.RequestJPA;
import switchfive.project.infrastructure.persistence.repositories.iRepositoriesJPA.IRequestRepositoryJPA;
import switchfive.project.domain.aggregates.request.Request;
import switchfive.project.domain.shared.valueObjects.Email;
import switchfive.project.domain.shared.valueObjects.ProfileDescription;
import switchfive.project.domain.shared.valueObjects.RequestID;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ImplRequestRepositoryTest {

    @Mock
    IRequestRepositoryJPA iRequestRepositoryJPA;

    @Mock
    IRequestAssemblerJPA iRequestAssemblerJPA;

    @InjectMocks
    private ImplRequestRepository implRequestRepository;

    @Test
    void save() {
        // Arrange
        Request requestMock = mock(Request.class);
        RequestJPA requestJPAMock = mock(RequestJPA.class);

        when(iRequestAssemblerJPA.toData(requestMock)).thenReturn(requestJPAMock);
        when(iRequestRepositoryJPA.save(requestJPAMock)).thenReturn(requestJPAMock);
        when(iRequestAssemblerJPA.toDomain(requestJPAMock)).thenReturn(requestMock);

        // Act
        Request actual = implRequestRepository.save(requestMock);

        // Assert
        assertEquals(requestMock, actual);
    }

    @Test
    void getRequestByUserIDAndProfileID() {
        // Arrange
        Request requestMock = mock(Request.class);
        RequestJPA requestJPAMock = mock(RequestJPA.class);
        Email userID = Email.create("valid@cenas.pt");
        ProfileDescription profileID = ProfileDescription
                .createProfileDescription("Visitor");

        when(iRequestRepositoryJPA
                .findByUserIDAndProfileDescription(any(), any()))
                .thenReturn(Optional.of(requestJPAMock));
        when(iRequestAssemblerJPA.toDomain(requestJPAMock))
                .thenReturn(requestMock);

        // Act
        Optional<Request> actual = implRequestRepository
                .getRequestByUserIDAndProfileID(userID, profileID);

        // Assert
        assertEquals(Optional.of(requestMock), actual);
    }

    @Test
    void getRequestByUserIDAndProfileIDRequestNotInRepo() {
        // Arrange
        Email userID = Email.create("valid@cenas.pt");
        ProfileDescription profileID = ProfileDescription
                .createProfileDescription("Visitor");

        when(iRequestRepositoryJPA
                .findByUserIDAndProfileDescription(any(), any()))
                .thenReturn(Optional.empty());

        // Act
        Optional<Request> actual = implRequestRepository
                .getRequestByUserIDAndProfileID(userID, profileID);

        // Assert
        assertEquals(Optional.empty(), actual);
    }

    @Test
    void getRequestByID() {
        // Arrange
        Request requestMock = mock(Request.class);
        RequestJPA requestJPAMock = mock(RequestJPA.class);
        RequestID requestID = RequestID.createRequestID();

        when(iRequestRepositoryJPA
                .findById(any()))
                .thenReturn(Optional.of(requestJPAMock));
        when(iRequestAssemblerJPA.toDomain(requestJPAMock))
                .thenReturn(requestMock);

        // Act
        Optional<Request> actual = implRequestRepository
                .getRequestByID(requestID);

        // Assert
        assertEquals(Optional.of(requestMock), actual);
    }

    @Test
    void getRequestByIDNotInRepo() {
        // Arrange
        RequestID requestID = RequestID.createRequestID();

        when(iRequestRepositoryJPA
                .findById(any()))
                .thenReturn(Optional.empty());
        // Act
        Optional<Request> actual = implRequestRepository
                .getRequestByID(requestID);

        // Assert
        assertEquals(Optional.empty(), actual);
    }
}
