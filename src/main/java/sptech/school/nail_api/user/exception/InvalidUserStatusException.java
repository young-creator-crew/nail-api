package sptech.school.nail_api.user.exception;

public class InvalidUserStatusException extends RuntimeException {
    public InvalidUserStatusException() {
        super("User unavailable");
    }
}
