package switchfive.project.assemblers.assemblersJPA.implAssemblersJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switchfive.project.assemblers.assemblersJPA.iAssemblersJPA.ISprintAssemblerJPA;
import switchfive.project.dataModel.dataJPA.SprintJPA;
import switchfive.project.domain.aggregates.sprint.Sprint;
import switchfive.project.domain.factories.iFactories.ISprintFactory;
import switchfive.project.domain.shared.valueObjects.*;

import java.text.ParseException;

@Component
public class ImplSprintAssemblerJPA implements ISprintAssemblerJPA {

    ISprintFactory sprintFactory;

    @Autowired
    public ImplSprintAssemblerJPA(ISprintFactory sprintFactory) {
        this.sprintFactory = sprintFactory;
    }

    public Sprint toDomain(final SprintJPA sprintJPA) throws ParseException {

        SprintID sprintID = sprintJPA.getSprintID();
        String codeJpa = sprintJPA.getSprintID().getProjectCode();
        Integer sprintNumberJpa = sprintJPA.getSprintID().getSprintNumber();
        String startDateJpa = sprintJPA.getStartDate();
        String endDateJpa = sprintJPA.getEndDate();
        String descriptionJpa = sprintJPA.getDescription();
        String statusJpa = sprintJPA.getStatus();

        ProjectCode code = ProjectCode.create(codeJpa);
        SprintNumber sprintNumber = SprintNumber.create(sprintNumberJpa);
        Time dates = Time.create(startDateJpa, endDateJpa);
        SprintDescription description = SprintDescription.create(descriptionJpa);
        SprintStatus status = SprintStatus.valueOf(statusJpa);

        return sprintFactory.create(sprintID, code, sprintNumber, dates, description, status);
    }
}
