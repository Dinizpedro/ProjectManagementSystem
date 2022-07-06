/*package switchfive.project.d_domain.shared.valueObjects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class TypologyIDTest {


    @Test
    @DisplayName("TypologyID is different from the other.")
    void uuidTypologyIsNotTheSame() {
        //Arrange
        TypologyID typologyID = TypologyID.createTypologyID();
        TypologyID otherTypologyID = TypologyID.createTypologyID();

        //Act
        boolean actual = typologyID.sameValueAs(otherTypologyID);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("TypologyID is the same.")
    void uuidTypologyIsTheSame() {
        //Arrange
        UUID uuidTypology = mock(UUID.class);

        MockedStatic<UUID> uuidMockedStatic = mockStatic(UUID.class);
        when(UUID.randomUUID()).thenReturn(uuidTypology);

        TypologyID theTypologyID = TypologyID.createTypologyID();
        TypologyID otherTypologyID = TypologyID.createTypologyID();

        //Act
        boolean actual = theTypologyID.sameValueAs(otherTypologyID);
        uuidMockedStatic.close();

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Typology is the same as other typology.")
    void thisTypologyIDIsTheSameAsOther() {
        //Arrange
        TypologyID typologyID = TypologyID.createTypologyID();
        TypologyID otherTypologyID = typologyID;

        //Act
        boolean actual = typologyID.sameValueAs(otherTypologyID);

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Other typology is not an instance of.")
    void otherTypologyIsNotAnInstance() {

        //Arrange
        TypologyID typology = TypologyID.createTypologyID();
        Object otherTypology = new Object();

        //Act
        boolean actual = typology.equals(otherTypology);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Same typology has same hashcode.")
    void testHashCode() {
        //Arrange
        TypologyID typologyID = TypologyID.createTypologyID();
        TypologyID otherTypologyID = typologyID;

        //Act
        boolean actual = typologyID.hashCode() == otherTypologyID.hashCode();

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Two different typologies have different hashcode.")
    void testHashCodeFails() {
        //Arrange
        TypologyID typologyID = TypologyID.createTypologyID();
        TypologyID otherTypologyID = TypologyID.createTypologyID();

        //Act
        boolean actual = typologyID.hashCode() == otherTypologyID.hashCode();

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("String is converted to UUID successfully")
    void stringToTypologyID() {
        String iDStringFormat = "123e4567-e89b-12d3-a456-426614174000";
        TypologyID typologyID = TypologyID.stringToTypologyID(iDStringFormat);

        boolean uUIDIsTheSame = Objects.equals(typologyID.getIdentity(), iDStringFormat);

        assertTrue(uUIDIsTheSame);

    }

    @Test
    @DisplayName("Get ID correctly")
    void getIdentity() {
        //Arrange

        String thisID = TypologyID.createTypologyID().getIdentity();
        String thatID = thisID;
        //Act
        boolean isTheSame = thisID == thatID;
        //Assert
        assertTrue(isTheSame);


    }
}*/
