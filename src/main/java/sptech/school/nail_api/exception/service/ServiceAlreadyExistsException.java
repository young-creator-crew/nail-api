package sptech.school.nail_api.exception.service;

public class ServiceAlreadyExistsException extends RuntimeException {
    public ServiceAlreadyExistsException() {
        super("A service with this name already exists.");
    }
}
