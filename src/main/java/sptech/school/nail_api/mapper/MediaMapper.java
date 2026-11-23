package sptech.school.nail_api.mapper;

import sptech.school.nail_api.dto.media.MediaRequestDTO;
import sptech.school.nail_api.dto.media.MediaResponseDTO;
import sptech.school.nail_api.entity.NailServiceMedia;

import java.util.List;

public class MediaMapper {

    public static NailServiceMedia toEntity(MediaRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        NailServiceMedia media = new NailServiceMedia();
        return media;
    }

    public static NailServiceMedia toEntity(MediaRequestDTO dto, String imageUrl, String objectName) {
        NailServiceMedia media = toEntity(dto);
        if (media != null) {
            media.setImgUrl(imageUrl);
            media.setObjectName(objectName);
        }
        return media;
    }

    public static MediaResponseDTO toResponseDTO(NailServiceMedia media) {
        if (media == null) {
            return null;
        }

        return new MediaResponseDTO(
                media.getImgUrl(),
                media.getObjectName(),
                media.getNailService() == null || media.getNailService().getIdNailService() == null
                        ? null
                        : Long.valueOf(media.getNailService().getIdNailService())
        );
    }

    public static MediaResponseDTO toResponse(NailServiceMedia media) {
        return toResponseDTO(media);
    }

    public static List<MediaResponseDTO> toResponseDTO(List<NailServiceMedia> medias) {
        if (medias == null) {
            return List.of();
        }

        return medias.stream()
                .map(MediaMapper::toResponseDTO)
                .toList();
    }
}
