package switchfive.project.interfaceAdapters.implRepositoriesWS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switchfive.project.assemblers.assemblersREST.iAssemblersREST.IRestProfileAssembler;
import switchfive.project.dataModel.dataREST.ProfileRest;
import switchfive.project.infrastructure.persistence.repositories.repositoriesREST.iRepositoriesREST.IProfileRestRepository;
import switchfive.project.interfaceAdapters.domainWS.ProfileWS;
import switchfive.project.applicationServices.iRepositoriesWS.IProfileWebRepository;
import switchfive.project.domain.shared.valueObjects.ProfileDescription;

import javax.net.ssl.SSLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ImplProfileWebRepository implements IProfileWebRepository {

    private final IProfileRestRepository iProfileRestRepository;
    private final IRestProfileAssembler iRestProfileAssembler;

    @Autowired
    public ImplProfileWebRepository(IProfileRestRepository iProfileRestRepository,
                                    IRestProfileAssembler iRestProfileAssembler) {
        this.iProfileRestRepository = iProfileRestRepository;
        this.iRestProfileAssembler = iRestProfileAssembler;
    }

    /**
     * Looks for a specific profile description in an external repository and returns a ProfileWS object
     *
     * @param profileDescription - ProfileDescription object
     * @return an Optional ProfileWS (domain model object specific to the external )
     */
    @Override
    public Optional<ProfileWS> getProfileByDescription(ProfileDescription profileDescription) throws SSLException {
        ProfileWS profileToFind = null;
        String description = profileDescription.getDescription();

        Optional<ProfileRest> optProfileRest = this.iProfileRestRepository
                .findProfileInExternalServiceByProfileDescription(description);

        if (optProfileRest.isPresent()) {
            ProfileRest profileRest = optProfileRest.get();
            profileToFind = iRestProfileAssembler.toDomain(profileRest);
        }

        return Optional.ofNullable(profileToFind);
    }

    /**
     * Gets all Profiles in an external repository as ProfileWS objects
     *
     * @return List<ProfileWS> (domain model object specific to the external )
     */
    @Override
    public List<ProfileWS> getProfiles() throws SSLException {
        List<ProfileRest>  profileRestList =
                iProfileRestRepository.findAllProfilesInExternalService();

        List<ProfileWS> profilesInWS = new ArrayList<>();

        if (profileRestList.size() > 0) {
            profilesInWS = iRestProfileAssembler.toDomain(profileRestList);
        }


        return profilesInWS;
    }
}
