package switchfive.project.mappers.mappersApp.implMappers;

import switchfive.project.dtos.MoveUserStoryDTO;
import switchfive.project.dtos.UserStoryDTO;
import switchfive.project.domain.aggregates.userStory.UserStory;

/**
 * UserStory Assembler : UserStory -> UserStoryDTO
 */
public class UserStoryMapper {

    public UserStoryMapper() {
    }

    /**
     * Map method that transforms a UserStory object into a UserStoryDTO object.
     *
     * @param userStoryInput UserStory object
     * @return UserStoryDTO object
     */
    public static UserStoryDTO toUserStoryDTO(UserStory userStoryInput) {

        String code = userStoryInput.getUserStoryCode();
        String description = userStoryInput.getUserStoryDescription();
        Integer effort;
        effort = userStoryInput.getEffort();
        /*try {
            effort = userStoryInput.getEffort();
        } catch (Exception e) {
            effort = null;
        }*/
        Integer priority = userStoryInput.getPriority();
        String status = userStoryInput.getStatus();

        UserStoryDTO userStoryDTO = new UserStoryDTO();
        userStoryDTO.setCode(code);
        userStoryDTO.setEffort(effort);
        userStoryDTO.setPriority(priority);
        userStoryDTO.setDescription(description);
        userStoryDTO.setStatus(status);

        String parentUSCode = userStoryInput.getParentUserStory();
        userStoryDTO.setParentUserStoryCode(parentUSCode);

        return userStoryDTO;
    }

    /**
     * Map method that transforms a UserStory object into a MoveUserStoryDTO object.
     *
     * @param userStory UserStory object o be mapped into a DTO.
     * @return the MoveUserStoryDTO version of the UserStory.
     */
    public static MoveUserStoryDTO toMoveUserStoryDTO(UserStory userStory) {

        MoveUserStoryDTO userStoryDTO = new MoveUserStoryDTO();

        userStoryDTO.setProjectCode(userStory.getProjectCodeOfUserStory());
        userStoryDTO.setCode(userStory.getUserStoryCode());
        userStoryDTO.setEffort(userStory.getEffort());
        userStoryDTO.setPriority(userStory.getPriority());
        userStoryDTO.setDescription(userStory.getUserStoryDescription());
        userStoryDTO.setSprintID(userStory.getSprintID().getSprintNumber());
        userStoryDTO.setStatus(userStory.getStatus());

        return userStoryDTO;
    }
}
