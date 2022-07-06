package switchfive.project.interfaceAdapters.domainWS;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectWSTest {

    @Test
    void ProjectWS_GetterAndSetter() {

        // Arrange
        ProjectWS project = new ProjectWS("teste","teste","teste","teste","teste","teste","teste","teste","teste",1,1,1);
        ProjectWS projectOne = ProjectWS.create("teste","teste","teste","teste","teste","teste","teste","teste","teste",1,1,1);

        project.setProjectBudget(1);
        project.setProjectDescription("teste");
        projectOne.setProjectCode("teste");
        projectOne.setProjectName("teste");
        projectOne.setEndDate("teste");
        projectOne.setStartDate("teste");
        projectOne.setProjectNumberOfPlannedSprints(1);
        project.setTypologyDescription("teste");
        project.setProjectSprintDuration(1);
        project.setStatus("teste");
        project.setCustomerName("teste");
        project.setProjectBusinessSector("teste");


        // Act

        // Arrange
        assertEquals(project,projectOne);
        assertEquals(project.getProjectBudget(),projectOne.getProjectBudget());
        assertEquals(project.getProjectCode(),projectOne.getProjectCode());
        assertEquals(project.getStatus(),projectOne.getStatus());
        assertEquals(project.getProjectDescription(),projectOne.getProjectDescription());
        assertEquals(project.getTypologyDescription(),projectOne.getTypologyDescription());
        assertEquals(project.getCustomerName(),projectOne.getCustomerName());
        assertEquals(project.getProjectName(),projectOne.getProjectName());
        assertEquals(project.getStartDate(),projectOne.getStartDate());
        assertEquals(project.getProjectBusinessSector(),projectOne.getProjectBusinessSector());
        assertEquals(project.getEndDate(),projectOne.getEndDate());
        assertEquals(project.getProjectNumberOfPlannedSprints(),projectOne.getProjectNumberOfPlannedSprints());
        assertEquals(project.getProjectBudget(),projectOne.getProjectBudget());
        assertEquals(project.getProjectSprintDuration(),projectOne.getProjectSprintDuration());
        assertEquals(project.hashCode(),projectOne.hashCode());

    }

}