package switchfive.project.interfaceAdapters.dtos;

import org.junit.jupiter.api.Test;
import switchfive.project.dtos.ScrumBoardDTO;
import switchfive.project.dtos.TaskDTO;
import switchfive.project.dtos.UserStoryDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScrumBoardDTOTest {

    @Test
    void assertThatANewInstanceHasEveryFieldAsAnEmptyArrayList() {
        ScrumBoardDTO dto = new ScrumBoardDTO();

        assertEquals(dto.plannedUserStoriesDTO, new ArrayList<>());
        assertEquals(dto.runningUserStoriesDTO, new ArrayList<>());
        assertEquals(dto.blockedUserStoriesDTO, new ArrayList<>());
        assertEquals(dto.finishedUserStoriesDTO, new ArrayList<>());
        assertEquals(dto.plannedTasksDTO, new ArrayList<>());
        assertEquals(dto.runningTasksDTO, new ArrayList<>());
        assertEquals(dto.blockedTasksDTO, new ArrayList<>());
        assertEquals(dto.finishedTasksDTO, new ArrayList<>());
    }

    @Test
    void assertThatAnInstanceHasTheCorrectListsAsFields() {
        UserStoryDTO userStoryDTO = new UserStoryDTO();
        TaskDTO taskDTO = new TaskDTO();
        List<UserStoryDTO> plannedUserStoriesDTO = new ArrayList<>();
        plannedUserStoriesDTO.add(userStoryDTO);
        List<UserStoryDTO> runningUserStoriesDTO = new ArrayList<>();
        runningUserStoriesDTO.add(userStoryDTO);
        List<UserStoryDTO> blockedUserStoriesDTO = new ArrayList<>();
        blockedUserStoriesDTO.add(userStoryDTO);
        List<UserStoryDTO> finishedUserStoriesDTO = new ArrayList<>();
        finishedUserStoriesDTO.add(userStoryDTO);
        List<TaskDTO> plannedTasksDTO = new ArrayList<>();
        plannedTasksDTO.add(taskDTO);
        List<TaskDTO> runningTasksDTO = new ArrayList<>();
        runningTasksDTO.add(taskDTO);
        List<TaskDTO> blockedTasksDTO = new ArrayList<>();
        blockedTasksDTO.add(taskDTO);
        List<TaskDTO> finishedTasksDTO = new ArrayList<>();
        finishedTasksDTO.add(taskDTO);

        ScrumBoardDTO scrumBoardDTO = new ScrumBoardDTO(plannedUserStoriesDTO,
                runningUserStoriesDTO,
                blockedUserStoriesDTO,
                finishedUserStoriesDTO,
                plannedTasksDTO,
                runningTasksDTO,
                blockedTasksDTO,
                finishedTasksDTO);

        assertEquals(scrumBoardDTO.plannedUserStoriesDTO, plannedUserStoriesDTO);
        assertEquals(scrumBoardDTO.runningUserStoriesDTO, runningUserStoriesDTO);
        assertEquals(scrumBoardDTO.blockedUserStoriesDTO, blockedUserStoriesDTO);
        assertEquals(scrumBoardDTO.finishedUserStoriesDTO, finishedUserStoriesDTO);
        assertEquals(scrumBoardDTO.plannedTasksDTO, plannedTasksDTO);
        assertEquals(scrumBoardDTO.runningTasksDTO, runningTasksDTO);
        assertEquals(scrumBoardDTO.blockedTasksDTO, blockedTasksDTO);
        assertEquals(scrumBoardDTO.finishedTasksDTO, finishedTasksDTO);
    }

}