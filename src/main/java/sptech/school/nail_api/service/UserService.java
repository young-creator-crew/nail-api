package sptech.school.nail_api.service;

import org.springframework.stereotype.Service;
import sptech.school.nail_api.exception.user.UserAlreadyExistsException;
import sptech.school.nail_api.exception.user.UserNotFoundException;
import sptech.school.nail_api.model.User;
import sptech.school.nail_api.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User get(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) { throw new UserNotFoundException(id); }
        return user.get();
    }

    public User update(User request, Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) { throw new UserNotFoundException(id); }
        if (userRepository.existsByEmailAndIdNot(request.getEmail(), id)) { throw new UserAlreadyExistsException();}
        return userRepository.save(request);
    }

    public void delete(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }
}
