package switchfive.project.dtos;

import java.util.ArrayList;

public class RefineUserStoryDTO {

    private String projectCode;

    private String userStoryCode;

    private ArrayList<String> newUserStoryDescription;

    public RefineUserStoryDTO() {
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getUserStoryCode() {
        return userStoryCode;
    }

    public void setUserStoryCode(String userStoryCode) {
        this.userStoryCode = userStoryCode;
    }

    public ArrayList<String> getNewUserStoryDescription() {
        return new ArrayList<>(newUserStoryDescription);
    }

    public void setNewUserStoryDescription(ArrayList<String> newUserStoryDescription) {
        this.newUserStoryDescription = new ArrayList<>(newUserStoryDescription);
    }
}
