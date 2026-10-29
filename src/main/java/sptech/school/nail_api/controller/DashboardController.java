package sptech.school.nail_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sptech.school.nail_api.dto.dashboard.DashboardResponseDTO;
import sptech.school.nail_api.mapper.DashboardMapper;
import sptech.school.nail_api.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public ResponseEntity<DashboardResponseDTO> getDashboard() {
        Integer totalAppointmentsWeek = dashboardService.getTotalAppointmentsWeek();
        Integer completedAppointments = dashboardService.getCompletedAppointmentsWeek();
        Integer canceledAppointments = dashboardService.getCanceledAppointmentsWeek();

        DashboardResponseDTO responseDTO = DashboardMapper.dashboardToDashboardResponse(
                totalAppointmentsWeek,
                completedAppointments,
                canceledAppointments);

        return ResponseEntity.status(200).body(responseDTO);
    }
}