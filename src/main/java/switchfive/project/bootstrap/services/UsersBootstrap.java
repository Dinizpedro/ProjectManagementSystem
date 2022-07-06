package switchfive.project.bootstrap.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switchfive.project.dtos.UserDTO;
import switchfive.project.applicationServices.appServices.iappServices.IAppUserService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Component
public class UsersBootstrap {

    private static final Logger LOG = LoggerFactory.getLogger(UsersBootstrap.class);

    private IAppUserService userService;


    @Autowired
    public UsersBootstrap(IAppUserService userService) {
        this.userService = userService;
    }


    public void execute() throws NoSuchAlgorithmException {
        LOG.info("Loading users ...");
        loadProfiles();
        LOG.info("Users loaded");
    }

    public void loadProfiles() throws NoSuchAlgorithmException {

        String dir = System.getProperty("user.dir");
        String file = dir + "/docs/database_loading/users.csv";
        BufferedReader reader = null;
        String line;

        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                userService.createAndSaveUser(addUser(row));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        this.userService.activateAll();


        dir = System.getProperty("user.dir");
        file = dir + "/docs/database_loading/user_roles_add.csv";

        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                userService.validateAndAddProfile(row[0], row[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        dir = System.getProperty("user.dir");
        file = dir + "/docs/database_loading/user_roles_remove_visitor.csv";

        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                userService.validateAndRemoveProfile(row[0], row[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private UserDTO addUser(String[] input) {
        UserDTO userDTO = new UserDTO();

        userDTO.email = input[0];
        userDTO.password = input[1];
        userDTO.userName = input[2];
        userDTO.userFunction = input[3];

        return userDTO;
    }
}
