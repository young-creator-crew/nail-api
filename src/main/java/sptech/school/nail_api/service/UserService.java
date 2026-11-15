package sptech.school.nail_api.service;

import org.springframework.stereotype.Service;
import sptech.school.nail_api.dto.user.UserResponseDTO;
import sptech.school.nail_api.dto.user.UserUpdateRequestDTO;
import sptech.school.nail_api.exception.user.UserAlreadyExistsException;
import sptech.school.nail_api.exception.user.UserNotFoundException;
import sptech.school.nail_api.mapper.UserMapper;
import sptech.school.nail_api.entity.User;
import sptech.school.nail_api.repository.UserRepository;

import java.util.Locale;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private User findActiveUser(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        if (!Boolean.TRUE.equals(user.getActive())) {
            throw new UserNotFoundException(id);
        }

        return user;
    }

    public UserResponseDTO get(Integer id) {
        User user = findActiveUser(id);
        return UserMapper.userToUserResponse(user);
    }

    public UserResponseDTO update(UserUpdateRequestDTO dto, Integer id) {
        User user = findActiveUser(id);

        String email = dto.getEmail().trim().toLowerCase(Locale.ROOT);

        if (userRepository.existsByEmailAndIdUserNot(email, id)) {
            throw new UserAlreadyExistsException();
        }

        user.setUserName(dto.getName().trim());
        user.setEmail(email);
        user.setPhone(dto.getPhone() == null || dto.getPhone().isBlank() ? null : dto.getPhone().trim());

        return UserMapper.userToUserResponse(userRepository.save(user));
    }

    public void delete(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        user.setActive(false);
        userRepository.save(user);
    }
}