package switchfive.project.assemblers.assemblersJPA.iAssemblersJPA;

import switchfive.project.dataModel.dataJPA.CustomerJPA;
import switchfive.project.domain.aggregates.customer.Customer;

public interface ICustomerAssemblerJPA {

    Customer customerJpaToCustomer (CustomerJPA customerJPA);

    CustomerJPA customerToCustomerJPA (Customer customer);

}
