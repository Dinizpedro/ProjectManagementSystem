package switchfive.project.infrastructure.persistence.repositories.repositoriesREST.iRepositoriesREST;

import switchfive.project.dataModel.dataREST.ProjectRest;
import switchfive.project.dataModel.dataREST.ProjectRestSimplified;

import javax.net.ssl.SSLException;
import java.util.List;
import java.util.Optional;

public interface IProjectRestRepository {
    Optional<ProjectRest> findProjectByCode(String projectCode) throws SSLException;

    List<ProjectRestSimplified> findAllProjects() throws SSLException;
}
