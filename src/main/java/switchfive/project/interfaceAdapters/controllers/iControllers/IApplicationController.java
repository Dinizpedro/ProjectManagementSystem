package switchfive.project.interfaceAdapters.controllers.iControllers;

import org.springframework.http.ResponseEntity;

import javax.net.ssl.SSLException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

public interface IApplicationController {

    ResponseEntity<Object> applicationOptionsMapping() throws ParseException, NoSuchAlgorithmException, SSLException;
}
