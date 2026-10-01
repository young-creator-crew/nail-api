package sptech.school.nail_api.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAppointment;
    private LocalDate appointmentDate;
    private LocalTime initHour;
    private LocalTime estimatedEndTime;
}
