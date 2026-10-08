package sptech.school.nail_api.dto.procedures;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class ProceduresRequestDTO {
    @NotBlank(message = "The name cannot be null, empty, or contain only whitespace.")
    private String name;

    @NotNull(message = "The price cannot be null.")
    @PositiveOrZero(message = "The price must be greater than or equal to zero.")
    private Double price;

    @NotNull(message = "The estimated duration cannot be null.")
    @Positive(message = "The estimated duration must be greater than zero.")
    private Integer estimatedDurationMinutes;

    public ProceduresRequestDTO() {
    }

    public ProceduresRequestDTO(String name, Double price, Integer estimatedDurationMinutes) {
        this.name = name;
        this.price = price;
        this.estimatedDurationMinutes = estimatedDurationMinutes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getEstimatedDurationMinutes() {
        return estimatedDurationMinutes;
    }

    public void setEstimatedDurationMinutes(Integer estimatedDurationMinutes) {
        this.estimatedDurationMinutes = estimatedDurationMinutes;
    }

}
