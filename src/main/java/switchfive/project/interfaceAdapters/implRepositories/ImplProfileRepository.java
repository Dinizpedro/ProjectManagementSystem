package switchfive.project.interfaceAdapters.implRepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switchfive.project.assemblers.assemblersJPA.iAssemblersJPA.IProfileAssemblerJPA;
import switchfive.project.dataModel.dataJPA.ProfileJPA;
import switchfive.project.infrastructure.persistence.repositories.iRepositoriesJPA.IProfileRepositoryJPA;
import switchfive.project.applicationServices.iRepositories.IProfileRepository;
import switchfive.project.domain.aggregates.profile.Profile;
import switchfive.project.domain.shared.valueObjects.ProfileDescription;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ImplProfileRepository implements IProfileRepository {


    private final IProfileRepositoryJPA iProfileRepositoryJPA;
    private final IProfileAssemblerJPA iProfileAssemblerJPA;

    @Autowired
    public ImplProfileRepository(IProfileRepositoryJPA iProfileRepositoryJPA,
                                 IProfileAssemblerJPA iProfileAssemblerJPA) {
        this.iProfileRepositoryJPA = iProfileRepositoryJPA;
        this.iProfileAssemblerJPA = iProfileAssemblerJPA;
    }

    /**
     * Private method that adds the new Profile to the system
     *
     * @param newProfile New Profile object to be validated and added to DataManagement System
     * @return true if the Profile is successfully added to the system
     */
    public Profile save(final Profile newProfile) {
        ProfileJPA profileJPA = iProfileAssemblerJPA.toData(newProfile);
        ProfileJPA profileJPAResult = this.iProfileRepositoryJPA.save(profileJPA);
        Profile profileInDB = iProfileAssemblerJPA.toDomain(profileJPAResult);
        return profileInDB;
    }


    /**
     * Looks for a specific profile description and returns an object
     *
     * @param profileDescription that will be searched
     * @return an Optional profile (model domain object)
     */
    public Optional<Profile> getProfileByDescription(ProfileDescription profileDescription) {
        Profile profileToFind = null;
        String description = profileDescription.getDescription();

        Optional<ProfileJPA> optProfileJPA = this.iProfileRepositoryJPA
                .findProfileJPAByProfileDescription(description);

        if (optProfileJPA.isPresent()) {
            ProfileJPA profileJPA = optProfileJPA.get();

            profileToFind = iProfileAssemblerJPA.toDomain(profileJPA);
        }

        return Optional.ofNullable(profileToFind);
    }

    /**
     * Checks if a ProfileJPA exists in the repository with a specific input description.
     * @param profileDescription - description of the Profile to be found
     * @return boolean
     */
    public boolean profileExists(String profileDescription) {
        return this.iProfileRepositoryJPA.existsProfileJPAByProfileDescription(profileDescription);
    }

    /**
     * Checks if a ProfileJPA exists in the repository with a specific input description.
     * @param profileDescription - ProfileDescription value object of the Profile to be found
     * @return boolean
     */
    public boolean profileExists(ProfileDescription profileDescription) {
        String descriptionToFind = profileDescription.getDescription();
        boolean profileExistsInDB = this.iProfileRepositoryJPA.existsProfileJPAByProfileDescription(descriptionToFind);
        return profileExistsInDB;
    }

    /**
     * Gets all the Profiles present in the JPA database.
     * @return : List<Profile> object
     */
    public List<Profile> getProfiles() {
        Iterable<ProfileJPA> profilesInRepo = this.iProfileRepositoryJPA.findAll();
        List<Profile> profiles = new ArrayList<>();

        for (ProfileJPA profileJPA : profilesInRepo) {
            Profile profileDomain = iProfileAssemblerJPA.toDomain(profileJPA);

            profiles.add(profileDomain);
        }

        return profiles;
    }

}
