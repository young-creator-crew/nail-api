package sptech.school.nail_api.dto.appointment;

import sptech.school.nail_api.enums.AppointmentStatus;
import sptech.school.nail_api.enums.OptionType;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AppointmentResponseDTO {

    @Schema(description = "Identificador do agendamento.", example = "10")
    private Integer idAppointment;

    @Schema(description = "Identificador do cliente.", example = "5")
    private Integer clientId;

    @Schema(description = "Nome do cliente.", example = "Maria Silva")
    private String clientName;

    @Schema(description = "E-mail do cliente.", example = "maria@email.com")
    private String clientEmail;

    @Schema(description = "Telefone do cliente.", example = "11987654321")
    private String clientPhone;

    @Schema(description = "Nome do serviço contratado.", example = "Alongamento em Gel")
    private String serviceName;

    @Schema(description = "Modalidade contratada.", example = "APLICACAO")
    private OptionType optionType;

    @Schema(description = "Data e horário de início.", type = "string", format = "date-time", example = "2026-10-30T09:00:00")
    private LocalDateTime scheduledStartAt;

    @Schema(description = "Data e horário previstos para o término.", type = "string", format = "date-time", example = "2026-10-30T10:30:00")
    private LocalDateTime scheduledEndAt;

    @Schema(description = "Preço da modalidade no momento do agendamento.", example = "120.00")
    private BigDecimal servicePrice;

    @Schema(description = "Nome do adicional contratado, se houver.", example = "Decoração")
    private String addonName;

    @Schema(description = "Preço do adicional no momento do agendamento.", example = "15.00")
    private BigDecimal addonPrice;

    @Schema(description = "Valor total contratado.", example = "135.00")
    private BigDecimal appointmentValue;

    @Schema(description = "Status atual do agendamento.", example = "CONFIRMADO")
    private AppointmentStatus appointmentStatus;

    @Schema(description = "Data de criação do registro.", type = "string", format = "date-time", example = "2026-10-20T14:30:00")
    private LocalDateTime createdAt;

    @Schema(description = "Data da última atualização do registro.", type = "string", format = "date-time", example = "2026-10-20T14:35:00")
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