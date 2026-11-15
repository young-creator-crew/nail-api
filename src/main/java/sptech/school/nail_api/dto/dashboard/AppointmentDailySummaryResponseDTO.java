package sptech.school.nail_api.dto.dashboard;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Contagem diária de agendamentos, agrupados por status atual.")
public class AppointmentDailySummaryResponseDTO {

    @Schema(description = "Data representada por este ponto de dados.", example = "2026-10-01")
    private LocalDate date;

    @Schema(description = "Agendamentos atualmente com status AGENDADO.", example = "5", minimum = "0")
    private long scheduled;

    @Schema(description = "Agendamentos atualmente com status CONFIRMADO.", example = "3", minimum = "0")
    private long confirmed;

    @Schema(description = "Agendamentos atualmente com status CONCLUIDO.", example = "2", minimum = "0")
    private long completed;

    @Schema(description = "Agendamentos atualmente com status CANCELADO.", example = "1", minimum = "0")
    private long cancelled;

    public AppointmentDailySummaryResponseDTO() {
    }

    public AppointmentDailySummaryResponseDTO(LocalDate date, long scheduled, long confirmed, long completed, long cancelled) {
        this.date = date;
        this.scheduled = scheduled;
        this.confirmed = confirmed;
        this.completed = completed;
        this.cancelled = cancelled;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getScheduled() {
        return scheduled;
    }

    public void setScheduled(long scheduled) {
        this.scheduled = scheduled;
    }

    public long getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(long confirmed) {
        this.confirmed = confirmed;
    }

    public long getCompleted() {
        return completed;
    }

    public void setCompleted(long completed) {
        this.completed = completed;
    }

    public long getCancelled() {
        return cancelled;
    }

    public void setCancelled(long cancelled) {
        this.cancelled = cancelled;
    }
}