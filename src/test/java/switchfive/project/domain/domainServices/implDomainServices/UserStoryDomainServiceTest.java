package switchfive.project.domain.domainServices.implDomainServices;

import org.junit.jupiter.api.Test;
import switchfive.project.domain.aggregates.project.Project;
import switchfive.project.domain.aggregates.sprint.Sprint;
import switchfive.project.domain.aggregates.userStory.UserStory;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserStoryDomainServiceTest {

    private ImplUserStoryDomainService userStoryDomainService = new ImplUserStoryDomainService();

    @Test
    void shouldReturnFalseWhenTheProjectStatusIsClosed() {
        Project projectMock = mock(Project.class);
        Sprint sprintMock = mock(Sprint.class);
        UserStory userStoryMock = mock(UserStory.class);

        when(projectMock.isProjectClosed()).thenReturn(true);

        assertFalse(userStoryDomainService.
                canUSBeMovedFromProductBacklogToSprintBacklog(projectMock,
                sprintMock,
                userStoryMock));
    }

    @Test
    void shouldReturnFalseWhenTheSprintStatusIsFinished() {
        Project projectMock = mock(Project.class);
        Sprint sprintMock = mock(Sprint.class);
        UserStory userStoryMock = mock(UserStory.class);

        when(sprintMock.isSprintStatusFinished()).thenReturn(true);

        assertFalse(userStoryDomainService.
                canUSBeMovedFromProductBacklogToSprintBacklog(projectMock,
                        sprintMock,
                        userStoryMock));
    }

    @Test
    void shouldReturnFalseWhenUserStoryStatusIsFinished() {
        Project projectMock = mock(Project.class);
        Sprint sprintMock = mock(Sprint.class);
        UserStory userStoryMock = mock(UserStory.class);

        when(userStoryMock.isUserStoryStatusFinished()).thenReturn(true);

        assertFalse(userStoryDomainService.
                canUSBeMovedFromProductBacklogToSprintBacklog(projectMock,
                        sprintMock,
                        userStoryMock));
    }

    @Test
    void shouldReturnFalseWhenUserStoryIsNotInProductBacklog() {
        Project projectMock = mock(Project.class);
        Sprint sprintMock = mock(Sprint.class);
        UserStory userStoryMock = mock(UserStory.class);

        when(userStoryMock.isUserStoryInProductBacklog()).thenReturn(false);

        assertFalse(userStoryDomainService.
                canUSBeMovedFromProductBacklogToSprintBacklog(projectMock,
                        sprintMock,
                        userStoryMock));
    }

    @Test
    void shouldReturnTrueWhenUserStoryIsIsInProductBacklogAndTheStatusesAreNotClosed() {
        Project projectMock = mock(Project.class);
        Sprint sprintMock = mock(Sprint.class);
        UserStory userStoryMock = mock(UserStory.class);

        when(projectMock.isProjectClosed()).thenReturn(false);
        when(sprintMock.isSprintStatusFinished()).thenReturn(false);
        when(userStoryMock.isUserStoryStatusFinished()).thenReturn(false);
        when(userStoryMock.isUserStoryInProductBacklog()).thenReturn(true);

        assertTrue(userStoryDomainService.
                canUSBeMovedFromProductBacklogToSprintBacklog(projectMock,
                        sprintMock,
                        userStoryMock));
    }
}