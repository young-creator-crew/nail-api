package sptech.school.nail_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sptech.school.nail_api.entity.Appointment;

import java.time.LocalDateTime;
import java.util.List;

public interface DashboardRepository extends JpaRepository<Appointment, Integer> {

    @Query(value = """
            SELECT
                COUNT(*),
                COALESCE(
                    SUM(
                        CASE
                            WHEN appointment_status = 'CONCLUIDO'
                            THEN appointment_value
                            ELSE 0
                        END
                    ),
                    0.00
                ),
                COALESCE(
                    SUM(
                        CASE
                            WHEN appointment_status = 'CANCELADO'
                            THEN 1
                            ELSE 0
                        END
                    ),
                    0
                )
            FROM appointments
            WHERE scheduled_start_at >= :startDateTime
              AND scheduled_start_at < :endDateExclusive
            """, nativeQuery = true)
    List<Object[]> findKpis(
            @Param("startDateTime") LocalDateTime startDateTime,
            @Param("endDateExclusive") LocalDateTime endDateExclusive
    );

    @Query(value = """
            SELECT
                DATE(scheduled_start_at),
                appointment_status,
                COUNT(*)
            FROM appointments
            WHERE scheduled_start_at >= :startDateTime
              AND scheduled_start_at < :endDateExclusive
            GROUP BY DATE(scheduled_start_at), appointment_status
            ORDER BY DATE(scheduled_start_at)
            """, nativeQuery = true)
    List<Object[]> findDailyStatuses(
            @Param("startDateTime") LocalDateTime startDateTime,
            @Param("endDateExclusive") LocalDateTime endDateExclusive
    );
}