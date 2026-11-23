package sptech.school.nail_api.exception.service;

public class ServiceNotActiveExeption extends RuntimeException {
    public ServiceNotActiveExeption() {
        super("The requested service is not active");
    }
}
