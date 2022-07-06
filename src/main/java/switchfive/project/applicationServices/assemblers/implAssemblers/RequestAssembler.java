package switchfive.project.applicationServices.assemblers.implAssemblers;

import switchfive.project.dtos.RequestDTO;
import switchfive.project.domain.aggregates.request.Request;

public class RequestAssembler {

    public static RequestDTO toDto(final Request request) {

        String resourceID = request.getIdentity();
        String creationDate = request.getCreationDate();
        String userID = request.getUserID();
        String profileDescription = request.getProfileDescription();
        boolean isApproved = request.isRequestApproved();

        return new RequestDTO(resourceID, creationDate, userID
                , profileDescription, isApproved);
    }


}
