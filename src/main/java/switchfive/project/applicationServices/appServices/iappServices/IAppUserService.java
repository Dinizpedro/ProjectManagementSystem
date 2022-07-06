package switchfive.project.applicationServices.appServices.iappServices;

import switchfive.project.applicationServices.iRepositories.IUserRepository;
import switchfive.project.domain.aggregates.user.User;
import switchfive.project.dtos.*;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface IAppUserService {
    UserDTO createAndSaveUser(UserDTO userDTO) throws NoSuchAlgorithmException;

    Optional<SearchUserDTO> validateAndAddProfile(String email, String profileID) throws NoSuchAlgorithmException;

    public Optional<SearchUserDTO> validateAndRemoveProfile(String email, String profileID) throws NoSuchAlgorithmException;

    List<SearchUserDTO> getUsersByEmail(String email) throws NoSuchAlgorithmException;

    List<SearchUserDTO> getUsersByProfile(String profileId) throws NoSuchAlgorithmException;

    IUserRepository getUserStore();

    //new
    Optional<UserDTO> changePassword(ChangePasswordDTO passwordDTO) throws NoSuchAlgorithmException;

    boolean setUserToInactive(String userID) throws NoSuchAlgorithmException;

    boolean setUserToActive(String userID) throws NoSuchAlgorithmException;

    Optional<UserDTO> updateUser(UserDTO userDTO) throws NoSuchAlgorithmException;

    Optional<UserDTO> activateAccount(UserDTO userDTO) throws NoSuchAlgorithmException;

    Optional<User> findById(String id) throws NoSuchAlgorithmException;

    Optional<SearchUserDTO> getUserByEmail(String email) throws NoSuchAlgorithmException;

    List<AllocatedProjectDTO> getAllocatedProjects(String email) throws ParseException, NoSuchAlgorithmException;

    Optional<List<UserDTO>> activateAll() throws NoSuchAlgorithmException;

    List<UserStatusDTO> getAllUserStatus() throws NoSuchAlgorithmException;

    List<UserDTO> getAllUsers() throws ParseException, NoSuchAlgorithmException;
}
