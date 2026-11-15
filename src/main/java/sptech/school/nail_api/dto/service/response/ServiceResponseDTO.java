package sptech.school.nail_api.dto.service.response;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Dados completos da resposta de um serviço, incluindo suas modalidades e complementos.")
public class ServiceResponseDTO {

    @Schema(description = "Identificador exclusivo do serviço.", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer idNailService;

    @Schema(description = "Nome do serviço.", example = "Alongamento de unhas em gel")
    private String serviceName;

    @Schema(description = "Descrição do serviço.", example = "Alongamento de unhas com gel.", nullable = true)
    private String serviceDescription;

    @Schema(description = "URL da foto do serviço.", example = "/fotos/2f59a2d0.jpg", nullable = true)
    private String photoUrl;

    @Schema(description = "Indica se o serviço está ativo.", example = "true")
    private Boolean active;

    @ArraySchema(
            arraySchema = @Schema(description = "Modalidades ativas disponíveis para este serviço."),
            schema = @Schema(implementation = ServiceOptionResponseDTO.class)
    )
    private List<ServiceOptionResponseDTO> options;

    @ArraySchema(
            arraySchema = @Schema(description = "Complementos ativos disponíveis para este serviço."),
            schema = @Schema(implementation = ServiceAddonResponseDTO.class)
    )
    private List<ServiceAddonResponseDTO> addons;

    public ServiceResponseDTO() {
    }

    public ServiceResponseDTO(Integer idNailService, String serviceName, String serviceDescription, String photoUrl, Boolean active, List<ServiceOptionResponseDTO> options, List<ServiceAddonResponseDTO> addons) {
        this.idNailService = idNailService;
        this.serviceName = serviceName;
        this.serviceDescription = serviceDescription;
        this.photoUrl = photoUrl;
        this.active = active;
        this.options = options;
        this.addons = addons;
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

    public List<ServiceOptionResponseDTO> getOptions() {
        return options;
    }

    public void setOptions(List<ServiceOptionResponseDTO> options) {
        this.options = options;
    }

    public List<ServiceAddonResponseDTO> getAddons() {
        return addons;
    }

    public void setAddons(List<ServiceAddonResponseDTO> addons) {
        this.addons = addons;
    }
}