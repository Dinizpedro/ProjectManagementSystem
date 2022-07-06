package switchfive.project.interfaceAdapters.implRepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switchfive.project.assemblers.assemblersJPA.iAssemblersJPA.ISprintAssemblerJPA;
import switchfive.project.dataModel.dataJPA.SprintJPA;
import switchfive.project.infrastructure.persistence.repositories.iRepositoriesJPA.ISprintRepositoryJPA;
import switchfive.project.applicationServices.iRepositories.ISprintRepository;
import switchfive.project.domain.aggregates.sprint.Sprint;
import switchfive.project.domain.shared.valueObjects.ProjectCode;
import switchfive.project.domain.shared.valueObjects.SprintID;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ImplSprintRepository implements ISprintRepository {
    private ISprintRepositoryJPA sprintRepositoryJPA;
    private ISprintAssemblerJPA sprintAssemblerJPA;

    @Autowired
    public ImplSprintRepository(ISprintRepositoryJPA repositoryJPA,
                                ISprintAssemblerJPA assemblerJPA) {
        this.sprintRepositoryJPA = repositoryJPA;
        this.sprintAssemblerJPA = assemblerJPA;
    }

    public Optional<Sprint> findBySprintID(final SprintID id) throws ParseException {
        SprintJPA selected;

        if (this.sprintRepositoryJPA.existsById(id)) {
            selected = this.sprintRepositoryJPA.findById(id).get();
            Sprint sprint = this.sprintAssemblerJPA.toDomain(selected);
            return Optional.of(sprint);
        }

        return Optional.empty();
    }

    public Sprint saveSprint(final Sprint sprint) throws ParseException {
        SprintJPA sprintJPA = new SprintJPA(sprint);

        SprintJPA result = this.sprintRepositoryJPA.save(sprintJPA);

        return this.sprintAssemblerJPA.toDomain(result);
    }

    public Optional<List<Sprint>> findAllSprintsByProjectCode(final ProjectCode code)
            throws ParseException {
        List<Sprint> sprintList = new ArrayList<>();
        Sprint thisSprint;

        List<SprintJPA> sprintJpa = (List<SprintJPA>) sprintRepositoryJPA.findAllBySprintID_projectCode(code.getCode());

        if (sprintJpa.isEmpty()) {

            return Optional.empty();
        }

        for (SprintJPA each : sprintJpa) {
            thisSprint = this.sprintAssemblerJPA.toDomain(each);
            sprintList.add(thisSprint);
        }

        return Optional.of(sprintList);
    }

    public long countSprintsByProjectCode(final ProjectCode code) {
        return sprintRepositoryJPA.countSprintJPABySprintID_projectCode(code.getCode());
    }
}
