package switchfive.project.dataModel.dataJPA;

import org.springframework.lang.Nullable;
import switchfive.project.domain.shared.valueObjects.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tasks")
@IdClass(TaskIDJPA.class)
public class TaskJPA {

    @Id
    private String taskCode;
    @Id
    private String projectCode;

    @Nullable
    private Integer sprintNumber;
    @Nullable
    private String userStoryCode;

    private String name;
    private String description;
    private String startDate;
    private String endDate;
    private double hoursSpent;
    private Integer effortEstimate;
    private double percentageOfExecution;

    @CollectionTable(name = "task_precedence")
    @ElementCollection
    private List<TaskIDJPA> precedenceList;


    private String typeOfTask;
    private String resourceResponsible;

    @CollectionTable(name = "task_logs")
    @ElementCollection
    private List<Log> taskLogs;

    private String taskStatus;


    public TaskJPA() {
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskJPA)) return false;
        TaskJPA taskJPA = (TaskJPA) o;
        return sameIdentityAs(taskJPA);
    }

    public boolean sameIdentityAs(final TaskJPA other) {
        return other != null &&
                this.taskCode.equals(other.taskCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskCode, projectCode, sprintNumber, userStoryCode, name, description, startDate, endDate, hoursSpent, effortEstimate, percentageOfExecution, precedenceList, typeOfTask, resourceResponsible, taskLogs, taskStatus);
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public double getHoursSpent() {
        return hoursSpent;
    }

    public void setHoursSpent(double hoursSpent) {
        this.hoursSpent = hoursSpent;
    }

    public Integer getEffortEstimate() {
        return effortEstimate;
    }

    public void setEffortEstimate(Integer effortEstimate) {
        this.effortEstimate = effortEstimate;
    }

    public double getPercentageOfExecution() {
        return percentageOfExecution;
    }

    public void setPercentageOfExecution(double percentageOfExecution) {
        this.percentageOfExecution = percentageOfExecution;
    }

    public List<TaskIDJPA> getPrecedenceList() {
        return precedenceList;
    }

    public void setPrecedenceList(List<TaskIDJPA> precedenceList) {
        this.precedenceList = precedenceList;
    }

    public String getTypeOfTask() {
        return typeOfTask;
    }

    public void setTypeOfTask(String typeOfTask) {
        this.typeOfTask = typeOfTask;
    }

    public String getResourceResponsible() {
        return resourceResponsible;
    }

    public void setResourceResponsible(String resourceResponsible) {
        this.resourceResponsible = resourceResponsible;
    }

    public List<Log> getTaskLogs() {
        return taskLogs;
    }

    public void setTaskLogs(List<Log> taskLogs) {
        this.taskLogs = taskLogs;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    @Nullable
    public Integer getSprintNumber() {
        return sprintNumber;
    }

    public void setSprintNumber(@Nullable Integer sprintNumber) {
        this.sprintNumber = sprintNumber;
    }

    @Nullable
    public String getUserStoryCode() {
        return userStoryCode;
    }

    public void setUserStoryCode(@Nullable String userStoryCode) {
        this.userStoryCode = userStoryCode;
    }
}
