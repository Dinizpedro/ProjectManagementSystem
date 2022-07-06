package switchfive.project.model.customer;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import switchfive.project.domain.shared.valueObjects.CustomerName;

import static org.junit.jupiter.api.Assertions.*;

class CustomerNameTest {

    @Test
    @DisplayName("CustomerName has same value as other.")
    void sameValueAs() {
        //Arrange
        CustomerName customerName = CustomerName.create("Insituto Superior de" +
                " Engenharia do Porto");
        CustomerName customerNameOne =
                CustomerName.create("Insituto Superior de" +
                        " Engenharia do Porto");

        //Act
        boolean actualResult = customerName.sameValueAs(customerNameOne);

        //Assert
        assertTrue(actualResult);

    }

    @Test
    @DisplayName("CustomerName doesn't have same value as other.")
    void notSameValueAs() {
        //Arrange
        CustomerName customerName = CustomerName.create("Ferrari");
        CustomerName customerNameOne = CustomerName.create("Honda");

        //Act
        boolean actualResult = customerName.sameValueAs(customerNameOne);

        //Assert
        assertFalse(actualResult);
    }


    @Test
    @DisplayName("Customer Name is the same value as other Customer Name.")
    void customerIsTheSameAsOther() {
        //Arrange
        CustomerName customerName = CustomerName.create("Insituto Superior de" +
                " Engenharia do Porto");
        CustomerName customerNameOne = customerName;

        //Act
        boolean actualResult = customerName.equals(customerNameOne);

        //Assert
        assertTrue(actualResult);
    }

    @Test
    @DisplayName("Other Customer Name is not an instance of.")
    void customerDoesNotHaveTheSameInstance() {
        //Arrange
        CustomerName customerName = CustomerName.create("Insituto Superior de" +
                " Engenharia do Porto");
        Object customerNameOne = new Object();

        //Act
        boolean actualResult = customerName.equals(customerNameOne);

        //Assert
        assertFalse(actualResult);
    }

    @Test
    @DisplayName("Customer Name is equals to other Customer Name.")
    void customerIsEqualsToOther() {
        //Arrange
        CustomerName customerName = CustomerName.create("Insituto Superior de" +
                " Engenharia do Porto");
        CustomerName customerNameOne =
                CustomerName.create("Insituto Superior de" +
                        " Engenharia do Porto");

        //Act
        boolean actualResult = customerName.equals(customerNameOne);

        //Assert
        assertTrue(actualResult);
    }


    @Test
    @DisplayName("Customer Name is equals to other Customer Name.")
    void customerIsEqualToOther() {
        //Arrange
        CustomerName customerName = CustomerName.create("Insituto Superior de" +
                " Engenharia do Porto");
        CustomerName customerNameOne =
                CustomerName.create("Insituto Superior de" +
                        " Engenharia do Porto");

        //Act & Assert
        assertEquals(customerName, customerNameOne);
    }


    @Test
    @DisplayName("Customer Name isn't equal to other Customer Name.")
    void customerIsNotEqualToOther() {
        //Arrange
        CustomerName customerName = CustomerName.create("Insituto Superior de" +
                " Engenharia do Porto");
        RandomStringUtils.random(49);
        CustomerName customerNameOne =
                CustomerName.create(RandomStringUtils.random(49));

        //Act & Assert
        assertNotEquals(customerName, customerNameOne);
    }

    @Test
    @DisplayName("Two equals customer names have the same hashcode.")
    void testHashCode() {
        //Arrange
        CustomerName customerName = CustomerName.create("Insituto Superior de" +
                " Engenharia do Porto");
        CustomerName customerNameOne =
                CustomerName.create("Insituto Superior de" +
                        " Engenharia do Porto");

        //Act
        boolean actual = customerName.hashCode() == customerNameOne.hashCode();

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Two different customer names have different hashcode.")
    void testHashCodeFails() {
        //Arrange
        CustomerName customerName = CustomerName.create("Insituto Superior de" +
                " Engenharia do Porto");
        CustomerName customerNameOne = CustomerName.create("Faculdade");

        //Act
        boolean actual = customerName.hashCode() == customerNameOne.hashCode();

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Customer Name has a null input, throwing an exception.")
    void testCustomerNameNullException() {
        //Arrange
        String name = null;

        //Act && Assert Throw
        assertThrows(NullPointerException.class,
                () -> CustomerName.create(name));
    }

    @DisplayName(
            "Customer Name has a input which exceeds the size allowed, " +
                    "throwing an exception.")
    void createNameAboveCharactersForCustomer() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CustomerName.create(RandomStringUtils.random(51));
        });

    }

    @ParameterizedTest
    @ValueSource(strings = {""})
    @DisplayName("Customer Name has an empty input, throwing an exception.")
    void testCustomerNameIsEmpty(String name) {
        //Act && Assert Throw
        assertThrows(IllegalArgumentException.class,
                () -> CustomerName.create(name));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "This is a sentence which has more than fifty characters and so " +
                    "the creation of a customer name fails. For the creation " +
                    "of a customer name it must have as a maximum fifty " +
                    "characters and at least one character. This description " +
                    "has more than fifty characters to show how this fails."})
    @DisplayName(
            "Customer Name has an input of more than 50 characters, throwing " +
                    "an exception.")
    void testCustomerNameIsAbove50Characters(String name) {

        //Act && Assert Throw
        assertThrows(IllegalArgumentException.class,
                () -> CustomerName.create(name));
    }

    @DisplayName(
            "This test covers the maximum characters a Customer Name may have.")
    @Test
    void testWithMaximumCharacters() {
        String maximumCharacters = RandomStringUtils.random(50);

        CustomerName customerName = CustomerName.create(maximumCharacters);

        assertEquals(customerName, customerName);

    }

    @DisplayName(
            "This test covers the minimum characters a Customer Name may have.")
    @Test
    void testWithMinimumCharacters() {
        String minimumCharacters = RandomStringUtils.random(5);

        CustomerName customerName = CustomerName.create(minimumCharacters);

        assertEquals(customerName, customerName);
    }

}
