package sptech.school.nail_api.dto.service.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Dados de resposta para um complemento de serviço.")
public class ServiceAddonResponseDTO {

    @Schema(description = "Identificador exclusivo do complemento de serviço.", example = "20", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer idNailServiceAddon;

    @Schema(description = "Nome do complemento.", example = "Nail art")
    private String addonName;

    @Schema(description = "Preço adicional cobrado pelo complemento.", example = "15.00", minimum = "0")
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