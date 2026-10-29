package sptech.school.nail_api.mapper;

import sptech.school.nail_api.dto.dashboard.AppointmentDailySummaryResponseDTO;
import sptech.school.nail_api.dto.dashboard.DashboardKpiResponseDTO;
import sptech.school.nail_api.dto.dashboard.DashboardResponseDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DashboardMapper {

    public static DashboardResponseDTO toResponse(LocalDate startDate, LocalDate endDate, Object[] kpis, List<Object[]> dailyStatuses) {
        long totalAppointments = toLong(kpis[0]);
        BigDecimal completedRevenue = toBigDecimal(kpis[1]);
        long cancelledAppointments = toLong(kpis[2]);

        BigDecimal cancellationRate = BigDecimal.ZERO.setScale(2);

        if (totalAppointments > 0) {
            cancellationRate = BigDecimal
                    .valueOf(cancelledAppointments)
                    .multiply(BigDecimal.valueOf(100))
                    .divide(BigDecimal.valueOf(totalAppointments), 2, RoundingMode.HALF_UP);
        }

        DashboardKpiResponseDTO kpiResponse =
                new DashboardKpiResponseDTO(
                        totalAppointments,
                        completedRevenue,
                        cancellationRate
                );

        List<AppointmentDailySummaryResponseDTO> dailyResponse = createDailySeries(startDate, endDate, dailyStatuses);

        return new DashboardResponseDTO(
                startDate,
                endDate,
                kpiResponse,
                dailyResponse
        );
    }

    private static List<AppointmentDailySummaryResponseDTO> createDailySeries(LocalDate startDate, LocalDate endDate, List<Object[]> dailyStatuses) {
        Map<LocalDate, AppointmentDailySummaryResponseDTO> dailyMap = new LinkedHashMap<>();

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            dailyMap.put(date, new AppointmentDailySummaryResponseDTO(date, 0, 0, 0, 0));
        }

        for (Object[] row : dailyStatuses) {
            LocalDate date = toLocalDate(row[0]);
            String status = row[1].toString();
            long total = toLong(row[2]);

            AppointmentDailySummaryResponseDTO daily = dailyMap.get(date);

            if (daily == null) {
                continue;
            }

            switch (status) {
                case "AGENDADO" -> daily.setScheduled(total);
                case "CONFIRMADO" -> daily.setConfirmed(total);
                case "CONCLUIDO" -> daily.setCompleted(total);
                case "CANCELADO" -> daily.setCancelled(total);
                default -> {}
            }
        }

        return new ArrayList<>(dailyMap.values());
    }

    private static long toLong(Object value) {
        return ((Number) value).longValue();
    }

    private static BigDecimal toBigDecimal(Object value) {
        if (value == null) {
            return BigDecimal.ZERO.setScale(2);
        }

        if (value instanceof BigDecimal decimal) {
            return decimal;
        }

        return new BigDecimal(value.toString());
    }

    private static LocalDate toLocalDate(Object value) {
        if (value instanceof Date date) {
            return date.toLocalDate();
        }

        if (value instanceof Timestamp timestamp) {
            return timestamp.toLocalDateTime().toLocalDate();
        }

        if (value instanceof LocalDate date) {
            return date;
        }

        return LocalDate.parse(value.toString());
    }
}