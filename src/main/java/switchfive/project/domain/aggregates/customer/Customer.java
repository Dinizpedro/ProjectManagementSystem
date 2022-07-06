package switchfive.project.domain.aggregates.customer;

import switchfive.project.domain.shared.dddTypes.AggregateRoot;
import switchfive.project.domain.shared.valueObjects.CustomerName;

import java.util.Objects;

public class Customer implements AggregateRoot<Customer> {

    private final CustomerName customerName;

    public Customer(CustomerName customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName.getName();
    }

    public boolean sameIdentityAs(Customer other) {
        return this.customerName.sameValueAs(other.customerName);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Customer customer = (Customer) object;
        return sameIdentityAs(customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerName);
    }

}
