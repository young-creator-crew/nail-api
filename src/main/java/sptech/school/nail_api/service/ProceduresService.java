package sptech.school.nail_api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sptech.school.nail_api.exception.procedure.ProcedureNotFoundException;
import sptech.school.nail_api.model.Procedure;
import sptech.school.nail_api.repository.ProceduresRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProceduresService {
    private final ProceduresRepository proceduresRepository;
    private final FileStorageService fileStorageService;

    public ProceduresService(ProceduresRepository proceduresRepository, FileStorageService fileStorageService) {
        this.proceduresRepository = proceduresRepository;
        this.fileStorageService = fileStorageService;
    }

    public List<Procedure> getAll() {
        return proceduresRepository.findAll();
    }

    public Procedure get(Integer id) {
        Optional<Procedure> procedure = proceduresRepository.findById(id);
        if (procedure.isEmpty()) { throw new ProcedureNotFoundException(id); }
        return procedure.get();
    }

    public Procedure create(Procedure procedure, MultipartFile foto) {

            if (foto != null && !foto.isEmpty()) {
                String fotoUrl = fileStorageService.salvar(foto);
                procedure.setPhotoUrl(fotoUrl);
            }return proceduresRepository.save(procedure);
    }

    public Procedure update(Procedure request, Integer id,  MultipartFile foto) {
        Procedure procedure = get(id);
        procedure.setName(request.getName());
        procedure.setCategory(request.getCategory());
        procedure.setDescription(request.getDescription());
        procedure.setPrice(request.getPrice());
        procedure.setEstimatedDurationMinutes(
                request.getEstimatedDurationMinutes());

        if (foto != null && !foto.isEmpty()) {
            String fotoUrl = fileStorageService.salvar(foto);
            procedure.setPhotoUrl(fotoUrl);
        }
        return proceduresRepository.save(procedure);
    }

    public void delete(Integer id) {
        if (!proceduresRepository.existsById(id)) {
            throw new ProcedureNotFoundException(id);
        }
        proceduresRepository.deleteById(id);
    }

}
