package sptech.school.nail_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.school.nail_api.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);
    Boolean existsByEmailAndIdNot(String email, Integer id);
}