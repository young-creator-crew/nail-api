package sptech.school.nail_api.service;

import org.springframework.stereotype.Service;
import sptech.school.nail_api.exception.procedure.ProcedureNotFoundException;
import sptech.school.nail_api.model.Procedure;
import sptech.school.nail_api.repository.ProceduresRepository;

import java.util.Optional;

@Service
public class ProceduresService {
    private final ProceduresRepository proceduresRepository;

    public ProceduresService(ProceduresRepository proceduresRepository) {
        this.proceduresRepository = proceduresRepository;
    }

    public Procedure get(Integer id) {
        Optional<Procedure> procedure = proceduresRepository.findById(id);
        if (procedure.isEmpty()) { throw new ProcedureNotFoundException(id); }
        return procedure.get();
    }

    public Procedure create(Procedure procedure) {
        return proceduresRepository.save(procedure);
    }

    public Procedure update(Procedure request, Integer id) {
        Procedure procedure = get(id);
        procedure.setName(request.getName());
        procedure.setPrice(request.getPrice());
        procedure.setEstimatedDurationMinutes(request.getEstimatedDurationMinutes());
        return proceduresRepository.save(procedure);
    }

    public void delete(Integer id) {
        if (!proceduresRepository.existsById(id)) {
            throw new ProcedureNotFoundException(id);
        }
        proceduresRepository.deleteById(id);
    }

}
