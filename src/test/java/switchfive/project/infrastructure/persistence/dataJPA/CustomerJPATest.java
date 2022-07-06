package switchfive.project.infrastructure.persistence.dataJPA;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import switchfive.project.dataModel.dataJPA.CustomerJPA;

class CustomerJPATest {

    @Test
    void createCustomerJpa() {
        //Arrange
        String name = "Ferrari";

        //Act
        CustomerJPA customerJPA = CustomerJPA.createCustomerJpa(name);
        String actual = customerJPA.getName();

        //Assert
        Assertions.assertEquals(name,actual);
    }
}
