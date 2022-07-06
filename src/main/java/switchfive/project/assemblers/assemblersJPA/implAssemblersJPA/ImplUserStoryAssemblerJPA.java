package switchfive.project.assemblers.assemblersJPA.implAssemblersJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switchfive.project.assemblers.assemblersJPA.iAssemblersJPA.IUserStoryAssemblerJPA;
import switchfive.project.dataModel.dataJPA.UserStoryJPA;
import switchfive.project.domain.aggregates.userStory.UserStory;
import switchfive.project.domain.factories.iFactories.UserStoryFactory;
import switchfive.project.domain.shared.valueObjects.*;

@Component public class ImplUserStoryAssemblerJPA implements IUserStoryAssemblerJPA {

    UserStoryFactory userStoryFactory;

    @Autowired public ImplUserStoryAssemblerJPA(UserStoryFactory userStoryFactory) {
        this.userStoryFactory = userStoryFactory;
    }

    @Override public UserStoryID getUserStoryIdFromUserStoryJPA(UserStoryJPA userStoryJPA) {
        UserStoryID userStoryID = userStoryJPA.getUserStoryID();
        return UserStoryID.createUserStoryID(userStoryID.getProjectCode(), userStoryID.getUserStoryCode());
    }

    @Override public ProjectCode getProjectCodeFromUserStoryJPA(UserStoryJPA userStoryJPA) {
        return ProjectCode.create(userStoryJPA.getUserStoryID().getProjectCode());
    }

    @Override public UserStoryCode getUserStoryCodeFromUserStoryJPA(UserStoryJPA userStoryJPA) {
        return UserStoryCode.createUserStoryCode(userStoryJPA.getUserStoryID().getUserStoryCode());
    }

    @Override public UserStoryDescription getDescriptionFromUserStoryJPA(UserStoryJPA userStoryJPA) {
        return UserStoryDescription.createUserStoryDescription(userStoryJPA.getDescription());
    }

    @Override public SprintID getSprintIdFromUserStoryJPA(UserStoryJPA userStoryJPA) {
        SprintID sprintID = SprintID.createSprintID(userStoryJPA.getUserStoryID().getProjectCode(), userStoryJPA.getSprintNumber());

        if (sprintID != null) {
            return SprintID.createSprintID(sprintID.getProjectCode(), sprintID.getSprintNumber());
        } else {
            return null;
        }
    }

    @Override public EffortEstimate getEffortEstimateFromUserStoryJPA(UserStoryJPA userStoryJPA) {
        Integer effort = userStoryJPA.getEffort();

        if (effort != null) {
            return EffortEstimate.createEffortEstimate(userStoryJPA.getEffort());
        } else {
            return null;
        }
    }

    @Override public Priority getPriorityFromUserStoryJPA(UserStoryJPA userStoryJPA) {
        return Priority.createPriority(userStoryJPA.getPriority());
    }

    @Override public UserStoryStatus getStatusFromUserStoryJPA(UserStoryJPA userStoryJPA) {
        return UserStoryStatus.valueOf(userStoryJPA.getStatus());
    }

    @Override public UserStoryCode getParentUserStoryCodeFromUserStoryJPA(UserStoryJPA userStoryJPA) {
        String parentUSCode = userStoryJPA.getParentUserStoryCode();

        if (parentUSCode != null) {
            return UserStoryCode.createUserStoryCode(userStoryJPA.getParentUserStoryCode());
        } else {
            return null;
        }

    }

    @Override public UserStoryJPA userStoryToUserStoryJPA(UserStory userStory) {
        UserStoryJPA userStoryJPA = new UserStoryJPA();

        userStoryJPA.setUserStoryID(userStory.getUserStoryID());
        userStoryJPA.setDescription(userStory.getUserStoryDescription());

        if (userStory.getSprintID() != null) {
            userStoryJPA.setSprintNumber(userStory.getSprintID().getSprintNumber());
        } else {
            userStoryJPA.setSprintNumber(0);
        }

        userStoryJPA.setEffort(userStory.getEffort());
        userStoryJPA.setPriority(userStory.getPriority());
        userStoryJPA.setStatus(userStory.getStatus());
        userStoryJPA.setParentUserStoryCode(userStory.getParentUserStory());

        return userStoryJPA;
    }

    @Override public UserStory userStoryJPAtoUserStory(UserStoryJPA userStoryJPA) {
        UserStoryID userStoryID = getUserStoryIdFromUserStoryJPA(userStoryJPA);
        ProjectCode projectCode = getProjectCodeFromUserStoryJPA(userStoryJPA);
        UserStoryCode userStoryCode = getUserStoryCodeFromUserStoryJPA(userStoryJPA);
        UserStoryDescription userStoryDescription = getDescriptionFromUserStoryJPA(userStoryJPA);
        SprintID sprintID = getSprintIdFromUserStoryJPA(userStoryJPA);
        EffortEstimate effortEstimate = getEffortEstimateFromUserStoryJPA(userStoryJPA);
        Priority priority = getPriorityFromUserStoryJPA(userStoryJPA);
        UserStoryStatus userStoryStatus = getStatusFromUserStoryJPA(userStoryJPA);
        UserStoryCode parentUserStoryCode = getParentUserStoryCodeFromUserStoryJPA(userStoryJPA);

        return userStoryFactory.createUserStory(userStoryID, projectCode, userStoryCode, priority, userStoryDescription, sprintID, effortEstimate, userStoryStatus, parentUserStoryCode);
    }
}
