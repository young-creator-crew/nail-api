package sptech.school.nail_api.dto.service.create;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import sptech.school.nail_api.enums.OptionType;

import java.math.BigDecimal;

@Schema(description = "Solicitar dados para a criação de uma modalidade de serviço.")
public class ServiceOptionCreateRequestDTO {

    @Schema(description = "Tipo de modalidade de serviço.", example = "APLICACAO", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "The service modality is required.")
    private OptionType optionType;

    @Schema(description = "Preço cobrado por esta modalidade de serviço.", example = "120.00", minimum = "0", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "The price is required.")
    @DecimalMin(value = "0.00", message = "The price must be greater than or equal to zero.")
    @Digits(integer = 8, fraction = 2, message = "The price must have at most 8 integer digits and 2 decimal places.")
    private BigDecimal price;

    @Schema(description = "Duração estimada da modalidade, em minutos.", example = "90", minimum = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "The estimated duration is required.")
    @Positive(message = "The estimated duration must be greater than zero.")
    private Integer durationMinutes;

    public ServiceOptionCreateRequestDTO() {
    }

    public ServiceOptionCreateRequestDTO(OptionType optionType, BigDecimal price, Integer durationMinutes) {
        this.optionType = optionType;
        this.price = price;
        this.durationMinutes = durationMinutes;
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