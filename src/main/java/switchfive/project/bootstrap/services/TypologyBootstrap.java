package switchfive.project.bootstrap.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switchfive.project.applicationServices.appServices.iappServices.IAppTypologyService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Component
public class TypologyBootstrap {

    private static final Logger LOG = LoggerFactory.getLogger(TypologyBootstrap.class);

    private IAppTypologyService typologyService;


    @Autowired
    public TypologyBootstrap(IAppTypologyService typologyService) {
        this.typologyService = typologyService;
    }


    public void execute() {
        LOG.info("Loading typologies ...");
        loadProfiles();
        LOG.info("Typologies loaded");
    }


    public void loadProfiles() {

        String dir = System.getProperty("user.dir");
        String file = dir + "/docs/database_loading/typologies.csv";
        BufferedReader reader = null;
        String line;

        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                typologyService.addNewTypology(line);
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
