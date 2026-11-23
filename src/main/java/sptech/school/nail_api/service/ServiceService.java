package sptech.school.nail_api.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import sptech.school.nail_api.dto.service.create.ServiceCreateRequestDTO;
import sptech.school.nail_api.dto.service.response.ServiceResponseDTO;
import sptech.school.nail_api.dto.service.response.ServiceSummaryResponseDTO;
import sptech.school.nail_api.dto.service.update.ServiceUpdateRequestDTO;
import sptech.school.nail_api.mapper.ServiceMapper;
import sptech.school.nail_api.entity.NailService;
import sptech.school.nail_api.repository.NailServiceRepository;

import java.util.List;

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
    public ServiceResponseDTO create(ServiceCreateRequestDTO request) {
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
    public ServiceResponseDTO update(ServiceUpdateRequestDTO request, Integer id) {
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

        return get(id);
    }

    @Transactional
    public void deactivate(Integer id) {
        NailService service = findById(id);
        service.setActive(false);
        nailServiceRepository.save(service);
    }

    private NailService findById(Integer id) {
        return nailServiceRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Service not found."));
    }
}