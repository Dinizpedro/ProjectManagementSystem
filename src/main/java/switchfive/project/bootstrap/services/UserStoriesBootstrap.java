package switchfive.project.bootstrap.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switchfive.project.applicationServices.appServices.iappServices.IAppUserStoryService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Component
public class UserStoriesBootstrap {

    private static final Logger LOG = LoggerFactory.getLogger(UserStoriesBootstrap.class);

    private IAppUserStoryService userStoryService;


    @Autowired
    public UserStoriesBootstrap(IAppUserStoryService userStoryService) {
        this.userStoryService = userStoryService;
    }


    public void execute() {
        LOG.info("Loading user stories ...");
        loadProfiles();
        LOG.info("User stories loaded");
    }

    public void loadProfiles() {
        String dir = System.getProperty("user.dir");
        String file = dir + "/docs/database_loading/userStories.csv";
        BufferedReader reader = null;
        String line;

        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                userStoryService.createAndAddUserStory(row[0], row[1]);
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
