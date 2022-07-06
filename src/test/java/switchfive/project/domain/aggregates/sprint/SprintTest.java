package switchfive.project.domain.aggregates.sprint;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchfive.project.domain.shared.valueObjects.*;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class SprintTest {

    @Test
    @DisplayName("Compare two sprints to see if they're two equal objects")
    void sprintIsEqualsToOther() throws ParseException {

        //----ARRANGE---
        ProjectCode projectCodeObj = ProjectCode.create("A0001");
        String projectCode = "A0001";
        SprintID sprintID = SprintID.createSprintID(projectCode,1);
        SprintNumber sprintNumber = SprintNumber.create(1);
        String startDate = "16/06/2023";
        String endDate = "17/06/2023";
        Time dates = Time.create(startDate,endDate);
        SprintDescription sprintDescription = SprintDescription.create("description");
        SprintStatus sprintStatus = SprintStatus.PLANNED;

        //----ACT-------

        Sprint sprint = new Sprint(sprintID,projectCodeObj,sprintNumber,dates,
                sprintDescription,sprintStatus);
        Sprint otherSprint = new Sprint(sprintID,projectCodeObj,sprintNumber,dates,
                sprintDescription,sprintStatus);

        //----ASSERT----

        assertEquals(sprint,otherSprint);
    }


    @Test
    @DisplayName("Compare two sprints to see if they're two equal objects")
    void sprintIsNotEqualsToOther() throws ParseException {

        //----ARRANGE---
        ProjectCode projectCodeObj = ProjectCode.create("A0001");
        String projectCode = "A0001";
        SprintID sprintID = SprintID.createSprintID(projectCode,1);
        SprintID sprintIDother = SprintID.createSprintID(projectCode,2);

        SprintNumber sprintNumber = SprintNumber.create(1);
        String startDate = "16/06/2023";
        String endDate = "17/06/2023";
        Time dates = Time.create(startDate,endDate);
        SprintDescription sprintDescription = SprintDescription.create("description");
        SprintStatus sprintStatus = SprintStatus.PLANNED;

        //----ACT-------

        Sprint sprint = new Sprint(sprintID,projectCodeObj,sprintNumber,dates,
                sprintDescription,sprintStatus);
        Sprint otherSprint = new Sprint(sprintIDother,projectCodeObj,sprintNumber,dates,
                sprintDescription,sprintStatus);

        //----ASSERT----

        assertNotEquals(sprint,otherSprint);
    }

    @Test
    @DisplayName("Two compared instances are equals.")
    void comparedSprintsAreEquals() throws ParseException {

        //----ARRANGE---

        ProjectCode projectCodeObj = ProjectCode.create("A0001");
        String projectCode = "A0001";
        SprintID sprintID = SprintID.createSprintID(projectCode, 1);
        SprintNumber sprintNumber = SprintNumber.create(1);
        String startDate = "16/06/2023";
        String endDate = "17/06/2023";
        Time dates = Time.create(startDate, endDate);
        SprintDescription sprintDescription = SprintDescription.create("description");
        SprintStatus sprintStatus = SprintStatus.PLANNED;


        Sprint sprint = new Sprint(sprintID, projectCodeObj, sprintNumber, dates,
                sprintDescription, sprintStatus);
        Sprint otherSprint = new Sprint(sprintID, projectCodeObj, sprintNumber, dates,
                sprintDescription, sprintStatus);

        //----ACT-------
        boolean actual = sprint.equals(otherSprint);

        //----ASSERT----
        assertTrue(actual);
    }

    @Test
    @DisplayName("Two compared instances are equals.")
    void comparedSprintsAreNotEquals() throws ParseException {

        //----ARRANGE---

        ProjectCode projectCodeObj = ProjectCode.create("A0001");
        String projectCode = "A0001";
        SprintID sprintID = SprintID.createSprintID(projectCode, 1);
        SprintID sprintIDOther = SprintID.createSprintID(projectCode,2);
        SprintNumber sprintNumber = SprintNumber.create(1);
        String startDate = "16/06/2023";
        String endDate = "17/06/2023";
        Time dates = Time.create(startDate, endDate);
        SprintDescription sprintDescription = SprintDescription.create("description");
        SprintStatus sprintStatus = SprintStatus.PLANNED;


        Sprint sprint = new Sprint(sprintID, projectCodeObj, sprintNumber, dates,
                sprintDescription, sprintStatus);
        Sprint otherSprint = new Sprint(sprintIDOther, projectCodeObj, sprintNumber, dates,
                sprintDescription, sprintStatus);

        //----ACT-------
        boolean actual = sprint.equals(otherSprint);

        //----ASSERT----
        assertFalse(actual);
    }

    @Test
    @DisplayName("One Sprint is not an instance of another object.")
    void instanceOfObject() throws ParseException {

        //----ARRANGE---

        ProjectCode projectCodeObj = ProjectCode.create("A0001");
        String projectCode = "A0001";
        SprintID sprintID = SprintID.createSprintID(projectCode, 1);
        SprintID sprintIDOther = SprintID.createSprintID(projectCode,2);
        SprintNumber sprintNumber = SprintNumber.create(1);
        String startDate = "16/06/2023";
        String endDate = "17/06/2023";
        Time dates = Time.create(startDate, endDate);
        SprintDescription sprintDescription = SprintDescription.create("description");
        SprintStatus sprintStatus = SprintStatus.PLANNED;


        Sprint sprint = new Sprint(sprintID, projectCodeObj, sprintNumber, dates,
                sprintDescription, sprintStatus);
        Object object = new Object();

        //----ACT-------
        boolean actual = sprint.equals(object);

        //----ASSERT----
        assertFalse(actual);
    }

    @Test
    @DisplayName("One Sprint is equals to itself.")
    void shouldReturnTrueWhenSprintIsComparedWithItself() throws ParseException {

        //----ARRANGE---

        ProjectCode projectCodeObj = ProjectCode.create("A0001");
        String projectCode = "A0001";
        SprintID sprintID = SprintID.createSprintID(projectCode, 1);
        SprintNumber sprintNumber = SprintNumber.create(1);
        String startDate = "16/06/2023";
        String endDate = "17/06/2023";
        Time dates = Time.create(startDate, endDate);
        SprintDescription sprintDescription = SprintDescription.create("description");
        SprintStatus sprintStatus = SprintStatus.PLANNED;


        Sprint sprint = new Sprint(sprintID, projectCodeObj, sprintNumber, dates,
                sprintDescription, sprintStatus);


        //----ACT-------
        boolean actual = sprint.equals(sprint);

        //----ASSERT----
        assertTrue(actual);
    }

    @Test
    @DisplayName("One Sprint is equals to itself.")
    void sprintEqualsItself() throws ParseException {

        //----ARRANGE---

        ProjectCode projectCodeObj = ProjectCode.create("A0001");
        String projectCode = "A0001";
        SprintID sprintID = SprintID.createSprintID(projectCode, 1);
        SprintNumber sprintNumber = SprintNumber.create(1);
        String startDate = "16/06/2023";
        String endDate = "17/06/2023";
        Time dates = Time.create(startDate, endDate);
        SprintDescription sprintDescription = SprintDescription.create("description");
        SprintStatus sprintStatus = SprintStatus.PLANNED;

        //----ACT-------
        Sprint sprint = new Sprint(sprintID, projectCodeObj, sprintNumber, dates,
                sprintDescription, sprintStatus);



        //----ASSERT----
        assertEquals(sprint,sprint);
    }

    @Test
    @DisplayName("Other object is null. Expected false.")
    void otherObjectIsNull() throws ParseException {
        //----ARRANGE---

        ProjectCode projectCodeObj = ProjectCode.create("A0001");
        String projectCode = "A0001";
        SprintID sprintID = SprintID.createSprintID(projectCode, 1);
        SprintNumber sprintNumber = SprintNumber.create(1);
        String startDate = "16/06/2023";
        String endDate = "17/06/2023";
        Time dates = Time.create(startDate, endDate);
        SprintDescription sprintDescription = SprintDescription.create("description");
        SprintStatus sprintStatus = SprintStatus.PLANNED;

        Sprint sprint = new Sprint(sprintID, projectCodeObj, sprintNumber, dates,
                sprintDescription, sprintStatus);

        //----ACT-------

        boolean actual = sprint.equals(null);

        //----ASSERT----
        assertFalse(actual);
    }


    @Test
    @DisplayName("Objects are equals. Expected same hashcode.")
    void sameHashcode() throws ParseException {
        //----ARRANGE---

        ProjectCode projectCodeObj = ProjectCode.create("A0001");
        String projectCode = "A0001";
        SprintID sprintID = SprintID.createSprintID(projectCode, 1);
        SprintNumber sprintNumber = SprintNumber.create(1);
        String startDate = "16/06/2023";
        String endDate = "17/06/2023";
        Time dates = Time.create(startDate, endDate);
        SprintDescription sprintDescription = SprintDescription.create("description");
        SprintStatus sprintStatus = SprintStatus.PLANNED;

        Sprint sprint = new Sprint(sprintID, projectCodeObj, sprintNumber, dates,
                sprintDescription, sprintStatus);
        Sprint otherSprint = new Sprint(sprintID, projectCodeObj, sprintNumber, dates,
                sprintDescription, sprintStatus);


        //----ACT-------

        boolean actual = sprint.hashCode() == otherSprint.hashCode();

        //----ASSERT----
        assertTrue(actual);
    }

    @Test
    @DisplayName("Objects are not equals. Expected different hashcode.")
    void differentHashcode() throws ParseException {
        //----ARRANGE---

        ProjectCode projectCodeObj = ProjectCode.create("A0001");
        String projectCode = "A0001";
        SprintID sprintID = SprintID.createSprintID(projectCode, 1);
        SprintID otherSprintID = SprintID.createSprintID(projectCode, 2);
        SprintNumber sprintNumber = SprintNumber.create(1);
        String startDate = "16/06/2023";
        String endDate = "17/06/2023";
        Time dates = Time.create(startDate, endDate);
        SprintDescription sprintDescription = SprintDescription.create("description");
        SprintStatus sprintStatus = SprintStatus.PLANNED;

        Sprint sprint = new Sprint(sprintID, projectCodeObj, sprintNumber, dates,
                sprintDescription, sprintStatus);
        Sprint otherSprint = new Sprint(otherSprintID, projectCodeObj, sprintNumber, dates,
                sprintDescription, sprintStatus);


        //----ACT-------

        boolean actual = sprint.hashCode() == otherSprint.hashCode();

        //----ASSERT----
        assertFalse(actual);
    }

    @Test
    @DisplayName("Should get the SprintID of a Sprint, comparing two equals sprintID's.")
    void getSprintID() throws ParseException {

        //----ARRANGE---

        ProjectCode projectCodeObj = ProjectCode.create("A0001");
        String projectCode = "A0001";
        SprintID sprintID = SprintID.createSprintID(projectCode, 1);
        SprintNumber sprintNumber = SprintNumber.create(1);
        String startDate = "16/06/2023";
        String endDate = "17/06/2023";
        Time dates = Time.create(startDate, endDate);
        SprintDescription sprintDescription = SprintDescription.create("description");
        SprintStatus sprintStatus = SprintStatus.PLANNED;

        Sprint sprint = new Sprint(sprintID, projectCodeObj, sprintNumber, dates,
                sprintDescription, sprintStatus);

        //----ACT-------

        SprintID expected = sprintID;
        SprintID actual = sprint.getSprintID();

        //----ASSERT----
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Should get the ProjectCode of a Sprint, comparing two equals ProjectCodes.")
    void getProjectCode() throws ParseException {

        //----ARRANGE---

        ProjectCode projectCodeObj = ProjectCode.create("A0001");
        String projectCode = "A0001";
        SprintID sprintID = SprintID.createSprintID(projectCode, 1);
        SprintNumber sprintNumber = SprintNumber.create(1);
        String startDate = "16/06/2023";
        String endDate = "17/06/2023";
        Time dates = Time.create(startDate, endDate);
        SprintDescription sprintDescription = SprintDescription.create("description");
        SprintStatus sprintStatus = SprintStatus.PLANNED;

        Sprint sprint = new Sprint(sprintID, projectCodeObj, sprintNumber, dates,
                sprintDescription, sprintStatus);

        //----ACT-------

        String expected = projectCode;
        String actual = sprint.getProjectCode();

        //----ASSERT----
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Should get the SprintNumber of a Sprint, comparing two equals SprintNumbers.")
    void getSprintNumber() throws ParseException {

        //----ARRANGE---

        ProjectCode projectCodeObj = ProjectCode.create("A0001");
        String projectCode = "A0001";
        SprintID sprintID = SprintID.createSprintID(projectCode, 1);
        SprintNumber sprintNumber = SprintNumber.create(1);
        String startDate = "16/06/2023";
        String endDate = "17/06/2023";
        Time dates = Time.create(startDate, endDate);
        SprintDescription sprintDescription = SprintDescription.create("description");
        SprintStatus sprintStatus = SprintStatus.PLANNED;

        Sprint sprint = new Sprint(sprintID, projectCodeObj, sprintNumber, dates,
                sprintDescription, sprintStatus);

        //----ACT-------

        int expected = 1;
        int actual = sprint.getSprintNumber();

        //----ASSERT----
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Should get the Description of a Sprint, comparing two equals Descriptions.")
    void getDescription() throws ParseException {

        //----ARRANGE---

        ProjectCode projectCodeObj = ProjectCode.create("A0001");
        String projectCode = "A0001";
        SprintID sprintID = SprintID.createSprintID(projectCode, 1);
        SprintNumber sprintNumber = SprintNumber.create(1);
        String startDate = "16/06/2023";
        String endDate = "17/06/2023";
        Time dates = Time.create(startDate, endDate);
        SprintDescription sprintDescription = SprintDescription.create("description");
        SprintStatus sprintStatus = SprintStatus.PLANNED;

        Sprint sprint = new Sprint(sprintID, projectCodeObj, sprintNumber, dates,
                sprintDescription, sprintStatus);

        //----ACT-------

        String expected = "description";
        String actual = sprint.getDescription();

        //----ASSERT----
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Should get the Status of a Sprint, comparing two equals Statuses.")
    void getStatus() throws ParseException {

        //----ARRANGE---

        ProjectCode projectCodeObj = ProjectCode.create("A0001");
        String projectCode = "A0001";
        SprintID sprintID = SprintID.createSprintID(projectCode, 1);
        SprintNumber sprintNumber = SprintNumber.create(1);
        String startDate = "16/06/2023";
        String endDate = "17/06/2023";
        Time dates = Time.create(startDate, endDate);
        SprintDescription sprintDescription = SprintDescription.create("description");
        SprintStatus sprintStatus = SprintStatus.PLANNED;

        Sprint sprint = new Sprint(sprintID, projectCodeObj, sprintNumber, dates,
                sprintDescription, sprintStatus);

        //----ACT-------

        String expected = "PLANNED";
        String actual = sprint.getStatus();

        //----ASSERT----
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Should check if the Status of a Sprint is Finished.")
    void checkIfSprintStatusIsFinished() throws ParseException {

        //----ARRANGE---

        ProjectCode projectCodeObj = ProjectCode.create("A0001");
        String projectCode = "A0001";
        SprintID sprintID = SprintID.createSprintID(projectCode, 1);
        SprintNumber sprintNumber = SprintNumber.create(1);
        String startDate = "16/06/2023";
        String endDate = "17/06/2023";
        Time dates = Time.create(startDate, endDate);
        SprintDescription sprintDescription = SprintDescription.create("description");
        SprintStatus sprintStatus = SprintStatus.FINISHED;

        Sprint sprint = new Sprint(sprintID, projectCodeObj, sprintNumber, dates,
                sprintDescription, sprintStatus);

        //----ACT-------

        boolean actual = sprint.isSprintStatusFinished();

        //----ASSERT----
        assertTrue(actual);
    }

    @Test
    @DisplayName("Should return false if status of Sprint is not FINISHED.")
    void checkIfSprintStatusIsFinishedFalse() throws ParseException {

        //----ARRANGE---

        ProjectCode projectCodeObj = ProjectCode.create("A0001");
        String projectCode = "A0001";
        SprintID sprintID = SprintID.createSprintID(projectCode, 1);
        SprintNumber sprintNumber = SprintNumber.create(1);
        String startDate = "16/06/2023";
        String endDate = "17/06/2023";
        Time dates = Time.create(startDate, endDate);
        SprintDescription sprintDescription = SprintDescription.create("description");
        SprintStatus sprintStatus = SprintStatus.PLANNED;

        Sprint sprint = new Sprint(sprintID, projectCodeObj, sprintNumber, dates,
                sprintDescription, sprintStatus);

        //----ACT-------

        boolean actual = sprint.isSprintStatusFinished();

        //----ASSERT----
        assertFalse(actual);
    }

    @Test
    @DisplayName("Should return true if dates are not within the Sprint Dates.")
    void checkIfDatesAreWithinSprintDatesFalse() throws ParseException {

        //----ARRANGE---

        ProjectCode projectCodeObj = ProjectCode.create("A0001");
        String projectCode = "A0001";
        SprintID sprintID = SprintID.createSprintID(projectCode, 1);
        SprintNumber sprintNumber = SprintNumber.create(1);
        String startDate = "16/06/2023";
        String endDate = "17/06/2023";
        Time dates = Time.create(startDate, endDate);
        SprintDescription sprintDescription = SprintDescription.create("description");
        SprintStatus sprintStatus = SprintStatus.PLANNED;

        Sprint sprint = new Sprint(sprintID, projectCodeObj, sprintNumber, dates,
                sprintDescription, sprintStatus);

        Time time = Time.create("16/05/2023","17/06/2023");

        //----ACT-------

        boolean actual = sprint.areDatesWithinSprintDates(time);

        //----ASSERT----
        assertFalse(actual);
    }

    @Test
    @DisplayName("Should return true if dates are within the Sprint Dates.")
    void checkIfDatesAreWithinSprintDatesTrue() throws ParseException {

        //----ARRANGE---

        ProjectCode projectCodeObj = ProjectCode.create("A0001");
        String projectCode = "A0001";
        SprintID sprintID = SprintID.createSprintID(projectCode, 1);
        SprintNumber sprintNumber = SprintNumber.create(1);
        String startDate = "16/06/2023";
        String endDate = "17/07/2023";
        Time dates = Time.create(startDate, endDate);
        SprintDescription sprintDescription = SprintDescription.create("description");
        SprintStatus sprintStatus = SprintStatus.PLANNED;

        Sprint sprint = new Sprint(sprintID, projectCodeObj, sprintNumber, dates,
                sprintDescription, sprintStatus);

        Time time = Time.create("23/06/2023","10/07/2023");

        //----ACT-------

        boolean actual = sprint.areDatesWithinSprintDates(time);

        //----ASSERT----
        assertTrue(actual);
    }


    @Test
    @DisplayName("Should return true if sprint dates are not inside input time.")
    void areSprintDatesInsideInputTimeTrue() throws ParseException {

        //----ARRANGE---

        ProjectCode projectCodeObj = ProjectCode.create("A0001");
        String projectCode = "A0001";
        SprintID sprintID = SprintID.createSprintID(projectCode, 1);
        SprintNumber sprintNumber = SprintNumber.create(1);
        String startDate = "16/06/2023";
        String endDate = "17/07/2023";
        Time dates = Time.create(startDate, endDate);
        SprintDescription sprintDescription = SprintDescription.create("description");
        SprintStatus sprintStatus = SprintStatus.PLANNED;

        Sprint sprint = new Sprint(sprintID, projectCodeObj, sprintNumber, dates,
                sprintDescription, sprintStatus);

        Time time = Time.create("15/06/2023","17/07/2023");

        //----ACT-------

        boolean actual = sprint.areSprintDatesInsideInputTime(time);

        //----ASSERT----
        assertTrue(actual);
    }

    @Test
    @DisplayName("Should return false if sprint dates are inside input time.")
    void areSprintDatesInsideInputTimeFalse() throws ParseException {

        //----ARRANGE---

        ProjectCode projectCodeObj = ProjectCode.create("A0001");
        String projectCode = "A0001";
        SprintID sprintID = SprintID.createSprintID(projectCode, 1);
        SprintNumber sprintNumber = SprintNumber.create(1);
        String startDate = "16/06/2023";
        String endDate = "17/07/2023";
        Time dates = Time.create(startDate, endDate);
        SprintDescription sprintDescription = SprintDescription.create("description");
        SprintStatus sprintStatus = SprintStatus.PLANNED;

        Sprint sprint = new Sprint(sprintID, projectCodeObj, sprintNumber, dates,
                sprintDescription, sprintStatus);

        Time time = Time.create("18/06/2023","17/07/2023");

        //----ACT-------

        boolean actual = sprint.areSprintDatesInsideInputTime(time);

        //----ASSERT----
        assertFalse(actual);
    }


}
