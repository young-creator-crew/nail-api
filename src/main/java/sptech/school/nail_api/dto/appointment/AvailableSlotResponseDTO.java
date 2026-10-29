package sptech.school.nail_api.dto.appointment;

import java.time.LocalDateTime;

public class AvailableSlotResponseDTO {

    private LocalDateTime startAt;
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