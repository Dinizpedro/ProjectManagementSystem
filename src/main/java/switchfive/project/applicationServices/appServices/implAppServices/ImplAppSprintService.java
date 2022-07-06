package switchfive.project.applicationServices.appServices.implAppServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchfive.project.dtos.SprintCreationDTO;
import switchfive.project.dtos.SprintDTO;
import switchfive.project.applicationServices.appServices.iappServices.IAppSprintService;
import switchfive.project.applicationServices.assemblers.iAssemblers.ISprintAssembler;
import switchfive.project.applicationServices.iRepositories.IProjectRepository;
import switchfive.project.applicationServices.iRepositories.ISprintRepository;
import switchfive.project.domain.aggregates.project.Project;
import switchfive.project.domain.aggregates.sprint.Sprint;
import switchfive.project.domain.domainServices.iDomainServices.ISprintDomainService;
import switchfive.project.domain.factories.iFactories.ISprintFactory;
import switchfive.project.domain.shared.valueObjects.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImplAppSprintService implements IAppSprintService {
    private ISprintRepository sprintRepository;
    private IProjectRepository projectRepository;
    private ISprintFactory sprintFactory;
    private ISprintDomainService sprintDomainService;
    private ISprintAssembler sprintAssembler;

    @Autowired
    public ImplAppSprintService(ISprintRepository sprintRepository,
                                IProjectRepository projectRepository,
                                ISprintFactory sprintFactory,
                                ISprintDomainService sprintDomainService,
                                ISprintAssembler sprintAssembler) {
        this.sprintRepository = sprintRepository;
        this.projectRepository = projectRepository;
        this.sprintFactory = sprintFactory;
        this.sprintDomainService = sprintDomainService;
        this.sprintAssembler = sprintAssembler;
    }

    public Optional<SprintDTO> getSprintBySprintId(final SprintID id)
            throws ParseException {

        ProjectCode code = ProjectCode.create(id.getProjectCode());

        if (!this.projectRepository.projectExists(code)) {
            throw new IllegalArgumentException("Selected project does not exist!");
        }

        if (this.sprintRepository.findBySprintID(id).isEmpty()) {
            return Optional.empty();
        }

        Sprint selectedSprint = this.sprintRepository.findBySprintID(id).get();

        return Optional.of(this.sprintAssembler.toDTO(selectedSprint));
    }

    public Optional<List<SprintDTO>> getSprintsByProjectCode(String code) throws ParseException {
        SprintDTO eachSprintDTO;
        List<SprintDTO> sprintsDTO = new ArrayList<>();

        ProjectCode projectCode = ProjectCode.create(code);

        if (!this.projectRepository.projectExists(projectCode)) {
            throw new IllegalArgumentException("Selected project does not exist!");
        }

        if (sprintRepository.findAllSprintsByProjectCode(projectCode).isEmpty()) {
            return Optional.empty();
        }

        List<Sprint> sprints = sprintRepository.findAllSprintsByProjectCode(projectCode).get();

        for (Sprint each : sprints) {
            eachSprintDTO = sprintAssembler.toDTO(each);
            sprintsDTO.add(eachSprintDTO);
        }

        return Optional.of(sprintsDTO);
    }

    public SprintDTO createAndSaveSprint(final SprintCreationDTO dto, final String codeByPath)
            throws ParseException {
        int index = 0;
        Time previousSprintTime = null;

        SprintStatus status = SprintStatus.PLANNED;
        ProjectCode code = ProjectCode.create(codeByPath);
        Time newSprintTime = Time.create(dto.getStartDate(), dto.getEndDate());
        SprintDescription description = SprintDescription.create(dto.getDescription());

        if (!projectRepository.projectExists(code)) {
            throw new IllegalArgumentException("Selected project does not exist!");
        }

        Project project = this.projectRepository.findByCode(code).get();

        if (sprintRepository.findAllSprintsByProjectCode(code).isPresent()) {
            index = (int) this.sprintRepository.countSprintsByProjectCode(code);

            SprintID previousSprintID = SprintID.createSprintID(codeByPath, index);

            Sprint previousSprint = sprintRepository.findBySprintID(previousSprintID).get();

            String previousSprintStartDate = previousSprint.getDates().startDate;
            String previousSprintEndDate = previousSprint.getDates().endDate;
            previousSprintTime = Time.create(previousSprintStartDate, previousSprintEndDate);
        }

        if (!sprintDomainService.sprintIsValid(previousSprintTime, newSprintTime, project)) {
            throw new IllegalArgumentException("Invalid dates!");
        }

        SprintNumber number = SprintNumber.create(index + 1);
        SprintID id = SprintID.createSprintID(codeByPath, index + 1);

        Sprint newSprint = this.sprintFactory.create(id, code, number, newSprintTime, description, status);

        Sprint saved = this.sprintRepository.saveSprint(newSprint);

        return this.sprintAssembler.toDTO(saved);
    }
}
