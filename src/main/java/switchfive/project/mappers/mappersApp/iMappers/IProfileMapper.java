package switchfive.project.mappers.mappersApp.iMappers;

import switchfive.project.interfaceAdapters.domainWS.ProfileWS;
import switchfive.project.dtos.ProfileDTO;
import switchfive.project.domain.aggregates.profile.Profile;

public interface IProfileMapper {

    ProfileDTO toDTO(Profile profile);

    ProfileDTO toDTO(ProfileWS profile);

}
