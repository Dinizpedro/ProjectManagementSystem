package switchfive.project.assemblers.assemblersJPA.iAssemblersJPA;

import switchfive.project.dataModel.dataJPA.ProfileJPA;
import switchfive.project.domain.aggregates.profile.Profile;

public interface IProfileAssemblerJPA {

    Profile toDomain(ProfileJPA profileJPA);

    ProfileJPA toData(Profile profile);

}
