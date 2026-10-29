package sptech.school.nail_api.controller;

import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sptech.school.nail_api.dto.appointment.*;
import sptech.school.nail_api.service.AppointmentService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // GET: horários disponíveis para a data selecionada.
    @GetMapping("/availability")
    public ResponseEntity<List<AvailableSlotResponseDTO>> getAvailableSlots(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam Integer serviceOptionId
    ) {
        return ResponseEntity.ok(appointmentService.getAvailableSlots(date, serviceOptionId));
    }

    // POST: criar um agendamento.
    @PostMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> create(
            @PathVariable Integer id,
            @Valid @RequestBody AppointmentRequestDTO request
    ) {
        AppointmentResponseDTO response = appointmentService.create(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // GET: todos os agendamentos do usuário autenticado.
    @GetMapping("/{id}")
    public ResponseEntity<List<AppointmentResponseDTO>> getMine(@PathVariable Integer id) {
        return ResponseEntity.ok(appointmentService.getMyAppointments(id));
    }

    // PUT: alterar serviço, modalidade, horário e adicional.
    @PutMapping("/{userId}/{idAppointment}")
    public ResponseEntity<AppointmentResponseDTO> update(
            @PathVariable Integer userId,
            @PathVariable Integer appointmentId,
            @Valid @RequestBody AppointmentRequestDTO request
    ) {
        return ResponseEntity.ok(appointmentService.update(userId, appointmentId, request));
    }

    // DELETE: cancela o agendamento mantendo o registro no banco.
    @DeleteMapping("/{userId}/{idAppointment}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer userId,
            @PathVariable Integer appointmentId
    ) {
        appointmentService.cancel(userId, appointmentId);
        return ResponseEntity.noContent().build();
    }
}