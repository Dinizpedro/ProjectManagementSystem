package switchfive.project.applicationServices.appServices.implAppServices;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchfive.project.dtos.CustomerDTO;
import switchfive.project.interfaceAdapters.implRepositories.ImplCustomerRepository;
import switchfive.project.domain.aggregates.customer.Customer;
import switchfive.project.domain.factories.implFactories.ImplCustomerFactory;
import switchfive.project.domain.shared.valueObjects.CustomerName;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImplAppCustomerServiceTest {

    @Mock
    CustomerDTO customerDTO;

    @Mock
    Customer customer;

    @Mock
    ImplCustomerFactory customerFactory;

    @Mock
     ImplCustomerRepository customerRepository;

    @InjectMocks
    ImplAppCustomerService customerService;

    @Test
    void findCustomerByDescriptionSuccess() {
        //Arrange
        ImplCustomerRepository customerRepositoryMock =
                mock(ImplCustomerRepository.class);
        ImplCustomerFactory customerFactoryMock =
                mock(ImplCustomerFactory.class);

        ImplAppCustomerService customerService =
                new ImplAppCustomerService(customerRepositoryMock,
                        customerFactoryMock);

        Optional<Customer> customerMock = Optional.of(mock(Customer.class));

        when(customerRepositoryMock.findCustomerByName(
                any(CustomerName.class))).thenReturn(customerMock);

        //Act
        Optional<Customer> actual =
                customerService.findCustomerByDescription("customer");

        //Act
        Assertions.assertEquals(customerMock, actual);
    }

    @Test
    void createAndSaveCustomerFails() {
        //Arrange
        ImplCustomerRepository customerRepositoryMock =
                mock(ImplCustomerRepository.class);
        ImplCustomerFactory customerFactoryMock =
                mock(ImplCustomerFactory.class);

        ImplAppCustomerService customerService =
                new ImplAppCustomerService(customerRepositoryMock,
                        customerFactoryMock);

        when(customerRepositoryMock.customerExists(
                any(CustomerName.class))).thenReturn(true);

        //Act

        //Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            customerService.createAndSaveCustomer("customer");
        });
    }

    @Test
    void createAndSaveCustomerSuccess() {
        //Arrange
        Customer customerMock = mock(Customer.class);

        ImplCustomerRepository customerRepositoryMock =
                mock(ImplCustomerRepository.class);
        ImplCustomerFactory customerFactoryMock =
                mock(ImplCustomerFactory.class);

        ImplAppCustomerService customerService =
                new ImplAppCustomerService(customerRepositoryMock,
                        customerFactoryMock);

        when(customerRepositoryMock.customerExists(
                any(CustomerName.class))).thenReturn(false);

        when(customerFactoryMock.createCustomer(
                any(CustomerName.class))).thenReturn(customerMock);

        //Act
        Customer actual = customerService.createAndSaveCustomer("customer");

        //Assert
        Assertions.assertEquals(customerMock, actual);
        verify(customerRepositoryMock, times(1)).saveCustomer(
                any(Customer.class));
    }

    @Test
    void getAllCustomers() {
        //Arrange
        List<Customer> customers = new ArrayList<>();
        List<CustomerDTO> customersDTO = new ArrayList<>();

        when(customerRepository.findAll()).thenReturn(customers);

        // Assert
        Assertions.assertEquals(customerService.getAllCustomers(),customersDTO);
    }

}
