package switchfive.project.infrastructure.persistence.repositories.repositoriesREST.iRepositoriesREST;

import switchfive.project.dataModel.dataREST.ProfileRest;

import javax.net.ssl.SSLException;
import java.util.List;
import java.util.Optional;

public interface IProfileRestRepository {
    Optional<ProfileRest> findProfileInExternalServiceByProfileDescription(String profileDescription) throws SSLException;

    List<ProfileRest> findAllProfilesInExternalService() throws SSLException;
}
