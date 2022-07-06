package switchfive.project.applicationServices.assemblers.iAssemblers;

import switchfive.project.dtos.CustomerDTO;
import switchfive.project.domain.aggregates.customer.Customer;

public interface ICustomerAssembler {
    CustomerDTO toDTO(Customer customer);
}
