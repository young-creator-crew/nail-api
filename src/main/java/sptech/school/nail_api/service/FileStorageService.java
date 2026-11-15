package sptech.school.nail_api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {

    private final Path uploadPath;

    public FileStorageService(@Value("${app.upload.dir}") String uploadDir) {
        this.uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
    }

    public String salvar(MultipartFile foto) {
        if (foto == null || foto.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The uploaded image is empty.");
        }

        String contentType = foto.getContentType();
        String extension = switch (contentType) {
            case "image/jpeg" -> ".jpg";
            case "image/png" -> ".png";
            case "image/webp" -> ".webp";
            case null, default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid image format. Use JPG, PNG, or WEBP.");
        };

        String fileName = UUID.randomUUID() + extension;
        Path destination = uploadPath.resolve(fileName).normalize();

        if (!destination.startsWith(uploadPath)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid file path.");
        }

        try {
            Files.createDirectories(uploadPath);

            try (InputStream inputStream = foto.getInputStream()) {
                Files.copy(inputStream, destination);
            }

            return "/fotos/" + fileName;
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to save the uploaded image.", e);
        }
    }
}