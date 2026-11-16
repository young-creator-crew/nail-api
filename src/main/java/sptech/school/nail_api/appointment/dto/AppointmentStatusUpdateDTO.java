package sptech.school.nail_api.appointment.dto;

import jakarta.validation.constraints.NotNull;
import sptech.school.nail_api.appointment.domain.AppointmentStatus;

import io.swagger.v3.oas.annotations.media.Schema;

public class AppointmentStatusUpdateDTO {

    @Schema(
            description = "Novo status do agendamento. Valores aceitos pela rota administrativa: CONFIRMADO, CONCLUIDO ou CANCELADO.",
            allowableValues = {"CONFIRMADO", "CONCLUIDO", "CANCELADO"},
            example = "CONFIRMADO",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull
    private AppointmentStatus status;

    public AppointmentStatusUpdateDTO() {
    }

    public AppointmentStatusUpdateDTO(AppointmentStatus status) {
        this.status = status;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
}