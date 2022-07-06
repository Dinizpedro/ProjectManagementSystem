package switchfive.project.interfaceAdapters.domainWS;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfileWSTest {

    @Test
    void ProfileWS_shouldThrowException() {

        // Arrange


        // Act

        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> new ProfileWS(null));
    }

    @Test
    void ProfileWS_GetterAndSetter() {

        // Arrange
        ProfileWS profile = new ProfileWS("");
        profile.setProfileDescription("Teste");

        // Act

        // Assert
        assertEquals(profile.getProfileDescription(),"Teste");
    }


}