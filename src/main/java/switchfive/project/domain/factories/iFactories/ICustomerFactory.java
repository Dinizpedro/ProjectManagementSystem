package switchfive.project.domain.factories.iFactories;

import switchfive.project.domain.aggregates.customer.Customer;
import switchfive.project.domain.shared.valueObjects.CustomerName;

public interface ICustomerFactory {

    Customer createCustomer(CustomerName name);

}
