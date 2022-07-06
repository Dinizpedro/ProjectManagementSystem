package switchfive.project.interfaceAdapters.controllers.implControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switchfive.project.interfaceAdapters.controllers.iControllers.IResourceController;
import switchfive.project.dtos.ResourceCreationDTO;
import switchfive.project.dtos.ResourceDTO;
import switchfive.project.applicationServices.appServices.iappServices.IAppResourceService;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * RequestMapping — by Path.
 */
@RestController
@RequestMapping("/api")
public class ImplResourceController implements IResourceController {

    /**
     * Inject Resource application service.  Constructor injected parameter.
     */
    private final IAppResourceService resourceService;

    /**
     * Constructor with injected service.
     */
    @Autowired
    public ImplResourceController(
            final IAppResourceService resourceService) {
        this.resourceService = resourceService;
    }

    /**
     * @param resourceID as String
     * @return HTTP response using Spring ResponseEntity Object.
     */
    @GetMapping(value = "/resources/{resourceID}")
    public ResponseEntity<Object> getResource(
            @PathVariable final String resourceID) throws ParseException {

        try {
            Optional<ResourceDTO> optionalResourceDTO = this.resourceService.getResourceDTO(resourceID);

            if (optionalResourceDTO.isPresent()) {

                ResourceDTO resourceDTO = optionalResourceDTO.get();


                Link link = linkTo(methodOn(ImplResourceController.class).
                        getResource(resourceDTO.resourceID))
                        .withSelfRel();
                resourceDTO.add(link);

                return new ResponseEntity<>(resourceDTO, HttpStatus.OK);

            } else {
                return new ResponseEntity<>("Resource not found in Repository", HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(),
                    HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping(value = "/resources/projectCode/{projectCode}/")
    public ResponseEntity<Object> getAllResourcesByProjectCode(@PathVariable final String projectCode) {
        try {
            RepresentationModel<ResourceDTO> resourcesLinks = new RepresentationModel<>();

            if (resourceService.getResourcesByProjectCode(projectCode).isEmpty()) {
                return new ResponseEntity<>("Project resources list is empty!", HttpStatus.OK);
            }

            List<ResourceDTO> resourceDTOList = resourceService.getResourcesByProjectCode(projectCode).get();

            for (ResourceDTO resourcesDTO : resourceDTOList) {
               Link link = linkTo(methodOn(ImplResourceController.class).
                        getResource(resourcesDTO.resourceID))
                        .withRel(resourcesDTO.resourceID)
                        .withType("GET");
                resourcesLinks.add(link);
            }

            return new ResponseEntity<>(resourcesLinks, HttpStatus.OK);

        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @param dto with inputs.
     * @return HTTP response using Spring ResponseEntity Object.
     */
    @PostMapping("/resources/teamMember")
    @ResponseBody
    public ResponseEntity<Object> definedTeamMemberOfAProject(
            @RequestBody ResourceCreationDTO dto) {

        try {
            Optional<ResourceDTO> optionalResourceDTO =
                    resourceService.definedTeamMemberOfAProject(dto);

            if (optionalResourceDTO.isPresent()) {

                ResourceDTO resourceDTO =
                        optionalResourceDTO.get();

                Link link = linkTo(methodOn(ImplResourceController.class)
                        .getResource(resourceDTO.resourceID)).withSelfRel();

                resourceDTO.add(link);

                return new ResponseEntity<>(resourceDTO, HttpStatus.CREATED);

            } else {

                return new ResponseEntity<>("Resource team member creation failed.",
                        HttpStatus.NOT_FOUND);

            }
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @param dto with inputs.
     * @return HTTP response using Spring ResponseEntity Object.
     */
    @PostMapping("/resources/productOwner")
    @ResponseBody
    public ResponseEntity<Object> definedProductOwnerOfAProject(
            @RequestBody ResourceCreationDTO dto) {

        try {

            Optional<ResourceDTO> optionalResourceDTO =
                    resourceService.definedProductOwnerOfAProject(dto);

            if (optionalResourceDTO.isPresent()) {

                ResourceDTO resourceDTO =
                        optionalResourceDTO.get();

                Link link = linkTo(methodOn(ImplResourceController.class)
                        .getResource(resourceDTO.resourceID)).withSelfRel();

                resourceDTO.add(link);

                return new ResponseEntity<>(resourceDTO, HttpStatus.CREATED);

            } else {

                return new ResponseEntity<>("Resource product owner creation failed.",
                        HttpStatus.NOT_FOUND);
            }

        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/resources/scrumMaster")
    @ResponseBody
    public ResponseEntity<Object> definedScrumMasterOfAProject(
            @RequestBody ResourceCreationDTO dto) {

        try {
            Optional<ResourceDTO> optionalResourceDTO =
                    resourceService.definedScrumMasterOfAProject(dto);

            if (optionalResourceDTO.isPresent()) {

                ResourceDTO resourceDTO =
                        optionalResourceDTO.get();

                Link link = linkTo(methodOn(ImplResourceController.class)
                        .getResource(resourceDTO.resourceID)).withSelfRel();

                resourceDTO.add(link);

                return new ResponseEntity<>(resourceDTO, HttpStatus.CREATED);

            } else {

                return new ResponseEntity<>("Resource scrum master creation failed.",
                        HttpStatus.NOT_FOUND);

            }
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
