package switchfive.project.domain.factories.iFactories;

import switchfive.project.domain.aggregates.profile.Profile;
import switchfive.project.domain.shared.valueObjects.ProfileDescription;

public interface ProfileFactory {
    Profile createProfile(ProfileDescription profileDescription);
}
