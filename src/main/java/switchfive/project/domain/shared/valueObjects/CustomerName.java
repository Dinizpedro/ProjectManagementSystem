package switchfive.project.domain.shared.valueObjects;

import switchfive.project.domain.shared.dddTypes.ID;

import java.util.Locale;
import java.util.Objects;

public class CustomerName implements ID<CustomerName> {

    private final String name;

    private CustomerName(final String customerName) {
        if (isValid(customerName)) {
            this.name = customerName;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static CustomerName create(final String customerName) {
        return new CustomerName(customerName);
    }

    public String getName() {
        return name;
    }

    private boolean isValid(final String name) {
        final int MINIMUM_SIZE = 3;
        final int MAXIMUM_SIZE = 50;
        final int NAME_LENGTH = name.trim().length();

        return NAME_LENGTH >= MINIMUM_SIZE && NAME_LENGTH <= MAXIMUM_SIZE;
    }

    public boolean sameValueAs(CustomerName other) {
        return other != null && name.toLowerCase(Locale.ROOT).trim()
                .equals(other.name.toLowerCase(Locale.ROOT).trim());
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof CustomerName)) {
            return false;
        }
        CustomerName customerName = (CustomerName) object;
        return sameValueAs(customerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase(Locale.ROOT).trim());
    }

}

