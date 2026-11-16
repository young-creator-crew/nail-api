package sptech.school.nail_api.shared.exception;

public class DataBaseAccessException extends RuntimeException {
    public DataBaseAccessException(String message) {
        super(message);
    }

    public DataBaseAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
