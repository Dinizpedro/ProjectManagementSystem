package switchfive.project.bootstrap.databaseLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import switchfive.project.bootstrap.services.*;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseLoader.class);

    private ProfilesBootstrap profilesBootstrap;
    private UsersBootstrap usersBootstrap;
    private CustomersBootstrap customersBootstrap;
    private TypologyBootstrap typologyBootstrap;
    private ProjectsBootstrap projectsBootstrap;
    private UserStoriesBootstrap userStoriesBootstrap;
    private SprintsBootstrap sprintsBootstrap;
    private ResourcesBootstrap resourcesBootstrap;
    private UpdateProjectsBootstrap updateProjectsBootstrap;

    @Value("${load.bootstrapData}")
    private boolean loadDefaultData;


    @Autowired
    public DatabaseLoader(ProfilesBootstrap profilesBootstrap,
                          UsersBootstrap usersBootstrap,
                          CustomersBootstrap customersBootstrap,
                          TypologyBootstrap typologyBootstrap,
                          ProjectsBootstrap projectsBootstrap,
                          UserStoriesBootstrap userStoriesBootstrap,
                          SprintsBootstrap sprintsBootstrap,
                          ResourcesBootstrap resourcesBootstrap,
                          UpdateProjectsBootstrap updateProjectsBootstrap) {
        this.profilesBootstrap = profilesBootstrap;
        this.usersBootstrap = usersBootstrap;
        this.customersBootstrap = customersBootstrap;
        this.typologyBootstrap = typologyBootstrap;
        this.projectsBootstrap = projectsBootstrap;
        this.userStoriesBootstrap = userStoriesBootstrap;
        this.sprintsBootstrap = sprintsBootstrap;
        this.resourcesBootstrap = resourcesBootstrap;
        this.updateProjectsBootstrap = updateProjectsBootstrap;
    }

    @Override
    public void run(String... args) throws Exception {
        String asciiIntro = "\n" +
                "   _____ _       __    ____  ______   ______    __  __       \n" +
                "  / ___/| |     / /   /  _/ /_  __/  / ____/   / / / /       \n" +
                "  \\__ \\ | | /| / /    / /    / /    / /       / /_/ /        \n" +
                " ___/ / | |/ |/ /   _/ /    / /    / /___    / __  /         \n" +
                "/____/  |__/|__/   /___/   /_/     \\____/   /_/ /_/          \n" +
                "   ______    ____    ____    __  __    ____            ______\n" +
                "  / ____/   / __ \\  / __ \\  / / / /   / __ \\          / ____/\n" +
                " / / __    / /_/ / / / / / / / / /   / /_/ /         /___ \\  \n" +
                "/ /_/ /   / _, _/ / /_/ / / /_/ /   / ____/         ____/ /  \n" +
                "\\____/   /_/ |_|  \\____/  \\____/   /_/             /_____/   \n" +
                "                                                             \n";
        String asciiSuccess = "\n" +
                "___  ____ ___ ____    _    ____ ____ ___  ____ ___  \n" +
                "|  \\ |__|  |  |__|    |    |  | |__| |  \\ |___ |  \\ \n" +
                "|__/ |  |  |  |  |    |___ |__| |  | |__/ |___ |__/ \n" +
                "                                                    \n";

        LOG.info(asciiIntro);

        if (loadDefaultData) {
            profilesBootstrap.execute();
            usersBootstrap.execute();
            customersBootstrap.execute();
            typologyBootstrap.execute();
            projectsBootstrap.execute();
            userStoriesBootstrap.execute();
            sprintsBootstrap.execute();
            resourcesBootstrap.execute();
            updateProjectsBootstrap.execute();

            LOG.info(asciiSuccess);
        }
    }
}

