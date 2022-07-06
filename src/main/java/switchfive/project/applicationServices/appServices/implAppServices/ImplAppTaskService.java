package switchfive.project.applicationServices.appServices.implAppServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchfive.project.dataModel.dataJPA.TaskIDJPA;
import switchfive.project.dtos.TaskCreationDTO;
import switchfive.project.dtos.TaskDTO;
import switchfive.project.dtos.TaskIdDTO;
import switchfive.project.applicationServices.appServices.iappServices.IAppTaskService;
import switchfive.project.applicationServices.assemblers.implAssemblers.TaskAssembler;
import switchfive.project.applicationServices.iRepositories.*;
import switchfive.project.domain.aggregates.resource.Resource;
import switchfive.project.domain.aggregates.sprint.Sprint;
import switchfive.project.domain.aggregates.task.Task;
import switchfive.project.domain.aggregates.userStory.UserStory;
import switchfive.project.domain.factories.iFactories.ITaskFactory;
import switchfive.project.domain.shared.valueObjects.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImplAppTaskService implements IAppTaskService {

    private final ITaskRepository taskRepository;

    private final ITaskFactory taskFactory;

    private final IResourceRepository iResourceRepository;

    private final ISprintRepository iSprintRepository;

    private final IUserStoryRepository iUserStoryRepository;


    @Autowired
    public ImplAppTaskService(ITaskRepository taskRepository, ITaskFactory taskFactory,
                              IResourceRepository iResourceRepository, ISprintRepository iSprintRepository,
                              IUserStoryRepository iUserStoryRepository) {
        this.taskRepository = taskRepository;
        this.taskFactory = taskFactory;
        this.iResourceRepository = iResourceRepository;
        this.iSprintRepository = iSprintRepository;
        this.iUserStoryRepository = iUserStoryRepository;
    }

    /**
     * Method to create a Task and Save it.
     * @param taskCreationDTO - Data entered by the user to create a task,
     *                        depending on the code inserted a task will be created
     *                        within a Sprint or within a User Story.
     * @return an Optional<TaskDTO> when SprintID or UserStoryCode aren't invalid.
     * @throws ParseException
     */
    public Optional<TaskDTO> createAndSaveTask(TaskCreationDTO taskCreationDTO) throws ParseException {

        TaskDTO taskDTO;
        if (taskCreationDTO.getSprintNumberDto() != null && taskCreationDTO.getUserStoryCode() == null) {
            taskDTO = createTaskInSprint(taskCreationDTO);

        } else if (taskCreationDTO.getSprintNumberDto() == null && taskCreationDTO.getUserStoryCode() != null) {
            taskDTO = createTaskInUserStory(taskCreationDTO);

        } else {
            throw new IllegalArgumentException("SprintID and User Story Code invalid");
        }

        return Optional.ofNullable(taskDTO);

    }

    private TaskDTO createTaskInUserStory(TaskCreationDTO taskCreationDTO) throws ParseException {
        TaskDTO taskDTO = null;
        UserStoryID taskContainerUserStoryID = UserStoryID.createUserStoryID(taskCreationDTO.getProjectCodeDto(),
                taskCreationDTO.getUserStoryCode());

        ProjectCode projectCode = ProjectCode.create(taskCreationDTO.getProjectCodeDto());
        String nextTaskCode = taskRepository.nextTaskCode(projectCode);
        TaskCode taskCode = TaskCode.createTaskCode(nextTaskCode);
        TaskID taskID = TaskID.createIDTask(taskCode, taskContainerUserStoryID);

        UserStoryCode userStoryCode = UserStoryCode.createUserStoryCode(taskContainerUserStoryID
                .getUserStoryCode());
        Optional<UserStory> userStoryOpt = iUserStoryRepository.getUserStory(projectCode, userStoryCode);

        if (userStoryOpt.isPresent()) {
            UserStory userStory = userStoryOpt.get();
            SprintID sprintID = userStory.getSprintID();

            if (sprintID != null) {

                // Check Precedence
                List<TaskIdDTO> precedenceTaskId = taskCreationDTO.getPrecedenceDto();
                boolean checkPrecedence = true;
                if (precedenceTaskId != null) {
                    checkPrecedence = checkTaskPrecedence(precedenceTaskId);
                }

                // Check Dates
                String startDate = taskCreationDTO.getStartDateDto();
                String endDate = taskCreationDTO.getEndDateDto();
                Time taskTime = Time.create(startDate, endDate);
                boolean checkTaskDatesWithinSprint = checkDatesAreWithinSprint(sprintID, taskTime);

                // Check Resource
                ResourceID resourceID = ResourceID.createResourceID(taskCreationDTO.getResponsibleResourceUUIDDto());
                boolean checkResourceIsTeamMember = checkResourceIsTeamMember(projectCode,
                        resourceID, sprintID);

                if (checkPrecedence && checkTaskDatesWithinSprint && checkResourceIsTeamMember) {
                    TaskName taskName = TaskName.createTaskName(taskCreationDTO.getNameDto());
                    TaskDescription taskDescription = TaskDescription.createTaskDescription(taskCreationDTO.getDescriptionDto());
                    EffortEstimate effortEstimate = EffortEstimate.createEffortEstimate(taskCreationDTO.getEffortDto());
                    TypeOfTask typeOfTask = TypeOfTask.valueOf(taskCreationDTO.getTypeOfTaskDto());

                    List<TaskIDJPA> precedenceTaskIDList = createTaskIdListFromDTO(precedenceTaskId);

                    Task task = taskFactory.createTask(taskID, taskName, taskDescription, taskTime, effortEstimate,
                            precedenceTaskIDList, typeOfTask, resourceID);

                    Task taskInRepo = taskRepository.save(task);
                    taskDTO = TaskAssembler.toTaskDTO(taskInRepo);
                }

            }
        }

        return taskDTO;
    }

    private TaskDTO createTaskInSprint(TaskCreationDTO taskCreationDTO) throws ParseException {
        TaskDTO taskDTO = null;
        SprintID taskContainerSprintID = SprintID.createSprintID(taskCreationDTO.getProjectCodeDto(),
                taskCreationDTO.getSprintNumberDto());

        ProjectCode projectCode = ProjectCode.create(taskCreationDTO.getProjectCodeDto());
        String nextTaskCode = taskRepository.nextTaskCode(projectCode);
        TaskCode taskCode = TaskCode.createTaskCode(nextTaskCode);
        TaskID taskID = TaskID.createIDTask(taskCode, taskContainerSprintID);

        // Check Precedence
        List<TaskIdDTO> precedenceTaskId = taskCreationDTO.getPrecedenceDto();
        boolean checkPrecedence = true;

        if (precedenceTaskId != null) {
            checkPrecedence = checkTaskPrecedence(precedenceTaskId);
        }

        // Check Dates
        String startDate = taskCreationDTO.getStartDateDto();
        String endDate = taskCreationDTO.getEndDateDto();
        Time taskTime = Time.create(startDate, endDate);
        boolean checkTaskDatesWithinSprint = checkDatesAreWithinSprint(taskContainerSprintID, taskTime);

        // Check Resource
        ResourceID resourceID = ResourceID.createResourceID(taskCreationDTO.getResponsibleResourceUUIDDto());
        boolean checkResourceSMorTeamMember = checkResourceSMorTeamMember(projectCode,
                resourceID, taskContainerSprintID);

        if (checkPrecedence && checkTaskDatesWithinSprint && checkResourceSMorTeamMember) {
            TaskName taskName = TaskName.createTaskName(taskCreationDTO.getNameDto());
            TaskDescription taskDescription = TaskDescription.createTaskDescription(taskCreationDTO.getDescriptionDto());
            EffortEstimate effortEstimate = EffortEstimate.createEffortEstimate(taskCreationDTO.getEffortDto());
            TypeOfTask typeOfTask = TypeOfTask.valueOf(taskCreationDTO.getTypeOfTaskDto());

            List<TaskIDJPA> precedenceTaskIDList = createTaskIdListFromDTO(precedenceTaskId);

            Task task = taskFactory.createTask(taskID, taskName, taskDescription, taskTime, effortEstimate,
                    precedenceTaskIDList, typeOfTask, resourceID);

            Task taskInRepo = taskRepository.save(task);
            taskDTO = TaskAssembler.toTaskDTO(taskInRepo);
        }
        return taskDTO;
    }

    private boolean checkResourceSMorTeamMember(ProjectCode projectCode,
                                                ResourceID resourceID,
                                                SprintID sprintID)
            throws ParseException {
        boolean checkResourceSMorTeamMember = false;

        Optional<Resource> resourceOpt = iResourceRepository.getResourceByID(resourceID);

        if (resourceOpt.isPresent()) {
            Resource resource = resourceOpt.get();

            if (resource.isResourceInProject(projectCode)) {
                if (resource.isTeamMember() || resource.isScrumMaster()) {
                    String startDate = resource.getStartDate();
                    String endDate = resource.getEndDate();
                    Time resourceTime = Time.create(startDate, endDate);

                    if (checkSprintDatesAreWithinInputTime(sprintID, resourceTime)) {
                        checkResourceSMorTeamMember = true;
                    }
                }
            }
        }

        return checkResourceSMorTeamMember;
    }


    private boolean checkResourceIsTeamMember(ProjectCode projectCode,
                                              ResourceID resourceID,
                                              SprintID sprintID)
            throws ParseException {
        boolean checkResourceIsTeamMember = false;

        Optional<Resource> resourceOpt = iResourceRepository.getResourceByID(resourceID);

        if (resourceOpt.isPresent()) {
            Resource resource = resourceOpt.get();

            if (resource.isResourceInProject(projectCode)) {
                if (resource.isTeamMember()) {
                    String startDate = resource.getStartDate();
                    String endDate = resource.getEndDate();
                    Time resourceTime = Time.create(startDate, endDate);

                    if (checkSprintDatesAreWithinInputTime(sprintID, resourceTime)) {
                        checkResourceIsTeamMember = true;
                    }
                }
            }
        }

        return checkResourceIsTeamMember;
    }

    private boolean checkDatesAreWithinSprint(SprintID sprintID, Time taskTime) throws ParseException {
        boolean checkDatesAreWithinSprint = false;

        Optional<Sprint> sprintOpt = iSprintRepository.findBySprintID(sprintID);

        if (sprintOpt.isPresent()) {
            Sprint sprint = sprintOpt.get();
            checkDatesAreWithinSprint = sprint.areDatesWithinSprintDates(taskTime);

        }

        return checkDatesAreWithinSprint;
    }

    private boolean checkSprintDatesAreWithinInputTime(SprintID sprintID, Time taskTime) throws ParseException {
        boolean checkSprintDatesAreWithinInputTime = false;

        Optional<Sprint> sprintOpt = iSprintRepository.findBySprintID(sprintID);

        if (sprintOpt.isPresent()) {
            Sprint sprint = sprintOpt.get();
            checkSprintDatesAreWithinInputTime = sprint.areSprintDatesInsideInputTime(taskTime);

        }

        return checkSprintDatesAreWithinInputTime;
    }


    private boolean checkTaskPrecedence(List<TaskIdDTO> precedenceTaskId) throws ParseException {

        boolean checkTaskPrecedence = true;
        int iterator = 0;

        while (checkTaskPrecedence && iterator < precedenceTaskId.size()) {
            TaskIdDTO taskIdDTO = precedenceTaskId.get(iterator);
            TaskCode taskCode = TaskCode.createTaskCode(taskIdDTO.getTaskCode());
            ProjectCode projectCode = ProjectCode.create(taskIdDTO.getProjectCode());
            TaskIDJPA taskIDJPA = TaskIDJPA.createTaskIDJPA(taskCode.getCode(),
                    projectCode.getCode());

            Optional<Task> taskOpt = taskRepository.findTaskById(taskIDJPA);

            if (taskOpt.isEmpty()) {
                checkTaskPrecedence = false;
            }

            iterator++;
        }

        return checkTaskPrecedence;
    }


    private List<TaskIDJPA> createTaskIdListFromDTO(List<TaskIdDTO> taskIdDTOList) {

        List<TaskIDJPA> precedenceTaskIDList = new ArrayList<>();

        if (taskIdDTOList != null) {
            for (TaskIdDTO taskIdDTO : taskIdDTOList) {
                TaskCode taskCode = TaskCode.createTaskCode(taskIdDTO.getTaskCode());
                ProjectCode projectCode = ProjectCode.create(taskIdDTO.getProjectCode());
                TaskIDJPA taskIDJPA = TaskIDJPA.createTaskIDJPA(taskCode.getCode(),
                        projectCode.getCode());

                precedenceTaskIDList.add(taskIDJPA);
            }
        }
        return precedenceTaskIDList;
    }

    /**
     * Method to get a Task in a project.
     * @param projectCode - selected project
     * @param taskCode - task to be found
     * @return and Optional of TaskDTO
     * @throws ParseException
     */
    public Optional<TaskDTO> getTask(String projectCode, String taskCode) throws ParseException {

        TaskDTO taskDTO = null;

        ProjectCode projCodeObj = ProjectCode.create(projectCode);
        TaskCode taskCodeObj = TaskCode.createTaskCode(taskCode);
        TaskIDJPA taskIDJPA = TaskIDJPA.createTaskIDJPA(taskCodeObj.getCode(),
                projCodeObj.getCode());

        Optional<Task> selectedTask = taskRepository.findTaskById(taskIDJPA);

        if (selectedTask.isPresent()) {
            Task task = selectedTask.get();
            taskDTO = TaskAssembler.toTaskDTO(task);
        }

        return Optional.ofNullable(taskDTO);

    }

    /**
     * Method to find all tasks in the repository
     * @return a list of TaskDTO
     * @throws ParseException
     */
    @Override
    public List<TaskDTO> getTasks() throws ParseException {

        List<Task> selectedTask = taskRepository.findAllTasks();
        List<TaskDTO> taskDTOList = new ArrayList<>();

        for (Task task : selectedTask) {
            TaskDTO taskDTO = TaskAssembler.toTaskDTO(task);
            taskDTOList.add(taskDTO);
        }

        return taskDTOList;
    }
}

