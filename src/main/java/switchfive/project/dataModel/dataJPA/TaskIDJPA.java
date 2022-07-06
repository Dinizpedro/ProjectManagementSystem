package switchfive.project.dataModel.dataJPA;

import java.io.Serializable;

public class TaskIDJPA implements Serializable {

    private String taskCode;

    private String projectCode;

    private TaskIDJPA(String taskCode, String projectCode) {
        this.taskCode = taskCode;
        this.projectCode = projectCode;
    }

    public TaskIDJPA(){
    }

    public static TaskIDJPA createTaskIDJPA(String taskCode, String projectCode) {
        return new TaskIDJPA(taskCode, projectCode);
    }


    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }
}
