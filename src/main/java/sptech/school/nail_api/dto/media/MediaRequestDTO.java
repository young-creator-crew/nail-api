package sptech.school.nail_api.dto.media;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.web.multipart.MultipartFile;

public class MediaRequestDTO {

    @NotNull(message = "O arquivo de imagem é obrigatório.")
    private MultipartFile media;

    @NotNull(message = "O ID do serviço é obrigatório.")
    @Positive(message = "O ID do serviço deve ser positivo.")
    private Integer serviceId;

    public MediaRequestDTO() {
    }

    public MediaRequestDTO(MultipartFile media, Integer serviceId) {
        this.media = media;
        this.serviceId = serviceId;
    }

    public MultipartFile getMedia() {
        return media;
    }

    public void setMedia(MultipartFile media) {
        this.media = media;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }
}
