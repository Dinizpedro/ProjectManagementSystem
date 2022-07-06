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
import switchfive.project.infrastructure.persistence.repositories.repositoriesREST.iRepositoriesREST.IProfileRestRepository;
import switchfive.project.dataModel.dataREST.ProfileRest;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ImplProfileRestRepository implements IProfileRestRepository {

    @Value("${externalservice.url}")
    private String url;

    @Value("${externalservice.profiles.url}")
    private String profilesRelativeURL;

    /**
     * Looks for a specific profile description in an external repository and returns a ProfileREST object.
     * The external url is defined in the application.properties file. If an error or an exception is thrown
     * an empty Optional<ProfileRest> is returned.
     *
     * @param profileDescription - String
     * @return an Optional ProfileREST (data model object specific to the external repository)
     */
    @Override
    public Optional<ProfileRest> findProfileInExternalServiceByProfileDescription(String profileDescription) throws SSLException {

        SslContext context = SslContextBuilder.forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();

        HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(context));

        WebClient webClient = WebClient.builder()
                .baseUrl(url)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", url))
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();

        ProfileRest profileRest;
        try {
            profileRest = webClient
                    .get()
                    .uri(profilesRelativeURL + profileDescription)
                    .retrieve()

                    .onStatus(HttpStatus::is4xxClientError, error -> {
                        return Mono.empty();
                    })

                    .bodyToMono(ProfileRest.class)

                    .onErrorReturn(null)

                    .block();
        } catch (Exception e) {
            profileRest = null;}

        return Optional.ofNullable(profileRest);
    }

    /**
     * Looks all Profiles in an external repository and returns a List of ProfileREST objects.
     * The external url is defined in the application.properties file. If an error or an exception is thrown
     * an empty List<ProfileRest> is returned.
     *
     * @return an Optional ProfileREST (data model object specific to the external repository)
     */
    @Override
    public List<ProfileRest> findAllProfilesInExternalService() throws SSLException {
        SslContext context = SslContextBuilder.forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();

        HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(context));

        WebClient webClient = WebClient.builder()
                .baseUrl(url)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", url))
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();

        List<ProfileRest> profileRest;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


            profileRest = webClient
                    .get()
                    .uri(profilesRelativeURL)
                    .retrieve()

                    .onStatus(HttpStatus::is4xxClientError, error -> {
                        return Mono.empty();
                    })

                    .bodyToMono(JsonNode.class)

                    .onErrorReturn(null)
                    .map(s-> s.get("internalUserProfiles").get("content"))
                    .map(s->{
                        try {
                            return mapper.readValue(s.traverse(), new TypeReference<List<ProfileRest>>() {} );
                        } catch (IOException e) {
                            e.printStackTrace();
                            return new ArrayList<ProfileRest>();
                        }
                    })
                    .block();


        } catch (Exception e) {
            profileRest = new ArrayList<>();}

        // In case of mapping error
        if (profileRest == null) {
            profileRest = new ArrayList<>();
        }
        return profileRest;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setProfilesRelativeURL(String profilesRelativeURL) {
        this.profilesRelativeURL = profilesRelativeURL;
    }
}
