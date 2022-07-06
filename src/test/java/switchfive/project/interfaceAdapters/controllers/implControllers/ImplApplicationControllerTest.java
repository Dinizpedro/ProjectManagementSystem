package switchfive.project.interfaceAdapters.controllers.implControllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import switchfive.project.dtos.RepresentationModelDTO;

import javax.net.ssl.SSLException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@ExtendWith(MockitoExtension.class)
class ImplApplicationControllerTest {

    @InjectMocks
    ImplApplicationController implApplicationController;


    @Test
    void applicationOptionsMapping() throws ParseException, NoSuchAlgorithmException, SSLException {

        // ------ ARRANGE ------------
        RepresentationModel<RepresentationModelDTO> expectedLinks
                = new RepresentationModel<>();

        Link projectsLink = linkTo(methodOn(ImplProjectController.class).getAllProjects())
                .withRel("projects").withType("GET");
        expectedLinks.add(projectsLink);

        Link customersLink = linkTo(methodOn(ImplCustomerController.class).getAllCustomers())
                .withRel("customers").withType("GET");
        expectedLinks.add(customersLink);

        Link profilesLink = linkTo(methodOn(ImplProfileController.class).getProfiles())
                .withRel("profiles").withType("GET");
        expectedLinks.add(profilesLink);

        Link typologyLink = linkTo(methodOn(ImplTypologyController.class).getTypologies())
                .withRel("typologies").withType("GET");
        expectedLinks.add(typologyLink);

        Link userLink = linkTo(methodOn(ImplUserController.class).createUser(null))
                .withRel("users").withType("POST");
        expectedLinks.add(userLink);
        Link userEmailLink = linkTo(methodOn(ImplUserController.class).getUsersByEmail(""))
                .withRel("usersEmail").withType("GET");
        expectedLinks.add(userEmailLink);

        Link allUsers = linkTo(methodOn(ImplUserController.class).getAllUsers())
                .withRel("usersAll").withType("GET");
        expectedLinks.add(allUsers);

        Link userProfileLink = linkTo(methodOn(ImplUserController.class).getUsersByProfile(""))
                .withRel("usersProfile").withType("GET");
        expectedLinks.add(userProfileLink);


        ResponseEntity<Object> expected = new ResponseEntity<>(expectedLinks,
                HttpStatus.OK);


        // ------ ACT ----------------
        ResponseEntity<Object> actual = implApplicationController
                .applicationOptionsMapping();

        // ------ ASSERT -------------
        assertEquals(expected, actual);

    }
}
