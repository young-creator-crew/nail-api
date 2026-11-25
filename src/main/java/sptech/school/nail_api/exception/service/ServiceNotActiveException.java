package sptech.school.nail_api.exception.service;

public class ServiceNotActiveException extends RuntimeException {
    public ServiceNotActiveException() {
        super("The requested service is not active");
    }
}
