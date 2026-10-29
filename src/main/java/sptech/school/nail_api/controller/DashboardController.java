package sptech.school.nail_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.nail_api.dto.dashboard.DashboardResponseDTO;
import sptech.school.nail_api.service.DashboardService;

import java.time.LocalDate;

@RestController
@RequestMapping("/dashboard")
@Tag(name = "Dashboard", description = "Operações para recuperar indicadores de painel e estatísticas de agendamentos.")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/summary")
    @Operation(summary = "Obter resumo do painel", description = "Retorna KPIs de agendamentos e a contagem diária de agendamentos por status para o período selecionado.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Resumo do painel recuperado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Intervalo de datas inválido. A data de início não deve ser posterior à data de término.")
    })
    public ResponseEntity<DashboardResponseDTO> getSummary(
            @Parameter(description = "Data de início do período de referência.", example = "2026-10-01", required = true)
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @Parameter(description = "Data de término do período de referência.", example = "2026-10-31", required = true)
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return ResponseEntity.ok(dashboardService.getSummary(startDate, endDate));
    }
}