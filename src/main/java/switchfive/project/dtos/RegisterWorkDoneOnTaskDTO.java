package switchfive.project.dtos;

public class RegisterWorkDoneOnTaskDTO {
    /**
     * The project identifier.
     */
    public String projectCode;
    /**
     * The sprint identifier.
     */
    public int sprintNumber;
    /**
     * The task identifier.
     */
    public int taskCode;
    /**
     * Time spent on the work session.
     */
    public double timeSpent;
    /**
     * Description of the work session.
     */
    public String workDescription;
}
