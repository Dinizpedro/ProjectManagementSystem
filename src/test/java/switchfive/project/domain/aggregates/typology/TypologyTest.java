package switchfive.project.domain.aggregates.typology;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchfive.project.domain.shared.valueObjects.TypologyDescription;

import static org.junit.jupiter.api.Assertions.*;

class TypologyTest {

    @Test
    @DisplayName("Two compared instances have the same description.")
    void typologiesAreEqual() {
        //Arrange
        TypologyDescription theDescription = TypologyDescription.create("Fixed Cost");
        Typology theTypology = new Typology(theDescription);
        Typology otherTypology = new Typology(theDescription);
        //Act
        boolean typologiesAreEqual =
                theTypology.equals(otherTypology);

        //Assert
        assertTrue(typologiesAreEqual);
    }

    @Test
    @DisplayName("Two compared instances are the same.")
    void typologiesAreTheSameInstance() {
        //Arrange
        TypologyDescription description =
                TypologyDescription.create("Fixed Cost");

        Typology thisTypology = new Typology(description);
        Typology otherTypology = thisTypology;

        //Act
        boolean sameTypology = thisTypology.equals(otherTypology);

        //Assert
        assertTrue(sameTypology);
    }

    @Test
    @DisplayName("Two typologies are the same although written differently.")
    void typologyShouldBeTheSameWithDifferentCap() {
        //Arrange
        TypologyDescription description =
                TypologyDescription.create("Fixed Cost");
        TypologyDescription descriptionTwo =
                TypologyDescription.create("fixed cost");
        Typology thisTypology = new Typology(description);
        Typology otherTypology = new Typology(descriptionTwo);
        //Act
        boolean actual = thisTypology.equals(otherTypology);

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Two compared objects are not instances "
            + "of same class.")
    void comparedObjectsAreNotInstancesOfSameClass() {
        //Arrange
        TypologyDescription description =
                TypologyDescription.create("time and " +
                        "materials");

        Typology thisTypology = new Typology(description);
        Object otherTypology = new Object();

        //Act
        boolean actual = thisTypology.equals(otherTypology);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("False Expected. Second object is null.")
    void otherObjectIsNull() {
        //Arrange
        TypologyDescription description =
                TypologyDescription.create("time and " +
                        "materials");

        Typology thisTypology = new Typology(description);


        //Act
        boolean actual = thisTypology == null;

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Objects are the same. Expected same hashcode.")
    void sameHashcode() {
        //Arrange

        TypologyDescription theDescription = TypologyDescription.create("Fixed Cost");

        Typology theTypology = new Typology(theDescription);
        Typology otherTypology = new Typology(theDescription);

        //Act
        boolean actual = theTypology.hashCode()
                == otherTypology.hashCode();
        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Objects are not equals. Expected different hashcode.")
    void differentHashcode() {
        //Arrange
        TypologyDescription description =
                TypologyDescription.create("time and " +
                        "materials");
        TypologyDescription descriptionTwo =
                TypologyDescription.create("Fixed Cost");

        Typology thisTypology = new Typology(description);
        Typology otherTypology = new Typology(descriptionTwo);

        //Act
        boolean actual = thisTypology.hashCode()
                == otherTypology.hashCode();

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("False because one of the descriptions is null")
    void typologyDescriptionIsNull() {
        //Arrange

        TypologyDescription theDescription = TypologyDescription.create("Fixed Cost");
        Typology theTypology = new Typology(theDescription);

        //Act
        boolean descriptionAreTheSame = theTypology.getDescription().equals(null);


        //Assert
        assertFalse(descriptionAreTheSame);
    }

    @Test
    void shouldReturnAnIllegalArgumentExceptionWhenDescriptionIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Typology(null));
    }
}