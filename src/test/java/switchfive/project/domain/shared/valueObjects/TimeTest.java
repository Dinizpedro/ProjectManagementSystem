package switchfive.project.domain.shared.valueObjects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


class TimeTest {

    @Test
    @DisplayName("startDate is after than endDate.")
    void timeCreationFailsBecauseStartDateIsAfterEndDate() {
        //Arrange
        String startDate = "27/09/2029";
        String endDate = "27/09/2028";

        //Act && Assert
        assertThrows(IllegalArgumentException.class,
                () ->
                        Time.create(startDate, endDate));
    }

    @Test
    @DisplayName("Two instances of Time are equals.")
    void instancesAreEquals() throws ParseException {
        //Arrange
        String startDate = "27/09/2029";
        String endDate = "27/09/2030";

        Time timeOne =
                Time.create(startDate, endDate);
        Time timeTwo =
                Time.create(startDate, endDate);

        //Act
        boolean actual = timeOne.equals(timeTwo);

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Two instances of Time are not " +
            "equals.")
    void instancesAreNotEquals() throws ParseException {
        //Arrange
        String startDate = "27/09/2029";
        String endDate = "27/09/2030";

        String startDateOne = "27/09/2030";
        String endDateOne = "27/09/2035";
        Time timeOne =
                Time.create(startDate, endDate);
        Time timeTwo =
                Time.create(startDateOne, endDateOne);

        //Act
        boolean actual = timeOne.equals(timeTwo);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Compared instance is null. Expected false.")
    void instancesIsNull() throws ParseException {
        //Arrange
        String startDate = "27/09/2029";
        String endDate = "27/09/2030";

        Time timeOne =
                Time.create(startDate, endDate);

        //Act
        boolean actual = timeOne.equals(null);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Two instances of Time are the " +
            "same.")
    void instancesAreTheSame() throws ParseException {
        //Arrange
        String startDate = "27/09/2029";
        String endDate = "27/09/2030";
        Time timeOne =
                Time.create(startDate, endDate);
        Time timeTwo =
                timeOne;

        //Act
        boolean actual = timeOne.equals(timeTwo);

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Compared object are not an instance of " +
            "Time class.")
    void areNotAnInstanceOfThisClass() throws ParseException {
        //Arrange
        String startDate = "27/09/2029";
        String endDate = "27/09/2030";
        Time timeOne =
                Time.create(startDate, endDate);
        Object timeTwo = new Object();

        //Act
        boolean actual = timeOne.equals(timeTwo);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Instances are equals. Expected same hashcode.")
    void sameHashcode() throws ParseException {
        //Arrange
        String startDate = "27/09/2029";
        String endDate = "27/09/2030";
        Time timeOne =
                Time.create(startDate, endDate);
        Time timeTwo =
                Time.create(startDate, endDate);

        //Act
        boolean actual = timeOne.hashCode() == timeTwo.hashCode();

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Instances are not equals. Expected different hashcode.")
    void differentHashcode() throws ParseException {
        //Arrange
        String startDate = "27/09/2029";
        String endDate = "27/09/2030";

        String startDateOne = "27/09/2030";
        String endDateOne = "27/09/2032";
        Time timeOne =
                Time.create(startDate, endDate);
        Time timeTwo =
                Time.create(startDateOne, endDateOne);

        //Act
        boolean actual = timeOne.hashCode() == timeTwo.hashCode();

        //Assert
        assertFalse(actual);
    }

    @Test
    void getStartDate() throws ParseException {
        //Arrange
        String startDate = "27/09/2029";
        String endDate = "27/09/2030";

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate_ = dateFormat.parse(startDate);
        Date endDate_ = dateFormat.parse(endDate);

        Time timeOne = new Time(startDate_, endDate_);

        Date result = timeOne.getStartDate();

        //Act
        boolean actual = startDate_.equals(result);

        //Assert
        assertTrue(actual);
    }

    @Test
    void getEndDate() throws ParseException {
        //Arrange
        String startDate = "27/09/2029";
        String endDate = "27/09/2030";

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate_ = dateFormat.parse(startDate);
        Date endDate_ = dateFormat.parse(endDate);

        Time timeOne = new Time(startDate_, endDate_);

        Date result = timeOne.getEndDate();

        //Act
        boolean actual = endDate_.equals(result);

        //Assert
        assertTrue(actual);
    }

    @Test
    void areDatesInsideObjectDates() throws ParseException {
        //Arrange
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        String startDateStrgThis = "27/09/2029";
        String endDateStgrThis = "27/09/2030";
        String startDateStrgOther = "27/10/2029";
        String endDateStgrOther = "27/07/2030";


        Date startDateThis = dateFormat.parse(startDateStrgThis);
        Date endDateThis = dateFormat.parse(endDateStgrThis);
        Date startDateOther = dateFormat.parse(startDateStrgOther);
        Date endDateOther = dateFormat.parse(endDateStgrOther);

        Time timeThis = new Time(startDateThis, endDateThis);
        Time timeOther = new Time(startDateOther, endDateOther);

        //Act
        boolean actual = timeThis.areInputDatesInsideTimeDates(timeOther);

        //Assert
        assertTrue(actual);
    }
}
