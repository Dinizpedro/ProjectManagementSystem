package switchfive.project.domain.domainServices.iDomainServices;

import switchfive.project.domain.aggregates.project.Project;
import switchfive.project.domain.aggregates.sprint.Sprint;
import switchfive.project.domain.aggregates.userStory.UserStory;

public interface IUserStoryDomainService {

    boolean canUSBeMovedFromProductBacklogToSprintBacklog(Project theProject,
                                                          Sprint theSprint,
                                                          UserStory theUserStory);
}
