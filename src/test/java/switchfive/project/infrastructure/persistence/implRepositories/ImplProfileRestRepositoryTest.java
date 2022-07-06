package switchfive.project.infrastructure.persistence.implRepositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import switchfive.project.dataModel.dataREST.ProfileRest;
import switchfive.project.infrastructure.persistence.repositories.repositoriesREST.implRepositoriesREST.ImplProfileRestRepository;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class ImplProfileRestRepositoryTest {

    public static MockWebServer mockBackEnd;

    /* The setUp and tearDown method takes care of creating and shutting down the MockWebServer.*/

    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
    }

    @InjectMocks
    ImplProfileRestRepository implProfileRestRepository;

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @BeforeEach
    void initialize() {
        this.implProfileRestRepository.setUrl(String.format("http://localhost:%s", mockBackEnd.getPort()));
        this.implProfileRestRepository.setProfilesRelativeURL("/profiles/");
    }

    @Test
    void findProfileInExternalServiceByProfileDescriptionSuccessfull() throws SSLException, JsonProcessingException {
        // Arrange
        ObjectMapper objectMapper = new ObjectMapper();

        ProfileRest profileRest = new ProfileRest();
        profileRest.setUserProfileName("Analyst");

        /*Stubbing a Response*/
        /* Use MockWebServer's handy enqueue method to queue a test response on the webserver*/
        mockBackEnd.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(profileRest))
                .addHeader("Content-Type", "application/json"));

        Optional<ProfileRest> expected = Optional.of(profileRest);

        // Act
        Optional<ProfileRest> actual = this.implProfileRestRepository
                .findProfileInExternalServiceByProfileDescription("Analyst");

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findProfileInExternalServiceByProfileDescriptionUnSuccessfull() throws SSLException, JsonProcessingException {
        // Arrange
        ObjectMapper objectMapper = new ObjectMapper();

        /*Stubbing a Response*/
        /* Use MockWebServer's handy enqueue method to queue a test response on the webserver*/
        mockBackEnd.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(null))
                .addHeader("Content-Type", "application/json"));

        Optional<ProfileRest> expected = Optional.ofNullable(null);

        // Act
        Optional<ProfileRest> actual = this.implProfileRestRepository
                .findProfileInExternalServiceByProfileDescription("Analyst");

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findAllProfilesInExternalServiceSuccessfull() throws SSLException {
        // Arrange
        List<ProfileRest> profileRestList = new ArrayList<>();
        ProfileRest profileRest = new ProfileRest();
        profileRest.setUserProfileName("Analyst");

        profileRestList.add(profileRest);

        /*Stubbing a Response*/
        /* Use MockWebServer's handy enqueue method to queue a test response on the webserver*/
        String jsonResponse = "{\n" +
                "    \"internalUserProfiles\": {\n" +
                "        \"content\": [\n" +
                "            {\n" +
                "                \"userProfileName\": \"Analyst\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";
        mockBackEnd.enqueue(new MockResponse()
                .setBody(jsonResponse)
                .addHeader("Content-Type", "application/json"));

        // Act
        List<ProfileRest> actual = this.implProfileRestRepository
                .findAllProfilesInExternalService();

        // Assert
        Assertions.assertEquals(profileRestList, actual);

    }

    @Test
    void findAllProfilesInExternalServiceUnSuccessfull() throws SSLException {
        // Arrange
        List<ProfileRest> profileRestList = new ArrayList<>();

        /*Stubbing a Response*/
        /* Use MockWebServer's handy enqueue method to queue a test response on the webserver*/
        String jsonResponse = "";
        mockBackEnd.enqueue(new MockResponse()
                .setBody(jsonResponse)
                .addHeader("Content-Type", "application/json"));

        // Act
        List<ProfileRest> actual = this.implProfileRestRepository
                .findAllProfilesInExternalService();

        // Assert
        Assertions.assertEquals(profileRestList, actual);

    }

}
