package sptech.school.nail_api.exception.procedure;

public class ProcedureNotFoundException extends RuntimeException{
    public ProcedureNotFoundException(Integer id) {
        super("No procedure exists with this id: " + id);
    }
}
