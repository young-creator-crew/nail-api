package sptech.school.nail_api.mapper;

import sptech.school.nail_api.dto.service.create.ServiceCreateRequestDTO;
import sptech.school.nail_api.dto.media.MediaResponseDTO;
import sptech.school.nail_api.dto.service.response.ServiceResponseDTO;
import sptech.school.nail_api.dto.service.response.ServiceSummaryResponseDTO;
import sptech.school.nail_api.dto.service.update.ServiceUpdateRequestDTO;
import sptech.school.nail_api.entity.NailService;

import java.util.List;

public class ServiceMapper {

    public static NailService toEntity(ServiceCreateRequestDTO dto) {
        NailService service = new NailService();

        service.setServiceName(dto.getServiceName().trim());
        service.setServiceDescription(dto.getServiceDescription() == null ? null : dto.getServiceDescription().trim());
        service.setPrice(dto.getServicePrice());
        service.setDurationMinutes(dto.getServiceDurationMinutes());
        service.setActive(true);

        return service;
    }

    public static void updateEntity(NailService service, ServiceUpdateRequestDTO dto) {
        service.setServiceName(dto.getServiceName().trim());
        service.setServiceDescription(dto.getServiceDescription() == null ? null : dto.getServiceDescription().trim());
        service.setPrice(dto.getServicePrice());
        service.setDurationMinutes(dto.getServiceDurationMinutes());
    }

    public static ServiceSummaryResponseDTO toSummaryResponseDTO(NailService service) {
        MediaResponseDTO photo = service.getUrlImages() == null || service.getUrlImages().isEmpty()
                ? null
                : MediaMapper.toResponseDTO(service.getUrlImages().getFirst());

        return new ServiceSummaryResponseDTO(
                service.getIdNailService(),
                service.getServiceName(),
                service.getServiceDescription(),
                photo,
                service.getActive()
        );
    }

    public static ServiceResponseDTO toResponseDTO(NailService service) {
        List<MediaResponseDTO> photos = service.getUrlImages() == null
                ? List.of()
                : service.getUrlImages().stream()
                .map(MediaMapper::toResponseDTO)
                .toList();

        return new ServiceResponseDTO(
                service.getIdNailService(),
                service.getServiceName(),
                service.getServiceDescription(),
                service.getPrice(),
                service.getDurationMinutes(),
                photos,
                service.getActive()
        );
    }

}