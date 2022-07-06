package switchfive.project.applicationServices.appServices.iappServices;

import switchfive.project.dtos.ResourceCreationDTO;
import switchfive.project.dtos.ResourceDTO;
import switchfive.project.domain.aggregates.resource.Resource;
import switchfive.project.domain.aggregates.user.User;
import switchfive.project.domain.shared.valueObjects.*;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

/**
 * Resource application service.
 */
public interface IAppResourceService {

    /**
     * Method to define team member of a project.
     */
    Optional<ResourceDTO> definedTeamMemberOfAProject(ResourceCreationDTO dto)
            throws ParseException, NoSuchAlgorithmException;

    /**
     * Method to define product owner of a project.
     */
    Optional<ResourceDTO> definedProductOwnerOfAProject(ResourceCreationDTO dto)
            throws ParseException, NoSuchAlgorithmException;

    /**
     * Method to define scrum master of a project.
     */
    Optional<ResourceDTO> definedScrumMasterOfAProject(ResourceCreationDTO dto)
            throws ParseException, NoSuchAlgorithmException;

    /**
     * Method to create and save Project Manager.
     */
    Resource createProjectManager(Email userID,
                                            ProjectCode projectCode,
                                            Time projectTime,
                                            ResourceCostPerHour costPerHour,
                                            ResourcePercentageOfAllocation allocation)
            throws ParseException, NoSuchAlgorithmException;

    /**
     * Method to find user by user id.
     */
    Optional<User> findUser(final String userID) throws NoSuchAlgorithmException;

    /**
     * @return project manager as an instance of Resource.
     */
    Optional<Resource> getProjectManager(ProjectCode code) throws ParseException;


    Optional<ResourceDTO> getResourceDTO(String resourceID) throws ParseException;

    Optional<List<ResourceDTO>> getResourcesByProjectCode(String projectCode) throws ParseException;

}
