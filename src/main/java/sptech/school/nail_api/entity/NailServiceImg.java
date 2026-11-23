package sptech.school.nail_api.entity;

import jakarta.persistence.*;

@Entity
public class NailServiceImg {

    @Id
    @Column(name = "id_photo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImg;

    @Column(name = "fk_nail_service ")
    private Long fkService;

    @Column(name = "")
    private String imgUrl;

    public NailServiceImg() {
    }

    public Long getIdImg() {
        return idImg;
    }

    public void setIdImg(Long idImg) {
        this.idImg = idImg;
    }

    public Long getFkService() {
        return fkService;
    }

    public void setFkService(Long fkService) {
        this.fkService = fkService;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
