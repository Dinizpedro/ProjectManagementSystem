package switchfive.project.dtos;

import org.springframework.hateoas.RepresentationModel;

public class ActivityDTO extends RepresentationModel<ActivityDTO> {

    private String typeOfActivity;

    private String activityCode;

    private String activityStatus;

    public ActivityDTO() {
    }

    public ActivityDTO(String typeOfActivity, String activityCode,
                       String activityStatus) {
        this.typeOfActivity = typeOfActivity;
        this.activityCode = activityCode;
        this.activityStatus = activityStatus;
    }
        public String getTypeOfActivity() {
            return typeOfActivity;
        }

        public void setTypeOfActivity(String typeOfActivity) {
            this.typeOfActivity = typeOfActivity;
        }

        public String getActivityCode() {
            return activityCode;
        }

        public void setActivityCode(String activityCode) {
            this.activityCode = activityCode;
        }

        public String getActivityStatus() {
            return activityStatus;
        }

        public void setActivityStatus(String activityStatus) {
            this.activityStatus = activityStatus;
        }
    }

