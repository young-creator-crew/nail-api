package sptech.school.nail_api.dto.service.update;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Schema(description = "Request data for updating a service, including its complete list of modalities and addons.")
public class ServiceUpdateRequestDTO {

    @Schema(description = "Name of the service.", example = "Gel nail extensions", maxLength = 100, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "The service name is required.")
    @Size(max = 100, message = "The service name must not exceed 100 characters.")
    private String serviceName;

    @Schema(description = "Description of the service.", example = "Nail extensions using gel.", maxLength = 500, nullable = true, requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Size(max = 500, message = "The service description must not exceed 500 characters.")
    private String serviceDescription;

    @ArraySchema(
            arraySchema = @Schema(description = "Complete list of service modalities to retain or create. Existing modalities omitted from this list will be deactivated.", requiredMode = Schema.RequiredMode.REQUIRED),
            schema = @Schema(implementation = ServiceOptionUpdateRequestDTO.class)
    )
    @NotEmpty(message = "At least one service modality is required.")
    @Valid
    private List<ServiceOptionUpdateRequestDTO> options;

    @ArraySchema(
            arraySchema = @Schema(description = "Complete list of addons to retain or create. Use an empty array if there are no addons. Existing addons omitted from this list will be deactivated.", requiredMode = Schema.RequiredMode.REQUIRED),
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