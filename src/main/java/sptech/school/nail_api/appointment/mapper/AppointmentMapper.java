package sptech.school.nail_api.appointment.mapper;

import org.springframework.stereotype.Component;
import sptech.school.nail_api.appointment.dto.AppointmentResponseDTO;
import sptech.school.nail_api.appointment.domain.Appointment;

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
                appointment.getOptionTypeSnapshot(),
                appointment.getScheduledStartAt(),
                appointment.getScheduledEndAt(),
                appointment.getServicePriceSnapshot(),
                appointment.getAddonNameSnapshot(),
                appointment.getAddonPriceSnapshot(),
                appointment.getAppointmentValue(),
                appointment.getAppointmentStatus(),
                appointment.getCreatedAt(),
                appointment.getUpdatedAt()
        );
    }
}