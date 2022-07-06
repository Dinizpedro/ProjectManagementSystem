package switchfive.project.applicationServices.assemblers.implAssemblers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switchfive.project.dtos.CustomerDTO;
import switchfive.project.applicationServices.assemblers.iAssemblers.ICustomerAssembler;
import switchfive.project.domain.aggregates.customer.Customer;

@Component
public class ImplCustomerAssembler implements ICustomerAssembler {
    CustomerDTO customerDTO;

    @Autowired
    public ImplCustomerAssembler() {
        customerDTO = new CustomerDTO();
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public CustomerDTO toDTO(Customer customer) {
        String customerName = customer.getCustomerName();

        customerDTO.setCustomerName(customerName);

        return customerDTO;
    }

}
