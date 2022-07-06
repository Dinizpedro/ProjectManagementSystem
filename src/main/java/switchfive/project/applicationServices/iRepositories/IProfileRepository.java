package switchfive.project.applicationServices.iRepositories;

import switchfive.project.domain.aggregates.profile.Profile;
import switchfive.project.domain.shared.valueObjects.ProfileDescription;

import java.util.List;
import java.util.Optional;

public interface IProfileRepository {
    Optional<Profile> getProfileByDescription(ProfileDescription profileDescription);

    Profile save(final Profile newProfile);

    boolean profileExists(String profileDescription);

    boolean profileExists(ProfileDescription profileDescription);

    List<Profile> getProfiles();
}
