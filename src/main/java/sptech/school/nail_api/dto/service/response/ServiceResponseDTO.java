package sptech.school.nail_api.dto.service.response;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import sptech.school.nail_api.dto.media.MediaResponseDTO;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "Dados completos da resposta de um serviço, incluindo suas modalidades e complementos.")
public class ServiceResponseDTO {

    @Schema(description = "Identificador exclusivo do serviço.", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer idNailService;

    @Schema(description = "Nome do serviço.", example = "Alongamento de unhas em gel")
    private String serviceName;

    @Schema(description = "Descrição do serviço.", example = "Alongamento de unhas com gel.", nullable = true)
    private String serviceDescription;

    @Schema(description = "Preço do serviço", example = "150.00", nullable = false, requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal servicePrice;

    @Schema(description = "Duração estimada de um serviço em minutos", example = "120", nullable = true, requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer serviceDurationMinutes;

    @ArraySchema(
            arraySchema = @Schema (description = "Todas as imagem de um serviço"),
            schema = @Schema(implementation = MediaResponseDTO.class)
    )
    private List<MediaResponseDTO> photosUrl;

    @Schema(description = "Indica se o serviço está ativo.", example = "true")
    private Boolean active;

    public ServiceResponseDTO() {
    }

    public ServiceResponseDTO(Integer idNailService, String serviceName, String serviceDescription, BigDecimal servicePrice, Integer serviceDurationMinutes, List<MediaResponseDTO> photosUrl, Boolean active) {
        this.idNailService = idNailService;
        this.serviceName = serviceName;
        this.serviceDescription = serviceDescription;
        this.servicePrice = servicePrice;
        this.serviceDurationMinutes = serviceDurationMinutes;
        this.photosUrl = photosUrl;
        this.active = active;
    }

    public Integer getIdNailService() {
        return idNailService;
    }

    public void setIdNailService(Integer idNailService) {
        this.idNailService = idNailService;
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

    public List<MediaResponseDTO> getPhotosUrl() {
        return photosUrl;
    }

    public void setPhotosUrl(List<MediaResponseDTO> photosUrl) {
        this.photosUrl = photosUrl;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}