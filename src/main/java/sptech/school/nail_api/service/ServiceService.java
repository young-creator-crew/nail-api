package sptech.school.nail_api.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import sptech.school.nail_api.dto.service.create.ServiceAddonCreateRequestDTO;
import sptech.school.nail_api.dto.service.update.ServiceAddonUpdateRequestDTO;
import sptech.school.nail_api.dto.service.create.ServiceCreateRequestDTO;
import sptech.school.nail_api.dto.service.create.ServiceOptionCreateRequestDTO;
import sptech.school.nail_api.dto.service.update.ServiceOptionUpdateRequestDTO;
import sptech.school.nail_api.dto.service.response.ServiceResponseDTO;
import sptech.school.nail_api.dto.service.response.ServiceSummaryResponseDTO;
import sptech.school.nail_api.dto.service.update.ServiceUpdateRequestDTO;
import sptech.school.nail_api.enums.OptionType;
import sptech.school.nail_api.mapper.ServiceMapper;
import sptech.school.nail_api.entity.NailService;
import sptech.school.nail_api.entity.NailServiceAddon;
import sptech.school.nail_api.entity.NailServiceOption;
import sptech.school.nail_api.repository.NailServiceAddonRepository;
import sptech.school.nail_api.repository.NailServiceOptionRepository;
import sptech.school.nail_api.repository.NailServiceRepository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ServiceService {

    private final NailServiceRepository nailServiceRepository;
    private final NailServiceOptionRepository optionRepository;
    private final NailServiceAddonRepository addonRepository;
    private final FileStorageService fileStorageService;

    public ServiceService(NailServiceRepository nailServiceRepository, NailServiceOptionRepository optionRepository, NailServiceAddonRepository addonRepository, FileStorageService fileStorageService) {
        this.nailServiceRepository = nailServiceRepository;
        this.optionRepository = optionRepository;
        this.addonRepository = addonRepository;
        this.fileStorageService = fileStorageService;
    }

    @Transactional(readOnly = true)
    public List<ServiceSummaryResponseDTO> getAll() {
        return nailServiceRepository.findAllActive()
                .stream()
                .map(ServiceMapper::toSummaryResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ServiceSummaryResponseDTO> getAllForManagement() {
        return nailServiceRepository.findAll()
                .stream()
                .map(ServiceMapper::toSummaryResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public ServiceResponseDTO get(Integer id) {
        NailService service = findById(id);

        List<NailServiceOption> options =
                optionRepository.findAllByNailService_IdNailService(id)
                        .stream()
                        .filter(option -> Boolean.TRUE.equals(option.getActive()))
                        .toList();

        List<NailServiceAddon> addons =
                addonRepository.findAllByNailService_IdNailService(id)
                        .stream()
                        .filter(addon -> Boolean.TRUE.equals(addon.getActive()))
                        .toList();

        return ServiceMapper.toResponseDTO(service, options, addons);
    }

    @Transactional
    public ServiceResponseDTO create(ServiceCreateRequestDTO request, MultipartFile foto) {
        String serviceName = request.getServiceName().trim();

        if (nailServiceRepository.existsByServiceNameIgnoreCase(serviceName)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A service with this name already exists.");
        }

        validateCreateOptionTypes(request.getOptions());

        NailService service = ServiceMapper.toEntity(request);
        service.setActive(true);

        if (foto != null && !foto.isEmpty()) {
            service.setPhotoUrl(fileStorageService.salvar(foto));
        }

        NailService savedService = nailServiceRepository.save(service);

        createOptions(savedService, request.getOptions());
        createAddons(savedService, request.getAddons());

        return get(savedService.getIdNailService());
    }

    @Transactional
    public ServiceResponseDTO update(ServiceUpdateRequestDTO request, Integer id, MultipartFile foto) {
        NailService service = findById(id);
        String serviceName = request.getServiceName().trim();

        if (nailServiceRepository.existsByServiceNameIgnoreCaseAndIdNailServiceNot(serviceName, id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Another service with this name already exists.");
        }

        validateUpdateOptionTypes(request.getOptions());

        ServiceMapper.updateEntity(service, request);

        if (foto != null && !foto.isEmpty()) {
            service.setPhotoUrl(fileStorageService.salvar(foto));
        }

        nailServiceRepository.save(service);

        updateOptions(service, request.getOptions());
        updateAddons(service, request.getAddons());

        return get(id);
    }

    @Transactional
    public void deactivate(Integer id) {
        NailService service = findById(id);
        service.setActive(false);
        nailServiceRepository.save(service);
    }

    private void createOptions(NailService service, List<ServiceOptionCreateRequestDTO> requestedOptions) {
        for (ServiceOptionCreateRequestDTO request : requestedOptions) {
            NailServiceOption option = ServiceMapper.toOptionEntity(request, service);
            optionRepository.save(option);
        }
    }

    private void createAddons(NailService service, List<ServiceAddonCreateRequestDTO> requestedAddons) {
        for (ServiceAddonCreateRequestDTO request : requestedAddons) {
            NailServiceAddon addon = ServiceMapper.toAddonEntity(request, service);
            addonRepository.save(addon);
        }
    }

    private void updateOptions(NailService service, List<ServiceOptionUpdateRequestDTO> requestedOptions) {
        Integer serviceId = service.getIdNailService();

        List<NailServiceOption> existingOptions = optionRepository.findAllByNailService_IdNailService(serviceId);

        Map<Integer, NailServiceOption> optionsById = new HashMap<>();

        for (NailServiceOption option : existingOptions) {
            optionsById.put(option.getIdNailServiceOption(), option);
        }

        Set<Integer> retainedIds = new HashSet<>();

        for (ServiceOptionUpdateRequestDTO request : requestedOptions) {
            Integer optionId = request.getIdNailServiceOption();

            if (optionId == null) {
                NailServiceOption newOption = ServiceMapper.toOptionEntity(request, service);
                optionRepository.save(newOption);
                continue;
            }

            if (!retainedIds.add(optionId)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The same service option cannot be sent more than once.");
            }

            NailServiceOption existingOption = optionsById.get(optionId);

            if (existingOption == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A service option does not belong to this service.");
            }

            ServiceMapper.updateOptionEntity(existingOption, request);
            existingOption.setActive(true);

            optionRepository.save(existingOption);
        }

        for (NailServiceOption option : existingOptions) {
            if (!retainedIds.contains(option.getIdNailServiceOption()) && Boolean.TRUE.equals(option.getActive())) {
                option.setActive(false);
                optionRepository.save(option);
            }
        }
    }

    private void updateAddons(NailService service, List<ServiceAddonUpdateRequestDTO> requestedAddons) {
        Integer serviceId = service.getIdNailService();

        List<NailServiceAddon> existingAddons = addonRepository.findAllByNailService_IdNailService(serviceId);

        Map<Integer, NailServiceAddon> addonsById = new HashMap<>();

        for (NailServiceAddon addon : existingAddons) {
            addonsById.put(addon.getIdNailServiceAddon(), addon);
        }

        Set<Integer> retainedIds = new HashSet<>();

        for (ServiceAddonUpdateRequestDTO request : requestedAddons) {
            Integer addonId = request.getIdNailServiceAddon();

            if (addonId == null) {
                NailServiceAddon newAddon = ServiceMapper.toAddonEntity(request, service);
                addonRepository.save(newAddon);
                continue;
            }

            if (!retainedIds.add(addonId)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The same service addon cannot be sent more than once.");
            }

            NailServiceAddon existingAddon = addonsById.get(addonId);

            if (existingAddon == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A service addon does not belong to this service.");
            }

            ServiceMapper.updateAddonEntity(existingAddon, request);
            existingAddon.setActive(true);

            addonRepository.save(existingAddon);
        }

        for (NailServiceAddon addon : existingAddons) {
            if (!retainedIds.contains(addon.getIdNailServiceAddon()) && Boolean.TRUE.equals(addon.getActive())) {
                addon.setActive(false);
                addonRepository.save(addon);
            }
        }
    }

    private void validateCreateOptionTypes(List<ServiceOptionCreateRequestDTO> options) {
        Set<OptionType> types = new HashSet<>();

        for (ServiceOptionCreateRequestDTO option : options) {
            if (!types.add(option.getOptionType())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Each service modality can only be included once.");
            }
        }
    }

    private void validateUpdateOptionTypes(List<ServiceOptionUpdateRequestDTO> options) {
        Set<OptionType> types = new HashSet<>();

        for (ServiceOptionUpdateRequestDTO option : options) {
            if (!types.add(option.getOptionType())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Each service modality can only be included once.");
            }
        }
    }

    private NailService findById(Integer id) {
        return nailServiceRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Service not found."));
    }
}