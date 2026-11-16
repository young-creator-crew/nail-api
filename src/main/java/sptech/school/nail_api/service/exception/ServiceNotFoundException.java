package sptech.school.nail_api.service.exception;


public class ServiceNotFoundException extends RuntimeException{
    public ServiceNotFoundException(Integer id) {
        super("No procedure exists with this id: " + id);
    }
}
