package switchfive.project.interfaceAdapters.controllers.implControllers;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import switchfive.project.interfaceAdapters.controllers.iControllers.IApplicationController;
import switchfive.project.dtos.RepresentationModelDTO;

import javax.net.ssl.SSLException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
public class ImplApplicationController implements IApplicationController {

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    public ResponseEntity<Object> applicationOptionsMapping() throws ParseException, NoSuchAlgorithmException, SSLException {
        RepresentationModel<RepresentationModelDTO> collectionLinks = new RepresentationModel<>();

        Link projectsLink = linkTo(methodOn(ImplProjectController.class).getAllProjects())
                .withRel("projects").withType("GET");
        collectionLinks.add(projectsLink);

        Link customersLink = linkTo(methodOn(ImplCustomerController.class).getAllCustomers())
                .withRel("customers").withType("GET");
        collectionLinks.add(customersLink);

        Link profilesLink = linkTo(methodOn(ImplProfileController.class).getProfiles())
                .withRel("profiles").withType("GET");
        collectionLinks.add(profilesLink);

        Link typologyLink = linkTo(methodOn(ImplTypologyController.class).getTypologies())
                .withRel("typologies").withType("GET");
        collectionLinks.add(typologyLink);

        Link userLink = linkTo(methodOn(ImplUserController.class).createUser(null))
                .withRel("users").withType("POST");
        collectionLinks.add(userLink);

        Link userEmailLink = linkTo(methodOn(ImplUserController.class).getUsersByEmail(""))
                .withRel("usersEmail").withType("GET");
        collectionLinks.add(userEmailLink);

        Link allUsers = linkTo(methodOn(ImplUserController.class).getAllUsers())
                .withRel("usersAll").withType("GET");
        collectionLinks.add(allUsers);

        Link userProfileLink = linkTo(methodOn(ImplUserController.class).getUsersByProfile(""))
                .withRel("usersProfile").withType("GET");
        collectionLinks.add(userProfileLink);

        return new ResponseEntity<>(collectionLinks, HttpStatus.OK);
    }
}
