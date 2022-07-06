package switchfive.project.dtos;

public class TimeDTO {
    /**
     * Start Date, as a String.
     */
    public String startDate;
    /**
     * End Date, as a String.
     */
    public String endDate;

    public TimeDTO(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
