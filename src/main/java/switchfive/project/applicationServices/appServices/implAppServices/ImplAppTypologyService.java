package switchfive.project.applicationServices.appServices.implAppServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchfive.project.dtos.TypologyDTO;
import switchfive.project.applicationServices.appServices.iappServices.IAppTypologyService;
import switchfive.project.applicationServices.iRepositories.ITypologyRepository;
import switchfive.project.domain.aggregates.typology.Typology;
import switchfive.project.domain.factories.iFactories.ITypologyFactory;
import switchfive.project.domain.shared.valueObjects.TypologyDescription;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImplAppTypologyService implements IAppTypologyService {
    private final ITypologyRepository typologyRepository;
    private final ITypologyFactory typologyFactory;

    @Autowired
    public ImplAppTypologyService(ITypologyRepository typologyRepository,
                                  ITypologyFactory typologyFactory) {
        this.typologyRepository = typologyRepository;
        this.typologyFactory = typologyFactory;
    }

    public Typology addNewTypology(String description) {

        TypologyDescription theTypologyDescription =
                TypologyDescription.create(description);
        Typology newTypology;

        if (this.typologyRepository.findTypology(theTypologyDescription)
                .isPresent()) {
            throw new IllegalArgumentException("Typology already exists");
        }
        newTypology =
                this.typologyFactory.createTypology(theTypologyDescription);

        this.typologyRepository.saveNewTypology(newTypology);

        return newTypology;
    }

    public Optional<Typology> findTypologyByDescription(final String description) {
        TypologyDescription typologyDescription = TypologyDescription.create(description);

        if (this.typologyRepository.findTypology(typologyDescription).isPresent()) {
            return this.typologyRepository.findTypology(typologyDescription);
        } else {
            return Optional.empty();
        }
    }


    public List<TypologyDTO> getAll() {
        List<TypologyDTO> typologyDTOList = new ArrayList<>();
        List<Typology> typologyList = this.typologyRepository.findAll();

        for (Typology each : typologyList) {
            TypologyDTO dto = new TypologyDTO();
            dto.setDescription(each.getDescription());
            typologyDTOList.add(dto);
        }

        return typologyDTOList;
    }
}
