package sptech.school.nail_api.service;

import com.oracle.bmc.objectstorage.ObjectStorage;
import com.oracle.bmc.objectstorage.requests.DeleteObjectRequest;
import com.oracle.bmc.objectstorage.requests.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sptech.school.nail_api.dto.media.MediaRequestDTO;
import sptech.school.nail_api.dto.media.MediaResponseDTO;
import sptech.school.nail_api.entity.NailService;
import sptech.school.nail_api.entity.NailServiceMedia;
import sptech.school.nail_api.exception.media.MediaNotFoundException;
import sptech.school.nail_api.exception.media.MediaStorageException;
import sptech.school.nail_api.exception.service.ServiceNotActiveException;
import sptech.school.nail_api.exception.service.ServiceNotFoundException;
import sptech.school.nail_api.mapper.MediaMapper;
import sptech.school.nail_api.repository.NailServiceRepository;
import sptech.school.nail_api.repository.NailServiceMediaRepository;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.server.ServerNotActiveException;
import java.util.UUID;

@Service
public class MediaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MediaService.class);

    private final NailServiceMediaRepository mediaRepository;
    private final NailServiceRepository nailServiceRepository;
    private final ObjectStorage objectStorageClient;

    @Value("${spring.cloud.oci.region.static}")
    private String region;

    @Value("${oci.bucket.namespace}")
    private String namespace;

    @Value("${oci.bucket.name}")
    private String bucketName;

    public MediaService(
            NailServiceMediaRepository mediaRepository,
            NailServiceRepository nailServiceRepository,
            ObjectStorage objectStorageClient
    ) {
        this.mediaRepository = mediaRepository;
        this.nailServiceRepository = nailServiceRepository;
        this.objectStorageClient = objectStorageClient;
    }

    @Transactional
    public MediaResponseDTO save(MediaRequestDTO request) {
        if (request == null) {
            throw new IllegalArgumentException("Os dados da mídia são obrigatórios.");
        }

        MultipartFile media = request.getMedia();
        validarMedia(media);

        NailService nailService = buscarServico(request.getServiceId());
        String extensao = obterExtensao(media.getOriginalFilename());
        String objectName = "servicos/" + UUID.randomUUID() + extensao;
        boolean objectUploaded = false;

        try(InputStream inputStream = media.getInputStream()) {
            PutObjectRequest objectRequest = PutObjectRequest.builder()
                    .namespaceName(namespace)
                    .bucketName(bucketName)
                    .objectName(objectName)
                    .contentType(media.getContentType())
                    .contentLength(media.getSize())
                    .putObjectBody(inputStream)
                    .build();

            objectStorageClient.putObject(objectRequest);
            objectUploaded = true;

            String urlImage = gerarUrlPublica(objectName);
            NailServiceMedia entityMedia = MediaMapper.toEntity(request, urlImage, objectName);
            entityMedia.setNailService(nailService);

            return MediaMapper.toResponseDTO(mediaRepository.save(entityMedia));

        }catch (IOException e) {
            if (objectUploaded) {
                removerObjetoCompensacao(objectName);
            }
            throw new MediaStorageException("Falha ao ler o arquivo de imagem para upload.", e);
        } catch (RuntimeException e) {
            if (objectUploaded) {
                removerObjetoCompensacao(objectName);
            }
            throw e;
        }
    }

    @Transactional
    public void delete(Integer id) {
        NailServiceMedia media = mediaRepository.findById(id)
                .orElseThrow(() -> new MediaNotFoundException(id));

        DeleteObjectRequest request =   DeleteObjectRequest.builder()
                .namespaceName(namespace)
                .bucketName(bucketName)
                .objectName(media.getObjectName())
                .build();

        try {
            objectStorageClient.deleteObject(request);
        } catch (RuntimeException exception) {
            throw new MediaStorageException("Falha ao remover a mídia do armazenamento.", exception);
        }

        mediaRepository.delete(media);
    }

    private NailService buscarServico(Integer serviceId) {
        if (serviceId == null) {
            throw new IllegalArgumentException("O ID do serviço é obrigatório.");
        }
        if (serviceId <= 0) {
            throw new IllegalArgumentException("O ID do serviço deve ser positivo.");
        }

        NailService service = nailServiceRepository.findById(serviceId)
                .orElseThrow(() -> new ServiceNotFoundException(serviceId));

        if (!Boolean.TRUE.equals(service.getActive())) {
            throw new ServiceNotActiveException();
        }

        return service;
    }

    private void validarMedia(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("O arquivo de imagem é obrigatório.");
        }
    }

    private String obterExtensao(String originalFilename) {
        if (originalFilename == null) {
            return "";
        }

        int lastDotIndex = originalFilename.lastIndexOf('.');
        return lastDotIndex >= 0 ? originalFilename.substring(lastDotIndex) : "";
    }

    private String gerarUrlPublica(String objectName) {
        return String.format(
                "https://objectstorage.%s.oraclecloud.com/n/%s/b/%s/o/%s",
                region, namespace, bucketName, objectName
        );
    }

    private void removerObjetoCompensacao(String objectName) {
        try {
            DeleteObjectRequest request = DeleteObjectRequest.builder()
                    .namespaceName(namespace)
                    .bucketName(bucketName)
                    .objectName(objectName)
                    .build();

            objectStorageClient.deleteObject(request);
        } catch (RuntimeException exception) {
            LOGGER.error("Não foi possível remover o objeto OCI após falha no processamento da mídia: {}", objectName, exception);
        }
    }

}
