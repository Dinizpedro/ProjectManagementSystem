package switchfive.project.applicationServices.assemblers.implAssemblers;

import org.junit.jupiter.api.Test;
import switchfive.project.dtos.CustomerDTO;
import switchfive.project.domain.aggregates.customer.Customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ImplCustomerAssemblerTest {

    @Test
    void toDTO() {
        //Arrange
        Customer customerMock = mock(Customer.class);
        CustomerDTO customerDTOMock = mock(CustomerDTO.class);

        ImplCustomerAssembler customerAssembler = new ImplCustomerAssembler();
        customerAssembler.setCustomerDTO(customerDTOMock);

        String name = "Daniel";
        when(customerMock.getCustomerName()).thenReturn(name);
        doNothing().when(customerDTOMock).setCustomerName(anyString());

        //Act
        CustomerDTO actual = customerAssembler.toDTO(customerMock);

        //Assert
        assertEquals(customerDTOMock, actual);
        verify(customerDTOMock, times(1)).setCustomerName(anyString());
    }
}
