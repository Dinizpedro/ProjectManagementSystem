package switchfive.project.assemblers.assemblersJPA.iAssemblersJPA;

import switchfive.project.dataModel.dataJPA.UserJPA;
import switchfive.project.domain.aggregates.user.User;

import java.security.NoSuchAlgorithmException;

public interface IUserAssemblerJPA {

    UserJPA toJPA(User user);

    User toDomain(UserJPA userJPA) throws NoSuchAlgorithmException;

}
