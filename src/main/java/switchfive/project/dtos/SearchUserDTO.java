package switchfive.project.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.List;
import java.util.Objects;

public class SearchUserDTO extends RepresentationModel<SearchUserDTO> {

    private String email;

    private List<String> userProfileList;

    private boolean activation;

    private String userName;

    private String function;


    public SearchUserDTO() {
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getUserProfileList() {
        return userProfileList;
    }

    public void setUserProfileList(List<String> userProfileList) {
        this.userProfileList = userProfileList;
    }

    public boolean isActivation() {
        return activation;
    }

    public void setActivation(boolean activation) {
        this.activation = activation;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchUserDTO that = (SearchUserDTO) o;
        return activation == that.activation && Objects.equals(email, that.email) && Objects.equals(userProfileList, that.userProfileList) && Objects.equals(userName, that.userName) && Objects.equals(function, that.function);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, userProfileList, activation, userName, function);
    }

    public String getEmail() {
        return email;
    }
}
