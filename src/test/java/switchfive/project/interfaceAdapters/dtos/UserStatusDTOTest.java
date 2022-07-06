package switchfive.project.interfaceAdapters.dtos;

import org.junit.jupiter.api.Test;
import switchfive.project.dtos.UserStatusDTO;

import static org.junit.jupiter.api.Assertions.*;

class UserStatusDTOTest {

    UserStatusDTO dtoOne = new UserStatusDTO();
    UserStatusDTO dtoTwo = new UserStatusDTO();

    @Test
    void testEquals() {

        // Arrange
        dtoOne.setEmail("123@isep.ipp.pt");
        dtoTwo.setEmail("123@isep.ipp.pt");

        dtoOne.setActivated(true);
        dtoTwo.setActivated(true);

        // Arrange
        assertEquals(dtoOne.getEmail(),dtoTwo.getEmail());
        assertEquals(dtoOne.isActivated(),dtoTwo.isActivated());
        assertEquals(dtoOne,dtoTwo);
    }

    @Test
    void testHashCode() {

        // Arrange
        assertEquals(dtoOne.hashCode(),dtoTwo.hashCode());

    }
}
