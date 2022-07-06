package switchfive.project.dataModel.dataJPA;

import org.springframework.lang.Nullable;
import switchfive.project.domain.shared.valueObjects.UserStoryID;

import javax.persistence.*;

@Entity
@Table(name = "userStories")
public class UserStoryJPA implements JPA {

    @EmbeddedId
    private UserStoryID userStoryID;

    private String description;

     @Nullable
    private Integer sprintNumber;

    private Integer effort;

    private int priority;

    private String status;

    private String parentUserStoryCode;

    public UserStoryJPA() {
    }

    public UserStoryID getUserStoryID() {
        return userStoryID;
    }

    public void setUserStoryID(UserStoryID userStoryID) {
        this.userStoryID = userStoryID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Nullable public Integer getSprintNumber() {
        return sprintNumber;
    }

    public void setSprintNumber(@Nullable Integer sprintNumber) {
        this.sprintNumber = sprintNumber;
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
