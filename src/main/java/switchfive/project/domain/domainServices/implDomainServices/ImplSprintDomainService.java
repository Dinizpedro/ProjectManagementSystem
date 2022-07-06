package switchfive.project.domain.domainServices.implDomainServices;

import org.springframework.stereotype.Service;
import switchfive.project.domain.aggregates.project.Project;
import switchfive.project.domain.domainServices.iDomainServices.ISprintDomainService;
import switchfive.project.domain.shared.valueObjects.Time;

import java.text.ParseException;

@Service
public class ImplSprintDomainService implements ISprintDomainService {

    public boolean sprintIsValid(Time previousSprintTime, Time newSprintTime,
                                 Project project)
            throws ParseException {
        String projectStartDate = project.getDates().startDate;
        String projectEndDate = project.getDates().endDate;
        Time projectTime = Time.create(projectStartDate, projectEndDate);

        if (!project.isProjectClosed()) {
            if (newSprintTime.getStartDate()
                    .after(projectTime.getStartDate()) &&
                    newSprintTime.getEndDate()
                            .before(projectTime.getEndDate())) {
                if (previousSprintTime == null) {
                    return true;
                } else {
                    return newSprintTime.getStartDate()
                            .after(previousSprintTime.getEndDate());
                }
            }
        }

        return false;
    }
}
