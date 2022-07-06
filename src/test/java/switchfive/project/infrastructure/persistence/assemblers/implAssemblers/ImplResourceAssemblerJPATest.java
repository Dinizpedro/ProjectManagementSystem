package switchfive.project.infrastructure.persistence.assemblers.implAssemblers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchfive.project.assemblers.assemblersJPA.implAssemblersJPA.ImplResourceAssemblerJPA;
import switchfive.project.dataModel.dataJPA.ResourceJPA;
import switchfive.project.domain.aggregates.resource.Resource;
import switchfive.project.domain.factories.iFactories.IResourceFactory;
import switchfive.project.domain.shared.valueObjects.*;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ImplResourceAssemblerJPATest {

    @Mock
    IResourceFactory iResourceFactory;

    @InjectMocks
    private ImplResourceAssemblerJPA implResourceAssemblerJPA;

    @Test
    void toDomain() throws ParseException {
        //Arrange
        ResourceJPA resourceJPA = new ResourceJPA();

        resourceJPA.setResourceID("123e4567-e89b-12d3-a456-426614174000");
        ResourceID expectedResourceID = ResourceID.createResourceID("123e4567-e89b-12d3-a456-426614174000");

        resourceJPA.setUserID("benfica@tetra.pt");
        Email expectedEmail = Email.create("benfica@tetra.pt");

        resourceJPA.setProjectCode("ISEP1");
        ProjectCode expectedCode = ProjectCode.create("ISEP1");

        resourceJPA.setStartDate("27/08/2025");
        resourceJPA.setEndDate("27/05/2026");

        Time expectedDates = Time.create("27/08/2025", "27/05/2026");

        resourceJPA.setCostPerHour(50.0);
        ResourceCostPerHour expectedCostPerHour = ResourceCostPerHour.create(50.0);

        resourceJPA.setPercentageOfAllocation(50.0);
        ResourcePercentageOfAllocation expectedAllocation = ResourcePercentageOfAllocation.create(50.0);

        resourceJPA.setRole("PRODUCT_OWNER");
        Role expectedRole = Role.valueOf("PRODUCT_OWNER");

        Resource expected = new Resource(expectedResourceID, expectedEmail, expectedCode, expectedDates, expectedCostPerHour, expectedAllocation, expectedRole);

        when(iResourceFactory.createResource(expectedResourceID, expectedEmail, expectedCode, expectedDates, expectedCostPerHour, expectedAllocation, expectedRole)).thenReturn(expected);

        //Act

        Resource result = implResourceAssemblerJPA.toDomain(resourceJPA);

        // Assert

        assertEquals(expected, result);
    }

    @Test
    void toData() throws ParseException {

        ResourceID expectedResourceID = ResourceID.createResourceID();
        Email expectedEmail = Email.create("benfica@tetra.pt");
        ProjectCode expectedCode = ProjectCode.create("ISEP1");
        Time expectedDates = Time.create("27/08/2025", "27/05/2026");
        ResourceCostPerHour expectedCostPerHour = ResourceCostPerHour.create(50.0);
        ResourcePercentageOfAllocation expectedAllocation = ResourcePercentageOfAllocation.create(50.0);
        Role expectedRole = Role.valueOf("PRODUCT_OWNER");

        Resource resource = new Resource(expectedResourceID, expectedEmail, expectedCode, expectedDates, expectedCostPerHour, expectedAllocation, expectedRole);
        ResourceJPA result = implResourceAssemblerJPA.toData(resource);

        assertEquals(resource.getResourceID(), result.getResourceID());
        assertEquals("benfica@tetra.pt", result.getUserID());
        assertEquals("ISEP1", result.getProjectCode());
        assertEquals("27/08/2025", result.getStartDate());
        assertEquals("27/05/2026", result.getEndDate());
        assertEquals(50.0, result.getCostPerHour());
        assertEquals(50.0, result.getPercentageOfAllocation());
        assertEquals("PRODUCT_OWNER", result.getRole());

    }
}
