package switchfive.project.model;

import org.junit.jupiter.api.Test;
import switchfive.project.domain.shared.valueObjects.RequestID;

import static org.junit.jupiter.api.Assertions.*;

class ID_RequestTest {

    @Test
    void compareIfObjectIsTheSame() {
        String requestIDString = "123e4567-e89b-12d3-a456-426614174000";

        RequestID requestID = RequestID.createRequestID(requestIDString);

        boolean result = requestID.equals(requestID);

        assertTrue(result);

    }

    @Test
    void objectsIsNull() {

        RequestID requestID = RequestID.createRequestID();

        boolean result = requestID.equals(null);

        assertFalse(result);

    }

    @Test
    void ClassesAreDifferents() {

        RequestID requestID = RequestID.createRequestID();

        String requestId = "cfdcd";

        boolean result = requestID.equals(requestId);

        assertFalse(result);

    }

    @Test
    void compareIDRequest() {
        String firstRequestIDString = "123e4567-e89b-12d3-a456-426614174000";
        RequestID requestID = RequestID.createRequestID(firstRequestIDString);

        String secondRequestIDString = "123e4567-e89b-12d3-a456-426614174000";
        RequestID requestID2 = RequestID.createRequestID(secondRequestIDString);

        boolean result = requestID.equals(requestID2);

        assertTrue(result);

    }

    @Test
    void compareIDRequestAreNotTheSame() {

        RequestID requestID = RequestID.createRequestID();
        RequestID requestID2 = RequestID.createRequestID();

        boolean result = requestID.equals(requestID2);

        assertFalse(result);

    }

    @Test
    void hashRequestID() {
        String firstRequestIDString = "123e4567-e89b-12d3-a456-426614174000";
        RequestID requestID = RequestID.createRequestID(firstRequestIDString);

        String secondRequestIDString = "123e4567-e89b-12d3-a456-426614174000";
        RequestID requestID2 = RequestID.createRequestID(secondRequestIDString);

        int result = requestID.hashCode();
        int expected = requestID2.hashCode();

        assertEquals(result, expected);


    }

    @Test
    void hashRequestIDAreNotTheSame() {

        RequestID requestID = RequestID.createRequestID();
        RequestID requestID2 = RequestID.createRequestID();

        int result = requestID.hashCode();
        int expected = requestID2.hashCode();

        assertNotEquals(result, expected);


    }

}
