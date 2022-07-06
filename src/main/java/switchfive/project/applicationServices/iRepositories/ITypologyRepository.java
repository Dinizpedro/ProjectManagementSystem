package switchfive.project.applicationServices.iRepositories;

import switchfive.project.domain.aggregates.typology.Typology;
import switchfive.project.domain.shared.valueObjects.TypologyDescription;


import java.util.List;
import java.util.Optional;

public interface ITypologyRepository {

    Optional<Typology> findTypology(TypologyDescription typologyDescription);

    void saveNewTypology(Typology newTypology);

    boolean typologyExists(TypologyDescription typologyDescription);

    List<Typology> findAll();
}
