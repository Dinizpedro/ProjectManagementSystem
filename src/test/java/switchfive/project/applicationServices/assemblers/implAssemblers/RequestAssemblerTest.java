package switchfive.project.applicationServices.assemblers.implAssemblers;

import org.junit.jupiter.api.Test;
import switchfive.project.dtos.RequestDTO;
import switchfive.project.domain.aggregates.request.Request;
import switchfive.project.domain.shared.valueObjects.Email;
import switchfive.project.domain.shared.valueObjects.ProfileDescription;
import switchfive.project.domain.shared.valueObjects.RequestID;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class RequestAssemblerTest {

    @Test
    void toDto() {
        // Arrange
        RequestID requestID = RequestID.createRequestID();
        Email userID = Email.create("abilio@isep.ipp.pt");
        ProfileDescription profileID = ProfileDescription
                .createProfileDescription("Visitor");
        LocalDate creationDate = java.time.LocalDate.parse("2010-09-29");
        Request request = new Request(requestID, userID, profileID, creationDate,
                false);

        // Act
        RequestDTO result = RequestAssembler.toDto(request);

        // Assert
        assertEquals(request.getIdentity(), result.requestID);
        assertEquals("Visitor", result.profileDescription);
        assertEquals("2010-09-29", result.creationDate);
        assertEquals("abilio@isep.ipp.pt", result.userID);
        assertFalse(result.isApproved);
    }
}
