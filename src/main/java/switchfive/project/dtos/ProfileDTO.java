package switchfive.project.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

public class ProfileDTO extends
        RepresentationModel<ProfileDTO> {

    public String profileDescription;

    public ProfileDTO(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    @Override
    public int hashCode() {
        return Objects.hash(profileDescription);
    }
}
