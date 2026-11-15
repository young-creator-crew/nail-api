package sptech.school.nail_api.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sptech.school.nail_api.dto.auth.LoginRequestDTO;
import sptech.school.nail_api.dto.auth.RegisterRequestDTO;
import sptech.school.nail_api.dto.user.UserResponseDTO;
import sptech.school.nail_api.exception.auth.InvalidCredentialsException;
import sptech.school.nail_api.exception.user.UserAlreadyExistsException;
import sptech.school.nail_api.mapper.UserMapper;
import sptech.school.nail_api.entity.User;
import sptech.school.nail_api.repository.UserRepository;

import java.util.Locale;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO login(LoginRequestDTO dto) {
        String email = normalizeEmail(dto.getEmail());

        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null || !Boolean.TRUE.equals(user.getActive()) || !passwordEncoder.matches(dto.getPassword(), user.getPasswordHash())) {
            throw new InvalidCredentialsException();
        }

        return UserMapper.userToUserResponse(user);
    }

    public UserResponseDTO register(RegisterRequestDTO dto) {
        String email = normalizeEmail(dto.getEmail());

        if (userRepository.existsByEmail(email)) { throw new UserAlreadyExistsException(); }

        User user = UserMapper.registerRequestToUser(dto);

        user.setEmail(email);
        user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));

        return UserMapper.userToUserResponse(userRepository.save(user));
    }

    private String normalizeEmail(String email) {
        return email.trim().toLowerCase(Locale.ROOT);
    }

}