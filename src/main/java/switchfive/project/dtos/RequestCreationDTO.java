package switchfive.project.dtos;

public class RequestCreationDTO {

    public String userEmail;

    public String profileDescription;

    public RequestCreationDTO(String userEmail, String profileID) {
        this.userEmail = userEmail;
        this.profileDescription = profileID;
    }
}
