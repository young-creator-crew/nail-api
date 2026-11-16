package sptech.school.nail_api.exception.user;

public class InvalidUserStatusException extends RuntimeException {
    public InvalidUserStatusException() {
        super("User unavailable");
    }
}
