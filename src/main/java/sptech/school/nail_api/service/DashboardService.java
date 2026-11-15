package sptech.school.nail_api.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import sptech.school.nail_api.dto.dashboard.DashboardResponseDTO;
import sptech.school.nail_api.mapper.DashboardMapper;
import sptech.school.nail_api.repository.DashboardRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class DashboardService {

    private static final long MAX_PERIOD_DAYS = 366;

    private final DashboardRepository dashboardRepository;

    public DashboardService(DashboardRepository dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }

    @Transactional(readOnly = true)
    public DashboardResponseDTO getSummary(LocalDate startDate, LocalDate endDate) {

        if (startDate == null || endDate == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Both startDate and endDate are required.");
        }

        if (endDate.isBefore(startDate)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The end date must not be before the start date.");
        }

        long periodDays = ChronoUnit.DAYS.between(startDate, endDate) + 1;

        if (periodDays > MAX_PERIOD_DAYS) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The reporting period must not exceed 366 days.");
        }

        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateExclusive = endDate.plusDays(1).atStartOfDay();

        List<Object[]> kpiRows = dashboardRepository.findKpis(startDateTime, endDateExclusive);

        List<Object[]> dailyStatuses = dashboardRepository.findDailyStatuses(startDateTime, endDateExclusive);

        return DashboardMapper.toResponse(
                startDate,
                endDate,
                kpiRows.getFirst(),
                dailyStatuses
        );
    }
}