package sptech.school.nail_api.mapper;

import sptech.school.nail_api.dto.dashboard.DashboardResponseDTO;

public class DashboardMapper {

    public static DashboardResponseDTO dashboardToDashboardResponse(
            Integer totalAppointmentsWeek,
            Integer completedAppointments,
            Integer canceledAppointments) {

        return new DashboardResponseDTO(totalAppointmentsWeek, completedAppointments, canceledAppointments);
    }
}
