package sptech.school.nail_api.mapper;

import sptech.school.nail_api.dto.service.create.ServiceAddonCreateRequestDTO;
import sptech.school.nail_api.dto.service.response.ServiceAddonResponseDTO;
import sptech.school.nail_api.dto.service.update.ServiceAddonUpdateRequestDTO;
import sptech.school.nail_api.dto.service.create.ServiceCreateRequestDTO;
import sptech.school.nail_api.dto.service.create.ServiceOptionCreateRequestDTO;
import sptech.school.nail_api.dto.service.response.ServiceOptionResponseDTO;
import sptech.school.nail_api.dto.service.update.ServiceOptionUpdateRequestDTO;
import sptech.school.nail_api.dto.service.response.ServiceResponseDTO;
import sptech.school.nail_api.dto.service.response.ServiceSummaryResponseDTO;
import sptech.school.nail_api.dto.service.update.ServiceUpdateRequestDTO;
import sptech.school.nail_api.entity.NailService;
import sptech.school.nail_api.entity.NailServiceAddon;
import sptech.school.nail_api.entity.NailServiceOption;

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

    public static NailServiceOption toOptionEntity(ServiceOptionCreateRequestDTO dto, NailService service) {
        NailServiceOption option = new NailServiceOption();

        option.setNailService(service);
        option.setOptionType(dto.getOptionType());
        option.setPrice(dto.getPrice());
        option.setDurationMinutes(dto.getDurationMinutes());
        option.setActive(true);

        return option;
    }

    public static NailServiceOption toOptionEntity(ServiceOptionUpdateRequestDTO dto, NailService service) {
        NailServiceOption option = new NailServiceOption();

        option.setNailService(service);
        option.setOptionType(dto.getOptionType());
        option.setPrice(dto.getPrice());
        option.setDurationMinutes(dto.getDurationMinutes());
        option.setActive(true);

        return option;
    }

    public static void updateOptionEntity(NailServiceOption option, ServiceOptionUpdateRequestDTO dto) {
        option.setOptionType(dto.getOptionType());
        option.setPrice(dto.getPrice());
        option.setDurationMinutes(dto.getDurationMinutes());
    }

    public static NailServiceAddon toAddonEntity(ServiceAddonCreateRequestDTO dto, NailService service) {
        NailServiceAddon addon = new NailServiceAddon();

        addon.setNailService(service);
        addon.setAddonName(dto.getAddonName().trim());
        addon.setAdditionalPrice(dto.getAdditionalPrice());
        addon.setActive(true);

        return addon;
    }

    public static NailServiceAddon toAddonEntity(ServiceAddonUpdateRequestDTO dto, NailService service) {
        NailServiceAddon addon = new NailServiceAddon();

        addon.setNailService(service);
        addon.setAddonName(dto.getAddonName().trim());
        addon.setAdditionalPrice(dto.getAdditionalPrice());
        addon.setActive(true);

        return addon;
    }

    public static void updateAddonEntity(NailServiceAddon addon, ServiceAddonUpdateRequestDTO dto) {
        addon.setAddonName(dto.getAddonName().trim());
        addon.setAdditionalPrice(dto.getAdditionalPrice());
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
        List<ServiceOptionResponseDTO> optionResponses =
                options.stream()
                        .map(ServiceMapper::toOptionResponseDTO)
                        .toList();

        List<ServiceAddonResponseDTO> addonResponses =
                addons.stream()
                        .map(ServiceMapper::toAddonResponseDTO)
                        .toList();

        return new ServiceResponseDTO(
                service.getIdNailService(),
                service.getServiceName(),
                service.getServiceDescription(),
                service.getPhotoUrl(),
                service.getActive(),
                optionResponses,
                addonResponses
        );
    }

    public static ServiceOptionResponseDTO toOptionResponseDTO(NailServiceOption option) {
        return new ServiceOptionResponseDTO(
                option.getIdNailServiceOption(),
                option.getOptionType(),
                option.getPrice(),
                option.getDurationMinutes()
        );
    }

    public static ServiceAddonResponseDTO toAddonResponseDTO(NailServiceAddon addon) {
        return new ServiceAddonResponseDTO(
                addon.getIdNailServiceAddon(),
                addon.getAddonName(),
                addon.getAdditionalPrice()
        );
    }
}