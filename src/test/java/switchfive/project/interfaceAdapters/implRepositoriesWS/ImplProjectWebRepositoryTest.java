package switchfive.project.interfaceAdapters.implRepositoriesWS;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import switchfive.project.assemblers.assemblersREST.iAssemblersREST.IRestProjectAssembler;
import switchfive.project.dataModel.dataREST.ProjectRest;
import switchfive.project.dataModel.dataREST.ProjectRestSimplified;
import switchfive.project.infrastructure.persistence.repositories.repositoriesREST.iRepositoriesREST.IProjectRestRepository;
import switchfive.project.interfaceAdapters.domainWS.ProjectWS;

import javax.net.ssl.SSLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class ImplProjectWebRepositoryTest {

    @Mock IProjectRestRepository projectRestRepositoryMock;

    @Mock IRestProjectAssembler restProjectAssemblerMock;

    @Mock ProjectRest projectRestMock;

    @Mock ProjectRestSimplified restSimplifiedMock;

    @Mock ProjectWS projectWSMock;

    @InjectMocks ImplProjectWebRepository projectWebRepository;

    @Test
    void findProjectSuccessful() throws SSLException, ParseException {
        //Arrange
        when(projectRestRepositoryMock.findProjectByCode(anyString())).thenReturn(Optional.of(projectRestMock));
        when(projectRestMock.getCode()).thenReturn("code");
        when(restProjectAssemblerMock.toWebServiceDomain(any(ProjectRest.class))).thenReturn(projectWSMock);

        //Act
        Optional<ProjectWS> expected = projectWebRepository.findByCode("any");

        //Act
        assertEquals(expected, Optional.of(projectWSMock));
    }

    @Test
    void findProjectThatNotExistsSuccessful() throws SSLException, ParseException {
        //Arrange
        when(projectRestRepositoryMock.findProjectByCode(anyString())).thenReturn(Optional.empty());

        //Act
        Optional<ProjectWS> expected = projectWebRepository.findByCode("any");

        //Act
        assertEquals(expected, Optional.empty());
    }

    @Test
    void findAllProjectSuccessful() throws SSLException, ParseException {
        //Arrange
        List<ProjectRestSimplified> actualList = new ArrayList<>();
        actualList.add(restSimplifiedMock);

        when(projectRestRepositoryMock.findAllProjects()).thenReturn(actualList);
        when(restProjectAssemblerMock.toWebServiceDomain(any(ProjectRestSimplified.class))).thenReturn(projectWSMock);

        //Act
        List<ProjectWS> expected = projectWebRepository.findAll();

        List<ProjectWS> actual = new ArrayList<>();
        actual.add(projectWSMock);

        //Act
        assertEquals(expected, actual);
    }

    @Test
    void findAllProjectEmpty() throws SSLException, ParseException {
        //Arrange
        List<ProjectRestSimplified> actualList = new ArrayList<>();

        when(projectRestRepositoryMock.findAllProjects()).thenReturn(actualList);
        when(restProjectAssemblerMock.toWebServiceDomain(any(ProjectRestSimplified.class))).thenReturn(projectWSMock);

        //Act
        List<ProjectWS> expected = projectWebRepository.findAll();

        List<ProjectWS> actual = new ArrayList<>();

        //Act
        assertEquals(expected, actual);
    }

}
