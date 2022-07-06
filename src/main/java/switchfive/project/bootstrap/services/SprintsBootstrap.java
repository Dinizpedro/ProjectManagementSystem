package switchfive.project.bootstrap.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switchfive.project.dtos.SprintCreationDTO;
import switchfive.project.applicationServices.appServices.iappServices.IAppSprintService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Component
public class SprintsBootstrap {

    private static final Logger LOG = LoggerFactory.getLogger(SprintsBootstrap.class);

    private IAppSprintService sprintService;


    @Autowired
    public SprintsBootstrap(IAppSprintService sprintService) {
        this.sprintService = sprintService;
    }


    public void execute() {
        LOG.info("Loading sprints ...");
        loadProfiles();
        LOG.info("Sprints loaded");
    }

    public void loadProfiles() {

        String dir = System.getProperty("user.dir");
        String file = dir + "/docs/database_loading/sprints.csv";
        BufferedReader reader = null;
        String line;

        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                sprintService.createAndSaveSprint(addSprint(row),row[0]);
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

    private SprintCreationDTO addSprint(final String[] input) {
        SprintCreationDTO sprintDTO = new SprintCreationDTO();

        sprintDTO.setStartDate(input[1]);
        sprintDTO.setEndDate(input[2]);
        sprintDTO.setDescription(input[3]);

        return sprintDTO;
    }
}
