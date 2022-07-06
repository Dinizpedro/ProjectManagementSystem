package switchfive.project.applicationServices.iRepositories;


import switchfive.project.domain.aggregates.user.User;
import switchfive.project.domain.shared.valueObjects.Email;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

public interface IUserRepository {

    User save(User user) throws NoSuchAlgorithmException;

    List<User> findUsersByProfile(String profileId) throws NoSuchAlgorithmException;

    List<User> findUsersByEmail(final String email) throws NoSuchAlgorithmException;

    Optional<User> addProfile (String email, String profileID) throws NoSuchAlgorithmException;

    Optional<User> removeProfile (String email, String profileID) throws NoSuchAlgorithmException;

    Optional<User> findUserByEmail(final String email) throws NoSuchAlgorithmException;

    Optional<User> update(User user) throws NoSuchAlgorithmException;

    Optional<List<User>> findAllUsers() throws NoSuchAlgorithmException;

    boolean userExists(String email);

    boolean userExists(Email email);
}
