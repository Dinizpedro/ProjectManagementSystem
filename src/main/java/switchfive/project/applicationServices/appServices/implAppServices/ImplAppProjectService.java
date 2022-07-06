package switchfive.project.applicationServices.appServices.implAppServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import switchfive.project.applicationServices.iRepositoriesWS.IProjectWebRepository;
import switchfive.project.interfaceAdapters.controllers.implControllers.*;
import switchfive.project.dtos.ActivityDTO;
import switchfive.project.dtos.ProjectDTO;
import switchfive.project.dtos.ProjectDTO_Domain;
import switchfive.project.dtos.UpdateProjectDTO;
import switchfive.project.interfaceAdapters.domainWS.ProjectWS;
import switchfive.project.applicationServices.appServices.iappServices.IAppProjectService;
import switchfive.project.applicationServices.appServices.iappServices.IAppResourceService;
import switchfive.project.mappers.mappersWS.iWsMappers.IProjectWSMapper;
import switchfive.project.mappers.mappersApp.iMappers.IProjectMapper;
import switchfive.project.applicationServices.assemblers.iAssemblers.IProjectAssembler;
import switchfive.project.applicationServices.iRepositories.*;
import switchfive.project.domain.aggregates.project.IProjectBuilder;
import switchfive.project.domain.aggregates.project.Project;
import switchfive.project.domain.aggregates.resource.Resource;
import switchfive.project.domain.aggregates.task.Task;
import switchfive.project.domain.aggregates.userStory.UserStory;
import switchfive.project.domain.shared.valueObjects.*;

import javax.net.ssl.SSLException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public final class ImplAppProjectService implements
        IAppProjectService {
    private final IProjectRepository projectRepository;
    private final IUserRepository userRepository;
    private final ITypologyRepository typologyRepository;
    private final ICustomerRepository customerRepository;
    private final IResourceRepository resourceRepository;
    private final IProjectBuilder projectBuilder;
    private final IAppResourceService resourceService;
    private final IProjectMapper projectMapper;
    private final IProjectAssembler projectAssembler;
    private final IUserStoryRepository userStoryRepository;
    private final ITaskRepository taskRepository;
    private final IProjectWebRepository projectWebRepository;
    private final IProjectWSMapper projectWSMapper;


    @Autowired
    public ImplAppProjectService(
            IProjectRepository projectRepositoryInput,
            IUserRepository userRepositoryInput,
            ITypologyRepository typologyRepositoryInput,
            ICustomerRepository customerRepositoryInput,
            IResourceRepository resourceRepositoryInput,
            IProjectBuilder iProjectBuilderInput,
            IAppResourceService iResourceServiceInput,
            IProjectMapper projectMapper,
            IProjectAssembler projectAssembler,
            IUserStoryRepository userStoryRepository,
            ITaskRepository taskRepository,
            IProjectWebRepository projectWebRepository,
            IProjectWSMapper projectWSMapper) {

        this.projectRepository = projectRepositoryInput;
        this.userRepository = userRepositoryInput;
        this.typologyRepository = typologyRepositoryInput;
        this.customerRepository = customerRepositoryInput;
        this.resourceRepository = resourceRepositoryInput;
        this.projectBuilder = iProjectBuilderInput;
        this.resourceService = iResourceServiceInput;
        this.projectMapper = projectMapper;
        this.projectAssembler = projectAssembler;
        this.userStoryRepository = userStoryRepository;
        this.taskRepository = taskRepository;
        this.projectWebRepository = projectWebRepository;
        this.projectWSMapper = projectWSMapper;
    }

    /**
     * Gets a Project in that matches the input projectCode.
     * The JPA repository and external Rest Repository are searched to find a Project that matches the input description.
     * If not found in any repository, an empty Optional is returned.
     */
    public Optional<ProjectDTO> getProjectDTO(final String code) throws ParseException, SSLException, NoSuchAlgorithmException {
        ProjectDTO dto = null;
        ProjectCode projectCode;
        Optional<Project> optProject;
        Optional<ProjectWS> optProjectWS;
        Resource projectManager;
        String codePattern = "^[a-zA-Z0-9]{5}+$";

        if (code.matches(codePattern)) {
            projectCode = ProjectCode.create(code);

            if (this.projectRepository.projectExists(projectCode)) {
                optProject = this.projectRepository.findByCode(projectCode);
                projectManager = this.resourceRepository.getProjectManager(projectCode).get();
                dto = this.projectMapper.toDTO(optProject.get(), projectManager);

                addLinksToDto(dto);
            }

            return Optional.ofNullable(dto);
        }

        optProjectWS = projectWebRepository.findByCode(code);

        if (optProjectWS.isPresent()) {
            ProjectWS projectFoundInDB = optProjectWS.get();
            dto = this.projectWSMapper.toDTO(projectFoundInDB);

            Link link = linkTo(methodOn(ImplProjectController.class)
                    .getProject(code)).withSelfRel();

            dto.add(link);
        }

        return Optional.ofNullable(dto);
    }

    /**
     * Gets all Projects in the JPA repository and external Rest Repository.
     */
    public List<ProjectDTO> getAllProjects() throws ParseException, SSLException {
        List<ProjectDTO> projectDTOList = new ArrayList<>();
        List<Project> projectList = this.projectRepository.findAll();
        List<ProjectWS> projectWSList = this.projectWebRepository.findAll();

        for (Project eachP : projectList) {
            ProjectDTO dto = this.projectMapper.toDTO(eachP);
            projectDTOList.add(dto);
        }

        for (ProjectWS eachPWS : projectWSList) {
            ProjectDTO dto = this.projectWSMapper.toDTO(eachPWS);
            projectDTOList.add(dto);
        }

        return projectDTOList;
    }

    /**
     * Creates a new Project and sends it to a repository to be saved.
     * <br>
     * If the project already exists in the repository, or customer / typology/userEmail doesn't exist, throws a Illegal Argument Expection.
     */
    public ProjectDTO createAndSaveProject(final ProjectDTO dto)
            throws ParseException, NoSuchAlgorithmException {

        ProjectDTO_Domain dtoDomain = this.projectAssembler.toDomain(dto);

        if (this.projectRepository.projectExists(dtoDomain.getProjectCode())) {
            throw new IllegalArgumentException("Project already exists.");
        }
        if (!this.customerRepository.customerExists(dtoDomain.getCustomerName())) {
            throw new IllegalArgumentException("Customer doesn't exist.");
        }

        if (!this.typologyRepository.typologyExists(dtoDomain.getTypologyDescription())) {
            throw new IllegalArgumentException("Typology doesn't exist.");
        }

        if (!this.userRepository.userExists(dtoDomain.getUserEmail().getUserEmail())) {
            throw new IllegalArgumentException("Email doesn't exist.");
        }

        Project newProject = this.projectBuilder
                .setCode(dtoDomain.getProjectCode())
                .setName(dtoDomain.getProjectName())
                .setDescription(dtoDomain.getProjectDescription())
                .setBusinessSector(dtoDomain.getProjectBusinessSector())
                .setTime(dtoDomain.getDates())
                .setSprints(dtoDomain.getProjectNumberOfPlannedSprints())
                .setSprintDuration(dtoDomain.getProjectSprintDuration())
                .setBudget(dtoDomain.getProjectBudget())
                .setTypology(dtoDomain.getTypologyDescription())
                .setCustomer(dtoDomain.getCustomerName())
                .setStatus(dtoDomain.getStatus())
                .build();

        Resource projectManager = this.resourceService.createProjectManager(dtoDomain.getUserEmail(),
                dtoDomain.getProjectCode(), dtoDomain.getDates(), dtoDomain.getCostPerHour(), dtoDomain.getPercentageOfAllocation());

        Project newProjectInDB = this.projectRepository.save(newProject);

        Resource newResourceInDB = this.resourceRepository.save(projectManager);

        return this.projectMapper.toDTO(newProjectInDB, newResourceInDB);
    }


    /**
     * @param projectCode projectCode as string
     * @param dtoInput    object with update data.
     * @return dto with updated project data.<p></p>
     * <p>When we use findById() to retrieve an entity within a transactional
     * method, the returned entity is managed by the persistence provider.</p>
     * So, any change to that entity will be automatically persisted in the
     * database, regardless of whether we are invoking the save() method.
     */
    public Optional<ProjectDTO> updateProject(final String projectCode,
                                              final UpdateProjectDTO dtoInput)
            throws ParseException {
        Project thisProject;

        ProjectCode code = ProjectCode.create(projectCode);
        ProjectName name = ProjectName.create(dtoInput.getProjectName());
        ProjectDescription description =
                ProjectDescription.create(dtoInput.getProjectDescription());
        ProjectBusinessSector business = ProjectBusinessSector.create(
                dtoInput.getProjectBusinessSector());
        Time dates =
                Time.create(dtoInput.getStartDate(), dtoInput.getEndDate());
        ProjectNumberOfPlannedSprints sprintNumber =
                ProjectNumberOfPlannedSprints.create(
                        dtoInput.getProjectNumberOfPlannedSprints());
        ProjectSprintDuration sprintDuration = ProjectSprintDuration.create(
                dtoInput.getProjectSprintDuration());
        ProjectBudget budget =
                ProjectBudget.create(dtoInput.getProjectBudget());
        CustomerName customerName =
                CustomerName.create(dtoInput.getCustomerName());
        TypologyDescription typologyDescription =
                TypologyDescription.create(dtoInput.getTypologyDescription());
        ProjectStatus projectStatus = ProjectStatus.valueOf(dtoInput.getStatus());

        if (this.projectRepository.findByCode(code).isEmpty()) {
            return Optional.empty();
        } else {
            thisProject = this.projectRepository.findByCode(code).get();
        }


        if (this.customerRepository.customerExists(customerName)) {

            if (this.typologyRepository.typologyExists(
                    typologyDescription)) {

                thisProject.addName(name);
                thisProject.addDescription(description);
                thisProject.addBusinessSector(business);
                thisProject.addDates(dates);
                thisProject.addNumberOfPlannedSprints(sprintNumber);
                thisProject.addSprintDuration(sprintDuration);
                thisProject.addBudget(budget);
                thisProject.addCustomer(customerName);
                thisProject.addTypologyDescription(typologyDescription);
                thisProject.addStatus(projectStatus);

                this.projectRepository.save(thisProject);

            } else {
                throw new IllegalArgumentException("Typology does not exist!");
            }

        } else {
            throw new IllegalArgumentException("Customer does not exist!");
        }

        return Optional.of(this.projectMapper.toDTO(thisProject));
    }

    /**
     * Search all the activities statuses in a selected project
     * @param projectCode - selected project
     * @return a list of all the status of the project activities,
     * user stories or/and tasks.
     * The list of ActivityDTO has the information of the type of activity,
     * code of activity and activity status.
     * @throws ParseException
     */
    @Override
    public List<ActivityDTO> getActivitiesStatuses(String projectCode) throws ParseException {
        List<ActivityDTO> activitiesDTO =
                new ArrayList<>();

        activitiesDTO = getUserStoriesStatus(
                projectCode, activitiesDTO);

        activitiesDTO = getTasksStatus(
                projectCode, activitiesDTO);

        return activitiesDTO;
    }

    /**
     * Search and get a list of User Stories and their statuses in a selected project.
     * @param projectCode - the selected project
     * @param activitiesDTO - list of ActivityDTO
     * @return a list of ActivityDTO adding the User Stories,
     * with fixed type of activity and then its code and status
     */
    public List<ActivityDTO> getUserStoriesStatus(
            String projectCode,
            List<ActivityDTO> activitiesDTO) {

        ProjectCode projectCodeObj = ProjectCode.create(projectCode);
        List<UserStory> userStories = userStoryRepository.
                getUserStoriesOrderedByStatus(projectCodeObj);

        boolean projectExists = projectRepository.projectExists(projectCodeObj);

        if(projectExists){

        for (UserStory userStory : userStories) {
            String typeOfActivity = "User Story";
            String activityCode = userStory.getUserStoryCode();
            String activityStatus = userStory.getStatus();

            ActivityDTO activityDTO = new ActivityDTO(
                    typeOfActivity, activityCode, activityStatus);

            activitiesDTO.add(activityDTO);
        }

        return activitiesDTO;
        } else {
            throw new IllegalArgumentException("Project doesn't exist!");
        }
    }

    /**
     * Search and get a list of Tasks and their statuses in a selected project.
     * @param projectCode - the selected project
     * @param activitiesDTO - list of ActivityDTO
     * @return a list of ActivityDTO adding the Tasks,
     * with fixed type of activity and then its code and status
     * @throws ParseException
     */
    public List<ActivityDTO> getTasksStatus(
            String projectCode,
            List<ActivityDTO> activitiesDTO) throws ParseException {

        ProjectCode projectCodeObj = ProjectCode.create(projectCode);

        boolean projectExists = projectRepository.projectExists(projectCodeObj);

        if(projectExists){

        List<Task> tasks = taskRepository.
                findTaskAndOrderByStatus(projectCodeObj);

        for (Task task : tasks) {
            String typeOfActivity = "Task";
            TaskID taskID = task.getIdTask();
            String activityCode = taskID.getTaskCode();
            String activityStatus = task.getTaskStatus();

            ActivityDTO activityDTO = new ActivityDTO(
                    typeOfActivity, activityCode, activityStatus);


            activitiesDTO.add(activityDTO);
        }

        return activitiesDTO;
        }
        else {
            throw new IllegalArgumentException("Project doesn't exist!");
        }
    }

    private void addLinksToDto(ProjectDTO dto) throws ParseException, NoSuchAlgorithmException {
        Link link = linkTo(methodOn(ImplProjectController.class)
                .getProject(dto.getProjectCode())).withSelfRel()
                .withType("PUT");

        Link linkToProductBacklog = linkTo(methodOn(ImplUserStoryController.class)
                .getUserStoryByPriority(dto.getProjectCode())).withRel("productBacklog")
                .withType("GET, PUT");

        Link linkToSprints = linkTo(methodOn(ImplSprintController.class)
                .getAllSprintsByProjectCode(dto.getProjectCode())).withRel("sprints")
                .withType("GET, PUT");

        Link linkToActivities = linkTo(methodOn(ImplProjectController.class)
                .getActivitiesStatus(dto.getProjectCode())).withRel("activities")
                .withType("GET");

        Link linkToResourceAddTeamMember = linkTo(methodOn(ImplResourceController.class)
                .definedTeamMemberOfAProject(null))
                .withRel("addTeamMember")
                .withType("POST");

        Link linkToResourceAddScrumMaster = linkTo(methodOn(ImplResourceController.class)
                .definedScrumMasterOfAProject(null))
                .withRel("addScrumMaster")
                .withType("POST");

        Link linkToResources = linkTo(methodOn(ImplResourceController.class)
                .getAllResourcesByProjectCode(dto.getProjectCode())).withRel("resources")
                .withType("GET");

        Link linkToResourceAddProductOwner = linkTo(methodOn(ImplResourceController.class)
                .definedProductOwnerOfAProject(null))
                .withRel("addProductOwner")
                .withType("POST");

        dto.add(linkToResources, linkToProductBacklog, linkToSprints, linkToActivities,
                linkToResourceAddTeamMember, linkToResourceAddScrumMaster,
                linkToResourceAddProductOwner, link);
    }


}
