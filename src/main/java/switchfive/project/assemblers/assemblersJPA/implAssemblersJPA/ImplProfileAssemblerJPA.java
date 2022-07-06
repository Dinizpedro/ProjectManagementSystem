package switchfive.project.assemblers.assemblersJPA.implAssemblersJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchfive.project.assemblers.assemblersJPA.iAssemblersJPA.IProfileAssemblerJPA;
import switchfive.project.dataModel.dataJPA.ProfileJPA;
import switchfive.project.domain.aggregates.profile.Profile;
import switchfive.project.domain.factories.iFactories.ProfileFactory;
import switchfive.project.domain.shared.valueObjects.ProfileDescription;

@Service
public class ImplProfileAssemblerJPA implements IProfileAssemblerJPA {

    @Autowired
    private ProfileFactory profileFactory;

    public Profile toDomain(ProfileJPA profileJPA) {
        String profileDescriptionJPA = profileJPA.getProfileDescription();

        ProfileDescription newProfileDescription = ProfileDescription
                .createProfileDescription(profileDescriptionJPA);

        return profileFactory.createProfile(newProfileDescription);

    }

    public ProfileJPA toData(Profile profile) {
        String profileDescriptionJPA = profile.getDesignation();

        ProfileJPA profileJPA = new ProfileJPA();
        profileJPA.setProfileDescription(profileDescriptionJPA);

        return profileJPA;

    }


}
