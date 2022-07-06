package switchfive.project.dtos;

import org.springframework.hateoas.RepresentationModel;

public class ChangePasswordDTO extends RepresentationModel<ChangePasswordDTO> {

    public String oldPassword;

    public String newPassword;

    public String email;

    public ChangePasswordDTO() { }

    public ChangePasswordDTO(String oldPassword, String newPassword, String email) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.email = email;
    }
}
