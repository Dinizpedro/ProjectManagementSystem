package switchfive.project.dataModel.dataREST;


import switchfive.project.dataModel.dataJPA.Rest;

public class ProfileRest implements Rest {

    private String userProfileName;


    public ProfileRest() {
    }

    public String getUserProfileName() {
        return userProfileName;
    }

    public void setUserProfileName(String userProfileName) {
        this.userProfileName = userProfileName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileRest that = (ProfileRest) o;
        return userProfileName.equals(that.userProfileName);
    }

}
