package sptech.school.nail_api.dto.appointment;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public class AppointmentRequestDTO {

    @NotNull
    @Positive
    private Integer serviceOptionId;

    @Positive
    private Integer addonId;

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