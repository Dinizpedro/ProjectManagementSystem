package switchfive.project.assemblers.assemblersJPA.iAssemblersJPA;

import switchfive.project.dataModel.dataJPA.TypologyJPA;
import switchfive.project.domain.aggregates.typology.Typology;
import switchfive.project.domain.shared.valueObjects.TypologyDescription;


public interface ITypologyAssemblerJPA {

    TypologyDescription getTypologyDescription(TypologyJPA typologyJPA);

    Typology toDomain(TypologyJPA typologyJPA);

    TypologyJPA toData(Typology typology);

}
