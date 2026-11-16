package sptech.school.nail_api.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sptech.school.nail_api.dto.user.UserResponseDTO;
import sptech.school.nail_api.dto.user.UserUpdatePasswordRequestDTO;
import sptech.school.nail_api.dto.user.UserUpdateRequestDTO;
import sptech.school.nail_api.exception.auth.InvalidCredentialsException;
import sptech.school.nail_api.exception.user.InvalidUserStatusException;
import sptech.school.nail_api.exception.user.UserAlreadyExistsException;
import sptech.school.nail_api.exception.user.UserNotFoundException;
import sptech.school.nail_api.mapper.UserMapper;
import sptech.school.nail_api.entity.User;
import sptech.school.nail_api.repository.UserRepository;

import java.util.Locale;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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

        user.setUserName(dto.getName().trim());
        user.setPhone(dto.getPhone() == null || dto.getPhone().isBlank() ? null : dto.getPhone().trim());

        return UserMapper.userToUserResponse(userRepository.save(user));
    }

    public void updatePassword(UserUpdatePasswordRequestDTO dto, Integer id){
        User user = findActiveUser(id);

        if(user == null || !Boolean.TRUE.equals(user.getActive())){
            throw new InvalidUserStatusException();
        }

        if (!passwordEncoder.matches(dto.getSenhaAtual(), user.getPasswordHash())){
            throw new InvalidCredentialsException();
        }

        if (passwordEncoder.matches(dto.getSenhaNova(), user.getPasswordHash())) {
            throw new IllegalArgumentException("The new password must be different from the current password.");
        }

        user.setPasswordHash(passwordEncoder.encode(dto.getSenhaNova()));

        userRepository.save(user);
    }

    public void delete(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        user.setActive(false);
        userRepository.save(user);
    }
}