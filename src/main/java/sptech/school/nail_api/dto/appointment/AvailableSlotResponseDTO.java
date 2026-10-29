package sptech.school.nail_api.dto.appointment;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

public class AvailableSlotResponseDTO {

    @Schema(description = "Data e horário de início disponível.", type = "string", format = "date-time", example = "2026-10-30T09:00:00")
    private LocalDateTime startAt;

    @Schema(description = "Data e horário previsto para o término.", type = "string", format = "date-time", example = "2026-10-30T10:30:00")
    private LocalDateTime endAt;

    public AvailableSlotResponseDTO() {
    }

    public AvailableSlotResponseDTO(LocalDateTime startAt, LocalDateTime endAt) {
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalDateTime startAt) {
        this.startAt = startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }

    public void setEndAt(LocalDateTime endAt) {
        this.endAt = endAt;
    }
}