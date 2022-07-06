package switchfive.project.domain.aggregates.user;

import switchfive.project.domain.shared.dddTypes.AggregateRoot;
import switchfive.project.domain.shared.valueObjects.*;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * User class describes the data and the methods of its objects.
 *
 * @author Daniel dos Santos Torres
 * @version 0
 * @since 30-12-2021
 */

public class User implements AggregateRoot<User> {
    /**
     * User email.
     */
    private Email email;
    /**
     * User profile list.
     */
    private List<ProfileDescription> userProfileList = new ArrayList<>();
    /**
     * User activation.
     */
    private Activation activation;
    /**
     * User password.
     */
    private Password password;
    /**
     * User name.
     */
    private UserName userName;
    /**
     * User function.
     */
    private Function function;

    public User() {
    }

    public User(Email email, List<ProfileDescription> userProfileList, Activation activation, Password password, UserName userName, Function function) {
        this.email = email;
        this.userProfileList = new ArrayList<>(userProfileList);
        this.activation = activation;
        this.password = password;
        this.userName = userName;
        this.function = function;
    }

    /**
     * User constructor with name, email, photo, function, password.
     *
     * @param userName           final UserName userNameInput
     * @param email              final Email emailInput
     * @param function           final Function functionInput
     * @param password           final Password passwordInput
     * @param profileDescription final Profile profileInput
     */
    public User(Email email,
                Password password,
                UserName userName,
                Function function,
                ProfileDescription profileDescription) {
        this.email = email;
        this.password = password;
        this.activation = Activation.createActivation();
        this.userName = userName;
        this.function = function;
        userProfileList.add(profileDescription);
    }


    public User(Email email,
                Password password,
                UserName userName,
                Function function) {
        this.email = email;
        this.password = password;
        this.activation = Activation.createActivation();
        this.userName = userName;
        this.function = function;
        userProfileList.add(ProfileDescription.createProfileDescription("Visitor"));
    }

    /**
     * @return the email of a specific user in a string format.
     */
    public String getEmail() {

        return this.email.getUserEmail();
    }

    /**
     * @param oldPassword is the old user password
     * @param newPassword is the new user password
     * @return true if password is updated, false otherwise.
     * @author Tânia Mota
     * public method which updates password.
     */
    public boolean updatePassword(String oldPassword,
                                  String newPassword) throws NoSuchAlgorithmException {
        boolean updated = false;

        if (newPassword != null && areTheSameHash(oldPassword)) {
            changePassword(newPassword);
            updated = true;
        }
        return updated;
    }

    /**
     * A get method for the User account password
     */

    public Password getPassword() {
        return this.password;
    }

    /**
     * A method with the objective of changing the User account password.
     *
     * @param newPassword the new changed password in string format.
     */
    private void changePassword(String newPassword) throws NoSuchAlgorithmException {
        this.password = Password.createPassword(newPassword);
    }

    /**
     * Auxiliary method for comparing the input old password from the user
     * and the old password saved in the system;
     *
     * @param inputOldPasswordString is the input password from the user
     * @return true if old input password from user is the same as the one in
     * system,
     * false otherwise
     */
    public boolean areTheSameHash(String inputOldPasswordString) throws NoSuchAlgorithmException {
        Password inputOldPassword = Password.createPassword(inputOldPasswordString);
        return inputOldPassword.sameValueAs(this.password);
    }

    /**
     * A get method for the User list of profiles password.
     *
     * @return a List made up of the user profiles
     */
    public List<ProfileDescription> getUserProfileList() {
        return new ArrayList<>(this.userProfileList);
    }

    /**
     * @param email that you want to validate
     * @return true if the user's email is the same as the given parameter,
     * false if not
     */
    public boolean compareEmail(final String email) {
        return !email.equals("") && getEmail().toLowerCase(Locale.ROOT).contains(email.toLowerCase(Locale.ROOT).trim());
    }

    public UserName getUserName() {
        return userName;
    }

    private void setUserName(UserName newUserName) {
        this.userName = newUserName;
    }

    /**
     * Method for activating user account.
     *
     * @param inputCode the code inserted by the user.
     * @return the user account is activated.
     */
    public boolean activateAccount(String inputCode) {
        return this.activation.validateActivation(inputCode);
    }

    /**
     * Get method for activating an account.
     *
     * @return activation status.
     */
    public Activation getActivation() {
        return activation;
    }

    /**
     * if user status is active, sets it to inactive and also sets the
     * deactivation date
     * to the current date.
     */
    public void setToInactive() {
        setActivationStatusToInactive();
        setInactivationDate();
    }

    /**
     * gets activation object and sets its inactivationDate attribute to false.
     */
    private void setInactivationDate() {
        getActivation().setInactivationDate();
    }

    /**
     * gets activation object and sets his isActivated attribute to false.
     */
    private void setActivationStatusToInactive() {
        getActivation().setToInactive();
    }

    /**
     * checks if the user isActivated attribute is true.
     *
     * @return true if it is.
     */
    public boolean isUserActive() {
        return getActivation().isActivated();
    }

    /**
     * if user status is inactive, sets it to active and also sets the
     * activation date
     * to the current date.
     */
    public void setToActive() {
        setActivationStatusToActive();
        setActivationDate();
    }

    /**
     * gets activation object and sets his activation date.
     */
    private void setActivationDate() {
        getActivation().setActivationDate();
    }

    /**
     * gets activation object and sets his isActivated status to true.
     */
    private void setActivationStatusToActive() {
        getActivation().setToActive();
    }


    /**
     * This method will delete a specific userProfile and add a new one. It
     * will only add a pretendedProfile if the actualProfile exists.
     *
     * @param actualProfile    profile that will be deleted
     * @param pretendedProfile profile that will be added
     * @return true if change is successfully done, false if not.
     */
    public boolean changeProfile(ProfileDescription actualProfile,
                                 ProfileDescription pretendedProfile) {
        return userProfileList.remove(actualProfile) && userProfileList.add(pretendedProfile);
    }

    /**
     * This method will iterate through a Users profile list, look for a
     * actualProfile, delete it and add a pretendedProfile.
     *
     * @param actualProfile    profile that will be deleted
     * @param pretendedProfile profile that will be added
     * @return true if the actualProfile is deleted and pretendedProfile is
     * added, false if not.
     */
    public boolean updateProfile(final ProfileDescription actualProfile,
                                 final ProfileDescription pretendedProfile) {
        boolean result = false;

        for (ProfileDescription selectedProfile : userProfileList) {
            if (actualProfile.equals(selectedProfile)) {
                result = changeProfile(selectedProfile, pretendedProfile);
            }
        }
        return result;
    }

    /**
     * Updates the user account's name, function and photo.
     *
     * @param newName     a String to be set as the new name.
     * @param newFunction a String to be set as the new function.
     */
    public void updateUserInformation(UserName newName,
                                      Function newFunction) {
        setUserName(newName);
        setFunction(newFunction);
    }

    /**
     * Method that checks if a User already has the requested Profile
     * associated to him.
     *
     * @param requestedProfile Profile Identifier object
     * @return true if User already has the requested Profile
     */
    public boolean doesUserAlreadyHaveRequestedProfile(final ProfileDescription requestedProfile) {
        return this.getUserProfileList().contains(requestedProfile);
    }

    public boolean getAccountStatus() {
        return getActivation().isActivated();
    }

    public Function getFunction() {
        return function;
    }

    private void setFunction(Function newFunction) {
        this.function = newFunction;
    }

    public String getFunctionDescription() {
        return getFunction().getDescription();
    }

    public List<ProfileDescription> getProfileList() {
        return this.userProfileList;
    }

    public List<String> getProfileListAsDescription() {
        List<String> profileList = new ArrayList<>();
        for (ProfileDescription profile : getProfileList()) {
            profileList.add(profile.getDescription());
        }
        return profileList;
    }

    /**
     * Method that determines if a User object is equal. A User object is
     * considered equal if another User
     * object has the same e-mail.
     *
     * @param o Object to compare
     * @return true if object is equal
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return sameIdentityAs(user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public boolean sameIdentityAs(User other) {
        return other != null && this.email.sameValueAs(other.email);
    }

    public boolean addProfile(ProfileDescription profileDescription) {
        if (!hasProfile(profileDescription)) {
            return userProfileList.add(profileDescription);
        }
        return false;
    }

    public boolean removeProfile(ProfileDescription profileDescription) {
        if (hasProfile(profileDescription)) {
            return userProfileList.remove(profileDescription);
        }
        return false;
    }

    /**
     * Checks if a user has a given profile.
     *
     * @param profile the profile to be checked.
     * @return true if it has the profile, false otherwise.
     */

    public boolean hasProfile(ProfileDescription profile) {
        boolean result = false;
        for (ProfileDescription selectedProfile : userProfileList) {
            if (selectedProfile.equals(profile) && !result) {
                result = true;
            }
        }
        return result;
    }

}




