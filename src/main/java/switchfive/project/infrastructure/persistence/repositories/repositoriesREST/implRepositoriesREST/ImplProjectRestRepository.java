package switchfive.project.infrastructure.persistence.repositories.repositoriesREST.implRepositoriesREST;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import switchfive.project.dataModel.dataREST.ProjectRest;
import switchfive.project.dataModel.dataREST.ProjectRestSimplified;
import switchfive.project.infrastructure.persistence.repositories.repositoriesREST.iRepositoriesREST.IProjectRestRepository;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ImplProjectRestRepository implements IProjectRestRepository {

    @Value("${externalservice.url}")
    String url;

    @Value("${externalservice.projects.url}")
    String projectsRelativeURL;

    private WebClient getWebClient() throws SSLException {
        SslContext context = SslContextBuilder.forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();

        HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(context));

        return WebClient.builder()
                .baseUrl(this.url)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", this.url))
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    /**
     * Looks for a specific projectCode in an external repository and returns a ProjectRest object.
     * The external url is defined in the application.properties file.
     * If an error or an exception is thrown, an empty Optional is returned.
     * @param projectCode as string
     * @return an Optional ProjectRest (data model object specific to the external repository)
     */
    public Optional<ProjectRest> findProjectByCode(String projectCode) throws SSLException {

        WebClient webClient = getWebClient();

        try {
            ProjectRest projectRest = webClient
                    .get()
                    .uri(this.projectsRelativeURL + projectCode)
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, error -> {
                        return Mono.empty();
                    })
                    .bodyToMono(ProjectRest.class)
                    .onErrorReturn(null)
                    .block();
            return Optional.ofNullable(projectRest);
        } catch (Exception e) {
            return Optional.empty();
        }

    }

    /**
     * Looks all Projects in an external repository and returns a List of ProjectRestSimplified objects.
     * The external url is defined in the application.properties file.
     * If an error or an exception is thrown an empty List  is returned.
     * @return an Optional ProjectRestSimplified (data model object specific to the external repository)
     */
    public List<ProjectRestSimplified> findAllProjects() throws SSLException {

        WebClient webClient = getWebClient();

        List<ProjectRestSimplified> projectRestList;

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            projectRestList = webClient
                    .get()
                    .uri(projectsRelativeURL)
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, error -> {
                        return Mono.empty();
                    })
                    .bodyToMono(JsonNode.class)
                    .onErrorReturn(null)
                    .map(s -> s.get("internalProjects").get("content"))
                    .map(s -> {
                        try {
                            return mapper.readValue(s.traverse(), new TypeReference<List<ProjectRestSimplified>>() {
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                            return new ArrayList<ProjectRestSimplified>();
                        }
                    })
                    .block();
        } catch (Exception e) {
            projectRestList = new ArrayList<>();
        }

        return projectRestList;
    }
}
