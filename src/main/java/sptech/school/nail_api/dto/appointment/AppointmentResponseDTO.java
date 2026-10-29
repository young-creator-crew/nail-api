package sptech.school.nail_api.dto.appointment;

import sptech.school.nail_api.enums.AppointmentStatus;
import sptech.school.nail_api.enums.OptionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AppointmentResponseDTO {

    private Integer idAppointment;
    private Integer clientId;
    private String clientName;
    private String clientEmail;
    private String clientPhone;
    private String serviceName;
    private OptionType optionType;
    private LocalDateTime scheduledStartAt;
    private LocalDateTime scheduledEndAt;
    private BigDecimal servicePrice;
    private String addonName;
    private BigDecimal addonPrice;
    private BigDecimal appointmentValue;
    private AppointmentStatus appointmentStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public AppointmentResponseDTO() {
    }

    public AppointmentResponseDTO(Integer idAppointment, Integer clientId, String clientName, String clientEmail, String clientPhone, String serviceName, OptionType optionType, LocalDateTime scheduledStartAt, LocalDateTime scheduledEndAt, BigDecimal servicePrice, String addonName, BigDecimal addonPrice, BigDecimal appointmentValue, AppointmentStatus appointmentStatus, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.idAppointment = idAppointment;
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.clientPhone = clientPhone;
        this.serviceName = serviceName;
        this.optionType = optionType;
        this.scheduledStartAt = scheduledStartAt;
        this.scheduledEndAt = scheduledEndAt;
        this.servicePrice = servicePrice;
        this.addonName = addonName;
        this.addonPrice = addonPrice;
        this.appointmentValue = appointmentValue;
        this.appointmentStatus = appointmentStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getIdAppointment() {
        return idAppointment;
    }

    public void setIdAppointment(Integer idAppointment) {
        this.idAppointment = idAppointment;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public OptionType getOptionType() {
        return optionType;
    }

    public void setOptionType(OptionType optionType) {
        this.optionType = optionType;
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

    public BigDecimal getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(BigDecimal servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getAddonName() {
        return addonName;
    }

    public void setAddonName(String addonName) {
        this.addonName = addonName;
    }

    public BigDecimal getAddonPrice() {
        return addonPrice;
    }

    public void setAddonPrice(BigDecimal addonPrice) {
        this.addonPrice = addonPrice;
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