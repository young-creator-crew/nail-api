package sptech.school.nail_api.mapper;

import sptech.school.nail_api.dto.service.create.ServiceCreateRequestDTO;
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
        service.setActive(true);

        return service;
    }

    public static void updateEntity(NailService service, ServiceUpdateRequestDTO dto) {
        service.setServiceName(dto.getServiceName().trim());
        service.setServiceDescription(dto.getServiceDescription() == null ? null : dto.getServiceDescription().trim());
    }

    public static ServiceSummaryResponseDTO toSummaryResponseDTO(NailService service) {
        return new ServiceSummaryResponseDTO(
                service.getIdNailService(),
                service.getServiceName(),
                service.getServiceDescription(),
                service.getPhotoUrl(),
                service.getActive()
        );
    }

    public static ServiceResponseDTO toResponseDTO(NailService service, List<NailServiceOption> options, List<NailServiceAddon> addons) {

        return new ServiceResponseDTO(
                service.getIdNailService(),
                service.getServiceName(),
                service.getServiceDescription(),
                service.getPhotoUrl(),
                service.getActive(),
                null,
                null
        );
    }

}