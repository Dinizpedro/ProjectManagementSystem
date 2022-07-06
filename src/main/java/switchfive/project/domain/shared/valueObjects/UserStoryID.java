package switchfive.project.domain.shared.valueObjects;

import switchfive.project.domain.aggregates.task.TaskContainer;
import switchfive.project.domain.shared.dddTypes.ValueObject;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserStoryID implements ValueObject<UserStoryID>, TaskContainer, Serializable {
    /**
     * Unique project code
     */
    private String projectCode;
    /**
     * Unique user story code in a project
     */
    private String userStoryCode;

    /**
     * ID_UserStory constructor which takes ywo parameters.
     *
     * @param projectCode   final String projectCode
     * @param userStoryCode final String userStoryCode
     */
    private UserStoryID(final String projectCode, final String userStoryCode) {
        this.projectCode = projectCode;
        this.userStoryCode = userStoryCode;
    }

    public UserStoryID() {
    }

    public static UserStoryID createUserStoryID(final String projectCode, final String userStoryCode) {
        return new UserStoryID(projectCode, userStoryCode);
    }

    /**
     * Method to return a project code string.
     */
    public String getProjectCode() {
        return projectCode;
    }

    /**
     * Method to return a user story code string.
     */
    public String getUserStoryCode() {
        return userStoryCode;
    }

    @Override
    public Object getTaskContainerID() {
        return this;
    }

    /**
     * @param other The other value object.
     * @return true if the given value object's and this value object's
     * attributes are the same.
     */
    @Override
    public boolean sameValueAs(UserStoryID other) {
        return projectCode.equals(other.projectCode) && userStoryCode.equals(other.userStoryCode);
    }

    /**
     * Check if two objects have the same data, as the classes in Java are
     * inherited from the object classes only.
     *
     * @param other final Object other
     * @return true if two objects have the same data; otherwise, returns false.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof UserStoryID)) return false;
        UserStoryID userStory = (UserStoryID) other;
        return sameValueAs(userStory);
    }

    /**
     * Whenever hashcode is invoked on the same object more than once
     * during an execution of a Java application, the hashCode method must
     * consistently return the same integer, provided no information used in
     * equals comparisons on the object is modified.
     *
     * @return true if two objects have the same hashcode; otherwise, returns
     * false.
     */
    @Override
    public int hashCode() {
        return Objects.hash(projectCode, userStoryCode);
    }
}
