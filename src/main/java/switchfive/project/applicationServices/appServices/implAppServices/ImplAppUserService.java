package switchfive.project.applicationServices.appServices.implAppServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchfive.project.applicationServices.appServices.iappServices.IAppUserService;
import switchfive.project.applicationServices.iRepositories.IProfileRepository;
import switchfive.project.applicationServices.iRepositories.IProjectRepository;
import switchfive.project.applicationServices.iRepositories.IResourceRepository;
import switchfive.project.applicationServices.iRepositories.IUserRepository;
import switchfive.project.mappers.mappersApp.iMappers.IResourceMapper;
import switchfive.project.mappers.mappersApp.implMappers.ImplUserMapper;
import switchfive.project.domain.aggregates.project.Project;
import switchfive.project.domain.aggregates.resource.Resource;
import switchfive.project.domain.aggregates.user.User;
import switchfive.project.domain.factories.iFactories.IUserFactory;
import switchfive.project.domain.shared.valueObjects.*;
import switchfive.project.dtos.*;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImplAppUserService implements IAppUserService {

    private final IUserRepository userStore;

    private final IResourceMapper resourceMapper;

    private final IResourceRepository resourceStore;

    private final IProfileRepository profileStore;

    private final IUserFactory userFactory;

    private final IProjectRepository projectStore;


    @Autowired
    public ImplAppUserService(IUserRepository userStore, IProfileRepository profileStore,
                              IUserFactory userFactory, IResourceRepository resourceStore,
                              IProjectRepository projectStore, IResourceMapper resourceMapper) {
        this.userStore = userStore;
        this.profileStore = profileStore;
        this.userFactory = userFactory;
        this.resourceStore = resourceStore;
        this.projectStore = projectStore;
        this.resourceMapper = resourceMapper;
    }

    public UserDTO createAndSaveUser(UserDTO userDTO) throws NoSuchAlgorithmException {

        if (userStore.findUserByEmail(userDTO.email).isPresent()) {
            throw new IllegalArgumentException("Email Taken!");
        }

        Email email = Email.create(userDTO.email);
        Password password = Password.createPassword(userDTO.password);
        UserName userName = UserName.createUsername(userDTO.userName);
        Function function = Function.createFunction(userDTO.userFunction);

        User newUser = userFactory.createUser(email, password, userName, function);
        userStore.save(newUser);

        return ImplUserMapper.toDTO(newUser);
    }

    public Optional<UserDTO> activateAccount(UserDTO userDTO) throws NoSuchAlgorithmException {

        Optional<User> userInStore = userStore.findUserByEmail(userDTO.email);

        if (userInStore.isPresent()) {
            User selectedUser = userInStore.get();
            userDTO.activation = selectedUser.activateAccount(userDTO.code);
            Optional<User> updatedUser = userStore.update(selectedUser);
            if (updatedUser.isPresent()) {
                userDTO = ImplUserMapper.toDTO(updatedUser.get());
            }
        }

        return Optional.of(userDTO);
    }

    public Optional<List<UserDTO>> activateAll() throws NoSuchAlgorithmException {
        UserDTO userDTO;
        List<UserDTO> usersDTO = new ArrayList<>();

        Optional<List<User>> usersInStore = userStore.findAllUsers();

        if (usersInStore.isEmpty()) {
            return Optional.empty();
        }

        for (User each : usersInStore.get()) {
            each.setToActive();
            userStore.save(each);
            userDTO = ImplUserMapper.toDTO(each);
            usersDTO.add(userDTO);
        }

        return Optional.of(usersDTO);
    }

    /**
     * if the store has a user, updates him with a new password.
     *
     * @param passwordDTO containing the user email, the old and the new password
     * @return true if the user is found and updated, otherwise false.
     */
    public Optional<UserDTO> changePassword(ChangePasswordDTO passwordDTO) throws NoSuchAlgorithmException {

        Optional<User> optUserInStore = userStore.findUserByEmail(passwordDTO.email);
        UserDTO userDTO = null;

        if (optUserInStore.isPresent()) {
            User userInStore = optUserInStore.get();

            boolean wasUpdate = userInStore.updatePassword(passwordDTO.oldPassword, passwordDTO.newPassword);

            if (!wasUpdate) {
                throw new UnsupportedOperationException("Update was not successful!" + "Old password is not the same.");
            }

            userStore.update(userInStore);
            userDTO = ImplUserMapper.toDTOWithPassword(userInStore);
        }

        return Optional.ofNullable(userDTO);
    }


    public Optional<User> findById(String id) throws NoSuchAlgorithmException {
        return userStore.findUserByEmail(id);
    }


    /**
     * If the store has a user with that id updates him with a new name and anew function.
     * If the name and/or function are not validated throws an Exception.
     *
     * @param userDTO the DTO containing the user email, the new name and the new function.
     * @return true if the user is found and updated, otherwise false.
     */
    public Optional<UserDTO> updateUser(UserDTO userDTO) throws NoSuchAlgorithmException {
        Optional<User> theUser = userStore.findUserByEmail(userDTO.email);
        UserDTO updatedUserDTO = null;
        if (theUser.isPresent()) {
            UserName newName = UserName.createUsername(userDTO.userName);
            Function newFunction = Function.createFunction(userDTO.userFunction);
            theUser.get().updateUserInformation(newName, newFunction);
            theUser = userStore.update(theUser.get());
            if (theUser.isPresent()) {
                updatedUserDTO = ImplUserMapper.toDTO(theUser.get());
            }
        }

        return Optional.ofNullable(updatedUserDTO);
    }

    /**
     * This method will take the input and compare it with the user emails.
     *
     * @param email that will be searched.
     * @return a list of SearchUserDTO
     * @throws NoSuchAlgorithmException
     */

    public List<SearchUserDTO> getUsersByEmail(String email) throws NoSuchAlgorithmException {

        List<User> userList = userStore.findUsersByEmail(email);

        return ImplUserMapper.toSearchDTOList(userList);

    }

    /**
     * This method will take the input and search for users with that profile.
     * @param profile that will be searched
     * @return list of SearchUserDTO
     * @throws NoSuchAlgorithmException
     */

    public List<SearchUserDTO> getUsersByProfile(String profile) throws NoSuchAlgorithmException {

        List<User> userList = userStore.findUsersByProfile(profile);

        return ImplUserMapper.toSearchDTOList(userList);
    }

    public Optional<SearchUserDTO> getUserByEmail(String email) throws NoSuchAlgorithmException {
        return Optional.of(ImplUserMapper.toSearchUserDTO(userStore.findUserByEmail(email).get()));
    }


    public IUserRepository getUserStore() {
        return userStore;
    }

    public boolean setUserToActive(String email) throws NoSuchAlgorithmException {

        boolean result;

        Optional<User> userOpt = userStore.findUserByEmail(email);

        if (userOpt.isPresent()
                && !userOpt.get().isUserActive()) {
            userOpt.get().setToActive();
            userStore.save(userOpt.get());
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public boolean setUserToInactive(String email) throws NoSuchAlgorithmException {

        boolean result;

        Optional<User> userOpt = userStore.findUserByEmail(email);
        if (userOpt.isPresent() && userOpt.get().isUserActive()) {
            userOpt.get().setToInactive();
            userStore.save(userOpt.get());
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public Optional<SearchUserDTO> validateAndAddProfile(String email, String profileDescription) throws NoSuchAlgorithmException {

        Optional<User> user = this.userStore.findUserByEmail(email);

        ProfileDescription profile = ProfileDescription.createProfileDescription(profileDescription);

        if (user.isEmpty()) {
            throw new IllegalArgumentException("User does not exist.");
        } else if (!this.profileStore.profileExists(profileDescription)) {
            throw new IllegalArgumentException("Profile does not exist.");
        } else if (user.get().hasProfile(profile)) {
            throw new IllegalArgumentException("User already has selected profile.");
        } else {
            user.get().addProfile(profile);
            this.userStore.addProfile(email, profileDescription);
            return Optional.of(ImplUserMapper.toSearchUserDTO(user.get()));
        }
    }

    public Optional<SearchUserDTO> validateAndRemoveProfile(String email, String profileDescription) throws NoSuchAlgorithmException {

        Optional<User> user = this.userStore.findUserByEmail(email);

        ProfileDescription profile = ProfileDescription.createProfileDescription(profileDescription);

        if (user.isEmpty()) {
            throw new IllegalArgumentException("User does not exist.");
        } else if (!user.get().hasProfile(profile)) {
            throw new IllegalArgumentException("User does not have selected profile.");
        } else {
            user.get().removeProfile(profile);
            this.userStore.removeProfile(email, profileDescription);
            return Optional.of(ImplUserMapper.toSearchUserDTO(user.get()));
        }
    }

    /**
     * Gets a list of Projects that the user matched with the Param Email is allocated to.
     * The JPA Repository is used to check if the user exists, if not an empty list is returned.
     * The JPA Repository is used to check in what projects that user is allocate as a resource, if
     * no match is found return an ampty list.
     * @param email
     * @return
     * @throws ParseException
     * @throws NoSuchAlgorithmException
     */
    public List<AllocatedProjectDTO> getAllocatedProjects(String email) throws ParseException, NoSuchAlgorithmException {

        Email emailVO = Email.create(email);
        boolean userExists = userStore.userExists(emailVO);

        List<AllocatedProjectDTO> allocatedProjectDTOs = new ArrayList<>();

        if (userExists) {

            List<Resource> resources = resourceStore.getResourcesByEmail(emailVO);

            if (!resources.isEmpty()) {
                for (Resource theResource : resources) {

                    String projectCode = theResource.getProjectCode();
                    ProjectCode projectCodeVO = ProjectCode.create(projectCode);
                    Optional<Project> theProject = projectStore.findByCode(projectCodeVO);
                    String projectName = theProject.get().getName();

                    AllocatedProjectDTO allocatedProjectDTO = resourceMapper.toAllocatedProjectDTO(theResource,projectName);

                    allocatedProjectDTOs.add(allocatedProjectDTO);
                }
            }
        }
        return allocatedProjectDTOs;
    }

    public List<UserStatusDTO> getAllUserStatus() throws NoSuchAlgorithmException {

        Optional<List<User>> users = userStore.findAllUsers();

        return ImplUserMapper.toDTOListWithStatus(users.get());

    }

    public List<UserDTO> getAllUsers() throws NoSuchAlgorithmException {
        List<UserDTO> userDtoList = new ArrayList<>();
        List<User> userList = this.userStore.findAllUsers().get();

        if (userList.isEmpty()) {
            return userDtoList;
        }

        for (User each : userList) {
            UserDTO dto = ImplUserMapper.toDTO(each);
            ;
            userDtoList.add(dto);
        }

        return userDtoList;
    }

}
