package switchfive.project.dtos;

import org.springframework.hateoas.RepresentationModel;

public class TypologyDTO extends RepresentationModel<TypologyDTO> {

    private String description;

    public TypologyDTO() {
    }

    public TypologyDTO(String newTypology) {
        this.description = newTypology;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
