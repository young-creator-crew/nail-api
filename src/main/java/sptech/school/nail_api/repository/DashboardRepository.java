package sptech.school.nail_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sptech.school.nail_api.model.Appointment;

import java.time.LocalDate;

public interface DashboardRepository extends JpaRepository<Appointment, Integer> {

    @Query(value = """
            SELECT COUNT(*)
            FROM Appointments
            WHERE appointment_date BETWEEN :startDate AND :endDate
            """, nativeQuery = true)
    Long countByAppointmentDateBetween(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query(value = """
            SELECT COUNT(*)
            FROM Appointments
            WHERE appointment_date BETWEEN :startDate AND :endDate
              AND appointment_status = 'CONCLUIDO'
            """, nativeQuery = true)
    Long countCompletedAppointmentsBetween(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query(value = """
            SELECT COUNT(*)
            FROM Appointments
            WHERE appointment_date BETWEEN :startDate AND :endDate
              AND appointment_status = 'CANCELADO'
            """, nativeQuery = true)
    Long countCanceledAppointmentsBetween(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}