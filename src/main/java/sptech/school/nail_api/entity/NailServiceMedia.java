package sptech.school.nail_api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "nail_service_media")
public class NailServiceMedia {

    @Id
    @Column(name = "id_photo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImg;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_nail_service", nullable = false)
    private NailService nailService;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "object_name")
    private String objectName;

    public NailServiceMedia() {
    }

    public Long getIdImg() {
        return idImg;
    }

    public void setIdImg(Long idImg) {
        this.idImg = idImg;
    }

    public NailService getNailService() {
        return nailService;
    }

    public void setNailService(NailService nailService) {
        this.nailService = nailService;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }
}
