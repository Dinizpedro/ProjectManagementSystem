package switchfive.project.domain.domainServices.iDomainServices;

import switchfive.project.domain.aggregates.project.Project;
import switchfive.project.domain.shared.valueObjects.Time;


import java.text.ParseException;

public interface ISprintDomainService {

    boolean sprintIsValid(Time previousSprintTime, Time newSprintTime,
                          Project project)
            throws ParseException;

}
