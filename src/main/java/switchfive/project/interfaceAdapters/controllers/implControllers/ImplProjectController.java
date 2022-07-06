package switchfive.project.interfaceAdapters.controllers.implControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switchfive.project.interfaceAdapters.controllers.iControllers.IProjectController;
import switchfive.project.dtos.ActivityDTO;
import switchfive.project.dtos.ProjectDTO;
import switchfive.project.dtos.UpdateProjectDTO;
import switchfive.project.applicationServices.appServices.iappServices.IAppProjectService;

import javax.net.ssl.SSLException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class ImplProjectController implements IProjectController {
    private final String WRONG_INFORMATION = "Wrong information";
    private final String PROJECT_DOES_NOT_EXIST = "Project do not exist";
    private final IAppProjectService projectService;


    @Autowired
    public ImplProjectController(final IAppProjectService iAppProjectService) {
        this.projectService = iAppProjectService;
    }


    /**
     * Create a new Project in the system
     * @return representation of the newly created Project and HttpStatus 201 Created. Otherwise, it returns a HttpStatus 400 Bad Request
     */
    @PostMapping(value = "/projects")
    public ResponseEntity<Object> createProject(
            @RequestBody final ProjectDTO dtoInput) {
        try {

            ProjectDTO output = this.projectService.createAndSaveProject(dtoInput);

            Link link = linkTo(methodOn(ImplProjectController.class)
                    .getProject(dtoInput.getProjectCode()))
                    .withSelfRel();

            output.add(link);

            return new ResponseEntity<>(output, HttpStatus.CREATED);

        } catch (IllegalArgumentException | ParseException | NoSuchAlgorithmException exception) {

            return new ResponseEntity<>(WRONG_INFORMATION, HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Get all Projects stored in the system.
     * @return RepresentationModel object with the links to all individual projects and HttpStatus 200 Ok.
     */
    @GetMapping(value = "/projects")
    public ResponseEntity<Object> getAllProjects() throws ParseException, NoSuchAlgorithmException, SSLException {

        List<ProjectDTO> dto = this.projectService.getAllProjects();
        RepresentationModel<ProjectDTO> linkList = new RepresentationModel<>();

        for (ProjectDTO each : dto) {

            Link link = linkTo(methodOn(ImplProjectController.class)
                    .getProject(each.getProjectCode()))
                    .withRel(each.getProjectCode())
                    .withType("GET");

            linkList.add(link);
        }

        return new ResponseEntity<>(linkList, HttpStatus.OK);
    }


    /**
     * Gets a Project that matches the input projectCode.
     * @return Response Entity object with the data transfer object the specified profile and HttpStatus 200 Ok.
     * Otherwise, if the profile is not found a HttpStatus of 404 is returned.
     * Also, if an exception is thrown a HttpStatus 400 Bad Request is returned.
     */
    @GetMapping(value = "/projects/{projectCode}")
    public ResponseEntity<Object> getProject(@PathVariable final String projectCode)
            throws ParseException, NoSuchAlgorithmException {
        try {

            if (this.projectService.getProjectDTO(projectCode).isEmpty()) {

                return new ResponseEntity<>(PROJECT_DOES_NOT_EXIST, HttpStatus.NOT_FOUND);
            }

            ProjectDTO dto = this.projectService.getProjectDTO(projectCode).get();

            return new ResponseEntity<>(dto, HttpStatus.OK);

        } catch (IllegalArgumentException | ParseException exception) {

            return new ResponseEntity<>(WRONG_INFORMATION, HttpStatus.BAD_REQUEST);

        } catch (SSLException e) {

            throw new RuntimeException(e);
        }
    }

    /**
     * @param projectCode as path variable
     * @param dto         object with update data, from json.
     * @return HTTP response using Spring ResponseEntity Object.
     */
    @PutMapping(value = "/projects/{projectCode}")
    public ResponseEntity<Object> updateProject(
            @PathVariable String projectCode,
            @RequestBody UpdateProjectDTO dto)
            throws NoSuchAlgorithmException {
        try {

            if (this.projectService.updateProject(projectCode, dto).isEmpty()) {
                return new ResponseEntity<>(PROJECT_DOES_NOT_EXIST, HttpStatus.NOT_FOUND);
            }

            ProjectDTO dtoOutput = this.projectService.updateProject(projectCode, dto).get();

            Link link = linkTo(methodOn(ImplProjectController.class).getProject(projectCode)).withSelfRel();

            dtoOutput.add(link);

            return new ResponseEntity<>(dtoOutput, HttpStatus.OK);

        } catch (IllegalArgumentException | ParseException exception) {

            return new ResponseEntity<>(WRONG_INFORMATION, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Obtain status of all activities in a project
     * @param projectCode - selected project
     * @return status of activities(userStories or/and tasks) if project exists and a HttpStatus 200 OK,
     * otherwise it returns a HttpStatus 400 Bad Request
     * @throws ParseException
     */
    @GetMapping(value = "/projects/{projectCode}/activities")
    public ResponseEntity<Object> getActivitiesStatus(
            @PathVariable(value = "projectCode") String projectCode) throws ParseException {
        try {
            List<ActivityDTO> activitiesDTO = this.projectService.getActivitiesStatuses(projectCode);

            for (ActivityDTO activityDTO: activitiesDTO) {
                if (activityDTO.getTypeOfActivity().equals("User Story")) {
                    Link link = linkTo(methodOn(ImplUserStoryController.class)
                            .getUserStory(projectCode,
                                    activityDTO.getActivityCode())).withSelfRel();
                    activityDTO.add(link);
                } else if (activityDTO.getTypeOfActivity().equals("Task")) {
                    Link link = linkTo(methodOn(ImplTaskController.class).getTask(activityDTO.getActivityCode(),
                            projectCode)).withSelfRel();
                    activityDTO.add(link);
                }
            }
            return new ResponseEntity<>(activitiesDTO, HttpStatus.OK);
        } catch (IllegalArgumentException | ParseException exception) {
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
