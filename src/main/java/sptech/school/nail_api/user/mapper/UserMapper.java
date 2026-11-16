package sptech.school.nail_api.user.mapper;

import sptech.school.nail_api.auth.dto.RegisterRequestDTO;
import sptech.school.nail_api.user.dto.UserResponseDTO;
import sptech.school.nail_api.user.domain.User;

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