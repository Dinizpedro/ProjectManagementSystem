package switchfive.project.applicationServices.assemblers.iAssemblers;

import switchfive.project.dtos.TypologyDTO;
import switchfive.project.domain.aggregates.typology.Typology;

public interface ITypologyAssembler {
    TypologyDTO toDTO(Typology typology);
}
