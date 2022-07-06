package switchfive.project.interfaceAdapters.controllers.implControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switchfive.project.interfaceAdapters.controllers.iControllers.IUserController;
import switchfive.project.applicationServices.appServices.iappServices.IAppUserService;
import switchfive.project.dtos.*;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@CrossOrigin
@RestController
@RequestMapping
public class ImplUserController implements IUserController {

    private final static String ERROR_MESSAGE = "Operation failed.";

    private final static String NO_USER_FOUND = "User not found.";

    private final static String FAILED = "the password has not been updated";

    /**
     * Inject User application service.
     */
    private final IAppUserService userService;

    @Autowired
    public ImplUserController(final IAppUserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/users/allUsers")
    public ResponseEntity<Object> getAllUsers() throws ParseException, NoSuchAlgorithmException {

        List<UserDTO> dto = this.userService.getAllUsers();
        RepresentationModel<UserDTO> linkList = new RepresentationModel<>();

        for (UserDTO each : dto) {

            Link link = linkTo(methodOn(ImplUserController.class).getUserByEmail(each.getEmail())).withRel(each.getEmail()).withType("GET");

            linkList.add(link);
        }

        return new ResponseEntity<>(linkList, HttpStatus.OK);
    }


    /**
     * Creates User if all fields are valid.
     *
     * @param userDTO - fields to be inserted by user.
     * @return HTTPStatus.OK with message if user is created successfully,
     * otherwise returns HTTPStatus Conflict with error message.
     */
    @PostMapping("/users/")
    @ResponseBody
    public ResponseEntity<Object> createUser(@RequestBody UserDTO userDTO) throws NoSuchAlgorithmException {
        try {
            UserDTO DTOOutput = userService.createAndSaveUser(userDTO);

            Link link = linkTo(methodOn(ImplUserController.class).getUsersByEmail(userDTO.email)).withSelfRel();

            DTOOutput.add(link);

            return new ResponseEntity<>(DTOOutput, HttpStatus.CREATED);
        } catch (IllegalArgumentException operationFailed) {
            return new ResponseEntity<>(ERROR_MESSAGE, HttpStatus.CONFLICT);
        }
    }


    @PostMapping("/users/{email}/updateProfile")
    public ResponseEntity<Object> addProfile(@PathVariable(value = "email") String email, @RequestBody UpdateUserProfileDTO dto) {
        try {
            Optional<SearchUserDTO> userDTO = userService.validateAndAddProfile(email, dto.getProfile());

            Link link = linkTo(methodOn(ImplUserController.class).getUserByEmail(email)).withSelfRel();

            userDTO.get().add(link);

            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } catch (IllegalArgumentException | NoSuchAlgorithmException | NoSuchElementException e) {
            return new ResponseEntity<>(ERROR_MESSAGE, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/users/{email}/updateProfile")
    public ResponseEntity<Object> removeProfile(@PathVariable(value = "email") String email, @RequestBody UpdateUserProfileDTO dto) {
        try {
            Optional<SearchUserDTO> userDTO = userService.validateAndRemoveProfile(email, dto.getProfile());

            Link link = linkTo(methodOn(ImplUserController.class).getUserByEmail(email)).withSelfRel();

            userDTO.get().add(link);

            return new ResponseEntity<>(userDTO, HttpStatus.OK);

        } catch (IllegalArgumentException | NoSuchAlgorithmException | NoSuchElementException e) {

            return new ResponseEntity<>(ERROR_MESSAGE, HttpStatus.BAD_REQUEST);

        }
    }

    /**
     * This method accepts partial searches. It will the take the input and compare it with the email of all users, retriveing the users that match.
     * @param email that will be searched.
     * @return a representationModel object with the links of the users.
     * @throws NoSuchAlgorithmException
     */

    @GetMapping(value = "/users/email/{email}")
    @ResponseBody
    public ResponseEntity<Object> getUsersByEmail(@PathVariable(value = "email") String email) throws NoSuchAlgorithmException {

        List<SearchUserDTO> userDTOList = userService.getUsersByEmail(email);

        RepresentationModel<SearchUserDTO> linkList = new RepresentationModel<>();

            for (SearchUserDTO user : userDTOList) {
                Link link = linkTo(methodOn(ImplUserController.class).getUserByEmail(user.getEmail())).withRel(user.getEmail()).withType("GET");
                linkList.add(link);
            }

        return new ResponseEntity<>(linkList, HttpStatus.OK);

    }

    /**
     * This method will search for users that have certain profile.
     * @param profile that will be searched.
     * @return a representationModel object with the links of the users.
     * @throws NoSuchAlgorithmException
     */

    @GetMapping(value = "/users/profile/{profile}")
    @ResponseBody
    public ResponseEntity<Object> getUsersByProfile(@PathVariable(value = "profile") String profile) throws NoSuchAlgorithmException {

        List<SearchUserDTO> userDTOList = userService.getUsersByProfile(profile);

        RepresentationModel<SearchUserDTO> linkList = new RepresentationModel<>();

            for (SearchUserDTO user : userDTOList) {
                Link link = linkTo(methodOn(ImplUserController.class).getUserByEmail(user.getEmail())).withRel(user.getUserName()).withType("GET");
                linkList.add(link);
            }

            return new ResponseEntity<>(linkList, HttpStatus.OK);

    }

    @GetMapping(value = "/users")
    @ResponseBody
    public ResponseEntity<Object> getUserByEmail(@RequestParam(value = "email") String email) throws NoSuchAlgorithmException {
        try {
            Optional<SearchUserDTO> user = userService.getUserByEmail(email);

            addLinksToDTO(user.get());

            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } catch (NoSuchElementException | ParseException e) {
            return new ResponseEntity<>(NO_USER_FOUND, HttpStatus.NO_CONTENT);
        }
    }

    /**
     * The purpose of this method is to receive list of users (domain) and convert it to a SearchUserDTO with selfLink.
     *
     * @return list of searchUserDTO with selflink.
     */

    private void addLinksToDTO(SearchUserDTO dto) throws NoSuchAlgorithmException, ParseException {

        Link selfLink = linkTo(methodOn(ImplUserController.class).getUserByEmail(dto.getEmail())).withSelfRel().withType("GET");

        Link addProfileLink = linkTo(methodOn(ImplUserController.class).addProfile(dto.getEmail(), null)).withRel("Add Profile").withType("POST");

        Link removeProfileLink = linkTo(methodOn(ImplUserController.class).removeProfile(dto.getEmail(), null)).withRel("Remove Profile").withType("DELETE");

        Link activateAccountLink = linkTo(methodOn(ImplUserController.class).activateAccount(null)).withRel("Activate Account").withType("PATCH");

        Link updateUserLink = linkTo(methodOn(ImplUserController.class).updateUser(null)).withRel("Update User").withType("PUT");

        Link changePasswordLink = linkTo(methodOn(ImplUserController.class).changePassword(null)).withRel("Change Password").withType("PATCH");

        Link getAllocatedProjectsLink = linkTo(methodOn(ImplUserController.class).getAllocatedProjects(dto.getEmail())).withRel("Get Allocated Projects").withType("GET");

        Link setToActiveLink = linkTo(methodOn(ImplUserController.class).setUserToActive(dto.getEmail())).withRel("Set To Active").withType("PATCH");

        Link setToInactiveLink = linkTo(methodOn(ImplUserController.class).setUserToInactive(dto.getEmail())).withRel("Set To Inactive").withType("PATCH");

        dto.add(selfLink, addProfileLink, removeProfileLink, activateAccountLink, updateUserLink, changePasswordLink, getAllocatedProjectsLink, setToActiveLink, setToInactiveLink);
    }


    /**
     * If code is the same as the one generated by the system user account is activated.
     *
     * @param userDTO - code to activate account.
     * @return HttpStatus.OK with message if User account is successfully activated,
     * otherwise returns HttpStatus.NOT_ACCEPTABLE with error message.
     */
    @PatchMapping("/users/")
    public ResponseEntity<Object> activateAccount(@RequestBody UserDTO userDTO) throws NoSuchAlgorithmException {

        Optional<UserDTO> userDTOOpt = userService.activateAccount(userDTO);

        if (userDTOOpt.isPresent() && userDTOOpt.get().activation) {

            UserDTO userDTOOutput = userDTOOpt.get();

            Link link = linkTo(methodOn(ImplUserController.class).getUserByEmail(userDTO.email)).withSelfRel();

            userDTOOutput.add(link);

            return new ResponseEntity<>(userDTOOutput, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(ERROR_MESSAGE, HttpStatus.UNAUTHORIZED);
        }
    }


    /**
     * Update user if fields to be changed are successfully inserted.
     *
     * @param userDTO - field to update user.
     * @return HttpStatus.OK with message if User is successfully updated,
     * otherwise returns HttpStatus Conflict with error message.
     */
    @PutMapping("/users")
    @ResponseBody
    public ResponseEntity<Object> updateUser(@RequestBody UserDTO userDTO) throws NoSuchAlgorithmException {

        Optional<UserDTO> updatedUserDTO = userService.updateUser(userDTO);

        if (updatedUserDTO.isPresent()) {
            Link link = linkTo(methodOn(ImplUserController.class).getUserByEmail(userDTO.email)).withSelfRel();
            updatedUserDTO.get().add(link);

            return new ResponseEntity<>(updatedUserDTO.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Change the password of the user
     *
     * @param passwordDto field to change password
     * @return HttpStatus.OK with message if password of the User is successfully changed,
     * otherwise returns HttpStatus Conflict with error message.
     */
    @PatchMapping("/users/password")
    public ResponseEntity<Object> changePassword(@RequestBody ChangePasswordDTO passwordDto) throws NoSuchAlgorithmException {

        try {
            Optional<UserDTO> userDTOOpt = userService.changePassword(passwordDto);

            if (userDTOOpt.isPresent()) {
                UserDTO userDTOoutp = userDTOOpt.get();

                Link link = linkTo(methodOn(ImplUserController.class).getUsersByEmail(userDTOoutp.email)).withSelfRel();

                userDTOoutp.add(link);

                return new ResponseEntity<>(userDTOoutp, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(ERROR_MESSAGE, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * Gets all projects that selected user is assigned to.
     * @param email
     * @return representation of the projects the user is assigned to and HttpsStatus 200 OK.
     * Otherwise returns HttpsStatus 400 Bad Request-
     * @throws NoSuchAlgorithmException
     * @throws ParseException
     */
    @GetMapping(value = "users/{email}/projects")
    public ResponseEntity<Object> getAllocatedProjects(@PathVariable final String email) throws NoSuchAlgorithmException, ParseException {

        try {
            List<AllocatedProjectDTO> allocatedProjectDTOs = userService.getAllocatedProjects(email);

            for (AllocatedProjectDTO allocatedProjectDTO : allocatedProjectDTOs) {

                String projectCode = allocatedProjectDTO.getProjectCode();
                Link link = linkTo(methodOn(ImplProjectController.class).getProject(projectCode)).withSelfRel();

                allocatedProjectDTO.add(link);
            }
            return new ResponseEntity<>(allocatedProjectDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(ERROR_MESSAGE, HttpStatus.BAD_REQUEST);
        }
    }


    @PatchMapping("users/email/{email}/activate-user")
    public ResponseEntity<Object> setUserToActive(@PathVariable(value = "email") String email) throws NoSuchAlgorithmException, ParseException {

        boolean userActivated = userService.setUserToActive(email);
        Optional<SearchUserDTO> user = userService.getUserByEmail(email);

        if (userActivated) {
            addLinksToDTO(user.get());
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ERROR_MESSAGE, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("users/email/{email}/inactivate-user")
    public ResponseEntity<Object> setUserToInactive(@PathVariable(value = "email") String email) throws NoSuchAlgorithmException, ParseException {

        boolean userInactivated = userService.setUserToInactive(email);
        Optional<SearchUserDTO> user = userService.getUserByEmail(email);

        if (userInactivated) {
            addLinksToDTO(user.get());
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ERROR_MESSAGE, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("users/status")
    public ResponseEntity<Object> getAllUserStatus() throws NoSuchAlgorithmException {

        List<UserStatusDTO> userList = userService.getAllUserStatus();

        for (UserStatusDTO user : userList) {

            Link link = linkTo(methodOn(ImplUserController.class).getUserByEmail(user.getEmail())).withSelfRel();

            user.add(link);

        }
        return new ResponseEntity<>(userList, HttpStatus.OK);

    }
}
