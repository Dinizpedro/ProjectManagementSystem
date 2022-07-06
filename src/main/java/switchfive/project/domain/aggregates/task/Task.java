package switchfive.project.domain.aggregates.task;

import switchfive.project.dataModel.dataJPA.TaskIDJPA;
import switchfive.project.domain.shared.dddTypes.AggregateRoot;
import switchfive.project.domain.shared.valueObjects.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Task class describes the data and the methods of its objects.
 *
 * @author dst
 */
public class Task implements AggregateRoot<Task> {
    /**
     * Number of the task in Sprint. Starts with 1 and it's auto-incremented.
     */
    TaskID idTask;
    /**
     * Designation of the task.
     */
    TaskName name;
    /**
     * Description of the task.
     */
    TaskDescription description;
    /**
     * Start date, end date.
     */
    Time taskTime;
    /**
     * Hours spent (0 at the time it is created).
     */
    Hour hoursSpent;
    /**
     * Effort estimate (start by having the initial estimate, but can be
     * updated several times throughout the project; uses Fibonacci series
     * for duration in hours.).
     */
    EffortEstimate effortEstimate;
    /**
     * Percentage of execution (0% if it has not yet started, up to 100% when
     * it is completed; value calculated automatically based on the
     * relationship between the hours spent and the estimated effort).
     */
    TaskPercentageOfExecution taskPercentageOfExecution;
    /**
     * Precedence (optional, list of tasks that must be completed before the
     * start of the task).
     */
    List<TaskIDJPA> precedenceList;
    /**
     * Type of task (Meeting, Documentation, Design, Implementation, Testing,
     * Deployment, etc.).
     */
    TypeOfTask typeOfTask;
    /**
     * Responsible (human resource responsible for the execution of the task).
     */
    ResourceID responsible;

    /**
     * List of logs that store the work done on a task.
     */
    List<Log> taskLogs = new ArrayList<>();
    /**
     * List of possible status in a task.
     */
    TaskStatus taskStatus;

    /**
     * Constructor for Task with the main attributes for its creation,
     * therefore some values are the default one - percentage of execution and
     * hours spent will be zero. The status of a just created task is always of planned
     * @param idTask
     * @param name
     * @param description
     * @param taskTime
     * @param effortEstimate
     * @param precedenceList
     * @param typeOfTask
     * @param responsible
     */
    public Task(TaskID idTask, TaskName name, TaskDescription description, Time taskTime,
                EffortEstimate effortEstimate, List<TaskIDJPA> precedenceList, TypeOfTask typeOfTask,
                ResourceID responsible) {
        this.idTask = idTask;
        this.name = name;
        this.description = description;
        this.taskTime = taskTime;
        this.hoursSpent = Hour.createHour(0);
        this.effortEstimate = effortEstimate;
        this.taskPercentageOfExecution = TaskPercentageOfExecution.createPercentage(0);
        this.precedenceList = precedenceList;
        this.typeOfTask = typeOfTask;
        this.responsible = responsible;
        this.taskStatus = TaskStatus.PLANNED;
    }

    /**
     * Task constructor for task for which it is possible to update its data.
     * @param idTask
     * @param name
     * @param description
     * @param taskTime
     * @param hoursSpent
     * @param effortEstimate
     * @param taskPercentageOfExecution
     * @param precedenceList
     * @param typeOfTask
     * @param responsible
     * @param taskLogs
     * @param taskStatus
     */
     public Task(TaskID idTask, TaskName name,
                TaskDescription description,
                Time taskTime, Hour hoursSpent,
                EffortEstimate effortEstimate,
                TaskPercentageOfExecution taskPercentageOfExecution,
                List<TaskIDJPA> precedenceList, TypeOfTask typeOfTask,
                ResourceID responsible, List<Log> taskLogs,
                TaskStatus taskStatus) {
        this.idTask = idTask;
        this.name = name;
        this.description = description;
        this.taskTime = taskTime;
        this.hoursSpent = hoursSpent;
        this.effortEstimate = effortEstimate;
        this.taskPercentageOfExecution = taskPercentageOfExecution;
        this.precedenceList = precedenceList;
        this.typeOfTask = typeOfTask;
        this.responsible = responsible;
        this.taskLogs = taskLogs;
        this.taskStatus = taskStatus;
    }

    /**
     * Adds a new log entry to this taskLogs.
     *
     * @param timeSpent       the amount of time in hours spent in a given
     *                        work session.
     * @param workDescription a description of the work done in a given work
     *                        session.
     * @return true if it was added, false otherwise.
     */
    public boolean addTaskLog(final Hour timeSpent,
                              final TaskDescription workDescription) {
        final int logCode = generateLogCode();
        Log newLog = Log.createLog(logCode, timeSpent, workDescription);
        updateHoursSpent(timeSpent);
        return this.taskLogs.add(newLog);
    }

    private void updateHoursSpent(Hour timeSpent) {
        double currentHours = this.hoursSpent.getAmountOfHours();
        double newHours = timeSpent.getAmountOfHours();
        double newTotal = currentHours + newHours;
        this.hoursSpent = Hour.createHour(newTotal);
    }

    private int generateLogCode() {
        return this.taskLogs.size() + 1;
    }

    /**
     * Verifies if the status of a given task is finished.
     * @return true if tasks is finished, otherwise false.
     */
    public boolean isTaskFinished() {
        return this.taskStatus.equals(TaskStatus.FINISHED);
    }


    @Override
    public boolean sameIdentityAs(final Task other) {
        return other != null && this.idTask.equals(other.idTask);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return sameIdentityAs(task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTask);
    }

    /**
     * method to get a TaskID
     * @return a TaskID
     */
    public TaskID getIdTask() {
        return idTask;
    }

    /**
     * Method to get a Task Name.
     * @return a String of the name
     */
    public String getName() {
        return name.getName();
    }

    /**
     * Method to get a Task Description.
     * @return a String of the description
     */
    public String getDescription() {
        return description.getDescription();
    }

    /**
     * Method to get the Start date of a task in the format
     * "dd/MM/yyyy"
     * @return a String for the start date.
     */
    public String getStartDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date startDate = this.taskTime.getStartDate();

        return dateFormat.format(startDate);

    }

    /**
     * Method to get the End date of a task in the format
     * "dd/MM/yyyy"
     * @return a String for the end date.
     */
    public String getEndDate() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date endDate = this.taskTime.getEndDate();

        return dateFormat.format(endDate);
    }

    /**
     * Method to get the Hour spent working on a task.
     * @return a double of hours spent.
     */
    public double getHoursSpent() {
        return hoursSpent.getAmountOfHours();
    }

    /**
     * Method to get the Effort Estimate of a Task
     * @return an Integer of the Effort Estimate
     */
    public Integer getEffortEstimate() {
        return effortEstimate.getEffort();
    }

    /**
     * Method to get the Percentage of Execution of a Task.
     * @return a double of the percentage of execution
     */
    public double getPercentageOfExecution() {
        return taskPercentageOfExecution.getPercentageOfExecution();
    }

    /**
     * Method to get a list of precedence of a Task.
     * It returns a list of other tasks which come before the new task.
     * @return list of TaskIDJPA
     */
    public List<TaskIDJPA> getPrecedenceList() {
        return precedenceList;
    }

    /**
     * method to get type of task.
     * @return a string of type of task.
     */
    public String getTypeOfTask() {
        return typeOfTask.toString();
    }

    /**
     * Method to find the resource responsible for a task
     * @return a String of that responsible ID
     */
    public String getResponsible() {
        return responsible.getResourceID().toString();
    }

    /**
     * Method to get the logs of a Task
     * @return a list of Log
     */
    public List<Log> getTaskLogs() {
        return taskLogs;
    }

    /**
     * Method to get the status of a Task
     * @return a String of its status
     */
    public String getTaskStatus() {
        return taskStatus.toString();
    }
}
