package switchfive.project.bootstrap.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switchfive.project.dtos.ProjectDTO;
import switchfive.project.applicationServices.appServices.iappServices.IAppProjectService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Component
public class ProjectsBootstrap {

    private static final Logger LOG = LoggerFactory.getLogger(ProjectsBootstrap.class);

    private IAppProjectService projectService;


    @Autowired
    public ProjectsBootstrap(IAppProjectService projectService) {
        this.projectService = projectService;
    }


    public void execute() {
        LOG.info("Loading projects ...");
        loadProfiles();
        LOG.info("Projects loaded");
    }


    public void loadProfiles() {

        String dir = System.getProperty("user.dir");
        String file = dir + "/docs/database_loading/projects.csv";
        BufferedReader reader = null;
        String line;

        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                this.projectService.createAndSaveProject(addProject(row));
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


    private ProjectDTO addProject(String[] input) {
        ProjectDTO projectDTO = new ProjectDTO();

        projectDTO.setProjectCode(input[0]);
        projectDTO.setProjectName(input[1]);
        projectDTO.setProjectDescription(input[2]);
        projectDTO.setStartDate(input[3]);
        projectDTO.setProjectSprintDuration(Integer.parseInt(input[4]));
        projectDTO.setProjectNumberOfPlannedSprints(Integer.parseInt(input[5]));
        projectDTO.setEndDate(input[6]);
        projectDTO.setCustomerName(input[7]);
        projectDTO.setProjectBusinessSector(input[8]);
        projectDTO.setTypologyDescription(input[9]);
        projectDTO.setProjectBudget(Double.parseDouble(input[10]));
        projectDTO.setUserEmail(input[11]);
        projectDTO.setCostPerHour(Double.parseDouble(input[12]));
        projectDTO.setPercentageOfAllocation(Double.parseDouble(input[13]));

        return projectDTO;
    }
}
