package sptech.school.nail_api.dto.media;

public class MediaResponseDTO {

    private Integer id;
    private String imageUrl;
    private Integer serviceId;

    public MediaResponseDTO() {
    }

    public MediaResponseDTO(Integer id, String imageUrl, Integer serviceId) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.serviceId = serviceId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }
}
