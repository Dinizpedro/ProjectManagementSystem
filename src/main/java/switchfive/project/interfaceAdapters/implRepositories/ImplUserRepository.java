package switchfive.project.interfaceAdapters.implRepositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switchfive.project.assemblers.assemblersJPA.iAssemblersJPA.IUserAssemblerJPA;
import switchfive.project.dataModel.dataJPA.UserJPA;
import switchfive.project.infrastructure.persistence.repositories.iRepositoriesJPA.IUserRepositoryJPA;
import switchfive.project.applicationServices.iRepositories.IUserRepository;
import switchfive.project.domain.aggregates.user.User;
import switchfive.project.domain.shared.valueObjects.Email;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ImplUserRepository implements IUserRepository {

    /**
     * Attribute UserList.
     */
    private final IUserRepositoryJPA userStore;
    private final IUserAssemblerJPA userAssembler;

    @Autowired
    public ImplUserRepository(IUserRepositoryJPA userStore,
                              IUserAssemblerJPA userAssembler) {
        this.userStore = userStore;
        this.userAssembler = userAssembler;
    }

    /**
     * A method that loops through a users list and finds a user with a given
     * profile description adding it to a result list.
     *
     * @param profile that will be searched.
     * @return a list with all the users with a given profileName.
     */
    public List<User> findUsersByProfile(final String profile)
            throws NoSuchAlgorithmException {

         List<UserJPA> userJPAList = userStore.findUsersJPAByProfile(profile);

        List<User> userList = new ArrayList<>();

        for (UserJPA userjpa : userJPAList) {
            User user = this.userAssembler.toDomain(userjpa);
            userList.add(user);
        }

        return userList;
    }

    public User save(User user) throws NoSuchAlgorithmException {
        UserJPA userJPA = this.userAssembler.toJPA(user);

        userStore.save(userJPA);

        return userAssembler.toDomain(userJPA);
    }

    public Optional<User> update(User user) throws NoSuchAlgorithmException {
        UserJPA userJPA = userAssembler.toJPA(user);

        userJPA = userStore.save(userJPA);

        return Optional.ofNullable(userAssembler.toDomain(userJPA));
    }

    public Optional<List<User>> findAllUsers() throws NoSuchAlgorithmException {
        User user;
        List<User> users = new ArrayList<>();

        List<UserJPA> usersJPA = this.userStore.findAll();

        if (usersJPA.isEmpty()) {
            return Optional.empty();
        }

        for (UserJPA each : usersJPA) {
            user = userAssembler.toDomain(each);
            users.add(user);
        }

        return Optional.of(users);
    }


    /**
     * /**
     * A method that loops through a users list until it finds a given email.
     *
     * @param email that will be searched
     * @return a list with the user with a given email
     */

    public List<User> findUsersByEmail(final String email) throws NoSuchAlgorithmException {

        List<UserJPA> userJPAList = userStore.findUsersByEmail(email);

        List<User> userList = new ArrayList<>();

        for (UserJPA userJPA : userJPAList) {
            User user = userAssembler.toDomain(userJPA);
            userList.add(user);
        }

        return userList;
    }

    public Optional<User> findUserByEmail(final String email) throws NoSuchAlgorithmException {

        Optional<UserJPA> theUserJPA = userStore.findUserJPAByEmail(email);

        if (theUserJPA.isPresent()) {
            User theUser = userAssembler.toDomain(theUserJPA.get());
            return Optional.of(theUser);
        }

        return Optional.empty();
    }


    public Optional<User> addProfile(String email, String profileDescription) throws NoSuchAlgorithmException {

        UserJPA userJPA;

        userJPA = this.userStore.findUserJPAByEmail(email).get();

        userJPA.addProfile(profileDescription);
        this.userStore.save(userJPA);

        User user = this.userAssembler.toDomain(userJPA);

        return Optional.of(user);

    }

    public Optional<User> removeProfile(String email, String profileDescription) throws NoSuchAlgorithmException {

        UserJPA userJPA;

        userJPA = this.userStore.findUserJPAByEmail(email).get();

        userJPA.removeProfile(profileDescription);
        this.userStore.save(userJPA);

        User user = this.userAssembler.toDomain(userJPA);

        return Optional.of(user);
    }

    public boolean userExists(String email) {
        return this.userStore.existsByEmail(email);
    }

    /**
     * checks if user exists in Database
     * @param email
     * @return
     */
    public boolean userExists(Email email) {
        return this.userStore.existsByEmail(email.getUserEmail());
    }

}
