package switchfive.project.interfaceAdapters.domainWS;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * Domain class for a Profile that is fetched in an external API service
 */
public class ProfileWS {

    private String profileDescription;

    public ProfileWS(String profileDescription) {
        if (Stream.of(profileDescription).allMatch(Objects::nonNull)) {
            this.profileDescription = profileDescription;
        } else {
            throw new IllegalArgumentException("Input arguments can't be null");
        }
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileWS profileWS = (ProfileWS) o;
        return Objects.equals(profileDescription, profileWS.profileDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profileDescription);
    }
}
