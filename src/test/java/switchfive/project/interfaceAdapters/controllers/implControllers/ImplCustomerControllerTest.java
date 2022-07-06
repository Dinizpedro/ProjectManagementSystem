package switchfive.project.interfaceAdapters.controllers.implControllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import switchfive.project.dtos.CustomerDTO;
import switchfive.project.applicationServices.appServices.implAppServices.ImplAppCustomerService;
import switchfive.project.applicationServices.assemblers.implAssemblers.ImplCustomerAssembler;
import switchfive.project.domain.aggregates.customer.Customer;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

class ImplCustomerControllerTest {
    final String WRONG_INFORMATION = "Wrong information";
    final String CUSTOMER_ALREADY_EXISTS = "Customer already exists";
    final String CUSTOMER_DOES_NOT_EXIST = "Customer does not exist";

    @Test
    void getCustomerWhenCustomerIsNotFound() {
        //Arrange
        ImplAppCustomerService customerServiceMock =
                mock(ImplAppCustomerService.class);
        ImplCustomerAssembler customerAssemblerMock =
                mock(ImplCustomerAssembler.class);
        ImplCustomerController customerController =
                new ImplCustomerController(customerServiceMock,
                        customerAssemblerMock);

        when(customerServiceMock.findCustomerByDescription(
                anyString())).thenReturn(Optional.empty());

        ResponseEntity<Object> expected =
                new ResponseEntity<>(CUSTOMER_DOES_NOT_EXIST,
                        HttpStatus.NOT_FOUND);

        //Act
        ResponseEntity<Object> actual = customerController.getCustomer(
                "Ferrari");

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getCustomerSuccess() {
        //Arrange
        ImplAppCustomerService customerServiceMock =
                mock(ImplAppCustomerService.class);
        ImplCustomerAssembler customerAssemblerMock =
                mock(ImplCustomerAssembler.class);
        ImplCustomerController customerController =
                new ImplCustomerController(customerServiceMock,
                        customerAssemblerMock);

        Optional<Customer> customerMock = Optional.of(mock(Customer.class));

        CustomerDTO customerDTOMock = mock(CustomerDTO.class);

        when(customerServiceMock.findCustomerByDescription(
                anyString())).thenReturn(customerMock);

        when(customerAssemblerMock.toDTO(customerMock.get())).thenReturn(
                customerDTOMock);

        ResponseEntity<Object> expected =
                new ResponseEntity<>(customerDTOMock,
                        HttpStatus.OK);

        //Act
        ResponseEntity<Object> actual = customerController.getCustomer(
                "Ferrari");

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getCustomerFails() {
        //Arrange
        ImplAppCustomerService customerServiceMock =
                mock(ImplAppCustomerService.class);
        ImplCustomerAssembler customerAssemblerMock =
                mock(ImplCustomerAssembler.class);
        ImplCustomerController customerController =
                new ImplCustomerController(customerServiceMock,
                        customerAssemblerMock);

        when(customerServiceMock.findCustomerByDescription(
                anyString())).thenThrow(new IllegalArgumentException());

        ResponseEntity<Object> expected =
                new ResponseEntity<>(WRONG_INFORMATION,
                        HttpStatus.BAD_REQUEST);

        //Act
        ResponseEntity<Object> actual = customerController.getCustomer(
                "invalidName");

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void createAndSaveCustomerWhenCustomerAlreadyExists() {
        //Arrange
        ImplAppCustomerService customerServiceMock =
                mock(ImplAppCustomerService.class);
        ImplCustomerAssembler customerAssemblerMock =
                mock(ImplCustomerAssembler.class);
        ImplCustomerController customerController =
                new ImplCustomerController(customerServiceMock,
                        customerAssemblerMock);

        Optional<Customer> customerMock = Optional.of(mock(Customer.class));

        when(customerServiceMock.findCustomerByDescription(
                anyString())).thenReturn(customerMock);

        ResponseEntity<Object> expected =
                new ResponseEntity<>(CUSTOMER_ALREADY_EXISTS,
                        HttpStatus.OK);

        //Act
        ResponseEntity<Object> actual =
                customerController.createAndSaveCustomer(new CustomerDTO(
                        "someName"));

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void createAndSaveCustomerSuccess() {
        //Arrange
        ImplAppCustomerService customerServiceMock =
                mock(ImplAppCustomerService.class);
        ImplCustomerAssembler customerAssemblerMock =
                mock(ImplCustomerAssembler.class);
        ImplCustomerController customerController =
                new ImplCustomerController(customerServiceMock,
                        customerAssemblerMock);

        Customer customerMock = mock(Customer.class);

        CustomerDTO customerDTOMock = mock(CustomerDTO.class);

        when(customerServiceMock.findCustomerByDescription(
                anyString())).thenReturn(Optional.empty());

        when(customerServiceMock.createAndSaveCustomer(anyString())).thenReturn(
                customerMock);

        when(customerAssemblerMock.toDTO(customerMock)).thenReturn(
                customerDTOMock);

        when(customerDTOMock.add(any(Link.class))).thenReturn(customerDTOMock);

        ResponseEntity<Object> expected =
                new ResponseEntity<>(customerDTOMock, HttpStatus.CREATED);

        //Act
        ResponseEntity<Object> actual =
                customerController.createAndSaveCustomer(new CustomerDTO(
                        "someName"));

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void createAndSaveCustomerFails() {
        //Arrange
        ImplAppCustomerService customerServiceMock =
                mock(ImplAppCustomerService.class);
        ImplCustomerAssembler customerAssemblerMock =
                mock(ImplCustomerAssembler.class);
        ImplCustomerController customerController =
                new ImplCustomerController(customerServiceMock,
                        customerAssemblerMock);

        when(customerServiceMock.findCustomerByDescription(
                anyString())).thenThrow(new IllegalArgumentException());

        ResponseEntity<Object> expected =
                new ResponseEntity<>(WRONG_INFORMATION,
                        HttpStatus.BAD_REQUEST);

        //Act
        ResponseEntity<Object> actual =
                customerController.createAndSaveCustomer(new CustomerDTO(
                        "invalidName"));

        //Assert
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void getAllCustomersSuccess() throws ParseException, NoSuchAlgorithmException {
        //Arrange
        ImplAppCustomerService customerServiceMock =
                mock(ImplAppCustomerService.class);
        ImplCustomerAssembler customerAssemblerMock =
                mock(ImplCustomerAssembler.class);
        ImplCustomerController customerController =
                new ImplCustomerController(customerServiceMock,
                        customerAssemblerMock);

        List<CustomerDTO> customerDTOList = new ArrayList<>();
        CustomerDTO customerDTO = new CustomerDTO("Armis");
        Link expectedLink = linkTo(methodOn(ImplCustomerController.class)
                .getCustomer(customerDTO.getCustomerName())).withSelfRel();
        customerDTO.add(expectedLink);
        customerDTOList.add(customerDTO);

        when(customerServiceMock.getAllCustomers()).thenReturn(customerDTOList);

        ResponseEntity expectedResponseEntity =
                new ResponseEntity<>(customerDTOList,
                        HttpStatus.OK);

        // Act
        ResponseEntity resultResponseEntity =
                customerController.getAllCustomers();

        // Assert
        assertEquals(expectedResponseEntity, resultResponseEntity);
    }

}
