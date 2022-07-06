package switchfive.project.dataModel.dataJPA;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
public class UserJPA implements JPA {

    @Id
    private String email;

    @CollectionTable(name = "profiles_user")
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> profileList;

    private String code;
    private boolean isActivated;

    private LocalDate activationDate;

    private LocalDate inactivationDate;
    private String password;
    private String username;
    private String function;

    public UserJPA() {
    }

    public UserJPA( String email, List<String> profileList, String code, boolean isActivated, LocalDate activationDate, LocalDate inactivationDate, String password, String username, String function) {
        this.email = email;
        this.profileList = profileList;
        this.code = code;
        this.isActivated = isActivated;
        this.activationDate = activationDate;
        this.inactivationDate = inactivationDate;
        this.password = password;
        this.username = username;
        this.function = function;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getProfileList() {
        return profileList;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getFunction() {
        return function;
    }

    public String getCode() {
        return code;
    }

    public LocalDate getActivationDate() {
        return activationDate;
    }

    public LocalDate getInactivationDate() {
        return inactivationDate;
    }

    public void addProfile(String profileDescription) {
        profileList.add(profileDescription);
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public void removeProfile(String profileDescription) {
        profileList.remove(profileDescription);
    }


}
