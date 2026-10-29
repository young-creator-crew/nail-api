package sptech.school.nail_api.dto.service.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Summary response data for a service.")
public class ServiceSummaryResponseDTO {

    @Schema(description = "Unique identifier of the service.", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer idNailService;

    @Schema(description = "Name of the service.", example = "Gel nail extensions")
    private String serviceName;

    @Schema(description = "Description of the service.", example = "Nail extensions using gel.", nullable = true)
    private String serviceDescription;

    @Schema(description = "URL of the service photo.", example = "/fotos/2f59a2d0.jpg", nullable = true)
    private String photoUrl;

    @Schema(description = "Indicates whether the service is active.", example = "true")
    private Boolean active;

    public ServiceSummaryResponseDTO() {
    }

    public ServiceSummaryResponseDTO(Integer idNailService, String serviceName, String serviceDescription, String photoUrl, Boolean active) {
        this.idNailService = idNailService;
        this.serviceName = serviceName;
        this.serviceDescription = serviceDescription;
        this.photoUrl = photoUrl;
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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}