package switchfive.project.domain.shared.valueObjects;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class FunctionTest {


    @Test
    void sameValueFunction() {
        //Arrange
        Function function1 = Function.createFunction("function");

        //Act
        boolean result = function1.sameValueAs(function1);

        //Assert
        assertTrue(result);
    }

    @Test
    void hashCodeFunctionAreTheSame() {
        //Arrange
        Function function1 = Function.createFunction("function");
        Function function2 = Function.createFunction("function");

        //Act
        int result = function1.hashCode();
        int expected = function2.hashCode();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void hashCodeFunctionAreDifferent() {
        //Arrange
        Function function1 = Function.createFunction("function");
        Function function2 = Function.createFunction("Function");

        //Act
        int result = function1.hashCode();
        int expected = function2.hashCode();

        //Assert
        assertNotEquals(expected, result);
    }

    @Test
    void getDescriptionSuccessfully() {
        //Arrange
        Function function1 = Function.createFunction("function");
        Function function2 = Function.createFunction("function");

        //Act
        String result = function1.getDescription();
        String expected = function2.getDescription();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getDescriptionFailure() {
        //Arrange
        Function function1 = Function.createFunction("function");
        Function function2 = Function.createFunction("Function");

        //Act
        String result = function1.getDescription();
        String expected = function2.getDescription();

        //Assert
        assertNotEquals(expected, result);
    }

    @Test
    void sameFunction() {
        //Arrange
        Function function1 = Function.createFunction("function");

        //Assert
        assertEquals(function1, function1);
    }

    @Test
    void FunctionNull() {
        //Arrange
        Function function1 = Function.createFunction("function");

        //Act
        assertNotNull(function1);
    }


    @Test
    void FunctionDifferentClass() {
        //Arrange
        Function function1 = Function.createFunction("function");
        String functionS = "functionTwo";

        //Act
        boolean result = function1.equals(functionS);
        //Assert
        assertFalse(result);
    }

    @Test
    void FunctionAreEquals() {
        //Arrange
        Function function1 = Function.createFunction("function");
        Function function2 = Function.createFunction("function");

        //Act
        boolean result = function1.equals(function2);

        //Assert
        assertTrue(result);
    }

    @Test
    void FunctionAreDifferent() {
        //Arrange
        Function function1 = Function.createFunction("function");
        Function function2 = Function.createFunction("function2");

        assertNotEquals(function1, function2);
    }

    @Test
    void invalidDescription() {
        assertThrows(IllegalArgumentException.class, () ->
                Function.createFunction("")
        );
    }

    @Test
    void nullExceptionForFunction() {
        assertThrows(IllegalArgumentException.class, () ->
                Function.createFunction(null)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "thisHas15Chars1"})
    void correctSizeOfDescription(String str) {
        Function function1 = Function.createFunction(str);

        assertInstanceOf(Function.class, function1);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "     ", "thisHas16Chars12", "thisHas17Chars123"})
    void incorrectSizeOfDescription(String str) {
        assertThrows(IllegalArgumentException.class, () ->
                Function.createFunction(str)
        );
    }

}
