package switchfive.project.applicationServices.appServices.implAppServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchfive.project.dtos.ResourceCreationDTO;
import switchfive.project.dtos.ResourceDTO;
import switchfive.project.applicationServices.appServices.iappServices.IAppResourceService;
import switchfive.project.mappers.mappersApp.iMappers.IResourceMapper;
import switchfive.project.applicationServices.iRepositories.IProjectRepository;
import switchfive.project.applicationServices.iRepositories.IResourceRepository;
import switchfive.project.applicationServices.iRepositories.IUserRepository;
import switchfive.project.domain.aggregates.project.Project;
import switchfive.project.domain.aggregates.resource.Resource;
import switchfive.project.domain.aggregates.user.User;
import switchfive.project.domain.domainServices.iDomainServices.IResourceDomainService;
import switchfive.project.domain.factories.iFactories.IResourceFactory;
import switchfive.project.domain.shared.valueObjects.*;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public final class ImplAppResourceService implements IAppResourceService {
    /**
     * ProjectStore interface. Constructor injected.
     */
    private final IProjectRepository iProjectRepository;
    /**
     * UserRepository interface. Constructor injected.
     */
    private final IUserRepository iUserRepository;
    /**
     * ResourceRepository interface. Constructor injected.
     */
    private final IResourceRepository iResourceRepository;
    /**
     * Resource domain-service interface. Constructor injected.
     */
    private final IResourceDomainService iResourceDomainService;
    /**
     * ResourceFactory interface. Constructor injected.
     */
    private final IResourceFactory resourceFactory;
    /**
     * ResourceMapper interface. Constructor injected.
     */
    private final IResourceMapper iResourceMapper;

    @Autowired
    public ImplAppResourceService(
            final IProjectRepository iProjectStoreInput,
            final IUserRepository iUserRepositoryInput,
            final IResourceRepository iResourceRepositoryInput,
            final IResourceDomainService iResourceDomainServiceInput,
            final IResourceFactory resourceFactoryInput, IResourceMapper iResourceMapper) {
        this.iProjectRepository = iProjectStoreInput;
        this.iUserRepository = iUserRepositoryInput;
        this.iResourceRepository = iResourceRepositoryInput;
        this.iResourceDomainService = iResourceDomainServiceInput;
        this.resourceFactory = resourceFactoryInput;
        this.iResourceMapper = iResourceMapper;
    }

    /**
     * Method to find user by userID.
     */
    public Optional<User> findUser(final String userID) throws NoSuchAlgorithmException {
        return this.iUserRepository.findUserByEmail(userID);
    }

    /**
     * Method to find ProjectManager by role.
     */
    public Optional<Resource> getProjectManager(final ProjectCode code)
            throws ParseException {
        return this.iResourceRepository.getProjectManager(code);
    }

    @Override
    public Optional<ResourceDTO> getResourceDTO(String resourceID)
            throws ParseException {
        ResourceID resourceIDToFind = ResourceID.createResourceID(resourceID);

        Optional<Resource> resourceInRepo =
                iResourceRepository.getResourceByID(resourceIDToFind);

        ResourceDTO resourceDTO = null;
        if (resourceInRepo.isPresent()) {
            resourceDTO = iResourceMapper.toDto(resourceInRepo.get());
        }
        return Optional.ofNullable(resourceDTO);
    }

    /**
     * Method to create and save projectManager of a project.
     */
    @Override
    public Resource createProjectManager(final Email email,
                                         final ProjectCode projectCode,
                                         final Time projectTime,
                                         final ResourceCostPerHour costPerHour,
                                         final ResourcePercentageOfAllocation allocation)
            throws ParseException {

        ResourceID resourceID = ResourceID.createResourceID();
        Role role = Role.PROJECT_MANAGER;

        if (!this.iUserRepository.userExists(email.getUserEmail())) {
            throw new IllegalArgumentException("Email doesn't exist.");
        }

        return this.resourceFactory.createResource(resourceID, email,
                projectCode, projectTime, costPerHour, allocation, role);
    }

    /**
     * Method to create and save team member of a project.
     */
    public Optional<ResourceDTO> definedTeamMemberOfAProject(
            ResourceCreationDTO dto)
            throws ParseException, NoSuchAlgorithmException {
        ResourceDTO resourceDTO = null;
        ProjectCode projectCodeVO = ProjectCode.create(dto.projectCodeDto);

        boolean projectExistsInDB = this.iProjectRepository.projectExists(projectCodeVO);
        boolean userExistsInDB = this.iUserRepository.userExists(dto.userIdDto);

        if (projectExistsInDB && userExistsInDB) {

            ArrayList<Resource> resourcesList =
                    this.iResourceRepository.getResourcesByProjectCode(dto.projectCodeDto);

            Project project = this.iProjectRepository.findByCode(projectCodeVO).get();

            if (this.iResourceDomainService.validateNewTeamMemberResource(
                    resourcesList, project, dto)) {
                ProjectCode projectCode =
                        ProjectCode.create(dto.projectCodeDto);
                ResourceID resourceID = ResourceID.createResourceID();
                Email userID = Email.create(dto.userIdDto);
                Time dates = Time.create(dto.startDateDto,
                        dto.endDateDto);
                ResourceCostPerHour costPerHour =
                        ResourceCostPerHour.create(dto.costPerHourDto);
                ResourcePercentageOfAllocation allocation =
                        ResourcePercentageOfAllocation.create(
                                dto.percentageOfAllocationDto);
                Role role = Role.TEAM_MEMBER;

                Resource newResource =
                        this.resourceFactory.createResource(resourceID, userID,
                                projectCode, dates, costPerHour, allocation,
                                role);

                Resource resourceInDB = this.iResourceRepository.save(newResource);

                resourceDTO = iResourceMapper.toDto(resourceInDB);
            }

        }

        return Optional.ofNullable(resourceDTO);
    }

    /**
     * Method to create and save product owner of a project.
     */
    @Override
    public Optional<ResourceDTO> definedProductOwnerOfAProject(final ResourceCreationDTO dto) throws ParseException, NoSuchAlgorithmException {

        ResourceDTO resourceDTO = null;
        ProjectCode projectCodeVO = ProjectCode.create(dto.projectCodeDto);

        Optional<Project> projectExists = this.iProjectRepository.findByCode(projectCodeVO);
        Optional<User> userExists = this.iUserRepository.findUserByEmail(dto.userIdDto);

        if (projectExists.isPresent()
                && userExists.isPresent()) {

            ArrayList<Resource> resources =
                    this.iResourceRepository.getResourcesByProjectCode(dto.projectCodeDto);

            Project project =
                    this.iProjectRepository.findByCode(projectCodeVO)
                            .get();

            if (this.iResourceDomainService.validateNewProductOwnerResource(
                    resources, project, dto)) {
                ProjectCode projectCode =
                        ProjectCode.create(dto.projectCodeDto);
                ResourceID resourceID = ResourceID.createResourceID();
                Email userID = Email.create(dto.userIdDto);
                Time dates = Time.create(dto.startDateDto,
                        dto.endDateDto);
                ResourceCostPerHour costPerHour =
                        ResourceCostPerHour.create(dto.costPerHourDto);
                ResourcePercentageOfAllocation allocation =
                        ResourcePercentageOfAllocation.create(
                                dto.percentageOfAllocationDto);
                Role role = Role.PRODUCT_OWNER;

                Resource newResource =
                        this.resourceFactory.createResource(resourceID, userID,
                                projectCode, dates, costPerHour, allocation,
                                role);

                Resource resourceInDB = iResourceRepository.save(newResource);
                resourceDTO = iResourceMapper.toDto(resourceInDB);
            }

        }

        return Optional.ofNullable(resourceDTO);
    }

    /**
     * Method to create and save scrum master of a project.
     */
    @Override
    public Optional<ResourceDTO> definedScrumMasterOfAProject(final ResourceCreationDTO dto) throws ParseException, NoSuchAlgorithmException {

        ResourceDTO resourceDTO = null;
        ProjectCode projectCodeVO = ProjectCode.create(dto.projectCodeDto);
        Optional<Project> projectExists = this.iProjectRepository.findByCode(projectCodeVO);
        Optional<User> userExists = this.iUserRepository.findUserByEmail(dto.userIdDto);

        if (projectExists.isPresent() && userExists.isPresent()) {

            ArrayList<Resource> resources =
                    this.iResourceRepository.getResourcesByProjectCode(dto.projectCodeDto);

            Project project =
                    this.iProjectRepository.findByCode(projectCodeVO)
                            .get();

            if (this.iResourceDomainService.validateNewScrumMasterResource(
                    resources, project, dto)) {
                ProjectCode projectCode =
                        ProjectCode.create(dto.projectCodeDto);
                ResourceID resourceID = ResourceID.createResourceID();
                Email userID = Email.create(dto.userIdDto);
                Time dates = Time.create(dto.startDateDto,
                        dto.endDateDto);
                ResourceCostPerHour costPerHour =
                        ResourceCostPerHour.create(dto.costPerHourDto);
                ResourcePercentageOfAllocation allocation =
                        ResourcePercentageOfAllocation.create(
                                dto.percentageOfAllocationDto);
                Role role = Role.SCRUM_MASTER;

                Resource newResource =
                        this.resourceFactory.createResource(resourceID, userID,
                                projectCode, dates, costPerHour, allocation,
                                role);

                Resource resourceInDB = iResourceRepository.save(newResource);
                resourceDTO = iResourceMapper.toDto(resourceInDB);
            }

        }

        return Optional.ofNullable(resourceDTO);
    }

    public Optional<List<ResourceDTO>> getResourcesByProjectCode(String code) throws ParseException {
        ResourceDTO eachResourceDTO;
        List<ResourceDTO> resourcesDTO = new ArrayList<>();

        ProjectCode projectCodeToFind = ProjectCode.create(code);

        if (!this.iProjectRepository.projectExists(projectCodeToFind)) {
            throw new IllegalArgumentException("Selected project does not exist!");
        }

        if (iResourceRepository.findAllResourcesByProjectCode(projectCodeToFind).isEmpty()) {
            return Optional.empty();
        }

        List<Resource> resourcesList = iResourceRepository.findAllResourcesByProjectCode(projectCodeToFind).get();

        for (Resource resourcesInDB : resourcesList) {
            eachResourceDTO = iResourceMapper.toDto(resourcesInDB);
            resourcesDTO.add(eachResourceDTO);
        }

        return Optional.of(resourcesDTO);
    }


}

