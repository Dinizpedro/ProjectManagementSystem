package switchfive.project.interfaceAdapters.controllers.iControllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import switchfive.project.dtos.ProfileCreationDTO;

public interface IProfileController {

    ResponseEntity<Object> addNewProfile(@RequestBody ProfileCreationDTO
                                                 profileCreationDTO);

    ResponseEntity<Object> getProfile (@PathVariable String profileDescription);
}
