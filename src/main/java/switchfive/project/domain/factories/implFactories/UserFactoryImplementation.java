package switchfive.project.domain.factories.implFactories;

import org.springframework.stereotype.Component;
import switchfive.project.domain.aggregates.user.User;
import switchfive.project.domain.factories.iFactories.IUserFactory;
import switchfive.project.domain.shared.valueObjects.*;

import java.util.List;

@Component
public class UserFactoryImplementation implements IUserFactory {


    public UserFactoryImplementation() {

    }

    @Override
    public User createUser(Email email,
                           Password password,
                           UserName userName,
                           Function function
    ) {
        return new User(email, password, userName, function);
    }

    @Override
    public User createUser(Email email, List<ProfileDescription> userProfileList, Activation activation, Password password, UserName userName, Function function) {
        return new User(email, userProfileList, activation, password, userName, function);
    }
}
