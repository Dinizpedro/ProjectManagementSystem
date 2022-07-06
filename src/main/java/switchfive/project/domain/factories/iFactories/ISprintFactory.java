package switchfive.project.domain.factories.iFactories;

import switchfive.project.domain.aggregates.sprint.Sprint;
import switchfive.project.domain.shared.valueObjects.*;

public interface ISprintFactory {

    Sprint create(SprintID sprintID, ProjectCode projectCode,
                  SprintNumber sprintNumber, Time dates,
                  SprintDescription description, SprintStatus status);

}
