package switchfive.project.interfaceAdapters.implRepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switchfive.project.assemblers.assemblersJPA.iAssemblersJPA.ICustomerAssemblerJPA;
import switchfive.project.dataModel.dataJPA.CustomerJPA;
import switchfive.project.infrastructure.persistence.repositories.iRepositoriesJPA.ICustomerRepositoryJPA;
import switchfive.project.applicationServices.iRepositories.ICustomerRepository;
import switchfive.project.domain.aggregates.customer.Customer;
import switchfive.project.domain.shared.valueObjects.CustomerName;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ImplCustomerRepository implements ICustomerRepository {
    private final ICustomerRepositoryJPA customerRepositoryJPA;
    private final ICustomerAssemblerJPA customerAssemblerJPA;

    @Autowired
    public ImplCustomerRepository(ICustomerRepositoryJPA customerRepositoryJPA,
                                  ICustomerAssemblerJPA customerAssemblerJPA) {
        this.customerRepositoryJPA = customerRepositoryJPA;
        this.customerAssemblerJPA = customerAssemblerJPA;
    }

    public void saveCustomer(Customer customer) {
        CustomerJPA entityJPA =
                this.customerAssemblerJPA.customerToCustomerJPA(customer);

        this.customerRepositoryJPA.save(entityJPA);
    }

    public Optional<Customer> findCustomerByName(CustomerName customerName) {
        CustomerJPA customerJPA;

        if (this.customerRepositoryJPA.findByName(customerName.getName())
                .isPresent()) {
            customerJPA =
                    this.customerRepositoryJPA.findByName(
                            customerName.getName()).get();

            Customer customer =
                    this.customerAssemblerJPA.customerJpaToCustomer(
                            customerJPA);

            return Optional.ofNullable(customer);
        }

        return Optional.empty();
    }

    public boolean customerExists(CustomerName customerName) {
        return this.customerRepositoryJPA.existsByName(customerName.getName());
    }

    public List<Customer> findAll() {
        List<Customer> customerList = new ArrayList<>();
        List<CustomerJPA> customerJPAList =
                this.customerRepositoryJPA.findAll();

        for (CustomerJPA each : customerJPAList) {
            customerList.add(this.customerAssemblerJPA.customerJpaToCustomer(each));
        }

        return customerList;
    }
}
