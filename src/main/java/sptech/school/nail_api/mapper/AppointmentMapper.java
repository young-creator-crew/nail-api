package sptech.school.nail_api.mapper;

import org.springframework.stereotype.Component;
import sptech.school.nail_api.dto.appointment.AppointmentResponseDTO;
import sptech.school.nail_api.entity.Appointment;

@Component
public class AppointmentMapper {

    public AppointmentResponseDTO toResponseDTO(Appointment appointment) {

        return new AppointmentResponseDTO(
                appointment.getIdAppointment(),
                appointment.getUser().getIdUser(),
                appointment.getUser().getUserName(),
                appointment.getUser().getEmail(),
                appointment.getUser().getPhone(),
                appointment.getServiceNameSnapshot(),
                appointment.getScheduledStartAt(),
                appointment.getScheduledEndAt(),
                appointment.getServicePriceSnapshot(),
                appointment.getAppointmentValue(),
                appointment.getAppointmentStatus(),
                appointment.getCreatedAt(),
                appointment.getUpdatedAt()
        );
    }
}
