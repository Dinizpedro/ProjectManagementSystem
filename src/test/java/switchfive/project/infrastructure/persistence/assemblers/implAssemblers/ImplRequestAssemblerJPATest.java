package switchfive.project.infrastructure.persistence.assemblers.implAssemblers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switchfive.project.assemblers.assemblersJPA.implAssemblersJPA.ImplRequestAssemblerJPA;
import switchfive.project.dataModel.dataJPA.RequestJPA;
import switchfive.project.domain.aggregates.request.Request;
import switchfive.project.domain.factories.iFactories.RequestFactory;
import switchfive.project.domain.shared.valueObjects.Email;
import switchfive.project.domain.shared.valueObjects.ProfileDescription;
import switchfive.project.domain.shared.valueObjects.RequestID;

import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@SpringBootTest
@Transactional
class ImplRequestAssemblerJPATest {

    @Autowired
    RequestFactory requestFactory;

    @Autowired
    ImplRequestAssemblerJPA implRequestAssemblerJPA;

    @Test
    void toDomain() {
        // Arrange
        RequestJPA requestJPA = new RequestJPA();
        requestJPA.setApproved(false);
        requestJPA.setCreationDate("2010-09-29");
        requestJPA.setProfileDescription("Visitor");
        requestJPA.setIdentity("123e4567-e89b-12d3-a456-426614174000");
        requestJPA.setUserID("abilio@isep.ipp.pt");

        RequestID requestID = RequestID.createRequestID(requestJPA.getIdentity());
        Email userID = Email.create(requestJPA.getUserID());
        ProfileDescription profileID = ProfileDescription
                .createProfileDescription(requestJPA.getProfileDescription());

        Request expected = new Request(requestID,userID,profileID);

        // Act
        Request result = implRequestAssemblerJPA.toDomain(requestJPA);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void toData() {
        // Arrange
        RequestID requestID = RequestID.createRequestID();
        Email userID = Email.create("abilio@isep.ipp.pt");
        ProfileDescription profileID = ProfileDescription
                .createProfileDescription("Visitor");
        LocalDate creationDate = java.time.LocalDate.parse("2010-09-29");
        Request request = new Request(requestID, userID, profileID, creationDate,
                true);


        // Act
        RequestJPA result = implRequestAssemblerJPA.toData(request);

        // Assert
        assertEquals(request.getIdentity(), result.getIdentity());
        assertEquals("Visitor",result.getProfileDescription());
        assertEquals("abilio@isep.ipp.pt",result.getUserID());
        assertEquals("2010-09-29",result.getCreationDate());
        assertTrue(result.isApproved());
    }
}
