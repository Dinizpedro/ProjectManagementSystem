package switchfive.project.applicationServices.appServices.iappServices;

import switchfive.project.dtos.SearchUserDTO;
import switchfive.project.applicationServices.iRepositories.IUserRepository;

import java.util.List;

public interface iSearchService {

    List<SearchUserDTO> getUserByEmail(String email);

    IUserRepository getUserStore();

    List<SearchUserDTO> getUserByProfile(int profileId);


}
