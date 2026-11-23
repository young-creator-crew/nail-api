package sptech.school.nail_api.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sptech.school.nail_api.dto.service.create.ServiceCreateRequestDTO;
import sptech.school.nail_api.dto.service.response.ServiceResponseDTO;
import sptech.school.nail_api.dto.service.response.ServiceSummaryResponseDTO;
import sptech.school.nail_api.dto.service.update.ServiceUpdateRequestDTO;
import sptech.school.nail_api.mapper.ServiceMapper;
import sptech.school.nail_api.entity.NailService;
import sptech.school.nail_api.exception.service.ServiceAlreadyExistsException;
import sptech.school.nail_api.exception.service.ServiceNotActiveExeption;
import sptech.school.nail_api.exception.service.ServiceNotFoundException;
import sptech.school.nail_api.repository.NailServiceRepository;

import java.util.List;

@Service
public class ServiceService {

    private final NailServiceRepository nailServiceRepository;

    public ServiceService(NailServiceRepository nailServiceRepository) {
        this.nailServiceRepository = nailServiceRepository;
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
        if (!Boolean.TRUE.equals(service.getActive())){
            throw new ServiceNotActiveExeption();
        }
        return ServiceMapper.toResponseDTO(service);
    }

    @Transactional
    public ServiceResponseDTO create(ServiceCreateRequestDTO request) {
        String serviceName = request.getServiceName().trim();

        if (nailServiceRepository.existsByServiceNameIgnoreCase(serviceName)) {
            throw new ServiceAlreadyExistsException();
        }

        NailService service = ServiceMapper.toEntity(request);
        service.setActive(true);

        NailService savedService;
        try {
            savedService = nailServiceRepository.saveAndFlush(service);
        } catch (DataIntegrityViolationException exception) {
            throw new ServiceAlreadyExistsException();
        }

        return get(savedService.getIdNailService());
    }

    @Transactional
    public ServiceResponseDTO update(ServiceUpdateRequestDTO request, Integer id) {
        NailService service = findById(id);
        if (!Boolean.TRUE.equals(service.getActive())) {
            throw new ServiceNotActiveExeption();
        }
        String serviceName = request.getServiceName().trim();

        if (nailServiceRepository.existsByServiceNameIgnoreCaseAndIdNailServiceNot(serviceName, id)) {
            throw new ServiceAlreadyExistsException();
        }

        ServiceMapper.updateEntity(service, request);

        try {
            nailServiceRepository.saveAndFlush(service);
        } catch (DataIntegrityViolationException exception) {
            throw new ServiceAlreadyExistsException();
        }

        return get(id);
    }

    @Transactional
    public void deactivate(Integer id) {
        NailService service = findById(id);
        if (!Boolean.TRUE.equals(service.getActive())) {
            throw new ServiceNotActiveExeption();
        }
        service.setActive(false);
        nailServiceRepository.save(service);
    }

    private NailService findById(Integer id) {
        return nailServiceRepository.findById(id).orElseThrow(() -> new ServiceNotFoundException(id));
    }
}