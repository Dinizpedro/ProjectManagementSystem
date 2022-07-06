package switchfive.project.applicationServices.assemblers.implAssemblers;

import org.springframework.stereotype.Component;
import switchfive.project.dtos.TypologyDTO;
import switchfive.project.applicationServices.assemblers.iAssemblers.ITypologyAssembler;
import switchfive.project.domain.aggregates.typology.Typology;

@Component
public class TypologyAssembler implements ITypologyAssembler {

    public TypologyDTO toDTO(Typology typology) {
        TypologyDTO typologyDTO = new TypologyDTO();
        typologyDTO.setDescription(typology.getDescription());

        return typologyDTO;
    }
}
