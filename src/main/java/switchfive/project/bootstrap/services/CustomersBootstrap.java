package switchfive.project.bootstrap.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switchfive.project.applicationServices.appServices.iappServices.IAppCustomerService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Component
public class CustomersBootstrap {

    private static final Logger LOG = LoggerFactory.getLogger(CustomersBootstrap.class);

    private IAppCustomerService customerService;


    @Autowired
    public CustomersBootstrap(IAppCustomerService customerService) {
        this.customerService = customerService;
    }


    public void execute() {
        LOG.info("Loading customers ...");
        loadProfiles();
        LOG.info("Customers loaded");
    }


    public void loadProfiles() {

        String dir = System.getProperty("user.dir");
        String file = dir + "/docs/database_loading/customers.csv";
        BufferedReader reader = null;
        String line;

        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                customerService.createAndSaveCustomer(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert reader != null;
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
