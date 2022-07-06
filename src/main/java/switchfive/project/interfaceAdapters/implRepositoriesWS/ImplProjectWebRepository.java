package switchfive.project.interfaceAdapters.implRepositoriesWS;

import org.springframework.stereotype.Repository;
import switchfive.project.infrastructure.persistence.repositories.repositoriesREST.iRepositoriesREST.IProjectRestRepository;
import switchfive.project.dataModel.dataREST.ProjectRest;
import switchfive.project.dataModel.dataREST.ProjectRestSimplified;
import switchfive.project.assemblers.assemblersREST.iAssemblersREST.IRestProjectAssembler;
import switchfive.project.applicationServices.iRepositoriesWS.IProjectWebRepository;
import switchfive.project.interfaceAdapters.domainWS.ProjectWS;

import javax.net.ssl.SSLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ImplProjectWebRepository implements IProjectWebRepository {
    private final IProjectRestRepository projectRestRepository;
    private final IRestProjectAssembler restProjectAssembler;

    public ImplProjectWebRepository(IProjectRestRepository projectRestRepository, IRestProjectAssembler restProjectAssembler) {
        this.projectRestRepository = projectRestRepository;
        this.restProjectAssembler = restProjectAssembler;
    }

    /**
     * Looks for a specific projectCode in an external repository and returns a ProjectWS object
     */
    public Optional<ProjectWS> findByCode(String projectCode) throws SSLException, ParseException {
        ProjectWS pretendedProject = null;

        Optional<ProjectRest> optProjectRest = this.projectRestRepository.findProjectByCode(projectCode);

        if (optProjectRest.isPresent()) {
            ProjectRest projectRest = optProjectRest.get();

            if (projectRest.getCode() != null) {
                pretendedProject = restProjectAssembler.toWebServiceDomain(projectRest);
            }

        }

        return Optional.ofNullable(pretendedProject);
    }

    /**
     * Gets all Projects in an external repository as ProjectWS objects
     */
    public List<ProjectWS> findAll() throws SSLException, ParseException {
        List<ProjectWS> projectWSList = new ArrayList<>();
        List<ProjectRestSimplified> projectRestList = this.projectRestRepository.findAllProjects();

        for (ProjectRestSimplified each : projectRestList) {
            projectWSList.add(this.restProjectAssembler.toWebServiceDomain(each));
        }

        return projectWSList;
    }
}
