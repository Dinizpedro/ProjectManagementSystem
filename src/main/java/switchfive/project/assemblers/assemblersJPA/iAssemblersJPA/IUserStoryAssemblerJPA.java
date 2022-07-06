package switchfive.project.assemblers.assemblersJPA.iAssemblersJPA;

import switchfive.project.dataModel.dataJPA.UserStoryJPA;
import switchfive.project.domain.aggregates.userStory.UserStory;
import switchfive.project.domain.shared.valueObjects.*;

public interface IUserStoryAssemblerJPA {

    UserStoryID getUserStoryIdFromUserStoryJPA(UserStoryJPA userStoryJPA);

    ProjectCode getProjectCodeFromUserStoryJPA(UserStoryJPA userStoryJPA);

    UserStoryCode getUserStoryCodeFromUserStoryJPA(UserStoryJPA userStoryJPA);

    UserStoryDescription getDescriptionFromUserStoryJPA(UserStoryJPA userStoryJPA);

    SprintID getSprintIdFromUserStoryJPA(UserStoryJPA userStoryJPA);

    EffortEstimate getEffortEstimateFromUserStoryJPA(UserStoryJPA userStoryJPA);

    Priority getPriorityFromUserStoryJPA(UserStoryJPA userStoryJPA);

    UserStoryStatus getStatusFromUserStoryJPA(UserStoryJPA userStoryJPA);

    UserStoryCode getParentUserStoryCodeFromUserStoryJPA(UserStoryJPA userStoryJPA);

    UserStoryJPA userStoryToUserStoryJPA(UserStory userStory);

    UserStory userStoryJPAtoUserStory(UserStoryJPA userStoryJPA);
}
