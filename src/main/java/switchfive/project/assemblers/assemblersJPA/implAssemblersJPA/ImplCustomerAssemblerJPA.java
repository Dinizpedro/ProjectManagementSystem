package switchfive.project.assemblers.assemblersJPA.implAssemblersJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switchfive.project.assemblers.assemblersJPA.iAssemblersJPA.ICustomerAssemblerJPA;
import switchfive.project.dataModel.dataJPA.CustomerJPA;
import switchfive.project.domain.aggregates.customer.Customer;
import switchfive.project.domain.factories.iFactories.ICustomerFactory;
import switchfive.project.domain.shared.valueObjects.CustomerName;

@Component
public class ImplCustomerAssemblerJPA implements ICustomerAssemblerJPA {

    ICustomerFactory customerFactory;

    @Autowired
    public ImplCustomerAssemblerJPA(ICustomerFactory customerFactory) {
        this.customerFactory = customerFactory;
    }

    public Customer customerJpaToCustomer(CustomerJPA customerJPA) {
        String jpaName = customerJPA.getName();
        CustomerName name = CustomerName.create(jpaName);

        return this.customerFactory.createCustomer(name);
    }

    public CustomerJPA customerToCustomerJPA(Customer customer) {
        String name = customer.getCustomerName();

        return CustomerJPA.createCustomerJpa(name);
    }
}
