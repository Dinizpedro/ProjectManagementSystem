package switchfive.project.domain.domainServices.iDomainServices;

import switchfive.project.dtos.ResourceCreationDTO;
import switchfive.project.domain.aggregates.project.Project;
import switchfive.project.domain.aggregates.resource.Resource;

import java.text.ParseException;
import java.util.ArrayList;

public interface IResourceDomainService {

    boolean validateNewTeamMemberResource(ArrayList<Resource> resources,
                                          Project project,
                                          ResourceCreationDTO dto) throws ParseException;

    boolean validateNewProductOwnerResource(ArrayList<Resource> resources,
                                            Project project,
                                            ResourceCreationDTO dto)
            throws ParseException;

    boolean validateNewScrumMasterResource(ArrayList<Resource> resources,
                                           Project project,
                                           ResourceCreationDTO dto)
            throws ParseException;

}
