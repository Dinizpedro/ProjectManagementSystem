package switchfive.project.applicationServices.appServices.implAppServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchfive.project.interfaceAdapters.domainWS.ProfileWS;
import switchfive.project.dtos.ProfileDTO;
import switchfive.project.applicationServices.appServices.iappServices.IAppProfileService;
import switchfive.project.mappers.mappersApp.iMappers.IProfileMapper;
import switchfive.project.applicationServices.iRepositories.IProfileRepository;
import switchfive.project.applicationServices.iRepositoriesWS.IProfileWebRepository;
import switchfive.project.domain.aggregates.profile.Profile;
import switchfive.project.domain.factories.iFactories.ProfileFactory;
import switchfive.project.domain.shared.valueObjects.ProfileDescription;

import javax.net.ssl.SSLException;
import java.util.*;

@Service
public class ImplAppProfileService implements IAppProfileService {

    private final IProfileRepository iProfileRepository;

    private final IProfileWebRepository iProfileWebRepository;

    private final ProfileFactory profileFactory;

    private final IProfileMapper iProfileMapper;

    @Autowired
    public ImplAppProfileService(IProfileRepository iProfileRepository,
                                 IProfileWebRepository iProfileWebRepository,
                                 ProfileFactory profileFactory,
                                 IProfileMapper iProfileMapper) {
        this.iProfileRepository = iProfileRepository;
        this.iProfileWebRepository = iProfileWebRepository;
        this.profileFactory = profileFactory;
        this.iProfileMapper = iProfileMapper;
    }

    /**
     * Creates a new Profile and sends it to a repository to be saved. If the profile already exists in the repository,
     * an empty Optional<ProfileDTO> object is returned.
     * @param description - String : description of the new Profile
     * @return ProfileDTO representative of the created Profile
     */
    @Override
    public Optional<ProfileDTO> addNewProfile(String description) {
        ProfileDTO profileDTO = null;

        ProfileDescription profileDescription = ProfileDescription
                .createProfileDescription(description);

        boolean profileExistsInDB = iProfileRepository
                .profileExists(profileDescription);

        if (!profileExistsInDB) {

            Profile newProfile = profileFactory.createProfile(profileDescription);

            Profile profileInDB = iProfileRepository.save(newProfile);

            profileDTO = iProfileMapper.toDTO(profileInDB);
        }

        return Optional.ofNullable(profileDTO);
    }

    /**
     * Gets a Profile in that matches the input description. The JPA repository and external Rest Repository
     * are searched to find a Profile that matches the input description. If not found in any repository,
     * an empty Optional<ProfileDTO> is returned.
     * @param profileDescription - input description : String
     * @return Optional<ProfileDTO> object
     * @throws SSLException
     */
    @Override
    public Optional<ProfileDTO> getProfile(String profileDescription) throws SSLException {

        ProfileDescription profileDescriptionToFind = ProfileDescription
                .createProfileDescription(profileDescription);

        Optional<Profile> profileInRepo = iProfileRepository
                .getProfileByDescription(profileDescriptionToFind);

        ProfileDTO profileDTO = null;

        if (profileInRepo.isPresent()) {
            Profile profileFoundInDB = profileInRepo.get();
            profileDTO = iProfileMapper.toDTO(profileFoundInDB);
        } else {
            Optional<ProfileWS> optProfileInWS = iProfileWebRepository
                    .getProfileByDescription(profileDescriptionToFind);

            if (optProfileInWS.isPresent()) {
                ProfileWS profileFoundInWS = optProfileInWS.get();
                profileDTO = iProfileMapper.toDTO(profileFoundInWS);
            }
        }

        return Optional.ofNullable(profileDTO);
    }

    /**
     * Gets all Profiles in the JPA repository and external Rest Repository.
     * Profiles in list must be unique (have the same description) to avoid returning
     * duplicates
     * @return Set<ProfileDTO> object
     * @throws SSLException
     */
    @Override
    public Set<ProfileDTO> getProfiles() throws SSLException {

        List<Profile> profilesInRepo = iProfileRepository.getProfiles();
        List<ProfileWS> profilesInWS = iProfileWebRepository.getProfiles();
        Set<ProfileDTO> profilesDTO = new HashSet<>();

        for (Profile profile : profilesInRepo) {
            ProfileDTO profileDTO = iProfileMapper.toDTO(profile);
            profilesDTO.add(profileDTO);
        }

        for (ProfileWS profileWS : profilesInWS) {
            ProfileDTO profileDTO = iProfileMapper.toDTO(profileWS);
            profilesDTO.add(profileDTO);
        }


        return profilesDTO;
    }
}
