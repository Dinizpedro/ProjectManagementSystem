package switchfive.project.domain.aggregates.typology;

import switchfive.project.domain.shared.dddTypes.AggregateRoot;
import switchfive.project.domain.shared.valueObjects.TypologyDescription;

import java.util.Objects;
import java.util.stream.Stream;
/**
 * Typology class describes the data and the methods of its objects.
 */
public class Typology implements AggregateRoot<Typology> {
    /**
     * Typology description and ID.
     */
    private final TypologyDescription description;

    public Typology(final TypologyDescription description) {
        if (Stream.of(description).allMatch(Objects::nonNull)) {
            this.description = description;
        }else{
            throw new IllegalArgumentException("Typology can't be null");
        }
    }

    public String getDescription() {
        return description.getDescription();
    }

    /**
     * Entities compare by identity, not by attributes.
     *
     * @param other The other entity.
     * @return true if the identities are the same, regardless of other
     * attributes.
     */
    @Override
    public boolean sameIdentityAs(final Typology other) {
        return this.description.sameValueAs(other.description);
    }

    /**
     * Check if two objects have the same data, as the classes in Java are
     * inherited from the object classes only.
     *
     * @param other final Object other
     * @return true if two objects have the same data; otherwise, returns false.
     */
    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Typology)) {
            return false;
        }
        Typology typology = (Typology) other;
        return sameIdentityAs(typology);
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
        return Objects.hash(this.description);
    }
}

