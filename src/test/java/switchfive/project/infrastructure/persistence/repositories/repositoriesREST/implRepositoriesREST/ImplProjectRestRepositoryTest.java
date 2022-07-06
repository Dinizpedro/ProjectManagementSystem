package switchfive.project.infrastructure.persistence.repositories.repositoriesREST.implRepositoriesREST;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import switchfive.project.dataModel.dataREST.ProjectRest;
import switchfive.project.dataModel.dataREST.ProjectRestSimplified;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootTest
class ImplProjectRestRepositoryTest {
    public static MockWebServer mockBackEnd;
    @InjectMocks ImplProjectRestRepository restRepository;

    /* The setUp and tearDown method takes care of creating and shutting down the MockWebServer.*/

    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    /*The next step is to map the port of the actual REST service call to the MockWebServer's port:*/

    @BeforeEach
    void initialize() {
        this.restRepository.url = String.format("http://localhost:%s", mockBackEnd.getPort());
        this.restRepository.projectsRelativeURL = "/projects/";
    }

    @Test
    void getProjectByProjectCodeSuccess() throws JsonProcessingException, SSLException {
        // Arrange
        ObjectMapper objectMapper = new ObjectMapper();

        ProjectRest projectRest = new ProjectRest();
        projectRest.setCode("abcde");
        projectRest.setProjectName("SWitCH");
        projectRest.setDescription("Re-qualification");
        projectRest.setCustomer("PortoTechHub");
        projectRest.setBusinessSector("Educational");
        projectRest.setTypo("Time and Materials");
        projectRest.setNumberOfSprints(1);
        projectRest.setBudget(0.0);
        projectRest.setStatus("Planned");
        projectRest.setStartDate("2022-06-30");
        projectRest.setEndDate("2022-08-30");
        projectRest.setSprintDuration(5);

        /*Stubbing a Response*/
        /* Use MockWebServer's handy enqueue method to queue a test response on the webserver*/
        mockBackEnd.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(projectRest))
                .addHeader("Content-Type", "application/json"));

        // Act
        Optional<ProjectRest> actual = this.restRepository.findProjectByCode("abcde");

        // Assert
        Assertions.assertEquals(Optional.of(projectRest), actual);
    }

    @Test
    void getProjectByProjectCodeFails() throws JsonProcessingException, SSLException {
        // Arrange

        /*Stubbing a Response*/
        /* Use MockWebServer's handy enqueue method to queue a test response on the webserver*/
        mockBackEnd.enqueue(new MockResponse());

        // Act
        Optional<ProjectRest> actual = this.restRepository.findProjectByCode("fails");

        // Assert
        Assertions.assertEquals(Optional.empty(), actual);
    }

    @Disabled
    @Test
    void getAllProjectsSuccess() throws JsonProcessingException, SSLException {
        // Arrange
        ObjectMapper objectMapper = new ObjectMapper();

        ProjectRestSimplified restSimplified = new ProjectRestSimplified();
        restSimplified.setCode("abcde");
        restSimplified.setProjectName("SWitCH");
        restSimplified.setDescription("Re-qualification");
        restSimplified.setStatus("Planned");
        restSimplified.setStartDate("2022-06-30");

        List<ProjectRestSimplified> projectRestList = new ArrayList<>();
        projectRestList.add(restSimplified);

        /*Stubbing a Response*/
        /* Use MockWebServer's handy enqueue method to queue a test response on the webserver*/
        mockBackEnd.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(restSimplified))
                .addHeader("Content-Type", "application/json"));

        // Act
        List<ProjectRestSimplified> actual = this.restRepository.findAllProjects();

        // Assert
        Assertions.assertEquals(projectRestList.get(0), actual.get(0));
    }

}
