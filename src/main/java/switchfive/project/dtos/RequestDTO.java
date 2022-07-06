package switchfive.project.dtos;

import org.springframework.hateoas.RepresentationModel;

public class RequestDTO extends RepresentationModel<RequestDTO> {

    /**
     * Request identification UUID.
     */
    public String requestID;
    /**
     * Request creation date.
     */
    public String creationDate;
    /**
     * User identifier that order the request.
     */
    public String userID;
    /**
     * Profile identifier requested by user.
     */
    public String profileDescription;
    /**
     * Request approval status.
     */
    public boolean isApproved;

    public RequestDTO(String requestID,
                      String creationDate,
                      String userID,
                      String profileDescription,
                      boolean isApproved) {
        this.requestID = requestID;
        this.creationDate = creationDate;
        this.userID = userID;
        this.profileDescription = profileDescription;
        this.isApproved = isApproved;
    }
}
