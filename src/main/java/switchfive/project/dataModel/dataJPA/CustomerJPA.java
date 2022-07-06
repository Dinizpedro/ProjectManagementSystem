package switchfive.project.dataModel.dataJPA;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class CustomerJPA implements JPA {
    /*@SequenceGenerator(name = "customer_sequence",
            sequenceName = "customer_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence")
    @Id
    private long identity;*/
    @Id
    private String name;

    public CustomerJPA() {
    }

    public static CustomerJPA createCustomerJpa(final String name) {
        CustomerJPA newCustomerJPA = new CustomerJPA();
        newCustomerJPA.setName(name);
        return newCustomerJPA;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    /*public long getIdentity() {
        return identity;
    }

    public void setIdentity(long identity) {
        this.identity = identity;
    }*/

}
