package sptech.school.nail_api.exception.user;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super("A user with this email already exist!");
    }
}