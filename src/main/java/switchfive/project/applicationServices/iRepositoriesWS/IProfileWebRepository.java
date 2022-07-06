package switchfive.project.applicationServices.iRepositoriesWS;

import switchfive.project.interfaceAdapters.domainWS.ProfileWS;
import switchfive.project.domain.shared.valueObjects.ProfileDescription;

import javax.net.ssl.SSLException;
import java.util.List;
import java.util.Optional;

public interface IProfileWebRepository {
    Optional<ProfileWS> getProfileByDescription(ProfileDescription profileDescription) throws SSLException;

    List<ProfileWS> getProfiles() throws SSLException;

}
