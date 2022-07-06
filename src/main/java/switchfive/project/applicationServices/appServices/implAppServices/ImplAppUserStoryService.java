package switchfive.project.applicationServices.appServices.implAppServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchfive.project.dtos.MoveUserStoryDTO;
import switchfive.project.dtos.RefineUserStoryDTO;
import switchfive.project.dtos.UserStoryDTO;
import switchfive.project.applicationServices.appServices.iappServices.IAppUserStoryService;
import switchfive.project.mappers.mappersApp.implMappers.UserStoryMapper;
import switchfive.project.applicationServices.iRepositories.IProjectRepository;
import switchfive.project.applicationServices.iRepositories.ISprintRepository;
import switchfive.project.applicationServices.iRepositories.IUserStoryRepository;
import switchfive.project.domain.aggregates.project.Project;
import switchfive.project.domain.aggregates.sprint.Sprint;
import switchfive.project.domain.aggregates.userStory.UserStory;
import switchfive.project.domain.domainServices.iDomainServices.IUserStoryDomainService;
import switchfive.project.domain.factories.iFactories.UserStoryFactory;
import switchfive.project.domain.shared.valueObjects.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImplAppUserStoryService implements IAppUserStoryService {

    private final IUserStoryRepository iUserStoryStore;
    private final UserStoryFactory userStoryFactory;
    private final IProjectRepository iProjectRepository;
    private final ISprintRepository iSprintRepository;
    private final IUserStoryDomainService iUserStoryDomainService;

    @Autowired
    public ImplAppUserStoryService(IUserStoryRepository iUserStoryStore,
                                   UserStoryFactory userStoryFactory,
                                   IProjectRepository iProjectRepository, ISprintRepository iSprintRepository, IUserStoryDomainService iUserStoryDomainService) {
        this.iUserStoryStore = iUserStoryStore;
        this.userStoryFactory = userStoryFactory;
        this.iProjectRepository = iProjectRepository;
        this.iSprintRepository = iSprintRepository;
        this.iUserStoryDomainService = iUserStoryDomainService;
    }

    /**
     * Public method create and add user story to userStoryList.
     *
     * @param description String description
     * @return true if user story was successfully created and added to userStoryList
     */
    @Override
    public UserStoryDTO createAndAddUserStory(String projectCode, String description)
            throws ParseException {

        ProjectCode projCode = ProjectCode.create(projectCode);
        Optional<Project> opProject = iProjectRepository.findByCode(projCode);
        if (opProject.isEmpty()) {
            throw new IllegalArgumentException("Project doesn't exist!");
        }

        String defaultCode = iUserStoryStore.generatorCode(projCode);
        UserStoryCode code = UserStoryCode.createUserStoryCode(defaultCode);

        int defaultPriority = iUserStoryStore.nextUserStoryNumber(projCode);
        Priority priority = Priority.createPriority(defaultPriority);

        UserStoryID userStoryID = UserStoryID.
                createUserStoryID(projectCode, code.getIdentity());

        UserStoryDescription userStoryDescription = UserStoryDescription.
                createUserStoryDescription(description);

        UserStory newUserStory = this.userStoryFactory.createUserStory(userStoryID, projCode,
                code, priority, userStoryDescription);
        Optional<UserStory> userStorySaved = this.iUserStoryStore.save(newUserStory);

        return UserStoryMapper.toUserStoryDTO(userStorySaved.get());
    }

    @Override
    public List<UserStoryDTO> getUserStoryListByPriority(String projectCode)
            throws ParseException {

        ProjectCode projCode = ProjectCode.create(projectCode);
        Optional<Project> opProject = iProjectRepository.findByCode(projCode);
        if (opProject.isEmpty()) {
            throw new IllegalArgumentException("Project doesn't exist!");
        }

        List<UserStoryDTO> listByPriority = new ArrayList<>();
        List<UserStory> userStoryList = iUserStoryStore.getUserStoryListProductBacklog(projCode);
        for (UserStory userStory : userStoryList) {
            UserStoryDTO userStoryDTO = UserStoryMapper.toUserStoryDTO(userStory);
            listByPriority.add(userStoryDTO);
        }
        return listByPriority;
    }

    @Override
    public Optional<UserStoryDTO> getUserStoryDTO(String projectCode, String userStoryCode) throws ParseException {

        UserStoryDTO userStoryDTO = null;

        ProjectCode projCode = ProjectCode.create(projectCode);
        Optional<Project> opProject = iProjectRepository.findByCode(projCode);
        if (opProject.isEmpty()) {
            throw new IllegalArgumentException("Project doesn't exist!");
        }

        UserStoryCode uSCode = UserStoryCode.createUserStoryCode(userStoryCode);

        Optional<UserStory> selectedUserStory = iUserStoryStore.getUserStory(projCode, uSCode);
        if (selectedUserStory.isPresent()) {
            userStoryDTO = UserStoryMapper.toUserStoryDTO(selectedUserStory.get());
        }

        return Optional.ofNullable(userStoryDTO);
    }

    @Override
    public ArrayList<UserStoryDTO> refineUserStory(RefineUserStoryDTO refineUserStoryDTO)
            throws ParseException {

        String projectCode = refineUserStoryDTO.getProjectCode();
        String userStoryCode = refineUserStoryDTO.getUserStoryCode();
        List<String> newUserStoryDescription = refineUserStoryDTO
                .getNewUserStoryDescription();

        ProjectCode projCode = ProjectCode.create(projectCode);
        Optional<Project> opProject = iProjectRepository.findByCode(projCode);
        if (opProject.isEmpty()) {
            throw new IllegalArgumentException("Project doesn't exist!");
        }

        Project project = opProject.get();
        if (project.isProjectClosed()) {
            throw new UnsupportedOperationException("Method not allowed. Project is finished");
        }

        // Find and get Parent US
        UserStoryCode parentUsCode = UserStoryCode.createUserStoryCode(userStoryCode);
        Optional<UserStory> optUserStoryToRefine = iUserStoryStore.getUserStory(projCode,
                parentUsCode);
        if (optUserStoryToRefine.isEmpty()) {
            throw new IllegalArgumentException("Parent User Story doesn't exist!");
        }

        UserStory userStoryToRefine = optUserStoryToRefine.get();

        // Check if US is available for refinement
        if (!userStoryToRefine.isUserStoryAvailableForRefinement()) {
            throw new UnsupportedOperationException("Method not allowed. User Story " +
                    "in not in a planned status.");
        }

        // Create New User Stories
        ArrayList<UserStoryDescription> newUSdescriptionVOList =
                userStoryDescriptionStringtoUserStoryDescriptionVO(newUserStoryDescription);

        ArrayList<UserStoryDTO> newUSDTOList = new ArrayList<>();

        if (!newUSdescriptionVOList.isEmpty()) {
            for (UserStoryDescription userStoryDescription : newUSdescriptionVOList) {
                UserStoryDTO newUS = this.createNewUSWithParentCode(projCode,
                        parentUsCode,
                        userStoryDescription);
                newUSDTOList.add(newUS);
            }
        } else {
            throw new IllegalArgumentException("New User Story description can't be empty");
        }

        // Set Parent US to Refined and Save
        userStoryToRefine.setStatusToRefined();

        // Save changes to original US
        iUserStoryStore.save(userStoryToRefine);

        UserStoryDTO userStoryDTOParent = UserStoryMapper.toUserStoryDTO(userStoryToRefine);
        newUSDTOList.add(userStoryDTOParent);

        return newUSDTOList;

    }

    private ArrayList<UserStoryDescription>
    userStoryDescriptionStringtoUserStoryDescriptionVO
            (List<String> newUserStoryDescription) {

        ArrayList<UserStoryDescription> newUSdescriptionVOList =
                new ArrayList<>();

        for (String description : newUserStoryDescription) {
            UserStoryDescription usDescriptionVO = UserStoryDescription
                    .createUserStoryDescription(description);

            newUSdescriptionVOList.add(usDescriptionVO);
        }

        return newUSdescriptionVOList;
    }

    @Override
    public UserStoryDTO createNewUSWithParentCode(ProjectCode projectCode,
                                                  UserStoryCode parentUserStoryCode,
                                                  UserStoryDescription userStoryDescription)
            throws ParseException {

        Optional<Project> opProject = iProjectRepository.findByCode(projectCode);
        if (opProject.isEmpty()) {
            throw new IllegalArgumentException("Project doesn't exist!");
        }

        String defaultCode = iUserStoryStore.generatorCode(projectCode);
        UserStoryCode code = UserStoryCode.createUserStoryCode(defaultCode);

        int defaultPriority = iUserStoryStore.nextUserStoryNumber(projectCode);
        Priority priority = Priority.createPriority(defaultPriority);
        UserStoryID userStoryID = UserStoryID.createUserStoryID(projectCode.getCode(),
                code.getIdentity());

        UserStory newUserStory = this.userStoryFactory.createUserStory(userStoryID, projectCode,
                code, priority, userStoryDescription, parentUserStoryCode);
        Optional<UserStory> newUserStoryInDBOpt =
                this.iUserStoryStore.save(newUserStory);

        UserStory newUserStoryInDB = null;

        if (newUserStoryInDBOpt.isPresent()) {
            newUserStoryInDB = newUserStoryInDBOpt.get();
        } else {
            throw new UnsupportedOperationException("Couldn't save new User Story in repo.");
        }

        return UserStoryMapper.toUserStoryDTO(newUserStoryInDB);
    }

    /**

     * Moves a UserStory from the product backlog to a given sprint, if the given Project exists and is not closed and
     * if the given Sprint exists and is not closed, and if the userStory exists, is not closed, and is in
     * the Project's backlog.
     *
     * @param projectCode      the project's identifier.
     * @param userStoryCode    the userStory's identifier.
     * @param moveUserStoryDTO the DTO, containing the sprint's identifier.
     * @return an Optional of the moveUserStoryDTO with the updated info.
     * @throws ParseException when an error has been reached while parsing.
     */
    public Optional<MoveUserStoryDTO> moveUSFromProductBacklogToSprintBacklog(String projectCode,
                                                                              String userStoryCode,
                                                                              MoveUserStoryDTO moveUserStoryDTO) throws ParseException {
        UserStoryCode pretendedUserStoryCode = UserStoryCode.createUserStoryCode(userStoryCode);
        int sprintID = moveUserStoryDTO.getSprintID();
        SprintID pretendedSprintID = SprintID.createSprintID(projectCode, sprintID);
        ProjectCode pretendedProjectCode = ProjectCode.create(projectCode);
        Optional<MoveUserStoryDTO> updatedDTOOptional = Optional.empty();

        if (isUserStoryMovable(pretendedProjectCode, pretendedSprintID, pretendedUserStoryCode)) {
            Optional<UserStory> updatedUserStoryOptional = setNewSprintIDAndSave(moveUserStoryDTO, pretendedUserStoryCode, pretendedProjectCode);

            if (updatedUserStoryOptional.isPresent()) {
                MoveUserStoryDTO updatedDTO = UserStoryMapper.toMoveUserStoryDTO(updatedUserStoryOptional.get());
                updatedDTOOptional = Optional.of(updatedDTO);
            }
        }
        return updatedDTOOptional;
    }

    private Optional<UserStory> setNewSprintIDAndSave(MoveUserStoryDTO moveUserStoryDTO, UserStoryCode newUserStoryCode, ProjectCode newProjectCode) {
        Optional<UserStory> userStoryOptional = iUserStoryStore.getUserStory(newProjectCode,
                newUserStoryCode);
        Integer newSprintID = moveUserStoryDTO.getSprintID();
        UserStory userStory = userStoryOptional.get();
        userStory.setSprintID(newProjectCode.getCode(), newSprintID);
        return iUserStoryStore.save(userStory);
    }

    private boolean isUserStoryMovable(ProjectCode projectCode, SprintID sprintID, UserStoryCode userStoryCode) throws ParseException {

        Optional<Project> projectOptional = iProjectRepository.findByCode(projectCode);
        boolean isUserStoryMovable = false;

        if (projectOptional.isPresent()) {
            Optional<Sprint> sprintOptional = iSprintRepository.findBySprintID(sprintID);

            if (sprintOptional.isPresent()) {
                Optional<UserStory> userStoryOptional = iUserStoryStore.getUserStory(projectCode, userStoryCode);

                if (userStoryOptional.isPresent()) {
                    isUserStoryMovable = iUserStoryDomainService.canUSBeMovedFromProductBacklogToSprintBacklog(projectOptional.get(),
                            sprintOptional.get(),
                            userStoryOptional.get());
                }
            }
        }
        return isUserStoryMovable;
    }

    public Optional<UserStoryDTO> userStoryChangeStatus(String projectCode, String userStoryCode, String status) throws ParseException {

        UserStoryDTO userStoryDTO = null;

        ProjectCode projCode = ProjectCode.create(projectCode);
        boolean projectExists = iProjectRepository.projectExists(projCode);
        if (projectExists == false) {
            throw new IllegalArgumentException("Project doesn't exist!");
        }

        UserStoryCode userStoryCode1 = UserStoryCode.createUserStoryCode(userStoryCode);
        UserStoryStatus.valueOf(status.toUpperCase());

        Optional<UserStory> userStory = iUserStoryStore.getUserStory(projCode, userStoryCode1);
        if (userStory.isPresent() && userStory.get().getSprintID().getSprintNumber() != 0
                && userStory.get().getStatus() != status.toUpperCase()) {
            userStory.get().updateStatus(status);
            Optional<UserStory> userStoryUpdated = iUserStoryStore.save(userStory.get());
            userStoryDTO = UserStoryMapper.toUserStoryDTO(userStoryUpdated.get());
        }
        return Optional.ofNullable(userStoryDTO);
    }

    public Optional<UserStoryDTO> userStoryChangePriority(String projectCode, String userStoryCode, int priority) {

        UserStoryDTO userStoryDTO = null;

        ProjectCode projectCodeVO = ProjectCode.create(projectCode);
        UserStoryCode userStoryCodeVO = UserStoryCode.createUserStoryCode(userStoryCode);

        Optional<UserStory> userStoryOpt = iUserStoryStore
                .getUserStory(projectCodeVO, userStoryCodeVO);

        if (userStoryOpt.isPresent()) {

            List<UserStory> userStoriesInProject =
                    iUserStoryStore.getUserStoryListProductBacklog(projectCodeVO);

            UserStory userStoryToChangePriority = userStoryOpt.get();
            int oldPriority = userStoryToChangePriority.getPriority();
            Priority newPriority = Priority.createPriority(priority);

            if (priority <= userStoriesInProject.size()) {
                // If new US priority is under old priority
                if (oldPriority > priority) {
                    for (int i = priority - 1; i < oldPriority; i++) {

                        UserStory userStoryPriorityOrder = userStoriesInProject.get(i);

                        Priority newPriorityInList = null;
                        if (i == oldPriority - 1) {
                            newPriorityInList = newPriority;
                        } else {
                            newPriorityInList = Priority.createPriority(i + 2);
                        }
                        userStoryPriorityOrder.setPriority(newPriorityInList);
                        Optional<UserStory> userStoryInDB = iUserStoryStore.save(userStoryPriorityOrder);

                        if (i == oldPriority - 1) {
                            userStoryDTO = UserStoryMapper.toUserStoryDTO(userStoryInDB.get());
                        }

                    }
                    // If new US priority is above old priority
                } else {
                    for (int i = oldPriority - 1; i < priority; i++) {

                        UserStory userStoryPriorityOrder = userStoriesInProject.get(i);

                        Priority newPriorityInList = null;
                        if (i == oldPriority - 1) {
                            newPriorityInList = newPriority;
                        } else {
                            newPriorityInList = Priority.createPriority(userStoryPriorityOrder.getPriority() - 1);
                        }
                        userStoryPriorityOrder.setPriority(newPriorityInList);
                        Optional<UserStory> userStoryInDB = iUserStoryStore.save(userStoryPriorityOrder);

                        if (i == oldPriority - 1) {
                            userStoryDTO = UserStoryMapper.toUserStoryDTO(userStoryInDB.get());
                        }
                    }
                }
            }
        }

        return Optional.ofNullable(userStoryDTO);
    }
}




