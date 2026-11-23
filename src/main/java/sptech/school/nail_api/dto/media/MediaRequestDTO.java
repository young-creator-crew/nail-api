package sptech.school.nail_api.dto.media;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public class MediaRequestDTO {

    @NotNull(message = "O arquivo de imagem é obrigatório.")
    private MultipartFile media;

    @NotNull(message = "O ID do serviço é obrigatório.")
    private Long serviceId;

    public MediaRequestDTO() {
    }

    public MediaRequestDTO(MultipartFile media, Long serviceId) {
        this.media = media;
        this.serviceId = serviceId;
    }

    public MultipartFile getMedia() {
        return media;
    }

    public void setMedia(MultipartFile media) {
        this.media = media;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }
}
