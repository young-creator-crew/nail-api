package sptech.school.nail_api.model;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "nail_services")
public class Procedure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idNailService")
    private Integer id;
    @Column(name = "service_name")
    private String name;
    private String category;
    private String description;
    private Double price;
    @Column(name = "estimatedDurationMinutes")
    private Integer estimatedDurationMinutes;
    @Column(name = "photo_url")
    private String photoUrl;


    public Procedure() {
    }

    public Procedure(Integer id, String name, String category, String description, Double price, Integer estimatedDurationMinutes, String photoUrl) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.estimatedDurationMinutes = estimatedDurationMinutes;
        this.photoUrl = photoUrl;
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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
