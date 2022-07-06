package switchfive.project.dataModel.dataJPA;

import javax.persistence.*;

@Entity
@Table(name = "profiles")
public class ProfileJPA implements JPA {

    @Id
    private String profileDescription;


    public ProfileJPA() {
    }


    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }
}
