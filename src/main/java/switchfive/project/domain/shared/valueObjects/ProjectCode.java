package switchfive.project.domain.shared.valueObjects;

import switchfive.project.domain.shared.dddTypes.ID;

import java.io.Serializable;
import java.util.Objects;

/**
 * ProjectCode class describes the data and the methods of its objects.
 */

public final class ProjectCode implements ID<ProjectCode>, Serializable {
    /**
     * Alphanumeric code, 5 digits.
     */
    private final String code;

    /**
     * We use private constructor so that any target class could not
     * instantiate this class directly by calling constructor.
     *
     * @param codeInput Alphanumeric codeInput, 5 digits.
     */
    private ProjectCode(final String codeInput) {
        if (codeInput.matches("^[a-zA-Z0-9]{5}+$")) {
            this.code = codeInput;
        } else {
            throw new IllegalArgumentException("Invalid Project Code");
        }
    }

    /**
     * @param code Alphanumeric code, 5 digits.
     * @return new instance of ProjectCode.
     */
    public static ProjectCode create(final String code) {
        return new ProjectCode(code);
    }

    /**
     * @param other The other value object.
     * @return true if instance "other" have same valued as this.code;
     * otherwise, returns false.
     */
    @Override
    public boolean sameValueAs(final ProjectCode other) {
        return other != null && code.equals(other.code);
    }

    /**
     * Override Equals.
     *
     * @param other final Object otherObject
     * @return true if objects are equals; otherwise, returns false
     */
    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ProjectCode)) {
            return false;
        }
        ProjectCode that = (ProjectCode) other;
        return sameValueAs(that);
    }

    /**
     * Override hash.
     *
     * @return hash (int).
     */
    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    /**
     * @return String code.
     */
    public String getCode() {
        return code;
    }
}
