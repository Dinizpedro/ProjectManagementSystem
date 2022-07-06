package switchfive.project.infrastructure.persistence.assemblers.assemblersREST.implAssemblersREST;

import org.junit.jupiter.api.Test;
import switchfive.project.assemblers.assemblersREST.implAssemblersREST.ImplRestProjectAssembler;
import switchfive.project.dataModel.dataREST.ProjectRest;
import switchfive.project.dataModel.dataREST.ProjectRestSimplified;
import switchfive.project.interfaceAdapters.domainWS.ProjectWS;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class ImplRestProjectAssemblerTest {

    @Test
    void toDomain_Succesfully() throws ParseException {

        // Arrange

        ImplRestProjectAssembler assembler = new ImplRestProjectAssembler();

        ProjectRest projectRest = new ProjectRest();
        projectRest.setStatus("a");
        projectRest.setBudget(1.0);
        projectRest.setCode("a");
        projectRest.setCustomer("a");
        projectRest.setDescription("a");
        projectRest.setProjectName("a");
        projectRest.setTypo("a");
        projectRest.setBusinessSector("a");
        projectRest.setNumberOfSprints(1);
        projectRest.setSprintDuration(1);
        projectRest.setStartDate("26-04-2023");
        projectRest.setEndDate("26-04-2023");

        ProjectWS projectWS = new ProjectWS("a","a","a","a","a","a","a","26-04-2023","26-04-2023",1,1,1);

        // Act


        // Assert
        assertEquals(projectWS,assembler.toWebServiceDomain(projectRest));


    }

    @Test
    void formatCalendarEU() throws ParseException {

        // Arrange
        ImplRestProjectAssembler assembler = new ImplRestProjectAssembler();
        String data = "26-04-2023";

        // Act

        // Assert
        assembler.formatCalendarEU(data);
        assertEquals("14/10/0031",assembler.formatCalendarEU(data));

    }

    @Test
    void formatStatus() {

        // Arrange
        ImplRestProjectAssembler assembler = new ImplRestProjectAssembler();

        ProjectRest projectRest = new ProjectRest();

        projectRest.setStatus("ESTADO");

        // Act

        // Assert
        assertEquals("Estado",assembler.formatStatus(projectRest));

    }

    @Test
    void toWebServiceDomain() {

        // Arrange
        ImplRestProjectAssembler assembler = new ImplRestProjectAssembler();

        ProjectRestSimplified projectSimplified = new ProjectRestSimplified();
        projectSimplified.setStatus("a");
        projectSimplified.setProjectName("a");
        projectSimplified.setCode("a");
        projectSimplified.setDescription("a");
        projectSimplified.setStartDate("26-04-2023");

        ProjectWS projectWS = new ProjectWS("a",null,null,"a","a","a",null,"26-04-2023",null,0,0,0);


        // Act

        // Assert
        assertEquals(projectWS,assembler.toWebServiceDomain(projectSimplified));

    }

}