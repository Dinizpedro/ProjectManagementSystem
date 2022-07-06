package switchfive.project.bootstrap.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switchfive.project.applicationServices.appServices.iappServices.IAppProfileService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Component
public class ProfilesBootstrap {

    private static final Logger LOG = LoggerFactory.getLogger(ProfilesBootstrap.class);

    private IAppProfileService createProfileService;


    @Autowired
    public ProfilesBootstrap(IAppProfileService createProfileService) {
        this.createProfileService = createProfileService;
    }


    public void execute() {
        LOG.info("Loading Profiles ...");
        loadProfiles();
        LOG.info("Profiles loaded");
    }


    public void loadProfiles() {

        String dir = System.getProperty("user.dir");
        String file = dir + "/docs/database_loading/profiles.csv";
        BufferedReader reader = null;
        String line;

        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                createProfileService.addNewProfile(line);
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
