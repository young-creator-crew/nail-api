package sptech.school.nail_api.dto.dashboard;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;

@Schema(description = "Resumo do painel para um intervalo de datas selecionado.")
public class DashboardResponseDTO {

    @Schema(description = "Data de início do período de referência.", example = "2026-10-01")
    private LocalDate startDate;

    @Schema(description = "Data de término do período de referência.", example = "2026-10-31")
    private LocalDate endDate;

    @Schema(description = "Indicadores-chave de desempenho para o período selecionado.")
    private DashboardKpiResponseDTO kpis;

    @ArraySchema(
            arraySchema = @Schema(description = "Contagem diária de agendamentos, incluindo datas sem agendamentos."),
            schema = @Schema(implementation = AppointmentDailySummaryResponseDTO.class)
    )
    private List<AppointmentDailySummaryResponseDTO> appointmentsByDay;

    public DashboardResponseDTO() {
    }

    public DashboardResponseDTO(LocalDate startDate, LocalDate endDate, DashboardKpiResponseDTO kpis, List<AppointmentDailySummaryResponseDTO> appointmentsByDay) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.kpis = kpis;
        this.appointmentsByDay = appointmentsByDay;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public DashboardKpiResponseDTO getKpis() {
        return kpis;
    }

    public void setKpis(DashboardKpiResponseDTO kpis) {
        this.kpis = kpis;
    }

    public List<AppointmentDailySummaryResponseDTO> getAppointmentsByDay() {
        return appointmentsByDay;
    }

    public void setAppointmentsByDay(List<AppointmentDailySummaryResponseDTO> appointmentsByDay) {
        this.appointmentsByDay = appointmentsByDay;
    }
}