package switchfive.project.model.task;

import org.junit.jupiter.api.Test;
import switchfive.project.domain.shared.valueObjects.Hour;
import switchfive.project.domain.shared.valueObjects.Log;
import switchfive.project.domain.shared.valueObjects.TaskDescription;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class LogTest {

    @Test
    void shouldReturnTrueComparingObjectsWithTheSameValue() {
        int validCode = 1;
        Hour validTimeSpent = Hour.createHour(3);
        TaskDescription validDescription = TaskDescription.createTaskDescription("Some description");

        Log logOne = Log.createLog(validCode, validTimeSpent, validDescription);
        Log logTwo = Log.createLog(validCode, validTimeSpent, validDescription);

        assertEquals(logOne, logTwo);
    }

    @Test
    void shouldReturnTrueAssertingDifferentValuesAreNotEquals() {
        int codeOne = 1;
        Hour timeSpentOne = Hour.createHour(3);
        TaskDescription descriptionOne = TaskDescription.createTaskDescription("Some description");
        int codeTwo = 2;
        Hour timeSpentTwo = Hour.createHour(2);
        TaskDescription descriptionTwo = TaskDescription.createTaskDescription("A different description");

        Log logOne = Log.createLog(codeOne, timeSpentOne, descriptionOne);
        Log logTwo = Log.createLog(codeTwo, timeSpentTwo, descriptionTwo);

        assertNotEquals(logOne, logTwo);
    }

    @Test
    void shouldReturnTrueComparingObjectsHashCodeWithTheSameValue() {
        int validCode = 1;
        Hour validTimeSpent = Hour.createHour(3);
        TaskDescription validDescription = TaskDescription.createTaskDescription("Some description");

        Log logOne = Log.createLog(validCode, validTimeSpent, validDescription);
        Log logTwo = Log.createLog(validCode, validTimeSpent, validDescription);

        assertEquals(logOne.hashCode(), logTwo.hashCode());
    }

    @Test
    void shouldReturnTrueAsseringDifferentValuesHaveDifferentHashCodes() {
        int codeOne = 1;
        Hour timeSpentOne = Hour.createHour(3);
        TaskDescription descriptionOne = TaskDescription.createTaskDescription("Some description");
        int codeTwo = 2;
        Hour timeSpentTwo = Hour.createHour(2);
        TaskDescription descriptionTwo = TaskDescription.createTaskDescription("A different description");

        Log logOne = Log.createLog(codeOne, timeSpentOne, descriptionOne);
        Log logTwo = Log.createLog(codeTwo, timeSpentTwo, descriptionTwo);

        assertNotEquals(logOne.hashCode(), logTwo.hashCode());
    }

    @Test
    void shouldReturnTrueAssertingTheSameInstanceIsEqual() {
        int validCode = 1;
        Hour validTimeSpent = Hour.createHour(3);
        TaskDescription validDescription = TaskDescription.createTaskDescription("Some description");

        Log logOne = Log.createLog(validCode, validTimeSpent, validDescription);

        assertEquals(logOne, logOne);
    }

    @Test
    void shouldReturnTrueAssertingAnInstanceIsNotNull() {
        int validCode = 1;
        Hour validTimeSpent = Hour.createHour(3);
        TaskDescription validDescription = TaskDescription.createTaskDescription("Some description");

        Log logOne = Log.createLog(validCode, validTimeSpent, validDescription);

        assertNotEquals(null, logOne);
    }
}
