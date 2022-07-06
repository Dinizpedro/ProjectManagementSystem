package switchfive.project.interfaceAdapters.controllers.implControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switchfive.project.interfaceAdapters.controllers.iControllers.IRequestController;
import switchfive.project.dtos.RequestCreationDTO;
import switchfive.project.dtos.RequestDTO;
import switchfive.project.applicationServices.appServices.iappServices.IAppRequestService;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api")
public class ImplRequestController implements IRequestController {

    @Autowired
    private IAppRequestService iAppRequestService;

    public ImplRequestController(IAppRequestService iAppRequestService) {
        this.iAppRequestService = iAppRequestService;
    }

    @GetMapping(value = "/requests/{requestID}")
    public ResponseEntity<Object> getRequest(@PathVariable String requestID) {

        Optional<RequestDTO> optRequestDTO = this.iAppRequestService.getRequestDTO(requestID);

        if (optRequestDTO.isPresent()) {
            RequestDTO requestDTO = optRequestDTO.get();
            return new ResponseEntity<>(requestDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Request not found in repository",
                    HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/requests")
    public ResponseEntity<Object> createNewProfileRequest(@RequestBody RequestCreationDTO
                                                                  requestCreationDTO) throws NoSuchAlgorithmException {

        Optional<RequestDTO> result = iAppRequestService
                .createNewProfileRequest(requestCreationDTO.userEmail,
                        requestCreationDTO.profileDescription);

        if (result.isPresent()) {
            RequestDTO requestDTO = result.get();
            Link link = linkTo(methodOn(ImplRequestController.class)
                    .getRequest(requestDTO.requestID)).withSelfRel();

            requestDTO.add(link);

            return new ResponseEntity<>(requestDTO, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Request creation failed",
                    HttpStatus.BAD_REQUEST);
        }

    }
}
