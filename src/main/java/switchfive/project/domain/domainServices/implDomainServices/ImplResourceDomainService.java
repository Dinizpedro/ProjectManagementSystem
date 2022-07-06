package switchfive.project.domain.domainServices.implDomainServices;

import org.springframework.stereotype.Service;
import switchfive.project.dtos.ResourceCreationDTO;
import switchfive.project.domain.aggregates.project.Project;
import switchfive.project.domain.aggregates.resource.Resource;
import switchfive.project.domain.domainServices.iDomainServices.IResourceDomainService;

import java.text.ParseException;
import java.util.ArrayList;

@Service
public class ImplResourceDomainService implements IResourceDomainService {


    public boolean validateNewTeamMemberResource(ArrayList<Resource> resources,
                                                 Project project,
                                                 ResourceCreationDTO dto)
            throws ParseException {

        return validateIfResourceDoesntAlreadyExistAndIsNotPO(resources, dto) &&
                areResourceDTODatesWithinProjectDates(project, dto);
    }

    public boolean validateNewProductOwnerResource(ArrayList<Resource> resources,
                                                   Project project,
                                                   ResourceCreationDTO dto)
            throws ParseException {

        return validateIfResourceDoesntAlreadyExistAndIsNotPO(resources, dto) &&
                areResourceDTODatesWithinProjectDates(project, dto);
    }

    public boolean validateNewScrumMasterResource(ArrayList<Resource> resources,
                                                  Project project,
                                                  ResourceCreationDTO dto)
            throws ParseException {

        return validateIfResourceDoesntAlreadyExistAndIsNotPOAndSM(resources, dto) &&
                areResourceDTODatesWithinProjectDates(project, dto);
    }

    private boolean areResourceDTODatesWithinProjectDates(Project project,
                                                          ResourceCreationDTO dto)
            throws ParseException {
        return project.areDatesWithinProjectDates(dto.startDateDto,
                dto.endDateDto);
    }

   /* private boolean validateIfResourceDoesntAlreadyExistAndIsNotSM(
            ArrayList<Resource> resources, ResourceCreationDTO dto) {
        for (Resource resource : resources) {
            if (resource.compareUserAndTeamMember(dto.userIdDto) ||
                    resource.compareUserAndSM(dto.userIdDto)) {
                return false;
            }
        }
        return true;
    }*/

    private boolean validateIfResourceDoesntAlreadyExistAndIsNotPO(
            ArrayList<Resource> resources, ResourceCreationDTO dto) {

        for (Resource resource : resources) {
            if (resource.compareUserAndTeamMember(dto.userIdDto) ||
                    resource.compareUserAndPO(dto.userIdDto)) {
                return false;
            }
        }
        return true;
    }

    private boolean validateIfResourceDoesntAlreadyExistAndIsNotPOAndSM(
            ArrayList<Resource> resources, ResourceCreationDTO dto) {
        for (Resource resource : resources) {
            if (resource.compareUserAndTeamMember(dto.userIdDto) ||
                    resource.compareUserAndPO(dto.userIdDto) || resource.compareUserAndSM(dto.userIdDto)) {
                return false;
            }
        }
        return true;
    }
}
