package switchfive.project.interfaceAdapters.controllers.implControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switchfive.project.interfaceAdapters.controllers.iControllers.ICustomerController;
import switchfive.project.dtos.CustomerDTO;
import switchfive.project.applicationServices.appServices.iappServices.IAppCustomerService;
import switchfive.project.applicationServices.assemblers.iAssemblers.ICustomerAssembler;
import switchfive.project.domain.aggregates.customer.Customer;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api")
public class ImplCustomerController implements ICustomerController {
    private final String WRONG_INFORMATION = "Wrong information";
    private final String CUSTOMER_ALREADY_EXISTS = "Customer already exists";
    private final String CUSTOMER_DOES_NOT_EXIST = "Customer does not exist";
    private final IAppCustomerService appCustomerService;
    private final ICustomerAssembler customerAssembler;

    @Autowired
    public ImplCustomerController(IAppCustomerService appCustomerService,
                                  ICustomerAssembler customerAssembler) {
        this.appCustomerService = appCustomerService;
        this.customerAssembler = customerAssembler;
    }

    @GetMapping(value = "/customers/{name}")
    public ResponseEntity<Object> getCustomer(@PathVariable String name) {
        try {
            Optional<Customer> customer =
                    this.appCustomerService.findCustomerByDescription(name);

            if (customer.isEmpty()) {
                return new ResponseEntity<>(CUSTOMER_DOES_NOT_EXIST,
                        HttpStatus.NOT_FOUND);
            }

            CustomerDTO customerDTO =
                    this.customerAssembler.toDTO(customer.get());

            return new ResponseEntity<>(customerDTO, HttpStatus.OK);

        } catch (IllegalArgumentException exception) {
            return new ResponseEntity<>(WRONG_INFORMATION,
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/customers")
    public ResponseEntity<Object> getAllCustomers() throws ParseException, NoSuchAlgorithmException {
        Link link;

        List<CustomerDTO> dto = this.appCustomerService.getAllCustomers();

        for (CustomerDTO each : dto) {
            link = linkTo(methodOn(ImplCustomerController.class).
                    getCustomer(each.getCustomerName())).withSelfRel();
            each.add(link);
        }

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping(value = "/customers")
    public ResponseEntity<Object> createAndSaveCustomer(
            @RequestBody final CustomerDTO dto) {
        try {

            String customerName = dto.getCustomerName();

            Optional<Customer> customer =
                    this.appCustomerService.findCustomerByDescription(
                            customerName);

            if (customer.isPresent()) {
                return new ResponseEntity<>(CUSTOMER_ALREADY_EXISTS,
                        HttpStatus.OK);
            }

            Customer newCustomer =
                    this.appCustomerService.createAndSaveCustomer(customerName);

            CustomerDTO customerDTO = this.customerAssembler.toDTO(newCustomer);

            customerDTO.add(linkTo(methodOn(ImplCustomerController.class).
                    getCustomer(customerName)).withSelfRel());

            return new ResponseEntity<>(customerDTO, HttpStatus.CREATED);

        } catch (IllegalArgumentException exception) {

            return new ResponseEntity<>(WRONG_INFORMATION,
                    HttpStatus.BAD_REQUEST);
        }
    }
}
