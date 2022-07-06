package switchfive.project.domain.factories.iFactories;

import switchfive.project.domain.aggregates.userStory.UserStory;
import switchfive.project.domain.shared.valueObjects.*;

public interface UserStoryFactory {

    UserStory createUserStory(UserStoryID userStoryID, ProjectCode projectCode, UserStoryCode userStoryCode,
                              Priority priority, UserStoryDescription description);

    UserStory createUserStory(UserStoryID userStoryID, ProjectCode projectCode, UserStoryCode userStoryCode,
                              Priority priority, UserStoryDescription description, UserStoryCode parentUserStoryCode);

    UserStory createUserStory(UserStoryID userStoryID, ProjectCode projectCode, UserStoryCode userStoryCode,
                              Priority priority, UserStoryDescription description, SprintID sprintID,
                              EffortEstimate effortEstimate, UserStoryStatus userStoryStatus,
                              UserStoryCode parentUserStoryCode);

}
