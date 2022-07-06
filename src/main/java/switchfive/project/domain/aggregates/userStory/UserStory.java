package switchfive.project.domain.aggregates.userStory;

import switchfive.project.domain.shared.dddTypes.AggregateRoot;
import switchfive.project.domain.shared.valueObjects.*;

import java.util.Objects;

/**
 * UserStory class describes the data and the methods of its objects.
 *
 * @author Maurício Pinto Barros
 * @version 0
 * @since Meeting 28-12-2021
 */

public class UserStory implements AggregateRoot<UserStory> {

    //Attributes
    /**
     * Unique ID_UserStory that identifies a user story
     */
    private final UserStoryID userStoryID;
    /**
     * Unique project code.
     */
    private final ProjectCode projectCode;
    /**
     * Unique user story identification code.
     */
    private final UserStoryCode userStoryCode;
    /**
     * User story description.
     */
    private final UserStoryDescription description;
    /**
     * Identification code from parent US - parent refined US
     */
    private final UserStoryCode parentUserStory;
    /**
     * This parameter tells us, if a user story is not in any sprint (null) or placed in any of them (1, 2, 3...).
     */
    private SprintID sprintID;
    /**
     * User story effort.
     */
    private EffortEstimate effort;
    /**
     * User story priority
     */
    private Priority priority;
    /**
     * User story status.
     */
    private UserStoryStatus status;

    /**
     * User story constructor which takes three parameters.
     *
     * @param userStoryID   final ID_UserStory userStoryID
     * @param projectCode   final ProjectCode projectCode
     * @param userStoryCode final UserStoryCode userStoryCode
     * @param priority      final Priority priority
     * @param description   final Description description
     */
    public UserStory(final UserStoryID userStoryID,
                     final ProjectCode projectCode,
                     final UserStoryCode userStoryCode,
                     final Priority priority,
                     final UserStoryDescription description) {

        this.userStoryID = userStoryID;
        this.projectCode = projectCode;
        this.userStoryCode = userStoryCode;
        this.sprintID = null;
        this.effort = null;
        this.priority = priority;
        this.description = description;
        this.status = UserStoryStatus.PLANNED;
        this.parentUserStory = null;
    }

    /**
     * User story constructor with parent US identifier
     *
     * @param userStoryID         final ID_UserStory userStoryID
     * @param projectCode         final ProjectCode projectCode
     * @param userStoryCode       final UserStoryCode userStoryCode
     * @param priority            final Priority priority
     * @param description         final Description description
     * @param parentUserStoryCode final UserStoryCode parentUSCode
     */
    public UserStory(final UserStoryID userStoryID,
                     final ProjectCode projectCode,
                     final UserStoryCode userStoryCode,
                     final Priority priority,
                     final UserStoryDescription description,
                     final UserStoryCode parentUserStoryCode) {

        this.userStoryID = userStoryID;
        this.projectCode = projectCode;
        this.userStoryCode = userStoryCode;
        this.sprintID = null;
        this.effort = null;
        this.priority = priority;
        this.description = description;
        this.status = UserStoryStatus.PLANNED;
        this.parentUserStory = parentUserStoryCode;
    }

    /**
     * User story constructor with all attributes
     *
     * @param userStoryID     final ID_UserStory userStoryID
     * @param projectCode     final ProjectCode projectCode
     * @param userStoryCode   final UserStoryCode userStoryCode
     * @param priority        final Priority priority
     * @param description     final Description description
     * @param sprintID        final SprintID sprintID
     * @param effortEstimate  final EffortEstimate effortEstimate
     * @param userStoryStatus final UserStoryStatus userStoryStatus
     * @param parentUSCode    final UserStoryCode parentUSCode
     */
    public UserStory(final UserStoryID userStoryID,
                     final ProjectCode projectCode,
                     final UserStoryCode userStoryCode,
                     final Priority priority,
                     final UserStoryDescription description,
                     final SprintID sprintID,
                     final EffortEstimate effortEstimate,
                     UserStoryStatus userStoryStatus,
                     final UserStoryCode parentUSCode) {
        this.userStoryID = userStoryID;
        this.projectCode = projectCode;
        this.userStoryCode = userStoryCode;
        this.sprintID = sprintID;
        this.effort = effortEstimate;
        this.priority = priority;
        this.description = description;
        this.status = userStoryStatus;
        this.parentUserStory = parentUSCode;
    }

    public UserStoryID getUserStoryID() {
        return userStoryID;
    }

    /**
     * A method that modifies the status of a user story.
     *
     * @param status Status status
     */
    public void updateStatus(String status) {
        this.status = UserStoryStatus.valueOf(status.toUpperCase());
    }

    /**
     * A get method that returns the user story code.
     */
    public String getUserStoryCode() {
        return userStoryCode.getIdentity();
    }

    /**
     * A get method that returns the user story description.
     */
    public String getUserStoryDescription() {
        return description.getDescription();
    }

    public SprintID getSprintID() {
        return this.sprintID;
    }

    public void setSprintID(String projectCode, int sprint) {
        this.sprintID = SprintID.createSprintID(projectCode, sprint);
    }

    /**
     * A get method that returns the project code.
     */
    public String getProjectCodeOfUserStory() {
        return projectCode.getCode();
    }

    /**
     * A get method that returns the user story priority
     */
    public int getPriority() {
        return priority.priority;
    }

    /**
     * A Set method that changes the attribute priority.
     *
     * @param priority int priority
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    /**
     * A get method that returns the user story status.
     *
     * @return the status as a Status
     */
    public String getStatus() {
        return this.status.toString();
    }

    /**
     * @param effort is the attribute to be change
     * @author Tânia Mota
     * Method with the objective of changing the effort of a user story
     */

    public void changeEffort(Integer effort) {
        this.effort = EffortEstimate.createEffortEstimate(effort);
    }

    /**
     * Returns the effort estimate of the UserStory.
     * author: mpc
     *
     * @return EffortEstimate object
     */
    public Integer getEffort() {
        if (this.effort == null) {
            return null;
        } else {
            return effort.getEffort();
        }
    }

    /**
     * Returns the description of parent User Story code
     *
     * @return UserStoryCode
     */
    public String getParentUserStory() {
        if (this.parentUserStory == null) {
            return null;
        } else {
            return parentUserStory.getIdentity();
        }
    }

    /**
     * Sets the User Story to Refined
     */
    public void setStatusToRefined() {
        this.status = UserStoryStatus.REFINED;
    }

    public boolean isUserStoryAvailableForRefinement() {
        return this.status == UserStoryStatus.PLANNED;
    }

    /**
     * Check if the current sprintID has a sprint number of value 0, indicating that the userStory is currently on the product backlog.
     *
     * @return true if this userStory has a sprintID with a sprint number of value 0, false otherwise.
     */
    public boolean isUserStoryInProductBacklog() {
        return this.sprintID.getSprintNumber() == 0;
    }

    /**
     * Check if this userStory status is FINISHED.
     *
     * @return true if it is FINISHED, false otherwise.
     */
    public boolean isUserStoryStatusFinished() {
        return this.status.equals(UserStoryStatus.FINISHED);
    }

    /**
     * Entities compare by identity, not by attributes.
     *
     * @param other The other entity.
     * @return true if the identities are the same, regardless of other
     * attributes.
     */
    @Override
    public boolean sameIdentityAs(UserStory other) {
        return Objects.equals(userStoryCode, other.userStoryCode) && Objects.equals(projectCode, other.projectCode);
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
        if (this == other) {
            return true;
        }
        if (!(other instanceof UserStory)) {
            return false;
        }
        UserStory userStory = (UserStory) other;
        return sameIdentityAs(userStory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userStoryID, projectCode, userStoryCode, sprintID, effort, priority, description, status);
    }

}
