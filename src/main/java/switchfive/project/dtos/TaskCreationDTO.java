package switchfive.project.dtos;

import java.util.List;

/**
 * This DTO carries data (inserted by SM/PM) between UI and Domain.
 */
public class TaskCreationDTO {

    /**
     * The name of the task.
     */
    private String nameDto;
    /**
     * The task description.
     */
    private String descriptionDto;
    /**
     * Task start date.
     */
    private String startDateDto;
    /**
     * Task end date.
     */
    private String endDateDto;
    /**
     * Precedence (precendence task codes).
     */
    private List<TaskIdDTO> precedenceDto;
    /**
     * Email of the task's responsible.
     */
    private String responsibleResourceUUIDDto;
    /**
     * Type of task (Meeting, Documentation, Design, Implementation, Testing,
     * Deployment, etc.).
     */
    private String typeOfTaskDto;
    /**
     * Code of project which the task is part of.
     */
    private String projectCodeDto;
    /**
     * Number of sprint which the task is part of.
     */
    private Integer sprintNumberDto;
    /**
     * Code of UserStory in which the tasks is part of.
     */
    private String userStoryCode;
    /**
     * Effort estimate (start by having the initial estimate, but can be
     * updated several times throughout the project; uses Fibonacci series
     * for duration in hours.).
     */
    private int effortDto;

    public TaskCreationDTO() {
    }

    public String getNameDto() {
        return nameDto;
    }

    public void setNameDto(String nameDto) {
        this.nameDto = nameDto;
    }

    public String getDescriptionDto() {
        return descriptionDto;
    }

    public void setDescriptionDto(String descriptionDto) {
        this.descriptionDto = descriptionDto;
    }

    public String getStartDateDto() {
        return startDateDto;
    }

    public void setStartDateDto(String startDateDto) {
        this.startDateDto = startDateDto;
    }

    public String getEndDateDto() {
        return endDateDto;
    }

    public void setEndDateDto(String endDateDto) {
        this.endDateDto = endDateDto;
    }

    public List<TaskIdDTO> getPrecedenceDto() {
        return precedenceDto;
    }

    public void setPrecedenceDto(List<TaskIdDTO> precedenceDto) {
        this.precedenceDto = precedenceDto;
    }

    public String getResponsibleResourceUUIDDto() {
        return responsibleResourceUUIDDto;
    }

    public void setResponsibleResourceUUIDDto(String responsibleResourceUUIDDto) {
        this.responsibleResourceUUIDDto = responsibleResourceUUIDDto;
    }

    public String getTypeOfTaskDto() {
        return typeOfTaskDto;
    }

    public void setTypeOfTaskDto(String typeOfTaskDto) {
        this.typeOfTaskDto = typeOfTaskDto;
    }

    public String getProjectCodeDto() {
        return projectCodeDto;
    }

    public void setProjectCodeDto(String projectCodeDto) {
        this.projectCodeDto = projectCodeDto;
    }

    public Integer getSprintNumberDto() {
        return sprintNumberDto;
    }

    public void setSprintNumberDto(Integer sprintNumberDto) {
        this.sprintNumberDto = sprintNumberDto;
    }

    public String getUserStoryCode() {
        return userStoryCode;
    }

    public void setUserStoryCode(String userStoryCode) {
        this.userStoryCode = userStoryCode;
    }

    public int getEffortDto() {
        return effortDto;
    }

    public void setEffortDto(int effortDto) {
        this.effortDto = effortDto;
    }
}
