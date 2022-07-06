package switchfive.project.applicationServices.appServices.iappServices;

import switchfive.project.dtos.ProfileDTO;

import javax.net.ssl.SSLException;
import java.util.Optional;
import java.util.Set;

public interface IAppProfileService {

    Optional<ProfileDTO> addNewProfile(String description);

    Optional<ProfileDTO> getProfile(String profileDescription) throws SSLException;

    Set<ProfileDTO> getProfiles() throws SSLException;

}
