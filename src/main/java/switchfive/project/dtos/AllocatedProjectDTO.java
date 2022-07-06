package switchfive.project.dtos;

import org.springframework.hateoas.RepresentationModel;

public class AllocatedProjectDTO extends RepresentationModel<AllocatedProjectDTO> {

    private String projectName;

    private String projectCode;

    private String role;

    public AllocatedProjectDTO() {
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }
}
