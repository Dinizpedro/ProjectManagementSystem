package switchfive.project.interfaceAdapters.dtos;

import org.junit.jupiter.api.Test;
import switchfive.project.dtos.SearchUserDTO;

import static org.junit.jupiter.api.Assertions.*;

class SearchUserDTOTest {

    @Test
    void TestHashCode() {
        // Arrange
        SearchUserDTO dtoOne = new SearchUserDTO();

        dtoOne.setUserProfileList(null);
        dtoOne.setUserName("Tester");
        dtoOne.setFunction("Developer");
        dtoOne.setEmail("123@isep.ipp.pt");
        dtoOne.setActivation(true);

        SearchUserDTO dtoTwo = new SearchUserDTO();

        dtoTwo.setUserProfileList(null);
        dtoTwo.setUserName("Tester");
        dtoTwo.setFunction("Developer");
        dtoTwo.setEmail("123@isep.ipp.pt");
        dtoTwo.setActivation(true);

        // Act

        // Assert
        assertEquals(dtoOne.hashCode(),dtoTwo.hashCode());

    }

}
