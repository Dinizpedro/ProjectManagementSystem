package switchfive.project.dtos;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public class TaskDTO extends
        RepresentationModel<TaskDTO> {

    private String taskCode;
    private String nameDto;
    private String descriptionDto;
    private String startDateDto;
    private String endDateDto;
    private List<Link> precedenceDto;
    private String responsibleResourceUUIDDto;
    private String typeOfTaskDto;
    private String projectCodeDto;
    private Integer sprintNumberDto;
    private String userStoryCode;
    private int effortDto;
    private String statusOfTask;

    private double hourSpent;

    private double percentageOfExecution;

    public TaskDTO() {
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
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

    public List<Link> getPrecedenceDto() {
        return precedenceDto;
    }

    public void setPrecedenceDto(List<Link> precedenceDto) {
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

    public String getStatusOfTask() {
        return statusOfTask;
    }

    public void setStatusOfTask(String statusOfTask) {
        this.statusOfTask = statusOfTask;
    }

    public double getHourSpent() {
        return hourSpent;
    }

    public void setHourSpent(double hourSpent) {
        this.hourSpent = hourSpent;
    }

    public double getPercentageOfExecution() {
        return percentageOfExecution;
    }

    public void setPercentageOfExecution(double percentageOfExecution) {
        this.percentageOfExecution = percentageOfExecution;
    }
}
