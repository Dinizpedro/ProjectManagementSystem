//package switchfive.project.d_domain.shared.valueObjects;
//
//import switchfive.project.d_domain.shared.dddTypes.ValueObject;
//
//import java.util.Objects;
//
//public class ProfileID implements ValueObject<ProfileID> {
//
//    private final int identity;
//
//    private ProfileID(int identity) {
//        this.identity = identity;
//    }
//
//    public static ProfileID createProfileID(final int identity) {
//        return new ProfileID(identity);
//    }
//
//    public int getProfileID() {
//        return identity;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        ProfileID that = (ProfileID) o;
//        return sameValueAs(that);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(identity);
//    }
//
//    @Override
//    public boolean sameValueAs(final ProfileID other) {
//        return this.identity == other.identity;
//    }
//}
