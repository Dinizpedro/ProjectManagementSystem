package switchfive.project.domain.shared.valueObjects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ResourceIDTest {

    @Test
    @DisplayName("Resource have same value as other.")
    void sameValueAs() {
        //Arrange
        UUID uuidMock = mock(UUID.class);

        MockedStatic<UUID> uuidMockedStatic = mockStatic(UUID.class);
        when(UUID.randomUUID()).thenReturn(uuidMock);

        ResourceID resourceID = ResourceID.createResourceID();
        ResourceID otherResourceID = ResourceID.createResourceID();

        //Act
        boolean actual = resourceID.sameValueAs(otherResourceID);
        uuidMockedStatic.close();

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Resource have not same value as other.")
    void notSameValueAs() {
        //Arrange
        ResourceID resourceID = ResourceID.createResourceID();
        ResourceID resourceIdOther = ResourceID.createResourceID();

        //Act
        boolean actual = resourceID.sameValueAs(resourceIdOther);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Resource is the same as other resource.")
    void thisResourceIsTheSameAsOther() {
        //Arrange
        ResourceID resourceID = ResourceID.createResourceID();
        ResourceID otherResourceID = resourceID;

        //Act
        boolean actual = resourceID.equals(otherResourceID);

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Other resource is not an instance of.")
    void otherResourceIsNotAnInstance() {
        //Arrange
        ResourceID resourceID = ResourceID.createResourceID();
        Object otherResource = new Object();

        //Act
        boolean actual = resourceID.equals(otherResource);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Resource have same value as other.")
    void thisResourceIsEqualsToOther() {
        //Arrange
        UUID uuidMock = mock(UUID.class);

        MockedStatic<UUID> uuidMockedStatic = mockStatic(UUID.class);
        when(UUID.randomUUID()).thenReturn(uuidMock);

        ResourceID resourceID = ResourceID.createResourceID();
        ResourceID otherResourceID = ResourceID.createResourceID();

        //Act
        boolean actual = resourceID.equals(otherResourceID);

        uuidMockedStatic.close();

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Resource have not same value as other.")
    void thisResourceIsNotEqualsToOther() {
        //Arrange
        ResourceID resourceID = ResourceID.createResourceID();
        ResourceID otherResourceID = ResourceID.createResourceID();

        //Act
        boolean actual = resourceID.equals(otherResourceID);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Two equals resources have same hashcode.")
    void testHashCode() {
        //Arrange
        UUID uuidMock = mock(UUID.class);

        MockedStatic<UUID> uuidMockedStatic = mockStatic(UUID.class);
        when(UUID.randomUUID()).thenReturn(uuidMock);

        ResourceID resourceID = ResourceID.createResourceID();
        ResourceID otherResourceID = ResourceID.createResourceID();

        //Act
        boolean actual = resourceID.hashCode() == otherResourceID.hashCode();

        uuidMockedStatic.close();

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Two different resources have different hashcode.")
    void testHashCodeFails() {
        //Arrange
        ResourceID resourceID = ResourceID.createResourceID();
        ResourceID otherResourceID = ResourceID.createResourceID();

        //Act
        boolean actual = resourceID.hashCode() == otherResourceID.hashCode();

        //Assert
        assertFalse(actual);
    }

    @Test
    void createResourceID() {
        String fromString = "123e4567-e89b-12d3-a456-426614174000";

        ResourceID resourceID = ResourceID.createResourceID(fromString);

        ResourceID resourceIdOther = ResourceID.createResourceID(fromString);

        //Act
        boolean actual = resourceID.sameValueAs(resourceIdOther);

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Get ID correctly")
    void getIdentity() {
        //Arrange

        String thisID = ResourceID.createResourceID().toString();
        String thatID = thisID;
        //Act
        boolean isTheSame = thisID == thatID;
        //Assert
        assertTrue(isTheSame);


    }
}
