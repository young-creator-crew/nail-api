package sptech.school.nail_api.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "nail_service_addons")
public class NailServiceAddon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nail_service_addon")
    private Integer idNailServiceAddon;

    @ManyToOne
    @JoinColumn(name = "fk_nail_service", nullable = false)
    private NailService nailService;

    @Column(name = "addon_name", nullable = false, length = 100)
    private String addonName;

    @Column(name = "additional_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal additionalPrice;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    public NailServiceAddon() {
    }

    public Integer getIdNailServiceAddon() {
        return idNailServiceAddon;
    }

    public void setIdNailServiceAddon(Integer idNailServiceAddon) {
        this.idNailServiceAddon = idNailServiceAddon;
    }

    public NailService getNailService() {
        return nailService;
    }

    public void setNailService(NailService nailService) {
        this.nailService = nailService;
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