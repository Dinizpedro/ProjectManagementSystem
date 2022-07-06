package switchfive.project.interfaceAdapters.controllers.implControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switchfive.project.interfaceAdapters.controllers.iControllers.ISprintController;
import switchfive.project.dtos.SprintCreationDTO;
import switchfive.project.dtos.SprintDTO;
import switchfive.project.applicationServices.appServices.iappServices.IAppSprintService;
import switchfive.project.domain.shared.valueObjects.SprintID;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class ImplSprintController implements ISprintController {

    private IAppSprintService sprintService;

    @Autowired
    public ImplSprintController(IAppSprintService service) {
        this.sprintService = service;
    }


    @PostMapping(value = "/sprints/projectCode/{projectCode}")
    public ResponseEntity<Object> createAndAddNewSprint(
            @RequestBody final SprintCreationDTO dto,
            @PathVariable final String projectCode) {
        try {
            SprintDTO dtoOutput = sprintService.createAndSaveSprint(dto, projectCode);

            Link link = linkTo(methodOn(ImplSprintController.class)
                    .getSprintById(dtoOutput.getSprintNumber(), dtoOutput.getProjectCode()))
                    .withSelfRel();

            dtoOutput.add(link);

            return new ResponseEntity<>(dtoOutput, HttpStatus.CREATED);

        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(value = "/sprints/projectCode/{projectCode}/")
    public ResponseEntity<Object> getAllSprintsByProjectCode(@PathVariable final String projectCode) {
        try {
            RepresentationModel<SprintDTO> linkList = new RepresentationModel<>();
            Link link;

            if (sprintService.getSprintsByProjectCode(projectCode).isEmpty()) {
                return new ResponseEntity<>("Project sprint list is empty!", HttpStatus.OK);
            }

            List<SprintDTO> result = sprintService.getSprintsByProjectCode(projectCode).get();

            for (SprintDTO sprintsDTO : result) {
                link = linkTo(methodOn(ImplSprintController.class).
                        getSprintById(sprintsDTO.getSprintNumber(), sprintsDTO.getProjectCode()))
                        .withRel(sprintsDTO.getSprintNumber().toString())
                        .withType("GET");
                linkList.add(link);
            }

            return new ResponseEntity<>(linkList, HttpStatus.OK);

        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(value = "/sprints/{sprintNumber}/projectCode/{projectCode}/")
    public ResponseEntity<Object> getSprintById(
            @PathVariable final int sprintNumber,
            @PathVariable final String projectCode) {
        try {
            SprintID id = SprintID.createSprintID(projectCode, sprintNumber);

            if (sprintService.getSprintBySprintId(id).isEmpty()) {
                return new ResponseEntity<>("Sprint does not exist!", HttpStatus.OK);
            }

            SprintDTO dto = sprintService.getSprintBySprintId(id).get();

            Link link = linkTo(methodOn(ImplSprintController.class)
                    .getSprintById(dto.getSprintNumber(), dto.getProjectCode()))
                    .withSelfRel()
                    .withType("PUT");

            dto.add(link);

            return new ResponseEntity<>(dto, HttpStatus.OK);

        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
