/*
package switchfive.project.model.customer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchfive.project.d_domain.aggregates.customer.Customer;
import switchfive.project.d_domain.shared.valueObjects.CustomerID;
import switchfive.project.d_domain.shared.valueObjects.CustomerName;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CustomerTest {

    @Test
    @DisplayName("Customer has same identity has other.")
    void customerHasSameIdentityAsOther() {
        //Arrange
        CustomerID customerID = mock(CustomerID.class);
        CustomerName customerName = mock(CustomerName.class);
        CustomerName customerNameOne = mock(CustomerName.class);

        Customer customer = new Customer(customerID,customerName);
        Customer customerOne = new Customer(customerID,customerNameOne);

        //Act
        boolean actualResult = customer.sameIdentityAs(customerOne);

        //Assert
        assertTrue(actualResult);
    }

    @Test
    @DisplayName("Customer has different identity has other.")
    void customerDoesNotHaveSameIdentityAsOther() {
        //Arrange
        CustomerID customerID = mock(CustomerID.class);
        CustomerID customerIDOne = mock(CustomerID.class);
        CustomerName customerName = mock(CustomerName.class);


        Customer customer = new Customer(customerID,customerName);
        Customer customerOne = new Customer(customerIDOne,customerName);

        //Act
        boolean actualResult = customer.sameIdentityAs(customerOne);

        //Assert
        assertFalse(actualResult);
    }

    @Test
    @DisplayName("Customer is the same as other has other.")
    void customerIsTheSameAsOther() {
        //Arrange
        CustomerID customerID = mock(CustomerID.class);
        CustomerName customerName = mock(CustomerName.class);


        Customer customer = new Customer(customerID,customerName);
        Customer customerOne = customer;

        //Act
        boolean actualResult = customer.equals(customerOne);

        //Assert
        assertTrue(actualResult);
    }

    @Test
    @DisplayName("Customer is not an instance of.")
    void customerIsNotAnInstanceOfOther() {
        //Arrange
        CustomerID customerID = mock(CustomerID.class);
        CustomerName customerName = mock(CustomerName.class);


        Customer customer = new Customer(customerID,customerName);
        Object customerOne = new Object();

        //Act
        boolean actualResult = customer.equals(customerOne);

        //Assert
        assertFalse(actualResult);
    }

    @Test
    @DisplayName("Customer has same value as other.")
    void customerIsEqualsToOther() {
        //Arrange
        CustomerID customerID = mock(CustomerID.class);
        CustomerName customerName = mock(CustomerName.class);


        Customer customer = new Customer(customerID,customerName);
        Customer customerOne = new Customer(customerID,customerName);

        //Act
        boolean actualResult = customer.equals(customerOne);

        //Assert
        assertTrue(actualResult);
    }


    @Test
    @DisplayName("Customer equals other customer.")
    void thisCustomerEqualsOther() {
        //Arrange
        CustomerID customerID = mock(CustomerID.class);
        CustomerName customerName = mock(CustomerName.class);

        Customer customer = new Customer(customerID,customerName);
        Customer customerOne = new Customer(customerID,customerName);

        //Act & Assert
        assertEquals(customer,customerOne);
    }

    @Test
    @DisplayName("Customer is different to other customer.")
    void thisCustomerIsNotEqualsOther() {
        //Arrange
        CustomerID customerID = mock(CustomerID.class);
        CustomerName customerName = mock(CustomerName.class);

        CustomerID customerIDOne = mock(CustomerID.class);
        CustomerName customerNameOne = mock(CustomerName.class);

        Customer customer = new Customer(customerID,customerName);
        Customer customerOne = new Customer(customerIDOne,customerNameOne);

        //Act & Assert
        assertNotEquals(customer,customerOne);
    }

    @Test
    @DisplayName("Two equals customers have the same hashcode.")
    void testHashCodeForCustomer() {
        //Arrange
        CustomerID customerID = mock(CustomerID.class);
        CustomerName customerName = mock(CustomerName.class);

        Customer customer = new Customer(customerID,customerName);
        Customer customerOne = new Customer(customerID,customerName);

        //Act
        boolean actualResult = customer.hashCode() == customerOne.hashCode();

        // Assert
        assertTrue(actualResult);
    }

    @Test
    @DisplayName("Two equals customers have different hashcodes.")
    void testHashCodeForCustomerFails() {
        //Arrange
        CustomerID customerID = mock(CustomerID.class);
        CustomerName customerName = mock(CustomerName.class);
        CustomerID customerIDOne = mock(CustomerID.class);

        Customer customer = new Customer(customerID,customerName);
        Customer customerOne = new Customer(customerIDOne,customerName);

        //Act
        boolean actualResult = customer.hashCode() == customerOne.hashCode();

        // Assert
        assertFalse(actualResult);
    }

}
*/
