package sptech.school.nail_api.dto.service.create;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "Solicitar dados para a criação de um serviço completo, incluindo suas modalidades e complementos.")
public class ServiceCreateRequestDTO {

    @Schema(description = "Nome do serviço.", example = "Alongamento de unhas em gel", maxLength = 100, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "The service name is required.")
    @Size(max = 100, message = "The service name must not exceed 100 characters.")
    private String serviceName;

    @Schema(description = "Descrição do serviço.", example = "Alongamento de unhas com gel.", maxLength = 500, requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Size(max = 500, message = "The service description must not exceed 500 characters.")
    private String serviceDescription;

    @Schema(description = "Preço do serviço", example = "150.00", nullable = false, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    @Positive
    private BigDecimal servicePrice;

    @Schema(description = "Duração estimada de um serviço em minutos", example = "120", nullable = true, requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Positive
    private Integer serviceDurationMinutes;

    public ServiceCreateRequestDTO() {
    }

    public ServiceCreateRequestDTO(String serviceName, String serviceDescription, BigDecimal servicePrice, Integer serviceDurationMinutes) {
        this.serviceName = serviceName;
        this.serviceDescription = serviceDescription;
        this.servicePrice = servicePrice;
        this.serviceDurationMinutes = serviceDurationMinutes;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public BigDecimal getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(BigDecimal servicePrice) {
        this.servicePrice = servicePrice;
    }

    public Integer getServiceDurationMinutes() {
        return serviceDurationMinutes;
    }

    public void setServiceDurationMinutes(Integer serviceDurationMinutes) {
        this.serviceDurationMinutes = serviceDurationMinutes;
    }
}