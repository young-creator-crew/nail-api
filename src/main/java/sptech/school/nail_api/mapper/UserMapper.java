package sptech.school.nail_api.mapper;

import sptech.school.nail_api.dto.auth.RegisterRequestDTO;
import sptech.school.nail_api.dto.user.UserResponseDTO;
import sptech.school.nail_api.entity.User;

public class UserMapper {

    public static UserResponseDTO userToUserResponse(User user) {
        return new UserResponseDTO(
                user.getIdUser(),
                user.getEmail(),
                user.getUserName(),
                user.getPhone(),
                user.getUserType()
        );
    }

    public static User registerRequestToUser(RegisterRequestDTO dto) {
        User user = new User();

        user.setUserName(dto.getName());
        user.setPhone(dto.getPhone());

        return user;
    }

}