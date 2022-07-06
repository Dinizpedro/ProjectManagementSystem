package switchfive.project.domain.factories.implFactories;

import org.springframework.stereotype.Component;
import switchfive.project.domain.aggregates.profile.Profile;
import switchfive.project.domain.factories.iFactories.ProfileFactory;
import switchfive.project.domain.shared.valueObjects.ProfileDescription;

@Component
public class ProfileFactoryImplementation implements ProfileFactory {

    @Override
    public Profile createProfile(ProfileDescription profileDescription) {
        return new Profile(profileDescription);
    }
}
