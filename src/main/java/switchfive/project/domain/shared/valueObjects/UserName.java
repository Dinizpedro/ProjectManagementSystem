package switchfive.project.domain.shared.valueObjects;

import switchfive.project.domain.shared.dddTypes.ValueObject;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserName implements ValueObject<UserName> {

    /**
     * attribute userName;
     */
    private final String userName;

    private UserName(String userName) {
        if (userName == null) {
            throw new NullPointerException("Username can't be null");
        }
        if (isValidName(userName)) {
            this.userName = userName;
        } else {
            throw new IllegalArgumentException("Illegal Name");
        }
    }

    /**
     * Static method to create userName object.
     *
     * @param userName the user name.
     * @return the UserName.
     */
    public static UserName createUsername(String userName) {
        return new UserName(userName);
    }

    /**
     * Validates the user account name. The name length must be between 6 and 30, the first character must be
     * in the lowercase/uppercase alphabet and the remaining characters must be alphanumerical
     *
     * @param name the new name as a String
     * @return true if the new name is valid, false otherwise
     */
    private boolean isValidName(String name) {
        // Regex to check valid username.
        // “^” represents that starting character of the string
        // “[A-Za-z]” makes sure that the starting character is in the lowercase or uppercase alphabet
        // “\\w{5, 29}” represents a check to make sure that the remaining items are word items, which includes the underscore, until it reaches the end and that is represented with $
        // The “{5, 29}” represents the 6-30 character constraint given to us minus the predefined first character
        String regex = "^[A-Za-z]\\w{2,29}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(name);
        return m.matches();
    }

    /**
     * method to get the String userName.
     *
     * @return the user's userName.
     */
    public String getUserName() {
        return userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserName)) return false;
        UserName userName1 = (UserName) o;
        return sameValueAs(userName1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }

    @Override
    public boolean sameValueAs(UserName other) {
        return other != null && this.userName.equals(other.userName);
    }
}
