package sptech.school.nail_api.dto.service.create;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Schema(description = "Request data for creating a service addon.")
public class ServiceAddonCreateRequestDTO {

    @Schema(description = "Name of the addon.", example = "Nail art", maxLength = 100, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "The addon name is required.")
    @Size(max = 100, message = "The addon name must not exceed 100 characters.")
    private String addonName;

    @Schema(description = "Additional price charged for the addon.", example = "15.00", minimum = "0", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "The additional price is required.")
    @DecimalMin(value = "0.00", message = "The additional price must be greater than or equal to zero.")
    @Digits(integer = 8, fraction = 2, message = "The additional price must have at most 8 integer digits and 2 decimal places.")
    private BigDecimal additionalPrice;

    public ServiceAddonCreateRequestDTO() {
    }

    public ServiceAddonCreateRequestDTO(String addonName, BigDecimal additionalPrice) {
        this.addonName = addonName;
        this.additionalPrice = additionalPrice;
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