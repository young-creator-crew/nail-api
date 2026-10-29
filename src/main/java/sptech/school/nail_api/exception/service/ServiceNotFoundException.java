package sptech.school.nail_api.exception.service;


public class ServiceNotFoundException extends RuntimeException{
    public ServiceNotFoundException(Integer id) {
        super("No procedure exists with this id: " + id);
    }
}
