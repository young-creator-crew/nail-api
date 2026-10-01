package sptech.school.nail_api.model;

import java.time.LocalTime;

public class Procedure {
    private Integer id;
    private String name;
    private Double price;
    private Integer estimatedDurationMinutes;

    public Procedure() {
    }

    public Procedure(Integer id, String name, Double price, Integer estimatedDurationMinutes) {
        this.id = id;
        this.name = name;
        this.price = price;
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
