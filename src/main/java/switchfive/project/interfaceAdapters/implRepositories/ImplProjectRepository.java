package switchfive.project.interfaceAdapters.implRepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switchfive.project.assemblers.assemblersJPA.iAssemblersJPA.IProjectAssemblerJPA;
import switchfive.project.dataModel.dataJPA.ProjectJPA;
import switchfive.project.infrastructure.persistence.repositories.iRepositoriesJPA.IProjectRepositoryJPA;
import switchfive.project.applicationServices.iRepositories.IProjectRepository;
import switchfive.project.domain.aggregates.project.Project;
import switchfive.project.domain.shared.valueObjects.ProjectCode;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ImplProjectRepository implements IProjectRepository {
    private final IProjectRepositoryJPA projectRepositoryJPA;
    private final IProjectAssemblerJPA projectAssembler;

    @Autowired
    public ImplProjectRepository(IProjectRepositoryJPA projectRepositoryJPA,
                                 IProjectAssemblerJPA projectAssembler) {
        this.projectRepositoryJPA = projectRepositoryJPA;
        this.projectAssembler = projectAssembler;
    }


    public Project save(final Project newProject) throws ParseException {
        ProjectJPA projectJPA = new ProjectJPA(newProject);
        ProjectJPA newProjectJPAInDB = this.projectRepositoryJPA.save(projectJPA);
        return this.projectAssembler.toDomain(newProjectJPAInDB);
    }


    /**
     * gets project in database matching the Param ProjectCode.
     * If no Project is found and Empty Optional is returned-
     * Uses JPA Repository to check if project exist and then return it.
     * @param code
     * @return
     * @throws ParseException
     */
    public Optional<Project> findByCode(final ProjectCode code) throws ParseException {
        ProjectJPA theProjectJPA;
        String projectCode = code.getCode();
        Project theProject = null;
        boolean projectExists = this.projectRepositoryJPA.existsByCode(projectCode);

        if (projectExists) {
            theProjectJPA = this.projectRepositoryJPA.findByCode(projectCode).get();
            theProject = this.projectAssembler.toDomain(theProjectJPA);
        }

        return Optional.ofNullable(theProject);
    }

    public boolean projectExists(final ProjectCode projectCode) {
        String projectCodeToFind = projectCode.getCode();

        boolean projectExistsInDB = this.projectRepositoryJPA.existsByCode(projectCodeToFind);

        return projectExistsInDB;
    }

    public List<Project> findAll() throws ParseException {
        List<Project> projectList = new ArrayList<>();
        List<ProjectJPA> projectJPAList = this.projectRepositoryJPA.findAll();

        for (ProjectJPA each : projectJPAList) {
            projectList.add(this.projectAssembler.toDomain(each));
        }

        return projectList;
    }
}
