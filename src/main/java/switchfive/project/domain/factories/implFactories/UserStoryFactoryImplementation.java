package switchfive.project.domain.factories.implFactories;

import org.springframework.stereotype.Component;
import switchfive.project.domain.aggregates.userStory.UserStory;
import switchfive.project.domain.factories.iFactories.UserStoryFactory;
import switchfive.project.domain.shared.valueObjects.*;

@Component
public class UserStoryFactoryImplementation implements UserStoryFactory {

    @Override
    public UserStory createUserStory(UserStoryID userStoryID, ProjectCode projectCode, UserStoryCode userStoryCode,
                                     Priority priority, UserStoryDescription description) {
        return new UserStory(userStoryID, projectCode, userStoryCode, priority, description);
    }

    @Override
    public UserStory createUserStory(UserStoryID userStoryID, ProjectCode projectCode, UserStoryCode userStoryCode,
                                     Priority priority, UserStoryDescription description, UserStoryCode parentUserStoryCode) {
        return new UserStory(userStoryID, projectCode, userStoryCode, priority, description, parentUserStoryCode);
    }


    @Override
    public UserStory createUserStory(UserStoryID userStoryID, ProjectCode projectCode, UserStoryCode userStoryCode,
                                     Priority priority, UserStoryDescription description, SprintID sprintID,
                                     EffortEstimate effortEstimate, UserStoryStatus userStoryStatus,
                                     UserStoryCode parentUserStoryCode) {
        return new UserStory(userStoryID, projectCode, userStoryCode, priority, description, sprintID,
                effortEstimate, userStoryStatus, parentUserStoryCode);
    }
}
