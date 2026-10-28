package sptech.school.nail_api.dto.dashboard;

public class DashboardResponseDTO {

    private Integer totalAppointmentsWeek;
    private Integer completedAppointments;
    private Integer canceledAppointments;

    public DashboardResponseDTO() {
    }

    public DashboardResponseDTO(Integer totalAppointmentsWeek, Integer completedAppointments, Integer canceledAppointments) {
        this.totalAppointmentsWeek = totalAppointmentsWeek;
        this.completedAppointments = completedAppointments;
        this.canceledAppointments = canceledAppointments;
    }

    public Integer getTotalAppointmentsWeek() {
        return totalAppointmentsWeek;
    }

    public void setTotalAppointmentsWeek(Integer totalAppointmentsWeek) {
        this.totalAppointmentsWeek = totalAppointmentsWeek;
    }

    public Integer getCompletedAppointments() {
        return completedAppointments;
    }

    public void setCompletedAppointments(Integer completedAppointments) {
        this.completedAppointments = completedAppointments;
    }

    public Integer getCanceledAppointments() {
        return canceledAppointments;
    }

    public void setCanceledAppointments(Integer canceledAppointments) {
        this.canceledAppointments = canceledAppointments;
    }
}
