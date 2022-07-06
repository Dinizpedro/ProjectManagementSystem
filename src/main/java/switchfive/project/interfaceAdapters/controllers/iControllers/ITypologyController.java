package switchfive.project.interfaceAdapters.controllers.iControllers;

import org.springframework.http.ResponseEntity;
import switchfive.project.dtos.TypologyDTO;

public interface ITypologyController {

    ResponseEntity<Object> createTypology(TypologyDTO dto);

    ResponseEntity<Object> getTypology(String typology);

    ResponseEntity<Object> getTypologies();


}
