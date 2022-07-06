package switchfive.project.assemblers.assemblersJPA.implAssemblersJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchfive.project.assemblers.assemblersJPA.iAssemblersJPA.IResourceAssemblerJPA;
import switchfive.project.dataModel.dataJPA.ResourceJPA;
import switchfive.project.domain.aggregates.resource.Resource;
import switchfive.project.domain.factories.iFactories.IResourceFactory;
import switchfive.project.domain.shared.valueObjects.*;

import java.text.ParseException;

@Service
public class ImplResourceAssemblerJPA implements IResourceAssemblerJPA {

    private final IResourceFactory resourceFactory;

    @Autowired
    public ImplResourceAssemblerJPA(IResourceFactory resourceFactory) {
        this.resourceFactory = resourceFactory;
    }

    public Resource toDomain(ResourceJPA resourceJPA) throws ParseException {
        String resourceIDJPA = resourceJPA.getResourceID();
        String userIDJPA = resourceJPA.getUserID();
        String projectCodeJPA = resourceJPA.getProjectCode();
        String startDateJPA = resourceJPA.getStartDate();
        String endDateJPA = resourceJPA.getEndDate();
        Double costPerHourJPA = resourceJPA.getCostPerHour();
        Double allocationJPA = resourceJPA.getPercentageOfAllocation();
        String roleJPA = resourceJPA.getRole();

        ResourceID resourceID = ResourceID.createResourceID(resourceIDJPA);
        Email userID = Email.create(userIDJPA);
        ProjectCode projectCode = ProjectCode.create(projectCodeJPA);
        Time dates = Time.create(startDateJPA, endDateJPA);
        ResourceCostPerHour costPerHour = ResourceCostPerHour.create(costPerHourJPA);
        ResourcePercentageOfAllocation allocation = ResourcePercentageOfAllocation.create(allocationJPA);
        Role role = Role.valueOf(roleJPA);

        return resourceFactory.createResource(resourceID, userID, projectCode, dates
                , costPerHour, allocation, role);

    }

    public ResourceJPA toData(Resource resource) {

        ResourceJPA resourceJPA = new ResourceJPA();

        resourceJPA.setResourceID(resource.getResourceID());
        resourceJPA.setUserID(resource.getUserEmail());
        resourceJPA.setProjectCode(resource.getProjectCode());
        resourceJPA.setStartDate(resource.getStartDate());
        resourceJPA.setEndDate(resource.getEndDate());
        resourceJPA.setCostPerHour(resource.getCostPerHour());
        resourceJPA.setPercentageOfAllocation(resource.getAllocation());
        resourceJPA.setRole(resource.getRole());
        resourceJPA.setEmail(resource.getUserEmail());

        return resourceJPA;
    }
}
