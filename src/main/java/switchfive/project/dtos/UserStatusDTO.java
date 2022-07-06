package switchfive.project.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

public class UserStatusDTO extends RepresentationModel {

    private  String email;
    private  boolean isActivated;

    public UserStatusDTO() {
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserStatusDTO that = (UserStatusDTO) o;
        return isActivated == that.isActivated && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, isActivated);
    }
}
