package sptech.school.nail_api.dto.service.update;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Schema(description = "Request data for updating or creating a service addon.")
public class ServiceAddonUpdateRequestDTO {

    @Schema(description = "Identifier of an existing addon. If omitted, a new addon will be created.", example = "20", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Positive(message = "The service addon ID must be positive.")
    private Integer idNailServiceAddon;

    @Schema(description = "Name of the addon.", example = "Nail art", maxLength = 100, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "The addon name is required.")
    @Size(max = 100, message = "The addon name must not exceed 100 characters.")
    private String addonName;

    @Schema(description = "Additional price charged for the addon.", example = "15.00", minimum = "0", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "The additional price is required.")
    @DecimalMin(value = "0.00", message = "The additional price must be greater than or equal to zero.")
    @Digits(integer = 8, fraction = 2, message = "The additional price must have at most 8 integer digits and 2 decimal places.")
    private BigDecimal additionalPrice;

    public ServiceAddonUpdateRequestDTO() {
    }

    public ServiceAddonUpdateRequestDTO(Integer idNailServiceAddon, String addonName, BigDecimal additionalPrice) {
        this.idNailServiceAddon = idNailServiceAddon;
        this.addonName = addonName;
        this.additionalPrice = additionalPrice;
    }

    public Integer getIdNailServiceAddon() {
        return idNailServiceAddon;
    }

    public void setIdNailServiceAddon(Integer idNailServiceAddon) {
        this.idNailServiceAddon = idNailServiceAddon;
    }

    public String getAddonName() {
        return addonName;
    }

    public void setAddonName(String addonName) {
        this.addonName = addonName;
    }

    public BigDecimal getAdditionalPrice() {
        return additionalPrice;
    }

    public void setAdditionalPrice(BigDecimal additionalPrice) {
        this.additionalPrice = additionalPrice;
    }
}