package sptech.school.nail_api.dto.appointment;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public class AppointmentRequestDTO {

    @Schema(description = "Identificador da modalidade do serviço.", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    @Positive
    private Integer serviceOptionId;

    @Schema(description = "Identificador do adicional opcional. Pode ser nulo quando nenhum adicional for escolhido.", example = "2")
    @Positive
    private Integer addonId;

    @Schema(description = "Data e horário de início do atendimento.", type = "string", format = "date-time", example = "2026-10-30T09:00:00", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private LocalDateTime scheduledStartAt;

    public AppointmentRequestDTO() {
    }

    public AppointmentRequestDTO(Integer serviceOptionId, Integer addonId, LocalDateTime scheduledStartAt) {
        this.serviceOptionId = serviceOptionId;
        this.addonId = addonId;
        this.scheduledStartAt = scheduledStartAt;
    }

    public Integer getServiceOptionId() {
        return serviceOptionId;
    }

    public void setServiceOptionId(Integer serviceOptionId) {
        this.serviceOptionId = serviceOptionId;
    }

    public Integer getAddonId() {
        return addonId;
    }

    public void setAddonId(Integer addonId) {
        this.addonId = addonId;
    }

    public LocalDateTime getScheduledStartAt() {
        return scheduledStartAt;
    }

    public void setScheduledStartAt(LocalDateTime scheduledStartAt) {
        this.scheduledStartAt = scheduledStartAt;
    }
}