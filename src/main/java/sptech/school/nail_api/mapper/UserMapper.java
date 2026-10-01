package sptech.school.nail_api.mapper;

import sptech.school.nail_api.dto.auth.RegisterRequestDTO;
import sptech.school.nail_api.dto.user.UpdateRequestDTO;
import sptech.school.nail_api.dto.user.UserResponseDTO;
import sptech.school.nail_api.model.User;

public class UserMapper {

    public static UserResponseDTO userToUserResponse(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getEmail(),
                user.getUsername()
        );
    }

    public static User registerRequestToUser(RegisterRequestDTO dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setUsername(dto.getName());
        return user;
    }

    public static User updateRequestToUser(UpdateRequestDTO dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getName());
        return user;
    }

}