package switchfive.project.applicationServices.iRepositories;

import switchfive.project.domain.aggregates.customer.Customer;
import switchfive.project.domain.shared.valueObjects.CustomerName;

import java.util.List;
import java.util.Optional;

public interface ICustomerRepository {

    Optional<Customer> findCustomerByName(CustomerName customerName);

    void saveCustomer(Customer customer);

    boolean customerExists(CustomerName customerName);

    List<Customer> findAll();
}
