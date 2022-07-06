package switchfive.project.infrastructure.persistence.data.dataREST;

import org.junit.jupiter.api.Test;
import switchfive.project.dataModel.dataREST.ProjectRest;

import static org.junit.jupiter.api.Assertions.*;

class ProjectRestTest {
    @Test
    void ProjectRest() {
        // Arrange
        ProjectRest project = new ProjectRest();
        ProjectRest projectOne = new ProjectRest();

        // Act

        // Assert
        assertEquals(project,projectOne);
        assertEquals(project.hashCode(),project.hashCode());
    }

}