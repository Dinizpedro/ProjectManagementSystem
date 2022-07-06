package switchfive.project.model.profile;

import org.junit.jupiter.api.Test;
import switchfive.project.domain.aggregates.profile.Profile;
import switchfive.project.domain.shared.valueObjects.ProfileDescription;

import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {

    @Test
    void testGetDescription() {
        // Arrange
        String expected = "Director";
        ProfileDescription profileDescriptionDirector = ProfileDescription.createProfileDescription(expected);
        Profile selectedProfileObj = new Profile(profileDescriptionDirector);

        // Act
        String result = selectedProfileObj.getDesignation();

        // Assert
        assertEquals(expected, result);
    }


    @Test
    void testConstructorProfileDescriptionNullException() {
        //Arrange
        ProfileDescription profileDescription = null;

        //Assert Throw
        assertThrows(IllegalArgumentException.class, () -> new Profile(profileDescription));
    }


    @Test
    void testEqualsTrue() {

        // Arrange
        ProfileDescription profileDescriptionDirector = ProfileDescription.createProfileDescription("Director");
        Profile selectedProfileObj1 = new Profile(profileDescriptionDirector);

        // Act
        Profile selectedProfileObj2 = new Profile(profileDescriptionDirector);

        // Assert
        assertEquals(selectedProfileObj1, selectedProfileObj2);

    }

    @Test
    void testEqualsSameObject() {

        // Arrange
        ProfileDescription profileDescriptionDirector = ProfileDescription.createProfileDescription("Director");
        Profile selectedProfileObj = new Profile(profileDescriptionDirector);

        // Assert
        assertEquals(selectedProfileObj, selectedProfileObj);

    }

    @Test
    void testEqualsDifferentObject() {

        // Arrange
        ProfileDescription profileDescriptionDirector = ProfileDescription.createProfileDescription("Director");
        Profile selectedProfileObj = new Profile(profileDescriptionDirector);

        // Act
        String otherObject = "Not a Profile";

        // Assert
        assertNotEquals(selectedProfileObj, otherObject);

    }

    @Test
    void testNotEqualDifferentDescription() {

        // Arrange
        ProfileDescription profileDescriptionDirector = ProfileDescription.createProfileDescription("Director");
        Profile selectedProfileObj1 = new Profile(profileDescriptionDirector);

        // Act
        ProfileDescription profileDescriptionVisitor = ProfileDescription.createProfileDescription("Visitor");
        Profile selectedProfileObj2 = new Profile(profileDescriptionVisitor);

        // Assert
        assertNotEquals(selectedProfileObj1, selectedProfileObj2);
    }

    @Test
    void hashCodeDifferentProfileDesignation() {
        // Arrange
        ProfileDescription profileDescriptionVisitor = ProfileDescription.createProfileDescription("Visitor");
        Profile profile1 = new Profile(profileDescriptionVisitor);

        ProfileDescription profileDescriptionDirector = ProfileDescription.createProfileDescription("Director");
        Profile profile2 = new Profile(profileDescriptionDirector);

        // Assert
        assertNotEquals(profile1.hashCode(), profile2.hashCode());
    }

    @Test
    void hashCodeSameProfileDesignation() {
        // Arrange
        ProfileDescription profileDescriptionVisitor = ProfileDescription.createProfileDescription("Visitor");
        Profile profile1 = new Profile(profileDescriptionVisitor);

        Profile profile2 = new Profile(profileDescriptionVisitor);

        // Assert
        assertEquals(profile1.hashCode(), profile2.hashCode());
    }

}
