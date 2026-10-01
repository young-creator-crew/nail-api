package sptech.school.nail_api.exception.auth;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
      super("Invalid email or password!");
    }
}