package sptech.school.nail_api.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.nail_api.dto.procedures.ProceduresRequestDTO;
import sptech.school.nail_api.dto.procedures.ProceduresResponseDTO;
import sptech.school.nail_api.mapper.ProceduresMapper;
import sptech.school.nail_api.model.Procedure;
import sptech.school.nail_api.service.ProceduresService;

@RestController
@RequestMapping("/procedures")
public class ProceduresController {

 private final ProceduresService proceduresService;

    public ProceduresController(ProceduresService proceduresService) {
        this.proceduresService = proceduresService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProceduresResponseDTO> getProcedure(@PathVariable Integer id) {
        Procedure procedure = proceduresService.get(id);
        return ResponseEntity.status(200).body(ProceduresMapper.procedureToProcedureResponse(procedure));
    }

    @PostMapping
    public ResponseEntity<ProceduresResponseDTO> createProcedure(@Valid @RequestBody ProceduresRequestDTO request) {
        Procedure response = proceduresService.create(ProceduresMapper.registerRequestToProcedure(request));
        return ResponseEntity.status(201).body(ProceduresMapper.procedureToProcedureResponse(response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProceduresResponseDTO> updateProcedure(@Valid @RequestBody ProceduresRequestDTO request, @PathVariable Integer id) {
        Procedure response = proceduresService.update(ProceduresMapper.updateRequestToProcedure(request), id);
        return ResponseEntity.status(200).body(ProceduresMapper.procedureToProcedureResponse(response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeProcedure(@PathVariable Integer id) {
        proceduresService.delete(id);
        return ResponseEntity.status(204).build();
    }
}
