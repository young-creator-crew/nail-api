package sptech.school.nail_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sptech.school.nail_api.entity.Appointment;
import sptech.school.nail_api.entity.User;
import sptech.school.nail_api.enums.AppointmentStatus;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    List<Appointment> findAllByUserOrderByScheduledStartAtDesc(User user);

    Optional<Appointment> findByIdAppointmentAndUser(Integer idAppointment, User user);

    List<Appointment> findByScheduledStartAtGreaterThanEqualAndScheduledStartAtLessThanOrderByScheduledStartAtAsc(
            LocalDateTime startOfDay,
            LocalDateTime startOfNextDay
    );

    @Query("""
        SELECT a
        FROM Appointment a
        WHERE a.scheduledStartAt < :endAt
          AND a.scheduledEndAt > :startAt
          AND a.appointmentStatus IN :statuses
    """)
    List<Appointment> findOverlappingAppointments(
            @Param("startAt") LocalDateTime startAt,
            @Param("endAt") LocalDateTime endAt,
            @Param("statuses") Collection<AppointmentStatus> statuses
    );

    @Query("""
        SELECT a
        FROM Appointment a
        WHERE a.scheduledStartAt < :endAt
          AND a.scheduledEndAt > :startAt
          AND a.appointmentStatus IN :statuses
          AND a.idAppointment <> :excludeId
    """)
    List<Appointment> findOverlappingAppointmentsExcluding(
            @Param("startAt") LocalDateTime startAt,
            @Param("endAt") LocalDateTime endAt,
            @Param("statuses") Collection<AppointmentStatus> statuses,
            @Param("excludeId") Integer excludeId
    );
}