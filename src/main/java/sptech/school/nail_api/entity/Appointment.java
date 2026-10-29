package sptech.school.nail_api.entity;

import jakarta.persistence.*;
import sptech.school.nail_api.enums.AppointmentStatus;
import sptech.school.nail_api.enums.OptionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_appointment")
    private Integer idAppointment;

    @ManyToOne
    @JoinColumn(name = "fk_client", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "fk_nail_service_option", nullable = false)
    private NailServiceOption nailServiceOption;

    @ManyToOne
    @JoinColumn(name = "fk_nail_service_addon")
    private NailServiceAddon nailServiceAddon;

    @Column(name = "scheduled_start_at", nullable = false)
    private LocalDateTime scheduledStartAt;

    @Column(name = "scheduled_end_at", nullable = false)
    private LocalDateTime scheduledEndAt;

    @Column(name = "service_name_snapshot", nullable = false, length = 100)
    private String serviceNameSnapshot;

    @Enumerated(EnumType.STRING)
    @Column(name = "option_type_snapshot", nullable = false)
    private OptionType optionTypeSnapshot;

    @Column(name = "service_price_snapshot", nullable = false, precision = 10, scale = 2)
    private BigDecimal servicePriceSnapshot;

    @Column(name = "addon_name_snapshot", length = 100)
    private String addonNameSnapshot;

    @Column(name = "addon_price_snapshot", nullable = false, precision = 10, scale = 2)
    private BigDecimal addonPriceSnapshot = BigDecimal.ZERO;

    @Column(name = "appointment_value", nullable = false, precision = 10, scale = 2)
    private BigDecimal appointmentValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "appointment_status", nullable = false)
    private AppointmentStatus appointmentStatus = AppointmentStatus.AGENDADO;

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    public Appointment() {
    }

    public Integer getIdAppointment() {
        return idAppointment;
    }

    public void setIdAppointment(Integer idAppointment) {
        this.idAppointment = idAppointment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public NailServiceOption getNailServiceOption() {
        return nailServiceOption;
    }

    public void setNailServiceOption(NailServiceOption nailServiceOption) {
        this.nailServiceOption = nailServiceOption;
    }

    public NailServiceAddon getNailServiceAddon() {
        return nailServiceAddon;
    }

    public void setNailServiceAddon(NailServiceAddon nailServiceAddon) {
        this.nailServiceAddon = nailServiceAddon;
    }

    public LocalDateTime getScheduledStartAt() {
        return scheduledStartAt;
    }

    public void setScheduledStartAt(LocalDateTime scheduledStartAt) {
        this.scheduledStartAt = scheduledStartAt;
    }

    public LocalDateTime getScheduledEndAt() {
        return scheduledEndAt;
    }

    public void setScheduledEndAt(LocalDateTime scheduledEndAt) {
        this.scheduledEndAt = scheduledEndAt;
    }

    public String getServiceNameSnapshot() {
        return serviceNameSnapshot;
    }

    public void setServiceNameSnapshot(String serviceNameSnapshot) {
        this.serviceNameSnapshot = serviceNameSnapshot;
    }

    public OptionType getOptionTypeSnapshot() {
        return optionTypeSnapshot;
    }

    public void setOptionTypeSnapshot(OptionType optionTypeSnapshot) {
        this.optionTypeSnapshot = optionTypeSnapshot;
    }

    public BigDecimal getServicePriceSnapshot() {
        return servicePriceSnapshot;
    }

    public void setServicePriceSnapshot(BigDecimal servicePriceSnapshot) {
        this.servicePriceSnapshot = servicePriceSnapshot;
    }

    public String getAddonNameSnapshot() {
        return addonNameSnapshot;
    }

    public void setAddonNameSnapshot(String addonNameSnapshot) {
        this.addonNameSnapshot = addonNameSnapshot;
    }

    public BigDecimal getAddonPriceSnapshot() {
        return addonPriceSnapshot;
    }

    public void setAddonPriceSnapshot(BigDecimal addonPriceSnapshot) {
        this.addonPriceSnapshot = addonPriceSnapshot;
    }

    public BigDecimal getAppointmentValue() {
        return appointmentValue;
    }

    public void setAppointmentValue(BigDecimal appointmentValue) {
        this.appointmentValue = appointmentValue;
    }

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}