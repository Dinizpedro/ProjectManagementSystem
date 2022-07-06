package switchfive.project.domain.factories.implFactories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switchfive.project.domain.aggregates.customer.Customer;
import switchfive.project.domain.factories.iFactories.ICustomerFactory;
import switchfive.project.domain.shared.valueObjects.CustomerName;

@Component
public class ImplCustomerFactory implements ICustomerFactory {

    @Autowired
    public ImplCustomerFactory() {
    }

    public Customer createCustomer(CustomerName customerName) {
        return new Customer(customerName);
    }
}
