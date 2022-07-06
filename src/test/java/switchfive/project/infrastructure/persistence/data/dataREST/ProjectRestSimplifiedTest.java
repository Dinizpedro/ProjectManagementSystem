package switchfive.project.infrastructure.persistence.data.dataREST;

import org.junit.jupiter.api.Test;
import switchfive.project.dataModel.dataREST.ProjectRestSimplified;

import static org.junit.jupiter.api.Assertions.*;

class ProjectRestSimplifiedTest {

    @Test
    void test() {
        //Arrange
        ProjectRestSimplified project = new ProjectRestSimplified();
        project.setProjectName("a");
        project.setCode("a");
        project.setDescription("a");
        project.setStatus("a");
        project.setStartDate("a");

        // Act and Assert
        assertEquals(project.getProjectName(),"a");
        assertEquals(project.getCode(),"a");
        assertEquals(project.getDescription(),"a");
        assertEquals(project.getStatus(),"a");
        assertEquals(project.getStartDate(),"a");
    }

}