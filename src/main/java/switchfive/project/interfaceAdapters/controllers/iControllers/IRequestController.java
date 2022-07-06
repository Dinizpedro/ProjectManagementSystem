package switchfive.project.interfaceAdapters.controllers.iControllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import switchfive.project.dtos.RequestCreationDTO;

import java.security.NoSuchAlgorithmException;

public interface IRequestController {
    ResponseEntity<Object> createNewProfileRequest(RequestCreationDTO requestCreationDTO) throws NoSuchAlgorithmException;

    ResponseEntity<Object> getRequest(@PathVariable String requestID);
}
