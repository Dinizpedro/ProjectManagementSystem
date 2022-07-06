package switchfive.project.applicationServices.appServices.iappServices;

import switchfive.project.dtos.CustomerDTO;
import switchfive.project.domain.aggregates.customer.Customer;

import java.util.List;
import java.util.Optional;

public interface IAppCustomerService {

    Optional<Customer> findCustomerByDescription(String description);

    Customer createAndSaveCustomer(String name);

    List<CustomerDTO> getAllCustomers();

}
