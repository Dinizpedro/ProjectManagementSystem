package switchfive.project.applicationServices.appServices.iappServices;

import switchfive.project.dtos.MoveUserStoryDTO;
import switchfive.project.dtos.RefineUserStoryDTO;
import switchfive.project.dtos.UserStoryDTO;
import switchfive.project.domain.shared.valueObjects.ProjectCode;
import switchfive.project.domain.shared.valueObjects.UserStoryCode;
import switchfive.project.domain.shared.valueObjects.UserStoryDescription;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface IAppUserStoryService {
    UserStoryDTO createAndAddUserStory(String projectCode, String description)
            throws ParseException;

    List<UserStoryDTO> getUserStoryListByPriority(String projectCode)
            throws ParseException;

    Optional<UserStoryDTO> getUserStoryDTO(String projectCode, String userStoryCode) throws ParseException;

    List<UserStoryDTO> refineUserStory(RefineUserStoryDTO refineUserStoryDTO) throws ParseException;

    UserStoryDTO createNewUSWithParentCode(ProjectCode projectCode,
                                           UserStoryCode parentUserStoryCode,
                                           UserStoryDescription userStoryDescription) throws ParseException;

   Optional<MoveUserStoryDTO> moveUSFromProductBacklogToSprintBacklog(String projecCode, String userStoryCode, MoveUserStoryDTO theUserStory) throws ParseException;

    Optional<UserStoryDTO> userStoryChangeStatus (String projectCode, String userStoryCode, String status) throws ParseException;

    Optional<UserStoryDTO> userStoryChangePriority(String projectCode, String userStoryCode, int priority);
}

