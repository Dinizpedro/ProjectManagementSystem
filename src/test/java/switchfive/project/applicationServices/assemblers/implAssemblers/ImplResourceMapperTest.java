package switchfive.project.applicationServices.assemblers.implAssemblers;

import org.junit.jupiter.api.Test;
import switchfive.project.dtos.ResourceDTO;
import switchfive.project.dtos.TimeDTO;
import switchfive.project.mappers.mappersApp.implMappers.ImplResourceMapper;
import switchfive.project.domain.aggregates.resource.Resource;
import switchfive.project.domain.shared.valueObjects.*;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ImplResourceMapperTest {

    @Test
    void toDto() throws ParseException {

        //Arrange
        ImplResourceMapper assembler = new ImplResourceMapper();
        ResourceID resourceID = ResourceID.createResourceID();
        Email userID = Email.create("benfica@campeao.pt");
        ProjectCode code = ProjectCode.create("ISEP1");
        Time time = Time.create("27/05/2025", "27/04/2026");
        TimeDTO timeDTO = new TimeDTO("27/05/2025", "27/04/2026");
        ResourceCostPerHour costPerHour = ResourceCostPerHour.create(50);
        ResourcePercentageOfAllocation allocation = ResourcePercentageOfAllocation.create(50);
        Role role = Role.TEAM_MEMBER;

        Resource resource = new Resource(resourceID, userID, code, time, costPerHour, allocation, role);


        ResourceDTO expected = new ResourceDTO(resourceID.toString(),
                userID.toString(), code.toString(), timeDTO, costPerHour.getCostPerHour(),
                allocation.getAllocation(), role.toString());

        //Act
        ResourceDTO actual = assembler.toDto(resource);

        //Assert
        assertEquals(expected, actual);
    }
}
