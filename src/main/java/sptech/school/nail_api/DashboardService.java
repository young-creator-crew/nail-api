package sptech.school.nail_api;

import org.springframework.stereotype.Service;
import sptech.school.nail_api.repository.DashboardRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Service
public class DashboardService {

    private final DashboardRepository dashboardRepository;

    public DashboardService(DashboardRepository dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }

    public Integer getTotalAppointmentsWeek() {
        LocalDate today = LocalDate.now();

        LocalDate startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate endOfWeek = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        Long totalAppointments = dashboardRepository.countByAppointmentDateBetween(startOfWeek, endOfWeek);

        return Math.toIntExact(totalAppointments);
    }
}
