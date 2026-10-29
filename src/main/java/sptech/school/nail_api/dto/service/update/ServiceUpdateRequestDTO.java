package sptech.school.nail_api.dto.service.update;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Schema(description = "Solicitar dados para a atualização de um serviço, incluindo sua lista completa de modalidades e complementos.")
public class ServiceUpdateRequestDTO {

    @Schema(description = "Nome do serviço.", example = "Alongamento de unhas em gel", maxLength = 100, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "The service name is required.")
    @Size(max = 100, message = "The service name must not exceed 100 characters.")
    private String serviceName;

    @Schema(description = "Descrição do serviço.", example = "Alongamento de unhas com gel.", maxLength = 500, nullable = true, requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Size(max = 500, message = "The service description must not exceed 500 characters.")
    private String serviceDescription;

    @ArraySchema(
            arraySchema = @Schema(description = "Lista completa das modalidades de serviço a serem mantidas ou criadas. As modalidades existentes que não constarem desta lista serão desativadas.", requiredMode = Schema.RequiredMode.REQUIRED),
            schema = @Schema(implementation = ServiceOptionUpdateRequestDTO.class)
    )
    @NotEmpty(message = "At least one service modality is required.")
    @Valid
    private List<ServiceOptionUpdateRequestDTO> options;

    @ArraySchema(
            arraySchema = @Schema(description = "Lista completa de complementos a manter ou criar. Use um array vazio caso não haja complementos. Complementos existentes que não constarem nesta lista serão desativados.", requiredMode = Schema.RequiredMode.REQUIRED),
            schema = @Schema(implementation = ServiceAddonUpdateRequestDTO.class)
    )
    @NotNull(message = "The addons list is required. Use an empty list if there are no addons.")
    @Valid
    private List<ServiceAddonUpdateRequestDTO> addons;

    public ServiceUpdateRequestDTO() {
    }

    public ServiceUpdateRequestDTO(String serviceName, String serviceDescription, List<ServiceOptionUpdateRequestDTO> options, List<ServiceAddonUpdateRequestDTO> addons) {
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

    public List<ServiceOptionUpdateRequestDTO> getOptions() {
        return options;
    }

    public void setOptions(List<ServiceOptionUpdateRequestDTO> options) {
        this.options = options;
    }

    public List<ServiceAddonUpdateRequestDTO> getAddons() {
        return addons;
    }

    public void setAddons(List<ServiceAddonUpdateRequestDTO> addons) {
        this.addons = addons;
    }
}