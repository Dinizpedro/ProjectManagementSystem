package switchfive.project.applicationServices.iRepositories;

import switchfive.project.domain.aggregates.userStory.UserStory;
import switchfive.project.domain.shared.valueObjects.ProjectCode;
import switchfive.project.domain.shared.valueObjects.UserStoryCode;

import java.util.List;
import java.util.Optional;

public interface IUserStoryRepository {
    Optional<UserStory> save(UserStory newUserStory);

    int nextUserStoryNumber(ProjectCode projectCode);

    String generatorCode(ProjectCode projectCode);

    List<UserStory> getUserStoryListProductBacklog(ProjectCode projectCode);

    Optional<UserStory> getUserStory(ProjectCode projectCode, UserStoryCode userStoryCode);

    boolean existsUserStory(ProjectCode projectCode, UserStoryCode userStoryCode);

    List<UserStory> getUserStoriesOrderedByStatus(ProjectCode projectCode);

}
