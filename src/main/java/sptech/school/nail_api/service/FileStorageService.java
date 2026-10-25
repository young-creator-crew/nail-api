package sptech.school.nail_api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

// FileStorageService é responsável por armazenar as fotos
// em uma pasta local do computador, gerando um nome único
// para cada arquivo e retornando o caminho da imagem
// para ser salvo no banco de dados.

@Service
public class FileStorageService {

        @Value("${app.upload.dir}")
        private String uploadDir;

        public String salvar(MultipartFile foto) {

            if (foto == null || foto.isEmpty()) {
                throw new RuntimeException("Foto não enviada");
            }

            String tipo = foto.getContentType();
            String extensao;

            if ("image/jpeg".equals(tipo)) {
                extensao = ".jpg";
            } else if ("image/png".equals(tipo)) {
                extensao = ".png";
            } else if ("image/webp".equals(tipo)) {
                extensao = ".webp";
            } else {
                throw new RuntimeException(
                        "Formato inválido. Use JPG, PNG ou WEBP.");
            }

            try {
                Path pasta = Paths.get(uploadDir)
                        .toAbsolutePath()
                        .normalize();

                Files.createDirectories(pasta);

                String nomeArquivo = UUID.randomUUID() + extensao;

                Path destino = pasta.resolve(nomeArquivo).normalize();

                Files.copy(foto.getInputStream(), destino);

                return "/fotos/" + nomeArquivo;

            } catch (IOException e) {
                throw new RuntimeException(
                        "Erro ao salvar a foto", e);
            }
        }
    }


