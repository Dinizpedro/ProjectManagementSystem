package switchfive.project.mappers.mappersApp.implMappers;

import org.springframework.stereotype.Component;
import switchfive.project.interfaceAdapters.domainWS.ProfileWS;
import switchfive.project.dtos.ProfileDTO;
import switchfive.project.mappers.mappersApp.iMappers.IProfileMapper;
import switchfive.project.domain.aggregates.profile.Profile;

@Component
public class ImplProfileMapper implements IProfileMapper {

    public ProfileDTO toDTO(Profile profile) {

        String description = profile.getDesignation();

        return new ProfileDTO(description);
    }

    public ProfileDTO toDTO(ProfileWS profileWS) {

        String description = profileWS.getProfileDescription();

        return new ProfileDTO(description);
    }


}
