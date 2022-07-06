package switchfive.project.domain.shared.valueObjects;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ActivationTest {

    @Test
    void validInputCode() {
        //Arrange
        Activation code = Activation.createActivation();

        //Act
        String activationCode = code.getCode();

        //Assert
        assertTrue(code.validateActivation(activationCode));

    }

    @Test
    void inputCodeIsNotEqualToGenerated() {

        //Arrange
        Activation code = Activation.createActivation();
        String codeInserted = "1235";

        //Assert
        assertNotEquals(code.validateActivation(code.getCode()), codeInserted);
    }


    @Test
    void inputCodeEmpty() {
        //Arrange
        Activation code = Activation.createActivation();

        //Assert
        assertFalse(code.validateActivation(""));
    }

    @Test
    void inputCodeNull() {
        //Arrange
        Activation code = Activation.createActivation();

        //Assert
        assertFalse(code.validateActivation(null));
    }

    @Test
    public void setToInactiveNotEquals() {
        //Arrange
        Activation activation = Activation.createActivation("1234",false,
                null,null);
        Activation activation1 = Activation.createActivation();

        activation.setToInactive();
        activation1.setToActive();


        assertNotEquals(activation, activation1);
    }

    @Test
    public void setToActiveNotEquals() {
        //Arrange
        Activation activation = Activation.createActivation();
        Activation activation1 = Activation.createActivation();

        activation.setToActive();
        activation1.setToActive();

        assertNotEquals(activation, activation1);
    }


    @Test
    public void setActivationDatesObjectsNotEquals() {
        //Arrange
        Activation activation = Activation.createActivation();
        Activation activation1 = Activation.createActivation();

        activation.setActivationDate();
        activation1.setActivationDate();

        assertNotEquals(activation, activation1);
    }

    @Test
    public void setInactivationDatesObjectsNotEquals() {
        //Arrange
        Activation activation = Activation.createActivation();
        Activation activation1 = Activation.createActivation();

        activation.setInactivationDate();
        activation1.setInactivationDate();

        assertNotEquals(activation, activation1);
    }

    @Test
    public void getActivationDatesObjectsEquals() {
        //Arrange
        Activation activation = Activation.createActivation();
        Activation activation1 = Activation.createActivation();

        LocalDate date1 = activation.getActivationDate();
        LocalDate date2 = activation1.getActivationDate();

        assertEquals(date1, date2);
    }

    @Test
    public void getInactivationDatesObjectsEquals() {
        //Arrange
        Activation activation = Activation.createActivation();
        Activation activation1 = Activation.createActivation();

        LocalDate date1 = activation.getInactivationDate();
        LocalDate date2 = activation1.getInactivationDate();

        assertEquals(date1, date2);
    }

    @Test
    public void isAccountActivated() {
        //Arrange
        Activation activation = Activation.createActivation();
        Activation activation1 = Activation.createActivation();

        boolean isActivated1 = activation.isActivated();
        boolean isActivated2 = activation1.isActivated();

        assertEquals(isActivated1, isActivated2);
    }

    @Test
    public void isActivatedWhenActivated() {
        // Arrange
        Activation activation = Activation.createActivation();
        activation.setToActive();

        // Act
        boolean result = activation.isActivated();

        // Assert
        assertTrue(result);
    }

    @Test
    public void isActivatedWhenInactive() {
        // Arrange
        Activation activation = Activation.createActivation();

        // Act
        boolean result = activation.isActivated();

        // Assert
        assertFalse(result);
    }

    @Test
    public void doesActivationSameValueAs(){
        Activation activation = Activation.createActivation();

        boolean result = activation.sameValueAs(activation);

        assertTrue(result);
    }

    @Test
    public void getActivationDate(){
        Activation activation = Activation.createActivation();
        activation.setActivationDate();

        Activation activationOne = Activation.createActivation();
        activationOne.setActivationDate();

        assertEquals(activation.getActivationDate(),activationOne.getActivationDate());
    }

    @Test
    public void activationDateIsNull(){
        Activation activation = Activation.createActivation();

        Activation activationOne = Activation.createActivation();


        assertNull(activation.getActivationDate(), String.valueOf(activationOne.getActivationDate()));
    }

    @Test
    public void getInactivationDate(){
        Activation activation = Activation.createActivation();
        activation.setInactivationDate();

        Activation activationOne = Activation.createActivation();
        activationOne.setInactivationDate();

        assertEquals(activation.getInactivationDate(),activationOne.getInactivationDate());
    }

    @Test
    public void inactivationDateIsNull(){
        Activation activation = Activation.createActivation();

        Activation activationOne = Activation.createActivation();

        assertNull(activation.getInactivationDate(), String.valueOf(activationOne.getInactivationDate()));
    }


    @Test
    public void activationObjectIsTheSame(){
        Activation activation = Activation.createActivation();
        Activation activationOne = activation;

        assertEquals(activation, activationOne);

    }

    @Test
    public void activationIsNotTheSame(){
        //Arrange

        Activation activation = Activation.createActivation("ADWS",false,LocalDate.now(),null);
        Activation activationOne = Activation.createActivation("FRES", false,LocalDate.now(),null);


        //Act
        boolean actual = activation.equals(activationOne);

        //Assert
        assertFalse(actual);

    }

    @Test
    public void activationCodeIsNotTheSameAnInstanceOf(){
        //Arrange
        String code = "ASDEDDE";
        Activation activation = Activation.createActivation(code,false,null,null);
        Object otherActivationCode = new Object();

        //Assert
        assertNotEquals(activation, otherActivationCode);

    }

    @Test
    public void activationCodeAreSameValueAs(){
        Activation activation = Activation.createActivation();
        Activation activationOne = activation;

        activation.getCode();
        activationOne.getCode();

        boolean result = activation.sameValueAs(activationOne);

        assertTrue(result);

    }
    @Test
    public void activationCodeIsEqualToOtherCode(){
        Activation activation = Activation.createActivation();
        Activation activationOne = activation;

        assertTrue(activation.getCode().equals(activationOne.getCode()));
   }

}
