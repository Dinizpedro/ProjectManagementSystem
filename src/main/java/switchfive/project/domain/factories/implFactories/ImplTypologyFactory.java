package switchfive.project.domain.factories.implFactories;

import org.springframework.stereotype.Component;
import switchfive.project.domain.aggregates.typology.Typology;
import switchfive.project.domain.factories.iFactories.ITypologyFactory;
import switchfive.project.domain.shared.valueObjects.TypologyDescription;

@Component
public class ImplTypologyFactory implements ITypologyFactory {

    public Typology createTypology(TypologyDescription description) {
        return new Typology(description);
    }
}
