package sptech.school.nail_api.dto.media;

public class MediaResponseDTO {

    private String imageUrl;
    private String objectName;
    private Long serviceId;

    public MediaResponseDTO() {
    }

    public MediaResponseDTO(String imageUrl, String objectName, Long serviceId) {
        this.imageUrl = imageUrl;
        this.objectName = objectName;
        this.serviceId = serviceId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }
}
