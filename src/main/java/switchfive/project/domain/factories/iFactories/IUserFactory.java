package switchfive.project.domain.factories.iFactories;

import switchfive.project.domain.aggregates.user.User;
import switchfive.project.domain.shared.valueObjects.*;

import java.util.List;


public interface IUserFactory {

    User createUser(Email email,
                    Password password,
                    UserName userName,
                    Function function
    );

    User createUser(Email email,
                    List<ProfileDescription> userProfileList,
                    Activation activation,
                    Password password,
                    UserName userName,
                    Function function);
}
