package sptech.school.nail_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.school.nail_api.model.Appointment;

import java.time.LocalDate;

public interface DashboardRepository extends JpaRepository<Appointment, Integer> {
    Long countByAppointmentDateBetween(LocalDate startDate, LocalDate endDate);
}
