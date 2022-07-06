package switchfive.project.interfaceAdapters.controllers.iControllers;

import org.springframework.http.ResponseEntity;
import switchfive.project.dtos.CustomerDTO;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

public interface ICustomerController {

    ResponseEntity<Object> getCustomer(String customerCode);

    ResponseEntity<Object> createAndSaveCustomer(CustomerDTO dto);

    ResponseEntity<Object> getAllCustomers() throws ParseException, NoSuchAlgorithmException;

}
