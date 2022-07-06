package switchfive.project.domain.shared.valueObjects;

import switchfive.project.domain.shared.dddTypes.ValueObject;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Email class describes the data and the methods of its objects.
 *
 * @author Maurício Pinto Barros
 * @version 0
 * @since Meeting 28-12-2021
 */

public class Email implements ValueObject<Email> {
    /**
     * User email.
     */
    private final String userEmail;

    /**
     * Constructor
     *
     * @param email that will be created
     */
    private Email(String email) {
        if (email == null) {
            throw new NullPointerException("Email can't be null");
        }
        if (isValidEmail(email)) {
            this.userEmail = email;
        } else {
            throw new IllegalArgumentException("Invalid Email.");
        }
    }

    /**
     * Statid method to create an email object.
     *
     * @param userEmail
     * @return
     */
    public static Email create(String userEmail) {
        return new Email(userEmail);
    }

    /**
     * @return the email of a specific user.
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Method that validates the email
     *
     * @param email the String to be checked.
     * @return true if email is valid.
     * author Valter Daniel Martins de Sousa
     */
    private boolean isValidEmail(String email) {
        //The following restrictions are imposed in the email addresses local-part by using this regex:
        //
        //It allows numeric values from 0 to 9
        //Both uppercase and lowercase letters from a to z are allowed
        //Allowed are underscore “_”, hyphen “-” and dot “.”
        //Dot isn't allowed at the start and end of the local-part
        //Consecutive dots aren't allowed
        //For the local part, a maximum of 64 characters are allowed

        //Restrictions for the domain-part in this regular expression include:
        //
        //It allows numeric values from 0 to 9
        //We allow both uppercase and lowercase letters from a to z
        //Hyphen “-” and dot “.” isn't allowed at the start and end of the domain-part
        //No consecutive dots
        String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)" +
                "*(\\.[A-Za-z]{2,})$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        return m.matches();

    }

    /**
     * Method that determines if an Email object is equal. An Email object is considered equal
     * if another Email object as the same email String
     *
     * @param o Object to compare
     * @return true if object is equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Email)) return false;
        Email email1 = (Email) o;
        return sameValueAs(email1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userEmail);
    }

    @Override
    public boolean sameValueAs(Email other) {
        return other != null && this.userEmail.equals(other.userEmail);
    }
}
