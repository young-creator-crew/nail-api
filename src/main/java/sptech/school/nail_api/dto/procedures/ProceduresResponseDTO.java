package sptech.school.nail_api.dto.procedures;

public class ProceduresResponseDTO {
    private Integer id;
    private String name;
    private Double price;
    private String category;
    private String description;
    private Integer estimatedDurationMinutes;

    public ProceduresResponseDTO() {
    }

    public ProceduresResponseDTO(Integer id, String name, Double price, String category, String description, Integer estimatedDurationMinutes) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
        this.estimatedDurationMinutes = estimatedDurationMinutes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
