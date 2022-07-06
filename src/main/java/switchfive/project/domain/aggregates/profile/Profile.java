package switchfive.project.domain.aggregates.profile;

import switchfive.project.domain.shared.dddTypes.AggregateRoot;
import switchfive.project.domain.shared.valueObjects.ProfileDescription;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * Profile class describes the data and the methods of its objects.
 *
 * @author Daniel dos Santos Torres
 * @version 0
 * @since 30-12-2021
 */

public class Profile implements AggregateRoot<Profile> {

    /**
     * Profile designation which serves also as an ID
     */
    private final ProfileDescription designation;

    /**
     * Profile constructor. The designation describes the Profile.
     *
     * @param designation can't be null.
     */

    public Profile(final ProfileDescription designation) {
        if (Stream.of(designation).allMatch(Objects::nonNull)) {
            this.designation = designation;
        } else {
            throw new IllegalArgumentException("Input arguments can't be null");
        }
    }

    public String getDesignation() {
        return designation.getDescription();
    }

    /**
     * Method that determines if a Profile object is equal. A Profile object is considered equal
     * if another Profile object has the same description (using not-case sensitive comparison).
     *
     * @param o Object to compare
     * @return true if object is equal
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Profile)) {
            return false;
        }
        Profile profile = (Profile) o;
        return sameIdentityAs(profile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.designation);
    }

    @Override
    public boolean sameIdentityAs(Profile other) {
        return this.designation.sameValueAs(other.designation);
    }
}
