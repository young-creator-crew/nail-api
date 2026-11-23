package sptech.school.nail_api.dto.service.response;

import io.swagger.v3.oas.annotations.media.Schema;
import sptech.school.nail_api.dto.media.MediaResponseDTO;

@Schema(description = "Dados de resposta resumidos para um serviço.")
public class ServiceSummaryResponseDTO {

    @Schema(description = "Identificador exclusivo do serviço.", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer idNailService;

    @Schema(description = "Nome do serviço.", example = "Alongamento de unhas em gel")
    private String serviceName;

    @Schema(description = "Descrição do serviço.", example = "Alongamento de unhas com gel.", nullable = true)
    private String serviceDescription;

    @Schema(description = "Imagem principal do serviço", nullable = true)
    private MediaResponseDTO photosUrl;

    @Schema(description = "Indica se o serviço está ativo.", example = "true")
    private Boolean active;

    public ServiceSummaryResponseDTO() {
    }

    public ServiceSummaryResponseDTO(Integer idNailService, String serviceName, String serviceDescription, MediaResponseDTO photosUrl, Boolean active) {
        this.idNailService = idNailService;
        this.serviceName = serviceName;
        this.serviceDescription = serviceDescription;
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

    public MediaResponseDTO getPhotosUrl() {
        return photosUrl;
    }

    public void setPhotosUrl(MediaResponseDTO photosUrl) {
        this.photosUrl = photosUrl;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}