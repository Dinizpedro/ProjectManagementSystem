package switchfive.project.domain.shared.valueObjects;

import switchfive.project.domain.shared.dddTypes.ValueObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Password class describes the data and the methods of its objects.
 *
 * @author Maurício Pinto Barros
 * @version 0
 * @since Meeting 28-12-2021
 */

public class Password implements ValueObject<Password> {
    /**
     * User password.
     */
    private String userPassword;

    public Password() {
    }

    /**
     * Private Constructor for the User Password
     *
     * @param password for the user account
     */
    private Password(String password) throws NoSuchAlgorithmException {
        if (password == null) {
            throw new NullPointerException("Password can't be null");
        }
        if (validatePassword(password)) {
            this.userPassword = generateHash(password);
        } else {
            throw new IllegalArgumentException("Invalid Password");
        }
    }

    /**
     * Static method to create a password object
     *
     * @param password the password to be hashed
     */
    public static Password createPassword(String password) throws NoSuchAlgorithmException {
        return new Password(password);
    }

    public static Password createPasswordFromDB(String passwordHash) {
        Password password1 = new Password();
        password1.setPassword(passwordHash);

        return password1;
    }

    //new
    private void setPassword(String passwordHash) {
        this.userPassword = passwordHash;
    }


    /**
     * Get method for finding Password
     *
     * @return the Password.
     */
    public String getUserPassword() {

        return userPassword;
    }


    /**
     * @param passwordToHash is the parameter to encrypt
     * @return password encrypted
     */
    private String generateHash(String passwordToHash) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(passwordToHash.getBytes());
        byte[] bytes = md.digest();
        StringBuilder sb = new StringBuilder();

        for (byte aByte : bytes) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }

       return sb.toString();
    }

    /**
     * method for validating password
     *
     * @param password is the attribute to be validated
     * @return true if the password is valid, false otherwise
     */
    private boolean validatePassword(String password) {
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";

        Pattern p = Pattern.compile(regex);

        Matcher m = p.matcher(password);

        return m.matches();
    }

    @Override
    public boolean sameValueAs(Password other) {

        return other != null && this.userPassword.equals(other.userPassword);
    }
    //new
    @Override
    public String toString() {

        return this.userPassword;
    }

}

