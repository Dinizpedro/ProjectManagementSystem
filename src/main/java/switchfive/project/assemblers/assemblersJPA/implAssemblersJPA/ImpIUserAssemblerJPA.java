package switchfive.project.assemblers.assemblersJPA.implAssemblersJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switchfive.project.assemblers.assemblersJPA.iAssemblersJPA.IUserAssemblerJPA;
import switchfive.project.dataModel.dataJPA.UserJPA;
import switchfive.project.domain.aggregates.user.User;
import switchfive.project.domain.factories.iFactories.IUserFactory;
import switchfive.project.domain.shared.valueObjects.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class ImpIUserAssemblerJPA implements IUserAssemblerJPA {

    private final IUserFactory userFactory;

    @Autowired
    public ImpIUserAssemblerJPA(IUserFactory userFactory) {
        this.userFactory = userFactory;
    }

    public UserJPA toJPA(User user) {

        return new UserJPA(user.getEmail(),
                getUserProfileLIst(user.getUserProfileList()),
                user.getActivation().getCode(),
                user.getActivation().isActivated(),
                user.getActivation().getActivationDate(),
                user.getActivation().getInactivationDate(),
                user.getPassword().getUserPassword(),
                user.getUserName().getUserName(),
                user.getFunction().getDescription());
    }

    @Override
    public User toDomain(UserJPA userJPA) {
        return userFactory.createUser(Email.create(userJPA.getEmail()),
                getUserJPAProfileLIst(userJPA.getProfileList()),
                Activation.createActivation(userJPA.getCode(), userJPA.isActivated(),
                        userJPA.getActivationDate(), userJPA.getInactivationDate()),
                Password.createPasswordFromDB(userJPA.getPassword()),
                UserName.createUsername(userJPA.getUsername()),
                Function.createFunction(userJPA.getFunction())
        );
    }

    private List<String> getUserProfileLIst(List<ProfileDescription> profileIDList) {

        List<String> profileList = new ArrayList<>();

        for (ProfileDescription profileDescription : profileIDList) {

            profileList.add(profileDescription.getDescription());
        }

        return profileList;
    }

    private List<ProfileDescription> getUserJPAProfileLIst(List<String> profileIDList) {

        List<ProfileDescription> profileList = new ArrayList<>();

        for (String profileDescription : profileIDList) {

            profileList.add(ProfileDescription.createProfileDescription(profileDescription));
        }

        return profileList;
    }
}
