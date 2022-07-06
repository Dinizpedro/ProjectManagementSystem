package switchfive.project.model.request;

import org.junit.jupiter.api.Test;
import switchfive.project.domain.aggregates.request.Request;
import switchfive.project.domain.shared.valueObjects.Email;
import switchfive.project.domain.shared.valueObjects.ProfileDescription;
import switchfive.project.domain.shared.valueObjects.RequestID;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class RequestTest {

    @Test
    void testUserIDInputNull() {
        // Arrange
        String fromString = "123e4567-e89b-12d3-a456-426614174000";
        RequestID request = RequestID.createRequestID(fromString);
        ProfileDescription profile1 = ProfileDescription.createProfileDescription("Visitor");

        assertThrows(IllegalArgumentException.class,
                () -> new Request(request, null, profile1, null,
                        true));
    }

    @Test
    void testProfileIDInputNull() {

        // Arrange
        String fromString = "123e4567-e89b-12d3-a456-426614174000";
        RequestID request = RequestID.createRequestID(fromString);
        Email user1 = Email.create("julinho@ksd.com");


        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> new Request(request, user1, null));
    }

    @Test
    void testRequestIDInputNull() {

        // Arrange
        Email user1 = Email.create("julinho@ksd.com");
        ProfileDescription profile1 = ProfileDescription.createProfileDescription("Visitor");


        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> new Request(null, user1, profile1));
    }

    @Test
    void testEqualsDifferentObjectsEqualSameClass() {
        //Arrange
        String firstRequestIDString = "123e4567-e89b-12d3-a456-426614174000";
        RequestID requestID = RequestID.createRequestID(firstRequestIDString);

        String secondRequestIDString = "123f4567-e89b-12d3-a456-426614174000";
        RequestID requestID2 = RequestID.createRequestID(secondRequestIDString);
        Email user1 = Email.create("julinho@ksd.com");
        ProfileDescription profile1 = ProfileDescription.createProfileDescription("Visitor");

        //Act
        Request firstRequest = new Request(requestID, user1, profile1);
        Request secondRequest = new Request(requestID2, user1, profile1);

        boolean result = firstRequest.equals(secondRequest);

        assertFalse(result);
    }

    @Test
    void testEqualsSameObjectsEqualSameClass() {
        //Arrange
        String fromString = "123e4567-e89b-12d3-a456-426614174000";
        RequestID request = RequestID.createRequestID(fromString);
        RequestID request2 = RequestID.createRequestID(fromString);
        Email user1 = Email.create("julinho@ksd.com");
        ProfileDescription profile1 = ProfileDescription.createProfileDescription("Visitor");

        //Act
        Request firstRequest = new Request(request, user1, profile1);
        Request secondRequest = new Request(request2, user1, profile1);

        boolean result = firstRequest.equals(secondRequest);

        assertTrue(result);

    }

    @Test
    void testEqualsSameObject() {

        //Arrange
        String fromString = "123e4567-e89b-12d3-a456-426614174000";
        RequestID request = RequestID.createRequestID(fromString);
        Email user1 = Email.create("julinho@ksd.com");
        ProfileDescription profile1 = ProfileDescription.createProfileDescription("Visitor");

        //Act
        Request request1 = new Request(request, user1, profile1);

        boolean result = request1.equals(request1);

        assertTrue(result);

    }

    @Test
    void testEqualsNullObject() {
        //Arrange
        String fromString = "123e4567-e89b-12d3-a456-426614174000";
        RequestID request = RequestID.createRequestID(fromString);
        Email user1 = Email.create("julinho@ksd.com");
        ProfileDescription profile1 = ProfileDescription.createProfileDescription("Visitor");

        //Act
        Request request1 = new Request(request, user1, profile1);

        boolean result = request1.equals(null);

        assertFalse(result);

    }

    @Test
    void testEqualsDifferentObjectClass() {
        //Arrange
        String fromString = "123e4567-e89b-12d3-a456-426614174000";
        RequestID request = RequestID.createRequestID(fromString);
        Email user1 = Email.create("julinho@ksd.com");
        ProfileDescription profile1 = ProfileDescription.createProfileDescription("Visitor");
        LocalDate date = java.time.LocalDate.now();

        //Act
        Request request1 = new Request(request, user1, profile1,
                date, true);
        String request2 = "cffc";

        boolean result = request1.equals(request2);

        assertFalse(result);

    }

    @Test
    void isAproved() {
        // Arrange
        String fromString = "123e4567-e89b-12d3-a456-426614174000";
        RequestID request = RequestID.createRequestID(fromString);
        Email user1 = Email.create("julinho@ksd.com");
        ProfileDescription profile1 = ProfileDescription.createProfileDescription("Visitor");

        Request request1 = new Request(request, user1, profile1);

        // Act
        boolean result = request1.approveRequest();

        // Assert
        assertTrue(result);
    }

    @Test
    void compareUserAndProfileOfRequest() {
        // Arrange
        String fromString = "123e4567-e89b-12d3-a456-426614174000";
        RequestID request = RequestID.createRequestID(fromString);
        Email user1 = Email.create("julinho@ksd.com");
        ProfileDescription profile1 = ProfileDescription.createProfileDescription("Visitor");

        Request request1 = new Request(request, user1, profile1);

        // Act
        boolean result = request1.compareUserAndProfileOfRequest(user1, profile1);

        // Assert
        assertTrue(result);
    }

    @Test
    void compareUserAndProfileOfRequestDifferentsUserIDs() {
        // Arrange
        String fromString = "123e4567-e89b-12d3-a456-426614174000";
        RequestID request = RequestID.createRequestID(fromString);
        Email user1 = Email.create("julinho@ksd.com");
        ProfileDescription profile1 = ProfileDescription.createProfileDescription("Visitor");

        Email user2 = Email.create("julio@ksd.com");

        Request request1 = new Request(request, user2, profile1);

        // Act
        boolean result = request1.compareUserAndProfileOfRequest(user1, profile1);

        // Assert
        assertFalse(result);
    }

    @Test
    void compareUserAndProfileOfRequestDifferentsProfilesIDs() {
        // Arrange
        String fromString = "123e4567-e89b-12d3-a456-426614174000";
        RequestID request = RequestID.createRequestID(fromString);
        Email user1 = Email.create("julinho@ksd.com");
        ProfileDescription profile1 = ProfileDescription.createProfileDescription("Visitor");

        ProfileDescription profile2 = ProfileDescription.createProfileDescription("Director");

        Request request1 = new Request(request, user1, profile2);

        // Act
        boolean result = request1.compareUserAndProfileOfRequest(user1, profile1);

        // Assert
        assertFalse(result);
    }


    @Test
    void isRequestApproved() {
        String fromString = "123e4567-e89b-12d3-a456-426614174000";
        RequestID request = RequestID.createRequestID(fromString);
        Email user1 = Email.create("julinho@ksd.com");
        ProfileDescription profile1 = ProfileDescription.createProfileDescription("Visitor");

        Request request1 = new Request(request, user1, profile1);

        request1.approveRequest();

        boolean result = request1.isRequestApproved();

        assertTrue(result);

    }

    @Test
    void isRequestApprovedFalse() {
        String fromString = "123e4567-e89b-12d3-a456-426614174000";
        RequestID request = RequestID.createRequestID(fromString);
        Email user1 = Email.create("julinho@ksd.com");
        ProfileDescription profile1 = ProfileDescription.createProfileDescription("Visitor");

        Request request1 = new Request(request, user1, profile1);

        boolean result = request1.isRequestApproved();

        assertFalse(result);

    }

    @Test
    void testHashCode() {
        String fromString = "123e4567-e89b-12d3-a456-426614174000";
        RequestID request = RequestID.createRequestID(fromString);
        Email user1 = Email.create("julinho@ksd.com");
        ProfileDescription profile1 = ProfileDescription.createProfileDescription("Visitor");

        RequestID requestID = RequestID.createRequestID(fromString);
        Email userID = Email.create("julio@ksd.com");
        ProfileDescription profile2 = ProfileDescription.createProfileDescription("Director");

        Request request1 = new Request(request, user1, profile1);
        Request request2 = new Request(requestID, userID, profile2);

        int result = request1.hashCode();

        assertEquals(result, request2.hashCode());

    }

    @Test
    void testHashCodeDifferent() {
        String firstRequestIDString = "123e4567-e89b-12d3-a456-426614174000";
        RequestID request = RequestID.createRequestID(firstRequestIDString);
        Email user1 = Email.create("julinho@ksd.com");
        ProfileDescription profile1 = ProfileDescription.createProfileDescription("Visitor");

        String secondRequestIDString = "123f4567-e89b-12d3-a456-426614174000";
        RequestID requestID = RequestID.createRequestID(secondRequestIDString);
        Email userID = Email.create("julio@ksd.com");
        ProfileDescription profile2 = ProfileDescription.createProfileDescription("Director");

        Request request1 = new Request(request, user1, profile1);
        Request request2 = new Request(requestID, userID, profile2);

        int result = request1.hashCode();

        assertNotEquals(result, request2.hashCode());
    }

    @Test
    void getIdentityTest() {
        // Arrange
        String fromString = "123e4567-e89b-12d3-a456-426614174000";
        RequestID requestID = RequestID.createRequestID(fromString);
        Email user = Email.create("julinho@ksd.com");
        ProfileDescription profile = ProfileDescription.createProfileDescription("Visitor");

        Request request = new Request(requestID, user, profile);

        // Act
        String result = request.getIdentity();

        // Assert
        assertEquals(fromString, result);
    }

    @Test
    void getCreationDate() {
        // Arrange
        String fromString = "123e4567-e89b-12d3-a456-426614174000";
        RequestID requestID = RequestID.createRequestID(fromString);
        Email user = Email.create("julinho@ksd.com");
        ProfileDescription profile = ProfileDescription.createProfileDescription("Visitor");

        Request request = new Request(requestID, user, profile);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String expected = java.time.LocalDateTime.now().format(dtf);

        // Act
        String result = request.getCreationDate();

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void getUserID() {
        // Arrange
        String expected = "julinho@ksd.com";
        String fromString = "123e4567-e89b-12d3-a456-426614174000";
        RequestID requestID = RequestID.createRequestID(fromString);
        Email user = Email.create("julinho@ksd.com");
        ProfileDescription profile = ProfileDescription.createProfileDescription("Visitor");

        Request request = new Request(requestID, user, profile);

        // Act
        String result = request.getUserID();

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void getProfileID() {
        // Arrange
        String expected = "Admin";
        String fromString = "123e4567-e89b-12d3-a456-426614174000";
        RequestID requestID = RequestID.createRequestID(fromString);
        Email user = Email.create("julinho@ksd.com");
        ProfileDescription profile = ProfileDescription.createProfileDescription(expected);

        Request request = new Request(requestID, user, profile);

        // Act
        String result = request.getProfileDescription();

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void testEqualsSameIdentity() {
        //Arrange
        String fromString = "123e4567-e89b-12d3-a456-426614174000";
        RequestID request = RequestID.createRequestID(fromString);
        Email user1 = Email.create("julinho@ksd.com");
        ProfileDescription profile1 = ProfileDescription.createProfileDescription("Visitor");
        LocalDate date = java.time.LocalDate.now();

        //Act
        Request request1 = new Request(request, user1, profile1,
                date, true);
        String request2 = "cffc";

        boolean result = request1.equals(request2);

        assertFalse(result);

    }
}
