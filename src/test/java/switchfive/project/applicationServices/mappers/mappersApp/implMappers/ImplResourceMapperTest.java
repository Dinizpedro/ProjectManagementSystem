package switchfive.project.applicationServices.mappers.mappersApp.implMappers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switchfive.project.dtos.AllocatedProjectDTO;
import switchfive.project.dtos.ResourceDTO;
import switchfive.project.dtos.TimeDTO;
import switchfive.project.domain.aggregates.resource.Resource;
import switchfive.project.mappers.mappersApp.implMappers.ImplResourceMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class ImplResourceMapperTest {

    @Autowired
    ImplResourceMapper resourceMapper;

    @Test
    void toDto() {

        //Arrange
        Resource resource = mock(Resource.class);
        when(resource.getResourceID()).thenReturn("resID");
        when(resource.getUserEmail()).thenReturn("as@mymail.com");
        when(resource.getProjectCode()).thenReturn("A0001");
        when(resource.getStartDate()).thenReturn("27/05/2023");
        when(resource.getEndDate()).thenReturn("27/06/2023");
        TimeDTO dates = new TimeDTO("27/05/2023", "27/06/2023");
        when(resource.getCostPerHour()).thenReturn(50.0);
        when(resource.getAllocation()).thenReturn(100.0);
        when(resource.getRole()).thenReturn("dev");

        ResourceDTO resourceDTO = new ResourceDTO("resID","as@mymail.com","A0001",
                dates,50.0,100.0,"dev");

        //Act
        ResourceDTO expected = resourceDTO;
        ResourceDTO actual = resourceMapper.toDto(resource);

        //Assert
        assertEquals(expected,actual);

    }

    @Test
    void toAllocatedProjectDTO() {

        //Arrange

        Resource resource = mock(Resource.class);
        String projectName = "name";
        String role = "dev";
        String projectCode = "Isep2";

        when(resource.getRole()).thenReturn(role);
        when(resource.getProjectCode()).thenReturn(projectCode);

        AllocatedProjectDTO allocatedProjectDTO = new AllocatedProjectDTO();
        allocatedProjectDTO.setProjectName(projectName);
        allocatedProjectDTO.setProjectCode(projectCode);
        allocatedProjectDTO.setRole(role);

        //Act
        AllocatedProjectDTO expected = allocatedProjectDTO;
        AllocatedProjectDTO actual = resourceMapper.toAllocatedProjectDTO(resource, projectName);

        //Assert
        assertEquals(expected, actual);

    }
}