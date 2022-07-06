package switchfive.project.dtos;

import java.util.Objects;

public class ProfileCreationDTO {

    public String description;

    public ProfileCreationDTO(String description) {
        this.description = description;
    }

    public ProfileCreationDTO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileCreationDTO that = (ProfileCreationDTO) o;
        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
