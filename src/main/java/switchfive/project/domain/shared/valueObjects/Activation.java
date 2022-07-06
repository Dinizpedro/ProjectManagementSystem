package switchfive.project.domain.shared.valueObjects;

import switchfive.project.domain.shared.dddTypes.ValueObject;

import java.time.LocalDate;
import java.util.UUID;

public final class Activation implements ValueObject<Activation> {
    /**
     * Code that allow the user to activate the account.
     */
    private final String code;
    /**
     * User account activation status.
     */
    private boolean isActivated;

    /**
     * Date that the userAccount is activated.
     */
    private LocalDate activationDate;

    /**
     * Date that the userAccount is inactivated.
     */
    private LocalDate inactivationDate;

    /**
     * Activation constructor, generates a code
     * and isActivated equals false.
     */
    private Activation() {
        this.code = generateCode();
        this.isActivated = false;
    }

    private Activation(String code, boolean isActivated,
                       LocalDate activationDate, LocalDate inactivationDate) {
        this.code = code;
        this.isActivated = isActivated;
        this.activationDate = activationDate;
        this.inactivationDate = inactivationDate;
    }

    public static Activation createActivation(String code, boolean isActivated,
                                              LocalDate activationDate, LocalDate inactivationDate) {
        return new Activation(code, isActivated, activationDate, inactivationDate);
    }

    /**
     * Static method to create Activation object.
     *
     * @return the activation of a user account.
     */
    public static Activation createActivation() {
        return new Activation();
    }

    /**
     * A get method for the activation code.
     *
     * @return the activation code.
     */
    public String getCode() {
        return code;
    }

    /**
     * Method for generating a random code to activate account.
     *
     * @return the activation code.
     */
    private String generateCode() {
        UUID randomCode = UUID.randomUUID();
        return randomCode.toString();
    }

    /**
     * Auxiliary method for comparing the input code from a user
     * and the generated code.
     *
     * @param inputCode the code inserted by the user.
     * @return the user account is activated.
     */
    private boolean compareActivationCode(String inputCode) {
        if (inputCode == null) {
            return false;
        }

        return this.code.equals(inputCode);
    }

    /**
     * If true, user is activated.
     * If false, user is not activated.
     *
     * @return true or false.
     */
    public boolean isActivated() {
        return isActivated;
    }

    /**
     * A method that validates the status of the account.
     *
     * @param inputCode the code inserted by the user
     * @return the user account is activated.
     */
    public boolean validateActivation(String inputCode) {
        if (compareActivationCode(inputCode)) {
            isActivated = true;
            activationDate = LocalDate.now();
            inactivationDate = null;
        }
        return isActivated;
    }

    /**
     * sets isActivated attribute to false.
     */
    public void setToInactive() {
        this.isActivated = false;
    }

    /**
     * sets isActivated attribute to true.
     */
    public void setToActive() {
        this.isActivated = true;
    }

    /**
     * sets activationDate to the current local date.
     */
    public void setActivationDate() {
        activationDate = LocalDate.now();
    }

    /**
     * sets deactivationDate to the current local date.
     */
    public void setInactivationDate() {
        inactivationDate = LocalDate.now();
    }

    /**
     * getter of activationDate attribute.
     *
     * @return date when an account was activated.
     */
    public LocalDate getActivationDate() {
        return activationDate;
    }

    /**
     * getter of inactivationDate attribute.
     *
     * @return the date of inactivation of a user account.
     */
    public LocalDate getInactivationDate() {
        return inactivationDate;
    }

    @Override
    public boolean sameValueAs(Activation other) {
        return this.isActivated == other.isActivated &&
                this.activationDate == other.activationDate &&
                this.code.equals(other.code) &&
                this.inactivationDate == other.inactivationDate;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Activation)) {
            return false;
        }
        Activation activation = (Activation) object;
        return sameValueAs(activation);
    }
}
