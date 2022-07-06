package switchfive.project.infrastructure.persistence.assemblers.assemblersREST.implAssemblersREST;

import org.junit.jupiter.api.Test;
import switchfive.project.assemblers.assemblersREST.implAssemblersREST.ImplRestProfileAssembler;
import switchfive.project.dataModel.dataREST.ProfileRest;
import switchfive.project.interfaceAdapters.domainWS.ProfileWS;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ImplRestProfileAssemblerTest {

    @Test
    void toDomain_Succesfully() {

        // Arrange
        ImplRestProfileAssembler assembler = new ImplRestProfileAssembler();

        ProfileRest profileRest = new ProfileRest();
        profileRest.setUserProfileName("Teste");

        ProfileWS profileWS = new ProfileWS("Teste");

        // Act

        // Assert
        assertEquals(profileWS,assembler.toDomain(profileRest));
        assertEquals(profileRest.getUserProfileName(),"Teste");
        assertEquals(profileWS.hashCode(),profileWS.hashCode());

    }

    @Test
    void toDomain_SuccesfullyList() {

        // Arrange
        ImplRestProfileAssembler assembler = new ImplRestProfileAssembler();

        ProfileRest profileRest = new ProfileRest();
        List<ProfileRest> profileRestList = new ArrayList<>();
        profileRest.setUserProfileName("Teste");
        profileRestList.add(profileRest);

        ProfileWS profileWS = new ProfileWS("Teste");
        List<ProfileWS> profileWSList = new ArrayList<>();
        profileWSList.add(profileWS);

        // Act

        // Assert
        assertEquals(profileWSList,assembler.toDomain(profileRestList));

    }

}