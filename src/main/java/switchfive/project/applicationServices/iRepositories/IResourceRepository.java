package switchfive.project.applicationServices.iRepositories;

import switchfive.project.domain.aggregates.resource.Resource;
import switchfive.project.domain.shared.valueObjects.Email;
import switchfive.project.domain.shared.valueObjects.ProjectCode;
import switchfive.project.domain.shared.valueObjects.ResourceID;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IResourceRepository {
    ArrayList<Resource> getResourcesByProjectCode(String projectCode) throws ParseException;

    /**
     * Method to save a new resource of a project.
     */
    Resource save(Resource newResource) throws ParseException;

    /**
     * Method to get project manager of a project.
     */
    Optional<Resource> getProjectManager(ProjectCode code) throws ParseException;

    /**
     * Method to get resource by id.
     */
    Optional<Resource> getResourceByID(ResourceID resourceIDToFind) throws ParseException;

    List<Resource> getResourcesByEmail(Email email) throws ParseException;

    Optional<List<Resource>> findAllResourcesByProjectCode(ProjectCode projectCode) throws ParseException;
}

