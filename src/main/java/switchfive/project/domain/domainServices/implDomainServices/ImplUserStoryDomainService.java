package switchfive.project.domain.domainServices.implDomainServices;

import org.springframework.stereotype.Service;
import switchfive.project.domain.aggregates.project.Project;
import switchfive.project.domain.aggregates.sprint.Sprint;
import switchfive.project.domain.aggregates.userStory.UserStory;
import switchfive.project.domain.domainServices.iDomainServices.IUserStoryDomainService;

@Service
public class ImplUserStoryDomainService implements IUserStoryDomainService {

    /**
     * Checks if the project status is not closed, if the sprint and userStory status are not finished.
     *
     * @param theProject   the project to be checked.
     * @param theSprint    the sprint to be checked.
     * @param theUserStory the userStory to be checked.
     * @return true if the status are not finished, false otherwise.
     */
    public boolean canUSBeMovedFromProductBacklogToSprintBacklog(Project theProject,
                                                                 Sprint theSprint,
                                                                 UserStory theUserStory) {
        return !theProject.isProjectClosed() &&
                !theSprint.isSprintStatusFinished() &&
                !theUserStory.isUserStoryStatusFinished() &&
                theUserStory.isUserStoryInProductBacklog();
    }


}
