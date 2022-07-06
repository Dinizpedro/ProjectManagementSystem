package switchfive.project.dtos;

import org.springframework.hateoas.RepresentationModel;

public class ResourceDTO extends RepresentationModel<ResourceDTO> {

    /**
     * Unique email associated with the Resource.
     */
    public final String userID;
    /**
     * Project code associated with the Resource.
     */
    public final String projectCode;
    /**
     * Start date and end date associated with the Resource role and between
     * project dates.
     */
    public final TimeDTO dates;
    /**
     * Cost per hour associated with the Resource.
     */
    public final double costPerHour;
    /**
     * % Allocation associated with the Resource.
     */
    public final double percentageOfAllocation;
    /**
     * Role associated with the Resource.
     */
    public final String role;
    /**
     * Unique UUID associated with the Resource.
     */
    public String resourceID;


    public ResourceDTO(String resourceID, String userID, String projectCode, TimeDTO dates,
                       double costPerHour, double percentageOfAllocation, String role) {
        this.resourceID = resourceID;
        this.userID = userID;
        this.projectCode = projectCode;
        this.dates = dates;
        this.costPerHour = costPerHour;
        this.percentageOfAllocation = percentageOfAllocation;
        this.role = role;
    }
}

