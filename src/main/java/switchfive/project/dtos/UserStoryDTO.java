package switchfive.project.dtos;

import org.springframework.hateoas.RepresentationModel;

public class UserStoryDTO extends RepresentationModel<UserStoryDTO> {
    /**
     * The unique code of user story.
     */
    private String code;
    /**
     * The estimated effort of a user story, always null in product backlog.
     */
    private Integer effort;
    /**
     * A int priority of the user story.
     */
    private int priority;
    /**
     * The user story description.
     */
    private String description;
    /**
     * The user story status.
     */
    private String status;
    /**
     * The parent User Story identifier
     */
    private String parentUserStoryCode;


    public UserStoryDTO() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getEffort() {
        return effort;
    }

    public void setEffort(Integer effort) {
        this.effort = effort;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
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

    public String getParentUserStoryCode() {
        return parentUserStoryCode;
    }

    public void setParentUserStoryCode(String parentUserStoryCode) {
        this.parentUserStoryCode = parentUserStoryCode;
    }
}
