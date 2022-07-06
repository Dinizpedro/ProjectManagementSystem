package switchfive.project.applicationServices.iRepositoriesWS;

import switchfive.project.interfaceAdapters.domainWS.ProjectWS;

import javax.net.ssl.SSLException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface IProjectWebRepository {

    Optional<ProjectWS> findByCode(String projectCode) throws SSLException, ParseException;

    List<ProjectWS> findAll() throws SSLException, ParseException;

}
