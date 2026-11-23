package sptech.school.nail_api.service;

import org.springframework.web.multipart.MultipartFile;
import sptech.school.nail_api.dto.service.response.MediaResponseDTO;
import sptech.school.nail_api.repository.NailServiceMediaRepository;

public class MediaService {

    private final NailServiceMediaRepository mediaRepository;

    public MediaService(NailServiceMediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    public MediaResponseDTO save(MultipartFile media) {
        return null;
    }

    public void delete(Long id) {

    }
}
