package switchfive.project.domain.aggregates.request;

import switchfive.project.domain.shared.dddTypes.AggregateRoot;
import switchfive.project.domain.shared.valueObjects.Email;
import switchfive.project.domain.shared.valueObjects.ProfileDescription;
import switchfive.project.domain.shared.valueObjects.RequestID;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Request class describes the data and the methods of its objects.
 *
 * @author Maurício Pinto Barros
 * @version 0
 * @since Meeting 28-12-2021
 */

public class Request implements AggregateRoot<Request> {
    /**
     * Request identification number.
     */
    private final RequestID identity;
    /**
     * Request creation date.
     */
    private final LocalDate creationDate;
    /**
     * User that order the request.
     */
    private final Email userID;
    /**
     * Profile requested by user.
     */
    private final ProfileDescription profileID;
    /**
     * Request approval status.
     */
    private boolean isApproved;

    /**
     * Request constructor with user and requestedProfile.
     *
     * @param userInput             User userInput
     * @param requestedProfileInput Profile requestedProfileInput
     */
    public Request(final RequestID requestID,
                   final Email userInput,
                   final ProfileDescription requestedProfileInput) {
        if (Stream.of(requestID,
                userInput,
                requestedProfileInput).allMatch(Objects::nonNull)) {

            this.identity = requestID;
            this.userID = userInput;
            this.profileID = requestedProfileInput;

            // Set CreationDate to the system LocalDate when the object is created
            this.creationDate = java.time.LocalDate.now();

            // Set isApproved to default false when the object is created
            this.isApproved = false;
        } else {
            throw new IllegalArgumentException("Inputs can't be null");
        }
    }

    /**
     * Request constructor with all attributes returned from JPA.
     *
     * @param userInput             User userInput
     * @param requestedProfileInput Profile requestedProfileInput
     */
    public Request(final RequestID requestID,
                   final Email userInput,
                   final ProfileDescription requestedProfileInput,
                   final LocalDate creationDate,
                   final boolean isApproved) {
        if (Stream.of(requestID,
                userInput,
                requestedProfileInput,
                creationDate,
                isApproved).allMatch(Objects::nonNull)) {

            this.identity = requestID;
            this.userID = userInput;
            this.profileID = requestedProfileInput;

            // Set CreationDate to the system LocalDate when the object is created
            this.creationDate = creationDate;

            // Set isApproved to default false when the object is created
            this.isApproved = isApproved;
        } else {
            throw new IllegalArgumentException("Inputs can't be null");
        }
    }

    /**
     * Method that approves the request.
     * author: mpc
     */
    public boolean approveRequest() {
        this.isApproved = true;
        return true;
    }

    /**
     * Compares input User and Profile with the ones defined in the Request.
     * author: mpc
     *
     * @param userToCompare    UserID in Request
     * @param profileToCompare ProfileID in Request
     * @return true if input user and profile are equal to the one in the Request
     */
    public boolean compareUserAndProfileOfRequest(Email userToCompare, ProfileDescription profileToCompare) {
        return userToCompare.sameValueAs(this.userID) &&
                profileToCompare.sameValueAs(this.profileID);
    }

    /**
     * Returns true if the Request is approved.
     *
     * @return Approval state
     */
    public boolean isRequestApproved() {
        return this.isApproved;
    }

    public String getIdentity() {
        return identity.toString();
    }

    public String getCreationDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return creationDate.format(dtf);
    }

    public String getUserID() {
        return userID.getUserEmail();
    }

    public String getProfileDescription() {
        return profileID.getDescription();
    }

    /**
     * Check if two Request objects have the same identity, as the classes in Java are
     * inherited from the object classes only.
     *
     * @param other final Object other
     * @return true if two objects have the same ide; otherwise, returns false.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Request request = (Request) other;
        return sameIdentityAs(request);
    }

    /**
     * Whenever hashcode is invoked on the same object more than once
     * during an execution of a Java application, the hashCode method must
     * consistently return the same integer, provided no information used in
     * equals comparisons on the object is modified.
     *
     * @return true if two objects have the same hashcode; otherwise, returns
     * false.
     */
    @Override
    public int hashCode() {
        return Objects.hash(identity);
    }

    @Override
    public boolean sameIdentityAs(final Request other) {
        return other != null && this.identity.sameValueAs(other.identity);
    }
}
