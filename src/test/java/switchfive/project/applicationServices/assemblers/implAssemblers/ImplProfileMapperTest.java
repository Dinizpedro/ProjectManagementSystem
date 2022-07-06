package switchfive.project.applicationServices.assemblers.implAssemblers;

import org.junit.jupiter.api.Test;
import switchfive.project.interfaceAdapters.domainWS.ProfileWS;
import switchfive.project.dtos.ProfileDTO;
import switchfive.project.mappers.mappersApp.implMappers.ImplProfileMapper;
import switchfive.project.domain.aggregates.profile.Profile;
import switchfive.project.domain.shared.valueObjects.ProfileDescription;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ImplProfileMapperTest {


    @Test
    void toDTODomainProfile() {
        // Arrange
        ProfileDescription profileDescription = ProfileDescription
                .createProfileDescription("Admin");
        Profile profile = new Profile(profileDescription);
        ImplProfileMapper implProfileMapper = new ImplProfileMapper();

        // Act
        ProfileDTO result = implProfileMapper.toDTO(profile);

        // Assert
        assertEquals("Admin", result.profileDescription);
    }

    @Test
    void toDTOWSProfile() {
        // Arrange
        ProfileWS profile = new ProfileWS("Admin");
        ImplProfileMapper implProfileMapper = new ImplProfileMapper();

        // Act
        ProfileDTO result = implProfileMapper.toDTO(profile);

        // Assert
        assertEquals("Admin", result.profileDescription);
    }


}
