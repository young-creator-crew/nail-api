package sptech.school.nail_api.service;

import org.springframework.stereotype.Service;
import sptech.school.nail_api.exception.auth.InvalidCredentialsException;
import sptech.school.nail_api.exception.user.UserAlreadyExistsException;
import sptech.school.nail_api.model.User;
import sptech.school.nail_api.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null || !user.getPassword().equals(password)) { throw new InvalidCredentialsException(); }
        return user;
    }

    public User register(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) { throw new UserAlreadyExistsException(); }
        return userRepository.save(user);
    }
}
