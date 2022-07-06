package switchfive.project.bootstrap.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switchfive.project.dtos.ResourceCreationDTO;
import switchfive.project.applicationServices.appServices.iappServices.IAppResourceService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

@Component
public class ResourcesBootstrap {

    private static final Logger LOG = LoggerFactory.getLogger(ResourcesBootstrap.class);

    private IAppResourceService resourceService;


    @Autowired
    public ResourcesBootstrap(IAppResourceService resourceService) {
        this.resourceService = resourceService;
    }


    public void execute() {
        LOG.info("Loading resources ...");
        loadProfiles();
        LOG.info("Resources loaded");
    }


    public void loadProfiles()  {

        String dir = System.getProperty("user.dir");
        String file = dir + "/docs/database_loading/resources.csv";
        BufferedReader reader = null;
        String line;

        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                if (Objects.equals(row[6], "po")) {
                    resourceService.definedProductOwnerOfAProject(addResource(row));
                } else if (Objects.equals(row[6], "sm")) {
                    resourceService.definedScrumMasterOfAProject(addResource(row));
                } else {
                    resourceService.definedTeamMemberOfAProject(addResource(row));
                }
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


    private ResourceCreationDTO addResource(String[] input) {
        String projectCodeDto = input[0];
        String userIdDto = input[1];
        String startDateDto = input[2];
        String endDateDto = input[3];
        double costPerHourDto = Double.parseDouble(input[4]);
        double percentageOfAllocationDto = Double.parseDouble(input[5]);

        return new ResourceCreationDTO(userIdDto, projectCodeDto, startDateDto, endDateDto, costPerHourDto, percentageOfAllocationDto);
    }
}
