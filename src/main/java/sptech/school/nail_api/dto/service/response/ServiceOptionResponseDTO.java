package sptech.school.nail_api.dto.service.response;

import io.swagger.v3.oas.annotations.media.Schema;
import sptech.school.nail_api.enums.OptionType;

import java.math.BigDecimal;

@Schema(description = "Dados de resposta para uma modalidade de serviço.")
public class ServiceOptionResponseDTO {

    @Schema(description = "Identificador exclusivo da modalidade de serviço.", example = "10", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer idNailServiceOption;

    @Schema(description = "Tipo de modalidade de serviço.", example = "APLICACAO")
    private OptionType optionType;

    @Schema(description = "Preço cobrado por esta modalidade de serviço.", example = "120.00", minimum = "0")
    private BigDecimal price;

    @Schema(description = "Duração estimada da modalidade, em minutos.", example = "90", minimum = "1")
    private Integer durationMinutes;

    public ServiceOptionResponseDTO() {
    }

    public ServiceOptionResponseDTO(Integer idNailServiceOption, OptionType optionType, BigDecimal price, Integer durationMinutes) {
        this.idNailServiceOption = idNailServiceOption;
        this.optionType = optionType;
        this.price = price;
        this.durationMinutes = durationMinutes;
    }

    public Integer getIdNailServiceOption() {
        return idNailServiceOption;
    }

    public void setIdNailServiceOption(Integer idNailServiceOption) {
        this.idNailServiceOption = idNailServiceOption;
    }

    public OptionType getOptionType() {
        return optionType;
    }

    public void setOptionType(OptionType optionType) {
        this.optionType = optionType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }
}