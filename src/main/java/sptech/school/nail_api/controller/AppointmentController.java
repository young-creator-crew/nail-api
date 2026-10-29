package sptech.school.nail_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

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
@Tag(
        name = "Agendamentos",
        description = "Operações de consulta, criação, alteração e cancelamento de agendamentos."
)
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // GET: horários disponíveis para a data selecionada.
    @Operation(
            summary = "Consultar horários disponíveis",
            description = "Retorna os horários disponíveis para a data e modalidade informadas, considerando a duração do serviço, o expediente e os agendamentos existentes."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Horários disponíveis retornados com sucesso.",
                    content = @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = AvailableSlotResponseDTO.class)
                            )
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Data inválida, passada ou parâmetros incorretos."),
            @ApiResponse(responseCode = "404", description = "Modalidade de serviço não encontrada.")
    })
    @GetMapping("/availability")
    public ResponseEntity<List<AvailableSlotResponseDTO>> getAvailableSlots(
            @Parameter(description = "Data desejada para o agendamento.", example = "2026-10-30", required = true)
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date,

            @Parameter(description = "Identificador da modalidade do serviço, utilizado para calcular a duração.", example = "1", required = true)
            @RequestParam
            Integer serviceOptionId
    ) {
        return ResponseEntity.ok(
                appointmentService.getAvailableSlots(date, serviceOptionId)
        );
    }

    // POST: criar um agendamento.
    @Operation(
            summary = "Criar agendamento",
            description = "Cria um agendamento para o cliente identificado na rota, validando a modalidade, o adicional, o expediente e a disponibilidade."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Agendamento criado com sucesso.",
                    content = @Content(
                            schema = @Schema(implementation = AppointmentResponseDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou horário fora das regras de funcionamento."),
            @ApiResponse(responseCode = "404", description = "Cliente, modalidade ou adicional não encontrado."),
            @ApiResponse(responseCode = "409", description = "Horário indisponível ou conflitante.")
    })
    @PostMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> create(
            @Parameter(description = "Identificador do cliente que realizará o agendamento.", example = "5", required = true)
            @PathVariable("id")
            Integer id,

            @Valid
            @RequestBody
            AppointmentRequestDTO request
    ) {
        AppointmentResponseDTO response =
                appointmentService.create(id, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // GET: todos os agendamentos do usuário.
    @Operation(
            summary = "Listar agendamentos do cliente",
            description = "Retorna todos os agendamentos do cliente informado, incluindo agendados, confirmados, concluídos e cancelados."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Agendamentos retornados com sucesso.",
                    content = @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = AppointmentResponseDTO.class)
                            )
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<List<AppointmentResponseDTO>> getMine(
            @Parameter(description = "Identificador do cliente.", example = "5", required = true)
            @PathVariable("id")
            Integer id
    ) {
        return ResponseEntity.ok(
                appointmentService.getMyAppointments(id)
        );
    }

    // PUT: alterar serviço, modalidade, horário e adicional.
    @Operation(
            summary = "Alterar agendamento",
            description = "Atualiza o serviço, a modalidade, o adicional e o horário de um agendamento. A alteração é permitida apenas para agendamentos agendados ou confirmados, com nova validação de disponibilidade."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Agendamento atualizado com sucesso.",
                    content = @Content(
                            schema = @Schema(implementation = AppointmentResponseDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou horário fora das regras de funcionamento."),
            @ApiResponse(responseCode = "404", description = "Cliente ou agendamento não encontrado."),
            @ApiResponse(responseCode = "409", description = "Horário indisponível ou status que não permite alteração.")
    })
    @PutMapping("/{userId}/{idAppointment}")
    public ResponseEntity<AppointmentResponseDTO> update(
            @Parameter(description = "Identificador do cliente.", example = "5", required = true)
            @PathVariable("userId")
            Integer userId,

            @Parameter(description = "Identificador do agendamento que será alterado.", example = "10", required = true)
            @PathVariable("idAppointment")
            Integer appointmentId,

            @Valid
            @RequestBody
            AppointmentRequestDTO request
    ) {
        return ResponseEntity.ok(
                appointmentService.update(userId, appointmentId, request)
        );
    }

    // DELETE: cancela o agendamento mantendo o registro no banco.
    @Operation(
            summary = "Cancelar agendamento",
            description = "Altera o status do agendamento para CANCELADO sem excluir o registro do banco. Permitido somente para agendamentos agendados ou confirmados."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Agendamento cancelado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Cliente ou agendamento não encontrado."),
            @ApiResponse(responseCode = "409", description = "O status atual não permite o cancelamento.")
    })
    @DeleteMapping("/{userId}/{idAppointment}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "Identificador do cliente.", example = "5", required = true)
            @PathVariable("userId")
            Integer userId,

            @Parameter(description = "Identificador do agendamento que será cancelado.", example = "10", required = true)
            @PathVariable("idAppointment")
            Integer appointmentId
    ) {
        appointmentService.cancel(userId, appointmentId);

        return ResponseEntity.noContent().build();
    }
}