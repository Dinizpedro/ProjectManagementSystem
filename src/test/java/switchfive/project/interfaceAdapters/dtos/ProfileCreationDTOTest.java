package switchfive.project.interfaceAdapters.dtos;

import org.junit.jupiter.api.Test;
import switchfive.project.dtos.ProfileCreationDTO;

import static org.junit.jupiter.api.Assertions.*;

class ProfileCreationDTOTest {



    @Test
    void sameProfile() {

        ProfileCreationDTO dtoOne = new ProfileCreationDTO("Profile");
        ProfileCreationDTO dtoTwo = new ProfileCreationDTO("Profile");

        assertEquals(dtoOne,dtoTwo);
        assertEquals(dtoOne.hashCode(),dtoTwo.hashCode());
    }

    @Test
    void testEqualsSameObject() {
        // Arrange
        ProfileCreationDTO dtoOne = new ProfileCreationDTO("Profile");

        // Act/Assert
        assertEquals(dtoOne,dtoOne);
    }

    @Test
    void testEqualsFalse() {
        // Arrange
        ProfileCreationDTO dtoOne = new ProfileCreationDTO("Profile");
        ProfileCreationDTO dtoTwo = new ProfileCreationDTO("Visitor");

        // Act/Assert
        assertNotEquals(dtoOne,dtoTwo);
    }

    @Test
    void testEqualsFalseNullObject() {
        // Arrange
        ProfileCreationDTO dtoOne = new ProfileCreationDTO("Profile");

        // Act/Assert
        assertNotEquals(dtoOne,null);
    }

    @Test
    void testHashCodeDifferent() {
        // Arrange
        ProfileCreationDTO dtoOne = new ProfileCreationDTO("Profile");
        ProfileCreationDTO dtoTwo = new ProfileCreationDTO("Visitor");

        // Act/Assert
        assertNotEquals(dtoOne.hashCode(),dtoTwo.hashCode());
    }
}
