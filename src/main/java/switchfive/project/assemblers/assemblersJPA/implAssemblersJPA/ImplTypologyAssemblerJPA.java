package switchfive.project.assemblers.assemblersJPA.implAssemblersJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchfive.project.assemblers.assemblersJPA.iAssemblersJPA.ITypologyAssemblerJPA;
import switchfive.project.dataModel.dataJPA.TypologyJPA;
import switchfive.project.domain.aggregates.typology.Typology;
import switchfive.project.domain.factories.iFactories.ITypologyFactory;
import switchfive.project.domain.shared.valueObjects.TypologyDescription;

@Service
public class ImplTypologyAssemblerJPA implements ITypologyAssemblerJPA {

    @Autowired
    private ITypologyFactory typologyFactory;

    public TypologyDescription getTypologyDescription(TypologyJPA typologyJPA) {
        String description = typologyJPA.getDescription();
        return TypologyDescription.create(description);
    }


    public Typology toDomain(TypologyJPA typologyJPA) {
        TypologyDescription description = this.getTypologyDescription(typologyJPA);
        return this.typologyFactory.createTypology(description);
    }


    public TypologyJPA toData(Typology typology) {
        String description = typology.getDescription();

        TypologyJPA typologyJPA = new TypologyJPA();
        typologyJPA.setDescription(description);

        return typologyJPA;
    }
}
