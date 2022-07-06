package switchfive.project.infrastructure.persistence.assemblers.implAssemblers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import switchfive.project.assemblers.assemblersJPA.implAssemblersJPA.ImplCustomerAssemblerJPA;
import switchfive.project.dataModel.dataJPA.CustomerJPA;
import switchfive.project.domain.aggregates.customer.Customer;
import switchfive.project.domain.factories.implFactories.ImplCustomerFactory;
import switchfive.project.domain.shared.valueObjects.CustomerName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ImplCustomerAssemblerJPATest {

    @Test
    void customerJpaToCustomer() {
        //Arrange
        String jpaName = "Ferrari";
        CustomerJPA customerJPAMock = mock(CustomerJPA.class);
        when(customerJPAMock.getName()).thenReturn(jpaName);

        CustomerName customerNameMock = mock(CustomerName.class);
        MockedStatic<CustomerName> customerNameMockedStatic =
                mockStatic(CustomerName.class);
        when(CustomerName.create(anyString())).thenReturn(customerNameMock);

        ImplCustomerFactory customerFactoryMock =
                mock(ImplCustomerFactory.class);
        Customer customerMock = mock(Customer.class);
        when(customerFactoryMock.createCustomer(
                any(CustomerName.class))).thenReturn(customerMock);

        ImplCustomerAssemblerJPA customerAssemblerJPA =
                new ImplCustomerAssemblerJPA(customerFactoryMock);

        //Act
        Customer actual =
                customerAssemblerJPA.customerJpaToCustomer(customerJPAMock);

        //Assert
        Assertions.assertEquals(customerMock, actual);
        customerNameMockedStatic.close();
    }

    @Test
    void customerToCustomerJPA() {
        //Arrange
        String jpaName = "Ferrari";
        Customer customerMock = mock(Customer.class);
        when(customerMock.getCustomerName()).thenReturn(jpaName);

        CustomerJPA expected = mock(CustomerJPA.class);

        MockedStatic<CustomerJPA> customerJPAMockedStatic =
                mockStatic(CustomerJPA.class);

        when(CustomerJPA.createCustomerJpa(anyString())).thenReturn(expected);

        ImplCustomerFactory customerFactoryMock =
                mock(ImplCustomerFactory.class);
        ImplCustomerAssemblerJPA customerAssemblerJPA =
                new ImplCustomerAssemblerJPA(customerFactoryMock);

        //Act
        CustomerJPA actual =
                customerAssemblerJPA.customerToCustomerJPA(customerMock);

        //Assert
        assertEquals(expected, actual);
        customerJPAMockedStatic.close();
    }


}
