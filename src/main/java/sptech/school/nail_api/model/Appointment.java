package sptech.school.nail_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAppointment;
    private LocalDate appointmentDate;
    private LocalTime initHour;
    private LocalTime estimatedEndTime;

    public Appointment() {
    }

    public Appointment(Integer idAppointment, LocalDate appointmentDate, LocalTime initHour, LocalTime estimatedEndTime) {
        this.idAppointment = idAppointment;
        this.appointmentDate = appointmentDate;
        this.initHour = initHour;
        this.estimatedEndTime = estimatedEndTime;
    }

    public Integer getIdAppointment() {
        return idAppointment;
    }

    public void setIdAppointment(Integer idAppointment) {
        this.idAppointment = idAppointment;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getInitHour() {
        return initHour;
    }

    public void setInitHour(LocalTime initHour) {
        this.initHour = initHour;
    }

    public LocalTime getEstimatedEndTime() {
        return estimatedEndTime;
    }

    public void setEstimatedEndTime(LocalTime estimatedEndTime) {
        this.estimatedEndTime = estimatedEndTime;
    }
}
