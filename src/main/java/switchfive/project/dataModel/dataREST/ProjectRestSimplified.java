package switchfive.project.dataModel.dataREST;


/**
 * DTO received from external REST API, filtered by the parameters needed to get a Project when director ask all Projects in the system.
 */
public class ProjectRestSimplified {

    private String code;
    private String projectName;
    private String description;
    private String status;
    private String startDate;

    public ProjectRestSimplified() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
