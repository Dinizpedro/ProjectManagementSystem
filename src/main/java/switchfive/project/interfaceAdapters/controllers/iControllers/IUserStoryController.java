package switchfive.project.interfaceAdapters.controllers.iControllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import switchfive.project.dtos.CreateUserStoryDTO;
import switchfive.project.dtos.MoveUserStoryDTO;
import switchfive.project.dtos.RefineUserStoryDTO;
import switchfive.project.dtos.UserStoryStatusDTO;

import java.text.ParseException;

public interface IUserStoryController {

    ResponseEntity<Object> createUserStory(@PathVariable(value = "projectCode") String projectCode,
                                           @RequestBody CreateUserStoryDTO description);

    ResponseEntity<Object> refineUserStory(@RequestBody RefineUserStoryDTO refineUserStoryDTO);

    ResponseEntity<Object> userStoryUpdateStatus(@PathVariable(value = "projectCode") String projectCode,
                                                 @PathVariable(value = "userStoryCode") String userStoryCode,
                                                 @RequestBody UserStoryStatusDTO newStatus) throws ParseException;

    ResponseEntity<Object> moveUSFromProductBacklogToSprintBacklog(@PathVariable(value = "projectCode") String projectCode,
                                                                   @PathVariable(value = "userStoryCode") String userStoryCode,
                                                                   @RequestBody MoveUserStoryDTO moveUserStoryDTO) throws ParseException;
}
