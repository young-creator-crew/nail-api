package sptech.school.nail_api.dto.service.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Response data for a service addon.")
public class ServiceAddonResponseDTO {

    @Schema(description = "Unique identifier of the service addon.", example = "20", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer idNailServiceAddon;

    @Schema(description = "Name of the addon.", example = "Nail art")
    private String addonName;

    @Schema(description = "Additional price charged for the addon.", example = "15.00", minimum = "0")
    private BigDecimal additionalPrice;

    public ServiceAddonResponseDTO() {
    }

    public ServiceAddonResponseDTO(Integer idNailServiceAddon, String addonName, BigDecimal additionalPrice) {
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