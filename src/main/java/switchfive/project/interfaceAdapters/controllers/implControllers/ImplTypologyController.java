package switchfive.project.interfaceAdapters.controllers.implControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switchfive.project.interfaceAdapters.controllers.iControllers.ITypologyController;
import switchfive.project.dtos.TypologyDTO;
import switchfive.project.applicationServices.appServices.iappServices.IAppTypologyService;
import switchfive.project.applicationServices.assemblers.iAssemblers.ITypologyAssembler;
import switchfive.project.domain.aggregates.typology.Typology;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin
public class ImplTypologyController implements ITypologyController {

    private final String WRONG_INFORMATION = "Wrong information";
    private final String TYPOLOGY_ALREADY_EXISTS = "Typology already exists";
    private final String TYPOLOGY_DOES_NOT_EXIST = "Typology doesn't exist";
    private final IAppTypologyService typologyService;
    private final ITypologyAssembler typologyAssembler;


    @Autowired
    public ImplTypologyController(IAppTypologyService iAppServiceTypology,
                                  ITypologyAssembler typologyAssembler) {
        this.typologyService = iAppServiceTypology;
        this.typologyAssembler = typologyAssembler;
    }

    @GetMapping("typologies/{typologyDescription}")
    public ResponseEntity<Object> getTypology(@PathVariable String typologyDescription) {

        try {
            Optional<Typology> typology = this.typologyService.findTypologyByDescription(typologyDescription);

            if (typology.isEmpty()) {
                return new ResponseEntity<>(TYPOLOGY_DOES_NOT_EXIST, HttpStatus.NOT_FOUND);
            }

            TypologyDTO typologyDTO = this.typologyAssembler.toDTO(typology.get());

            Link link = linkTo(methodOn(ImplTypologyController.class).getTypology(typologyDescription)).withSelfRel();
            typologyDTO.add(link);

            return new ResponseEntity<>(typologyDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(WRONG_INFORMATION, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/typologies")
    public ResponseEntity<Object> createTypology(@RequestBody final TypologyDTO dto) {

        try {

            String typologyDescription = dto.getDescription();

            Optional<Typology> typology = this.typologyService.findTypologyByDescription(typologyDescription);

            if (typology.isPresent()) {
                return new ResponseEntity<>(TYPOLOGY_ALREADY_EXISTS, HttpStatus.OK);
            }

            Typology newTypology = this.typologyService.addNewTypology(typologyDescription);

            TypologyDTO typologyDTO = this.typologyAssembler.toDTO(newTypology);

            typologyDTO.add(linkTo(methodOn(ImplTypologyController.class).getTypology(typologyDescription)).withSelfRel());

            return new ResponseEntity<>(typologyDTO, HttpStatus.CREATED);

        } catch (IllegalArgumentException exception) {

            return new ResponseEntity<>(WRONG_INFORMATION, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/typologies")
    public ResponseEntity<Object> getTypologies() {
        List<TypologyDTO> dto = this.typologyService.getAll();

        RepresentationModel<TypologyDTO> linksList = new RepresentationModel<>();

        for (TypologyDTO each : dto) {
            Link link = linkTo(methodOn(ImplTypologyController.class).
                    getTypology(each.getDescription())).withRel(each.getDescription());
            linksList.add(link);
        }
        if (dto.isEmpty()) {

            return new ResponseEntity<>("Typology doesn't exist.", HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(linksList, HttpStatus.OK);
    }

}

