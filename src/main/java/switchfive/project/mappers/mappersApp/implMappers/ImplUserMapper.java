package switchfive.project.mappers.mappersApp.implMappers;

import switchfive.project.dtos.SearchUserDTO;
import switchfive.project.dtos.UserDTO;
import switchfive.project.dtos.UserStatusDTO;
import switchfive.project.mappers.mappersApp.iMappers.IUserMapper;
import switchfive.project.domain.aggregates.user.User;

import java.util.ArrayList;
import java.util.List;

public class ImplUserMapper implements IUserMapper {

    public ImplUserMapper() {
    }

    public static List<SearchUserDTO> toSearchDTOList(List<User> userList) {
        List<SearchUserDTO> resultList = new ArrayList<>();
        for (User selectedUser : userList) {
            SearchUserDTO userDTO = ImplUserMapper.toSearchUserDTO(selectedUser);
            resultList.add(userDTO);
        }
        return resultList;
    }


    public static SearchUserDTO toSearchUserDTO(User user) {
        SearchUserDTO userDTO = new SearchUserDTO();
        userDTO.setUserName(user.getUserName().getUserName());
        userDTO.setUserProfileList(user.getProfileListAsDescription());
        userDTO.setActivation(user.getAccountStatus());
        userDTO.setEmail(user.getEmail());
        userDTO.setFunction(user.getFunctionDescription());
        return userDTO;
    }

    public static UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.userName = user.getUserName().getUserName();
        dto.email = user.getEmail();
        dto.userFunction = user.getFunctionDescription();
        dto.activation = user.getActivation().isActivated();
        return dto;
    }

    public static UserDTO toDTOWithPassword(User user) {
        UserDTO dto = new UserDTO();
        dto.userName = user.getUserName().getUserName();
        dto.email = user.getEmail();
        dto.password = user.getPassword().getUserPassword();
        dto.activation = user.isUserActive();
        return dto;
    }

    public static UserStatusDTO toDTOWithStatus(User user) {
        UserStatusDTO userDTO = new UserStatusDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setActivated(user.getActivation().isActivated());
        return userDTO;
    }

    public static List<UserStatusDTO> toDTOListWithStatus(List<User> users) {

        List<UserStatusDTO> dtoList = new ArrayList<>();

        for(User user : users) {
            UserStatusDTO dto = toDTOWithStatus(user);
            dtoList.add(dto);
        }
        return dtoList;
    }

}
