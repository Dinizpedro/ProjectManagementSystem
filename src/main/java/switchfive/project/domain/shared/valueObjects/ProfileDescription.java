package switchfive.project.domain.shared.valueObjects;

import switchfive.project.domain.shared.dddTypes.ID;

import java.util.Locale;
import java.util.Objects;
import java.util.stream.Stream;

public class ProfileDescription implements ID<ProfileDescription> {

    private final String description;

    private ProfileDescription(final String description) {
        if (isValidDescription(description)) {
            this.description = description;
        } else {
            throw new IllegalArgumentException("Invalid Description");
        }
    }

    public static ProfileDescription createProfileDescription(final String ProfileDescription) {
        return new ProfileDescription(ProfileDescription);
    }

    private boolean isValidDescription(String description) {
        return description != null && isDescriptionBetween1And50(description) && !isDescriptionOnlyBlankSpaces(description);
    }

    private boolean isDescriptionOnlyBlankSpaces(String description) {
        return !(description.trim().length() > 0);
    }

    private boolean isDescriptionBetween1And50(String description) {
        final int MINIMUM_SIZE = 1;
        final int MAXIMUM_SIZE = 50;
        final int NAME_LENGTH = description.length();

        return NAME_LENGTH > MINIMUM_SIZE && NAME_LENGTH < MAXIMUM_SIZE;
    }

    public String getDescription() {
        return description;
    }


    /**
     * Method that compares the Profile object designation with an input String.
     * This comparison is case-sensitive.
     *
     * @param profileDesignation String to be compared
     * @return true if both are equal
     */
    public boolean compareProfileDesignation(final String profileDesignation) {
        if (Stream.of(profileDesignation).allMatch(Objects::nonNull)) {
            return this.description.toLowerCase(Locale.ROOT).trim().
                    equals(profileDesignation.toLowerCase(Locale.ROOT).trim());
        }
        return false;
    }

    @Override
    public boolean sameValueAs(ProfileDescription other) {
        return other != null && compareProfileDesignation(other.description);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof ProfileDescription)) return false;
        ProfileDescription that = (ProfileDescription) object;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}


