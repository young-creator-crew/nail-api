package sptech.school.nail_api.entity;

import jakarta.persistence.*;
import sptech.school.nail_api.enums.OptionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "nail_service_options")
public class NailServiceOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nail_service_option")
    private Integer idNailServiceOption;

    @ManyToOne
    @JoinColumn(name = "fk_nail_service", nullable = false)
    private NailService nailService;

    @Enumerated(EnumType.STRING)
    @Column(name = "option_type", nullable = false)
    private OptionType optionType;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "duration_minutes", nullable = false)
    private Integer durationMinutes;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    public NailServiceOption() {
    }

    public Integer getIdNailServiceOption() {
        return idNailServiceOption;
    }

    public void setIdNailServiceOption(Integer idNailServiceOption) {
        this.idNailServiceOption = idNailServiceOption;
    }

    public NailService getNailService() {
        return nailService;
    }

    public void setNailService(NailService nailService) {
        this.nailService = nailService;
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}