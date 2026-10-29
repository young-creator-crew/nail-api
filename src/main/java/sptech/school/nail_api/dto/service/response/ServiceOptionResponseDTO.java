package sptech.school.nail_api.dto.service.response;

import io.swagger.v3.oas.annotations.media.Schema;
import sptech.school.nail_api.enums.OptionType;

import java.math.BigDecimal;

@Schema(description = "Response data for a service modality.")
public class ServiceOptionResponseDTO {

    @Schema(description = "Unique identifier of the service modality.", example = "10", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer idNailServiceOption;

    @Schema(description = "Type of service modality.", example = "APLICACAO")
    private OptionType optionType;

    @Schema(description = "Price charged for this service modality.", example = "120.00", minimum = "0")
    private BigDecimal price;

    @Schema(description = "Estimated duration of the modality, in minutes.", example = "90", minimum = "1")
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