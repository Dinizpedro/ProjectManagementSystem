package switchfive.project.dtos;

import org.springframework.hateoas.RepresentationModel;

public class CustomerDTO extends RepresentationModel<CustomerDTO> {

    private String customerName;

    public CustomerDTO() {
    }

    public CustomerDTO(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
