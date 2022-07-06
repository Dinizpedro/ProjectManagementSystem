package switchfive.project.interfaceAdapters.implRepositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switchfive.project.assemblers.assemblersJPA.iAssemblersJPA.IResourceAssemblerJPA;
import switchfive.project.dataModel.dataJPA.ResourceJPA;
import switchfive.project.infrastructure.persistence.repositories.iRepositoriesJPA.IResourceRepositoryJPA;
import switchfive.project.applicationServices.iRepositories.IResourceRepository;
import switchfive.project.domain.aggregates.resource.Resource;
import switchfive.project.domain.shared.valueObjects.Email;
import switchfive.project.domain.shared.valueObjects.ProjectCode;
import switchfive.project.domain.shared.valueObjects.ResourceID;
import switchfive.project.domain.shared.valueObjects.Role;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ImplResourceRepository implements IResourceRepository {

    /**
     * Repository of resources.
     */
    private final IResourceRepositoryJPA iResourceRepositoryJPA;
    private final IResourceAssemblerJPA iResourceAssemblerJPA;

    @Autowired
    public ImplResourceRepository(IResourceRepositoryJPA iResourceRepositoryJPA,
                                  IResourceAssemblerJPA iResourceAssemblerJPA) {
        this.iResourceRepositoryJPA = iResourceRepositoryJPA;
        this.iResourceAssemblerJPA = iResourceAssemblerJPA;
    }

    public ArrayList<Resource> getResourcesByProjectCode(final String projectCode) throws ParseException {
        ArrayList<ResourceJPA> resourceListJPA = iResourceRepositoryJPA.
                getResourceJPAByProjectCode(projectCode);

        ArrayList<Resource> resourceList = new ArrayList<>();

        for (ResourceJPA resourceJPA : resourceListJPA) {
            Resource resource = iResourceAssemblerJPA.toDomain(resourceJPA);
            resourceList.add(resource);
        }

        return resourceList;
    }


    /**
     * Private method that adds the new Resource to the system
     *
     * @param newResource New Resource in Data Base objects to be
     *                    validated and added to DataManagement System
     */
    public Resource save(final Resource newResource) throws ParseException {
        ResourceJPA resourceJPA = this.iResourceAssemblerJPA.toData(newResource);
        ResourceJPA resourceJPAResult = this.iResourceRepositoryJPA.save(resourceJPA);

        return iResourceAssemblerJPA.toDomain(resourceJPAResult);
    }


    /**
     * Looks for a specific resource and returns an object
     *
     * @param code that will be searched
     * @return an Optional project manager (model domain object)
     */
    public Optional<Resource> getProjectManager(final ProjectCode code) throws ParseException {

        Resource resourceToFind = null;
        String projectCode = code.getCode();
        String pmRole = Role.PROJECT_MANAGER.toString();

        Optional<ResourceJPA> optResourceJPA = this.iResourceRepositoryJPA
                .getResourceJPAByProjectCodeAndRole(projectCode, pmRole);

        if (optResourceJPA.isPresent()) {
            ResourceJPA resourceJPA = optResourceJPA.get();

            resourceToFind = iResourceAssemblerJPA.toDomain(resourceJPA);
        }

        return Optional.ofNullable(resourceToFind);
    }


    /**
     * Looks for a specific profile description and returns an object
     *
     * @param resourceID that will be searched
     * @return an resource (model domain object)
     */
    public Optional<Resource> getResourceByID(
            final ResourceID resourceID) throws ParseException {

        Resource resourceToFind = null;

        Optional<ResourceJPA> optionalResourceJPA = iResourceRepositoryJPA.findById(resourceID.toString());
        if (optionalResourceJPA.isPresent()) {
            ResourceJPA resourceJPA = optionalResourceJPA.get();
            resourceToFind = iResourceAssemblerJPA.toDomain((resourceJPA));

        }

        return Optional.ofNullable(resourceToFind);

    }

    /**
     * get a list of of all resources created for user matching the param Email.
     * Uses JPA Repository to find the resources this user is assigned to.
     * @param email
     * @return
     * @throws ParseException
     */
    public List<Resource> getResourcesByEmail(Email email) throws ParseException {
        List<Resource> resources = new ArrayList<>();
        String emailString = email.getUserEmail();
        List<ResourceJPA> resourcesJPA = iResourceRepositoryJPA.getResourcesJPAByEmail(emailString);

        for (ResourceJPA resourceJPA : resourcesJPA) {
            Resource theResource = iResourceAssemblerJPA.toDomain(resourceJPA);
            resources.add(theResource);
        }
        return resources;
    }

    public Optional<List<Resource>> findAllResourcesByProjectCode(final ProjectCode code)
            throws ParseException {
        List<Resource> resources = new ArrayList<>();

        List<ResourceJPA> resourcesInRepo = iResourceRepositoryJPA.getResourceJPAByProjectCode(code.getCode());

        if (resourcesInRepo.isEmpty()) {

            return Optional.empty();
        }

        for (ResourceJPA resourceJPA : resourcesInRepo) {
            Resource resourceDomain = this.iResourceAssemblerJPA.toDomain(resourceJPA);
            resources.add(resourceDomain);
        }

        return Optional.ofNullable(resources);
    }
}
