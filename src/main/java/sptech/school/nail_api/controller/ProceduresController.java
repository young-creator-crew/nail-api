package sptech.school.nail_api.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sptech.school.nail_api.dto.procedures.ProceduresRequestDTO;
import sptech.school.nail_api.dto.procedures.ProceduresResponseDTO;
import sptech.school.nail_api.mapper.ProceduresMapper;
import sptech.school.nail_api.model.Procedure;
import sptech.school.nail_api.service.ProceduresService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/procedures")
public class ProceduresController {

 private final ProceduresService proceduresService;

    public ProceduresController(ProceduresService proceduresService) {
        this.proceduresService = proceduresService;
    }

    @GetMapping
    public ResponseEntity<List<ProceduresResponseDTO>> getProcedures() {
        List<Procedure> procedures = proceduresService.getAll();
        List<ProceduresResponseDTO> response = new ArrayList<>();

        for (Procedure procedure : procedures) {
            response.add(ProceduresMapper.procedureToProcedureResponse(procedure));
        }

        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProceduresResponseDTO> getProcedure(@PathVariable Integer id) {
        Procedure procedure = proceduresService.get(id);
        return ResponseEntity.status(200).body(ProceduresMapper.procedureToProcedureResponse(procedure));
    }


    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<ProceduresResponseDTO> createProcedure(
            @RequestPart("procedure") ProceduresRequestDTO request,
            @RequestPart(value = "foto", required = false) MultipartFile foto) {

        Procedure procedure = ProceduresMapper.registerRequestToProcedure(request);

        Procedure response = proceduresService.create(procedure, foto);

        return ResponseEntity.status(201).body(
                ProceduresMapper.procedureToProcedureResponse(response)
        );
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<ProceduresResponseDTO> updateProcedure(
            @PathVariable Integer id,
            @RequestPart("procedure") ProceduresRequestDTO request,
            @RequestPart(value = "foto", required = false) MultipartFile foto) {

        Procedure procedure = ProceduresMapper.updateRequestToProcedure(request);

        Procedure response = proceduresService.update(procedure, id, foto);

        return ResponseEntity.status(200).body(
                ProceduresMapper.procedureToProcedureResponse(response)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeProcedure(@PathVariable Integer id) {
        proceduresService.delete(id);
        return ResponseEntity.status(204).build();
    }
}
