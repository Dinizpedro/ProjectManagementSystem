package switchfive.project.dataModel.dataJPA;

import javax.persistence.*;
@Entity
@Table(name = "typologies")
public class TypologyJPA implements JPA {

    @Id
    private String description;

    public TypologyJPA() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

