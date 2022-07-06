package switchfive.project.interfaceAdapters.controllers.implControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switchfive.project.interfaceAdapters.controllers.iControllers.IProfileController;
import switchfive.project.dtos.ProfileCreationDTO;
import switchfive.project.dtos.ProfileDTO;
import switchfive.project.applicationServices.appServices.iappServices.IAppProfileService;

import java.util.Optional;
import java.util.Set;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping(value = "/api")
public class ImplProfileController implements IProfileController {

    @Autowired
    private IAppProfileService iAppProfileService;

    public ImplProfileController(IAppProfileService iAppProfileService) {
        this.iAppProfileService = iAppProfileService;
    }

    /**
     * Gets a Profile that matches the input description.
     * @param profileDescription
     * @return Response Entity object with the data transfer object the specified profile and HttpStatus 200 Ok.
     * Otherwise, if the profile is not found a HttpStatus of 404 is returned.
     * Also, if an exception is thrown a HttpStatus 400 Bad Request is returned.
     */
    @GetMapping("/profiles/{profileDescription}")
    public ResponseEntity<Object> getProfile(@PathVariable String profileDescription) {
        try {
            Optional<ProfileDTO> optProfileDTO = iAppProfileService.getProfile(profileDescription);

            if (optProfileDTO.isPresent()) {
                ProfileDTO profileDTO = optProfileDTO.get();
                Link link = linkTo(methodOn(ImplProfileController.class)
                        .getProfile(profileDTO.profileDescription)).withSelfRel();
                profileDTO.add(link);
                return new ResponseEntity<>(profileDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Profile not found in repository",
                        HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Get all Profiles stored in the system.
     * @return RepresentationModel object with the links to all individual profiles and HttpStatus 200 Ok.
     * Otherwise if exception is thrown an HttpStatus 400 Bad Request is returned.
     */
    @GetMapping("/profiles")
    public ResponseEntity<Object> getProfiles() {
        try {
            Set<ProfileDTO> profilesDTO = iAppProfileService.getProfiles();
            RepresentationModel<ProfileDTO> profileLinks = new RepresentationModel<>();

            for (ProfileDTO profileDTO : profilesDTO) {
                Link link = linkTo(methodOn(ImplProfileController.class)
                        .getProfile(profileDTO.profileDescription)).withRel(profileDTO.profileDescription);
                profileLinks.add(link);
            }

            return new ResponseEntity<>(profileLinks, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }



    /**
     * Create a new Profile in the system
     * @param profileCreationDTO - DTO with the new profile description
     * @return representation of the newly created Profile and HttpStatus 201 Created.
     * Otherwise it returns a HttpStatus 400 Bad Request
     */
    @PostMapping("/profiles")
    public ResponseEntity<Object> addNewProfile(@RequestBody ProfileCreationDTO
                                                        profileCreationDTO) {
        try {
            String inputProfileDescription = profileCreationDTO.description;
            Optional<ProfileDTO> profileDTOOpt = iAppProfileService
                    .addNewProfile(inputProfileDescription);

            if (profileDTOOpt.isPresent()) {
                ProfileDTO profileDTO = profileDTOOpt.get();

                Link link = linkTo(methodOn(ImplProfileController.class)
                        .getProfile(profileDTO.profileDescription)).withSelfRel();

                profileDTO.add(link);
                return new ResponseEntity<>(profileDTO, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Profile already in Store",
                        HttpStatus.BAD_REQUEST);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }


}
