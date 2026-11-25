package sptech.school.nail_api.dto.appointment;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public class AppointmentRequestDTO {

    @Schema(description = "Identificador do serviço.", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    @Positive
    private Integer serviceId;

    @Schema(description = "Data e horário de início do atendimento.", type = "string", format = "date-time", example = "2026-10-30T09:00:00", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private LocalDateTime scheduledStartAt;

    public AppointmentRequestDTO() {
    }

    public AppointmentRequestDTO(Integer serviceId, LocalDateTime scheduledStartAt) {
        this.serviceId = serviceId;
        this.scheduledStartAt = scheduledStartAt;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public LocalDateTime getScheduledStartAt() {
        return scheduledStartAt;
    }

    public void setScheduledStartAt(LocalDateTime scheduledStartAt) {
        this.scheduledStartAt = scheduledStartAt;
    }
}
