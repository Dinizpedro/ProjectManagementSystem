package switchfive.project.dtos;

public class UpdateUserProfileDTO {
    public String profile;

    public UpdateUserProfileDTO(String profile) {
        this.profile = profile;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
