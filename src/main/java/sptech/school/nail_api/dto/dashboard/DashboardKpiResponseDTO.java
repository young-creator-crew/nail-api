package sptech.school.nail_api.dto.dashboard;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Indicadores-chave de desempenho para o período selecionado.")
public class DashboardKpiResponseDTO {

    @Schema(example = "128", minimum = "0", description = "Número total de agendamentos com início previsto dentro do período selecionado, independentemente do status.")
    private long totalAppointments;

    @Schema(example = "8450.00", minimum = "0", description = "Soma dos valores dos agendamentos marcados atualmente como concluídos dentro do período selecionado.")
    private BigDecimal completedRevenue;

    @Schema(example = "8.59", minimum = "0", maximum = "100", description = "Percentual de agendamentos no período selecionado que estão marcados como cancelados. Varia de 0 a 100 e é arredondado para duas casas decimais.")
    private BigDecimal cancellationRate;

    public DashboardKpiResponseDTO() {
    }

    public DashboardKpiResponseDTO(long totalAppointments, BigDecimal completedRevenue, BigDecimal cancellationRate) {
        this.totalAppointments = totalAppointments;
        this.completedRevenue = completedRevenue;
        this.cancellationRate = cancellationRate;
    }

    public long getTotalAppointments() {
        return totalAppointments;
    }

    public void setTotalAppointments(long totalAppointments) {
        this.totalAppointments = totalAppointments;
    }

    public BigDecimal getCompletedRevenue() {
        return completedRevenue;
    }

    public void setCompletedRevenue(BigDecimal completedRevenue) {
        this.completedRevenue = completedRevenue;
    }

    public BigDecimal getCancellationRate() {
        return cancellationRate;
    }

    public void setCancellationRate(BigDecimal cancellationRate) {
        this.cancellationRate = cancellationRate;
    }
}