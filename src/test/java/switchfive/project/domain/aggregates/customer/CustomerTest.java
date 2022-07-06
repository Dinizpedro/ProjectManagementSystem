package switchfive.project.domain.aggregates.customer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchfive.project.domain.shared.valueObjects.CustomerName;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    @DisplayName("Expected true. Two compared instances are equals.")
    void customersAreEqual() {
        //Arrange
        CustomerName theCustomerName = CustomerName.create("Bezos");

        Customer theCustomer = new Customer(theCustomerName);
        Customer otherCustomer = new Customer(theCustomerName);

        //Act
        boolean customersAreEqual = theCustomer.equals(otherCustomer);

        //Assert
        assertTrue(customersAreEqual);
    }

    @Test
    @DisplayName("Expected false. Two compared instances are equals.")
    void customersAreNotEqual() {
        //Arrange
        CustomerName theCustomerName = CustomerName.create("Bezos");

        Customer theCustomer = new Customer(theCustomerName);

        CustomerName otherCustomerName = CustomerName.create("Musk");
        Customer otherCustomer = new Customer(otherCustomerName);

        //Act
        boolean customersAreEqual = theCustomer.equals(otherCustomer);

        //Assert
        assertFalse(customersAreEqual);
    }

    @Test
    @DisplayName("Expected true. Two compared instances are the same.")
    void typologiesAreTheSameInstance() {
        //Arrange
        CustomerName theName =
                CustomerName.create("Bezos");

        Customer thisCustomer = new Customer(theName);
        Customer otherCustomer = thisCustomer;

        //Act
        boolean sameCustomer = thisCustomer.equals(otherCustomer);

        //Assert
        assertTrue(sameCustomer);
    }


    @Test
    @DisplayName("Expected false. Two compared objects are not instances "
            + "of same class.")
    void comparedObjectsAreNotInstancesOfSameClass() {
        //Arrange
        CustomerName name = CustomerName.create("Bezos");
        Customer thisCustomer = new Customer(name);
        Object otherCustomer = new Object();
        //Act
        boolean actual = thisCustomer.equals(otherCustomer);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Expected false. Second object is null.")
    void otherComparedObjectsIsNull() {
        //Arrange
        CustomerName name = CustomerName.create("Bo Burnham");
        Customer thisCustomer = new Customer(name);
        Customer otherCustomer = null;

        //Act
        boolean actual = thisCustomer.equals(otherCustomer);

        //Assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("Expected true. Objects are equals.")
    void sameHashcode() {
        //Arrange
        CustomerName name = CustomerName.create("Bo Burnham");
        Customer theCustomer = new Customer(name);
        Customer otherCustomer = new Customer(name);

        //Act
        boolean actual = theCustomer.hashCode() == (otherCustomer.hashCode());

        //Assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("Expected false. Objects are different.")
    void differentHashcode() {
        //Arrange
        CustomerName nameTheCustomer = CustomerName.create("Bo Burnham");
        Customer theCustomer = new Customer(nameTheCustomer);
        CustomerName nameOtherCustomer = CustomerName.create("Bill Clinton");
        Customer otherCustomer = new Customer(nameOtherCustomer);

        //Assert
        assertNotEquals(theCustomer.hashCode(), otherCustomer.hashCode());
    }

    @Test
    @DisplayName("Expected true. Two equal objects return same string value.")
    void getCustomerNameSuccessfully() {
        //Arrange
        CustomerName name = CustomerName.create("Portugal");
        Customer theCustomer = new Customer(name);
        Customer otherCustomerWithSameID = new Customer(name);

        //Act
        boolean compareCustomerName = theCustomer.getCustomerName()
                .equals(otherCustomerWithSameID.getCustomerName());

        //Assert
        assertTrue(compareCustomerName);
    }
}
