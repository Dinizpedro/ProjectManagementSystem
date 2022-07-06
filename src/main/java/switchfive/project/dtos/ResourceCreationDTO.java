package switchfive.project.dtos;

public class  ResourceCreationDTO {

    /**
     * Email that identifies the user who takes the action.
     */
    public String userIdDto;

    /**
     * Project code in which the user story will be modified.
     */
    public String projectCodeDto;

    /**
     * Resource time, as a startDate.
     */
    public String startDateDto;

    /**
     * Resource time, as a  endDate.
     */
    public String endDateDto;

    /**
     * Resource cost per hour, as double.
     */
    public double costPerHourDto;

    /**
     * Resource percentage of allocation, as double.
     */
    public double percentageOfAllocationDto;

    public ResourceCreationDTO() {
    }

    public ResourceCreationDTO(String userIdDto, String projectCodeDto, String startDate, String endDate, double costPerHourDto, double percentageOfAllocationDto) {
        this.userIdDto = userIdDto;
        this.projectCodeDto = projectCodeDto;
        this.startDateDto = startDate;
        this.endDateDto = endDate;
        this.costPerHourDto = costPerHourDto;
        this.percentageOfAllocationDto = percentageOfAllocationDto;
    }
}

