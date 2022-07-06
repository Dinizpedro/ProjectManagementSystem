package switchfive.project.bootstrap.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switchfive.project.dtos.UpdateProjectDTO;
import switchfive.project.applicationServices.appServices.iappServices.IAppProjectService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Component
public class UpdateProjectsBootstrap {

    private static final Logger LOG = LoggerFactory.getLogger(UpdateProjectsBootstrap.class);

    private IAppProjectService projectService;


    @Autowired
    public UpdateProjectsBootstrap(IAppProjectService projectService) {
        this.projectService = projectService;
    }


    public void execute() {
        LOG.info("Updating projects ...");
        loadProfiles();
        LOG.info("Projects updated (closed)");
    }


    public void loadProfiles() {

        String dir = System.getProperty("user.dir");
        String file = dir + "/docs/database_loading/updateProjects.csv";
        BufferedReader reader = null;
        String line;

        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                this.projectService.updateProject(row[0], updateProject(row));
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

    private UpdateProjectDTO updateProject(String[] input) {
        UpdateProjectDTO updateProjectDTO = new UpdateProjectDTO();

        updateProjectDTO.setProjectName(input[1]);
        updateProjectDTO.setProjectDescription(input[2]);
        updateProjectDTO.setStartDate(input[3]);
        updateProjectDTO.setProjectSprintDuration(Integer.parseInt(input[4]));
        updateProjectDTO.setProjectNumberOfPlannedSprints(Integer.parseInt(input[5]));
        updateProjectDTO.setEndDate(input[6]);
        updateProjectDTO.setCustomerName(input[7]);
        updateProjectDTO.setProjectBusinessSector(input[8]);
        updateProjectDTO.setTypologyDescription(input[9]);
        updateProjectDTO.setProjectBudget(Double.parseDouble(input[10]));
        updateProjectDTO.setStatus(input[11]);

        return updateProjectDTO;
    }
}
