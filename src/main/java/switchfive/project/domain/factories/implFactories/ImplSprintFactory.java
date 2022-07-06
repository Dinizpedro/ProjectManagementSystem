package switchfive.project.domain.factories.implFactories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switchfive.project.domain.aggregates.sprint.Sprint;
import switchfive.project.domain.factories.iFactories.ISprintFactory;
import switchfive.project.domain.shared.valueObjects.ProjectCode;
import switchfive.project.domain.shared.valueObjects.SprintDescription;
import switchfive.project.domain.shared.valueObjects.SprintID;
import switchfive.project.domain.shared.valueObjects.SprintNumber;
import switchfive.project.domain.shared.valueObjects.SprintStatus;
import switchfive.project.domain.shared.valueObjects.Time;

@Component
public class ImplSprintFactory implements ISprintFactory {

    @Autowired
    public ImplSprintFactory() {
    }

    public Sprint create(final SprintID sprintID, final ProjectCode projectCode,
                         final SprintNumber sprintNumber, final Time dates,
                         final SprintDescription description,
                         final SprintStatus status) {

        return new Sprint(sprintID, projectCode, sprintNumber, dates,
                description, status);
    }
}
