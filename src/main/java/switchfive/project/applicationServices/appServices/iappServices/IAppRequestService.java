package switchfive.project.applicationServices.appServices.iappServices;

import switchfive.project.dtos.RequestDTO;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public interface IAppRequestService {
    Optional<RequestDTO> createNewProfileRequest(String userID, String profileDescription) throws NoSuchAlgorithmException;

    Optional<RequestDTO> getRequestDTO(String requestID);
}
