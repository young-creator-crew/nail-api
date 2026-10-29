package sptech.school.nail_api.dto.appointment;

import jakarta.validation.constraints.NotNull;
import sptech.school.nail_api.enums.AppointmentStatus;

public class AppointmentStatusUpdateDTO {

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