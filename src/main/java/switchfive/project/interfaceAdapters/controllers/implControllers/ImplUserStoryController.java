package switchfive.project.interfaceAdapters.controllers.implControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import switchfive.project.interfaceAdapters.controllers.iControllers.IUserStoryController;
import switchfive.project.applicationServices.appServices.iappServices.IAppUserStoryService;
import switchfive.project.dtos.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@CrossOrigin
@Controller
@RestController(value = "/api")
public class ImplUserStoryController implements IUserStoryController {

    private final static String ERROR_MESSAGE = "Operation failed.";
    private IAppUserStoryService iAppUserStoryService;

    @Autowired
    public ImplUserStoryController(IAppUserStoryService iAppCreateUserStoryService) {
        this.iAppUserStoryService = iAppCreateUserStoryService;
    }

    @PostMapping("/userStories/projectCode/{projectCode}")
    @ResponseBody
    public ResponseEntity<Object> createUserStory(@PathVariable(value = "projectCode") String projectCode,
                                                  @RequestBody CreateUserStoryDTO description) {

        try {
            UserStoryDTO userStoryOutput = this.iAppUserStoryService.createAndAddUserStory(projectCode, description.getDescription());

            Link link = linkTo(methodOn(ImplUserStoryController.class).getUserStory(projectCode, userStoryOutput.getCode())).withSelfRel();

            userStoryOutput.add(link);

            return new ResponseEntity<>(userStoryOutput, HttpStatus.CREATED);
        } catch (IllegalArgumentException | ParseException operationFailed) {
            return new ResponseEntity<>(operationFailed.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/userStories/{userStoryCode}/projectCode/{projectCode}")
    public ResponseEntity<Object> getUserStory(@PathVariable(value = "projectCode") String projectCode,
                                               @PathVariable(value = "userStoryCode") String userStoryCode) {
        try {
            Optional<UserStoryDTO> userStoryDTO = this.iAppUserStoryService
                    .getUserStoryDTO(projectCode, userStoryCode);

            if (userStoryDTO.isEmpty()) {
                return new ResponseEntity<>("User Story doesn't exists!", HttpStatus.OK);
            }

            Link linkMoveToSprint = linkTo(methodOn(ImplUserStoryController.class)
                    .moveUSFromProductBacklogToSprintBacklog(projectCode, userStoryCode, null))
                    .withRel("moveToSprint").withType("PATCH");

            Link linkStatus = linkTo(methodOn(ImplUserStoryController.class).userStoryUpdateStatus(projectCode,
                    userStoryCode, null)).withRel("updateStatus").withType("PATCH");

            Link linkPriority = linkTo(methodOn(ImplUserStoryController.class).
                    userStoryUpdatePriority(projectCode, userStoryCode, 0)).withRel("updatePriority")
                    .withType("PATCH");

            Link linkRefine = linkTo(methodOn(ImplUserStoryController.class).refineUserStory(null))
                    .withRel("refineUserStory").withType("POST");


            userStoryDTO.get().add(linkStatus, linkMoveToSprint, linkPriority, linkRefine);

            return new ResponseEntity<>(userStoryDTO.get(), HttpStatus.OK);
        } catch (IllegalArgumentException | ParseException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/userStories/projectCode/{projectCode}")
    public ResponseEntity<Object> getUserStoryByPriority(@PathVariable(value = "projectCode") String projectCode) {

        try {
            List<UserStoryDTO> userStoriesOfProject = iAppUserStoryService.
                    getUserStoryListByPriority(projectCode);
            RepresentationModel<UserStoryDTO> linksObj = new RepresentationModel<>();

            for (UserStoryDTO userStoryDTO : userStoriesOfProject) {

                Link link = linkTo(methodOn(ImplUserStoryController.class).getUserStory(projectCode,
                        userStoryDTO.getCode())).withRel(userStoryDTO.getCode());

                linksObj.add(link);
            }
            return new ResponseEntity<>(linksObj, HttpStatus.OK);
        } catch (IllegalArgumentException | ParseException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/userStories/refineUS")
    public ResponseEntity<Object> refineUserStory(@RequestBody RefineUserStoryDTO refineUserStoryDTO) {

        try {
            List<UserStoryDTO> newUserStoryList =
                    iAppUserStoryService.refineUserStory(refineUserStoryDTO);

            for (UserStoryDTO userStoryDTO : newUserStoryList) {

                Link link = linkTo(methodOn(ImplUserStoryController.class)
                        .getUserStory(refineUserStoryDTO.getProjectCode(),
                                userStoryDTO.getCode())).withSelfRel();

                userStoryDTO.add(link);
            }

            return new ResponseEntity<>(newUserStoryList, HttpStatus.OK);

        } catch (IllegalArgumentException |
                UnsupportedOperationException |
                ParseException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PatchMapping("/projects/{projectCode}/productBacklog/{userStoryCode}")
    public ResponseEntity<Object> moveUSFromProductBacklogToSprintBacklog(@PathVariable(value = "projectCode") String projectCode,
                                                                          @PathVariable(value = "userStoryCode") String userStoryCode,
                                                                          @RequestBody MoveUserStoryDTO moveUserStoryDTO) throws ParseException {
        Optional<MoveUserStoryDTO> moveUserStoryDTOOptional = iAppUserStoryService.moveUSFromProductBacklogToSprintBacklog(projectCode, userStoryCode, moveUserStoryDTO);

        if (moveUserStoryDTOOptional.isPresent()) {
            Link linkToSelf = linkTo(methodOn(ImplUserStoryController.class).getUserStory(projectCode, userStoryCode))
                    .withSelfRel();

            Link linkToSprint = linkTo(methodOn(ImplSprintController.class).getSprintById(moveUserStoryDTO.getSprintID(), moveUserStoryDTO.getProjectCode()))
                    .withRel("sprint");

            moveUserStoryDTOOptional.get().add(linkToSelf, linkToSprint);

            return new ResponseEntity<>(moveUserStoryDTOOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/userStories/{userStoryCode}/projectCode/{projectCode}/status")
    public ResponseEntity<Object> userStoryUpdateStatus(@PathVariable(value = "projectCode") String projectCode,
                                                        @PathVariable(value = "userStoryCode") String userStoryCode,
                                                        @RequestBody UserStoryStatusDTO newStatus) throws ParseException {
        try {
            Optional<UserStoryDTO> userStoryOutput = this.iAppUserStoryService.userStoryChangeStatus(projectCode,
                    userStoryCode, newStatus.getNewStatus());

            Link link = linkTo(methodOn(ImplUserStoryController.class).getUserStory(projectCode,
                    userStoryOutput.get().getCode())).withSelfRel();

            userStoryOutput.get().add(link);

            return new ResponseEntity<>(userStoryOutput, HttpStatus.OK);

        } catch (Exception operationFailed) {
            return new ResponseEntity<>(operationFailed.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("userStories/{userStoryCode}/projectCode/{projectCode}/priority")
    public ResponseEntity<Object> userStoryUpdatePriority(@PathVariable(value = "projectCode") String projectCode,
                                                          @PathVariable(value = "userStoryCode") String userStoryCode,
                                                          @RequestBody int priority) {

        try {
        Optional<UserStoryDTO> userStoryDto = this.iAppUserStoryService.userStoryChangePriority
                (projectCode, userStoryCode, priority);
            if (userStoryDto.isPresent()) {

                Link link = linkTo(methodOn(ImplUserStoryController.class).getUserStory
                        (projectCode, userStoryDto.get().getCode())).withSelfRel();

                userStoryDto.get().add(link);

                return new ResponseEntity<>(userStoryDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(ERROR_MESSAGE, HttpStatus.BAD_REQUEST);
            }
        } catch (IllegalArgumentException exception) {

            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
// Test
}

