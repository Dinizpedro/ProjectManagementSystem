package switchfive.project.infrastructure.persistence.assemblers.implAssemblers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchfive.project.assemblers.assemblersJPA.implAssemblersJPA.ImplProfileAssemblerJPA;
import switchfive.project.dataModel.dataJPA.ProfileJPA;
import switchfive.project.domain.aggregates.profile.Profile;
import switchfive.project.domain.factories.iFactories.ProfileFactory;
import switchfive.project.domain.shared.valueObjects.ProfileDescription;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ImplProfileAssemblerJPATest {

    @Mock
    ProfileFactory profileFactory;

    @InjectMocks
    private ImplProfileAssemblerJPA implProfileAssemblerJPA;

    @Test
    void toDomain() {
        // Arrange
        ProfileJPA profileJPA = new ProfileJPA();
        profileJPA.setProfileDescription("Description");

        ProfileDescription expectedDescription = ProfileDescription
                .createProfileDescription("Description");
        Profile expected = new Profile(expectedDescription);

        when(profileFactory.createProfile(expectedDescription)).thenReturn(expected);

        // Act
        Profile result = implProfileAssemblerJPA.toDomain(profileJPA);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void toData() {
        // Arrange
        ProfileDescription description = ProfileDescription
                .createProfileDescription("Description");
        Profile profile = new Profile(description);

        // Act
        ProfileJPA result = implProfileAssemblerJPA.toData(profile);

        // Assert
        assertEquals("Description",result.getProfileDescription());
    }
}
