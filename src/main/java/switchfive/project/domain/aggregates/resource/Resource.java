package switchfive.project.domain.aggregates.resource;

import switchfive.project.domain.shared.dddTypes.AggregateRoot;
import switchfive.project.domain.shared.valueObjects.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Resource class describes the data and the methods of its objects.
 */
public class Resource implements AggregateRoot<Resource> {
    /**
     * Random value.
     */
    private final ResourceID identity;
    /**
     * User who integrates the resource.
     */
    private final Email userID;
    /**
     * Project who integrates the resource.
     */
    private final ProjectCode projectCode;
    /**
     * Resource allocation start and end date.
     */
    private final Time date;
    /**
     * Resource cost per hour.
     */
    private final ResourceCostPerHour costPerHour;
    /**
     * Resource percentage of allocation.
     */
    private final ResourcePercentageOfAllocation allocation;
    /**
     * Enum Role is a special "class" that represents a group of constants
     * (unchangeable variables).
     */
    private final Role role;


    /**
     * @param identityInput    final ID_Resource
     *                         identityInput
     * @param userIDInput      final ID_User userIDInput
     * @param projectCodeInput final ID_User projectIdentityInput
     * @param datesInput       final Time datesInput
     * @param costPerHourInput final ResourceCostPerHour
     *                         costPerHourInput
     * @param allocationInput  final
     *                         ResourcePercentageOfAllocation
     *                         allocationInput
     * @param roleInput        final Role roleInput
     */
    public Resource(final ResourceID identityInput, final Email userIDInput, final ProjectCode projectCodeInput, final Time datesInput, final ResourceCostPerHour costPerHourInput, final ResourcePercentageOfAllocation allocationInput, final Role roleInput) {
        this.identity = identityInput;
        this.userID = userIDInput;
        this.projectCode = projectCodeInput;
        this.date = datesInput;
        this.costPerHour = costPerHourInput;
        this.allocation = allocationInput;
        this.role = roleInput;
    }


    public boolean compareUserAndTeamMember(final String userID) {
        return this.userID.getUserEmail().equals(userID) && this.role.equals(Role.TEAM_MEMBER);
    }

    public boolean compareUserAndPO(final String userID) {
        return this.userID.getUserEmail().equals(userID) && this.role.equals(Role.PRODUCT_OWNER);
    }

    public Email getUserID() {
        return userID;
    }

    public String getUserEmail() {
        return userID.getUserEmail();
    }

    public double getCostPerHour() {
        return costPerHour.getCostPerHour();
    }

    public String getResourceID() {
        return identity.toString();
    }

    public String getProjectCode() {
        return projectCode.getCode();
    }

    public String getStartDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date startDate = this.date.getStartDate();

        return dateFormat.format(startDate);

    }


    public String getEndDate() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date endDate = this.date.getEndDate();

        return dateFormat.format(endDate);
    }

    public String getRole() {
        return role.toString();
    }

    public double getAllocation() {
        return this.allocation.getAllocation();
    }

    public boolean compareUserAndSM(final String userID) {
        return this.userID.getUserEmail().equals(userID) && this.role.equals(Role.SCRUM_MASTER);
    }

    /**
     * @param other The other entity.
     * @return true if other have same identity as this; otherwise, returns
     * false.
     */
    @Override
    public boolean sameIdentityAs(final Resource other) {
        return other != null && this.identity.sameValueAs(other.identity);
    }

    /**
     * @param other other instance
     * @return true id objects are equals; otherwise, returns false.
     */
    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Resource)) {
            return false;
        }
        Resource resource = (Resource) other;
        return sameIdentityAs(resource);
    }

    /**
     * @return hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(identity);
    }

    public boolean isProjectManager() {
        return this.role == Role.PROJECT_MANAGER;
    }

    public boolean isScrumMaster() {
        return this.role == Role.SCRUM_MASTER;
    }

    public boolean isTeamMember() {
        return this.role == Role.TEAM_MEMBER;
    }

    public boolean isResourceInProject(ProjectCode projectCode) {
        return this.projectCode.sameValueAs(projectCode);
    }


}
