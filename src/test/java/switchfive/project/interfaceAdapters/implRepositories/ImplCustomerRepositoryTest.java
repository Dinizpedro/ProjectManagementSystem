
package switchfive.project.interfaceAdapters.implRepositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import switchfive.project.assemblers.assemblersJPA.iAssemblersJPA.ICustomerAssemblerJPA;
import switchfive.project.dataModel.dataJPA.CustomerJPA;
import switchfive.project.infrastructure.persistence.repositories.iRepositoriesJPA.ICustomerRepositoryJPA;
import switchfive.project.domain.aggregates.customer.Customer;
import switchfive.project.domain.shared.valueObjects.CustomerName;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ImplCustomerRepositoryTest {

    @Test
    void saveCustomer() {
        //Arrange
        Customer customerMock = mock(Customer.class);
        CustomerJPA customerJPAMock = mock(CustomerJPA.class);

        ICustomerRepositoryJPA customerRepositoryJpaMock =
                mock(ICustomerRepositoryJPA.class);
        ICustomerAssemblerJPA customerAssemblerJPAMock =
                mock(ICustomerAssemblerJPA.class);

        ImplCustomerRepository customerRepository =
                new ImplCustomerRepository(customerRepositoryJpaMock,
                        customerAssemblerJPAMock);

        when(customerAssemblerJPAMock.customerToCustomerJPA(
                any(Customer.class))).thenReturn(customerJPAMock);

        when(customerRepositoryJpaMock.save(any(CustomerJPA.class))).thenReturn(
                customerJPAMock);

        //Act
        customerRepository.saveCustomer(customerMock);

        //Assert
        verify(customerAssemblerJPAMock, times(1)).customerToCustomerJPA(
                any(Customer.class));
        verify(customerRepositoryJpaMock, times(1)).save(
                any(CustomerJPA.class));
    }

    @Test
    void findCustomerByName_NameDoesNotExists() {
        // Arrange
        ICustomerRepositoryJPA customerRepositoryJpaMock =
                mock(ICustomerRepositoryJPA.class);
        ICustomerAssemblerJPA customerAssemblerJPAMock =
                mock(ICustomerAssemblerJPA.class);

        ImplCustomerRepository customerRepository =
                new ImplCustomerRepository(customerRepositoryJpaMock,
                        customerAssemblerJPAMock);

        when(customerRepositoryJpaMock.existsByName(
                anyString())).thenReturn(false);

        // Act
        Optional<Customer> actual =
                customerRepository.findCustomerByName(CustomerName.create(
                        "customer"));

        // Assert
        Assertions.assertEquals(Optional.empty(), actual);
    }

    @Test
    void findCustomerByName_NameExists() {
        // Arrange
        Customer customerMock = mock(Customer.class);
        Optional<CustomerJPA> customerJPAMock =
                Optional.of(mock(CustomerJPA.class));

        ICustomerRepositoryJPA customerRepositoryJpaMock =
                mock(ICustomerRepositoryJPA.class);
        ICustomerAssemblerJPA customerAssemblerJPAMock =
                mock(ICustomerAssemblerJPA.class);

        ImplCustomerRepository customerRepository =
                new ImplCustomerRepository(customerRepositoryJpaMock,
                        customerAssemblerJPAMock);

        when(customerRepositoryJpaMock.existsByName(
                anyString())).thenReturn(true);

        when(customerRepositoryJpaMock.findByName(anyString())).thenReturn(
                customerJPAMock);

        when(customerAssemblerJPAMock.customerJpaToCustomer(
                any(CustomerJPA.class))).thenReturn(customerMock);

        // Act
        Optional<Customer> actual =
                customerRepository.findCustomerByName(CustomerName.create(
                        "customer"));

        // Assert
        Assertions.assertEquals(Optional.ofNullable(customerMock), actual);
    }

    @Test
    void existsName() {
        //Arrange
        ICustomerRepositoryJPA customerRepositoryJpaMock =
                mock(ICustomerRepositoryJPA.class);
        ICustomerAssemblerJPA customerAssemblerJPAMock =
                mock(ICustomerAssemblerJPA.class);

        ImplCustomerRepository customerRepository =
                new ImplCustomerRepository(customerRepositoryJpaMock,
                        customerAssemblerJPAMock);

        when(customerRepositoryJpaMock.existsByName(anyString())).thenReturn(
                true);

        //Act
        boolean actual = customerRepository.customerExists(CustomerName.create(
                "customer"));

        //Assert
        Assertions.assertTrue(actual);
    }

    @Test
    void findAllCustomersSuccessfully() {
        //Arrange
        ICustomerRepositoryJPA customerRepositoryJpaMock =
                mock(ICustomerRepositoryJPA.class);
        ICustomerAssemblerJPA customerAssemblerJPAMock =
                mock(ICustomerAssemblerJPA.class);
        Customer customerMock = mock(Customer.class);
        CustomerJPA customerJPAMock = mock(CustomerJPA.class);

        ImplCustomerRepository customerRepository =
                new ImplCustomerRepository(customerRepositoryJpaMock,
                        customerAssemblerJPAMock);

        List<CustomerJPA> customerJPAList = new ArrayList<>();
        customerJPAList.add(customerJPAMock);

        List<Customer> customerList = new ArrayList<>();
        customerList.add(customerMock);

        when(customerRepositoryJpaMock.findAll()).thenReturn(customerJPAList);
        when(customerAssemblerJPAMock.customerJpaToCustomer(any(CustomerJPA.class))).thenReturn(customerMock);

        //Act
        List<Customer> expected = customerRepository.findAll();

        //Assert
        Assertions.assertEquals(expected,customerList);
    }

    @Test
    void findAllCustomersEmpty() {
        //Arrange
        ICustomerRepositoryJPA customerRepositoryJpaMock =
                mock(ICustomerRepositoryJPA.class);
        ICustomerAssemblerJPA customerAssemblerJPAMock =
                mock(ICustomerAssemblerJPA.class);
        Customer customerMock = mock(Customer.class);

        ImplCustomerRepository customerRepository =
                new ImplCustomerRepository(customerRepositoryJpaMock,
                        customerAssemblerJPAMock);

        List<CustomerJPA> customerJPAList = new ArrayList<>();

        List<Customer> customerList = new ArrayList<>();

        when(customerRepositoryJpaMock.findAll()).thenReturn(customerJPAList);
        when(customerAssemblerJPAMock.customerJpaToCustomer(any(CustomerJPA.class))).thenReturn(customerMock);

        //Act
        List<Customer> expected = customerRepository.findAll();

        //Assert
        Assertions.assertEquals(expected,customerList);
    }
}

