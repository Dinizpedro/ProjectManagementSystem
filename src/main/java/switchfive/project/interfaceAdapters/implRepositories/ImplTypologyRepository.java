package switchfive.project.interfaceAdapters.implRepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switchfive.project.assemblers.assemblersJPA.iAssemblersJPA.ITypologyAssemblerJPA;
import switchfive.project.dataModel.dataJPA.TypologyJPA;
import switchfive.project.infrastructure.persistence.repositories.iRepositoriesJPA.ITypologyRepositoryJPA;
import switchfive.project.applicationServices.iRepositories.ITypologyRepository;
import switchfive.project.domain.aggregates.typology.Typology;
import switchfive.project.domain.shared.valueObjects.TypologyDescription;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class ImplTypologyRepository implements ITypologyRepository {

    private final ITypologyAssemblerJPA typologyAssemblerJPA;

    private final ITypologyRepositoryJPA typologyRepositoryJPA;

    @Autowired
    public ImplTypologyRepository(ITypologyRepositoryJPA typologyRepositoryJPA,
                                  ITypologyAssemblerJPA typologyAssemblerJPA) {
        this.typologyAssemblerJPA = typologyAssemblerJPA;
        this.typologyRepositoryJPA = typologyRepositoryJPA;
    }

    public void saveNewTypology(Typology newTypology) {

        TypologyJPA typologyJPA = this.typologyAssemblerJPA.toData(newTypology);

        this.typologyRepositoryJPA.save(typologyJPA);
    }

    public boolean typologyExists(TypologyDescription typologyDescription) {
        return this.typologyRepositoryJPA.existsByDescription(typologyDescription.getDescription());
    }

    public List<Typology> findAll() {
        List<Typology> typologiesList = new ArrayList<>();
        Iterable<TypologyJPA> typologyJPAList =
                this.typologyRepositoryJPA.findAll();

        for (TypologyJPA each : typologyJPAList) {
            typologiesList.add(this.typologyAssemblerJPA.toDomain(each));
        }

        return typologiesList;
    }

    public Optional<Typology> findTypology(
            final TypologyDescription typologyDescription) {
        TypologyJPA typologyJPA;

        if (this.typologyRepositoryJPA.existsByDescription(typologyDescription.getDescription())) {
            typologyJPA = this.typologyRepositoryJPA.findByDescription(typologyDescription.getDescription()).get();

            Typology typology = this.typologyAssemblerJPA.toDomain(typologyJPA);

            return Optional.ofNullable(typology);
        }

        return Optional.empty();
    }
}
