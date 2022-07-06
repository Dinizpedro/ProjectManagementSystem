package switchfive.project.applicationServices.assemblers.implAssemblers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switchfive.project.dtos.SprintDTO;
import switchfive.project.applicationServices.assemblers.iAssemblers.ISprintAssembler;
import switchfive.project.domain.aggregates.sprint.Sprint;

@Component
public class ImplSprintAssembler implements ISprintAssembler {

    @Autowired
    protected ImplSprintAssembler() {
    }

    public SprintDTO toDTO(Sprint sprint) {
        SprintDTO output = new SprintDTO();

        output.setProjectCode(sprint.getProjectCode());
        output.setSprintNumber(sprint.getSprintNumber());
        output.setStartDate(sprint.getDates().startDate);
        output.setEndDate(sprint.getDates().endDate);
        output.setDescription(sprint.getDescription());
        output.setStatus(sprint.getStatus());

        return output;
    }
}
