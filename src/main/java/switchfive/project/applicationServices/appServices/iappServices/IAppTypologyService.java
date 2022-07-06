package switchfive.project.applicationServices.appServices.iappServices;

import switchfive.project.dtos.TypologyDTO;
import switchfive.project.domain.aggregates.typology.Typology;

import java.util.List;
import java.util.Optional;

public interface IAppTypologyService {

    Typology addNewTypology(String description);

    Optional<Typology> findTypologyByDescription(String description);

    List<TypologyDTO> getAll();
}
