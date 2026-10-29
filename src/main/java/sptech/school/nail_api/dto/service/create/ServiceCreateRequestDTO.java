package sptech.school.nail_api.dto.service.create;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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

    @ArraySchema(
            arraySchema = @Schema(description = "Modalidades de atendimento disponíveis para este serviço.", requiredMode = Schema.RequiredMode.REQUIRED),
            schema = @Schema(implementation = ServiceOptionCreateRequestDTO.class)
    )
    @NotEmpty(message = "At least one service modality is required.")
    @Valid
    private List<ServiceOptionCreateRequestDTO> options;

    @ArraySchema(
            arraySchema = @Schema(description = "Complementos disponíveis para este serviço. Envie um array vazio caso o serviço não possua complementos.", requiredMode = Schema.RequiredMode.REQUIRED),
            schema = @Schema(implementation = ServiceAddonCreateRequestDTO.class)
    )
    @NotNull(message = "The addons list is required. Use an empty list if there are no addons.")
    @Valid
    private List<ServiceAddonCreateRequestDTO> addons;

    public ServiceCreateRequestDTO() {
    }

    public ServiceCreateRequestDTO(String serviceName, String serviceDescription, List<ServiceOptionCreateRequestDTO> options, List<ServiceAddonCreateRequestDTO> addons) {
        this.serviceName = serviceName;
        this.serviceDescription = serviceDescription;
        this.options = options;
        this.addons = addons;
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

    public List<ServiceOptionCreateRequestDTO> getOptions() {
        return options;
    }

    public void setOptions(List<ServiceOptionCreateRequestDTO> options) {
        this.options = options;
    }

    public List<ServiceAddonCreateRequestDTO> getAddons() {
        return addons;
    }

    public void setAddons(List<ServiceAddonCreateRequestDTO> addons) {
        this.addons = addons;
    }
}