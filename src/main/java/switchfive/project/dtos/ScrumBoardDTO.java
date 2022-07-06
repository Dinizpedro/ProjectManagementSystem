package switchfive.project.dtos;

import java.util.ArrayList;
import java.util.List;

public class ScrumBoardDTO {

    public final List<UserStoryDTO> plannedUserStoriesDTO;
    public final List<UserStoryDTO> runningUserStoriesDTO;
    public final List<UserStoryDTO> blockedUserStoriesDTO;
    public final List<UserStoryDTO> finishedUserStoriesDTO;
    public final List<TaskDTO> plannedTasksDTO;
    public final List<TaskDTO> runningTasksDTO;
    public final List<TaskDTO> blockedTasksDTO;
    public final List<TaskDTO> finishedTasksDTO;

    public ScrumBoardDTO() {
        this.plannedUserStoriesDTO = new ArrayList<>();
        this.runningUserStoriesDTO = new ArrayList<>();
        this.blockedUserStoriesDTO = new ArrayList<>();
        this.finishedUserStoriesDTO = new ArrayList<>();
        this.plannedTasksDTO = new ArrayList<>();
        this.runningTasksDTO = new ArrayList<>();
        this.blockedTasksDTO = new ArrayList<>();
        this.finishedTasksDTO = new ArrayList<>();
    }

    public ScrumBoardDTO(List<UserStoryDTO> plannedUserStoriesDTO
            , List<UserStoryDTO> runningUserStoriesDTO
            , List<UserStoryDTO> blockedUserStoriesDTO
            , List<UserStoryDTO> finishedUserStoriesDTO
            , List<TaskDTO> plannedTasksDTO
            , List<TaskDTO> runningTasksDTO
            , List<TaskDTO> blockedTasksDTO
            , List<TaskDTO> finishedTasksDTO) {
        this.plannedUserStoriesDTO = plannedUserStoriesDTO;
        this.runningUserStoriesDTO = runningUserStoriesDTO;
        this.blockedUserStoriesDTO = blockedUserStoriesDTO;
        this.finishedUserStoriesDTO = finishedUserStoriesDTO;
        this.plannedTasksDTO = plannedTasksDTO;
        this.runningTasksDTO = runningTasksDTO;
        this.blockedTasksDTO = blockedTasksDTO;
        this.finishedTasksDTO = finishedTasksDTO;
    }


}
