package switchfive.project.applicationServices.appServices.implAppServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchfive.project.dtos.CustomerDTO;
import switchfive.project.applicationServices.appServices.iappServices.IAppCustomerService;
import switchfive.project.applicationServices.iRepositories.ICustomerRepository;
import switchfive.project.domain.aggregates.customer.Customer;
import switchfive.project.domain.factories.iFactories.ICustomerFactory;
import switchfive.project.domain.shared.valueObjects.CustomerName;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImplAppCustomerService implements IAppCustomerService {

    private final ICustomerFactory customerFactory;
    private final ICustomerRepository customerRepository;

    @Autowired
    public ImplAppCustomerService(ICustomerRepository customerRepository,
                                  ICustomerFactory customerFactory) {
        this.customerFactory = customerFactory;
        this.customerRepository = customerRepository;
    }

    public Optional<Customer> findCustomerByDescription(String description) {
        CustomerName name = CustomerName.create(description);

        return customerRepository.findCustomerByName(name);
    }

    public Customer createAndSaveCustomer(String name) {
        CustomerName customerName = CustomerName.create(name);

        if (customerRepository.customerExists(customerName)) {
            throw new IllegalArgumentException("Customer already exists");
        }

        Customer newCustomer =
                this.customerFactory.createCustomer(customerName);

        this.customerRepository.saveCustomer(newCustomer);

        return newCustomer;
    }

    public List<CustomerDTO> getAllCustomers() {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        List<Customer> customerList = this.customerRepository.findAll();

        for (Customer each : customerList) {
            CustomerDTO dto = new CustomerDTO(each.getCustomerName());
            customerDTOList.add(dto);
        }

        return customerDTOList;
    }
}
