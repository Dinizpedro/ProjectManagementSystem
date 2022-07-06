package switchfive.project.domain.factories.iFactories;

import switchfive.project.domain.aggregates.typology.Typology;
import switchfive.project.domain.shared.valueObjects.TypologyDescription;


public interface ITypologyFactory {

    Typology createTypology(TypologyDescription description);
}
